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

    //Método para calcular o grau do vértice
    public int calcularGrauVertice(int vertice) {
    int grau = 0;
    for (int i = 0; i < matriz.length; i++) {
        if (matriz[vertice][i] != 0) {
            grau++;
        }
    }
    return grau;
    }
    
    //Método para ver se o grafo é completo
    public boolean ehCompleto() {
    int n = matriz.length; //Número de vértices no grafo

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            //Em um grafo completo, a diagonal principal deve ser 0 e o restante deve ser 1
            if (i != j && matriz[i][j] != 1) {
                return false;
            } else if (i == j && matriz[i][j] != 0) {
                return false;
            }
        }
    }
    return true;
    }

    public boolean verificarConexidadeMatriz() {
        int numVertices = matriz.length;
        boolean[] visitado = new boolean[numVertices];
        
        // Realizar a busca em profundidade a partir do primeiro vértice
        realizarBP_Matriz(0, visitado);
        
        // Verificar se todos os vértices foram visitados
        for (boolean foiVisitado : visitado) {
            if (!foiVisitado) {
                return false; // Pelo menos um vértice não foi alcançado
            }
        }
        return true; // Todos os vértices foram alcançados
    }
    
    // Método auxiliar de BP para matriz de adjacência
    private void realizarBP_Matriz(int vertice, boolean[] visitado) {
        visitado[vertice] = true;
        
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[vertice][i] != 0 && !visitado[i]) { // Existe uma aresta e ainda não foi visitado
                realizarBP_Matriz(i, visitado);
            }
        }
    }
    
    // Método para verificar se o grafo é regular
    public boolean ehRegular() {
    int grauPadrao = calcularGrauVertice(0); // Grau do primeiro vértice como referência

    for (int i = 1; i < matriz.length; i++) {
        if (calcularGrauVertice(i) != grauPadrao) {
            return false; // Se algum vértice tiver grau diferente, o grafo não é regular
        }
    }
    return true; // Todos os vértices têm o mesmo grau
    }
        public void floydWarshall() {
            int numVertices = matriz.length;
            int[][] dist = new int[numVertices][numVertices];

            // Inicializar a matriz de distâncias com os pesos das arestas ou infinito
            for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    dist[i][j] = 0; // Distância de um vértice para ele mesmo é 0
                } else if (matriz[i][j] != 0) {
                    dist[i][j] = matriz[i][j]; // Peso da aresta
                } else {
                    dist[i][j] = Integer.MAX_VALUE; // Infinito (não há aresta)
                }
            }
            }

        // Algoritmo de Floyd-Warshall
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    // Evitar soma com infinito para evitar overflow
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE 
                        && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Exibir a matriz de distâncias
        System.out.println("Matriz de menores distâncias (Floyd-Warshall):");
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (dist[i][j] == Integer.MAX_VALUE) {
                        System.out.print("INF "); // Imprimir infinito
                    } else {
                    System.out.print(dist[i][j] + " ");
                    }
                }
                System.out.println();
            }
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

