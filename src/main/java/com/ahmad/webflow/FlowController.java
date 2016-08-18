package com.ahmad.webflow;

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
import com.ahmad.dao.ShippingAddressDAO;
import com.ahmad.model.BillingAddress;
import com.ahmad.model.CardDetail;
import com.ahmad.model.Customer;
import com.ahmad.model.OrderDetail;
import com.ahmad.model.OrderedItems;
import com.ahmad.model.ShippingAddress;

@Component
public class FlowController {

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

	public FlowController initFlow() {
		return new FlowController();
	}

	public String addShippingAddress(ShippingAddress shippingAddress) {
		customer = customerDAO
				   .getCustomerByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		this.shippingAddress.setCustomerId(customer.getCustomerId());
		this.shippingAddress.setLine1(shippingAddress.getLine1());
		this.shippingAddress.setLine2(shippingAddress.getLine2());
		this.shippingAddress.setCity(shippingAddress.getCity());
		this.shippingAddress.setState(shippingAddress.getState());
		this.shippingAddress.setCountry(shippingAddress.getCountry());
		this.shippingAddress.setZipCode(shippingAddress.getZipCode());
		
		return "success";
	}
	
	public String addBillingAddress(BillingAddress billingAddress)
	{
		customer = customerDAO
				   .getCustomerByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		this.billingAddress.setCustomerId(customer.getCustomerId());
		this.billingAddress.setLine1(billingAddress.getLine1());
		this.billingAddress.setLine2(billingAddress.getLine2());
		this.billingAddress.setCity(billingAddress.getCity());
		this.billingAddress.setState(billingAddress.getState());
		this.billingAddress.setCountry(billingAddress.getCountry());
		this.billingAddress.setZipCode(billingAddress.getZipCode());
		return "success";
	}
	
	public String addCardDetails(CardDetail cardDetail)
	{
		customer = customerDAO
				   .getCustomerByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		shippingAddressDAO.saveOrUpdate(shippingAddress);
		billingAddressDAO.saveOrUpdate(billingAddress);
		cardDetail.setCustomerId(customer.getCustomerId());
		cardDetail.setTotalCost(cartDAO.getCartByCustomerId(customer.getCustomerId()).getGrandTotal());
		cardDetailDAO.saveOrUpdate(cardDetail);
		return "success";
	}

}
