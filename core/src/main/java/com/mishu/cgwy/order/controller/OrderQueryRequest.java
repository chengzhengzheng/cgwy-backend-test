package com.mishu.cgwy.order.controller;

import java.util.Date;

import lombok.Data;

@Data
public class OrderQueryRequest {
	private Date start;
	private Date end;
	private Integer status;
	private Long restaurantId;
	private String restaurantName;
	private Long customerId;
	private Long adminId;
	private Long orderId;
	private Date expectedArrivedDate;

	private int page;
	private int pageSize = 100;

	private String sortProperty = "id";
	private String sortDirection = "desc";

	private boolean PromotionTag = false;
	private Long warehouseId;

	private boolean hasMemo = true;
	private boolean satisfied = true;
	private Long trackerId;

}
