package com.mountainpier.platform.web;

import com.mountainpier.platform.domain.Server;
import com.mountainpier.platform.service.ServerServiceImpl;
import com.mountainpier.platform.web.model.ServerRequest;
import com.mountainpier.platform.web.model.ServerResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
	public Page<ServerResponse> getServers(@RequestParam(value = "page", required = false) Integer page,
										   @RequestParam(value = "size", required = false) Integer size) {
		page = page != null ? page : 0;
		size = size != null ? size : 25;
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
	public ServerResponse updateServerById(@PathVariable("serverId") Integer serverId,
										   @RequestBody @Valid ServerRequest serverRequest) {
	 return new ServerResponse(serverService.updateServerById(serverId, serverRequest));
	}

	@RequestMapping(value = "/servers/{serverId}", method = RequestMethod.DELETE)
	public void deleteServerById(@PathVariable("serverId") Integer serverId) {
		serverService.deleteServerById(serverId);
	}



}
