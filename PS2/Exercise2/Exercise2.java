package Exercise2;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Mateo
 */
public class Exercise2 {

    public static int[] squareOfPlots(int[][] array) {
        int[] outArray = new int[array.length - 1];
        int square = 0;
        for (int i = 0; i < outArray.length; i++) {
            square = (array[i][0] - array[i][2]) * (array[i][1] - array[i][3]);
            outArray[i] = square;
        }
        return outArray;
    }

    public static void swapRows(int[][] array, int rowA, int rowB) {
        int[] tmpRow = array[rowA];
        array[rowA] = array[rowB];
        array[rowB] = tmpRow;
    }

    public static void displayMenu() {
        System.out.println("*************                 MENU               ************");
        System.out.println("*************1-Wczytaj dane z pliku              ************");
        System.out.println("*************2-Oblicz                            ************");
        System.out.println("*************3-Testuj                            ************");
        System.out.println("*************4-Zakoncz program                   ************");
    }

    public static int partition(int c[], int a, int b) {
        int e, tmp;
        e = c[a];        //elemennt dzielacy
        while (a < b) {
            while ((a < b) && (c[b] >= e)) b--;
            while ((a < b) && (c[a] < e)) a++;
            if (a < b) {
                tmp = c[a];
                c[a] = c[b];
                c[b] = tmp;
            }
        }
        return a;
    }

    public static int findKthElement(List<Integer> a, int k) {
        if (a.size() < 10) {
            Collections.sort(a);
            return a.get(k);
        }
        ArrayList<Integer> medians = new ArrayList<Integer>();
        for (int i = 0; i < a.size() - a.size() % 5; i = i + 5)
            medians.add(getMedian(a.subList(i, i + 5)));
        int v = getMedian(medians);

        ArrayList<Integer> left = getPartition(a, v, true);
        ArrayList<Integer> right = getPartition(a, v, false);

        return (left.size() + 1 == k) ? v : (left.size() > k) ? findKthElement(
                left, k) : findKthElement(right, k - left.size());
    }

    public static int getMedian(List<Integer> a) {
        Collections.sort(a);
        return a.get(a.size() / 2);
    }

