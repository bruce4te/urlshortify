package info.icould.spring.urlshortify.repository;

import info.icould.spring.urlshortify.domain.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class ShortUrlRepositoryImpl implements ShortUrlRepositoryCustom {

    private static final String BASE_KEY = "aaa";
    private static final int MAX_KEY_POS = 2;

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public ShortUrl save(String url) {
        Long keyCount = mongoOperation.getCollection("shortUrl").count();
        ShortUrl shortUrl = new ShortUrl();
        String _id = createKey(keyCount);
        shortUrl.setId(_id);
        shortUrl.setUrl(url);
        shortUrl.setShortUrl(_id);
        mongoOperation.save(shortUrl);
        return shortUrl;
    }

    @Override
    public ShortUrl updateShortifyCount(ShortUrl shortUrl) {
        shortUrl.setShortifyCount(shortUrl.getShortifyCount() + 1);
        mongoOperation.save(shortUrl);
        return shortUrl;
    }

    @Override
    public ShortUrl updateRedirectCount(ShortUrl shortUrl) {
        shortUrl.setRedirectCount(shortUrl.getRedirectCount() + 1);
        mongoOperation.save(shortUrl);
        return shortUrl;
    }

    public String createKey(Long count) {
        /* 0 => aaa
        *  1 => aab
        *  ...
        *  25 => aaz
        *  26 => aba
        *  27 => abb
        */
        if (count == 0) {
            return BASE_KEY;
        } else {
            int keyPos = 100;
            if (count < 26) {
                int posValue = count.intValue() % 26;
                int[] values = {posValue};
                return createKeyFromCountArray(values);
            } else if (count >= 26 && count < 676) {
                int lowerValue = count.intValue() % 26;
                int posValue = (count.intValue() - lowerValue) / 26;
                int[] values = {lowerValue, posValue};
                return createKeyFromCountArray(values);
            } else if (count >= 676){
                int lowestValue = count.intValue() % 26;
                int lowerValue = ((count.intValue() % 676) - lowestValue) / 26;
                int posValue = (count.intValue() - (lowerValue*26) - lowestValue) / 676;
                int[] values = {lowestValue, lowerValue, posValue};
                return createKeyFromCountArray(values);
            }
            return BASE_KEY;
        }

    }

    private String createKeyFromCountArray(int[] counts){
        StringBuilder key = new StringBuilder(BASE_KEY);
        for(int index=0;index<counts.length;index++){
            int keyPos = MAX_KEY_POS - index;
            int value = BASE_KEY.charAt(keyPos);
            value+=counts[index];
            char c = (char) value;
            key.setCharAt(keyPos, c);
        }
        return key.toString();
    }
}
