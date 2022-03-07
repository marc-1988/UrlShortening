package com.marc.urlshortening.controller;

import com.marc.urlshortening.model.ShortUrl;
import com.marc.urlshortening.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Rest controller with CRUD operation to get short url
 */
@RestController
@RequestMapping(value = "/short_url")
public class UrlShorteningController {

    @Autowired
    private UrlService service;

    /**
     * Get ShortUrl by id
     * @param id
     * @return ShortUrl
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ShortUrl> findById(@PathVariable("id") final int id){
        try{
        return new ResponseEntity<ShortUrl>(service.getUrl(id), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Redirect from short url to the original url
     * @param response
     * @param url
     */
    @GetMapping(value = "/s/{url}")
    public void redirect(HttpServletResponse response, @PathVariable("url") String url){
        try {
            response.sendRedirect(service.getRedirect(url).getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create new ShortUrl for a given url
     * @param url
     * @return ShortUrl
     */
    @PostMapping
    public ResponseEntity<ShortUrl> create(@RequestBody final String url){
        try {
            return new ResponseEntity<ShortUrl>(service.insertUrl(url), HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update ShortUrl
     * @param id
     * @param url
     * @return ShortUrl
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ShortUrl> update(@PathVariable("id") final int id, @RequestBody final String url) {

        try{
            return new ResponseEntity<ShortUrl>(service.editUrl(id, url), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete created ShortUrl by id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity delete(@PathVariable("id") final int id) {
        if(service.deleteUrl(id))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