    public static ArrayList<Integer> getPartition(List<Integer> a, int v,
                                           boolean isLessThan) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int val : a)
            if (isLessThan && val < v)
                res.add(val);
            else if (!isLessThan && val >= v)
                res.add(val);
        return res;
    }


    /**
     * @param array
     * @return
     */
    public static int algorytm1(int[][] array) throws FileNotFoundException {
        int licznik = 0, znalezione = 0, liczba = 0, x = array[0].length;
        for (int i = 0; i < x; i++) {
            if (znalezione != 4) {
                liczba = array[0][i];
                znalezione = 0;
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < array[j].length; k++) {
                        if (array[j][k] > liczba) {
                            licznik++;
                            break;
                        }
                        if (array[j][k] == liczba) {
                            licznik++;
                            znalezione++;
                            break;
                        }
                        if (array[j][k] < liczba) {
                            licznik++;
                        }
                    }
                }
            } else {
                System.out.println("Znaleziona liczba to:" + liczba);
                return licznik;
            }
        }
        System.out.println("Znaleziona liczba to:" + liczba);
        return licznik;
    }

    public static int algorytm2(int[][] tab) throws FileNotFoundException {
        int znalezione = 0, licznik = 0, x = tab[0].length, indeks = 0, liczba = -1;
        for (int i = 1; i < 5; i++) {
            if (x > tab[i].length) {
                x = tab[i].length;
                indeks = i;
                licznik++;
            }
        }
        swapRows(tab, indeks, 4);
        for (int i = 0; i < x; i++) {
            if (znalezione != 4) {
                liczba = tab[4][i];
                znalezione = 0;
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < tab[j].length; k++) {
                        if (tab[j][k] > liczba) {
                            licznik++;
                            break;
                        }
                        if (tab[j][k] == liczba) {
                            licznik++;
                            znalezione++;
                            break;
                        }
                        if (tab[j][k] < liczba) {
                            licznik++;
                        }
                    }
                }
            } else {
                // System.out.println("Znaleziona liczba to:" + liczba);
                swapRows(tab, indeks, 4);

                return licznik;
            }
        }
        if (znalezione != 4) {
            licznik = 0;
            swapRows(tab, indeks, 4);

            return licznik;
        }
        System.out.println("Znaleziona liczba to:" + liczba);
        swapRows(tab, indeks, 4);

        return licznik;
    }

    public static int algorytm3(int[][] array) throws FileNotFoundException {
        int licznik = 0, i = 0, j = array.length - 2, n = array[array.length - 1][0], k, w = n;
        int[] listOfSqaures = squareOfPlots(array);
        PrintWriter zapis = new PrintWriter("c:/Users/matow/Dev/Algorithms-and-Datastructure/PS2/Exercise2/out2.txt");

        while (i != j) {
            k = partition(listOfSqaures, i, j);
            k = k - i + 1;
            licznik++;
            if (k >= w) j = i + k - 1;
            if (k < w) {
                w -= k;
                i += k;
            }
        }

        zapis.println(Arrays.toString(listOfSqaures));
        zapis.println(listOfSqaures[i] + " " + Arrays.toString(array[i]));
        zapis.close();

        return licznik;
    }


    public static int algorytm4(int[][] array) {
        int licznik = 0;
        int[] listOfSqaures = squareOfPlots(array);
        List<Integer> list = new ArrayList<Integer>(listOfSqaures.length);
        for (int i = 0; i < listOfSqaures.length; i++) list.add(Integer.valueOf(listOfSqaures[i]));
        System.out.println("Rozwiazanie 4 algorytmu" + findKthElement(list,3));
        return 0;
    }

    public static int[][] wczytaj() throws FileNotFoundException {
        String nazwaPliku = "c:/Users/matow/Dev/Algorithms-and-Datastructure/PS2/Exercise2/data.txt";
        int[][] tab = null;
        int liczba;
        BufferedReader br = new BufferedReader(new FileReader(nazwaPliku));
        String linia = null;
        String[] podzielonaLinia = null;
        try {
            for (int i = 0; (linia = br.readLine()) != null; i++) {
                podzielonaLinia = linia.split(" ");
                for (int j = 0; j < podzielonaLinia.length; j++) {
                    liczba = Integer.parseInt(podzielonaLinia[j].trim());
                    if (i == 0) {
                        tab = new int[liczba + 1][4];
                        tab[i][j] = liczba;
                    }
                    if (i == 1) {
                        tab[i - 1][j] = liczba;
                    }
                    if (i >= 2) {
                        tab[i - 1][j] = liczba;
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Wystapil blad przy wczytywaniu danych");
        }
        return tab;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int[][] tab = null;
        int x, l1 = 0, l2 = 0, l3 = 0, l4 = 0;
        Scanner s = new Scanner(System.in);
        // System.out.println(Arrays.deepToString(tab));
        while (true) {
            displayMenu();
            x = s.nextInt();
            switch (x) {
                case 1:
                    tab = wczytaj();
                    System.out.println(Arrays.deepToString(tab));
                    break;
                case 2:
                    System.out.println("Tablica pól  " + Arrays.toString(squareOfPlots(tab)));
                    l1 = algorytm1(tab);
//                    l2 = algorytm2(tab);
                    l3 = algorytm3(tab);
                    l4 = algorytm4(tab);
                    break;
                case 3:
                    System.out.println("licznik operacji w algorytmie 1 = " + l1 + "\nlicznik operacji w algorytmie 2 = " +
                            "" + l2 + "\nlicznik operacji w algorytmie 3 = " + l3);
                    break;
                case 4:
                    return;
            }
        }
    }
}
