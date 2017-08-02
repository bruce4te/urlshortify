package info.icould.spring.urlshortify.domain;

import org.springframework.data.annotation.Id;

public class ShortUrl {

    @Id
    private String id;

    private String url;

    private String shortUrl;

    private long shortifyCount;

    private long redirectCount;

    public ShortUrl(){
        this.setShortifyCount(0L);
        this.setRedirectCount(0L);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public long getShortifyCount() {
        return shortifyCount;
    }

    public void setShortifyCount(long shortifyCount) {
        this.shortifyCount = shortifyCount;
    }

    public long getRedirectCount() {
        return redirectCount;
    }

    public void setRedirectCount(long redirectCount) {
        this.redirectCount = redirectCount;
    }
}
