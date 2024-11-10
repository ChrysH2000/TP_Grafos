package tp_grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Declaração de variáveis
class Lista_Adj {
    private Map<Integer, List<Aresta>> listaAdjacencia = new HashMap<>();
    private String[] rotulos;
    
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

}