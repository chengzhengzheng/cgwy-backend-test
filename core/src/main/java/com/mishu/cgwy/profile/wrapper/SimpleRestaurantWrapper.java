package com.mishu.cgwy.profile.wrapper;

import com.mishu.cgwy.profile.domain.Restaurant;

public class SimpleRestaurantWrapper {
	private Long id;
	private String name;
	private AddressWrapper address;
	private String license;
	private int status;

	private String receiver; // 联系人
	private String telephone; // 联系人电话

	private Integer type;// 餐馆类型

	public SimpleRestaurantWrapper() {

	}

	public SimpleRestaurantWrapper(Restaurant restaurant) {
		id = restaurant.getId();
		name = restaurant.getName();
		if (restaurant.getAddress() != null) {
			address = new AddressWrapper(restaurant.getAddress());
		}

		license = restaurant.getLicense();
		status = restaurant.getStatus();
		receiver = restaurant.getReceiver();
		telephone = restaurant.getTelephone();
		type = restaurant.getType();
	}

}
