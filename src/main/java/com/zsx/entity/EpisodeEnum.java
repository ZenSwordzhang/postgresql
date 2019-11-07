package com.zsx.entity;

public enum EpisodeEnum {

    NEWHOPE(4), EMPIRE(5), JEDI(6);

    private EpisodeEnum(Integer value) {
        this.value = value;
    }

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public static EpisodeEnum getEpisodeEnum(Integer value) {
        for(EpisodeEnum episodeEnum : EpisodeEnum.values()){
            if(value.equals(episodeEnum.getValue())){
                return episodeEnum;
            }
        }
        return null;
    }

}
