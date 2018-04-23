package com.mountainpier.platform.service;

import com.mountainpier.platform.domain.Match;
import com.mountainpier.platform.domain.Server;
import com.mountainpier.platform.repository.MatchRepository;
import com.mountainpier.platform.web.model.MatchRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class MatchServiceImpl implements MatchService {
	
	private final MatchRepository matchRepository;
	
	private final ServerServiceImpl serverService;
	
	@Autowired
	public MatchServiceImpl(MatchRepository matchRepository,
							@Lazy ServerServiceImpl serverService) {
		this.matchRepository = matchRepository;
		this.serverService = serverService;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Match> getMatches(final Integer page,
								  final Integer size) {
		return matchRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	@Transactional
	public Match createMatch(final Integer serverId, MatchRequest matchRequest) {
		Server server = this.serverService.getServerById(serverId);
		Match match = new Match()
			.setUserId(UUID.fromString(matchRequest.getUserId()))
			.setServer(server);
		return matchRepository.save(match);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Match getMatchById(Integer matchId) {
		return matchRepository.findById(matchId)
			.orElseThrow(() -> new EntityNotFoundException("Match '{" + matchId + "}' not found"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Match getMatchByUserId(UUID userId) {
		return matchRepository.getMatchByUserId(userId);
	}
	
	@Override
	@Transactional
	public Match updateMatchById(Integer matchId, MatchRequest matchRequest) {
		Match match = this.getMatchById(matchId);
		match.setUserId(matchRequest != null ? UUID.fromString(matchRequest.getUserId()) : match.getUserId());
		return matchRepository.save(match);
	}
	
	@Override
	@Transactional
	public void deleteMatchById(Integer matchId) {
		matchRepository.deleteById(matchId);
	}
	
	@Override
	@Transactional
	public void deleteMatchBeServerIdAndUserId(Integer serverId, UUID userId) {
		Server server = this.serverService.getServerById(serverId);
		matchRepository.deleteMatchByUserIdAndServer(userId, server);
	}
	
}
