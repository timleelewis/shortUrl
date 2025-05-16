package com.example.C.Users.lewis.Documents.shorturl.controller;

import com.example.C.Users.lewis.Documents.shorturl.service.shortUrlService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.concurrent.Semaphore;


@RestController
public class shortUrlController {
    private final shortUrlService service;
    private final Semaphore semaphore;

    public shortUrlController(shortUrlService service, Semaphore semaphore) {
        this.service = service;
        this.semaphore = semaphore;
    }

    @PostMapping("/encode")
    public ResponseEntity<?> encode(@RequestBody Map<String, String> body) {
        if (!semaphore.tryAcquire()) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(Map.of("error", "Too many concurrent requests"));
        }
        try {
            String originalUrl = body.get("url");
            String shortUrl = service.encode(originalUrl);
            return ResponseEntity.ok(Map.of("shortUrl", shortUrl));
        } finally {
            semaphore.release();
        }
    }

    @PostMapping("/decode")
    public ResponseEntity<?> decode(@RequestBody Map<String, String> body) {
        if (!semaphore.tryAcquire()) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(Map.of("error", "Too many concurrent requests"));
        }
        try {
            String shortUrl = body.get("shortUrl");
            String originalUrl = service.decode(shortUrl);
            return ResponseEntity.ok(Map.of("originalUrl", originalUrl));
        } finally {
            semaphore.release();
        }
    }
    
    
}
