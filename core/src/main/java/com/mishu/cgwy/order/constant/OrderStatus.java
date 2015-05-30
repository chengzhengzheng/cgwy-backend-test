package com.mishu.cgwy.order.constant;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderStatus {
	UNCOMMITTED(-2, "未提交"), COMMITTED(3, "已下单"), SHIPPING(2, "配送中"), COMPLETED(
			4, "已完成"), CANCEL(-1, "已取消");

	private Integer value;
	private String name;

	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	private OrderStatus(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public static OrderStatus fromInt(int i) {
		switch (i) {
		case -2:
			return UNCOMMITTED;
		case 3:
			return COMMITTED;
		case 2:
			return SHIPPING;
		case 4:
			return COMPLETED;
		case -1:
			return CANCEL;
		default:
			return UNCOMMITTED;
		}
	}
}
