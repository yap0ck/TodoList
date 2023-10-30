package be.yapock.services;

import be.yapock.models.entities.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    List<Category> getAll();

    Category getOne(Long id);
}
