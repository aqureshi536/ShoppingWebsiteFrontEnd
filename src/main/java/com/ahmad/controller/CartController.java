package com.ahmad.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ahmad.dao.CartDAO;
import com.ahmad.dao.CartItemDAO;
import com.ahmad.dao.CategoryDAO;
import com.ahmad.dao.CustomerDAO;
import com.ahmad.dao.ProductDAO;
import com.ahmad.dao.SupplierDAO;
import com.ahmad.model.Cart;
import com.ahmad.model.CartItem;
import com.ahmad.model.Category;
import com.ahmad.model.Customer;
import com.ahmad.model.OrderedItems;
import com.ahmad.model.Product;
import com.ahmad.model.Supplier;
import com.ahmad.viewmodel.CartItemModel;

@Controller

public class CartController {

	@Autowired
	private Product product;
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private Category category;
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private Supplier supplier;
	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private Customer customer;
	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private Cart cart;
	@Autowired
	private CartItem cartItem;

	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private CartItemDAO cartItemDAO;

	@Autowired
	HttpSession httpSession;

	@RequestMapping("/user/cart/")
	public ModelAndView viewCart(Model model, Principal userName,
			@RequestParam(value = "cartItemRemoved", required = false) String cartItemRemoved,
			@RequestParam(value = "updateSuccessfull", required = false) String updateSuccessfull,
			@RequestParam(value = "cancelUpdate", required = false) String cancelUpdate,
			@RequestParam(value = "cannotUpdate", required = false) String cannotUpdate)

	{
		ModelAndView mv = new ModelAndView("index");
		String customerName = userName.getName();
		if (cartItemRemoved != null) {
			model.addAttribute("cartItemRemoved", "Cart item removed successfully");
		}
		if (updateSuccessfull != null) {
			model.addAttribute("updateCartSuccessfull", "Quantity updated successfully");
		}
		if (cancelUpdate != null) {
			model.addAttribute("cancelUpdate",
					"Sorry, Quantity of product you are trying to update is insufficient in our stock");
		}
		if (cannotUpdate != null) {
			model.addAttribute("cannotUpdate", "Sorry, The product you are trying to update is not available");
		}
		customer = customerDAO.getCustomerByUserName(customerName);
		String customerId = customer.getCustomerId();

		List<CartItemModel> cartItems = null;
		// Check whether the customer cart exist in database or not
		if (cartDAO.getCartByCustomerId(customerId) != null) {
			int noOfProducts=updateCartAgain(cartDAO.getCartByCustomerId(customerId).getCartId(), customerId);
			httpSession.setAttribute("noOfProducts",noOfProducts);
			Cart selectedCart = cartDAO.getCartByCustomerId(customerId);
			// To save from null pointer exception we will check whether there
			// are
			// products or not

			if (returnCartItemModelList(customerId) != null || !returnCartItemModelList(customerId).isEmpty()) {
				// if present then add to the list
				cartItems = returnCartItemModelList(customerId);
				mv.addObject("noOfProducts", cartItems.size());
				double grandTotal = selectedCart.getGrandTotal();
				for (CartItemModel item : cartItems) {

					// This is to check whether the productId is null as it was
					// deleted from database or quantity of
					// the item i zero then deduct its price from the cart grand
					// total
					if (item.getCartItem().getProductId() == null || item.getCartItem().getQuantity() < 1
							|| productDAO.get(item.getCartItem().getProductId()).getQuantity() <= 0) {
						grandTotal = selectedCart.getGrandTotal() - item.getCartItem().getTotalPrice();
						selectedCart.setGrandTotal(grandTotal);
						cartDAO.saveOrUpdate(selectedCart);

					}
				}
			
				// pass a model saying that the grand total is zero
				// If the grand total is zero than set no of products to zero
				if (cartDAO.getCartByCustomerId(customerId).getGrandTotal() <= 0) {
					model.addAttribute("zeroGrandTotal", "Product not present");

				} // Else set the real grand total
				else {
					model.addAttribute("grandTotal", selectedCart.getGrandTotal());

				}

			}//If list of cart items is empty then execute this 
			else {
				model.addAttribute("cartEmpty", "No items present in the cart");
//				mv.addObject("noOfProducts", 0);
			}

		}//If cart doesn't exist execute this 
		else {
			model.addAttribute("cartEmpty", "No items present in the cart");
			mv.addObject("noOfProducts", 0);
		}

	if (cartItems.size() < 1||cartItems==null) {
			model.addAttribute("cartEmpty", "No items present in the cart");
			mv.addObject("noOfProducts", 0);
		}
		// Gets the category on the nav bar
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================

		
		mv.addObject("isClickedViewCart", true);
		mv.addObject("displayCart", "true");
		mv.addObject("activeNavMenu", "viewCart");

		return mv;
	}

