package com.mishu.cgwy.product.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mishu.cgwy.common.domain.MediaFile;

@Data
public class Product {
	
	private static ObjectMapper objectmapper = new ObjectMapper();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String desciption;
	
	private String barCode;
	
	/**
	 * 标准包装、散装
	 */
	private boolean discreate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "media_file_id")
	private MediaFile mediaFile;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	private int capacityInBundle = 1;
	
	@OneToMany(mappedBy="product",fetch=FetchType.LAZY)
	private List<Sku> skus = new ArrayList<Sku>();
	
	@Column(columnDefinition="text")
	private String properties;
	
	private String details;
	
	
	public Map<String,String> getPropertyMap(){
		try{
			if(StringUtils.isBlank(properties)){
				return new HashMap<String,String>();
			}else{
				return objectmapper.readValue(properties, new TypeReference<Map<String,String>>() {
				});
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		
	}
	
	public void setPropertyMap(Map<String,String> propertyMap){
		JSONObject jsonObject = new JSONObject(propertyMap);
		this.properties = jsonObject.toString();
	}
	
	
	
	
	
	
	
	

}
