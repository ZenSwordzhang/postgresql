package com.zsx.graphql;

import java.io.Serializable;
import java.util.List;

public interface Character extends Serializable {

    String getId();

    String getName();

    List<String> getFriends();

    List<Integer> getAppearsIn();

}
