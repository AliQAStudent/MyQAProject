package com.myqa.project;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myqa.project.DB.DB;
import com.myqa.project.DB.model.Stock;
import com.myqa.project.DTOs.LoadStockRequest;
import com.myqa.project.DTOs.LoadStockResponse;
import com.myqa.project.DTOs.UpdatePricesRequest;
import com.myqa.project.DTOs.UpdatePricesResponse;

@RestController
public class Controller {
	@RequestMapping("/test")
    public String getAllUsers() {
        return "hello world";
    }
	
	//START Supplier APIs
	@PostMapping("/loadStock")
	public LoadStockResponse register(@RequestBody LoadStockRequest newStock) {
		DB.getInstance().updateStock(newStock.getType(),newStock.getAmount());
		LoadStockResponse resp = new LoadStockResponse();
		resp.AllCurrentStock = DB.getInstance().getAllStock();
	    return resp;
	}
	@PostMapping("/updatePrices")
	public UpdatePricesResponse register(@RequestBody UpdatePricesRequest newPrice) {
		DB.getInstance().updatePrice(newPrice.getType(),newPrice.getNewPrice());
		UpdatePricesResponse resp = new UpdatePricesResponse();
		resp.AllCurrentPrices = DB.getInstance().getAllPrices();
	    return resp;
	}
	//END
}
