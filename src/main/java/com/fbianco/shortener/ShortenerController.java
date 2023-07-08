package com.fbianco.shortener;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenerController {

	@PostMapping( value = "/shorten")
	public String shortenUrl(@RequestBody String url) {
		return url;
	}

}