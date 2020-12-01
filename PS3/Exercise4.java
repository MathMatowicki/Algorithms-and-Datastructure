/*
Zaimplementuj algorytm wspomagający proces decyzyjny w fabryce papieru odnośnie cięcia
dużych arkuszy papieru na mniejsze. Wiadomo, że celem zachowania kątów prostych, cięcia
wykonywane są przez maszynę jedynie po liniach poziomych lub pionowych. Odrzut papieru z
dużego arkusza jest wyrażony w postaci kosztów linii cięcia. Należy zminimalizować koszt
pocięcia dużego arkusza na mniejsze . Koszty cięć wzdłuż pionowych linii
oznaczone są jako x1, x2, ..., xm-1, zaś wzdłuż poziomych linii jako y1, y2, ..., yn-1. Całkowity koszt
pocięcia arkusza to suma kosztów wybranych linii cięcia.
Dla przykładowego arkusza koszt jego pocięcia wzdłuż przerywanych linii jest równy:
 y1 + y2 + y3 + 4(x1 + x2 + x3 + x4 + x5)
 */

import java.io.*;
import java.util.*;

public class Exercise4 {


    public static void displayMenu() {
        System.out.println("*************                 MENU               ************");
        System.out.println("*************1-Wczytaj dane z pliku              ************");
        System.out.println("*************2-Oblicz                            ************");
        System.out.println("*************3-Testuj                            ************");
        System.out.println("*************4-Zakoncz program                   ************");
    }

    public static int[][] wczytaj() throws FileNotFoundException {
        String nazwaPliku = "c:/Users/matow/Dev/Algorithms-and-Datastructure/PS3/in4.txt";
        int[][] tab = null;
        int liczba;
        BufferedReader br = new BufferedReader(new FileReader(nazwaPliku));
        String linia;
        String[] podzielonaLinia = null;
        try {
            for (int i = 0; (linia = br.readLine()) != null; i++) {
                podzielonaLinia = linia.split(" ");
                for (int j = 0; j < podzielonaLinia.length; j++) {
                    liczba = Integer.parseInt(podzielonaLinia[j].trim());
                    if (i == 0) {
                        tab = new int[liczba][5];
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


    public static void main(String[] args) throws IOException {
        int[][] tab = null;
        int x, l1 = 0;
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
                    l1 = algorytm1(tab);
                    break;
                case 3:
                    System.out.println("licznik operacji w algorytmie 1 = " + l1);
                    break;
                case 4:
                    return;
            }
        }
    }

    private static int algorytm1(int[][] tab) throws FileNotFoundException {
        int counter = 0, sum, cieciex, cieciey;

        PrintWriter zapis = new PrintWriter("c:/Users/matow/Dev/Algorithms-and-Datastructure/PS3/out4.txt");

        List<Integer> xList = new ArrayList<>(tab[0].length);
        List<Integer> yList = new ArrayList<>(tab[1].length - 2);
        //Zmiana tablicy na typ ArrayList w celu łatwiejszego znajdywania maxa
        for (int i : tab[0]) {
            xList.add(i);
        }
        for (int i : tab[1]) {
            yList.add(i);
        }
        Integer ele = 0;
        while (yList.contains(ele)) {
            yList.remove(ele);
        }

        int n = yList.size(), m = xList.size();
        cieciex = 1;
        cieciey = 1;
        sum = 0;

        for (int i = 0; i < n + m; i++) {
            counter++;
            if (!xList.isEmpty() && !yList.isEmpty()) {
                counter++;
                if (Collections.max(xList) >= Collections.max(yList)) {
                    sum = sum + Collections.max(xList) * cieciex;
//              System.out.println("Suma:" + sum + "cieciex " + cieciex);
                    cieciey++;
                    xList.remove(Collections.max(xList));
                } else {
                    sum = sum + Collections.max(yList) * cieciey;
//                   System.out.println("Suma:" + sum + "cieciey " + cieciey);
                    cieciex++;
                    yList.remove(Collections.max(yList));
                }
                counter++;
            } else if (xList.isEmpty()) {
                while (!yList.isEmpty()) {
                    sum = sum + Collections.max(yList) * cieciey;
//                    System.out.println("Suma:" + sum + "cieciey " + cieciey);
                    cieciey++;
                    yList.remove(Collections.max(yList));
                }
                counter++;
            } else  {
                while (!xList.isEmpty()) {
                    sum = sum + Collections.max(xList) * cieciex;
//                    System.out.println("Suma:" + sum + "cieciex " + cieciex);
                    cieciex++;
                    xList.remove(Collections.max(xList));
                }
            }
        }

        zapis.println("Suma to : " + sum);
        zapis.close();

        System.out.println("Suma to : " + sum);

        return counter;
    }


}