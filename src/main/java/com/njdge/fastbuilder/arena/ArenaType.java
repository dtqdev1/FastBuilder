package com.njdge.fastbuilder.arena;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ArenaType {
    EXTRA_SHORT(18,2),
    SHORTER(25,2),
    SHORT(32,3),
    NORMAL(44,3),
    LONG(55,6),
    EXTRA_LONG(135,21),
    ;
//    NORMAL(55, 6),
//    SHORT(33,3),
//    INCLINED_SHORT(23,3);

    private final int length; //spawn point to finish line
    private final int height; //spawn point's height to finish line's height

    ArenaType(int length, int height) {
        this.length = length;
        this.height = height;
    }
}
