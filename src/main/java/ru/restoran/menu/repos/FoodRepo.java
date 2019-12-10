package ru.restoran.menu.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restoran.menu.domain.Food;

public interface FoodRepo extends JpaRepository<Food, Long> {
}
