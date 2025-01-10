package com.balieiro.Catalog.controllers;

import com.balieiro.Catalog.entities.Category;
import com.balieiro.Catalog.entities.DTO.CategoryDTO;
import com.balieiro.Catalog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

     //Buscar todos
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> list = categoryService.findAll();
        return ResponseEntity.ok().body(list);
    }

    //Buscar por id
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<CategoryDTO> finById(@PathVariable Long id) {
        CategoryDTO entity = categoryService.findById(id);
        return ResponseEntity.ok().body(entity);
    }

    //Adicionar nova categoria

    //Atualizar nova categoria

    //Deletar categoria
}
