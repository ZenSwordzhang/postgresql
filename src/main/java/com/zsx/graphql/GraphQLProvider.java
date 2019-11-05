package com.zsx.graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
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
        String sdlSchema = Resources.toString(urlSchema, Charsets.UTF_8);
        String sdlCompany = Resources.toString(urlCompany, Charsets.UTF_8);
        this.graphQL = GraphQL.newGraphQL(buildSchema(sdlSchema, sdlCompany)).build();
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
                        .dataFetcher("bookById", bookGraphQLDataFetchers.getBookByIdDataFetcher()))
                .type(newTypeWiring("Book")
                        .dataFetcher("author", bookGraphQLDataFetchers.getAuthorDataFetcher())
                        // This line is new: we need to register the additional DataFetcher
                        .dataFetcher("pageCount", bookGraphQLDataFetchers.getPageCountDataFetcher()))
                .type(newTypeWiring("Query")
                        .dataFetcher("companyById", companyGraphQLDataFetchers.getCompanyByIdDataFetcher()))
                .build();
    }

}
