package com.mountainpier.platform.repository;

import com.mountainpier.platform.domain.Match;
import com.mountainpier.platform.domain.Server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface MatchRepository
		extends JpaRepository<Match, Integer> {
	
	Match getMatchByUserId(UUID userId);
	
	void deleteMatchByUserIdAndServer(UUID userId, Server server);
	
}
