package com.mountainpier.platform.web.model;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ChannelRequest {
	
	private String username;
	
	private String email;
	
	private String password;
	
	private Date dateAdded;
	
	private String creator;
	
	private String oauthToken;
	
}
