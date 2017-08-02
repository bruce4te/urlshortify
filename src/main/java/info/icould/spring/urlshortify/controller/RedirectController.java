package info.icould.spring.urlshortify.controller;

import info.icould.spring.urlshortify.domain.ShortUrl;
import info.icould.spring.urlshortify.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RedirectController {

    @Autowired
    ShortUrlRepository repository;

    @RequestMapping(value = "/{shortUrl}", method = GET)
    public void redirectUrl(@PathVariable("shortUrl") String shortUrl, HttpServletResponse response) throws IOException {
        if(shortUrl.length()==3){
            ShortUrl url = repository.findByShortUrl(shortUrl);
            if(null!=url) {
                repository.updateRedirectCount(url);
                System.out.println("Redirecting localhost:8080/" + shortUrl + " to " + url.getUrl());
                response.sendRedirect(url.getUrl());
            }
        }
    }


    @RequestMapping(value = "/shortifyUrl", method = POST)
    public RedirectView saveShortUrl(@RequestParam String url, HttpServletResponse response, RedirectAttributes attributes) throws IOException {
        ShortUrl shortUrl = repository.findByUrl(url);
        if(null==shortUrl){
            System.out.println("Saving url: " + url);
            shortUrl = repository.save(url);
            attributes.addAttribute("shortUrl","http://localhost:8080/" + shortUrl.getShortUrl());
        } else {
            repository.updateShortifyCount(shortUrl);
        }
        return new RedirectView("/web/home");
    }
}
