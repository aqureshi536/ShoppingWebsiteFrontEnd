package com.ahmad.admin.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ahmad.dao.CategoryDAO;
import com.ahmad.dao.ProductDAO;
import com.ahmad.dao.SupplierDAO;
import com.ahmad.model.Category;
import com.ahmad.model.Product;
import com.ahmad.model.Supplier;
import com.ahmad.viewmodel.ProductModel;

@Controller
public class ProductController {
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

	@RequestMapping("/productDetail/{productId}")
	public ModelAndView productDetail(@PathVariable String productId, Model model) {
		ModelAndView mv = new ModelAndView("/index");

		product = productDAO.get(productId);
		model.addAttribute("product", product);

		// gets category name
		String categoryName;
		if (product.getCategoryId() != null && !product.getCategoryId().isEmpty()) {
			category = categoryDAO.get(product.getCategoryId());
			categoryName = category.getCategoryName();
		} else {
			category.setCategoryName("'Not Available'");
			categoryName = category.getCategoryName();
		}
		mv.addObject("categoryName", categoryName);

		// gets supplier name
		String supplierName;
		if (product.getSupplierId() != null && !product.getSupplierId().isEmpty()) {
			supplier = supplierDAO.get(product.getSupplierId());
			supplierName = supplier.getSupplierName();
		} else {
			supplier.setSupplierName("'Not Available'");
			supplierName = supplier.getSupplierName();
		}
		mv.addObject("supplierName", supplierName);

		mv.addObject("isClickedProductDetail", "true");
		mv.addObject("active", "login");
		mv.addObject("displayCart", "true");
		return mv;
	}

	// Product methods goes here

	// activates when admin clicked Product on Side Bar

