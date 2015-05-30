package com.mishu.cgwy.admin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class AdminPermission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String displayName;

	@Override
	public String toString() {
		return "AdminPermission [id=" + id + ", name=" + name
				+ ", displayName=" + displayName + "]";
	}
}
