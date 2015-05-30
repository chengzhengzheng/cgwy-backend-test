package com.mishu.cgwy.profile.domain;

import javax.persistence.Embeddable;

import org.elasticsearch.common.lang3.StringUtils;

import lombok.Data;

@Data
@Embeddable
public class Wgs84Point {
	private Double latitude;
	private Double longitude;

	public static Wgs84Point fromString(String s) {
		try {
			if (StringUtils.isNotBlank(s)) {
				final String[] tokens = s.split(",");
				Wgs84Point result = new Wgs84Point();
				result.setLatitude(Double.valueOf(tokens[0]));
				result.setLongitude(Double.valueOf(tokens[1]));
				return result;
			} else {
				return null;
			}

		} catch (Exception e) {
			return null;
		}
	}
}
