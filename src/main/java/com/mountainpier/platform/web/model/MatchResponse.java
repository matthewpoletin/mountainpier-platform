package com.mountainpier.platform.web.model;

import com.mountainpier.platform.domain.Match;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchResponse {
	
	public String userId;
	public Integer serverId;
	
	public MatchResponse(Match match) {
		this.userId = match.getUserId().toString();
		if (match.getServer() != null) this.serverId = match.getServer().getId();
	}
	
}
