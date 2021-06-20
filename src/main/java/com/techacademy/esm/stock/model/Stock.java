package com.techacademy.esm.stock.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "StockId")
	private Long stockId;
	@Column(name = "StockPrice", nullable = false)
	private BigDecimal stockPrice;
	@Column(name = "StockPurchaseDate")
	private LocalDateTime stockPurchaseDateTime;
	@Column(name = "CompanyCode")
	private String companyCode;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public LocalDateTime getStockPurchaseDateTime() {
		return stockPurchaseDateTime;
	}

	public void setStockPurchaseDateTime(LocalDateTime stockPurchaseDateTime) {
		this.stockPurchaseDateTime = stockPurchaseDateTime;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

}
