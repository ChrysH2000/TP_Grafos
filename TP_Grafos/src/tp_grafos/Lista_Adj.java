package tp_grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Queue;

//Declaração de variáveis
class Lista_Adj {
    private Map<Integer, List<Aresta>> listaAdjacencia = new HashMap<>();
    private String[] rotulos;
    private int[] TD;  //Tempo de descoberta
    private int[] TT;  //Tempo de término
    private Integer[] pai;  //Predecessor ou pai
    private int tempo;  //Tempo global
    
    // Metodo construtor das arestas
    private static class Aresta {
        int destino;
        int peso;
        public Aresta(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }
    
    //Atribuição de valores - Construtor padrão
    public Lista_Adj (int numVertices) {
        rotulos = new String[numVertices];
        for (int i = 0; i < numVertices; i++) {
            listaAdjacencia.put(i, new ArrayList<>());
        }
        TD = new int[numVertices];
        TT = new int[numVertices];
        pai = new Integer[numVertices];
        tempo = 0;
    }

    //Construção da lista
    public void rotularVertice(int vertice, String rotulo) {
        rotulos[vertice] = rotulo;
    }
    public void adicionarAresta(int origem, int destino, int peso) {
        listaAdjacencia.get(origem).add(new Aresta(destino, peso));
        listaAdjacencia.get(destino).add(new Aresta(origem, peso)); // Para grafos não-direcionados
    }
    public void removerAresta(int origem, int destino) {
        listaAdjacencia.get(origem).removeIf(aresta -> aresta.destino == destino);
        listaAdjacencia.get(destino).removeIf(aresta -> aresta.destino == origem);
    }

    //Exibição da lista
    public void exibirGrafo() {
        for (int vertice : listaAdjacencia.keySet()) {
            System.out.print("Vértice " + vertice + " (" + rotulos[vertice] + "): ");
            for (Aresta aresta : listaAdjacencia.get(vertice)) {
                System.out.print(" -> " + aresta.destino + "(Peso: " + aresta.peso + ")");
            }
            System.out.println();
        }
    }
    // Implementação do método verificarAdjacencia
    public boolean verificarAdjacencia(int vertice1, int vertice2) {
        // Verifica se o vértice2 está na lista de adjacências do vértice1
        for (Aresta aresta : listaAdjacencia.get(vertice1)) {
            if (aresta.destino == vertice2) {
                return true;  // Vértices adjacentes
            }
        }
        return false;  // Vértices não adjacentes
    }

    // Método para mostrar a vizinhança de um vértice
    public void mostrarVizinhança(int vertice) {
        System.out.print("Vizinhança do vértice " + vertice + " (" + rotulos[vertice] + "): ");
        boolean temVizinhos = false;
        for (Aresta aresta : listaAdjacencia.get(vertice)) {
            System.out.print(aresta.destino + " ");
            temVizinhos = true;
        }
        if (!temVizinhos) {
            System.out.print("Nenhum vizinho.");
        }
        System.out.println();
    }
    //Método para calcular o grau do vértice
    public int calcularGrauVertice(int vertice) {
        if (!listaAdjacencia.containsKey(vertice)) {
            System.out.println("Vértice não encontrado.");
            return -1;
        }
        return listaAdjacencia.get(vertice).size();
    }

    //Método para verificar se o grafo é completo
    public boolean ehCompleto() {
    int n = listaAdjacencia.size(); //Número de vértices no grafo

    for (int vertice : listaAdjacencia.keySet()) {
        //Verifica se o número de vizinhos de cada vértice é igual a n - 1
        if (listaAdjacencia.get(vertice).size() != n - 1) {
            return false;
        }
    }
    return true;
}
    //Método de Busca em profundidade
    // Inicializar e executar BP para todos os vértices ainda não visitados
    void executarBP() {
         tempo = 0;  // Reset do tempo global
        for (int v : listaAdjacencia.keySet()) {
            if (TD[v] == 0) {
                bp(v);
            }
        }
    }
    // Algoritmo simplificado de Busca em Profundidade (BP)
    public void bp(int v) {
        TD[v] = ++tempo;  //Tempo de descoberta

        for (Aresta aresta : listaAdjacencia.get(v)) {
            int w = aresta.destino;
            if (TD[w] == 0) {  //Se w não foi visitado
                pai[w] = v;
                System.out.println("Visitar aresta {" + v + ", " + w + "} - Aresta de árvore");
                bp(w);
            } else if (TT[w] == 0 && w != pai[v]) {  //w é ancestral, mas não o pai
                System.out.println("Visitar aresta {" + v + ", " + w + "} - Aresta de retorno");
            }
        }

        TT[v] = ++tempo;  //Tempo de término
    }

