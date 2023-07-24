package com.edu.springbootelasticsearch.service;

import com.edu.springbootelasticsearch.entity.Product;
import com.edu.springbootelasticsearch.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;


    public Iterable<Product> getProducts(){
       return  productRepository.findAll();
    }


    public Product save(Product product){

        return  productRepository.save(product);
    }

    public Product   update(Product product){
        return productRepository.save(product);
    }


    public void delete(Long id){
        productRepository.deleteById(id);
    }

}
