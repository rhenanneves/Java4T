package Exercicio5;
import java.io.*;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

// Classe para representar um item no estoque
class Item {
    private String nome;
    private int quantidade;

    public Item(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return nome + ":" + quantidade;
    }
}

// Classe para gerenciar o estoque
public class SistemaEstoque {

    private static final String ARQUIVO_ESTOQUE = "estoque.txt";
    private List<Item> estoque;

    public SistemaEstoque() {
        estoque = new ArrayList<>();
        carregarEstoque();
    }

    // Método para adicionar um item ao estoque
    public void adicionarItem(String nome, int quantidade) {
        estoque.add(new Item(nome, quantidade));
        salvarEstoque();
    }

    // Método para remover um item do estoque
    public void removerItem(String nome) {
        Item itemParaRemover = null;
        for (Item item : estoque) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                itemParaRemover = item;
                break;
            }
        }
        if (itemParaRemover != null) {
            estoque.remove(itemParaRemover);
            salvarEstoque();
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    // Método para listar todos os itens do estoque
    public void listarItens() {
        if (estoque.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            for (Item item : estoque) {
                System.out.println(item);
            }
        }
    }

    // Método para salvar o estoque em um arquivo
    private void salvarEstoque() {
        try (FileWriter fileWriter = new FileWriter(ARQUIVO_ESTOQUE);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Item item : estoque) {
                bufferedWriter.write(item.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o estoque: " + e.getMessage());
        }
    }

    // Método para carregar o estoque de um arquivo
    private void carregarEstoque() {
        File arquivo = new File(ARQUIVO_ESTOQUE);
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo de estoque: " + e.getMessage());
            }
            return;
        }

        try (FileReader fileReader = new FileReader(arquivo);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                String[] partes = linha.split(":");
                if (partes.length == 2) {
                    String nome = partes[0];
                    int quantidade;
                    try {
                        quantidade = Integer.parseInt(partes[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Dados inválidos no arquivo: " + linha);
                        continue;
                    }
                    estoque.add(new Item(nome, quantidade));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o estoque: " + e.getMessage());
        }
    }

    // Método principal para interação com o usuário
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaEstoque sistemaEstoque = new SistemaEstoque();
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1 - Adicionar item");
                System.out.println("2 - Remover item");
                System.out.println("3 - Listar itens");
                System.out.println("4 - Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer de entrada

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o nome do item: ");
                        String nomeAdicionar = scanner.nextLine();
                        System.out.print("Digite a quantidade do item: ");
                        int quantidadeAdicionar = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer de entrada
                        sistemaEstoque.adicionarItem(nomeAdicionar, quantidadeAdicionar);
                        System.out.println("Item adicionado com sucesso.");
                        break;
                    case 2:
                        System.out.print("Digite o nome do item a ser removido: ");
                        String nomeRemover = scanner.nextLine();
                        sistemaEstoque.removerItem(nomeRemover);
                        System.out.println("Item removido com sucesso.");
                        break;
                    case 3:
                        sistemaEstoque.listarItens();
                        break;
                    case 4:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                scanner.next();  // Limpa a entrada errada
            }
        }

        scanner.close();
        System.out.println("Aplicação encerrada.");
    }
}