    // Método para verificar se o grafo é Euleriano, Semi-Euleriano ou Nenhum
    public void verificarTipoEuleriano() {
        // Passo 1: Verificar se o grafo é conexo
        if (!ehConexo()) {
            System.out.println("Não é Euleriano, o grafo não é conexo");
        }

        // Passo 2: Contar vértices de grau ímpar
        int verticesGrauImpar = 0;
        for (int vertice : listaAdjacencia.keySet()) {
            if (calcularGrauVertice(vertice) % 2 != 0) {
                verticesGrauImpar++;
            }
        }

        // Passo 3: Classificar o grafo
        if (verticesGrauImpar == 0) {
            System.out.println("Grafo Euleriano");
        } else if (verticesGrauImpar == 2) {
            System.out.println("Grafo Semi-Euleriano");
        } else {
            System.out.println("Não é Euleriano, o grafo possui mais de dois vértices com grau impar");
        }
    }
    // Método auxiliar para verificar se o grafo é conexo
    private boolean ehConexo() {
        // Realizar uma busca em profundidade (BP) a partir de um vértice qualquer
        boolean[] visitado = new boolean[listaAdjacencia.size()];
        int verticeInicial = listaAdjacencia.keySet().iterator().next(); // Pega um vértice arbitrário
        realizarBP(verticeInicial, visitado);

        // Verificar se todos os vértices foram visitados
        for (int vertice : listaAdjacencia.keySet()) {
            if (!visitado[vertice]) {
                return false; // Pelo menos um vértice não foi alcançado
            }
        }
        return true;
    }

    // Método auxiliar para busca em profundidade (BP)
    private void realizarBP(int vertice, boolean[] visitado) {
        visitado[vertice] = true;
        for (Aresta aresta : listaAdjacencia.get(vertice)) {
            if (!visitado[aresta.destino]) {
                realizarBP(aresta.destino, visitado);
            }
        }
    }

    // Método para realizar a busca de menor caminho da origem para todos Dijkstra
    public void dijkstra(int origem) {
        int numVertices = listaAdjacencia.size();
        int[] distancias = new int[numVertices]; // Array de distâncias
        boolean[] visitados = new boolean[numVertices]; // Array de visitados
    
        // Inicializar distâncias com infinito e visitados como false
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[origem] = 0; // Distância da origem para ela mesma é 0
    
        // Fila de prioridade para selecionar o próximo vértice com a menor distância
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{origem, 0}); // {vértice, distância}
    
        while (!pq.isEmpty()) {
            int[] atual = pq.poll();
            int verticeAtual = atual[0];
            int distanciaAtual = atual[1];
    
            if (visitados[verticeAtual]) continue;
            visitados[verticeAtual] = true;
    
            // Iterar sobre os vizinhos do vértice atual
            for (Aresta aresta : listaAdjacencia.get(verticeAtual)) {
                int vizinho = aresta.destino;
                int peso = aresta.peso;
    
                if (!visitados[vizinho] && distancias[verticeAtual] + peso < distancias[vizinho]) {
                    distancias[vizinho] = distancias[verticeAtual] + peso;
                    pq.add(new int[]{vizinho, distancias[vizinho]});
                }
            }
        }
    
        // Mostrar as distâncias na tela
        System.out.println("Distâncias a partir do vértice " + origem + ":");
        for (int i = 0; i < distancias.length; i++) {
            if (distancias[i] == Integer.MAX_VALUE) {
                System.out.println("Vértice " + i + ": Inacessível");
            } else {
                System.out.println("Vértice " + i + ": " + distancias[i]);
            }
        }
    }
    
    //Busca em Largura
    public void bl(int verticeInicial) {
    int numVertices = listaAdjacencia.size();
    int[] nivel = new int[numVertices];
    Integer[] pai = new Integer[numVertices];
    int[] L = new int[numVertices];  // Marcadores de tempo ou ordem de descoberta
    boolean[] visitado = new boolean[numVertices];
    int t = 1;

    Queue<Integer> fila = new LinkedList<>();
    L[verticeInicial] = t++;
    nivel[verticeInicial] = 0;
    fila.add(verticeInicial);

    System.out.println("Iniciando a Busca em Largura a partir do vértice " + verticeInicial + ":");

    while (!fila.isEmpty()) {
        int verticeAtual = fila.remove();  // Remove o primeiro elemento da fila
        System.out.println("Vértice atual: " + verticeAtual + " (Nível: " + nivel[verticeAtual] + ")");

        for (Aresta aresta : listaAdjacencia.get(verticeAtual)) {
            int vizinho = aresta.destino;

            if (L[vizinho] == 0) {  // Primeira vez que visitamos o vértice
                pai[vizinho] = verticeAtual;
                nivel[vizinho] = nivel[verticeAtual] + 1;
                L[vizinho] = t++;
                fila.add(vizinho);
                System.out.println("Visitando aresta pai {" + verticeAtual + ", " + vizinho + "} (Nível: " + nivel[vizinho] + ")");
            
            } else if (nivel[vizinho] == nivel[verticeAtual] + 1) {
                System.out.println("Visitando aresta tio {" + verticeAtual + ", " + vizinho + "}");
                
            } else if (nivel[vizinho] == nivel[verticeAtual] && pai[verticeAtual] == pai[vizinho] && L[vizinho] > L[verticeAtual]) {
                System.out.println("Visitando aresta irmão {" + verticeAtual + ", " + vizinho + "}");
                
            } else if (nivel[vizinho] == nivel[verticeAtual] && pai[verticeAtual] != pai[vizinho] && L[vizinho] > L[verticeAtual]) {
                System.out.println("Visitando aresta primo {" + verticeAtual + ", " + vizinho + "}");
            }
        }
    }
}
}
   

