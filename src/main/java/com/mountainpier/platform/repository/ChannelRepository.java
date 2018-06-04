package com.mountainpier.platform.repository;

import com.mountainpier.platform.domain.Channel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository
		extends JpaRepository<Channel, Integer> {
	
	Channel getChannelByUsernameIgnoreCase(String username);
	
	Channel getChannelByEmailIgnoreCase(String email);
}
