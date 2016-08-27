package com.ahmad.webflow;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ahmad.dao.BillingAddressDAO;
import com.ahmad.dao.CardDetailDAO;
import com.ahmad.dao.CartDAO;
import com.ahmad.dao.CartItemDAO;
import com.ahmad.dao.CustomerDAO;
import com.ahmad.dao.OrderDetailDAO;
import com.ahmad.dao.OrderedItemsDAO;
import com.ahmad.dao.ProductDAO;
import com.ahmad.dao.ShippingAddressDAO;
import com.ahmad.model.BillingAddress;
import com.ahmad.model.CardDetail;
import com.ahmad.model.Cart;
import com.ahmad.model.CartItem;
import com.ahmad.model.Customer;
import com.ahmad.model.OrderDetail;
import com.ahmad.model.OrderedItems;
import com.ahmad.model.Product;
import com.ahmad.model.ShippingAddress;
import com.ahmad.temp.CheckoutTemp;

@Component
public class FlowController {
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ShippingAddress shippingAddress;

	@Autowired
	private BillingAddress billingAddress;

	@Autowired
	private OrderDetail orderDetail;

	@Autowired
	private OrderedItems orderedItems;

	@Autowired
	private CardDetail cardDetail;

	@Autowired
	private ShippingAddressDAO shippingAddressDAO;

	@Autowired
	private BillingAddressDAO billingAddressDAO;

	@Autowired
	private OrderedItemsDAO orderedItemsDAO;

	@Autowired
	private OrderDetailDAO orderDetailDAO;

	@Autowired
	private CardDetailDAO cardDetailDAO;
	@Autowired
	Customer customer;
	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	CartDAO cartDAO;
	@Autowired
	CartItemDAO cartItemDAO;
	@Autowired
	HttpSession httpSession;
	@Autowired
	Product product;
	CheckoutTemp checkoutTemp = new CheckoutTemp();

	public CheckoutTemp initFlow() {
		customer = customerDAO.getCustomerByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		checkoutTemp.setCart(cartDAO.getCartByCustomerId(customer.getCustomerId()));
		checkoutTemp.setCustomer(customerDAO.getCustomerByUserName(customer.getUsername()));
		return checkoutTemp;
	}

	public String addShippingAddress(CheckoutTemp checkoutTemp, ShippingAddress shippingAddress) {
		customer = customerDAO.getCustomerByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

		shippingAddress.setCustomerId(customer.getCustomerId());
		checkoutTemp.setShippingAddress(shippingAddress);
		/*
		 * this.shippingAddress.setCustomerId(customer.getCustomerId());
		 * this.shippingAddress.setLine1(shippingAddress.getLine1());
		 * this.shippingAddress.setLine2(shippingAddress.getLine2());
		 * this.shippingAddress.setCity(shippingAddress.getCity());
		 * this.shippingAddress.setState(shippingAddress.getState());
		 * this.shippingAddress.setCountry(shippingAddress.getCountry());
		 * this.shippingAddress.setZipCode(shippingAddress.getZipCode());
		 */

		return "success";
	}

	public String addBillingAddress(CheckoutTemp checkoutTemp, BillingAddress billingAddress) {
		customer = customerDAO.getCustomerByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		billingAddress.setCustomerId(customer.getCustomerId());
		checkoutTemp.setBillingAddress(billingAddress);
		/*
		 * this.billingAddress.setCustomerId(customer.getCustomerId());
		 * this.billingAddress.setLine1(billingAddress.getLine1());
		 * this.billingAddress.setLine2(billingAddress.getLine2());
		 * this.billingAddress.setCity(billingAddress.getCity());
		 * this.billingAddress.setState(billingAddress.getState());
		 * this.billingAddress.setCountry(billingAddress.getCountry());
		 * this.billingAddress.setZipCode(billingAddress.getZipCode());
		 */
		return "success";
	}

	public String addCardDetails(CheckoutTemp checkoutTemp, CardDetail cardDetail) {
		System.out.println(checkoutTemp);
		customer = customerDAO.getCustomerByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		shippingAddressDAO.saveOrUpdate(checkoutTemp.getShippingAddress());
		billingAddressDAO.saveOrUpdate(checkoutTemp.getBillingAddress());

		cardDetail.setCustomerId(customer.getCustomerId());
		cardDetail.setTotalCost(cartDAO.getCartByCustomerId(customer.getCustomerId()).getGrandTotal());
		cardDetailDAO.saveOrUpdate(cardDetail);

		List<CartItem> listOfCartItems = cartItemDAO.getCartItemsByCustomerId(customer.getCustomerId());
		System.out.println(listOfCartItems);
		for (CartItem item : listOfCartItems) {
			// if the product is present but its less than stock or its zero or
			// id is null so don't consider it
			if (item.getProductId() == null || productDAO.get(item.getProductId()).getQuantity() == 0
					|| item.getQuantity() > productDAO.get(item.getProductId()).getQuantity()) {

			} else {
				orderedItems = new OrderedItems();
				orderedItems.setCustomerId(item.getCustomerId());
				orderedItems.setProductName(productDAO.get(item.getProductId()).getProductName());
				orderedItems.setProductId(item.getProductId());
				orderedItems.setQuantity(item.getQuantity());
				orderedItems.setTotalPrice(item.getTotalPrice());
				orderedItemsDAO.saveOrUpdate(orderedItems);

				// Now update the product as customer buys the product it will
				// decrease
				product = productDAO.get(orderedItems.getProductId());

				if (product.getQuantity() <= 0) {
					product.setQuantity(0);
					product.setOutOffStock(true);

				} else {
					product.setQuantity(product.getQuantity() - orderedItems.getQuantity());
					if (product.getQuantity() <= 0) {
						product.setQuantity(0);
						product.setOutOffStock(true);
					}
				}
				productDAO.saveOrUpdate(product);
				cartItemDAO.delete(item.getCartItemId());
			}
		}

		listOfCartItems = cartItemDAO.getCartItemsByCustomerId(customer.getCustomerId());
		Cart cart = new Cart();
		cart = cartDAO.getCartByCustomerId(customer.getCustomerId());
		double grandTotal = 0;
		for (CartItem item : listOfCartItems) {
			// Check whether the cart item is in stock or it not exists
			// Also check is there any item which should not be considered
			if (item.getProductId() == null || productDAO.get(item.getProductId()).getQuantity() == 0
					|| item.getQuantity() > productDAO.get(item.getProductId()).getQuantity()) {
			}

			/* grandTotal = grandTotal; */
			else {
				// So all of the above condition is false do this
				grandTotal = grandTotal + item.getTotalPrice();

				cart.setGrandTotal(grandTotal);

				cart.setCartId(cart.getCartId());
				cart.setCustomerId(cart.getCustomerId());
				cart.setNoOfProducts(listOfCartItems.size());

			}
			cartDAO.saveOrUpdate(cart);
		}
		httpSession.setAttribute("noOfProducts", cartItemDAO.getCartItemsByCustomerId(customer.getCustomerId()).size());

		return "success";
	}

}
