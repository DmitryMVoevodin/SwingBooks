package com.netcracker.core;

public class Extra {

    private static final double EPS = 0.0000000001;

    public enum Gender{ Male, Female }

    public static boolean isEqual(double a, double b) {
        return (Math.abs(a - b) < EPS);
    }

}
