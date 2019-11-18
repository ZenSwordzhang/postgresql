package com.zsx.graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(GraphQLProvider.class);

    @Autowired
    private BookGraphQLDataFetchers bookGraphQLDataFetchers;
    @Autowired
    private CompanyGraphQLDataFetchers companyGraphQLDataFetchers;

    // 通过graphQL注入的形式等价于schema注入，graphQL注入bean不能自定义graphql.servlet.mapping
//    private GraphQL graphQL;
//
//    @Bean
//    public GraphQL graphQL() {
//        return graphQL;
//    }
//
//    @PostConstruct
//    public void init() {
//        List<String> sdls = getAllSdl("schema.graphqls", "book.graphqls", "company.graphqls", "starWarsSchema.graphqls", "product.graphqls");
//        this.graphQL = GraphQL.newGraphQL(buildSchema(sdls)).build();
//    }

    @Bean
    public GraphQL graphQL() {
        return GraphQL.newGraphQL(schema()).build();
    }

    @Bean
    public GraphQLSchema schema() {
        List<String> sdls = getAllSdl("schema.graphqls", "book.graphqls", "company.graphqls", "starWarsSchema.graphqls", "product.graphqls");
        return buildSchema(sdls);
    }

    private List<String> getAllSdl(String ... fileNames) {
        try {
            URL urlSchema;
            List<String> sdls = new ArrayList<>();
            for (String fileName: fileNames) {
                urlSchema = Resources.getResource(fileName);
                sdls.add(Resources.toString(urlSchema, Charsets.UTF_8));
            }
            return sdls;
        } catch (IOException e) {
            LOGGER.error("GraphQLProvider.getAllSdl.IOException", e);
            return null;
        }
    }

    private GraphQLSchema buildSchema(List<String> schemaInputs) {
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
                        .dataFetcher("friends", StarWarsData.getFriendsDataFetcher())
                )
                // you can use builder syntax if you don't like the lambda syntax
                .type("Droid", typeWiring -> typeWiring
                        .dataFetcher("friends", StarWarsData.getFriendsDataFetcher())
                )
                .type(newTypeWiring("Episode")
                        .enumValues(StarWarsData.getEpisodeProvider()))
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
