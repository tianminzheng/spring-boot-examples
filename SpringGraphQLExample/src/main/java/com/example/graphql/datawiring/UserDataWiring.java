package com.example.graphql.datawiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.boot.RuntimeWiringBuilderCustomizer;
import org.springframework.stereotype.Component;

import com.example.graphql.datafetcher.AllUsersDataFetcher;
import com.example.graphql.datafetcher.ArticlesDataFetcher;
import com.example.graphql.datafetcher.UserDataFetcher;

import graphql.schema.idl.RuntimeWiring.Builder;

@Component
public class UserDataWiring implements RuntimeWiringBuilderCustomizer {

    private AllUsersDataFetcher allUsersDataFetcher;
    private UserDataFetcher userDataFetcher;
    private ArticlesDataFetcher articlesDataFetcher;

    @Autowired
    public UserDataWiring(AllUsersDataFetcher allUsersDataFetcher, UserDataFetcher userDataFetcher, ArticlesDataFetcher articlesDataFetcher) {
        this.allUsersDataFetcher = allUsersDataFetcher;
        this.userDataFetcher = userDataFetcher;
        this.articlesDataFetcher = articlesDataFetcher;
    }
    
	@Override
	public void customize(Builder builder) {

		builder.type("Query", typeWiring -> typeWiring
					.dataFetcher("users", allUsersDataFetcher)
					.dataFetcher("user", userDataFetcher))
        		.type("User", typeWiring -> typeWiring
        			.dataFetcher("articles", articlesDataFetcher)
        			.dataFetcher("friends", allUsersDataFetcher));		
	}
}
