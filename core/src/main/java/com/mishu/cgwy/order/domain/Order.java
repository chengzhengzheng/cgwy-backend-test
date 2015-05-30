package com.mishu.cgwy.order.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

import com.mishu.cgwy.profile.domain.Customer;
import com.mishu.cgwy.profile.domain.Restaurant;

@Entity
@Table(name = "cgwy_order", indexes = { @Index(name = "ORDER_STATUS_INDEX", columnList = "status", unique = false) })
@Data
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal total = BigDecimal.ZERO;

	/**
	 * Returns the subtotal price for the order. The subtotal price is the price
	 * of all order items with item offers applied. The subtotal does not take
	 * into account the order promotions, shipping costs or any taxes that apply
	 * to this order.
	 *
	 * @return the total item price with offers applied
	 */
	private BigDecimal subToal = BigDecimal.ZERO;

	private BigDecimal shipping = BigDecimal.ZERO;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	private int status;

	private Date submitDate;

	// drops the time value and only preserves the date use the
	// TemporalType.DATE
	@Temporal(TemporalType.DATE)
	private Date expectedArrivedDate;

	// orphanRemoval:(Optional) Whether to apply the remove operation to
	// entities that have been removed
	// from the relationship and to cascade the remove operation to those
	// entities
	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "order_id")
	// Unidirectional One-to-Many association using a foreign key mapping
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	private String memo = "";

	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

	@OneToMany(mappedBy = "order")
	private List<Refund> refunds = new ArrayList<Refund>();

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Promotion.class)
	@JoinTable(name = "order_promotion_xref", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "promotion_id"))
	private List<Promotion> promotions = new ArrayList<Promotion>();

	// 计算所有的OrderItem的值只是简单的相加
	public void calculateSubTotal() {
		subToal = BigDecimal.ZERO;
		for (OrderItem orderItem : orderItems) {
			orderItem.setTotalPrice(orderItem.getPrice().multiply(
					BigDecimal.valueOf(orderItem.getQuantity())));
			subToal = subToal.add(orderItem.getTotalPrice());
		}
	}

	// 应用promoation、shipping、discount 之后实际的价格
	public void calculateTotal() {
		BigDecimal discount = BigDecimal.ZERO;
		for (Promotion promotion : promotions) {
			discount = discount.add(promotion.getDiscount());
		}
		total = subToal.add(shipping).subtract(discount);
	}

	private boolean satisfied;

	@Override
	public String toString() {
		return "Order [id=" + id + ", total=" + total + ", subToal=" + subToal
				+ ", shipping=" + shipping + ", customer=" + customer
				+ ", status=" + status + ", submitDate=" + submitDate
				+ ", expectedArrivedDate=" + expectedArrivedDate
				+ ", orderItems=" + orderItems + ", memo=" + memo
				+ ", restaurant=" + restaurant + ", refunds=" + refunds
				+ ", promotions=" + promotions + "]";
	}

}
