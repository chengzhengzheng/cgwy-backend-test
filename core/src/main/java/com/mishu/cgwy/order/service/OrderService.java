package com.mishu.cgwy.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.mishu.cgwy.admin.domain.AdminUser;
import com.mishu.cgwy.admin.domain.AdminUser_;
import com.mishu.cgwy.order.controller.OrderQueryRequest;
import com.mishu.cgwy.order.domain.OrderGroup;
import com.mishu.cgwy.order.domain.OrderGroup_;
import com.mishu.cgwy.order.repository.OrderGroupRepository;

@Service
public class OrderService {
	@Autowired
	private OrderGroupRepository orderGroupRepository;

	public Page<OrderGroup> getOrderByTracker(final OrderQueryRequest request,
			AdminUser operator) {

		PageRequest pageable = new PageRequest(request.getPage(),

		request.getPageSize(), new Sort(Sort.Direction.DESC, "id"));

		return orderGroupRepository.findAll(new Specification<OrderGroup>() {
			public Predicate toPredicate(Root<OrderGroup> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<Predicate>();

				if (request.getTrackerId() != null) {
					predicates.add(cb.equal(root.get(OrderGroup_.tracker).get(AdminUser_.id), request.getTrackerId()));
				}
				
				if(request.getStart() != null){
					predicates.add(cb.greaterThanOrEqualTo(root.get(OrderGroup_.expectedArrivedDate), request.getStart()));
				}
				
				if(request.getEnd() != null){
					predicates.add(cb.lessThanOrEqualTo(root.get(OrderGroup_.expectedArrivedDate), request.getEnd()));
				}
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));

			}
		}, pageable);
	}

}
