package com.edu.springbootelasticsearch.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.edu.springbootelasticsearch.entity.Product;
import com.edu.springbootelasticsearch.service.ElasticSearchService;
import com.edu.springbootelasticsearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){

        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }


    @GetMapping("/matchAll")
    public ResponseEntity<String> matchAll() throws IOException {

        SearchResponse<Map> searchResponse = elasticSearchService.matchAllServices();
        System.out.println(searchResponse.hits().hits().toString());
        return ResponseEntity.ok(searchResponse.hits().hits().toString());
    }

    @GetMapping("/matchAllProducts")
    public ResponseEntity<List<Product>> matchAllProducts() throws IOException {

        SearchResponse<Product> searchResponse = elasticSearchService.matchAllProductServices();
        System.out.println(searchResponse.hits().hits().toString());
        List<Hit<Product>> listHits = searchResponse.hits().hits();
//        List<Product> listOfProducts = new ArrayList<>();
//
//
//        for (Hit<Product> hit : listHits){
//            listOfProducts.add(hit.source());
//        }

        //
        return ResponseEntity.ok(listHits.stream().map(Hit::source).toList());
    }

}
