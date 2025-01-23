package com.balieiro.Catalog.controllers;

import com.balieiro.Catalog.entities.DTO.CategoryDTO;
import com.balieiro.Catalog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

     //Buscar todos
    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable)
    {
        Page<CategoryDTO> list = categoryService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    //Buscar por id
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<CategoryDTO> finById(@PathVariable Long id) {
        CategoryDTO entity = categoryService.findById(id);
        return ResponseEntity.ok().body(entity);
    }

    //Adicionar nova categoria
    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO entity =  categoryService.save(categoryDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }
    //Atualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO entity = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok().body(entity);
    }

    //Deletar categoria
    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
