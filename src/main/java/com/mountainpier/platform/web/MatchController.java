package com.mountainpier.platform.web;

import com.mountainpier.platform.domain.Match;
import com.mountainpier.platform.service.MatchServiceImpl;
import com.mountainpier.platform.web.model.MatchRequest;
import com.mountainpier.platform.web.model.MatchResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(MatchController.matchBaseApi)
public class MatchController {
	
	static final String matchBaseApi = "/api/platform";
	
	private final MatchServiceImpl matchService;
	
	@Autowired
	public MatchController(MatchServiceImpl matchService) {
		this.matchService = matchService;
	}
	
	@RequestMapping(value = "/matches", method = RequestMethod.GET)
	Page<MatchResponse> getMatches(@RequestParam(value = "page", required = false) Integer page,
								   @RequestParam(value = "size", required = false) Integer size) {
		page = page != null ? page : 0;
		size = size != null ? size : 25;
		return matchService.getMatches(page, size)
			.map(MatchResponse::new);
	}
	
	@RequestMapping(value = "/matches", method = RequestMethod.POST)
	MatchResponse createMatch(@RequestBody @Valid MatchRequest matchRequest,
							  HttpServletResponse response) {
		Match match = matchService.createMatch(matchRequest);
		response.setHeader(HttpHeaders.LOCATION, matchBaseApi + "/matches/" + match.getId());
		return new MatchResponse(match);
	}
	@RequestMapping(value = "/matches/{matchId}", method = RequestMethod.GET)
	MatchResponse getMatchById(@PathVariable("matchId") final Integer matchId) {
		return new MatchResponse(matchService.getMatchById(matchId));
	}
	
	@RequestMapping(value = "/matches/{matchId}", method = RequestMethod.PATCH)
	MatchResponse updateMatchById(@PathVariable("matchId") final Integer matchId,
								  @RequestBody @Valid MatchRequest matchRequest) {
		return new MatchResponse(matchService.updateMatchById(matchId, matchRequest));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/matches/{matchId}", method = RequestMethod.DELETE)
	void deleteMatchById(@PathVariable("matchId") final Integer matchId) {
		matchService.deleteMatchById(matchId);
	}
	
}
