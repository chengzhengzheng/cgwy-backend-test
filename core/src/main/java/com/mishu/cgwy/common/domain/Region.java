package com.mishu.cgwy.common.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;

@Data
public class Region {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "city_id", nullable = false)
	private City city;

	@OneToMany(mappedBy = "region")
	private List<Zone> zones = new ArrayList<Zone>();

	@Transient
	public String getDisplayName() {
		return city.getName() + "-" + name;
	}

	@Override
	public String toString() {
		return "Region{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}
