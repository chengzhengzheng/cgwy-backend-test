package com.mishu.cgwy.common.wrapper;

import lombok.Data;

import com.mishu.cgwy.common.domain.Warehouse;

@Data
public class WarehouseWrapper {
	private Long id;

	private String name;

	private CityWrapper city;

	private String displayName;

	public WarehouseWrapper() {

	}

	public WarehouseWrapper(Warehouse warehouse) {
		this.id = warehouse.getId();
		this.name = warehouse.getName();
		this.city = new CityWrapper(warehouse.getCity());
		this.displayName = warehouse.getDisplayName();
	}

}
