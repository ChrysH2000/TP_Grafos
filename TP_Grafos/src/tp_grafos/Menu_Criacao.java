package tp_grafos;
import java.util.Scanner;

public class Menu_Criacao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Inicialização do Menu de criação
        System.out.print("Digite o número de vértices do grafo: ");
            int numVertices = scanner.nextInt();
        System.out.print("Você deseja ponderar suas arestas (1 - Sim / 2 - Não): ");
            int ponderararestas = scanner.nextInt();

        Matriz_Adj grafoMatriz = new Matriz_Adj(numVertices);
        Lista_Adj grafoLista = new Lista_Adj(numVertices);
        
        //Estabelecendo as opções
        int opcao;
        do {
            System.out.println("\nMENU DE CRIAÇÃO:");
            System.out.println("1 - Adicionar Aresta");
            System.out.println("2 - Remover Aresta");
            System.out.println("3 - Rotular Vértice");
            System.out.println("4 - Exibir Grafo (Matriz de Adjacência)");
            System.out.println("5 - Exibir Grafo (Lista de Adjacência)");
            System.out.println("6 - Prosseguir Ao Menu de Checagem");
            System.out.println("0 - Encerrar o Programa");
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            int origem;
            int destino;
            int peso;

            //Ações com base na opção selecionada
            switch (opcao) {
                case 1: //Adicionar aresta
                    switch (ponderararestas) {
                        case 1:
                            System.out.print("Origem da aresta: ");
                            origem = scanner.nextInt();
                            System.out.print("Destino da aresta: ");
                            destino = scanner.nextInt();
                            System.out.print("Peso da aresta: ");
                            peso = scanner.nextInt();
                            grafoMatriz.adicionarAresta(origem, destino, peso);
                            grafoLista.adicionarAresta(origem, destino, peso);
                            break;
                            
                        case 2:
                            System.out.print("Origem da aresta: ");
                            origem = scanner.nextInt();
                            System.out.print("Destino da aresta: ");
                            destino = scanner.nextInt();
                            grafoMatriz.adicionarAresta(origem, destino, 1);
                            grafoLista.adicionarAresta(origem, destino, 1);
                            break;
                            
                        default:
                            System.out.print("Opção inválida");
                            break;
                    }
                break;
                    
                case 2: //Remover aresta
                    System.out.print("Origem da aresta: ");
                    origem = scanner.nextInt();
                    System.out.print("Destino da aresta: ");
                    destino = scanner.nextInt();
                    grafoMatriz.removerAresta(origem, destino);
                    grafoLista.removerAresta(origem, destino);
                    break;
                    
                case 3: //Rotular Vértice
                    System.out.print("Vértice a rotular: ");
                    int vertice = scanner.nextInt();
                    System.out.print("Rótulo do vértice: ");
                    String rotulo = scanner.next();
                    grafoMatriz.rotularVertice(vertice, rotulo);
                    grafoLista.rotularVertice(vertice, rotulo);
                    break;
                    
                case 4: //Exibe a Matriz Adjacência
                    System.out.println("Grafo representado por Matriz de Adjacência:");
                    grafoMatriz.exibirGrafo();
                    break;
                    
                case 5: //Exibe a Lista de Adjacência
                    System.out.println("Grafo representado por Lista de Adjacência:");
                    grafoLista.exibirGrafo();
                    break;
                    
                case 6: //Exibe o menu de checagem
                	Menu_Checagem.MenuChecagem(scanner, grafoLista, grafoMatriz);
                	break;
                    
                case 0: //Encerra o programa
                    System.out.println("Programa Encerrado!");
                    break;
                    
                default: //Opção inválida
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        
        scanner.close();
    }
}
