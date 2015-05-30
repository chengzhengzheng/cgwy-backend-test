package com.mishu.cgwy.common.wrapper;

import lombok.Data;

import com.mishu.cgwy.common.domain.City;

@Data
public class CityWrapper {
	private Long id;

	private String name;

	public CityWrapper() {
	}

	public CityWrapper(City city) {
		this.id = city.getId();
		this.name = city.getName();
	}
}
