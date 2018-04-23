package com.mountainpier.platform.service;

import com.mountainpier.platform.domain.Match;
import com.mountainpier.platform.web.model.MatchRequest;

import org.springframework.data.domain.Page;
import java.util.UUID;

public interface MatchService {
	Page<Match> getMatches(Integer page, Integer size);
	Match createMatch(Integer serverId, MatchRequest matchRequest);
	Match getMatchById(Integer matchId);
	Match getMatchByUserId(UUID userId);
	Match updateMatchById(Integer matchId, MatchRequest matchRequest);
	void deleteMatchById(Integer matchId);
	void deleteMatchBeServerIdAndUserId(Integer serverId, UUID userId);
}
