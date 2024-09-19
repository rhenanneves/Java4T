package Exercicio2;

import java.util.*;

abstract class Operacao {
    public abstract double calcular(double a, double b) throws ArithmeticException;
}

class Soma extends Operacao {
    @Override
    public double calcular(double a, double b) {
        return a + b;
    }
}

class Subtracao extends Operacao {
    @Override
    public double calcular(double a, double b) {
        return a - b;
    }
}

class Multiplicacao extends Operacao {
    @Override
    public double calcular(double a, double b) {
        return a * b;
    }
}

class Divisao extends Operacao {
    @Override
    public double calcular(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida.");
        }
        return a / b;
    }
}

class RaizQuadrada extends Operacao {
    @Override
    public double calcular(double a, double b) {
        return 0;  // Não usado, apenas para respeitar a assinatura abstrata.
    }

    // Sobrecarga para calcular a raiz de um único número
    public double calcular(double a) throws ArithmeticException {
        if (a < 0) {
            throw new ArithmeticException("Não é possível calcular a raiz quadrada de número negativo.");
        }
        return Math.sqrt(a);
    }
}

public class CalculadoraAvancada {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\nEscolha a operação:");
                System.out.println("1 - Soma");
                System.out.println("2 - Subtração");
                System.out.println("3 - Multiplicação");
                System.out.println("4 - Divisão");
                System.out.println("5 - Raiz Quadrada");
                System.out.println("6 - Sair");

                int opcao = scanner.nextInt();

                if (opcao == 6) {
                    continuar = false;
                    break;
                }

                double a = 0, b = 0;
                if (opcao != 5) {
                    System.out.print("Digite o primeiro número: ");
                    a = scanner.nextDouble();
                    System.out.print("Digite o segundo número: ");
                    b = scanner.nextDouble();
                } else {
                    System.out.print("Digite o número para a raiz quadrada: ");
                    a = scanner.nextDouble();
                }

                Operacao operacao;
                switch (opcao) {
                    case 1:
                        operacao = new Soma();
                        System.out.println("Resultado: " + operacao.calcular(a, b));
                        break;
                    case 2:
                        operacao = new Subtracao();
                        System.out.println("Resultado: " + operacao.calcular(a, b));
                        break;
                    case 3:
                        operacao = new Multiplicacao();
                        System.out.println("Resultado: " + operacao.calcular(a, b));
                        break;
                    case 4:
                        operacao = new Divisao();
                        System.out.println("Resultado: " + operacao.calcular(a, b));
                        break;
                    case 5:
                        RaizQuadrada raiz = new RaizQuadrada();
                        System.out.println("Resultado: " + raiz.calcular(a));
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira números.");
                scanner.next();  // Limpa a entrada errada
            } catch (ArithmeticException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Calculadora encerrada.");
    }
}
