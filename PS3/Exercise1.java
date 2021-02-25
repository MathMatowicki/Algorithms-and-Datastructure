import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/*
Pewna firma szkoleniowa ma do dyspozycji jedną aulę wykładową. Wykładowcy, którzy chcą
skorzystać z tej sali składają swoje zapotrzebowanie określając czas rozpoczęcia i zakończenia
wykładu. Osoba układająca plan zajęć musi go tak ułożyć aby czas wykorzystania sali było jak
najdłuższy. Zakładamy, że w momencie zakończenia jednego wykładu może się rozpocząć
następny wykład. Napisz algorytm, który na podstawie życzeń wykładowców wybiera takie
wykłady, których łączny czas trwania będzie najdłuższy. Zakładamy, że łączna liczba życzeń
wynosi n<=10000. Zakładamy, że życzenie każdego wykładowcy to para liczb całkowitych p
(początek) i k (koniec)(0<=p<=k<=30000). Przedział [p,k) charakteryzuje życzenia jednego
wykładowcy, który chce aby jego wykład rozpoczął się w czasie p i kończył przed czasem k.
Przykład
Dane:
n=12
[1,2), [3,5), [0,4), [6,8), [7,13), [4,6), [9,12), [11,14), [15,19), [14,16), [18,20)
Wynik:
Maksymalny czas trwania wykładów: 15
Wykłady: [0,4), [4,6), [7,13), [15,19)
 */
public class Exercise1 {

    public static void displayMenu() {
        System.out.println("*************                 MENU               ************");
        System.out.println("*************1-Wczytaj dane z pliku              ************");
        System.out.println("*************2-Oblicz                            ************");
        System.out.println("*************3-Testuj                            ************");
        System.out.println("*************4-Zakoncz program                   ************");
    }

    public static int[][] wczytaj() throws FileNotFoundException {
        String nazwaPliku = "c:/Users/matow/Dev/Algorithms-and-Datastructure/PS3/in1.txt";
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
                        tab = new int[liczba][2];
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
       //             l1 = algorytm1(tab);
                    //           l2 = algorytm2(tab);
                    l3 = algorytm3(tab);
                    break;
                case 3:
                    System.out.println("licznik operacji w algorytmie  = " + l3 );
                    break;
                case 4:
                    return;
            }
        }
    }

    private static int algorytm3(int[][] tab) throws FileNotFoundException {
        Arrays.sort(tab, (a, b) -> Double.compare(a[1], b[1]));
        System.out.println(Arrays.deepToString(tab));

        PrintWriter zapis = new PrintWriter("c:/Users/matow/Dev/Algorithms-and-Datastructure/PS3/out1.txt");


        int j = 0, counter = 0, time = 0;
        System.out.println("Wykłady:");
        if (tab[j][1] - tab[j][0] < tab[j + 1][1] - tab[j + 1][0]) {
            j++;
        }

        zapis.print("[" + tab[j][0] + ", " + tab[j][1] + ") ");

        System.out.print("[" + tab[j][0] + ", " + tab[j][1] + ") ");
        time += tab[j][1] - tab[j][0];
        for (int i = 1; i < tab.length; i++) {
            counter++;
            if (tab[i][0] >= tab[j][1]) {
                System.out.print("[" + tab[i][0] + ", " + tab[i][1] + ") ");
                zapis.print("[" + tab[i][0] + ", " + tab[i][1] + ") ");
                time += tab[i][1] - tab[i][0];
                j = i;
            }
        }
        System.out.println("czas zajęc: " + time);
        zapis.println("czas zajęc: " + time);
        zapis.close();
        return counter;
    }

//
//    private static int algorytm2(int[][] tab) {
//        Arrays.sort(tab, (a, b) -> Double.compare(a[1], b[1]));
//        System.out.println(Arrays.deepToString(tab));
//        int counter = 0, timeOfStart = tab[0][0], timeOfEnd = tab[0][1], time = 0;
//        for (int i = 1; i < tab.length; i++) {
//            counter++;
//            if (timeOfEnd <= tab[i][0]) {
//                System.out.println("[" + timeOfStart + " " + timeOfEnd + "]");
//                System.out.println("[" + tab[i][0] + " " + tab[i][1] + "]");
//                if (i != 11) {
//                    time += timeOfEnd - timeOfStart;
//                    if (timeOfEnd - timeOfStart > tab[i + 1][1] - tab[i + 1][0]) {
//                        timeOfEnd = tab[i + 1][1];
//                        timeOfStart = tab[i + 1][0];
//                        i++;
//                        continue;
//                    }
//                } else {
//                    time += timeOfEnd - timeOfStart;
//                }
//                timeOfEnd = tab[i][1];
//                timeOfStart = tab[i][0];
//            } else {
//                continue;
//            }
//        }
//        System.out.println(time);
//        return counter;
//    }
//
//    private static int algorytm1(int[][] tab) {
//        int counter = 0, time = 0;
//        Arrays.sort(tab, (a, b) -> Double.compare(a[1], b[1]));
//        int i = 0;
//        System.out.println(Arrays.deepToString(tab));
//
//        for (int j = 0; j < tab.length; j++) {
//            counter++;
//            if (tab[j][0] >= tab[i][1]) {
//                System.out.print(j + " ");
//                System.out.println(tab[j][0] + " " + tab[i][1]);
//                i = j;
//                time += tab[j][1] - tab[j][0];
//            }
//        }
//        System.out.println("czas zajęc: " + time);
//        return counter;
//    }

}