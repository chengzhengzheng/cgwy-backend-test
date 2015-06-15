package com.mishu.cgwy.product.wrapper;

import java.math.BigDecimal;

import com.mishu.cgwy.product.domain.Sku;

public class SkuWrapper {

	private Long id;
	
	private Long productId;
	
	private String name;
	
	private BrandWrapper brand;
	
	private CategoryWrapper category;
	
	private SkuState status;
	
	private String medialFileUrl;
	
	private BigDecimal marketPrice;
	
	private BigDecimal salePrice;
	
	private int capacityInBoudle = 1;
	
	private boolean boudle;
	
	public SkuWrapper(){
		
	}
	
	public SkuWrapper(Sku sku){
		
	}
	
}
