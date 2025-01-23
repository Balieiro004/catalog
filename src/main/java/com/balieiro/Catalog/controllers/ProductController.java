package com.balieiro.Catalog.controllers;

import com.balieiro.Catalog.entities.DTO.ProductDTO;
import com.balieiro.Catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService ProductService;

     //Buscar todos
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction)
    {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<ProductDTO> list = ProductService.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    //Buscar por id
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<ProductDTO> finById(@PathVariable Long id) {
        ProductDTO entity = ProductService.findById(id);
        return ResponseEntity.ok().body(entity);
    }

    //Adicionar nova categoria
    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO ProductDTO) {
        ProductDTO entity =  ProductService.save(ProductDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }
    //Atualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO ProductDTO) {
        ProductDTO entity = ProductService.update(id, ProductDTO);
        return ResponseEntity.ok().body(entity);
    }

    //Deletar categoria
    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        ProductService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
