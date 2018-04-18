package com.mountainpier.platform.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "matches", schema = "public")
public class Match {

	@Id
	@Column(name = "matches_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "matches_user_id")
	private UUID userId;
	
	@ManyToOne
	@JoinColumn(name = "matches_server_id")
	private Server server;

}
