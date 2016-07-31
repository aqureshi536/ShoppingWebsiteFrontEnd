package com.ahmad.viewmodel;

import com.ahmad.model.Category;

public class CategoryModel {
	private Category category;
	private long noOfProducts;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public long getNoOfProducts() {
		return noOfProducts;
	}

	public void setNoOfProducts(long noOfProducts) {
		this.noOfProducts = noOfProducts;
	}

}
