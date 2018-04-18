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
	private String oauthToken;
	private String email;
	private Date dateAdded;
	private String creator;

	public ChannelResponse(Channel channel) {
		this.id = channel.getId();
		this.email = channel.getEmail();
		this.password = channel.getPassword();
		this.oauthToken = channel.getOauthToken();
		this.dateAdded = channel.getDateAdded();
		this.creator = channel.getCreatorId().toString();
	}
}
