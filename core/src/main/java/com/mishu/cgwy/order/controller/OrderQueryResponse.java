package com.mishu.cgwy.order.controller;

import java.util.List;

import lombok.Data;

import com.mishu.cgwy.order.dto.OrderStatistics;
import com.mishu.cgwy.order.wrapper.SimpleOrderWrapper;

@Data
public class OrderQueryResponse {
	private long total;
	private int page;
	private int pageSize;

	private List<SimpleOrderWrapper> orders;
	private OrderStatistics orderStatistics;

}
