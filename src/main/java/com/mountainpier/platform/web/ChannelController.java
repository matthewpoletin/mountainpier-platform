package com.mountainpier.platform.web;

import com.mountainpier.platform.domain.Channel;
import com.mountainpier.platform.service.ChannelServiceImpl;
import com.mountainpier.platform.web.model.ChannelRequest;
import com.mountainpier.platform.web.model.ChannelResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(ChannelController.channelBaseApi)
public class ChannelController {

	static final String channelBaseApi = "/api/platform";

	private final ChannelServiceImpl channelService;
	
	@Autowired
	ChannelController(ChannelServiceImpl channelService) {
		this.channelService = channelService;
	}
	
	@RequestMapping(value = "/channels", method = RequestMethod.GET)
	public Page<ChannelResponse> getChannels(@RequestParam(value = "page", required = false) Integer page,
											 @RequestParam(value = "size", required = false) Integer size) {
		page = page != null ? page : 0;
		size = size != null ? size : 25;
		return channelService.getChannels(page, size)
			.map(ChannelResponse::new);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/channels", method = RequestMethod.POST)
	public ChannelResponse createChannel(@RequestBody @Valid ChannelRequest channelRequest,
										 HttpServletResponse response) {
		Channel channel = channelService.createChannel(channelRequest);
		response.addHeader(HttpHeaders.LOCATION, channelBaseApi + "/channels/" + channel.getId().toString());
		return new ChannelResponse(channel);
	}

	@RequestMapping(value = "/channels/{channelId}", method = RequestMethod.GET)
	public ChannelResponse getChannelById(@PathVariable("channelId") final Integer channelId) {
		return new ChannelResponse(channelService.getChannelById(channelId));
	}
	
//	@RequestMapping(value = "/channels/by", method = RequestMethod.GET)
//	public ChannelResponse getUserBy(@RequestParam(name = "email", required = false) final String email,
//									 HttpServletResponse response) {
//		Channel channel;
//		if (username != null && username.length() > 0) {
//			channel = channelService.getUserByUsername(username);
//		} else if (email != null && email.length() > 0) {
//			channel = channelService.getUserByRegEmail(email);
//		} else {
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			return null;
//		}
//
//		if (channel != null) {
//			return new ChannelResponse(channel);
//		} else {
//			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//			return null;
//		}
//	}
	
	@RequestMapping(value = "/channels/{channelId}", method = RequestMethod.PATCH)
	public ChannelResponse updateChannelById(@PathVariable("channelId") final Integer channelId,
											 @RequestBody @Valid ChannelRequest channelRequest) {
		return new ChannelResponse(channelService.updateChannelById(channelId, channelRequest));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/channels/{channelId}", method = RequestMethod.DELETE)
	public void deleteChannelById(@PathVariable("channelId") final Integer channelId) {
		channelService.deleteChannelById(channelId);
	}
	
}
