package com.fbianco.shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class ShortenerController {

	@Autowired
	ShortUrlService shortUrlService;

	@PostMapping(value = "/shorten")
	public String shortenUrl(@RequestBody String url) {
		ShortUrl shortUrl = shortUrlService.createShortUrl(url);
		return shortUrl.encode();
	}

	@GetMapping("/{shortUrl}")
	public @ResponseBody RedirectView getAttr(@PathVariable(value="shortUrl") String shortUrl) {
		String longUrl = shortUrlService.getLongUrl(shortUrl);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(longUrl);
		return redirectView;
	}
}