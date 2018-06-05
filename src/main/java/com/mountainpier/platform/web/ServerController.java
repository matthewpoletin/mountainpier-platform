package com.mountainpier.platform.web;

import com.mountainpier.platform.domain.Server;
import com.mountainpier.platform.service.ServerServiceImpl;
import com.mountainpier.platform.web.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(ServerController.serverBaseApi)
public class ServerController {

	static final String serverBaseApi = "/api/platform";

	private final ServerServiceImpl serverService;

	@Autowired
	public ServerController(ServerServiceImpl serverService) {
		this.serverService = serverService;
	}

	@RequestMapping(value = "/servers", method = RequestMethod.GET)
	public Page<ServerResponse> getServers(@RequestParam(value = "page", defaultValue = "0") final Integer page,
										   @RequestParam(value = "size", defaultValue = "25") final Integer size) {
		return serverService.getServers(page, size)
			.map(ServerResponse::new);
	}

	@RequestMapping(value = "/servers", method = RequestMethod.POST)
	public ServerResponse createServer(@RequestBody @Valid ServerRequest serverRequest,
									   HttpServletResponse response) {
		Server server = serverService.createServer(serverRequest);
		response.addHeader(HttpHeaders.LOCATION, serverBaseApi + "/servers/" + server.getId());
		return new ServerResponse(server);
	}

	@RequestMapping(value = "/servers/{serverId}", method = RequestMethod.GET)
	public ServerResponse getServerById(@PathVariable("serverId") final Integer serverId) {
		return new ServerResponse(serverService.getServerById(serverId));
	}

	@RequestMapping(value = "/servers/{serverId}", method = RequestMethod.PATCH)
	public ServerResponse updateServerById(@PathVariable("serverId") final Integer serverId,
										   @RequestBody @Valid ServerRequest serverRequest) {
	 return new ServerResponse(serverService.updateServerById(serverId, serverRequest));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/servers/{serverId}", method = RequestMethod.DELETE)
	public void deleteServerById(@PathVariable("serverId") final Integer serverId) {
		serverService.deleteServerById(serverId);
	}

	@RequestMapping(value = "/servers/{serverId}/channel", method = RequestMethod.GET)
	public ChannelResponse getChannelOfServerById(@PathVariable("serverId") final Integer serverId) {
		return new ChannelResponse(serverService.getChannelOfServerById(serverId));
	}
	
	@RequestMapping(value = "/servers/{serverId}/users", method = RequestMethod.GET)
	public Page<UUID> getUsersOfServerById(@PathVariable("serverId") final Integer serverId,
										   @RequestParam(value = "page", required = false) Integer page,
										   @RequestParam(value = "size", required = false) Integer size) {
		page = page != null ? page : 0;
		size = size != null ? size : 25;
		return serverService.getUsersOfServerById(serverId, page, size);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/servers/{serverId}/users/{userId}", method = RequestMethod.POST)
	public MatchResponse addUserToServerById(@PathVariable("serverId") final Integer serverId,
											 @PathVariable("userId") final UUID userId,
											 @RequestBody @Valid MatchRequest matchRequest) {
		if (userId != null) matchRequest.setUserId(userId.toString());
		return new MatchResponse(serverService.addUserToServerById(serverId, matchRequest));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/servers/{serverId}/users/{userId}", method = RequestMethod.DELETE)
	public void removeUserFromServerById(@PathVariable("serverId") final Integer serverId,
										 @PathVariable("userId") final UUID userId) {
		serverService.removeUserFromServerById(serverId, userId);
	}

}
