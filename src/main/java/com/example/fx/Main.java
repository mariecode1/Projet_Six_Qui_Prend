package com.example.fx;

import com.example.fx.mechanic.Start;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        Start.regle();
        Start.start();
        Start.GameLogic();

    }
}