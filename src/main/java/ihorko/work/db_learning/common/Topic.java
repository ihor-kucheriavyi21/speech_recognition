package ihorko.work.db_learning.common;

import lombok.Getter;

@Getter
public enum Topic {
    ENGLISH("en-US"),
    UKRAINIAN("uk-UA");

    private final String code;

    Topic(String code) {
        this.code = code;
    }
}
