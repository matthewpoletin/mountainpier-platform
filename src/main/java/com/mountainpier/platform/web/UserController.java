package com.mountainpier.platform.web;

import com.mountainpier.platform.service.MatchServiceImpl;
import com.mountainpier.platform.web.model.ServerResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping(UserController.userBaseUrl)
public class UserController {
	
	static final String userBaseUrl = "/api/platform/";
	
	private final MatchServiceImpl matchService;
	
	@Autowired
	public UserController(MatchServiceImpl matchService) {
		this.matchService = matchService;
	}
	
	@RequestMapping(value = "/users/{userId}/servers", method = RequestMethod.GET)
	ServerResponse getServerOfUserById(@PathVariable("userId") final UUID userId) {
		return new ServerResponse(matchService.getMatchByUserId(userId).getServer());
	}
	
}
