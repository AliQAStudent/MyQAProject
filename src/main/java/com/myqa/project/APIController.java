package com.myqa.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myqa.project.DB.DB;
import com.myqa.project.DTOs.GetAllSalesResponse;
import com.myqa.project.DTOs.GetAllStockResponse;
import com.myqa.project.DTOs.LoadStockRequest;
import com.myqa.project.DTOs.LoadStockResponse;
import com.myqa.project.DTOs.PurchaseIceCreamRequest;
import com.myqa.project.DTOs.UpdatePricesRequest;
import com.myqa.project.DTOs.UpdatePricesResponse;

@RestController
public class APIController {
	@RequestMapping("/test")
    public String getAllUsers() {
        return "hello world";
    }
	
	//START Supplier APIs
	@PostMapping("/loadStock")
	public LoadStockResponse loadStock(@RequestBody LoadStockRequest newStock) {
		DB.getInstance().updateStock(newStock.getType(),newStock.getAmount());
		LoadStockResponse resp = new LoadStockResponse();
		resp.AllCurrentStock = DB.getInstance().getAllStock();
	    return resp;
	}
	@PostMapping("/updatePrices")
	public UpdatePricesResponse updatePrices(@RequestBody UpdatePricesRequest newPrice) {
		DB.getInstance().updatePrice(newPrice.getType(),newPrice.getNewPrice());
		UpdatePricesResponse resp = new UpdatePricesResponse();
		resp.AllCurrentPrices = DB.getInstance().getAllPrices();
	    return resp;
	}
	//END
	//START Admin APIs
	@GetMapping("/getAllStock")
	public GetAllStockResponse getAllStock() {
		GetAllStockResponse resp = new GetAllStockResponse();
		resp.AllCurrentStock = DB.getInstance().getAllStock();
	    return resp;
	}
	@GetMapping("/getTodaysEarnings")
	public int getTodaysEarnings() {
		return DB.getInstance().getTodaysEarnings();
	}
	@GetMapping("/getAllSales")
	public GetAllSalesResponse getAllSales() {
		GetAllSalesResponse resp = new GetAllSalesResponse();
		resp.AllSales = DB.getInstance().getAllSales();
	    return resp;
	}
	//END
	//START Customer APIs
	@PostMapping("/purchaseIceCream")
	public String purchaseIceCream(@RequestBody PurchaseIceCreamRequest purchaseRequest) {
		return DB.getInstance().purchaseIceCream(purchaseRequest.getType());
	}
	//END
}
