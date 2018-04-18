package com.mountainpier.platform.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "channels", schema = "public")
public class Channel {
	
	@Id
	@Column(name = "channels_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "channels_email", unique = true)
	private String email;

	@Column(name = "channels_password")
	private String password;
	
	@Column(name = "channels_oauth_token")
	private String oauthToken;
	
	@Column(name = "channels_creator_id")
	private UUID creatorId;
	
	@Column(name = "channels_date_added")
	private Date dateAdded;
	
}
