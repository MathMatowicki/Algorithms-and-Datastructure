
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
import java.util.Scanner;

/**
 *
 * @author Mateo
 */
public class Exercise2 {

    public static void swapRows(int array[][], int rowA, int rowB) {
        int tmpRow[] = array[rowA];
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

    /**
     *
     * @param tab
     * @return
     */
    public static int algorytm1(int[][] tab) {
        int licznik = 0, znalezione = 0, liczba = 0, x = tab[0].length;
        for (int i = 0; i < x; i++) {
            if (znalezione != 4) {
                liczba = tab[0][i];
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
                System.out.println("Znaleziona liczba to:" + liczba);
                return licznik;
            }
        }
        return licznik;
    }

    public static int algorytm2(int tab[][]) throws FileNotFoundException {
        int znalezione = 0, licznik = 0, x = tab[0].length, indeks = 0, liczba = -1;
        PrintWriter zapis = new PrintWriter("out2.txt");
        for (int i = 1; i < 5; i++) {
            if (x > tab[i].length) {
                x = tab[i].length;
                indeks = i;
                licznik++;
            }
        }
        swapRows(tab, indeks, 4);
        // System.out.println(Arrays.deepToString(tab));
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
                System.out.println("Znaleziona liczba to:" + liczba);
                swapRows(tab, indeks, 4);
                return licznik;
            }
        }
        if (znalezione != 4) {
            licznik = 0;

            zapis.println("BRAK");

            zapis.close();
            swapRows(tab, indeks, 4);
            return licznik;
        }
        System.out.println("Znaleziona liczba to:" + liczba);
        zapis.println(liczba);
        zapis.close();
        swapRows(tab, indeks, 4);
        return licznik;
    }

    public static int[][] wczytaj() throws FileNotFoundException {
        String nazwaPliku = "in2.txt";
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
                    // System.out.println(podzielonaLinia[j]);
                    if (i == 0) {
                        tab = new int[liczba][];
                    }
                    if (i == 1) {
                        tab[j] = new int[liczba];
                    }
                    if (i >= 2) {
                        tab[i - 2][j] = liczba;
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
        int x, l1 = 0, l2 = 0;
        Scanner s = new Scanner(System.in);
        // System.out.println(Arrays.deepToString(tab));
        while (true) {
            displayMenu();
            x = s.nextInt();
            switch (x) {
            case 1:
                tab = wczytaj();
                break;
            case 2:
                l1 = algorytm1(tab);
                l2 = algorytm2(tab);
                break;
            case 3:
                System.out.println(
                        "licznik operacji w algorytmie 1 = " + l1 + "\nlicznik operacji w algorytmie 2 = " + l2);
                break;
            case 4:
                return;
            }
        }
    }
}
