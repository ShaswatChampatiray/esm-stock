package com.techacademy.esm.stock.model;

import java.util.List;

public class Stocks {
	
	private List<Stock> stocks;
	
	public Stocks() {
		
	}

	public Stocks(List<Stock> stocks) {
		super();
		this.stocks = stocks;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	
	

}
