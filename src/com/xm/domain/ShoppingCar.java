package com.xm.domain;

import java.util.List;

public class ShoppingCar {
	private int sid;
	private int amount;
	private int subtotal;
	private int uid;
	private Products products;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "ShoppingCar [sid=" + sid + ", amount=" + amount + ", subtotal=" + subtotal + ", uid=" + uid
				+ ", products=" + products + "]";
	}
}
