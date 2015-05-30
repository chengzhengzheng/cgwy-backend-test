package com.mishu.cgwy.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;

	private String description;

	private String rule;
	private BigDecimal discount = BigDecimal.ZERO;

	private PromotableItems promotableItems = null;
	private Integer promotionConstants;

}
