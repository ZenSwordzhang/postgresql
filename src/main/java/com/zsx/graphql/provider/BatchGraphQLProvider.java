//package com.zsx.graphql.provider;
//
//import com.google.common.base.Charsets;
//import com.google.common.io.Resources;
//import com.zsx.entity.StarWarsCharacter;
//import com.zsx.graphql.BookGraphQLDataFetchers;
//import com.zsx.graphql.CompanyGraphQLDataFetchers;
//import com.zsx.graphql.StarWarsData;
//import com.zsx.graphql.fetcher.BatchDataFetcher;
//import graphql.ExecutionInput;
//import graphql.ExecutionResult;
//import graphql.GraphQL;
//import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
//import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentationOptions;
//import graphql.schema.DataFetcher;
//import graphql.schema.DataFetchingEnvironment;
//import graphql.schema.GraphQLSchema;
//import graphql.schema.StaticDataFetcher;
//import graphql.schema.idl.*;
//import org.dataloader.BatchLoader;
//import org.dataloader.DataLoader;
//import org.dataloader.DataLoaderRegistry;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.CompletionStage;
//
//import static graphql.ExecutionInput.newExecutionInput;
//import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;
//
//@Component
//public class BatchGraphQLProvider {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(BatchGraphQLProvider.class);
//
//    private GraphQL graphQL;
//
//    @Autowired
//    private BatchDataFetcher batchDataFetcher;
//
//    @Bean("batchGraphQL")
//    public GraphQL graphQL() {
//        return graphQL;
//    }
//
//    @PostConstruct
//    public void init() {
////        DataLoaderDispatcherInstrumentationOptions options = DataLoaderDispatcherInstrumentationOptions
////                .newOptions().includeStatistics(true);
////
////        DataLoaderDispatcherInstrumentation dispatcherInstrumentation
////                = new DataLoaderDispatcherInstrumentation(options);
////        List<String> sdls = getAllSdl("schema.graphqls", "company.graphqls", "starWarsSchema.graphqls", "product.graphqls");
////        this.graphQL = GraphQL.newGraphQL(buildSchema(sdls))
////                .instrumentation(dispatcherInstrumentation)
////                .build();
//        BatchLoader<String, Object> characterBatchLoader = new BatchLoader<String, Object>() {
//            @Override
//            public CompletionStage<List<Object>> load(List<String> keys) {
//                return CompletableFuture.supplyAsync(() -> getCharacterDataViaBatchHTTPApi(keys));
//            }
//        };
//
//        DataFetcher heroDataFetcher = new DataFetcher() {
//            @Override
//            public Object get(DataFetchingEnvironment environment) {
//                DataLoader<String, Object> dataLoader = environment.getDataLoader("character");
//                return dataLoader.load("2001"); // R2D2
//            }
//        };
//
//        DataFetcher friendsDataFetcher = new DataFetcher() {
//            @Override
//            public Object get(DataFetchingEnvironment environment) {
//                StarWarsCharacter starWarsCharacter = environment.getSource();
//                List<String> friendIds = starWarsCharacter.getFriendIds();
//                DataLoader<String, Object> dataLoader = environment.getDataLoader("character");
//                return dataLoader.loadMany(friendIds);
//            }
//        };
//
//        DataLoaderDispatcherInstrumentationOptions options = DataLoaderDispatcherInstrumentationOptions
//                .newOptions().includeStatistics(true);
//
//        DataLoaderDispatcherInstrumentation dispatcherInstrumentation
//                = new DataLoaderDispatcherInstrumentation(options);
//
//        GraphQL graphQL = GraphQL.newGraphQL(buildSchema())
//                .instrumentation(dispatcherInstrumentation)
//                .build();
//
//        DataLoader<String, Object> characterDataLoader = DataLoader.newDataLoader(characterBatchLoader);
//
//        DataLoaderRegistry registry = new DataLoaderRegistry();
//        registry.register("character", characterDataLoader);
//
//        ExecutionInput executionInput = newExecutionInput()
//                .query(getQuery())
//                .dataLoaderRegistry(registry)
//                .build();
//
//        ExecutionResult executionResult = graphQL.execute(executionInput);
//    }
//
////    private List<String> getAllSdl(String ... fileNames) {
////        try {
////            URL urlSchema;
////            List<String> sdls = new ArrayList<>();
////            for (String fileName: fileNames) {
////                urlSchema = Resources.getResource(fileName);
////                sdls.add(Resources.toString(urlSchema, Charsets.UTF_8));
////            }
////            return sdls;
////        } catch (IOException e) {
////            LOGGER.error("BatchGraphQLProvider.getAllSdl.IOException", e);
////            return null;
////        }
////    }
////
////    private GraphQLSchema buildSchema(List<String> schemaInputs) {
////        // TODO: we will create the schema here later
////        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
////        // each registry is merged into the main registry
////        SchemaParser schemaParser = new SchemaParser();
////        for (String schemaInput : schemaInputs) {
////            typeRegistry.merge(schemaParser.parse(schemaInput));
////        }
////        RuntimeWiring runtimeWiring = buildWiring();
////        SchemaGenerator schemaGenerator = new SchemaGenerator();
////        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
////    }
////
////
////    private RuntimeWiring buildWiring() {
////        return RuntimeWiring.newRuntimeWiring()
////                .type(newTypeWiring("Query")
////                        .dataFetcher("hero", batchDataFetcher.getHeroDataFetcher())
////                        .dataFetcher("human", StarWarsData.getHumanDataFetcher())
////                        .dataFetcher("droid", StarWarsData.getDroidDataFetcher())
////                )
////                // this uses builder function lambda syntax
////                .type("Human", typeWiring -> typeWiring
////                        .dataFetcher("friends", StarWarsData.getFriendsDataFetcher())
////                )
////                // you can use builder syntax if you don't like the lambda syntax
////                .type("Droid", typeWiring -> typeWiring
////                        .dataFetcher("friends", batchDataFetcher.getFriendsDataFetcher())
////                )
////                .type(newTypeWiring("Episode")
////                        .enumValues(StarWarsData.getEpisodeProvider()))
////                // or full builder syntax if that takes your fancy
////                .type(newTypeWiring("Character")
////                        .typeResolver(StarWarsData.getCharacterTypeResolver()).build())
////                .build();
////    }
//
//}
