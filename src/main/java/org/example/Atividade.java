package org.example;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

//public static float Resultado(int maior, float media) {
//    return maior/(media * media);

public class Atividade {
    public static void main(String[] args) {

        List<Integer> numeros = new ArrayList<>();

        numeros.add(5);
        numeros.add(8);
        numeros.add(7);
        numeros.add(3);
        numeros.add(5);
        numeros.add(30);
        numeros.add(23);
        numeros.add(3);
        numeros.add(6);
        numeros.add(89);

        System.out.println("Lista de numeros: " + numeros);

        int maior = Integer.MIN_VALUE;
        int menor = Integer.MAX_VALUE;
        float soma = 0;


        //para entender
        for(int n : numeros){

            if (n > maior) {
                maior = n;
            }
            if (n < menor) {
                menor = n;
            }
            soma += n;
        }
        float media = soma/numeros.size();
        System.out.println("------------");
        System.out.println("maior: " + maior + "|");
        System.out.println("menor: " + menor + "|");
        System.out.println("media: " + media + "|");
        System.out.println("------------");
    }
}


