package tp_grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Declaração de variáveis
class Lista_Adj {
    private Map<Integer, List<Aresta>> listaAdjacencia = new HashMap<>();
    private String[] rotulos;
    private int[] TD;  //Tempo de descoberta
    private int[] TT;  //Tempo de término
    private Integer[] pai;  //Predecessor ou pai
    private int tempo;  //Tempo global

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

    /*
    //Busca em Largura
    public void blListaAdj() {

    // Inicialização
    for (int i = 0; i < numVertices; i++) {
        L[i] = 0;
        nivel[i] = 0;
        pai[i] = null;
    }

    // Percorre todos os vértices não visitados
    for (int v = 0; v < numVertices; v++) {
        if (L[v] == 0) {  // Encontrou uma nova raiz
            t++;
            L[v] = t;
            fila[fimFila++] = v;  // Insere a raiz na fila

            // Executa a busca em largura a partir da raiz `v`
            while (inicioFila != fimFila) {
                int verticeAtual = fila[inicioFila++]; // Remove o primeiro da fila

                // Explora a vizinhança de `verticeAtual` usando a lista de adjacência
                for (int w : listaAdjacencia[verticeAtual]) {
                    if (L[w] == 0) { // Se o vértice `w` é visitado pela primeira vez
                        System.out.println("Visitando aresta pai {" + rotulos[verticeAtual] + ", " + rotulos[w] + "}");
                        pai[w] = verticeAtual;
                        nivel[w] = nivel[verticeAtual] + 1;
                        t++;
                        L[w] = t;
                        fila[fimFila++] = w; // Insere `w` na fila

                    } else if (nivel[w] == nivel[verticeAtual] + 1) {
                        System.out.println("Visitando aresta tio {" + rotulos[verticeAtual] + ", " + rotulos[w] + "}");

                    } else if (nivel[w] == nivel[verticeAtual] && pai[verticeAtual] == pai[w] && L[w] > L[verticeAtual]) {
                        System.out.println("Visitando aresta irmão {" + rotulos[verticeAtual] + ", " + rotulos[w] + "}");

                    } else if (nivel[w] == nivel[verticeAtual] && pai[verticeAtual] != pai[w] && L[w] > L[verticeAtual]) {
                        System.out.println("Visitando aresta primo {" + rotulos[verticeAtual] + ", " + rotulos[w] + "}");
                    }
                }
            }
        }
    }
}
*/
}
   

