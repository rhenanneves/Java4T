package Exercicio3;

import java.util.InputMismatchException;
import java.util.Scanner;

// Exceção personalizada para valores inválidos
class ValorInvalidoException extends Exception {
    public ValorInvalidoException(String mensagem) {
        super(mensagem);
    }
}

public class FatorialRecursivo {

    // Método recursivo para calcular o fatorial
    public static int fatorial(int n) throws ValorInvalidoException {
        if (n < 0) {
            throw new ValorInvalidoException("O número não pode ser negativo.");
        } else if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * fatorial(n - 1); // Chamada recursiva
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean entradaValida = false;
        
        while (!entradaValida) {
            try {
                System.out.print("Digite um número inteiro positivo para calcular o fatorial: ");
                int numero = scanner.nextInt();

                // Verifica se o número é positivo e lança a exceção personalizada, se necessário
                if (numero < 0) {
                    throw new ValorInvalidoException("Número inválido! Insira um número inteiro positivo.");
                }

                // Calcula o fatorial se a entrada for válida
                int resultado = fatorial(numero);
                System.out.println("O fatorial de " + numero + " é: " + resultado);
                entradaValida = true;

            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro.");
                scanner.next();  // Limpa a entrada errada
            } catch (ValorInvalidoException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
