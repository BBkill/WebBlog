package ltw.nhom6.blog.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

@Configuration
@EnableCaching
@Component
public class CacheConfiguration implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

    private static final int OTP_LIFE_TIME = 3;
    private static final int PASSWORD_RESET_LIFE_TIME = 5;

    @Bean("otpCache")
    public LoadingCache<String, String> createOtpCacheBean() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(OTP_LIFE_TIME, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return key.toUpperCase();
                    }
                });
    }

    @Bean("passwordResetCache")
    public LoadingCache<String, String> createPasswordResetCacheBean() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(PASSWORD_RESET_LIFE_TIME, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return key.toUpperCase();
                    }
                });
    }

    @Bean("cache")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("users", "author", "all", "search", "home");
    }

    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
        cacheManager.setCacheNames(asList("users", "author", "all", "search", "home"));
    }
}
