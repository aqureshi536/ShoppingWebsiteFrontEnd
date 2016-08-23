package com.ahmad.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ahmad.dao.CartDAO;
import com.ahmad.dao.CartItemDAO;
import com.ahmad.dao.CategoryDAO;
import com.ahmad.dao.CustomerDAO;
import com.ahmad.dao.ProductDAO;
import com.ahmad.dao.UserAuthoritiesDAO;
import com.ahmad.dao.UsersDAO;
import com.ahmad.model.Cart;
import com.ahmad.model.CartItem;
import com.ahmad.model.Category;
import com.ahmad.model.Customer;
import com.ahmad.model.Product;
import com.ahmad.model.UserAuthorities;
import com.ahmad.model.Users;

@Controller
public class PageController {
	@Autowired
	Product product;
	@Autowired
	CartItem cartItem;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	CartItemDAO cartItemDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	Customer customer;
	@Autowired
	CustomerDAO customerDAO;
	@Autowired
	Users users;
	@Autowired
	UsersDAO usersDAO;
	@Autowired
	UserAuthorities userAuthorities;
	@Autowired
	UserAuthoritiesDAO userAuthoritiesDAO;

	@Autowired
	HttpSession httpSession;
	@Autowired
	CartDAO cartDAO;
	@Autowired
	Cart cart;

	// Activates When Home Page Is accessed

	@RequestMapping(value = { "/", "/index" })
	public ModelAndView home(Principal username, Model model) {
		ModelAndView mv = new ModelAndView("index");
		List<Product> productList = productDAO.listProductByStock();
		int size = productList.size();
		
		if (size != 0) {
			int toIterate = 0;
			int size1 = productList.size();
			switch (size1) {
			case 1:
				toIterate = 0;
				break;
			case 2:
				toIterate = 1;
				break;
			default:
				toIterate=2;
				break;

			}
			Product[] productArray = new Product[toIterate+1];
			for (int i = 0; i <= toIterate; i++) {
				productArray[i] = productList.get(size1 - 1);
				model.addAttribute("productArray", productArray);
				size1--;
			}
		} else {
			model.addAttribute("noLatestProducts", " noLatestProducts");
		}

		if (size >= 9) {
			Product[] productArray1 = new Product[3];
			Product[] productArray2 = new Product[3];
			Product[] productArray3 = new Product[3];
			for (int i = 0; i <= 2; i++) {
				productArray1[i] = productList.get(size - 1);
				size--;
			}
			for (int i = 0; i <= 2; i++) {
				productArray2[i] = productList.get(size - 1);
				size--;
			}
			for (int i = 0; i <= 2; i++) {
				productArray3[i] = productList.get(size - 1);
				size--;
			}
			model.addAttribute("productArray1", productArray1);
			model.addAttribute("productArray2", productArray2);
			model.addAttribute("productArray3", productArray3);
		} else {
			model.addAttribute("noProducts", "noProducts");
		}

		// Gets the category on the navber
		List<Category> categoryList = categoryDAO.listCategory();
		httpSession.setAttribute("categoryList", categoryList);
		// ================================================================
		mv.addObject("isHomeClicked", "true");
		mv.addObject("activeNavMenu", "home");
		mv.addObject("displayLogin", "true");
		mv.addObject("displayCart", "true");
		mv.addObject("displayAdminAction", "true");

		// Optional
		mv.addObject("pageBeforeAdminAction", "true");
		if (username != null) {
			if (customerDAO.getCustomerByUserName(username.getName()) != null) {
				customer = customerDAO.getCustomerByUserName(username.getName());
				String customerId = customer.getCustomerId();
				// If customer doesnt exist like admin
				if (customerId != null) {
					if (cartDAO.getCartByCustomerId(customerId) != null) {
						int noOfProduct = cartDAO
								.getCartByCustomerId(
										customerDAO.getCustomerByUserName(username.getName()).getCustomerId())
								.getNoOfProducts();
						httpSession.setAttribute("noOfProducts", noOfProduct);
					} else {
						httpSession.setAttribute("noOfProducts", 0);
					}
				}
			}

		}

		return mv;
	}

	// Activates WHen Login Clicked

