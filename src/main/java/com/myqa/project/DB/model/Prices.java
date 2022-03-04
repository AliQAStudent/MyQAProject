package com.myqa.project.DB.model;

public class Prices {
	private String type;
	private int price;
	public Prices() {
		this.type = "undefined";
		this.price = 0;
	}
	public Prices(String type, int price) {
		this.type = type;
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public int getPrice() {
		return price;
	}
}
