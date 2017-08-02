package info.icould.spring.urlshortify.repository;

import info.icould.spring.urlshortify.domain.ShortUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "urls", path = "urls")
public interface ShortUrlRepository extends MongoRepository<ShortUrl, String>, ShortUrlRepositoryCustom {
    ShortUrl findByUrl(String url);
    ShortUrl findByShortUrl(String shortUrl);
}
