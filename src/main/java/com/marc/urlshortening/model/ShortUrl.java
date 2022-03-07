package com.marc.urlshortening.model;

import lombok.Getter;
import lombok.Setter;

/**
 * ShrotUrl model
 */
@Setter
@Getter
public class ShortUrl {
    private int id;
    private String url;
    private String shortUrl;
}
