package com.mishu.cgwy.common.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.StringUtils;

public class MediaFile {
	 private static final String default7NiuDomain = "http://7xijms.com1.z0.glb.clouddn.com/";
	    // TODO
	    private static final String defaultLocalDomain = "http://www.canguanwuyou.cn";


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String localPath;

	    private String qiNiuHash;

	    public String getUrl() {
	        if (StringUtils.isNotBlank(qiNiuHash) ) {
	            return default7NiuDomain + qiNiuHash;
	        } else if (StringUtils.isNotBlank(localPath)) {
	            return defaultLocalDomain + localPath;
	        } else {
	            return null;
	        }
	    }

}
