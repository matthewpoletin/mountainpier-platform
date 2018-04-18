package com.mountainpier.platform.repository;

import com.mountainpier.platform.domain.Server;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository
		extends JpaRepository<Server, Integer> {
}
