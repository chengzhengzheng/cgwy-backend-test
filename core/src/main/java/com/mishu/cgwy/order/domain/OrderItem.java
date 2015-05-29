package com.mishu.cgwy.order.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

import com.mishu.cgwy.product.domain.Sku;

@Data
@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "sku_id")
	private Sku sku;
	@Column(precision = 19, scale = 2)
	private BigDecimal price;

	private int quantity;
	@Column(precision = 19, scale = 2)
	private BigDecimal totalPrice = BigDecimal.ZERO;

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", order=" + order + ", sku=" + sku
				+ ", price=" + price + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + "]";
	}

}
