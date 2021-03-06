package com.ahmad.admin.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

import com.ahmad.config.FileUtil;
import com.ahmad.dao.CategoryDAO;
import com.ahmad.dao.SupplierDAO;
import com.ahmad.model.Category;
import com.ahmad.model.Supplier;

@Controller
public class SupplierController {

	@Autowired
	private Supplier supplier;
	@Autowired
	private SupplierDAO supplierDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	private String path=   "E:\\ShoppingCart\\suppliers\\";
	// Supplier methods goes
	// here=============================================================

	@RequestMapping("/admin/viewSupplier")
	public ModelAndView adminViewSupplier(Model model) {
		ModelAndView mv = new ModelAndView("index");

		List<Supplier> supplierList = supplierDAO.listSupplier();
		if (supplierList != null && !supplierList.isEmpty())
			model.addAttribute("suppliers", supplierList);
		else
			model.addAttribute("noSupplier", "No supplier present");
		// Gets the category on the navbar
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		mv.addObject("isClickedAdminViewSupplier", "true");
		mv.addObject("active", "adminSupplier");
		mv.addObject("displayAdminAction", "true");
		return mv;

	}

	// It get form for adding supplier
	@RequestMapping("/admin/viewSupplier/addSupplier")
	public ModelAndView addSupplier() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("supplier", new Supplier());
		mv.addObject("isAddSupplierClicked", "true");
		// Gets the category on the navbar
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	// It gets after add the supplier
	@RequestMapping(value = "/admin/viewSupplier", method = RequestMethod.POST)
	public ModelAndView addSupplier(@ModelAttribute("supplier") @Valid Supplier supplier, BindingResult result,
			Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");

		// This gets activated when the values are not properly
		if (result.hasErrors()) {
			// Gets the category on the navbar
			List<Category> categoryList = categoryDAO.listCategory();
			mv.addObject("categoryList", categoryList);
			// ================================================================
			mv.addObject("isAddSupplierClicked", "true");
			mv.addObject("displayLogout", "true");
			mv.addObject("displayAdminAction", "true");
			return mv;
		}

		MultipartFile supplierImage = supplier.getSupplierImage();

		if(!Files.exists(Paths.get(path))){
			try{
			Files.createDirectories(Paths.get(path));
			}
			catch(IOException io){
				io.printStackTrace();
			}
		}
			FileUtil.upload(path, supplierImage, supplier.getSupplierId()+".png");

		supplierDAO.saveOrUpdate(supplier);
		List<Supplier> supplierList = supplierDAO.listSupplier();
		if (supplierList != null && !supplierList.isEmpty())
			model.addAttribute("suppliers", supplierList);
		else
			model.addAttribute("noSupplier", "No supplier present");

		// Get supplier name and supplier row
		int supplierSize = supplierList.size();
		this.supplier = supplierList.get(supplierSize - 1);
		String supplierName = this.supplier.getSupplierName();

		mv.addObject("supplierName", supplierName);
		mv.addObject("supplierSize", supplierSize);
		// ====================================================
		// Gets the category on the navbar
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================
		mv.addObject("addedSupplierMessage", "true");
		mv.addObject("isClickedAdminViewSupplier", "true");
		mv.addObject("active", "adminSupplier");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	@RequestMapping("/admin/viewSupplier/deleteSupplier/{supplierId}")
	public ModelAndView deleteSupplier(@PathVariable("supplierId") String supplierId, Model model,
			HttpServletRequest request) throws IOException {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("supplier", supplier);

		supplier = supplierDAO.get(supplierId);

		// Get the supplier name
		String supplierName = supplier.getSupplierName();
		mv.addObject("supplierNameDeleted", supplierName);
		// =======================================================
		mv.addObject("deletedSupplierMessage", "true");

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		System.out.println(Paths.get(path + supplierId + ".png"));
		if (!Files.exists(Paths.get(path))) {
			try {
				if (!Files.exists(Paths.get(path))) {
					Files.createDirectories(Paths.get(path));

				}
				if (Files.exists(Paths.get(path + supplierId + ".png"))) {

					Files.delete(Paths.get(path + supplierId + ".png"));

				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("File unable to delete", e);
			}
		}

		supplierDAO.delete(supplierId);

		List<Supplier> supplierList = supplierDAO.listSupplier();
		if (supplierList != null && !supplierList.isEmpty())
			model.addAttribute("suppliers", supplierList);
		else
			model.addAttribute("noSupplier", "No longer any supplier exists");
		// Gets the category on the navbar
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================

		mv.addObject("deletedSupplierMessage", "true");
		mv.addObject("isClickedAdminViewSupplier", "true");
		mv.addObject("active", "adminSupplier");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	@RequestMapping("/admin/viewSupplier/updateSupplier/{supplierId}")
	public ModelAndView updateSupplier(@PathVariable("supplierId") String supplierId) {
		ModelAndView mv = new ModelAndView("index");

		supplier = supplierDAO.get(supplierId);
		mv.addObject("supplier", supplier);

		// Gets the category on the navbar
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================

		mv.addObject("isUpdateSupplierClicked", "true");
		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	@RequestMapping(value = "/admin/viewSupplier/update", method = RequestMethod.POST)
	public ModelAndView updateSupplier(@ModelAttribute("supplier") @Valid Supplier supplier, BindingResult result,
			HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView("index");

		if (result.hasErrors()) {
			// Gets the category on the navbar
			List<Category> categoryList = categoryDAO.listCategory();
			mv.addObject("categoryList", categoryList);
			// ================================================================
			mv.addObject("isUpdateSupplierClicked", "true");
			mv.addObject("displayLogout", "true");
			mv.addObject("displayAdminAction", "true");
			return mv;
		}

		MultipartFile supplierImage = supplier.getSupplierImage();

		if(!Files.exists(Paths.get(path))){
			try{
			Files.createDirectories(Paths.get(path));
			}
			catch(IOException io){
				io.printStackTrace();
			}
		}
			FileUtil.upload(path, supplierImage, supplier.getSupplierId()+".png");
			
		supplierDAO.saveOrUpdate(supplier);
		List<Supplier> supplierList = supplierDAO.listSupplier();
		if(supplierList!=null&&!supplierList.isEmpty())
			model.addAttribute("suppliers", supplierList);
			else
				model.addAttribute("noSupplier","No supplier present");
		// Gets the category on the navbar
		List<Category> categoryList = categoryDAO.listCategory();
		mv.addObject("categoryList", categoryList);
		// ================================================================

		String supplierName = supplier.getSupplierName();
		mv.addObject("updatedSupplierName", supplierName);
		mv.addObject("updatedSupplierMessage", "true");
		mv.addObject("isClickedAdminViewSupplier", "true");
		// mv.addObject("isUpdateSupplierClicked", "true");
		mv.addObject("displayLogout", "true");
		mv.addObject("displayAdminAction", "true");
		return mv;
	}

	// Method for displaying Products on the admin page

	// private ModelAndView displaySupplierList(Model model) {
	//
	//
	// List<Supplier> supplierList = supplierDAO.listSupplier();
	// model.addAttribute("suppliers", supplierList);
	//
	//
	// ModelAndView mv= null;
	// mv.addObject("isClickedAdminViewSupplier", "true");
	// mv.addObject("active", "adminSupplier");
	// mv.addObject("displayAdminAction", "true");
	// return mv;
	// }

}
