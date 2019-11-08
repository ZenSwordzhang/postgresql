package com.zsx.graphql.fetcher;

import com.zsx.entity.StarWarsCharacter;
import com.zsx.entity.User;
import graphql.schema.DataFetcher;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Component
public class BatchDataFetcher {

    static User luke = new User(
            "1000",
            "Luke Skywalker",
            asList("1002", "1003", "2000", "2001")
    );

    static User vader = new User(
            "1001",
            "Darth Vader",
            asList("1004")
    );

    static User han = new User(
            "1002",
            "Han Solo",
            asList("1000", "1003", "2001")
    );

    static User leia = new User(
            "1003",
            "Leia Organa",
            asList("1000", "1002", "2000", "2001")
    );

    static User tarkin = new User(
            "1004",
            "Wilhuff Tarkin",
            asList("1001")
    );

    static User threepio = new User(
            "2000",
            "C-3PO",
            asList("1000", "1002", "1003", "2001")
    );

    static User artoo = new User(
            "2001",
            "R2-D2",
            asList("1000", "1002", "1003")
    );

    static List<User> users = List.of(luke, vader, han, leia, tarkin, threepio, artoo);

    public DataFetcher getHeroDataFetcher() {
        return environment -> {
            DataLoader<String, Object> dataLoader = environment.getDataLoader("user");
            return dataLoader.load("2001");
        };
    }

    public DataFetcher getFriendsDataFetcher() {
        return environment -> {
            String userId = environment.getArgument("id");
            if (userId == null) {
                return null;
            }
            List<String> friendIds = loadUserById(userId).getFriendIds();
            DataLoader<String, Object> dataLoader = environment.getDataLoader("user");
            return dataLoader.loadMany(friendIds);
        };
    }

    public DataFetcher getUserDataFetcher() {
        return dataFetchingEnvironment -> {
            return loadUserById(dataFetchingEnvironment.getArgument("id"));
        };
    }

    public List<User> getUserDataViaBatchHTTPApi(List<String> userIds) {
//        return userIds.stream().map(this::loadUserById).collect(Collectors.toList());
        return userIds.stream().map(id -> loadUserById(id)).collect(Collectors.toList());
    }

    private User loadUserById(String userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        BatchDataFetcher batchDataFetcher = new BatchDataFetcher();
        List<String> userIds = List.of("1000", "1002");
        System.out.println(userIds.stream().map(id -> batchDataFetcher.loadUserById(id)).collect(Collectors.toList()));
        System.out.println(userIds.stream().map(id -> batchDataFetcher.loadUserById(id)).collect(Collectors.toList()));
    }

}
