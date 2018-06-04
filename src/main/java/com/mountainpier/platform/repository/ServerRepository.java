package com.mountainpier.platform.repository;

import com.mountainpier.platform.domain.Server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository
		extends JpaRepository<Server, Integer> {
}
