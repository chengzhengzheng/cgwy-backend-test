package com.mishu.cgwy.profile.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

import com.mishu.cgwy.admin.domain.AdminUser;
import com.mishu.cgwy.common.domain.Zone;

@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String password;

	private String telephone;

	private boolean enabled = true;

	@Transient
	private String userNumber;

	private Long referrerId;

	@ManyToOne
	@Column(name = "zone_id")
	private Zone zone;

	@ManyToOne
	@Column(name = "admin_user_id")
	private AdminUser adminUser;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Transient
	public String getUserNumber() {
		return String.valueOf(id);
	}

	@Override
	public String toString() {
		return "Customer{" + "id=" + id + ", username='" + username + '\''
				+ ", password='" + password + '\'' + ", telephone='"
				+ telephone + '\'' + ", enabled=" + enabled + ", createTime="
				+ createTime + '}';
	}

}
