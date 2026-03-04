package org.example;

public class Aula {
    public static void main(String[] args) {


        String nome = "Thiago";
        System.out.println(nome);

        int numero = 5;

        if (numero % 2 == 0) {
            System.out.println("par");
        } else {
            System.out.println("impar");
        }

        int idadePessoas = 50;
        switch (idadePessoas) {
            case 5:
                System.out.println("tem cinco anos");
                break;
            default:
                System.out.println("ok");
                break;
        }
    }
}
