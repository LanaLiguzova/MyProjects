package com.stores.bookstore.service;

import com.stores.bookstore.model.Category;
import com.stores.bookstore.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //---Check it!!!!
    public Category getCategory(int id) {
        return categoryRepository.getReferenceById(id);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }


}
