package com.mishu.cgwy.profile.wrapper;

import lombok.Data;

import com.mishu.cgwy.common.wrapper.ZoneWrapper;
import com.mishu.cgwy.profile.domain.Address;
import com.mishu.cgwy.profile.domain.Wgs84Point;

@Data
public class AddressWrapper {
	private ZoneWrapper zone;

	private RegionWrapper region;

	private String address;

	private Wgs84Point wgs84Point;

	public AddressWrapper() {

	}

	public AddressWrapper(Address obj) {
		zone = obj.getZone() == null ? null : new ZoneWrapper(obj.getZone());
		region = obj.getZone() == null ? null : new RegionWrapper(obj.getZone()
				.getRegion());
		address = obj.getAddress();
		wgs84Point = obj.getWgs84Point();
	}

}
