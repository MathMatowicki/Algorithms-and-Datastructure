import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Exercise3 {

    public static int[][] load() throws FileNotFoundException {
        String nazwaPliku = "C:/Users/Jan/Desktop/Dev/Algorithms-and-Datastructure/PS5/data3.txt";
        int[][] tab = null;
        BufferedReader br = new BufferedReader(new FileReader(nazwaPliku));
        String line = null;
        String[] trimLine = null;
        try {
            line = br.readLine();
            trimLine = line.split(" ");
            tab = new int[Integer.parseInt((trimLine[0].trim()))][Integer.parseInt((trimLine[0].trim()))];
            for (int i = 1; (line = br.readLine()) != null; i++) {
                trimLine = line.split(" ");
                tab[Integer.parseInt(trimLine[0]) - 1][Integer.parseInt(trimLine[1]) - 1] = Integer.parseInt(trimLine[2]);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Wystapil blad przy wczytywaniu danych");
        }
        return tab;
    }

    public static int[][] normalizeMatrix(int a[][]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                int temp = a[i][j];
                if (temp != 0) {
                    a[j][i] = a[i][j];
                }
            }
        }
        return a;
    }

    public static int minDistance(int path_array[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < path_array.length; v++)
            if (sptSet[v] == false && path_array[v] <= min) {
                min = path_array[v];
                min_index = v;
            }
        return min_index;
    }

    // Implementation of Dijkstra's algorithm for graph (adjacency matrix)
    public static int[] algo_dijkstra(int graph[][], int src_node) {
        int path_array[] = new int[graph.length]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // spt (shortest path set) contains vertices that have shortest path
        Boolean sptSet[] = new Boolean[graph.length];

        // Initially all the distances are INFINITE and stpSet[] is set to false
        for (int i = 0; i < graph.length; i++) {
            path_array[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Path between vertex and itself is always 0
        path_array[src_node] = 0;
        // now find shortest path for all vertices
        for (int count = 0; count < graph.length - 1; count++) {
            // call minDistance method to find the vertex with min distance
            int u = minDistance(path_array, sptSet);
            // the current vertex u is processed
            sptSet[u] = true;
            // process adjacent nodes of the current vertex
            for (int v = 0; v < graph.length; v++)

                // if vertex v not in sptset then update it
                if (!sptSet[v] && graph[u][v] != 0 && path_array[u] !=
                        Integer.MAX_VALUE && path_array[u]
                        + graph[u][v] < path_array[v])
                    path_array[v] = path_array[u] + graph[u][v];
        }
        return path_array;
    }

    public static int[][] transpose(int[][] original) {
        int[][] transposedArray = original;
        for (int i = 0; i < original[0].length; i++) {
            for (int j = 0; j < original.length; j++) {
                transposedArray[j][i] = original[i][j];
            }
        }
        System.out.println(Arrays.deepToString(transposedArray));
        return transposedArray;
    }

    public static void main(String[] args) {

        int arr[][] = null;
        try {
            arr = load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        arr = normalizeMatrix(arr);
        int arrDijkstra[][] = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrDijkstra[i] = algo_dijkstra(arr, i);
        }
        int min = Integer.MAX_VALUE, cost = 0, costi = 0, costj = 0;
        List<String> nodesVisited = new ArrayList<>();
        System.out.println(Arrays.deepToString(arrDijkstra));

        List<List<Integer>> listDijkstra = new ArrayList<>();

        for (int[] ints : arrDijkstra) {
            List<Integer> list = new ArrayList<>();
            for (int i : ints) {
                list.add(i);
            }
            listDijkstra.add(list);
        }

//        for (int i = 0; i < arrDijkstra.length; i = costj) {
//            for (int j = 0; j < arrDijkstra[i].length; j++) {
//                if (arrDijkstra[i][j] > 0 && nodesVisited.size() != arrDijkstra.length - 1) {
//                    if (min > arrDijkstra[i][j]) {
//                        min = arrDijkstra[i][j];
//                        costi = i;
//                        costj = j;
//                    }
//                }
//            }
//            if (nodesVisited.size() == arrDijkstra.length - 1) break;
//            cost += min;
//            System.out.println(costi + "  " + costj + "cost: " + cost);
//            nodesVisited.add(costi + "  " + costj + "cost: " + cost);
//            min = Integer.MAX_VALUE;
//        }
//
//        System.out.println(listDijkstra.toString());
//        System.out.println("Drugi");

        try {
            PrintWriter zapis = new PrintWriter("C:/Users/Jan/Desktop/Dev/Algorithms-and-Datastructure/PS5/out3.txt");

            costi = 0;
            costj = 0;
            min = Integer.MAX_VALUE;
            nodesVisited.clear();
            cost = 0;
            boolean flag = false;
            for (int i = 0; i < listDijkstra.size(); i = costj) {
                for (int j = 0; j < listDijkstra.get(i).size(); j++) {
                    if (listDijkstra.get(i).get(j) > 0 && nodesVisited.size() != arrDijkstra.length - 1) {
                        if (listDijkstra.get(i).get(j) < min) {
                            min = listDijkstra.get(i).get(j);
                            costi = i;
                            costj = j;
                            for (int k = 0; k < arr.length; k++) {
                                if (listDijkstra.get(k).get(j) < min && listDijkstra.get(k).get(j) > 0) {
                                    min = listDijkstra.get(k).get(j);
                                    costi = k;
                                    costj = j;
                                }
                            }
                        }
                    } else if (nodesVisited.size() == arrDijkstra.length - 1) {
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
                cost += min;
                zapis.print(costi + 1);
                zapis.print(" ");
                zapis.print(costj + 1 + "\n");
                System.out.print(costi + 1);
                System.out.print(" ");
                System.out.print(costj + 1 + "\n");
                nodesVisited.add(costi + 1 + "  " + costj + 1 + " cost: " + cost);
                min = Integer.MAX_VALUE;
                for (int j = 0; j < arrDijkstra.length; j++) {
                    listDijkstra.get(j).set(costi, Integer.MAX_VALUE);
                    listDijkstra.get(j).set(costj, Integer.MAX_VALUE);
                }
            }
            System.out.println("cost: " + cost);
            zapis.println("cost: " + cost);
            zapis.println("tunnels: " + nodesVisited.size());
            zapis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
