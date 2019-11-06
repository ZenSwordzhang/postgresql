package com.zsx.entity;

import com.zsx.graphql.Character;

import java.util.List;

public class Droid implements Character {
    final String id;
    final String name;
    final List<String> friends;
    final List<EpisodeEnum> appearsIn;
    final String primaryFunction;

    public Droid(String id, String name, List<String> friends, List<EpisodeEnum> appearsIn, String primaryFunction) {
        this.id = id;
        this.name = name;
        this.friends = friends;
        this.appearsIn = appearsIn;
        this.primaryFunction = primaryFunction;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getFriends() {
        return friends;
    }

    public List<EpisodeEnum> getAppearsIn() {
        return appearsIn;
    }

    public String getPrimaryFunction() {
        return primaryFunction;
    }

    @Override
    public String toString() {
        return "Droid{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}