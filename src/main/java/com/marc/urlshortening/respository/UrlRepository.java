package com.marc.urlshortening.respository;

import com.google.common.hash.Hashing;
import com.marc.urlshortening.model.ShortUrl;
import org.springframework.stereotype.Repository;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class UrlRepository {
    private Map<Integer, ShortUrl> urls = new HashMap<Integer, ShortUrl>();

    /**
     * Get ShortUrl from repository
     * @param id
     * @return ShortUrl
     */
    public ShortUrl getById(int id){
        if(urls.containsKey(id))
            return urls.get(id);
        else
            throw new NoSuchElementException();
    }

    /**
     * Get ShortUrl from repository by short string
     * @param url
     * @return ShortUrl
     */
    public ShortUrl getRedirect(String url){
        Collection<ShortUrl> values = urls.values();
        ShortUrl exist = values.stream().filter(s -> s.getShortUrl().equals(url)).findFirst().orElse(null);
        if(exist != null)
            return exist;
        else
            throw new NoSuchElementException();
    }

    /**
     * Insert new ShortUrl in repository
     * @param url
     * @param shortUrl
     * @return ShortUrl
     */
    public ShortUrl insertUrl(String url, String shortUrl){
        Collection<ShortUrl> values = urls.values();
        ShortUrl exist = values.stream().filter(s -> s.getUrl().equals(url)).findFirst().orElse(null);
        if( exist == null) {
            ShortUrl newUrls = new ShortUrl();
            newUrls.setUrl(url);
            newUrls.setShortUrl(shortUrl);
            newUrls.setId(urls.size() + 1);
            urls.put(newUrls.getId(), newUrls);
            return newUrls;
        }
        else
        {
            return getById(exist.getId());
        }
    }

    /**
     * Edit ShortUrl in repository
     * @param id
     * @param url
     * @param shortUrl
     * @return ShortUrl
     */
    public ShortUrl editUrl(int id, String url, String shortUrl){
        if(urls.containsKey(id)) {
            ShortUrl newUrls = new ShortUrl();
            newUrls.setUrl(url);
            newUrls.setShortUrl(shortUrl);
            newUrls.setId(id);
            urls.remove(id);
            urls.put(id, newUrls);
            return newUrls;
        }
        else
            throw new NoSuchElementException();
    }

    /**
     * Delete ShortUrl in repository
     * @param id
     * @return ShortUrl
     */
    public boolean delete(int id){
        if(urls.containsKey(id)) {
            urls.remove(id);
            return true;
        }
        else
            return false;
    }
}