	// Method to get name of product
	public List<CartItemModel> returnCartItemModelList(String customerId) {

		List<CartItem> cartItems = cartItemDAO.getCartItemsByCustomerId(customerId);

		List<CartItemModel> cartItemModelList = new ArrayList<>();

		CartItemModel cartItemModel = null;

		for (CartItem item : cartItems) {
			cartItemModel = new CartItemModel();
			cartItemModel.setCartItem(item);
			if (item.getProductId() != null && !item.getProductId().isEmpty()) {
				product = productDAO.get(item.getProductId());
				cartItemModel.setProductName(product.getProductName());
			} else {
				cartItemModel.setProductName("Currently not avilable");
			}
			cartItemModelList.add(cartItemModel);
		}
		return cartItemModelList;
	}

	@RequestMapping("/user/cart/addToCart/{productId}")
	public String addToCart(@PathVariable("productId") String productId, Model model, Principal userName) {

		// System.out.println(name);

		// 1.Get the customer id by its user name
		String customerName = userName.getName();
		customer = customerDAO.getCustomerByUserName(customerName);
		String customerId = customer.getCustomerId();

		// 2.Check whether his cart is present in the cart table
		// If cart is not present then make a cart for him

		if (cartDAO.getCartByCustomerId(customerId) == null) {
			cart = new Cart();
			cart.setCustomerId(customerId);
			cartDAO.saveOrUpdate(cart);

			// cartItem.setCartId(cart.getCartId());
		}

		// This statement changes the cart if cart is present and due to
		// unpresence of this there where errors
		else {
			cart = cartDAO.getCartByCustomerId(customerId);
		}

		String cartId = cart.getCartId();

		// 3.get the product price

		product = productDAO.get(productId);

		// If cart is present then go into the cartItem table and search for
		// product
		// this customer selected whether it exists or it is a new product.
		// -------------
		// passing the customerId and productId to a method name returnCartItem
		// through a method

		// if we get null means the product is not present
		// String
		// redirectPage="redirect:/productDetail/{productId}?addToCartSuccessMessage";
		String redirectPage = null;
		int codeRecieved = addCartItem(customerId, productId, cartId);
		switch (codeRecieved) {
		case 0: {
			cartItem = new CartItem();
			cartItem.setCartId(cartId);
			cartItem.setCustomerId(customerId);
			cartItem.setProductId(product.getProductId());
			cartItem.setQuantity(1);
			cartItem.setTotalPrice(product.getPrice());
			cartItemDAO.saveOrUpdate(cartItem);
			System.out.println("Insertion of cartItem");
			updateCartAgain(cartId, customerId);
			redirectPage = "redirect:/productDetail/{productId}?addToCartSuccessMessage";
			break;
		}
		case 1:
			redirectPage = "redirect:/productDetail/{productId}?cancelledAddToCart";
			break;
		case 2:
			redirectPage = "redirect:/productDetail/{productId}?addToCartSuccessMessage";
		}
		httpSession.setAttribute("noOfProducts", returnNoOfProducts(customerId));
		// Now navigate to the same page
		return redirectPage;
	}

