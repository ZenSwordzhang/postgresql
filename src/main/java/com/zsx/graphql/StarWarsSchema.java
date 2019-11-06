//package com.zsx.graphql;
//
//import com.zsx.entity.EpisodeEnum;
//import graphql.schema.GraphQLEnumType;
//import graphql.schema.GraphQLInputObjectType;
//import graphql.schema.GraphQLInterfaceType;
//import graphql.schema.GraphQLObjectType;
//import graphql.schema.GraphQLSchema;
//import graphql.schema.GraphqlTypeComparatorRegistry;
//import graphql.schema.StaticDataFetcher;
//
//import static graphql.Scalars.GraphQLString;
//import static graphql.schema.GraphQLArgument.newArgument;
//import static graphql.schema.GraphQLEnumType.newEnum;
//import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
//import static graphql.schema.GraphQLInputObjectField.newInputObjectField;
//import static graphql.schema.GraphQLInputObjectType.newInputObject;
//import static graphql.schema.GraphQLInterfaceType.newInterface;
//import static graphql.schema.GraphQLList.list;
//import static graphql.schema.GraphQLNonNull.nonNull;
//import static graphql.schema.GraphQLObjectType.newObject;
//import static graphql.schema.GraphQLTypeReference.typeRef;
//import static graphql.schema.GraphqlTypeComparatorRegistry.*;
//import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;
//
//public class StarWarsSchema {
//
//    /**
//     * enum Episode {
//     *     NEWHOPE
//     *     EMPIRE
//     *     JEDI
//     * }
//     */
//    public static GraphQLEnumType episodeEnum = newEnum()
//            .name("Episode")
//            .description("One of the films in the Star Wars Trilogy")
//            .value("NEWHOPE", EpisodeEnum.NEWHOPE, "Released in 1977.")
//            .value("EMPIRE", EpisodeEnum.EMPIRE, "Released in 1980.")
//            .value("JEDI", EpisodeEnum.JEDI, "Released in 1983.")
//            .comparatorRegistry(BY_NAME_REGISTRY)
//            .build();
//
//
//    /**
//     * interface Character {
//     *     id: ID!
//     *     name: String!
//     *     friends: [Character]
//     *     appearsIn: [Episode]!
//     * }
//     */
//    public static GraphQLInterfaceType characterInterface = newInterface()
//            .name("Character")
//            .description("A character in the Star Wars Trilogy")
//            .field(newFieldDefinition()
//                    .name("id")
//                    .description("The id of the character.")
//                    .type(nonNull(GraphQLString)))
//            .field(newFieldDefinition()
//                    .name("name")
//                    .description("The name of the character.")
//                    .type(GraphQLString))
//            .field(newFieldDefinition()
//                    .name("friends")
//                    .description("The friends of the character, or an empty list if they have none.")
//                    .type(list(typeRef("Character"))))
//            .field(newFieldDefinition()
//                    .name("appearsIn")
//                    .description("Which movies they appear in.")
//                    .type(list(episodeEnum)))
//            .typeResolver(StarWarsData.getCharacterTypeResolver())
//            .comparatorRegistry(BY_NAME_REGISTRY)
//            .build();
//
//    /**
//     * type Human implements Character {
//     *     id: ID!
//     *     name: String!
//     *     friends: [Character]
//     *     appearsIn: [Episode]!
//     *     homePlanet: String
//     * }
//     */
//    public static GraphQLObjectType humanType = newObject()
//            .name("Human")
//            .description("A humanoid creature in the Star Wars universe.")
//            .withInterface(characterInterface)
//            .field(newFieldDefinition()
//                    .name("id")
//                    .description("The id of the human.")
//                    .type(nonNull(GraphQLString)))
//            .field(newFieldDefinition()
//                    .name("name")
//                    .description("The name of the human.")
//                    .type(GraphQLString))
//            .field(newFieldDefinition()
//                    .name("friends")
//                    .description("The friends of the human, or an empty list if they have none.")
//                    .type(list(characterInterface))
//                    .dataFetcher(StarWarsData.getFriendsDataFetcher()))
//            .field(newFieldDefinition()
//                    .name("appearsIn")
//                    .description("Which movies they appear in.")
//                    .type(list(episodeEnum)))
//            .field(newFieldDefinition()
//                    .name("homePlanet")
//                    .description("The home planet of the human, or null if unknown.")
//                    .type(GraphQLString))
//            .comparatorRegistry(BY_NAME_REGISTRY)
//            .build();
//
//    /**
//     * type Droid implements Character {
//     *     id: ID!
//     *     name: String!
//     *     friends: [Character]
//     *     appearsIn: [Episode]!
//     *     primaryFunction: String
//     * }
//     */
//    public static GraphQLObjectType droidType = newObject()
//            .name("Droid")
//            .description("A mechanical creature in the Star Wars universe.")
//            .withInterface(characterInterface)
//            .field(newFieldDefinition()
//                    .name("id")
//                    .description("The id of the droid.")
//                    .type(nonNull(GraphQLString)))
//            .field(newFieldDefinition()
//                    .name("name")
//                    .description("The name of the droid.")
//                    .type(GraphQLString))
//            .field(newFieldDefinition()
//                    .name("friends")
//                    .description("The friends of the droid, or an empty list if they have none.")
//                    .type(list(characterInterface))
//                    .dataFetcher(StarWarsData.getFriendsDataFetcher()))
//            .field(newFieldDefinition()
//                    .name("appearsIn")
//                    .description("Which movies they appear in.")
//                    .type(list(episodeEnum)))
//            .field(newFieldDefinition()
//                    .name("primaryFunction")
//                    .description("The primary function of the droid.")
//                    .type(GraphQLString))
//            .comparatorRegistry(BY_NAME_REGISTRY)
//            .build();
//
//    public static GraphQLInputObjectType inputHumanType = newInputObject()
//            .name("HumanInput")
//            .description("Input for A humanoid creature in the Star Wars universe.")
//            .field(newInputObjectField()
//                    .name("id")
//                    .description("The id of the human.")
//                    .type(nonNull(GraphQLString)))
//            .comparatorRegistry(BY_NAME_REGISTRY)
//            .build();
//
//    /**
//     * type Query {
//     *     bookById(id: ID): Book
//     *     companyById(id: ID): Company
//     *     hero(episode: Episode): Character
//     *     human(id : String) : Human
//     *     droid(id: ID!): Droid
//     * }
//     */
//    public static GraphQLObjectType queryType = newObject()
//            .name("Query")
//            .field(newFieldDefinition()
//                    .name("hero")
//                    .type(characterInterface)
//                    .argument(newArgument()
//                            .name("episode")
//                            .description("If omitted, returns the hero of the whole saga. If provided, returns the hero of that particular episode.")
//                            .type(episodeEnum))
//                    .dataFetcher(new StaticDataFetcher(StarWarsData.getArtoo())))
//            .field(newFieldDefinition()
//                    .name("human")
//                    .type(humanType)
//                    .argument(newArgument()
//                            .name("id")
//                            .description("id of the human")
//                            .type(nonNull(GraphQLString)))
//                    .dataFetcher(StarWarsData.getHumanDataFetcher()))
//            .field(newFieldDefinition()
//                    .name("droid")
//                    .type(droidType)
//                    .argument(newArgument()
//                            .name("id")
//                            .description("id of the droid")
//                            .type(nonNull(GraphQLString)))
//                    .dataFetcher(StarWarsData.getDroidDataFetcher()))
//            .comparatorRegistry(BY_NAME_REGISTRY)
//            .build();
//
//    public static GraphQLObjectType mutationType = newObject()
//            .name("MutationType")
//            .field(newFieldDefinition()
//                    .name("createHuman")
//                    .type(characterInterface)
//                    .argument(newArgument()
//                            .name("input")
//                            .type(inputHumanType))
//                    .dataFetcher(new StaticDataFetcher(StarWarsData.getArtoo())))
//            .comparatorRegistry(BY_NAME_REGISTRY)
//            .build();
//
//    public static GraphQLSchema starWarsSchema = GraphQLSchema.newSchema()
//            .query(queryType)
//            .mutation(mutationType)
//            .build();
//}
