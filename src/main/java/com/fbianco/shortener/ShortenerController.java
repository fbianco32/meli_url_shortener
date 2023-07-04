package com.fbianco.shortener;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenerController {

	@GetMapping("/")
	public String index() {
		return "Hello world!";
	}

}