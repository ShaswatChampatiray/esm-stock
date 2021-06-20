package com.techacademy.esm.stock.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.esm.stock.model.Stock;

public interface StockRepository extends CrudRepository<Stock, String> {

	public List<Stock> findByCompanyCode(String companyCode);
	
	public List<Stock> findAllByStockPurchaseDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

	@Query("select stk from Stock stk where stk.companyCode = :companyCode and stk.stockPurchaseDateTime between :startDateTime and :endDateTime")
	public List<Stock> findAllByCompanyCodeByStockPurchaseDateBetween(String companyCode, LocalDateTime startDateTime, LocalDateTime endDateTime);
	
	@Query(value = "select stk.* from stock stk where stk.company_code = :companyCode order by stk.stock_purchase_date desc limit 1", nativeQuery = true)
    public Stock findLatestStockByCompanyCode(String companyCode);

	@Transactional
	@Modifying
	@Query("delete from Stock stk where stk.companyCode = :companyCode")
	public void deleteAllByCompanyCode(String companyCode);

}
