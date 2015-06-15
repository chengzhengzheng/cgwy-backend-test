package com.mishu.cgwy.profile.controller;

import java.math.BigDecimal;

import lombok.Data;

import com.mishu.cgwy.product.wrapper.SkuWrapper;
import com.mishu.cgwy.profile.wrapper.CustomerWrapper;
@Data
public class FavoriteWrapper {
	private Long id;
	
	private CustomerWrapper customer;
	
	private SkuWrapper sku;
	
	private BigDecimal salePrice = BigDecimal.ZERO;
	
	private long quantity = 0;
	
	private int count = 0;
	
	private int maxBuy = 0;
	
	public FavoriteWrapper(){
		
}



	
	
	
			
			
	
	

}
