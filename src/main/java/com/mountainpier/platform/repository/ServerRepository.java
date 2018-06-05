package com.mountainpier.platform.repository;

import com.mountainpier.platform.domain.Server;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ServerRepository
		extends JpaRepository<Server, Integer> {
	
	Page<Server> getServersByGameId(UUID gameId, Pageable pageable);
	
}
