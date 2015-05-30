package com.mishu.cgwy.admin.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

import org.hibernate.annotations.BatchSize;

import com.mishu.cgwy.common.domain.Warehouse;
import com.mishu.cgwy.common.domain.Zone;

@Data
@Entity
public class AdminUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(unique = true)
	private String username;

	@JoinColumn(nullable = false)
	private String realname;

	private String password;

	private boolean enabled = true;

	private String telephone;

	@ManyToMany
	@JoinTable(name = "admin_user_role_xref", joinColumns = @JoinColumn(name = "admin_user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "admin_role_id", referencedColumnName = "id"))
	@BatchSize(size = 50)
	private Set<AdminRole> adminRoles = new HashSet<AdminRole>();

	@ManyToMany
	@JoinTable(name = "admin_user_zone_xref", joinColumns = @JoinColumn(name = "admin_user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "zone_id", referencedColumnName = "id"))
	@BatchSize(size = 50)
	private Set<Zone> zones = new HashSet<Zone>();

	@ManyToMany
	@JoinTable(name = "admin_user_warehouse_xref", joinColumns = @JoinColumn(name = "admin_user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "warehouse_id", referencedColumnName = "id"))
	@BatchSize(size = 50)
	private Set<Warehouse> warehouses = new HashSet<Warehouse>();

	public boolean hasRole(String roleName) {
		for (AdminRole role : adminRoles) {
			if (role.getName().equals(roleName))
				return true;
		}
		return false;
	}

}
