package com.mishu.cgwy.order.wrapper;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

import com.mishu.cgwy.order.constant.OrderStatus;
import com.mishu.cgwy.order.domain.Order;
import com.mishu.cgwy.profile.wrapper.CustomerWrapper;
import com.mishu.cgwy.profile.wrapper.SimpleRestaurantWrapper;

@Data
public class SimpleOrderWrapper {
	private Long id;

	private BigDecimal total;

	private BigDecimal subTotal;

	private BigDecimal shipping;

	private OrderStatus status;

	private Date submitDate;

	private String memo;

	private CustomerWrapper customer;

	private SimpleRestaurantWrapper restaurant;

	private String orderNumber;

	private boolean satisfied;

	private SimpleOrderWrapper() {

	}

	public SimpleOrderWrapper(Order order) {
		id = order.getId();
		total = order.getTotal();
		shipping = order.getShipping();
		status = OrderStatus.fromInt(order.getStatus());
		submitDate = order.getSubmitDate();
		memo = order.getMemo();
		restaurant = new SimpleRestaurantWrapper(order.getRestaurant());
		orderNumber = String.valueOf(id);
		customer = new CustomerWrapper(order.getCustomer());
		satisfied = order.isSatisfied();

	}
}
