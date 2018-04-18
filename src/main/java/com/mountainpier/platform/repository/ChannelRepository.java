package com.mountainpier.platform.repository;

import com.mountainpier.platform.domain.Channel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository
		extends JpaRepository<Channel, Integer> {
}
