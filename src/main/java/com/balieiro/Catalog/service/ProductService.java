package com.balieiro.Catalog.service;

import com.balieiro.Catalog.entities.Category;
import com.balieiro.Catalog.entities.DTO.CategoryDTO;
import com.balieiro.Catalog.entities.DTO.ProductDTO;
import com.balieiro.Catalog.entities.Product;
import com.balieiro.Catalog.repositories.CategoryRepository;
import com.balieiro.Catalog.repositories.ProductRepository;
import com.balieiro.Catalog.service.exceptions.ControllerNotFoundException;
import com.balieiro.Catalog.service.exceptions.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
        Page<Product> products = productRepository.findAll(pageRequest);
        return products.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        Product entity = product.orElseThrow(() -> new ControllerNotFoundException("Category not found"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        Product product = new Product();
       // product.setName(productDTO.getName());
        return new ProductDTO(productRepository.save(product));

    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        try{
            Product entity = productRepository.getReferenceById(id);
           // entity.setName(productDTO.getName());
            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ControllerNotFoundException("Category not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ControllerNotFoundException("Category not found");
        }
        try{
            productRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }
}
