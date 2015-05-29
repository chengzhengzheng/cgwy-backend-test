package com.mishu.cgwy.order.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import lombok.Data;

import com.mishu.cgwy.admin.domain.Agent;
import com.mishu.cgwy.common.domain.Warehouse;
@Data
public class OrderGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@JoinTable(name="order_group_member_xref",
			joinColumns = @JoinColumn(name = "order_group_id"),
			inverseJoinColumns = @JoinColumn(name = "order_id")
	)
	private List<Order> members = new ArrayList<Order>();
	
	@ManyToOne
	@JoinColumn(name="agent_id")
	private Agent agent;
	
	@ManyToOne
	@JoinColumn(name="warehouse_id")
	private Warehouse warehouse;
	

}
