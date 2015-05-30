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

@Data
@Entity
public class AdminRole {
	public static final String Administrator = "Administrator";
	public static final String CustomerServiceSupervisor = "CustomerServiceSupervisor";
	public static final String CustomerService = "CustomerService";
	public static final String OperationsSupervisor = "OperationsSupervisor";
	public static final String OperationsStaff = "OperationsStaff";
	public static final String FinancialStaff = "FinancialStaff";
	public static final String PurchaseStaff = "PurchaseStaff";
	public static final String PurchaseSupervisor = "PurchaseSupervisor";
	public static final String LogisticsSupervisor = "LogisticsSupervisor";
	public static final String LogisticsStaff = "LogisticsStaff";

	public static final String CustomerServiceAssistant = "CustomerServiceAssistant";
	public static final String LogisticsAssistant = "LogisticsAssistant";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String displayName;

	@ManyToMany
	@JoinTable(name = "admin_role_permission_xref", joinColumns = @JoinColumn(name = "admin_role_id"), inverseJoinColumns = @JoinColumn(name = "admin_permission_id"))
	private Set<AdminPermission> adminPermissions = new HashSet<AdminPermission>();

	@Override
	public String toString() {
		return "AdminRole [id=" + id + ", name=" + name + ", displayName="
				+ displayName + ", adminPermissions=" + adminPermissions + "]";
	}

}
