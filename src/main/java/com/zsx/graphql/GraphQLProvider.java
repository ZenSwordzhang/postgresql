package com.zsx.graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {

    private GraphQL graphQL;

    @Autowired
    private BookGraphQLDataFetchers bookGraphQLDataFetchers;
    @Autowired
    private CompanyGraphQLDataFetchers companyGraphQLDataFetchers;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL urlSchema = Resources.getResource("schema.graphqls");
        URL urlCompany = Resources.getResource("company.graphqls");
        URL urlStarWars = Resources.getResource("starWarsSchema.graphqls");
        String sdlSchema = Resources.toString(urlSchema, Charsets.UTF_8);
        String sdlCompany = Resources.toString(urlCompany, Charsets.UTF_8);
        String sdlStarWars = Resources.toString(urlStarWars, Charsets.UTF_8);
        this.graphQL = GraphQL.newGraphQL(buildSchema(sdlSchema, sdlCompany, sdlStarWars)).build();
    }

    private GraphQLSchema buildSchema(String ... schemaInputs) {
        // TODO: we will create the schema here later
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        // each registry is merged into the main registry
        SchemaParser schemaParser = new SchemaParser();
        for (String schemaInput : schemaInputs) {
            typeRegistry.merge(schemaParser.parse(schemaInput));
        }
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }


    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("bookById", bookGraphQLDataFetchers.getBookByIdDataFetcher())
                        .dataFetcher("companyById", companyGraphQLDataFetchers.getCompanyByIdDataFetcher())
                        .dataFetcher("hero", new StaticDataFetcher(StarWarsData.getArtoo()))
                        .dataFetcher("human", StarWarsData.getHumanDataFetcher())
                        .dataFetcher("droid", StarWarsData.getDroidDataFetcher())
                )
                .type(newTypeWiring("Book")
                        .dataFetcher("author", bookGraphQLDataFetchers.getAuthorDataFetcher())
                        // This line is new: we need to register the additional DataFetcher
                        .dataFetcher("pageCount", bookGraphQLDataFetchers.getPageCountDataFetcher()))
                // this uses builder function lambda syntax
                .type("Human", typeWiring -> typeWiring
                        .dataFetcher("friends", StarWarsData.getFriendsDataFetcher()))
                // you can use builder syntax if you don't like the lambda syntax
                .type("Droid", typeWiring -> typeWiring
                        .dataFetcher("friends", StarWarsData.getFriendsDataFetcher()))
                // or full builder syntax if that takes your fancy
                .type(newTypeWiring("Character")
                        .typeResolver(StarWarsData.getCharacterTypeResolver()).build())
                .build();
    }

    private RuntimeWiring buildDynamicRuntimeWiring() {
        WiringFactory dynamicWiringFactory = new WiringFactory() {

        };
        return RuntimeWiring.newRuntimeWiring()
                .wiringFactory(dynamicWiringFactory).build();
    }

}
