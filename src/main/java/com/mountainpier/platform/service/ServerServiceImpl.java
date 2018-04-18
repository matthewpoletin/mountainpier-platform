package com.mountainpier.platform.service;

import com.mountainpier.platform.domain.Server;
import com.mountainpier.platform.repository.ServerRepository;
import com.mountainpier.platform.web.model.ServerRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class ServerServiceImpl implements ServerService {
	
	private final ServerRepository serverRepository;
	
	@Autowired
	public ServerServiceImpl(ServerRepository serverRepository) {
		this.serverRepository = serverRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Server> getServers(Integer page, Integer size) {
		return serverRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	@Transactional
	public Server createServer(ServerRequest serverRequest) {
		Server server = new Server()
			.setName(serverRequest.getName())
			.setGameId(UUID.fromString(serverRequest.getGameId()));
		return serverRepository.save(server);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Server getServerById(Integer serverId) {
		return serverRepository.findById(serverId)
			.orElseThrow(() -> new EntityNotFoundException("Server '{" + serverId + "}' not found"));
	}
	
	@Override
	@Transactional
	public Server updateServerById(Integer serverId, ServerRequest serverRequest) {
		Server server = this.getServerById(serverId);
		server.setGameId(serverRequest.getGameId() != null ? UUID.fromString(serverRequest.getGameId()) : server.getGameId());
		server.setName(serverRequest.getName() != null ? serverRequest.getName() : server.getName());
		return serverRepository.save(server);
	}
	
	@Override
	@Transactional
	public void deleteServerById(Integer serverId) {
		serverRepository.deleteById(serverId);
	}
	
}