	@RequestMapping("/admin/viewProducts")
	public ModelAndView adminViewProducts(Model model) {
		ModelAndView mv = new ModelAndView("index");

		// Add products to we page
		List<Product> productList = productDAO.listProduct();
		model.addAttribute("products", productList);

		// gets category List and name should be accessed from the front end
		// Tried but showing same category name
		/*
		 * List<Category> categoryList = categoryDAO.listCategory(); String
		 * categoryName; List listName=null; for(int
		 * i=0;i<productList.size();i++){ product=productList.get(i);
		 * category=categoryDAO.get(product.getCategoryId());
		 * categoryName=category.getCategoryName(); listName=new ArrayList<>();
		 * listName.add(categoryName); }
		 */

		List<ProductModel> products = new ArrayList<>();
		// Calling a method
		products = getNameSuppAndCat(products);

		model.addAttribute("products", products);

		// gets supplier List and name should be accessed from the front end
		List<Supplier> supplierList = supplierDAO.listSupplier();
		model.addAttribute("supplierName", supplierList);

		mv.addObject("isClickedAdminViewProducts", "true");
		mv.addObject("active", "adminProducts");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	// Add the product to database

	@RequestMapping(value = "/admin/viewProducts/addProduct")
	public ModelAndView addProduct(@ModelAttribute("Product") Product product, Model model) {
		ModelAndView mv = new ModelAndView("/index");
		List<Category> categoryList = categoryDAO.listCategory();
		model.addAttribute("categories", categoryList);

		List<Supplier> supplierList = supplierDAO.listSupplier();
		model.addAttribute("suppliers", supplierList);

		mv.addObject("isAddProductClicked", "true");
		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	// This get after admin clicks add Product on the form
	@RequestMapping(value = "/admin/viewProducts", method = RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute("Product") @Valid Product product, BindingResult result,
			HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView("index");

		// Hibernate validation
		if (result.hasErrors()) {

			List<Category> categoryList = categoryDAO.listCategory();
			model.addAttribute("categories", categoryList);
			List<Supplier> supplierList = supplierDAO.listSupplier();
			model.addAttribute("suppliers", supplierList);

			mv.addObject("isAddProductClicked", "true");
			mv.addObject("displayLogout", "true");
			mv.addObject("displayAdminAction", "true");
			return mv;
		}

		productDAO.saveOrUpdate(product);

		MultipartFile productImage = product.getImageUrl();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		if (productImage != null && !productImage.isEmpty()) {
			try {
				if (!Files.exists(Paths.get(rootDirectory + "\\resources\\images\\product\\"))) {

					Files.createDirectories(Paths.get(rootDirectory + "\\resources\\images\\product\\"));
				}
				productImage.transferTo(new File(
						Paths.get(rootDirectory + "\\resources\\images\\product\\" + product.getProductId() + ".png")
								.toString()));

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Product Image Saving Failed", e);
			}
		}

		// get the list of products
		List<Product> productList = productDAO.listProduct();

		// Get the Last Product
		int productListSize = productDAO.listProduct().size();
		Product pL = productList.get(productListSize - 1);
		String lastProduct = pL.getProductName();
		mv.addObject("lastProduct", lastProduct);
		mv.addObject("addProductSuccessMessage", "true");

		// ----------------------------------------------------------------------
		model.addAttribute("productListSize", productListSize);
		model.addAttribute("products", productList);

		List<ProductModel> products = new ArrayList<>();
		products = getNameSuppAndCat(products);
		model.addAttribute("products", products);

		// gets supplier List and name should be accessed form the front end
		List<Supplier> supplierList = supplierDAO.listSupplier();
		model.addAttribute("suppliers", supplierList);
		// ---------------------------------------------------------
		mv.addObject("isClickedAdminViewProducts", "true");
		mv.addObject("active", "adminProducts");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	// Update the product till the form
	@RequestMapping(value = "/admin/viewProducts/updateProduct/{productId}")
	public ModelAndView updateProduct(@PathVariable("productId") String productId, Model model) {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("product", productDAO.get(productId));

		Product product = productDAO.get(productId);
		mv.addObject("productToUpdate", product);

		List<Category> categoryList = categoryDAO.listCategory();
		model.addAttribute("categories", categoryList);
		List<Supplier> supplierList = supplierDAO.listSupplier();
		model.addAttribute("suppliers", supplierList);
		mv.addObject("categories", categoryList);
		mv.addObject("suppliers", supplierList);

		String categoryName;
		String supplierName;
		if (product.getCategoryId() != null && !product.getCategoryId().isEmpty()) {
			category = categoryDAO.get(product.getCategoryId());
			categoryName = category.getCategoryName();
		} else {
			category.setCategoryName("Not Available");
			categoryName = category.getCategoryName();
		}

		if (product.getSupplierId() != null && !product.getSupplierId().isEmpty()) {
			supplier = supplierDAO.get(product.getSupplierId());
			supplierName = supplier.getSupplierName();
		} else {
			supplier.setSupplierName("Not Available");
			supplierName = supplier.getSupplierName();
		}

		mv.addObject("categoryName", categoryName);
		mv.addObject("supplierName", supplierName);

		mv.addObject("isUpdateProductClicked", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	// Actual update form
	@RequestMapping(value = "/admin/viewProducts/updateProduct", method = RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute("Product") @Valid Product product, BindingResult result,
			Model model, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("product", product);
		// Hibernate validation update product

		if (result.hasErrors()) {

			List<Category> categoryList = categoryDAO.listCategory();
			model.addAttribute("categories", categoryList);
			List<Supplier> supplierList = supplierDAO.listSupplier();
			model.addAttribute("suppliers", supplierList);
			mv.addObject("categories", categoryList);
			mv.addObject("suppliers", supplierList);

			String categoryName;
			String supplierName;
			if (product.getCategoryId() != null && !product.getCategoryId().isEmpty()) {
				category = categoryDAO.get(product.getCategoryId());
				categoryName = category.getCategoryName();
			} else {
				category.setCategoryName("Not Available");
				categoryName = category.getCategoryName();
			}

			if (product.getSupplierId() != null && !product.getSupplierId().isEmpty()) {
				supplier = supplierDAO.get(product.getSupplierId());
				supplierName = supplier.getSupplierName();
			} else {
				supplier.setSupplierName("Not Available");
				supplierName = supplier.getSupplierName();
			}

			mv.addObject("categoryName", categoryName);
			mv.addObject("supplierName", supplierName);
			mv.addObject("isUpdateProductClicked", "true");
			mv.addObject("displayAdminAction", "true");
			return mv;
		}

		// Actual update Starts here

		MultipartFile productImage = product.getImageUrl();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		String productName = product.getProductName();
		mv.addObject("productNameToUpdate", productName);

		if (productImage != null && !productImage.isEmpty()) {
			try {
				if (!Files.exists(Paths.get(rootDirectory + "\\resources\\images\\product\\"))) {

					Files.createDirectories(Paths.get(rootDirectory + "\\resources\\images\\product\\"));
				}
				productImage.transferTo(new File(
						Paths.get(rootDirectory + "\\resources\\images\\product\\" + product.getProductId() + ".png")
								.toString()));

			} catch (Exception e) {
				throw new RuntimeException("Product image updating failed", e);
			}
		}
		productDAO.saveOrUpdate(product);

		List<Product> productList = productDAO.listProduct();

		// Get the Last Product
		int productListSize = productDAO.listProduct().size();
		Product pL = productList.get(productListSize - 1);
		String lastProduct = pL.getProductName();
		mv.addObject("lastProduct", lastProduct);
		mv.addObject("updateProductSuccessMessage", "true");

		// ----------------------------------------------------------------------
		model.addAttribute("productListSize", productListSize);
		model.addAttribute("products", productList);

		List<ProductModel> products = new ArrayList<>();
		products = getNameSuppAndCat(products);
		model.addAttribute("products", products);

		// gets supplier List and name should be accessed form the front end
		List<Supplier> supplierList = supplierDAO.listSupplier();
		model.addAttribute("suppliers", supplierList);
		// ---------------------------------------------------------
		mv.addObject("isClickedAdminViewProducts", "true");
		mv.addObject("active", "adminProducts");
		mv.addObject("displayAdminAction", "true");
		return mv;
		// get the list of products
		// return "redirect:/admin/viewProducts";
	}

	@RequestMapping("/admin/viewProducts/delete/{productId}")
	public ModelAndView deleteProduct(@PathVariable String productId, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");

		product = productDAO.get(productId);
		mv.addObject("product", product);
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		System.out.println(Paths.get(rootDirectory + "\\resources\\images\\product\\" + productId + ".png"));
		if (Files.exists(Paths.get(rootDirectory + "\\resources\\images\\product\\" + productId + ".png"))) {
			try {
				if (!Files.exists(Paths.get(rootDirectory + "\\resources\\images\\product\\"))) {

					Files.createDirectories(Paths.get(rootDirectory + "\\resources\\images\\product\\"));
				}
				Files.delete(Paths.get(rootDirectory + "\\resources\\images\\product\\" + productId + ".png"));

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("File unable to delete", e);
			}
		}

		productDAO.delete(productId);

		List<Product> productList = productDAO.listProduct();
		model.addAttribute("products", productList);
		List<ProductModel> products = new ArrayList<>();
		products = getNameSuppAndCat(products);
		model.addAttribute("products", products);

		// gets supplier List and name should be accessed form the front end
		List<Supplier> supplierList = supplierDAO.listSupplier();
		model.addAttribute("suppliers", supplierList);
		mv.addObject("deleteProductSuccessMessage", "true");
		mv.addObject("isClickedAdminViewProducts", "true");
		mv.addObject("active", "adminProducts");
		mv.addObject("displayAdminAction", "true");

		return mv;

	}

	// This method is common for almost every methods
	private List<ProductModel> getNameSuppAndCat(List<ProductModel> products) {
		List<Product> productList = productDAO.listProduct();
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
