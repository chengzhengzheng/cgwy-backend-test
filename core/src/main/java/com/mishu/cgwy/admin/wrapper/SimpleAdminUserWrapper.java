package com.mishu.cgwy.admin.wrapper;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

import com.mishu.cgwy.admin.domain.AdminRole;
import com.mishu.cgwy.admin.domain.AdminUser;

@Data
public class SimpleAdminUserWrapper {
	private Long id;

	private String username;

	private boolean enabled = true;

	private String telephone;

	private String realname;

	private Set<SimpleAdminRoleWrapper> adminRoles = new HashSet<SimpleAdminRoleWrapper>();

	public SimpleAdminUserWrapper() {

	}

	public SimpleAdminUserWrapper(AdminUser adminUser) {
		id = adminUser.getId();
		username = adminUser.getUsername();
		telephone = adminUser.getTelephone();
		enabled = adminUser.isEnabled();
		realname = adminUser.getRealname();
		for (AdminRole role : adminUser.getAdminRoles()) {
			adminRoles.add(new SimpleAdminRoleWrapper(role));
		}
	}
}
