package info.icould.spring.urlshortify.repository;

import info.icould.spring.urlshortify.domain.ShortUrl;

public interface ShortUrlRepositoryCustom {

    ShortUrl save(String shortUrl);
    ShortUrl updateShortifyCount(ShortUrl shortUrl);
    ShortUrl updateRedirectCount(ShortUrl shortUrl);

}
