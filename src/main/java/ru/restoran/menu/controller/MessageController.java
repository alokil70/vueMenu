package ru.restoran.menu.controller;

import org.springframework.web.bind.annotation.*;
import ru.restoran.menu.exeptions.NotFoundExeption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    private int counter = 4;

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "first message");
        }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getById(@PathVariable String id){
        return messages.stream().filter(message -> message.get("id").equals(id))
                .findFirst().orElseThrow(NotFoundExeption::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message){
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return (Map<String, String>) messages;
    }
}
