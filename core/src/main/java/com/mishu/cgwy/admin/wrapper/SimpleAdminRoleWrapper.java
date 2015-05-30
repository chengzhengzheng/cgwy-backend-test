package com.mishu.cgwy.admin.wrapper;

import lombok.Data;

import com.mishu.cgwy.admin.domain.AdminRole;

@Data
public class SimpleAdminRoleWrapper {
	private Long id;

	private String name;

	private String displayName;

	public SimpleAdminRoleWrapper() {

	}

	public SimpleAdminRoleWrapper(AdminRole adminRole) {
		id = adminRole.getId();
		name = adminRole.getName();
		displayName = adminRole.getDisplayName();
	}

}
