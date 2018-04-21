package com.mountainpier.platform.service;

import com.mountainpier.platform.domain.Channel;
import com.mountainpier.platform.repository.ChannelRepository;
import com.mountainpier.platform.web.model.ChannelRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class ChannelServiceImpl implements ChannelService {
	
	private final ChannelRepository channelRepository;
	
	@Autowired
	public ChannelServiceImpl(ChannelRepository channelRepository) {
		this.channelRepository = channelRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Channel> getChannels(Integer page, Integer size) {
		return channelRepository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	@Transactional
	public Channel createChannel(ChannelRequest channelRequest) {
		Channel channel = new Channel()
			.setUsername(channelRequest.getUsername())
			.setEmail(channelRequest.getEmail())
			.setPassword(channelRequest.getPassword())
			.setOauthToken(channelRequest.getOauthToken())
			.setCreatorId(UUID.fromString(channelRequest.getCreator()))
			.setDateAdded(channelRequest.getDateAdded());
		return channelRepository.save(channel);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Channel getChannelById(Integer channelId) {
		return channelRepository.findById(channelId)
			.orElseThrow(() -> new EntityNotFoundException("Channel '{" + channelId + "}' not found"));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Channel getChannelByUsername(String username) {
		return channelRepository.getChannelByUsernameIgnoreCase(username);
	}

	@Override
	@Transactional(readOnly = true)
	public Channel getChannelByRegEmail(String regEmail) {
		return channelRepository.getChannelByEmailIgnoreCase(regEmail);
	}
	
	@Override
	@Transactional
	public Channel updateChannelById(Integer channelId, ChannelRequest channelRequest) {
		Channel channel = this.getChannelById(channelId);
		channel.setUsername(channelRequest.getUsername() != null ? channelRequest.getUsername() : channel.getUsername());
		channel.setEmail(channelRequest.getEmail() != null ? channelRequest.getEmail() : channel.getEmail());
		channel.setPassword(channelRequest.getPassword() != null ? channelRequest.getPassword() : channel.getPassword());
		channel.setOauthToken(channelRequest.getOauthToken() != null ? channelRequest.getOauthToken() : channel.getOauthToken());
		channel.setCreatorId(channelRequest.getCreator() != null ? UUID.fromString(channelRequest.getCreator()) : channel.getCreatorId());
		channel.setDateAdded(channelRequest.getDateAdded() != null ? channelRequest.getDateAdded() : channel.getDateAdded());
		return channelRepository.save(channel);
	}
	
	@Override
	@Transactional
	public void deleteChannelById(Integer channelId) {
		channelRepository.deleteById(channelId);
	}
	
}
