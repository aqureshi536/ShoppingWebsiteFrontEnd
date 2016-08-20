package com.ahmad.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahmad.dao.CategoryDAO;
import com.ahmad.dao.ProductDAO;
import com.ahmad.dao.SupplierDAO;
import com.ahmad.model.Category;
import com.ahmad.model.Product;
import com.ahmad.model.Supplier;
import com.ahmad.viewmodel.ProductModel;

@RestController
public class JsonDataController {
	
	@Autowired
	Product product;
	
	@Autowired
	ProductDAO productDAO;
	@Autowired
	SupplierDAO supplierDAO;
	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	Supplier supplier;
	@Autowired
	Category category;

	// -----To Display all products-------
	@RequestMapping("/product/all")
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

	//-------To display specific product  ---------
	@RequestMapping("/product/productDetail/{productId}")
	public Product productDetail(@PathVariable("productId")String productId,Model model)
	{
		model.addAttribute("showProductDetail", "notEmpty");
		product = productDAO.get(productId);
		model.addAttribute("supplierName", supplierDAO.get(product.getSupplierId()));
		return product;
		
	}
	
	
	// ------To display data category wise----------
	@RequestMapping("/product/{categoryId}")
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

	
	
}
