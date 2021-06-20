package com.techacademy.esm.stock.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techacademy.esm.stock.model.Stock;
import com.techacademy.esm.stock.model.Stocks;
import com.techacademy.esm.stock.repository.StockRepository;

@Service
public class StockService {
	
	Logger logger = LoggerFactory.getLogger(StockService.class);
	
	@Autowired
	private StockRepository stockRepository;
	
	public Stock addStock(String companyCode, Stock stock) {
		stock.setCompanyCode(companyCode);
		stock.setStockPurchaseDateTime(LocalDateTime.now());
		return stockRepository.save(stock);
	}

	public Stocks getStock(String companyCode, Optional<LocalDateTime> startDate, Optional<LocalDateTime> endDate) {
		List<Stock> stockList = new ArrayList<Stock>();
		if (startDate.isPresent() && endDate.isPresent()) {
			logger.info("StockService :: Invoked getStock method for company Code : " +companyCode + " and between " + startDate + " and " + endDate );
			stockRepository.findAllByCompanyCodeByStockPurchaseDateBetween(companyCode, startDate.get(), endDate.get()).forEach(stockList::add);;
			return new Stocks(stockList);
	    } else {
	    	logger.info("StockService :: Invoked getStock method for company Code : " +companyCode + "");
	        stockRepository.findByCompanyCode(companyCode).forEach(stockList::add);
	        return new Stocks(stockList);
	    }
	}

	public Stock getLatestStockByCompanyCode(String companyCode) {
		logger.info("StockService :: Invoked getLatestStockByCompanyCode method for company Code : " +companyCode);
		return stockRepository.findLatestStockByCompanyCode(companyCode);
	}

	public void deleteStockByCompanyCode(String companyCode) {
		logger.info("StockService :: Invoked deleteStockByCompanyCode method for company Code : " +companyCode);
		stockRepository.deleteAllByCompanyCode(companyCode);
	}

}
