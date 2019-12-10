package ru.restoran.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.restoran.menu.domain.User;
import ru.restoran.menu.repos.FoodRepo;

import java.util.HashMap;


@Controller
@RequestMapping("/")
public class MainController {
    private final FoodRepo foodRepo;

/*    @Value("${spring.profiles.active}")
    private String profile;*/

    public MainController(FoodRepo foodRepo) {
        this.foodRepo = foodRepo;
    }

    @GetMapping
    public String main(Model model, User user) {
        HashMap<Object, Object> data = new HashMap<>();

        user.setId("1");
        user.setName("Alex");

        if (user != null) {
            data.put("messages", foodRepo.findAll());
            data.put("profile", user);
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", true);

        return "index";
    }
}
