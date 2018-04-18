package com.mountainpier.platform.repository;

import com.mountainpier.platform.domain.Match;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository
		extends JpaRepository<Match, Integer> {
}
