package com.ahmad.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ahmad.dao.CategoryDAO;
import com.ahmad.dao.ProductDAO;
import com.ahmad.dao.SupplierDAO;
import com.ahmad.dao.UserLoginDAO;
import com.ahmad.model.Category;
import com.ahmad.model.Product;
import com.ahmad.model.Supplier;
import com.ahmad.viewmodel.CategoryModel;
import com.ahmad.viewmodel.ProductModel;

@Controller
public class PageController {

	@Autowired
	private UserLoginDAO userLoginDAO;

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

	public void setUserLoginDAO(UserLoginDAO userLoginDAO) {
		this.userLoginDAO = userLoginDAO;
	}

	// Activates When Home Page Is accessed
	
	@RequestMapping(value = { "/", "/index" })
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("isHomeClicked", "true");
		mv.addObject("active", "home");
		mv.addObject("displayLogin", "true");
		mv.addObject("displayCart", "true");
		return mv;
	}

	// Activates WHen Login Clicked

	@RequestMapping("/login")
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("isLoginClicked", "true");
		mv.addObject("displayLogin", "true");
		mv.addObject("active", "login");
		mv.addObject("displayCart", "true");
		return mv;
	}

	// activates when clicked View All Products on NavBar

	@RequestMapping("/allProducts")
	public ModelAndView allProducts(Model model) {
		ModelAndView mv = new ModelAndView("index");

		// Add products to we page
		List<Product> productList = productDAO.listProduct();
		model.addAttribute("products", productList);

		mv.addObject("isClickedViewAllProducts", "true");
		mv.addObject("displayLogin", "true");
		mv.addObject("displayCart", "true");
		mv.addObject("activeNavMenu", "viewAllProducts");
		return mv;
	}

	// Activate When clicked on any Product

	@RequestMapping("/productDetail/{productId}")
	public ModelAndView productDetail(@PathVariable String productId, Model model) {
		ModelAndView mv = new ModelAndView("/index");

		product = productDAO.get(productId);
		model.addAttribute("product", product);

		// gets category name
		category = categoryDAO.get(product.getCategoryId());
		String categoryName = category.getCategoryName();
		mv.addObject("categoryName", categoryName);

		// gets supplier name
		supplier = supplierDAO.get(product.getSupplierId());
		String supplierName = supplier.getSupplierName();
		mv.addObject("supplierName", supplierName);

		mv.addObject("isClickedProductDetail", "true");
		mv.addObject("active", "login");
		mv.addObject("displayCart", "true");
		return mv;
	}

	
	
	// activates when admin clicked Category on sideBar

	@RequestMapping("/admin/viewSupplier")
	public ModelAndView adminViewSupplier(Model model) {
		List<Supplier> supplierList = supplierDAO.listSupplier();
		model.addAttribute("suppliers", supplierList);

		ModelAndView mv = new ModelAndView("index");
		mv.addObject("isClickedAdminViewSupplier", "true");
		mv.addObject("active", "adminSupplier");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}
	
	
	//Product methods goes here
	
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
				ProductModel productModel = null;
				for (Product p : productList) {
					productModel = new ProductModel();
					productModel.setProduct(p);

					category = categoryDAO.get(p.getCategoryId());
					supplier = supplierDAO.get(p.getSupplierId());
					productModel.setCategoryName(category.getCategoryName());
					productModel.setSupplierName(supplier.getSupplierName());
					products.add(productModel);
				}

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

	@RequestMapping(value = "/admin/viewProducts", method = RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute("Product") Product product, HttpServletRequest request,
			Model model) {
		ModelAndView mv = new ModelAndView("index");
		productDAO.saveOrUpdate(product);

		MultipartFile productImage = product.getImageUrl();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		if (productImage != null && !productImage.isEmpty()) {
			try {
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
		ProductModel productModel = null;
		for (Product p : productList) {
			productModel = new ProductModel();
			productModel.setProduct(p);

			category = categoryDAO.get(p.getCategoryId());
			supplier = supplierDAO.get(p.getSupplierId());
			productModel.setCategoryName(category.getCategoryName());
			productModel.setSupplierName(supplier.getSupplierName());
			products.add(productModel);
		}
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
		product = productDAO.get(productId);
		model.addAttribute("productToUpdate", product);

		List<Category> categoryList = categoryDAO.listCategory();
		model.addAttribute("categories", categoryList);
		List<Supplier> supplierList = supplierDAO.listSupplier();
		model.addAttribute("suppliers", supplierList);
		mv.addObject("categories", categoryList);
		mv.addObject("suppliers", supplierList);

		category = categoryDAO.get(product.getCategoryId());
		String categoryName = category.getCategoryName();
		supplier = supplierDAO.get(this.product.getSupplierId());
		String supplierName = supplier.getSupplierName();
		mv.addObject("categoryName", categoryName);
		mv.addObject("supplierName", supplierName);

		mv.addObject("isUpdateProductClicked", "true");
		return mv;
	}

	// Actual update form
	@RequestMapping(value = "/admin/viewProducts/updateProduct", method = RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute("Product") Product product, Model model,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		MultipartFile productImage = product.getImageUrl();
		String rootDiectory = request.getSession().getServletContext().getRealPath("/");

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(
						Paths.get(rootDiectory + "\\resources\\images\\product\\" + product.getProductId() + ".png")
								.toString()));
			} catch (Exception e) {
				throw new RuntimeException("Product image updating failed", e);
			}
		}
		productDAO.saveOrUpdate(product);

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
		ProductModel productModel = null;
		for (Product p : productList) {
			productModel = new ProductModel();
			productModel.setProduct(p);

			category = categoryDAO.get(p.getCategoryId());
			supplier = supplierDAO.get(p.getSupplierId());
			productModel.setCategoryName(category.getCategoryName());
			productModel.setSupplierName(supplier.getSupplierName());
			products.add(productModel);
		}

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

	// Methods to add Category goer here
	// activates when admin licked Category on sideBar

	
		@RequestMapping("/admin/viewCategory")
		public ModelAndView adminViewCategory(Model model) {
			ModelAndView mv = new ModelAndView("index");
			List<Category> categoryList = categoryDAO.listCategory();

			CategoryModel categoryModel = null;
			List<CategoryModel> categories = new ArrayList<CategoryModel>();
			int noOfProduct;
			for (Category c : categoryList) {
				categoryModel = new CategoryModel();
				categoryModel.setCategory(c);
				noOfProduct = categoryDAO.getProductCountByCategory(c.getCategoryId());
				categoryModel.setNoOfProducts(noOfProduct);
				categories.add(categoryModel);
			}
			model.addAttribute("categories", categories);

			mv.addObject("isClickedAdminViewCategory", "true");
			mv.addObject("active", "adminCategory");
			mv.addObject("displayAdminAction", "true");
			return mv;
		}

	
	
	@RequestMapping("/admin/viewCategory/addCategory")
	public ModelAndView addCategory(@ModelAttribute("Category") Category category,Model model) {
		ModelAndView mv = new ModelAndView("/index");

		mv.addObject("isAddCategoryClicked", "true");

		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	@RequestMapping(value = "/admin/viewCategory", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("Category") Category category, Model model,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");
		categoryDAO.saveOrUpdate(category);

		MultipartFile categoryImage = category.getCategoryImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		if (categoryImage != null && !categoryImage.isEmpty()) {
			try {
				categoryImage.transferTo(new File(Paths
						.get(rootDirectory + "\\resources\\images\\category\\" + this.category.getCategoryId() + ".png")
						.toString()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Category Image Saving Failed", e);
			}
		}
		List<Category> categoryList = categoryDAO.listCategory();

		CategoryModel categoryModel = null;
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		
		int categoryListSize = categoryDAO.listCategory().size();
		this.category=categoryList.get(categoryListSize-1);
		String categoryName = this.category.getCategoryName();
		mv.addObject("categoryName", categoryName);
		mv.addObject("categoryListSize", categoryListSize);
		mv.addObject("addedCategoryMessage", "true");
		
		int noOfProduct;
		for (Category c : categoryList) {
			categoryModel = new CategoryModel();
			categoryModel.setCategory(c);
			noOfProduct = categoryDAO.getProductCountByCategory(c.getCategoryId());
			categoryModel.setNoOfProducts(noOfProduct);
			categories.add(categoryModel);
		}
		model.addAttribute("categories", categories);

		mv.addObject("isClickedAdminViewCategory", "true");
		mv.addObject("active", "adminCategory");
		mv.addObject("displayAdminAction", "true");
		return mv;
		
		

	

	}

	@RequestMapping(value = "/admin/viewCategory/delete/{categoryId}")
	public ModelAndView deleteCategory(@PathVariable("categoryId") String categoryId, Model model,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");
		category = categoryDAO.get(categoryId);
		String categoryName=category.getCategoryName();
		mv.addObject("categoryNameDeleted", categoryName);
		mv.addObject("deletedCategoryMessage", "true");
		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		System.out.println(Paths.get(rootDirectory + "\\resources\\images\\category\\" + categoryId + ".png"));
		if (Files.exists(Paths.get(rootDirectory + "\\resources\\images\\category\\" + categoryId + ".png"))) {
			try {
				Files.delete(Paths.get(rootDirectory + "\\resources\\images\\category\\" + categoryId + ".png"));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("File unable to delete", e);
			}
		}

		categoryDAO.delete(categoryId);

		List<Category> categoryList = categoryDAO.listCategory();

		CategoryModel categoryModel = null;
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		int noOfProduct;
		for (Category c : categoryList) {
			categoryModel = new CategoryModel();
			categoryModel.setCategory(c);
			noOfProduct = categoryDAO.getProductCountByCategory(c.getCategoryId());
			categoryModel.setNoOfProducts(noOfProduct);
			categories.add(categoryModel);
		}
		model.addAttribute("categories", categories);

		mv.addObject("isClickedAdminViewCategory", "true");
		mv.addObject("active", "adminCategory");
		mv.addObject("displayAdminAction", "true");
		return mv;
		
	}

	@RequestMapping("/addSupplier")
	public ModelAndView addSupplier() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("isAddSupplierClicked", "true");

		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	@RequestMapping("/updateSupplier")
	public ModelAndView updateSupplier() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("isUpdateSupplierClicked", "true");

		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	public ModelAndView names(Model model) {
		ModelAndView mv = new ModelAndView();

		category = categoryDAO.get("${product}");
		String categoryName = category.getCategoryName();
		model.addAttribute("categoryName", categoryName);
		return mv;
	}

}
