package Exercicio4;
import java.util.*;

// Classe para representar um contato
class Contato {
    private String nome;
    private String telefone;

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Telefone: " + telefone;
    }
}

// Exceção personalizada para quando um contato não é encontrado
class ContatoNaoEncontradoException extends Exception {
    public ContatoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

// Exceção personalizada para quando a agenda está cheia
class AgendaCheiaException extends Exception {
    public AgendaCheiaException(String mensagem) {
        super(mensagem);
    }
}

// Classe para gerenciar a agenda telefônica
public class AgendaTelefonica {

    private static final int MAX_CONTATOS = 100;
    private Contato[] contatos;
    private int numContatos;

    public AgendaTelefonica() {
        contatos = new Contato[MAX_CONTATOS];
        numContatos = 0;
    }

    // Método para adicionar um contato
    public void adicionarContato(String nome, String telefone) throws AgendaCheiaException {
        if (numContatos >= MAX_CONTATOS) {
            throw new AgendaCheiaException("Agenda cheia! Não é possível adicionar mais contatos.");
        }
        contatos[numContatos++] = new Contato(nome, telefone);
    }

    // Método para remover um contato
    public void removerContato(String nome) throws ContatoNaoEncontradoException {
        int index = encontrarContatoIndex(nome);
        if (index == -1) {
            throw new ContatoNaoEncontradoException("Contato não encontrado.");
        }
        for (int i = index; i < numContatos - 1; i++) {
            contatos[i] = contatos[i + 1];
        }
        contatos[--numContatos] = null;
    }

    // Método para buscar um contato
    public Contato buscarContato(String nome) throws ContatoNaoEncontradoException {
        int index = encontrarContatoIndex(nome);
        if (index == -1) {
            throw new ContatoNaoEncontradoException("Contato não encontrado.");
        }
        return contatos[index];
    }

    // Método para listar todos os contatos
    public void listarContatos() {
        if (numContatos == 0) {
            System.out.println("A agenda está vazia.");
            return;
        }
        for (int i = 0; i < numContatos; i++) {
            System.out.println(contatos[i]);
        }
    }

    // Método auxiliar para encontrar o índice de um contato
    private int encontrarContatoIndex(String nome) {
        for (int i = 0; i < numContatos; i++) {
            if (contatos[i].getNome().equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    // Método principal para interação com o usuário
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgendaTelefonica agenda = new AgendaTelefonica();
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1 - Adicionar contato");
                System.out.println("2 - Remover contato");
                System.out.println("3 - Buscar contato");
                System.out.println("4 - Listar contatos");
                System.out.println("5 - Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer de entrada

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o nome do contato: ");
                        String nomeAdicionar = scanner.nextLine();
                        System.out.print("Digite o telefone do contato: ");
                        String telefoneAdicionar = scanner.nextLine();
                        try {
                            agenda.adicionarContato(nomeAdicionar, telefoneAdicionar);
                            System.out.println("Contato adicionado com sucesso.");
                        } catch (AgendaCheiaException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("Digite o nome do contato a ser removido: ");
                        String nomeRemover = scanner.nextLine();
                        try {
                            agenda.removerContato(nomeRemover);
                            System.out.println("Contato removido com sucesso.");
                        } catch (ContatoNaoEncontradoException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("Digite o nome do contato a ser buscado: ");
                        String nomeBuscar = scanner.nextLine();
                        try {
                            Contato contato = agenda.buscarContato(nomeBuscar);
                            System.out.println("Contato encontrado: " + contato);
                        } catch (ContatoNaoEncontradoException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;
                    case 4:
                        agenda.listarContatos();
                        break;
                    case 5:
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
