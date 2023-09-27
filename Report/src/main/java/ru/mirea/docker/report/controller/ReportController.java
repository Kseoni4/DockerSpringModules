package ru.mirea.docker.report.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ReportController {

    private static final String KEY = "UserData";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @ResponseBody
    @GetMapping("/report")
    public String report(){
        System.out.println("report request");
        Map<Object, Object> map = hashOperations.entries(KEY);
        return map.entrySet().toString();
    }

}
