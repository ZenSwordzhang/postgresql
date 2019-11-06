package com.zsx.entity;

import com.zsx.graphql.Character;

import java.util.List;

public class Human implements Character {
    final String id;
    final String name;
    final List<String> friends;
    final List<EpisodeEnum> appearsIn;
    final String homePlanet;

    public Human(String id, String name, List<String> friends, List<EpisodeEnum> appearsIn, String homePlanet) {
        this.id = id;
        this.name = name;
        this.friends = friends;
        this.appearsIn = appearsIn;
        this.homePlanet = homePlanet;
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

    public String getHomePlanet() {
        return homePlanet;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
