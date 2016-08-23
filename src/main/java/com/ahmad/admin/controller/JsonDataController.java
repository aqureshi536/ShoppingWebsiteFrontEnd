package com.ahmad.admin.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import com.ahmad.viewmodel.ProductModel;

@RestController
public class JsonDataController {

	@Autowired
	Product product;
	@Autowired
	ProductDAO productDAO;

	@Autowired
	Category category;
	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	Supplier supplier;
	@Autowired
	SupplierDAO supplierDAO;

	@Autowired
	Customer customer;
	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	Cart cart;
	@Autowired
	CartItem cartItem;

	@Autowired
	CartDAO cartDAO;
	@Autowired
	CartItemDAO cartItemDAO;

	// -----To Display all products-------
	@RequestMapping(value = "/product/all", method = RequestMethod.GET)
	public @ResponseBody List<ProductModel> returnAllProductsModel(
			@RequestParam(value = "addToCartAllProducts", required = false) String addToCartAllProducts, Model model) {
		if (addToCartAllProducts != null) {
			model.addAttribute("addToCartAllProducts", "Product added to cart Successfully");
		}

		List<ProductModel> products = new ArrayList<>();
		List<Product> productList = productDAO.listProductByStock();
		ProductModel productModel = null;
		for (Product p : productList) {
			productModel = new ProductModel();
			productModel.setProduct(p);

			// Check for category presence
			if (p.getCategoryId() != null && !p.getCategoryId().isEmpty()) {
				category = categoryDAO.get(p.getCategoryId());
				productModel.setCategoryName(category.getCategoryName());
			} else {
				productModel.setCategoryName("Not Available");
			}

			// Check for supplier presence
			if (p.getSupplierId() != null && !p.getSupplierId().isEmpty()) {
				supplier = supplierDAO.get(p.getSupplierId());
				productModel.setSupplierName(supplier.getSupplierName());
			} else {
				productModel.setSupplierName("Not Available");

			}
			products.add(productModel);
		}
		model.addAttribute("showAllProducts", "notEmpty");
		return products;
	}

	// -------To display specific product ---------
	@RequestMapping(value = "/product/productDetail/{productId}", method = RequestMethod.GET)
	public Product productDetail(@PathVariable("productId") String productId, Model model) {
		model.addAttribute("showProductDetail", "notEmpty");
		product = productDAO.get(productId);
		model.addAttribute("supplierName", supplierDAO.get(product.getSupplierId()));
		return product;

	}

	// ------To display data category wise----------  not implemented
	@RequestMapping(value = "/product/{categoryId}", method = RequestMethod.GET)
	public @ResponseBody List<Product> productByCategory(@PathVariable("categoryId") String categoryId, Model model) {
		List<Product> listProductByCategory = categoryDAO.selectedCategoryProductList(categoryId);
		model.addAttribute("showProductByCategory", "notEmpty");
		if (!listProductByCategory.isEmpty()) {
			return listProductByCategory;
		} else {
			model.addAttribute("productNotPresent",
					"Sorry, No products present in " + categoryDAO.get(categoryId).getCategoryName() + " category");
		}
		return null;
	}

	@RequestMapping(value = "/dummy", method = RequestMethod.GET)
	public @ResponseBody List<Product> checkDummy() {

		return productDAO.listProduct();
	}

	// To view Cart

	@RequestMapping("/view/cart/")
	public @ResponseBody List<CartItemModel> viewCart(Principal principal) {
		customer = customerDAO.getCustomerByUserName(principal.getName());
		List<CartItem> cartItems = cartItemDAO.getCartItemsByCustomerId(customer.getCustomerId());

		List<CartItemModel> cartItemModelList = new ArrayList<>();

		CartItemModel cartItemModel = null;

		for (CartItem item : cartItems) {
			cartItemModel = new CartItemModel();
			
			/*if (item.getQuantity() >= product.getQuantity()) {
				
			} */
			cartItemModel.setCartItem(item);
			if (item.getProductId() != null && !item.getProductId().isEmpty()) {
				product = productDAO.get(item.getProductId());
				cartItemModel.setProductName(product.getProductName());
			} else {
				cartItemModel.setProductName("Currently not avilable");
			}
			if ( productDAO.get(item.getProductId()).getQuantity() <= 0){
				cartItemModel.setProductName("Not in stock");
			}
			cartItemModelList.add(cartItemModel);
		}
		return cartItemModelList;
	}

	
//	to view history of the product bought
	@RequestMapping(value="/view/orderedItems",method=RequestMethod.GET)
	public List<OrderedItems> listOfOrderedItems(Principal principal){
		List<OrderedItems> listofOrderedItems = cartDAO.listOrderedItems(customerDAO.getCustomerByUserName
												(principal.getName()).getCustomerId());
		if(listofOrderedItems!=null&&!listofOrderedItems.isEmpty())
		{
			return listofOrderedItems;
		}
		return null;
	}

}
