package com.mishu.cgwy.profile.wrapper;

import java.util.Date;

import lombok.Data;

import com.mishu.cgwy.admin.wrapper.SimpleAdminUserWrapper;
import com.mishu.cgwy.common.wrapper.ZoneWrapper;
import com.mishu.cgwy.profile.domain.Customer;

@Data
public class CustomerWrapper {
	private Long id;

	private String username;

	private String userNumber;

	private Date createTime;

	private ZoneWrapper zone;

	private SimpleAdminUserWrapper adminUser;

	public CustomerWrapper() {

	}

	public CustomerWrapper(Customer customer) {
		this.id = customer.getId();
		this.username = customer.getUsername();
		this.userNumber = customer.getUserNumber();
		this.zone = customer.getZone() == null ? null : new ZoneWrapper(
				customer.getZone());
		this.adminUser = customer.getAdminUser() == null ? null
				: new SimpleAdminUserWrapper(customer.getAdminUser());
		this.createTime = customer.getCreateTime();

	}
}
