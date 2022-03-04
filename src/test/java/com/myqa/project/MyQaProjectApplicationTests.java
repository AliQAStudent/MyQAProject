package com.myqa.project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.myqa.project.DTOs.GetAllPricesResponse;
import com.myqa.project.DTOs.GetAllStockResponse;
import com.myqa.project.DTOs.LoadStockRequest;
import com.myqa.project.DTOs.LoadStockResponse;
import com.myqa.project.DTOs.PurchaseIceCreamRequest;
import com.myqa.project.DTOs.UpdatePricesRequest;
import com.myqa.project.DTOs.UpdatePricesResponse;

@SpringBootTest
class MyQaProjectApplicationTests {
	int conesInStock = 0;
	int lolliesInStock = 0;
	int tubsInStock = 0;
	int conesPrice = 0;
	int lolliesPrice = 0;
	int tubsPrice = 0;
	int todaysEarnings = 0;
	int sales = 0;
	@Autowired
	private APIController controller;
	
	@BeforeTestClass
	public void setup() {
		todaysEarnings = controller.getTodaysEarnings();
		sales = controller.getAllSales().AllSales.size();
	}
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
	@Test
	void testStockIsPresent() {
		GetAllStockResponse resp = controller.getAllStock();
		resp.AllCurrentStock.forEach(stock ->{
			if(stock.getType().equals("lollie"))
				lolliesInStock = stock.getAmount();
			else if(stock.getType().equals("tub"))
				tubsInStock = stock.getAmount();
			else if(stock.getType().equals("cone"))
				conesInStock = stock.getAmount();
		});
		assertThat(resp.AllCurrentStock.size() == 3);
	}
	@Test
	void testPricesArePresent() {
		GetAllPricesResponse resp = controller.getAllPrices();
		resp.AllCurrentPrices.forEach(stock ->{
			if(stock.getType().equals("lollie"))
				lolliesPrice = stock.getPrice();
			else if(stock.getType().equals("tub"))
				tubsPrice = stock.getPrice();
			else if(stock.getType().equals("cone"))
				conesPrice = stock.getPrice();
		});
		assertThat(resp.AllCurrentPrices.size() == 3);
	}
	@Test
	void testStockIsIncremented() {
		boolean[] hasIncremented = {false};
		LoadStockRequest request = new LoadStockRequest("cone",3);
		LoadStockResponse resp = controller.loadStock(request);
		resp.AllCurrentStock.forEach(stock->{
			if(stock.getType().equals("cone") && stock.getAmount() == conesInStock + 3)
				hasIncremented[0] = true;
		});
		assertThat(hasIncremented[0]);
	}
	@Test
	void testPriceIsUpdated() {
		boolean[] hasUpdated = {false};
		UpdatePricesRequest request = new UpdatePricesRequest("cone",conesPrice + 1);
		UpdatePricesResponse resp = controller.updatePrices(request);
		resp.AllCurrentPrices.forEach(price->{
			if(price.getType().equals("cone") && price.getPrice() == conesPrice + 1)
				hasUpdated[0] = true;
		});
		assertThat(hasUpdated[0]);
	}
	@Test
	void testPurchase() {
		PurchaseIceCreamRequest request = new PurchaseIceCreamRequest("cone");
		String resp = controller.purchaseIceCream(request);
		assertEquals("SUCCESS",resp);
	}
	@Test
	void testEarningsHaveIncreased() {
		assertNotSame(todaysEarnings, controller.getTodaysEarnings());
	}
	@Test
	void testSalesHaveIncreased() {
		assertNotSame(sales, controller.getAllSales().AllSales.size());
	}
}
