package com.ahmad.viewmodel;

import com.ahmad.model.CartItem;

public class CartItemModel {
	private CartItem cartItem;
	private String productName;

	public CartItem getCartItem() {
		return cartItem;
	}

	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