	// This function will update the cart
	public int updateCartAgain(String cartId, String customerId) {

		List<CartItem> listOfSelectedCartItems;
		// Now after getting the cartItem change grandTotal and No of Products
		listOfSelectedCartItems = cartItemDAO.getCartItemsByCustomerId(customerId);
		double grandTotal = 0;
		for (CartItem item : listOfSelectedCartItems) {
			grandTotal = grandTotal + item.getTotalPrice();
		}
		cart.setGrandTotal(grandTotal);

		int noOfProducts = listOfSelectedCartItems.size();

		cart.setCartId(cartId);
		cart.setCustomerId(customerId);
		cart.setNoOfProducts(noOfProducts);
		cartDAO.saveOrUpdate(cart); // This method updates the cart

		return noOfProducts;
		// =========== Completed Adding the item to cart =====

	}

	// This is the method which perform all the operations related to addition
	// of product cartItem
	public int addCartItem(String customerId, String productId, String cartId) {

		List<CartItem> listOfSelectedCartItems = cartItemDAO.getCartItemsByCustomerId(customerId);
		Product product = productDAO.get(productId);
		for (CartItem item : listOfSelectedCartItems) {
			String itemProductId = item.getProductId();
			System.out.println(itemProductId);
			if (itemProductId != null) {
				if (itemProductId.equals(product.getProductId())) {
					System.out.println(item.getCartItemId());
					item.setCartItemId(item.getCartItemId());

					System.out.println(item.getQuantity());
					// Check the whether the user is adding the item to cart
					// more
					// than its quantity
					if (item.getQuantity() >= product.getQuantity()) {
						return 1; // This is a code which denotes product is
									// not enough to added to his cart as its
									// present

						/* "redirect:/productDetail/{productId}?cancelledAddToCart"; */
					} else {
						item.setQuantity(item.getQuantity() + 1);

						System.out.println(item.getTotalPrice());
						item.setTotalPrice(item.getTotalPrice() + product.getPrice());

						System.out.println(item.toString());
						cartItemDAO.saveOrUpdate(item);
						updateCartAgain(cartId, customerId);
						return 2; // This is a code which denotes product added
									// to
									// cart successfully
						/* "redirect:/productDetail/{productId}?addToCartSuccessMessage"; */
					} // ---else ends
				} // ----- outer if ends -----
			} // --- if to manage whether the product exist or not
		} // ----- for loop ends
			// ------- If ends
		return 0; // Product is not present in cart we need to generate a new
					// one
	}

	// To remove the cart items one by one from the cart
	@RequestMapping("/user/cart/remove/{cartItemId}")
	public String removeCartItems(@PathVariable("cartItemId") String cartItemId, Model model, Principal username) {
		cartItem = cartItemDAO.getCartItem(cartItemId);
		String customerId = cartItem.getCustomerId();
		String cartId = cartItem.getCartId();
		cartItemDAO.delete(cartItemId);
		int noOfProducts = updateCartAgain(cartId, customerId);
		model.addAttribute("noOfProducts", noOfProducts);
		httpSession.setAttribute("noOfProducts", returnNoOfProducts(customerId));
		return "redirect:/user/cart/?cartItemRemoved";
	}

	// This is the method to count cart items
	public int returnNoOfProducts(String customerId) {
		if (customerId != null) {
			int noOfProduct = cartDAO.getCartByCustomerId(customerId).getNoOfProducts();
			return noOfProduct;
		}
		return 0;
	}

	// To get the listOf ordered items
	@RequestMapping("/user/cart/history")
	public ModelAndView listOrderedItems(Principal principal, Model model) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("isViewHistoryclicked", "true");
		customer = customerDAO.getCustomerByUserName(principal.getName());

