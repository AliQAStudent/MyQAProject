package com.myqa.project.DB.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Stock {
	@Id
    @GeneratedValue
    private int id;
	private String type;
	private int amount;
	public Stock() {
		this.id = 0;
		this.type = "undefined";
		this.amount = 0;
	}
	public Stock(int id, String type, int amount) {
		this.id = id;
		this.type = type;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public int getAmount() {
		return amount;
	}
}
