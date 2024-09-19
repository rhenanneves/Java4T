package com.example;

public class ExemploConteudoBasico {
    // operações e tipos de variáveis
    public void operacoes() {
        int a = 20, b = 30;
        double c = 7.8;
        char letra = 'D';
        boolean teste = false;

        teste = (a > b) && (c < 8);
        System.err.println("resultado a+b = " + (a + b));
        System.out.println("resultado para teste: " + teste);
    }

    // controles de fluxo
    public void controleFluxo() {
        // If-Else
        int idade = 18;
        if (idade >= 18) {
            System.out.println("Maior de idade");
        } else {
            System.out.println("Menor de idade");
        }

        // Switch
        int dia = 2;
        switch (dia) {
            case 1:
                System.out.println("Segunda-feira");
                break;
            case 2:
                System.out.println("Terça-feira");
                break;
            default:
                System.out.println("Outro dia");
        }

        // laços de repetição
        for (int i = 0; i < 10; i++) {
            System.err.println(i);
        }
        int i = 0;
        while (i > 10) {
            System.out.println(i);
            i--;
        }
    }
}
