package ru.restoran.menu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import ru.restoran.menu.domain.Food;
import ru.restoran.menu.domain.Views;
import ru.restoran.menu.exeptions.NotFoundExeption;
import ru.restoran.menu.repos.FoodRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    private final FoodRepo foodRepo;

    public MessageController(FoodRepo foodRepo) {
        this.foodRepo = foodRepo;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Food> list() {
        return foodRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Food getOne(@PathVariable("id") Food food) {
        return food;
    }

    @PostMapping
    public Food create(@RequestBody Food food) {
        food.setCreationDate(LocalDateTime.now());
        return foodRepo.save(food);
    }

    @PutMapping("{id}")
    public Food update(
            @PathVariable("id") Food foodFromDb,
            @RequestBody Food food
    ) {
        BeanUtils.copyProperties(food, foodFromDb, "id");

        return foodRepo.save(foodFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Food food) {
        foodRepo.delete(food);
    }
}
