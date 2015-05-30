package com.mishu.cgwy.common.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;

@Data
public class Zone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private boolean active;

	@ManyToOne
	@JoinColumn(name = "warehouse_id")
	private Warehouse warehouse;

	@ManyToOne
	@JoinColumn(name = "region_id", nullable = false)
	private Region region;

	@Transient
	public String getDisplayName() {
		return region.getDisplayName() + "-" + name;
	}

	@Override
	public String toString() {
		return "Zone{" + "id=" + id + ", name='" + name + '\'' + ", active="
				+ active + '}';
	}

}
