package com.devstarrk.challenger.repositories;

import com.devstarrk.challenger.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
