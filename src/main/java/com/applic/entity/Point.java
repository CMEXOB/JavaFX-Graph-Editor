package com.applic.entity;

import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {
    private Integer x;
    private Integer y;
    private Color color;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        color = Color.BLACK;
    }
}