		List<OrderedItems> listofOrderedItems = cartDAO.listOrderedItems(customer.getCustomerId());
		if (listofOrderedItems != null && !listofOrderedItems.isEmpty()) {
			model.addAttribute("listOfOrderedItems", listofOrderedItems);
		} else {
			model.addAttribute("noProductsinHistory", "No products ordered till now");
		}
		mv.addObject("activeNavMenu", "viewCart");
		return mv;

	}

	// Add a product to cart on all products page
	@RequestMapping("/user/allProducts/addToCart/{productId}")
	public String addToCartForAllProducts(@PathVariable("productId") String productId, Model model,
			Principal userName) {

		// System.out.println(name);

		// 1.Get the customer id by its user name
		String customerName = userName.getName();
		customer = customerDAO.getCustomerByUserName(customerName);
		String customerId = customer.getCustomerId();

		// 2.Check whether his cart is present in the cart table
		// If cart is not present then make a cart for him

		if (cartDAO.getCartByCustomerId(customerId) == null) {
			cart = new Cart();
			cart.setCustomerId(customerId);
			cartDAO.saveOrUpdate(cart);

			// cartItem.setCartId(cart.getCartId());
		}

		// This statement changes the cart if cart is present and due to
		// unpresence of this there where errors
		else {
			cart = cartDAO.getCartByCustomerId(customerId);
		}

		String cartId = cart.getCartId();

		// 3.get the product price

		product = productDAO.get(productId);

		// If cart is present then go into the cartItem table and search for
		// product
		// this customer selected whether it exists or it is a new product.
		// -------------
		// passing the customerId and productId to a method name returnCartItem
		// through a method

		// if we get null means the product is not present

		String redirect = null;
		int codeRecieved = addCartItem(customerId, productId, cartId);
		switch (codeRecieved) {
		case 0: {
			cartItem = new CartItem();
			cartItem.setCartId(cartId);
			cartItem.setCustomerId(customerId);
			cartItem.setProductId(product.getProductId());
			cartItem.setQuantity(1);
			cartItem.setTotalPrice(product.getPrice());
			cartItemDAO.saveOrUpdate(cartItem);
			System.out.println("Insertion of cartItem");
			updateCartAgain(cartId, customerId);
			redirect = "redirect:/allProducts?addToCartAllProducts";
			break;
		}
		case 1:
			redirect = "redirect:/allProducts?cancelledAddToCart";
			break;
		case 2:
			redirect = "redirect:/allProducts?addToCartAllProducts";

		}
		httpSession.setAttribute("noOfProducts", returnNoOfProducts(customerId));
		// Now navigate to the same page
		return redirect;
	}

	// To update the cart quantity
	@RequestMapping("/user/cart/update")
	public String updateCartItemQuantity(@RequestParam(value = "cartItemId") String cartItemId,
			@RequestParam(value = "cartQuantity") int quantity) {
		String redirect = null;
		cartItem = cartItemDAO.getCartItem(cartItemId);

		String productId = cartItem.getProductId();
		// check whether the product is present or not in database
		if (productId != null) {
			product = productDAO.get(cartItem.getProductId());
			int productQuantity = product.getQuantity();
			// Check whether the cartItem quantity is more or enough
			if (quantity > product.getQuantity()) {
				redirect = "redirect:/user/cart/?cancelUpdate";
			} /*
				 * else if (quantity > 5) { redirect =
				 * "redirect:/user/cart/?maxCartItem"; }
				 */ else {
				cartItem.setQuantity(quantity);
				cartItem.setTotalPrice(quantity * product.getPrice());
				cartItemDAO.saveOrUpdate(cartItem);
				updateCartAgain(cartItem.getCartId(), cartItem.getCustomerId());
				redirect = "redirect:/user/cart/?updateSuccessfull";
			}
		} else {
			redirect = "redirect:/user/cart/?cannotUpdate";
		}
		return redirect;
	}

}