	@RequestMapping(value = { "/login" })
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model,
			@RequestParam(value = "registrationSuccessfull", required = false) String registered,
			@RequestParam(value = "userExists", required = false) String userExists) {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("customer", new Customer());
		if (error != null) {
			model.addAttribute("error", "Oops! Invalid credentials,Please try again");
		}
		if (logout != null) {
			model.addAttribute("msg", "Thank You, You're successfully logged out");
		}
		if (registered != null) {
			model.addAttribute("registered", "Registration Successfull, Please login again");
		}
		if (userExists != null) {
			model.addAttribute("userExists", "Oops! Try diffrent username or email");
		}
		// Gets the category on the navber
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		mv.addObject("isLoginClicked", "true");
		mv.addObject("displayLogin", "true");
		mv.addObject("activeNavMenu", "login");
		mv.addObject("displayCart", "true");
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String registerCustomer(@ModelAttribute("customer") Customer customer) {
		this.customer = customer;
		Customer customerToCheck = customerDAO.getCustomerByUserName(customer.getUsername());
		if (customerToCheck == null) {
			// save to the customer table
			customerDAO.saveOrUpdate(customer);

			// After saving to customer table save to users table
			users.setCustomerId(customer.getCustomerId());
			users.setEnabled(true);
			users.setUsername(customer.getUsername());
			users.setPassword(customer.getPassword());
			usersDAO.saveOrUpdate(users);

			// After saving users table now save to user authorities table
			userAuthorities.setCustomerId(customer.getCustomerId());
			userAuthorities.setUsername(customer.getUsername());
			userAuthorities.setAuthority("ROLE_USER");
			userAuthoritiesDAO.saveOrUpdate(userAuthorities);
			return "redirect:/login?registrationSuccessfull";
		}
		return "redirect:/login?userExists";
	}

	// activates when clicked View All Products on NavBar

	@RequestMapping("/allProducts")
	public ModelAndView allProducts(Model model,
			@RequestParam(value = "addToCartAllProducts", required = false) String addToCartAllProducts,
			@RequestParam(value="cancelledAddToCart",required=false)String cancelledAddToCart) {
		if (addToCartAllProducts != null) {
			model.addAttribute("addToCartAllProducts", "Product added to cart Successfully");
		}
		if(cancelledAddToCart!=null)
		{
			model.addAttribute("cancelledAddToCart","This product cannot be added to cart no more");
		}
		ModelAndView mv = new ModelAndView("index");

		// Add products to we page
		List<Product> productListByStock = productDAO.listProductByStock();
		if (productListByStock != null && !productListByStock.isEmpty())
			model.addAttribute("products", productListByStock);
		else
			model.addAttribute("nothing", "Sorry, currently no products available");
		// Gets the category on the navber
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		mv.addObject("isClickedViewAllProducts", "true");
		mv.addObject("displayLogin", "true");
		mv.addObject("displayCart", "true");
		mv.addObject("activeNavMenu", "category");

		// Optional
		mv.addObject("pageBeforeAdminAction", "true");
		return mv;
	}

	// Customer after registered
	/*
	 * @RequestMapping(value = "/customer/home", method = RequestMethod.POST)
	 * public ModelAndView register(@ModelAttribute("customer") Customer
	 * customer) { ModelAndView mv = new ModelAndView("index");
	 * 
	 * customerDAO.saveOrUpdate(customer); mv.addObject("isHomeClicked",
	 * "true"); mv.addObject("active", "home"); mv.addObject("displayLogin",
	 * "true"); mv.addObject("displayCart", "true");
	 * 
	 * // Optional mv.addObject("pageBeforeAdminAction", "true"); // Gets the
	 * category on the navber List<Category> categoryList =
	 * categoryDAO.listCategory(); mv.addObject("categoryList", categoryList);
	 * // ================================================================
	 * return mv; }
	 */

	// To get the products according to category
	@RequestMapping("/allProducts/{categoryId}")
	public ModelAndView categoryProducts(@PathVariable("categoryId") String categoryId, Model model) {
		ModelAndView mv = new ModelAndView("index");

		List<Product> productList = categoryDAO.selectedCategoryProductList(categoryId);
		if (!productList.isEmpty()) {
			mv.addObject("productList", productList);
		} else {
			model.addAttribute("productNotPresent",
					"Sorry, No products present in " + categoryDAO.get(categoryId).getCategoryName() + " category");
		}
		mv.addObject("isViewProductByCategory", "true");

		// Gets the category on the navber
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		return mv;
	}

	// To get the products according to category
	@RequestMapping("/admin/category/{categoryId}")
	public ModelAndView categoryAdminProducts(@PathVariable("categoryId") String categoryId, Model model) {
		ModelAndView mv = new ModelAndView("index");

		List<Product> productList = categoryDAO.selectAllCategoryProducts(categoryId);
		if (!productList.isEmpty()) {
			mv.addObject("productList", productList);
		} else {
			model.addAttribute("productNotPresent",
					"Sorry, No products present in " + categoryDAO.get(categoryId).getCategoryName() + " category");
		}
		mv.addObject("isViewProductByCategory", "true");

		// Gets the category on the navber
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		return mv;
	}

	// Method to Search the product on user page or a in public
	@RequestMapping(value = "/search/", method = RequestMethod.GET)
	public ModelAndView productSearch(@RequestParam(value = "keyword") String keyword, Model model) {
		ModelAndView mv = new ModelAndView("index");
		List<Product> listOfsearchedProducts = productDAO.searchProduct(keyword);
		if (!listOfsearchedProducts.isEmpty()) {
			mv.addObject("listOfsearchedProducts", listOfsearchedProducts);
		} else {
			model.addAttribute("noResultsFound", "Sorry, No results found for keyword " + keyword);
		}
		mv.addObject("isSearchProducts", "true");
		return mv;

	}

	@RequestMapping(value = "/admin/search", method = RequestMethod.GET)
	public ModelAndView productSeachAdmin(@RequestParam(value = "keywordAdmin") String keywordAdmin, Model model) {
		ModelAndView mv = new ModelAndView("index");
		List<Product> listOfsearchedProducts = productDAO.searchProductAdmin(keywordAdmin);
		if (!listOfsearchedProducts.isEmpty()) {
			mv.addObject("listOfsearchedProducts", listOfsearchedProducts);
		} else {
			model.addAttribute("noResultsFound", "Sorry, No results found for keyword " + keywordAdmin);
		}
		mv.addObject("isSearchProducts", "true");
		return mv;
	}
	
	
	
	
	

	// Add a product to cart on all products page

	@RequestMapping("user/allProducts/addToCart/{productId}")
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
//String redirect = "redirect:/user/allProducts/?addToCartSuccessMessage";
		String redirect=addCartItem(customerId, productId, cartId, model);
		if (redirect == null) {
			cartItem = new CartItem();
			cartItem.setCartId(cartId);
			cartItem.setCustomerId(customerId);
			cartItem.setProductId(product.getProductId());
			cartItem.setQuantity(1);
			cartItem.setTotalPrice(product.getPrice());
			cartItemDAO.saveOrUpdate(cartItem);
			System.out.println("Insertion of cartItem");
			updateCartAgain(cartId, customerId);

		}
		httpSession.setAttribute("noOfProducts", returnNoOfProducts(userName));
		// Now navigate to the same page
		return redirect;
	}

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
	public String addCartItem(String customerId, String productId, String cartId, Model model) {
		List<CartItem> listOfSelectedCartItems = cartItemDAO.getCartItemsByCustomerId(customerId);
		Product product = productDAO.get(productId);
		for (CartItem item : listOfSelectedCartItems) {
			String itemProductId = item.getProductId();
			System.out.println(itemProductId);
			if (itemProductId.equals(product.getProductId())) {
				System.out.println(item.getCartItemId());
				item.setCartItemId(item.getCartItemId());

				System.out.println(item.getQuantity());
				// Check the whether the user is adding the item to cart more
				// than its quantity
				if (item.getQuantity() >= product.getQuantity()) {
					return "redirect:/allProducts?cancelledAddToCart";
				} else {
					item.setQuantity(item.getQuantity() + 1);

					System.out.println(item.getTotalPrice());
					item.setTotalPrice(item.getTotalPrice() + product.getPrice());

					System.out.println(item.toString());
					cartItemDAO.saveOrUpdate(item);
					updateCartAgain(cartId, customerId);

					return "redirect:/allProducts?addToCartAllProducts";
				}
			}

		}

		return null;
	}

	public int returnNoOfProducts(Principal username) {
		if (username != null) {
			int noOfProduct = cartDAO
					.getCartByCustomerId(customerDAO.getCustomerByUserName(username.getName()).getCustomerId())
					.getNoOfProducts();
			return noOfProduct;
		}
		return 0;
	}

	@RequestMapping("/403")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("index");

		mv.addObject("navigate403", "true");
		return mv;
	}

}
