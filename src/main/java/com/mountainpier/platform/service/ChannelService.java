package com.mountainpier.platform.service;

import com.mountainpier.platform.domain.Channel;
import com.mountainpier.platform.web.model.ChannelRequest;

import org.springframework.data.domain.Page;

public interface ChannelService {
	Page<Channel> getChannels(Integer page, Integer size);
	Channel createChannel(ChannelRequest channelRequest);
	Channel getChannelById(Integer channelId);
	Channel getChannelByUsername(String username);
	Channel getChannelByRegEmail(String email);
	Channel updateChannelById(Integer channelId, ChannelRequest channelRequest);
	void deleteChannelById(Integer channelId);
}
