package com.cqsd.socket.core.utils;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Desc redis 工具类
 **/
public class BarrageCacheUtils {

    private final static Cache<String, Map<String, Object>> HASH_CACHE;
    private final static Cache<String, List<String>> LIST_CACHE;
    private final static Cache<String, AtomicInteger> INCR_CACHE;
    private final static Cache<String, String> STRING_CACHE;

    static {
        HASH_CACHE = Caffeine.newBuilder()
                // 访问完半小时后删除
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
        LIST_CACHE = Caffeine.newBuilder()
                // 访问完半小时后删除
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
        INCR_CACHE = Caffeine.newBuilder()
                // 访问完半小时后删除
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
        STRING_CACHE = Caffeine.newBuilder()
                // 访问完半小时后删除
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
    }

    /**
     * 存放数据集合
     *
     * @param key   数据key
     * @param value 数据值
     */
    public static void listPush(String key, String value) {
        List<String> list = getListIfPresent(key, LIST_CACHE.getIfPresent(key));
        list.add(value);
    }

    private static List<String> getListIfPresent(String key, List<String> list) {
        if (list == null) {
            synchronized (LIST_CACHE) {
                if (list == null) {
                    list = new ArrayList<>();
                    LIST_CACHE.put(key, list);
                }
            }
        }
        return list;
    }

    /**
     * 获取所有的集合数据
     *
     * @param key 指定的 RedisKey
     * @returnK 数据集合
     */
    public static List<String> listGetAll(String key) {
        return getListIfPresent(key, LIST_CACHE.getIfPresent(key));
    }

    private static Map<String, Object> geHashIfPresent(String key, Map<String, Object> hash) {
        if (hash == null) {
            synchronized (HASH_CACHE) {
                if (hash == null) {
                    hash = new ConcurrentHashMap<>();
                    HASH_CACHE.put(key, hash);
                }
            }
        }
        return hash;
    }

    /**
     * 存放hash数据
     *
     * @param key   key
     * @param hKey  hKey
     * @param value value
     */
    public static void hashPut(String key, String hKey, String value) {
        Map<String, Object> hash = geHashIfPresent(key, HASH_CACHE.getIfPresent(key));
        hash.put(hKey, value);
    }

    public static String hashGet(String key, String hKey) {
        Map<String, Object> hash = geHashIfPresent(key, HASH_CACHE.getIfPresent(key));

        return (String) hash.get(hKey);
    }

    private static AtomicInteger geIncrIfPresent(String key, AtomicInteger atomicInteger) {
        if (atomicInteger == null) {
            synchronized (INCR_CACHE) {
                if (atomicInteger == null) {
                    atomicInteger = new AtomicInteger();
                    INCR_CACHE.put(key, atomicInteger);
                }
            }
        }
        return atomicInteger;
    }

    /**
     * 给指定的key的数值增加1
     *
     * @param key key
     */
    public static void increment(String key) {
        AtomicInteger atomic = geIncrIfPresent(key, INCR_CACHE.getIfPresent(key));
        atomic.incrementAndGet();
    }

    /**
     * 给指定的key的数值减少1
     *
     * @param key key
     */
    public static void decrement(String key) {
        AtomicInteger atomic = geIncrIfPresent(key, INCR_CACHE.getIfPresent(key));
        atomic.decrementAndGet();
    }

    /**
     * 根据key获取value值
     *
     * @param key key
     * @return value值
     */
    public static String get(String key) {
        return geIncrIfPresent(key, INCR_CACHE.getIfPresent(key)).toString();
    }


    /**
     * 根据key设置value值
     *
     * @param key   key
     * @param value value
     */
    public static void set(String key, String value) {
        AtomicInteger atomic = geIncrIfPresent(key, INCR_CACHE.getIfPresent(key));
        atomic.set(Integer.parseInt(value));
    }

}
