package com.mountainpier.platform.web.model;

import com.mountainpier.platform.domain.Channel;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class ChannelResponse {

	private Integer id;
	private String username;
	private String password;
	private String email;
	private String oauthToken;
	private Date dateAdded;
	private String creatorId;

	public ChannelResponse(Channel channel) {
		this.id = channel.getId();
		this.username = channel.getUsername();
		this.email = channel.getEmail();
		this.password = channel.getPassword();
		this.oauthToken = channel.getOauthToken();
		this.dateAdded = channel.getDateAdded();
		this.creatorId = channel.getCreatorId().toString();
	}
}
