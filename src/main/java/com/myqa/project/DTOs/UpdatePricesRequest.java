package com.myqa.project.DTOs;


public class UpdatePricesRequest {
	private String type;
	private int newPrice;
	public String getType() {
		return type;
	}
	public int getNewPrice() {
		return newPrice;
	}
	public UpdatePricesRequest(String type, int newPrice) {
		this.type = type;
		this.newPrice = newPrice;
	}
}
