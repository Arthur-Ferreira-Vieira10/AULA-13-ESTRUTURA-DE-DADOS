import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== MENU LISTA DUPLAMENTE ENCADEADA =====");
            System.out.println("1 - Inserir no início");
            System.out.println("2 - Inserir no fim");
            System.out.println("3 - Inserir em posição específica");
            System.out.println("4 - Remover do início");
            System.out.println("5 - Remover do fim");
            System.out.println("6 - Remover em posição específica");
            System.out.println("7 - Verificar se valor existe");
            System.out.println("8 - Exibir lista");
            System.out.println("9 - Esvaziar lista");
            System.out.println("10 - Mostrar tamanho da lista");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor: ");
                    String valorInicio = scanner.nextLine();
                    lista.inserirNoInicio(valorInicio);
                    System.out.println("✅ Inserido no início!");
                    break;

                case 2:
                    System.out.print("Digite o valor: ");
                    String valorFim = scanner.nextLine();
                    lista.inserir(valorFim);
                    System.out.println("✅ Inserido no fim!");
                    break;

                case 3:
                    System.out.print("Digite o valor: ");
                    String valorPos = scanner.nextLine();
                    System.out.print("Digite a posição: ");
                    int posicao = scanner.nextInt();
                    scanner.nextLine();
                    lista.inserirNaPosicao(valorPos, posicao);
                    System.out.println("✅ Inserido na posição " + posicao);
                    break;

                case 4:
                    lista.removerInicio();
                    System.out.println("❌ Primeiro elemento removido!");
                    break;

                case 5:
                    lista.removerFim();
                    System.out.println("❌ Último elemento removido!");
                    break;

                case 6:
                    System.out.print("Digite a posição para remover: ");
                    int posRemover = scanner.nextInt();
                    scanner.nextLine();
                    lista.removerPosicao(posRemover);
                    System.out.println("❌ Elemento da posição " + posRemover + " removido!");
                    break;

                case 7:
                    System.out.print("Digite o valor para buscar: ");
                    String valorBuscar = scanner.nextLine();
                    if (lista.contem(valorBuscar)) {
                        System.out.println("🔍 O valor '" + valorBuscar + "' está na lista!");
                    } else {
                        System.out.println("⚠️ O valor '" + valorBuscar + "' NÃO está na lista!");
                    }
                    break;

                case 8:
                    System.out.println("📜 Lista atual: " + lista);
                    break;

                case 9:
                    lista.esvaziar();
                    System.out.println("🧹 Lista esvaziada!");
                    break;

                case 10:
                    System.out.println("📏 Tamanho da lista: " + lista.tamanho());
                    break;

                case 0:
                    System.out.println("👋 Encerrando o programa...");
                    break;

                default:
                    System.out.println("❗ Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}


class No {
    String valor;
    No anterior;
    No proximo;

    public No(String valor) {
        this.valor = valor;
        this.anterior = null;
        this.proximo = null;
    }
}


class ListaDuplamenteEncadeada {
    private No inicio;
    private No fim;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        inicio = null;
        fim = null;
        tamanho = 0;
    }

    
    public void inserir(String valor) {
        No novo = new No(valor);
        if (inicio == null) {
            inicio = fim = novo;
        } else {
            fim.proximo = novo;
            novo.anterior = fim;
            fim = novo;
        }
        tamanho++;
    }

    
    public void inserirNoInicio(String valor) {
        No novo = new No(valor);
        if (inicio == null) {
            inicio = fim = novo;
        } else {
            novo.proximo = inicio;
            inicio.anterior = novo;
            inicio = novo;
        }
        tamanho++;
    }

    
    public void inserirNaPosicao(String valor, int pos) {
        if (pos <= 0) {
            inserirNoInicio(valor);
        } else if (pos >= tamanho) {
            inserir(valor);
        } else {
            No novo = new No(valor);
            No atual = inicio;
            for (int i = 0; i < pos; i++) {
                atual = atual.proximo;
            }
            novo.proximo = atual;
            novo.anterior = atual.anterior;
            atual.anterior.proximo = novo;
            atual.anterior = novo;
            tamanho++;
        }
    }

    
    public void removerInicio() {
        if (inicio == null) return;
        if (inicio == fim) {
            inicio = fim = null;
        } else {
            inicio = inicio.proximo;
            inicio.anterior = null;
        }
        tamanho--;
    }

    
    public void removerFim() {
        if (fim == null) return;
        if (inicio == fim) {
            inicio = fim = null;
        } else {
            fim = fim.anterior;
            fim.proximo = null;
        }
        tamanho--;
    }

    
    public void removerPosicao(int pos) {
        if (pos < 0 || pos >= tamanho) return;
        if (pos == 0) {
            removerInicio();
        } else if (pos == tamanho - 1) {
            removerFim();
        } else {
            No atual = inicio;
            for (int i = 0; i < pos; i++) atual = atual.proximo;
            atual.anterior.proximo = atual.proximo;
            atual.proximo.anterior = atual.anterior;
            tamanho--;
        }
    }

    
    public boolean contem(String valor) {
        No atual = inicio;
        while (atual != null) {
            if (atual.valor.equalsIgnoreCase(valor)) return true;
            atual = atual.proximo;
        }
        return false;
    }

    
    public void esvaziar() {
        inicio = fim = null;
        tamanho = 0;
    }

    
    public int tamanho() {
        return tamanho;
    }

    
    @Override
    public String toString() {
        if (inicio == null) return "Lista vazia";
        StringBuilder sb = new StringBuilder();
        No atual = inicio;
        while (atual != null) {
            sb.append(atual.valor);
            if (atual.proximo != null) sb.append(" <-> ");
            atual = atual.proximo;
        }
        return sb.toString();
    }
}
