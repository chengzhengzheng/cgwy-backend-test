package com.mishu.cgwy.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mishu.cgwy.order.domain.OrderGroup;

public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long>,
		JpaSpecificationExecutor<OrderGroup> {

}
