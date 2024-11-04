package tp_grafos;

class Matriz_Adj {
	//Declaração de variáveis
    private int[][] matriz;
    private String[] rotulos;
    
    //Atribuição de valores - Construtor padrão
    public Matriz_Adj(int numVertices) {
        matriz = new int[numVertices][numVertices];
        rotulos = new String[numVertices];
    }
    
    //Construção da matriz
    public void rotularVertice(int vertice, String rotulo) {
        rotulos[vertice] = rotulo;
    }
    public void adicionarAresta(int origem, int destino, int peso) {
        matriz[origem][destino] = peso;
        matriz[destino][origem] = peso; // Se for um grafo não-direcionado
    }
    public void removerAresta(int origem, int destino) {
        matriz[origem][destino] = 0;
        matriz[destino][origem] = 0; // Se for um grafo não-direcionado
    }

    //Exibição da matriz
    public void exibirGrafo() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
