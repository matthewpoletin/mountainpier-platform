package com.mountainpier.platform.service;

import com.mountainpier.platform.domain.Channel;
import com.mountainpier.platform.domain.Match;
import com.mountainpier.platform.domain.Server;
import com.mountainpier.platform.web.model.MatchRequest;
import com.mountainpier.platform.web.model.ServerRequest;

import org.springframework.data.domain.Page;
import java.util.UUID;

public interface ServerService {
	Page<Server> getServers(Integer page, Integer size);
	Server createServer(ServerRequest serverRequest);
	Server getServerById(Integer serverId);
	Server updateServerById(Integer serverId, ServerRequest serverRequest);
	void deleteServerById(Integer serverId);
	
	Channel getChannelOfServerById(Integer serverId);
	
	Page<UUID> getUsersOfServerById(final Integer serverId, Integer page, Integer size);
	Match addUserToServerById(Integer serverId, MatchRequest matchRequest);
	
	void removeUserFromServerById(Integer serverId, UUID userId);
	
	Page<Server> getServersOfGame(UUID gameId, Integer page, Integer size);
}
