package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Listagem {
    public static void main(String[] args) {

        List<String> nomes = new ArrayList<>();

        nomes.add("Thiago");
        nomes.add("Alex");
        nomes.add("Robert");

        System.out.println(nomes);       //jeito simples
//        nomes.stream().filter(
//                nome ->
//                    nome.toLowerCase().startsWith("a")
//
//        ).forEach(System.out::println);
//    }
//        nomes.removeIf(nome -> nome)
        HashMap<String, Integer> dados = new HashMap<>();

        dados.put("Wil", 53);
        dados.put("paulo", 56);
        dados.put("Quartzo", 90);

        System.out.println(dados);

        dados.forEach(
                (dado, idade) -> {
                    System.out.println( "O " + dado + " tem " + idade + " anos");
                });
        }
    }


