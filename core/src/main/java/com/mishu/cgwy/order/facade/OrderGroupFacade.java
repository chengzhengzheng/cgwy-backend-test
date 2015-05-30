package com.mishu.cgwy.order.facade;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.common.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.mishu.cgwy.admin.domain.AdminUser;
import com.mishu.cgwy.order.controller.OrderQueryRequest;
import com.mishu.cgwy.order.controller.OrderQueryResponse;
import com.mishu.cgwy.order.domain.Order;
import com.mishu.cgwy.order.domain.OrderGroup;
import com.mishu.cgwy.order.service.OrderService;
import com.mishu.cgwy.order.wrapper.SimpleOrderWrapper;

@Service
public class OrderGroupFacade {
	@Autowired
	private OrderService orderService;

	public OrderQueryResponse getOrderByTracker(OrderQueryRequest request,
			AdminUser operator) {

		OrderQueryResponse response = new OrderQueryResponse();

		Page<OrderGroup> page = orderService.getOrderByTracker(request,
				operator);
		List<SimpleOrderWrapper> result = new ArrayList<SimpleOrderWrapper>();

		for (OrderGroup og : page) {
			List<Order> orders = og.getMembers();

			for (Order order : orders) {

				boolean result1 = checkSatisfied(order, request);
				boolean result2 = checkMemo(order, request);

				if (result1 && result2)
					result.add(new SimpleOrderWrapper(order));

			}
		}

		response.setOrders(result);
		response.setPage(request.getPage());
		response.setPageSize(request.getPageSize());
		response.setOrderStatistics(null);

		return response;

	}

	private boolean checkMemo(Order order, OrderQueryRequest request) {
		boolean hasMemo = StringUtils.isNotBlank(order.getMemo()) ? true:false;
		return request.isHasMemo() == hasMemo;
	}
	

	private boolean checkSatisfied(Order order, OrderQueryRequest request) {
		return order.isSatisfied() == request.isSatisfied();
	}

}
