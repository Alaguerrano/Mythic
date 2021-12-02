package com.example.mythic.model

public class Dado(val caras : Int) {
    public fun tirar(): Int{
        return (1..caras).random()
    }
}