package be.yapock.services.impl;

import be.yapock.models.entities.Category;
import be.yapock.repositories.CategoryRepository;
import be.yapock.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Crée une nouvelle catégorie en l'ajoutant au référentiel.
     *
     * @param category La catégorie à créer.
     * @return La catégorie créée.
     */
    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Récupère toutes les catégories présentes dans le référentiel.
     *
     * @return Une liste de toutes les catégories.
     */
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    /**
     * Récupère une catégorie spécifique par son identifiant.
     *
     * @param id L'identifiant de la catégorie à récupérer.
     * @return La catégorie correspondant à l'identifiant.
     * @throws NoSuchElementException si la catégorie n'est pas trouvée.
     */
    @Override
    public Category getOne(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
