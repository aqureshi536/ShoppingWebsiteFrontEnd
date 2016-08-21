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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ahmad.dao.CategoryDAO;
import com.ahmad.model.Category;
import com.ahmad.viewmodel.CategoryModel;

@Controller
public class CategoryController {

	@Autowired
	private Category category;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	HttpSession httpSession;
	// Methods to add Category goer here
	// activates when admin licked Category on sideBar

	@RequestMapping("/admin/viewCategory")
	public ModelAndView adminViewCategory(Model model) {
		ModelAndView mv = new ModelAndView("index");
	//  gets from the method at bottom
			if (getCatoryModelList() != null && !getCatoryModelList().isEmpty())
				model.addAttribute("categories", getCatoryModelList());
			else
				model.addAttribute("noCategoryPresent", "No category present");
		
		// ================================================================
		mv.addObject("isClickedAdminViewCategory", "true");
		mv.addObject("active", "adminCategory");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	@RequestMapping(value = "/admin/viewCategory/addCategory")
	public ModelAndView addCategory() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("category", new Category());

		// ================================================================
		mv.addObject("isAddCategoryClicked", "true");
		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	// @RequestMapping(value = "admin/viewCategory", method =
	// RequestMethod.POST)
	// public String addCategory(@ModelAttribute("category") Category category,
	// Model model, HttpServletRequest request) {
	//
	// return "redirect:/admin/viewCategory";
	// }

	@RequestMapping(value = "/admin/viewCategory", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("category") @Valid Category category, BindingResult result,
			Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");

		if (result.hasErrors()) {
			// Gets the category on the navber
			List<Category> categoryList = categoryDAO.listCategory();
			httpSession.setAttribute("categoryList", categoryList);
			// ================================================================
			mv.addObject("isAddCategoryClicked", "true");
			mv.addObject("displayLogout", "true");
			mv.addObject("displayAdminAction", "true");
			return mv;
		}
		MultipartFile categoryImage = category.getCategoryImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		if (categoryImage != null && !categoryImage.isEmpty()) {
			try {
				if (!Files.exists(Paths.get(rootDirectory + "\\resources\\images\\category\\"))) {
					Files.createDirectories(Paths.get(rootDirectory + "\\resources\\images\\category\\"));
				}
				categoryImage.transferTo(new File(
						Paths.get(rootDirectory + "\\resources\\images\\category\\" + category.getCategoryId() + ".png")
								.toString()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Category Image Saving Failed", e);
			}
		}
		categoryDAO.saveOrUpdate(category);

		CategoryModel categoryModel = null;
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		List<Category> categoryList = categoryDAO.listCategory();
		int categoryListSize = categoryDAO.listCategory().size();
		this.category = categoryList.get(categoryListSize - 1);
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

			if (categoryList!= null && !categoryList.isEmpty())
				model.addAttribute("categories", categoryList);
			else
				model.addAttribute("noCategoryPresent", "No category present");
	

		// Gets the category on the navber

		mv.addObject("categoryList", categoryList);
		// ================================================================
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
		String categoryName = category.getCategoryName();
		mv.addObject("categoryNameDeleted", categoryName);
		mv.addObject("deletedCategoryMessage", "true");

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		System.out.println(Paths.get(rootDirectory + "\\resources\\images\\category\\" + categoryId + ".png"));
		if (Files.exists(Paths.get(rootDirectory + "\\resources\\images\\category\\" + categoryId + ".png"))) {
			try {
				if (!Files.exists(Paths.get(rootDirectory + "\\resources\\images\\category\\"))) {
					Files.createDirectories(Paths.get(rootDirectory + "\\resources\\images\\category\\"));
				}
				Files.delete(Paths.get(rootDirectory + "\\resources\\images\\category\\" + categoryId + ".png"));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("File unable to delete", e);
			}
		}

		categoryDAO.delete(categoryId);

	//  gets from the method at bottom
			if (getCatoryModelList() != null && !getCatoryModelList().isEmpty())
				model.addAttribute("categories", getCatoryModelList());
			else
				model.addAttribute("noCategoryPresent", "No longer any category exists");
		// Gets the category on the navber

		httpSession.setAttribute("categoryList", categoryDAO.listCategory());
		// ================================================================
		mv.addObject("isClickedAdminViewCategory", "true");
		mv.addObject("active", "adminCategory");
		mv.addObject("displayAdminAction", "true");
		return mv;

	}

	@RequestMapping("/admin/viewCategory/update/{categoryId}")
	public ModelAndView updateCategory(@PathVariable("categoryId") String categoryId) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("category", category);

		category = categoryDAO.get(categoryId);
		mv.addObject("categoryToUpdate", category);
		// Gets the category on the navber

		// ================================================================
		mv.addObject("isClickedAdminUpdateCategory", "true");
		mv.addObject("active", "adminCategory");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	@RequestMapping(value = "/admin/viewCategory/updated", method = RequestMethod.POST)
	public ModelAndView updateCategory(@ModelAttribute("category") @Valid Category category, BindingResult result,
			Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");
		if (result.hasErrors()) {

			// Gets the category on the navber
			List<Category> categoryList = categoryDAO.listCategory();

			// ================================================================
			mv.addObject("categoryToUpdate", category);
			mv.addObject("isClickedAdminUpdateCategory", "true");
			mv.addObject("active", "adminCategory");
			mv.addObject("displayAdminAction", "true");
			return mv;
		}

		MultipartFile categoryImage = category.getCategoryImage();

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		if (categoryImage != null && !categoryImage.isEmpty()) {
			try {
				if (!Files.exists(Paths.get(rootDirectory + "\\resources\\images\\category\\"))) {
					Files.createDirectories(Paths.get(rootDirectory + "\\resources\\images\\category\\"));
				}
				categoryImage.transferTo(new File(
						Paths.get(rootDirectory + "\\resources\\images\\category\\" + category.getCategoryId() + ".png")
								.toString()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Category Image updating failed", e);
			}

		}

		// Gets the name before and after updating the product

		categoryDAO.saveOrUpdate(category);
		
//  gets from the method at bottom
		if (getCatoryModelList() != null && !getCatoryModelList().isEmpty())
			model.addAttribute("categories", getCatoryModelList());
		else
			model.addAttribute("noCategoryPresent", "No category present");

		String categoryNameAfterUpdate = category.getCategoryName();
		mv.addObject("categoryNameAfterUpdate", categoryNameAfterUpdate);
		// Gets the category on the navber

		httpSession.setAttribute("categoryList", categoryDAO.listCategory());
		// ================================================================
		mv.addObject("categoryUpdateMessage", "true");
		mv.addObject("isClickedAdminViewCategory", "true");
		mv.addObject("active", "adminCategory");
		mv.addObject("displayAdminAction", "true");

		return mv;
	}

	// ------------------------------ end of category ----------------
	
	
	
//	Method for performing list operation
	private List<CategoryModel> getCatoryModelList() {
		List<CategoryModel> list = new ArrayList<>();
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
		return list;
	}
}
