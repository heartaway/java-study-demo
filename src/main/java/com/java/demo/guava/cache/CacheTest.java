package com.java.demo.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author: xinyuan.ymm
 * @create: 2017-03-17 上午12:59
 */
public class CacheTest {

    @Test
    public void testCache() {
        CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(10, TimeUnit.MINUTES).removalListener(null)
            .build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) throws Exception {
                    return null;
                }
            });
    }
}
