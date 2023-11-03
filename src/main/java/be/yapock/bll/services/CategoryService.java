package be.yapock.bll.services;

import be.yapock.dal.models.entities.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    List<Category> getAll();

    Category getOne(Long id);
}
