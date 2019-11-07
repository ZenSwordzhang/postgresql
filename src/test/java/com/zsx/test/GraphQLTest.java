package com.zsx.test;

import com.zsx.test.entity.*;
import graphql.*;
import graphql.schema.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static graphql.Scalars.GraphQLBoolean;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.FieldCoordinates.coordinates;
import static graphql.schema.GraphQLCodeRegistry.newCodeRegistry;
import static graphql.schema.GraphQLEnumType.newEnum;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInputObjectField.newInputObjectField;
import static graphql.schema.GraphQLInputObjectType.newInputObject;
import static graphql.schema.GraphQLInterfaceType.newInterface;
import static graphql.schema.GraphQLObjectType.newObject;
import static graphql.schema.GraphQLUnionType.newUnionType;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GraphQLTest {

    private static final GraphQLObjectType CatType = newObject()
            .name("CatType")
            .field(newFieldDefinition()
                    .name("cat")
                    .type(GraphQLString)
            )
            .build();
    private static final GraphQLObjectType DogType = newObject()
            .name("DogType")
            .field(newFieldDefinition()
                    .name("dog")
                    .type(GraphQLString)
            )
            .build();

    /**
     * type Foo {
     * bar: String
     * }
     */
    @Test
    void createSchema() {
        GraphQLObjectType fooType = newObject()
                .name("Foo")
                .field(newFieldDefinition()
                        .name("bar")
                        .type(GraphQLString))
                .build();
        System.out.println(fooType);
    }

    @Test
    void testTypeResolver() {
        new TypeResolver() {
            @Override
            public GraphQLObjectType getType(TypeResolutionEnvironment env) {
                Object javaObject = env.getObject();
                if (javaObject instanceof Wizard) {
                    return env.getSchema().getObjectType("WizardType");
                } else if (javaObject instanceof Witch) {
                    return env.getSchema().getObjectType("WitchType");
                } else {
                    return env.getSchema().getObjectType("NecromancerType");
                }
            }
        };
    }

    @Test
    void testDataFetcher() {
        DataFetcher<Foo> fooDataFetcher = new DataFetcher<Foo>() {
            @Override
            public Foo get(DataFetchingEnvironment environment) {
                // environment.getSource() is the value of the surrounding
                // object. In this case described by objectType
                Foo value = perhapsFromDatabase(); // Perhaps getting from a DB or whatever
                return value;
            }
        };
    }

    @Test
    void testGraphQLObjectType() {
        GraphQLObjectType objectType = newObject()
                .name("ObjectType")
                .field(newFieldDefinition()
                        .name("foo")
                        .type(GraphQLString)
                )
                .build();
    }

    @Test
    void testGraphQLCodeRegistry() {
        DataFetcher fooDataFetcher = getFooDataFetcher();
        GraphQLCodeRegistry codeRegistry = newCodeRegistry()
                .dataFetcher(
                        coordinates("ObjectType", "foo"),
                        fooDataFetcher)
                .build();
    }

    /**
     * type SimpsonCharacter {
     *         name: String
     *         mainCharacter: Boolean
     *     }
     */
    @Test
    void testGraphQLObjectType1() {
        GraphQLObjectType simpsonCharacter = newObject()
                .name("SimpsonCharacter")
                .description("A Simpson character")
                .field(newFieldDefinition()
                        .name("name")
                        .description("The name of the character.")
                        .type(GraphQLString))
                .field(newFieldDefinition()
                        .name("mainCharacter")
                        .description("One of the main Simpson characters?")
                        .type(GraphQLBoolean))
                .build();
        System.out.println(simpsonCharacter);
    }

    /**
     * interface ComicCharacter {
     *         name: String;
     *     }
     */
    @Test
    void testGraphQLInterfaceType() {
        GraphQLInterfaceType comicCharacter = newInterface()
                .name("ComicCharacter")
                .description("An abstract comic character.")
                .field(newFieldDefinition()
                        .name("name")
                        .description("The name of the character.")
                        .type(GraphQLString))
                .build();
    }

    /**
     * type Cat {
     *         name: String;
     *         lives: Int;
     *     }
     *
     *     type Dog {
     *         name: String;
     *         bonesOwned: int;
     *     }
     *
     *     union Pet = Cat | Dog
     */
    @Test
    void testUnion() {
        TypeResolver typeResolver = new TypeResolver() {
            @Override
            public GraphQLObjectType getType(TypeResolutionEnvironment env) {
                if (env.getObject() instanceof Cat) {
                    return CatType;
                }
                if (env.getObject() instanceof Dog) {
                    return DogType;
                }
                return null;
            }
        };
        GraphQLUnionType petType = newUnionType()
                .name("Pet")
                .possibleType(CatType)
                .possibleType(DogType)
                .build();

        GraphQLCodeRegistry codeRegistry = newCodeRegistry()
                .typeResolver("Pet", typeResolver)
                .build();
    }

    /**
     * enum Color {
     *         RED
     *         GREEN
     *         BLUE
     *     }
     */
    @Test
    void testGraphQLEnumType() {
        GraphQLEnumType colorEnum = newEnum()
                .name("Color")
                .description("Supported colors.")
                .value("RED")
                .value("GREEN")
                .value("BLUE")
                .build();
        System.out.println(colorEnum);
    }

    /**
     * input Character {
     *         name: String
     *     }
     */
    @Test
    void testGraphQLInputObjectType() {
        GraphQLInputObjectType inputObjectType = newInputObject()
                .name("inputObjectType")
                .field(newInputObjectField()
                        .name("field")
                        .type(GraphQLString))
                .build();
    }

    @Test
    void testTypeReferences() {
        GraphQLObjectType person = newObject()
                .name("Person")
                .field(newFieldDefinition()
                        .name("friends")
                        .type(GraphQLList.list(GraphQLTypeReference.typeRef("Person"))))
                .build();
    }

    @Test
    void testGraphQL() {

        final GraphQLObjectType queryType = newObject()
                .name("hero")
                .field(newFieldDefinition()
                        .name("name")
                        .type(GraphQLString)
                )
                .build();
        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();

        GraphQL graphQL = GraphQL.newGraphQL(schema)
                .build();

        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query("query { hero { name } }")
                .build();

        ExecutionResult executionResult = graphQL.execute(executionInput);

        Object data = executionResult.getData();
        System.out.println("========================");
        System.out.println(data);
        List<GraphQLError> errors = executionResult.getErrors();
        System.out.println(errors);

    }

    private DataFetcher getFooDataFetcher() {
        DataFetcher<Foo> fooDataFetcher = new DataFetcher<Foo>() {
            @Override
            public Foo get(DataFetchingEnvironment environment) {
                // environment.getSource() is the value of the surrounding
                // object. In this case described by objectType
                Foo value = perhapsFromDatabase(); // Perhaps getting from a DB or whatever
                return value;
            }
        };
        return fooDataFetcher;
    }

    private Foo perhapsFromDatabase() {
        return new Foo();
    }
}
