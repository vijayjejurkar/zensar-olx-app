package com.app.olx.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfo implements InfoContributor{

	@Override
	public void contribute(Builder builder) {
		
		Map<String, Object> totalUser = new HashMap<>();
		totalUser.put("total-registered-users", (Object)new Integer(478));
		totalUser.put("active-logged-in-count", (Object)new Integer(35));
		builder.withDetails(totalUser);
		
	}

}
