package com.ahmad.admin.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	HttpSession httpSession;

	@RequestMapping("/productDetail/{productId}")
	public ModelAndView productDetail(@PathVariable String productId, Model model,
			@RequestParam(value = "addToCartSuccessMessage", required = false) String addToCartSuccessMessage,
			@RequestParam(value="cancelledAddToCart",required=false)String cancelledAddToCart) {
		ModelAndView mv = new ModelAndView("/index");
		if (addToCartSuccessMessage != null) {
			model.addAttribute("addToCartSuccessMessage", "Product added to cart successfully");
		}
		if(cancelledAddToCart!=null)
		{
			model.addAttribute("cancelledAddToCart","You cannot add this product to cart any more");
		}
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
		if (similarProducts(productId) != null) {
			model.addAttribute("similarProducts", similarProducts(productId));
		} else {
			model.addAttribute("noSimilarProducts", "No similar products");
		}

		mv.addObject("supplierName", supplierName);
		
		List<Category> categoryList = categoryDAO.listCategory();
		httpSession.setAttribute("categoryList", categoryList);
		// model.addAttribute("showProductDetail", "notEmpty");
		mv.addObject("isClickedProductDetail", "true");
		mv.addObject("active", "login");
		mv.addObject("displayCart", "true");
		return mv;
	}

	// Pass on the similar products
	public Product[] similarProducts(String productId) {
		List<Product> similarProductList = productDAO.similarProducts(productId);

		int sizeOfSimilarProducts = similarProductList.size();
		int size = 0;

		switch (sizeOfSimilarProducts) {
		case 0:
			size = -1;
			break;
		case 1:
			size = 0;
			break;

		case 2:
			size = 1;
			break;

		default:
			size = 2;
			break;

		}
		Product[] productArray = new Product[size + 1];
		if (sizeOfSimilarProducts != 0) {
			for (int i = 0; i <= size; i++) {
				//int randomNumber = (int) (Math.random() * size);
				productArray[i] = similarProductList.get(i);
			}
			return productArray;
		}
		return null;
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
		
		if (products != null && !products.isEmpty())
			model.addAttribute("products", products);
		else
			model.addAttribute("noProducts", "No products present");
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
	public ModelAndView addProduct(Model model) {
		ModelAndView mv = new ModelAndView("/index");
		model.addAttribute("product", new Product());
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
	public ModelAndView addProduct(@ModelAttribute("product") @Valid Product product, BindingResult result,
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
		// call a method
		product = changeProductStock(product);

		// This is a statement for adding the product to database
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
		if (products != null && !products.isEmpty())
			model.addAttribute("products", products);
		else
			model.addAttribute("noProducts", "No products present");

		// gets supplier List and name should be accessed form the front end
		List<Supplier> supplierList = supplierDAO.listSupplier();
		model.addAttribute("suppliers", supplierList);
		// ---------------------------------------------------------

		mv.addObject("isClickedAdminViewProducts", "true");
		mv.addObject("active", "adminProducts");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	String productId;

	// Update the product till the form
	@RequestMapping(value = "/admin/viewProducts/updateProduct/{productId}")
	public ModelAndView updateProduct(@PathVariable("productId") String productId, Model model) {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("product", productDAO.get(productId));

		this.productId = productDAO.get(productId).getProductId();

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
	public ModelAndView updateProduct(@ModelAttribute("product") @Valid Product product, BindingResult result,
			Model model, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("index");

		// Hibernate validation update product

		if (result.hasErrors()) {
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
		} // error part completed

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
		// Call a method at last
		product = changeProductStock(product);
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
		if (products != null && !products.isEmpty())
			model.addAttribute("products", products);
		else
			model.addAttribute("noProducts", "No products present");

		// gets supplier List and name should be accessed form the front end
		List<Supplier> supplierList = supplierDAO.listSupplier();
		model.addAttribute("suppliers", supplierList);
		// ---------------------------------------------------------
		// Gets the category on the navbar
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
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
		if (products != null && !products.isEmpty())
			model.addAttribute("products", products);
		else
			model.addAttribute("noProducts", "No longer any products exists");

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

	// This method changes the value of the product stock
	public Product changeProductStock(Product product) {
		if (product.getQuantity() <= 0) {
			product.setOutOffStock(true);
		} else {
			product.setOutOffStock(false);
		}
		return product;
	}

}
