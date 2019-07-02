package com.xm.domain;

import java.sql.Date;

public class Order {
	private String oid;
	private String condition;
	private Date date;
	private int subtotal;
	private int uid;
	private String picture;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", condition=" + condition + ", date=" + date + ", subtotal=" + subtotal + ", uid="
				+ uid + ", picture=" + picture + "]";
	}
	
}
