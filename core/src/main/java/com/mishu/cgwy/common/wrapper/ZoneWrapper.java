package com.mishu.cgwy.common.wrapper;

import lombok.Data;

import com.mishu.cgwy.common.domain.Zone;

@Data
public class ZoneWrapper {
	private Long id;

	private String name;

	private Boolean active;

	private String displayName;

	private WarehouseWrapper warehouse;

	public ZoneWrapper() {

	}

	public ZoneWrapper(Zone zone) {

	}

}
