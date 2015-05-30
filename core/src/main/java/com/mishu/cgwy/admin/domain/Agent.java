package com.mishu.cgwy.admin.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mishu.cgwy.common.domain.Warehouse;

import lombok.Data;

@Data
public class Agent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String telephone;

	private String email;

	private String address;

	private boolean selfSupport = false;

	@ManyToOne
	@JoinColumn(name = "warehouse_id", nullable = false)
	private Warehouse warehouse;

	private boolean available = true;

}
