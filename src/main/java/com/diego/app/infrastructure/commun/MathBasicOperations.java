package com.diego.app.infrastructure.commun;

public class MathBasicOperations {

    public static Double toNegative(Double number){
        return number * -1;
    }

    public static Double toPositive(Double number){
        return Math.abs(number);
    }
}
