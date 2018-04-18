package com.mountainpier.platform.service;

import com.mountainpier.platform.domain.Server;
import com.mountainpier.platform.web.model.ServerRequest;

import org.springframework.data.domain.Page;

public interface ServerService {
	Page<Server> getServers(Integer page, Integer size);
	Server createServer(ServerRequest serverRequest);
	Server getServerById(Integer serverId);
	Server updateServerById(Integer serverId, ServerRequest serverRequest);
	void deleteServerById(Integer serverId);
}
