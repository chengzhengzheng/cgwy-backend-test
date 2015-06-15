package com.mishu.cgwy.profile.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

import com.mishu.cgwy.product.domain.Sku;
@Data
public class Favorite {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "sku_id")
	private Sku sku;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	

}
