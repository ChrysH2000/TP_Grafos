package tp_grafos;
import java.util.Scanner;

public class Menu_Checagem {	
	 public static void MenuChecagem(Scanner scanner, Lista_Adj grafoLista, Matriz_Adj grafoMatriz) { //Metodo a ser chamado no menu de criação
	    int opcao;
	    
	    //Criação do menu de opções
	    do {
	        System.out.println("\nMENU DE CHECAGEM:");
	        System.out.println("1 - Adjacência entre Vértices");
	        System.out.println("2 - Vizinhança do Vértice");
	        System.out.println("3 - Grau do Vértice");
	        System.out.println("4 - Grafo Completo");
	        System.out.println("5 - Grafo Regular");
	        System.out.println("6 - Grafo Conexo");
	        System.out.println("7 - Grafo Acíclico");
	        System.out.println("8 - Grafo Euleriano");
	        System.out.println("9 - Busca em Profundidade");
	        System.out.println("10- Busca em Largura");
	        System.out.println("11- Menor Distância de Uma Origem Para Todos os outros Vértices");
	        System.out.println("12- Menor Distância de Todos Para Todos");
	        System.out.println("0 - Voltar ao Menu de Criação\n");
	        System.out.print("Escolha uma opção: ");
	        opcao = scanner.nextInt();
	        
	        //Chamamento de funções e procedimentos de acordo com a opção selecionada
	        switch (opcao) {
	            case 1: //Adjacênia entre Vértices
	                System.out.println("Adjacênia entre Vértices");
                    // Solicitar vértices ao usuário
                    System.out.print("Digite o primeiro vértice: ");
                    int vertice1 = scanner.nextInt();
                    System.out.print("Digite o segundo vértice: ");
                    int vertice2 = scanner.nextInt();
					boolean resultado = grafoLista.verificarAdjacencia(vertice1, vertice2);
                    if (resultado) {
                        System.out.println("Os vértices " + vertice1 + " e " + vertice2 + " são adjacentes.");
                    } else {
                        System.out.println("Os vértices " + vertice1 + " e " + vertice2 + " não são adjacentes.");
                    }
                    break;
	                
	            case 2: //Vizinhança do vértice
	                System.out.println("Vizinhança do vértice");
					System.out.print("Digite o vértice que você deseja saber a vizinhança: ");
                    int vizinhancavert = scanner.nextInt();
					grafoLista.mostrarVizinhança(vizinhancavert);
	                break;
	                
	            case 3: //Grau do vértice
	                System.out.println("Grau do vértice");
				System.out.print("Digite o vértice que você deseja saber o grau: ");
				int verticeGrau = scanner.nextInt();

				System.out.println("Escolha a representação do grafo para cálculo do grau:");
				System.out.println("1 - Lista de Adjacência");
				System.out.println("2 - Matriz de Adjacência");
				int escolhaRepresentacao = scanner.nextInt();

				if (escolhaRepresentacao == 1) {
					int grauLista = grafoLista.calcularGrauVertice(verticeGrau);
					System.out.println("O grau do vértice " + verticeGrau + " na Lista de Adjacência é: " + grauLista);
				} else if (escolhaRepresentacao == 2) {
					int grauMatriz = grafoMatriz.calcularGrauVertice(verticeGrau);
					System.out.println("O grau do vértice " + verticeGrau + " na Matriz de Adjacência é: " + grauMatriz);
				} else {
					System.out.println("Opção inválida.");
				}
	                break;
	                
	            case 4: //Grafo completo
	                System.out.println("Grafo completo");
	                break;
	                
	            case 5: //Grafo regular
	                System.out.println("Grafo regular");
	                break;
	                
	            case 6: //Grafo conexo
	                System.out.println("Grafo conexo");
	                break;
	                
	            case 7: //Grafo acíclico
	                System.out.println("Grafo acíclico");
	                break;
	                
	            case 8: //Grafo euleriano
	                System.out.println("Grafo euleriano");
	                break;
	                
	            case 9: //Busca em Profundidade
	                System.out.println("Busca em Profundidade");
	                break;
	                
	            case 10: //Busca em Largura
	                System.out.println("Busca em Largura");
	                break;
	                
	            case 11: //Menor distancia origem x outros vértices
	                System.out.println("Menor distancia origem x outros vértices");
	                break;
	                
	            case 12: //Menor distancia todos x todos
	                System.out.println("Menor distancia todos x todos");
	                break;
	                
	            case 0: //Volta para o menu de criação de grafos
	                System.out.println("Voltando ao menu de criação...");
	                break;
	                
	            default: //Opção inválida
	                System.out.println("Opção inválida. Tente novamente.\n");
	        }
	    } while (opcao != 0);
	}
}


