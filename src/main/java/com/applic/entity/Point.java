package com.applic.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {
    private Integer x;
    private Integer y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
