package com.example.C.Users.lewis.Documents.shorturl.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class shortUrlService {
    private final String domain = "http://lil.tim/";
    private final Map<String, String> codeToUrl = new ConcurrentHashMap<>();
    private final Map<String, String> urlToCode = new ConcurrentHashMap<>();

    public String encode(String url) {
        return urlToCode.computeIfAbsent(url, k -> {
            String code = UUID.randomUUID().toString().substring(0, 6);
            codeToUrl.put(code, k);
            return domain + code;
        });
    }

    public String decode(String shortUrl) {
        String code = shortUrl.replace(domain, "");
        return codeToUrl.getOrDefault(code, "URL not found");
    }
    
}
