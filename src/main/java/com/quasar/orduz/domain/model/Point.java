package com.quasar.orduz.domain.model;

import lombok.Data;

@Data
public class Point {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
