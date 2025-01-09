package com.balieiro.Catalog.controllers;

import com.balieiro.Catalog.entities.Category;
import com.balieiro.Catalog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

     //Buscar todos
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = categoryService.findAll();
        return ResponseEntity.ok().body(list);
    }


     //Buscar por ai
     //Adicionar nova categoria

     //Atualizar nova categoria

     //Deletar categoria
}
