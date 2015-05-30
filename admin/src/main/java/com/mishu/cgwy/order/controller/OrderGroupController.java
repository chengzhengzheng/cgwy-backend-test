package com.mishu.cgwy.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mishu.cgwy.admin.controller.CurrentAdminUser;
import com.mishu.cgwy.admin.domain.AdminUser;
import com.mishu.cgwy.order.facade.OrderGroupFacade;

@Controller
public class OrderGroupController {
	
	@Autowired
	private OrderGroupFacade orderGroupFacade;
    @RequestMapping(value = "/api/order/{id}/complete", method = RequestMethod.POST)
    @ResponseBody
	public OrderQueryResponse listOrders(OrderQueryRequest request,@CurrentAdminUser  AdminUser operator){
		return orderGroupFacade.getOrderByTracker(request,operator);
	}

}
