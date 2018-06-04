package com.mountainpier.platform.service;

import com.mountainpier.platform.domain.Channel;
import com.mountainpier.platform.domain.Match;
import com.mountainpier.platform.domain.Server;
import com.mountainpier.platform.repository.ServerRepository;
import com.mountainpier.platform.web.model.MatchRequest;
import com.mountainpier.platform.web.model.ServerRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ServerServiceImpl implements ServerService {
	
	private final ServerRepository serverRepository;
	private final ChannelService channelService;
	private final MatchService matchService;
	
	@Autowired
	public ServerServiceImpl(ServerRepository serverRepository,
							 ChannelService channelService,
							 MatchService matchService) {
		this.serverRepository = serverRepository;
		this.channelService = channelService;
		this.matchService = matchService;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Server> getServers(final Integer page, Integer size) {
		return serverRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	@Transactional
	public Server createServer(final ServerRequest serverRequest) {
		Channel channel = this.channelService.getChannelById(serverRequest.getChannelId());
		Server server = new Server()
			.setName(serverRequest.getName())
			.setChannel(channel)
			.setGameId(UUID.fromString(serverRequest.getGameId()));
		return serverRepository.save(server);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Server getServerById(final Integer serverId) {
		return serverRepository.findById(serverId)
			.orElseThrow(() -> new EntityNotFoundException("Server '{" + serverId + "}' not found"));
	}
	
	@Override
	@Transactional
	public Server updateServerById(final Integer serverId, ServerRequest serverRequest) {
		Server server = this.getServerById(serverId);
		server.setGameId(serverRequest.getGameId() != null ? UUID.fromString(serverRequest.getGameId()) : server.getGameId());
		server.setName(serverRequest.getName() != null ? serverRequest.getName() : server.getName());
		if (serverRequest.getChannelId() != null) {
			Channel channel = this.channelService.getChannelById(serverRequest.getChannelId());
			server.setChannel(channel);
		}
		return serverRepository.save(server);
	}
	
	@Override
	@Transactional
	public void deleteServerById(final Integer serverId) {
		serverRepository.deleteById(serverId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Channel getChannelOfServerById(final Integer serverId) {
		return this.getServerById(serverId).getChannel();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<UUID> getUsersOfServerById(final Integer serverId,
										   final Integer page,
										   final Integer size) {
		Server server = this.getServerById(serverId);
		List<Match> matches = server.getMatches();
		List<UUID> usersList = new ArrayList<>();
		matches.forEach(match -> usersList.add(match.getUserId()));
		return new PageImpl<>(usersList, PageRequest.of(page, size), size);
	}
	
	@Override
	@Transactional
	public Match addUserToServerById(final Integer serverId, MatchRequest matchRequest) {
		return matchService.createMatch(serverId, matchRequest);
	}
	
	@Override
	@Transactional
	public void removeUserFromServerById(final Integer serverId, final UUID userId) {
		matchService.deleteMatchBeServerIdAndUserId(serverId, userId);
	}
	
}
