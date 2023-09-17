package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Cache {
    private Map<String, Map<String, Object>> caches = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(Cache.class.getName());


    public Cache() throws IOException {
        FileHandler fileHandler = new FileHandler("cache_log.txt");
        LOGGER.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }

    public boolean put(String cacheName, String key, Object value) {
        if (!caches.containsKey(cacheName)) {
            caches.put(cacheName, new HashMap<>());
        }
        Map<String, Object> cache = caches.get(cacheName);
        cache.put(key, value);
        LOGGER.info("Додано об'єкт '" + key + "' до кешу '" + cache + "'");
        return true;
    }

    public Object get(String cacheName, String key) {
        if (caches.containsKey(cacheName)) {
            Map<String, Object> cache = caches.get(cacheName);
            if (cache.containsKey(key)) {
                Object value = cache.get(key);
                LOGGER.info("Зчитано об'єкт '" + key + "' з кешу '" + cacheName + "'");
                return value;
            }
        }
        LOGGER.info("Об'єкт '" + key + "' не знайдено в кеші '" + cacheName + "'");
        return null;
    }

    public void clear() {
        caches.clear();
        LOGGER.info("Всі кеші було очищено");
    }

    public void clear(String cacheName) {
        if (caches.containsKey(cacheName)) {
            caches.remove(cacheName);
            LOGGER.info("Кеш '" + cacheName + "' було очищено");
        }

    }

    public boolean isCacheExist(String cacheName) {
        boolean exists = caches.containsKey(cacheName);
        if (exists) {
            LOGGER.info("Кеш '" + cacheName + "' існує");
        } else {
            LOGGER.info("Кеш '" + cacheName + "' не існує");
        }
        return exists;
    }

}



