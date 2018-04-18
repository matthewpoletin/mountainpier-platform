package com.mountainpier.platform.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "servers", schema = "public")
public class Server {
	
	@Id
	@Column(name = "servers_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "servers_game_id")
	private UUID gameId;
	
	@Column(name = "servers_name")
	private String name;
	
	@OneToOne
	@JoinColumn(name = "servers_channel_id")
	private Channel channel;
	
}
