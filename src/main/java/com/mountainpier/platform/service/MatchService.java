package com.mountainpier.platform.service;

import com.mountainpier.platform.domain.Match;

import com.mountainpier.platform.web.model.MatchRequest;
import org.springframework.data.domain.Page;

public interface MatchService {
	Page<Match> getMatches(Integer page, Integer size);
	Match createMatch(MatchRequest matchRequest);
	Match getMatchById(Integer matchId);
	Match updateMatchById(Integer matchId, MatchRequest matchRequest);
	void deleteMatchById(Integer matchId);
}
