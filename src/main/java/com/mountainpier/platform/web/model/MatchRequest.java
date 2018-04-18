package com.mountainpier.platform.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MatchRequest {

	public String userId;

}
