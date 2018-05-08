package com.mountainpier.platform.web.model;

import com.mountainpier.platform.domain.Server;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServerResponse {
	
	private Integer id;
	private String gameId;
	private String name;
	private ChannelResponse channel;
	
	public ServerResponse(Server server) {
		this.id = server.getId();
		this.gameId = server.getId().toString();
		this.name = server.getName();
		this.channel = new ChannelResponse(server.getChannel());
	}
	
}
