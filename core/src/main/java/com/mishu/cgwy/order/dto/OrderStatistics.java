package com.mishu.cgwy.order.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderStatistics {
	private Long count;

	private BigDecimal total;

	private int firstOrderCount;

}
