package com.example.graphql.controller;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.GraphQlSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GraphQLController {
    private GraphQL graphQL;
        
    @Autowired
    public GraphQLController(GraphQlSource graphQlSource) throws IOException {
    	graphQL = graphQlSource.graphQl();
    }

    @PostMapping(value = "/query")
    public ResponseEntity<Object> query(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        System.out.println("errors: " + result.getErrors());
        return ResponseEntity.ok(result.getData());
    }
}
