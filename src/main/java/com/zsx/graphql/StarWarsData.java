package com.zsx.graphql;

import com.zsx.entity.Droid;
import com.zsx.entity.EpisodeEnum;
import com.zsx.entity.Human;
import graphql.schema.DataFetcher;
import graphql.schema.TypeResolver;
import graphql.schema.idl.EnumValuesProvider;
import graphql.schema.idl.NaturalEnumValuesProvider;

import java.util.ArrayList;
import java.util.List;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class StarWarsData {


    static Human luke = new Human(
            "1000",
            "Luke Skywalker",
            asList("1002", "1003", "2000", "2001"),
            asList(4, 5, 6),
            "Tatooine"
    );

    static Human vader = new Human(
            "1001",
            "Darth Vader",
            asList("1004"),
            asList(4, 5, 6),
            "Tatooine"
    );

    static Human han = new Human(
            "1002",
            "Han Solo",
            asList("1000", "1003", "2001"),
            asList(4, 5, 6),
            null
    );

    static Human leia = new Human(
            "1003",
            "Leia Organa",
            asList("1000", "1002", "2000", "2001"),
            asList(4, 5, 6),
            "Alderaan"
    );

    static Human tarkin = new Human(
            "1004",
            "Wilhuff Tarkin",
            asList("1001"),
            asList(6),
            null
    );

    static Map<String, Human> humanData = new LinkedHashMap<>();
    static List<Human> humans = List.of(luke, vader, han, leia, tarkin);
    static {

        humanData.put("1000", luke);
        humanData.put("1001", vader);
        humanData.put("1002", han);
        humanData.put("1003", leia);
        humanData.put("1004", tarkin);
    }

    static Droid threepio = new Droid(
            "2000",
            "C-3PO",
            asList("1000", "1002", "1003", "2001"),
            asList(4, 5, 6),
            "Protocol"
    );

    static Droid artoo = new Droid(
            "2001",
            "R2-D2",
            asList("1000", "1002", "1003"),
            asList(4, 5, 6),
            "Astromech"
    );

    static Map<String, Droid> droidData = new LinkedHashMap<>();
    static List<Droid> droids = List.of(threepio, artoo);

    static {
        droidData.put("2000", threepio);
        droidData.put("2001", artoo);

    }

    public static boolean isHuman(String id) {
        return humanData.get(id) != null;
    }

    public static Object getCharacterData(String id) {
        if (humanData.get(id) != null) {
            return humanData.get(id);
        } else if (droidData.get(id) != null) {
            return droidData.get(id);
        }
        return null;
    }


    public static Object getArtoo() {
        return artoo;
    }

    public static DataFetcher getHumanDataFetcher() {
        return dataFetchingEnvironment -> {
            String id  = dataFetchingEnvironment.getArgument("id");
            return humans
                    .stream()
                    .filter(human -> human.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        };
    }

    public static DataFetcher getDroidDataFetcher() {
        return dataFetchingEnvironment -> {
            String id  = dataFetchingEnvironment.getArgument("id");
            return droids
                    .stream()
                    .filter(droid -> droid.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        };
    }

    public static DataFetcher getFriendsDataFetcher() {
//        for (String id : environment.source.friends) {
        return environment -> {
            List<Object> result = new ArrayList<>();
            List<String> friendIds = ((Character)environment.getSource()).getFriends();
            friendIds.forEach(id -> {result.add(getCharacter(id));});
            return result;
        };
    }

//    public static TypeResolver getCharacterTypeResolver() {
//        TypeResolver typeResolver = new TypeResolver() {
//            @Override
//            public GraphQLObjectType getType(TypeResolutionEnvironment env) {
//                return env.getSchema().getObjectType("hero");
//            }
//        };
//        return typeResolver;
//    }
    public static TypeResolver getCharacterTypeResolver() {
        return env -> {
            if (env.getObject() instanceof Human) {
                return env.getSchema().getObjectType("Human");
            }else if (env.getObject() instanceof Droid) {
                return env.getSchema().getObjectType("Droid");
            }
            return null;
        };
    }

    public static TypeResolver getEpisodeTypeResolver() {
        return env -> {
            return env.getSchema().getObjectType("Episode");
        };
    }

    public static EnumValuesProvider getEpisodeProvider() {
        return name -> {
            EpisodeEnum episodeEnum = EpisodeEnum.valueOf(name);
            return  episodeEnum == null ? null : episodeEnum.getValue();
        };
    }

    private static Character getCharacter(String id) {
        if (humanData.get(id) != null) return humanData.get(id);
        if (droidData.get(id) != null) return droidData.get(id);
        return null;
    }
}
