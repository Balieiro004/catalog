package com.balieiro.Catalog.service;

import com.balieiro.Catalog.entities.Category;
import com.balieiro.Catalog.entities.DTO.CategoryDTO;
import com.balieiro.Catalog.repositories.CategoryRepository;
import com.balieiro.Catalog.service.exceptions.ControllerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //@Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        return categoryRepository.findAll().stream().map(CategoryDTO::new).toList();
    }

    public CategoryDTO findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        Category entity = category.orElseThrow(() -> new ControllerNotFoundException("Category not found"));
        return new CategoryDTO(entity);
    }
}
