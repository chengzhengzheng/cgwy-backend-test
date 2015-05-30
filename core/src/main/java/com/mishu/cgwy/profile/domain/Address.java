package com.mishu.cgwy.profile.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

import com.mishu.cgwy.common.domain.Zone;

@Embeddable
@Data
public class Address {

	@ManyToOne
	@JoinColumn(name = "zone_id")
	private Zone zone;

	private String address;

	@Embedded
	private Wgs84Point wgs84Point;

}
