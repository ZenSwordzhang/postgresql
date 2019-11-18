//package com.zsx.graphql.schema;
//
//import graphql.schema.GraphQLSchema;
//import graphql.schema.StaticDataFetcher;
//import graphql.schema.idl.RuntimeWiring;
//import graphql.schema.idl.SchemaGenerator;
//import graphql.schema.idl.SchemaParser;
//import graphql.schema.idl.TypeDefinitionRegistry;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;
//
//@Component
//public class CustomizeGraphQLSchema {
//
//    /**
//     * 此处会覆盖com.zsx.graphql.GraphQLProvider
//     * 自定义的graphql.servlet.mapping生效
//     * @return
//     */
//    @Bean
//    public GraphQLSchema schema() {
//        String schema = "type Query {hello: String}";
//
//        SchemaParser schemaParser = new SchemaParser();
//        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);
//
//        RuntimeWiring runtimeWiring = newRuntimeWiring()
//                .type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("world")))
//                .build();
//
//        SchemaGenerator schemaGenerator = new SchemaGenerator();
//        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
//    }
//}
