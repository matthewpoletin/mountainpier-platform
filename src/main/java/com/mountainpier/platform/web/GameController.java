package com.mountainpier.platform.web;

import com.mountainpier.platform.service.ServerService;
import com.mountainpier.platform.web.model.ServerResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(GameController.gameBaseURI)
public class GameController {
	
	static final String gameBaseURI = "/api/platform";
	
	private ServerService serverService;
	
	@Autowired
	public GameController(ServerService serverService) {
		this.serverService = serverService;
	}
	
	@RequestMapping(value = "/games/{gameId}/servers", method = RequestMethod.GET)
	Page<ServerResponse> getServersOfGame(@PathVariable("gameId") final UUID gameId,
										  @RequestParam(name = "page", defaultValue = "0") final Integer page,
										  @RequestParam(name = "page", defaultValue = "25") final Integer size) {
		return this.serverService.getServersOfGame(gameId, page, size)
			.map(ServerResponse::new);
	}
	
}
