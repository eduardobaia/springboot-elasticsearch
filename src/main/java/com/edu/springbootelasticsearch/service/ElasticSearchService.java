package com.edu.springbootelasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.edu.springbootelasticsearch.entity.Product;
import com.edu.springbootelasticsearch.utils.ElasticSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public SearchResponse<Map> matchAllServices() throws IOException {
        //Return all the records all the records in elasticsearch
      SearchResponse<Map> searchResponse = elasticsearchClient.search(s -> s.query(ElasticSearchUtil.supplier().get()), Map.class);
        System.out.println("elasticsearch query is "+ ElasticSearchUtil.supplier().get().toString());
      return searchResponse;
    }


    public SearchResponse<Product> matchAllProductServices() throws IOException {
        //Return all the records inside index products
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s.index("products").query(ElasticSearchUtil.supplier().get()), Product.class);
        System.out.println("elasticsearch query is "+ ElasticSearchUtil.supplier().get().toString());
        return searchResponse;
    }

        //Match products with name
    public SearchResponse<Product> matchProductByName(String name) throws IOException {
        //Return all the records inside index products
        Supplier<Query> supplier = ElasticSearchUtil.supplierWithName(name);
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s.index("products").query(supplier.get()), Product.class);
        System.out.println("elasticsearch query is "+ ElasticSearchUtil.supplier().get().toString());
        return searchResponse;
    }

}
