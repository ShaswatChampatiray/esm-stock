package com.techacademy.esm.stock.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techacademy.esm.stock.model.Stock;
import com.techacademy.esm.stock.model.Stocks;
import com.techacademy.esm.stock.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

	Logger logger = LoggerFactory.getLogger(StockController.class);

	@Autowired
	private StockService stockService;

	@PostMapping("/add/{companyCode}")
	public ResponseEntity<Stock> addStock(@PathVariable String companyCode, @RequestBody Stock stock) {
		logger.info("StockController :: Invoked addStock method");
		
		if(companyCode != null) {
			return new ResponseEntity<>(stockService.addStock(companyCode, stock), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = {"/get/{companyCode}", "/get/{companyCode}/{startDate}/{endDate}"})
	public ResponseEntity<Stocks> getStock(@PathVariable String companyCode,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> startDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> endDate) {
		logger.info("StockController :: Invoked getStock method");
		return new ResponseEntity<>(stockService.getStock(companyCode, startDate, endDate), HttpStatus.OK);
	}

	@RequestMapping(value = "/get/latest/{companyCode}")
	public ResponseEntity<Stock> getLatestStockByCompanyCode(@PathVariable String companyCode) {
		logger.info("StockController :: Invoked getLatestStockByCompanyCode method");
		return new ResponseEntity<>(stockService.getLatestStockByCompanyCode(companyCode), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/company/{companyCode}")
	public ResponseEntity<Void> deleteStockByCompanyCode(@PathVariable String companyCode) {
		logger.info("StockController :: Invoked getLatestStockByCompanyCode method");
		stockService.deleteStockByCompanyCode(companyCode);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
