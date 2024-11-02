package tp_grafos;
import java.util.Scanner;

public class Menu {	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	    int opcao;
	    //Criação do menu de opções
	    do {
	        System.out.println("\nCHECAGEM DE: \n");
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
	        System.out.println("\n0 - VOLTAR\n");
	        System.out.print("Escolha uma opção: ");
	        opcao = scanner.nextInt();
	        //Chamamento de funções e procedimentos de acordo com a opção selecionada
	        switch (opcao) {
	            case 1:
	                System.out.println("Você escolheu a Opção 1.");
	                break;
	            case 2:
	                System.out.println("Você escolheu a Opção 2.");
	                break;
	            case 3:
	                System.out.println("Você escolheu a Opção 3.");
	                break;
	            case 4:
	                System.out.println("Você escolheu a Opção 4.");
	                break;
	            case 5:
	                System.out.println("Você escolheu a Opção 5.");
	                break;
	            case 6:
	                System.out.println("Você escolheu a Opção 6.");
	                break;
	            case 7:
	                System.out.println("Você escolheu a Opção 7.");
	                break;
	            case 8:
	                System.out.println("Você escolheu a Opção 8.");
	                break;
	            case 9:
	                System.out.println("Você escolheu a Opção 9.");
	                break;
	            case 10:
	                System.out.println("Você escolheu a Opção 10.");
	                break;
	            case 11:
	                System.out.println("Você escolheu a Opção 11.");
	                break;
	            case 12:
	                System.out.println("Você escolheu a Opção 12.");
	                break;
	            case 0:
	                System.out.println("Saindo do menu...");
	                break;
	            default:
	                System.out.println("Opção inválida. Tente novamente.");
	        }
	    } while (opcao != 0);
	    scanner.close();
	}
}


