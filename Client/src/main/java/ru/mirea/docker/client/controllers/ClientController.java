package ru.mirea.docker.client.controllers;

import io.lettuce.core.RedisException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final String htmlCode =
                """
                <html>
                <head>
                    <title>TEST REDIS</title>
                </head>
                <body>
                <h1>TEST REDIS</h1>
                <form action="/create" method="post">
                    <p><label>
                        Name
                        <input type="text" name="name"/>
                    </label>
                    </p>
                    <p>
                        <label>
                        Age
                        <input type="number" name="age"/>
                    </label>
                    </p>
                    <button type="submit"> SUBMIT!</button>
                </form>
                </body>
                </html>
            """;

    private static final String KEY = "UserData";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @ResponseBody
    @GetMapping("/")
    public String index(){
        System.out.println("Get request");
        return htmlCode;
    }

    @ResponseBody
    @PostMapping("/create")
    public String create(String name, int age) {
        System.out.println("Get create request");
        try {
            hashOperations.put(KEY, name, age);
        } catch (RedisException e){
            System.out.println(e.getMessage());
        }
        return "OK";
    }
}
