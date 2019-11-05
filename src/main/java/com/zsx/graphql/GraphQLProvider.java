//package com.zsx.graphql;
//
//import com.google.common.base.Charsets;
//import com.google.common.io.Resources;
//import graphql.ExecutionResult;
//import graphql.GraphQL;
//import graphql.schema.GraphQLSchema;
//import graphql.schema.idl.RuntimeWiring;
//import graphql.schema.idl.SchemaGenerator;
//import graphql.schema.idl.SchemaParser;
//import graphql.schema.idl.TypeDefinitionRegistry;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.net.URL;
//
//import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;
//
//@Component
//public class GraphQLProvider {
//
//    private GraphQL graphQL;
//
//    @Autowired
//    private GraphQLDataFetchers graphQLDataFetchers;
//
//    @Bean
//    public GraphQL graphQL() {
//        return graphQL;
//    }
//
//    @PostConstruct
//    public void init() throws IOException {
//        URL url = Resources.getResource("schema.graphqls");
//        String sdl = Resources.toString(url, Charsets.UTF_8);
//        GraphQLSchema graphQLSchema = buildSchema(sdl);
//        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
//        String query = "{bookById(id: \"book-1\") {id, name, pageCount, author {firstName, lastName}}}";
//        ExecutionResult executionResult = graphQL.execute(query);
//        System.out.println("==========" + executionResult.toSpecification());
//    }
//
//    private GraphQLSchema buildSchema(String sdl) {
//        // TODO: we will create the schema here later
//        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
//        RuntimeWiring runtimeWiring = buildWiring();
//        SchemaGenerator schemaGenerator = new SchemaGenerator();
//        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
//    }
//
//
//    private RuntimeWiring buildWiring() {
//        return RuntimeWiring.newRuntimeWiring()
//                .type(newTypeWiring("Query")
//                        .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
//                .type(newTypeWiring("Book")
//                        .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher())
//                        // This line is new: we need to register the additional DataFetcher
//                        .dataFetcher("pageCount", graphQLDataFetchers.getPageCountDataFetcher()))
//                .build();
//    }
//
//}
