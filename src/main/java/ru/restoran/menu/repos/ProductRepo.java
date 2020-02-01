package ru.restoran.menu.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restoran.menu.domain.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
