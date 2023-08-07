package com.edu.springbootelasticsearch.utils;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;

import java.util.function.Supplier;

public class ElasticSearchUtil {


    private static MatchQuery.Builder matchAllQuery;

    public static Supplier<Query> supplier(){
        Supplier<Query> supplier = () -> Query.of( q-> q.matchAll(matchAllQuery()));
        return supplier;
    }


    public static MatchAllQuery matchAllQuery (){
        val matchAllQuery = new MatchAllQuery.Builder();
        return matchAllQuery.build();
    }


    public static MatchQuery matchWithName(String fieldValue){
        val matchQuery = new MatchQuery.Builder();
        return matchQuery.field("name").query(fieldValue).build();
    }
    public static Supplier<Query> supplierWithName(String fieldValue){
        Supplier<Query> supplier = () -> Query.of( q-> q.match(matchWithName(fieldValue)));
        return supplier;
    }




}
