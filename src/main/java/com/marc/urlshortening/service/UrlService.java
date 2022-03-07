package com.marc.urlshortening.service;

import com.google.common.hash.Hashing;
import com.marc.urlshortening.model.ShortUrl;
import com.marc.urlshortening.respository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

/**
 * Service to manage the ShortUrl
 */
@Service
public class UrlService {
    @Autowired
    UrlRepository repository;
    private String shortUrlPrefix = "http://locahost:8080/short_url/s/";
    public UrlService(){

    }

    /**
     * Get ShortUrl by id from repository
     * @param id
     * @return ShortUrl
     */
    public ShortUrl getUrl(int id){
        return repository.getById(id);
    }

    /**
     * Get ShortUrl by short string
     * @param url
     * @return ShortUrl
     */
    public ShortUrl getRedirect(String url){
        return repository.getRedirect(shortUrlPrefix + url);
    }

    /**
     * Create a new ShortUrl
     * @param url
     * @return ShortUrl
     */
    public ShortUrl insertUrl(String url){
        String key = Hashing.murmur3_32_fixed().hashString(url, Charset.defaultCharset()).toString();
        return repository.insertUrl(url, shortUrlPrefix + key);
    }

    /**
     * Update ShortUrl
     * @param id
     * @param url
     * @return ShortUrl
     */
    public ShortUrl editUrl(int id, String url){
        String key = Hashing.murmur3_32_fixed().hashString(url, Charset.defaultCharset()).toString();
        return repository.editUrl(id, url, shortUrlPrefix + key);
    }

    /**
     * Delete ShortUrl
     * @param id
     * @return true/false
     */
    public boolean deleteUrl(int id){
        return repository.delete(id);
    }
}
