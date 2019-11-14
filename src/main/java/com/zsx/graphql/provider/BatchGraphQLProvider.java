package com.zsx.graphql.provider;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.zsx.entity.User;
import com.zsx.entity.User;
import com.zsx.graphql.BookGraphQLDataFetchers;
import com.zsx.graphql.CompanyGraphQLDataFetchers;
import com.zsx.graphql.StarWarsData;
import com.zsx.graphql.fetcher.BatchDataFetcher;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentationOptions;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.*;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static graphql.ExecutionInput.newExecutionInput;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class BatchGraphQLProvider {

    public static void main(String[] args) {
        BatchGraphQLProvider batchGraphQLProvider = new BatchGraphQLProvider();
        batchGraphQLProvider.init();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchGraphQLProvider.class);

    private GraphQL graphQL;

    @Autowired
    private BatchDataFetcher batchDataFetcher;

    @Bean("batchGraphQL")
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() {
        BatchLoader<String, User> userBatchLoader = new BatchLoader<String, User>() {
            @Override
            public CompletionStage<List<User>> load(List<String> keys) {
                return CompletableFuture.supplyAsync(() -> batchDataFetcher.getUserDataViaBatchHTTPApi(keys));
            }
        };

        DataLoader<String, User> userDataLoader = DataLoader.newDataLoader(userBatchLoader);

        DataLoaderDispatcherInstrumentationOptions options = DataLoaderDispatcherInstrumentationOptions
                .newOptions().includeStatistics(true);

        DataLoaderDispatcherInstrumentation dispatcherInstrumentation
                = new DataLoaderDispatcherInstrumentation(options);

        this.graphQL = GraphQL.newGraphQL(buildSchema())
                .instrumentation(dispatcherInstrumentation)
                .build();

        DataLoaderRegistry registry = new DataLoaderRegistry();
//        registry.register("user", userDataLoader);
        registry.register("1000", userDataLoader);
        registry.register("1001", userDataLoader);
        registry.register("1002", userDataLoader);
        registry.register("1003", userDataLoader);
        registry.register("1004", userDataLoader);
        registry.register("2000", userDataLoader);
        registry.register("2001", userDataLoader);
        System.out.println("=====================");
        System.out.println(registry.getKeys());
        ExecutionInput executionInput = newExecutionInput()
                .query(getQuery())
                .dataLoaderRegistry(registry)
                .build();

        ExecutionResult executionResult = graphQL.execute(executionInput);
        System.out.println("=======================");
        System.out.println(executionResult.toSpecification());
    }

    private String getQuery() {
        return "query { user(id: \"1000\") { id, name, friends {id, name} } }";
    }

    private GraphQLSchema buildSchema() {
        try {
            // TODO: we will create the schema here later
            URL urlBatchSchema = Resources.getResource("batchSchema.graphqls");
            String sdlBatch = Resources.toString(urlBatchSchema, Charsets.UTF_8);
            TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
            // each registry is merged into the main registry
            SchemaParser schemaParser = new SchemaParser();
            typeRegistry.merge(schemaParser.parse(sdlBatch));
            RuntimeWiring runtimeWiring = buildWiring();
            SchemaGenerator schemaGenerator = new SchemaGenerator();
            return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("QueryType")
                        .dataFetcher("user", batchDataFetcher.getUserDataFetcher()))
                .type("User", typeWiring -> typeWiring
                        .dataFetcher("friends", batchDataFetcher.getFriendsDataFetcher1())
                ).build();
    }

}
