package com.mountainpier.platform.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServerRequest {
	
	private String gameId;
	
	private String name;
	
	private Integer channelId;
	
}
