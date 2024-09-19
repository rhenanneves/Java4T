package Exercicio1;
import java.util.Scanner;

public class SistemaAprovacao {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] notas = new double[4];
        double somaNotas = 0;
        boolean todasNotasMaiorQueNove = true;

        // Capturando as 4 notas
        for (int i = 0; i < 4; i++) {
            System.out.print("Digite a nota da disciplina " + (i + 1) + ": ");
            notas[i] = scanner.nextDouble();
            
            // Verificando se todas as notas são maiores que 9
            if (notas[i] <= 9) {
                todasNotasMaiorQueNove = false;
            }
            
            somaNotas += notas[i];  // Somando as notas
        }

        double media = somaNotas / 4;  // Calculando a média

        // Aplicando o bônus se necessário
        if (todasNotasMaiorQueNove) {
            media += media * 0.10;  // Adicionando 10% de bônus
        }

        // Verificando o status do aluno
        if (media >= 7) {
            System.out.println("Aprovado com média: " + media);
        } else if (media >= 5 && media < 7) {
            System.out.println("Recuperação com média: " + media);
        } else {
            System.out.println("Reprovado com média: " + media);
        }

        scanner.close();
    }
}
