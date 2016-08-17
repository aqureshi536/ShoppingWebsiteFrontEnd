package com.ahmad.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
ProductDAO productDAO;
@Autowired
SupplierDAO supplierDAO;
@Autowired
CategoryDAO categoryDAO;

@Autowired
Supplier supplier;
@Autowired
Category category;




	
	@RequestMapping("/product/all")
	public @ResponseBody List<ProductModel> returnAllProductsModel()
	{
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
		return products;
	}
}
