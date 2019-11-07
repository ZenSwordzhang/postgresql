package com.zsx.graphql.fetcher;

import com.zsx.entity.StarWarsCharacter;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchDataFetcher {

    public DataFetcher getHeroDataFetcher() {
        return environment -> {
            DataLoader<String, Object> dataLoader = environment.getDataLoader("character");
            return dataLoader.load("2001");
        };
    }

    public DataFetcher getFriendsDataFetcher() {
        return environment -> {
            StarWarsCharacter starWarsCharacter = environment.getSource();
            List<String> friendIds = starWarsCharacter.getFriendIds();
            DataLoader<String, Object> dataLoader = environment.getDataLoader("character");
            return dataLoader.loadMany(friendIds);
        };
    }

}
