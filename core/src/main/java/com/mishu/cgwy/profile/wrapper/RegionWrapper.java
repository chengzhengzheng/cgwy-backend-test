package com.mishu.cgwy.profile.wrapper;

import lombok.Data;

import com.mishu.cgwy.common.domain.Region;

@Data
public class RegionWrapper {
	private Long id;
	private String name;
	private String displayName;

	public RegionWrapper() {

	}

	public RegionWrapper(Region region) {
		id = region.getId();
		name = region.getName();
		displayName = region.getDisplayName();
	}
}
