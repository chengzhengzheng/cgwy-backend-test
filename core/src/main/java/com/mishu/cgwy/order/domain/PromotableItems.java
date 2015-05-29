package com.mishu.cgwy.order.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

import com.mishu.cgwy.product.domain.Sku;

@Data
@Entity
public class PromotableItems {
	@ManyToOne
	@JoinColumn(name="sku_id")
	 private Sku sku;
	private int quantity;

}
