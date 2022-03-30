package com.example.springdataintro.services;

import com.example.springdataintro.entities.Category;
import com.example.springdataintro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Set<Category> getRandomCategories() {
        long size = categoryRepository.count();

        Random random = new Random();

        int categoriesCount = random.nextInt((int) size) + 1;

        Set<Integer> categories = new HashSet<>();
        for (int i = 0; i < categoriesCount; i++) {
            int id = random.nextInt((int) size) + 1;
            categories.add(id);
        }

        List<Category> allById = categoryRepository.findAllById(categories);
        return (new HashSet<>(allById));
    }
}
