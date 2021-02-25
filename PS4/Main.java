import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void displayMenu() {
        System.out.println("*************                 MENU               ************");
        System.out.println("*************1-Wstaw podaną liczbę               ************");
        System.out.println("*************2-Wyszukaj podaną liczbę            ************");
        System.out.println("*************3-Usuń podaną liczbę                ************");
        System.out.println("*************4-Pokaz drzewo i zapisz je do pliku ************");
        System.out.println("*************5-Przedział                         ************");
        System.out.println("*************6-Załaduj drzewo                    ************");
        System.out.println("*************7-Zakończ program                   ************");
    }

    public static void generate(int total) {
        HashSet hs = new HashSet();
        while (hs.size() < total) {
            int num = (int) (Math.random() * ((32768 - (-32768)) + 1)) - 32768;
            hs.add(num);
        }
        Iterator it = hs.iterator();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("avlin.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        while (it.hasNext()) {

            printWriter.printf("%d\n", it.next());
        }
        printWriter.close();
    }

    public static void main(String[] args) throws IOException {
        int x, number = 0;
        Scanner s = new Scanner(System.in);
        Scanner sNumber = new Scanner(System.in);
        AVLTree avlTree = new AVLTree();
        while (true) {
            displayMenu();
            x = s.nextInt();
            switch (x) {
                case 1:
                    System.out.println("Podaj liczbę jaką chciałbyś umieścić w drzewie");
                    number = s.nextInt();
                    avlTree.insert(number);
                    break;
                case 2:
                    System.out.println("Podaj liczbę jaką chciałbyś odnaleźć w drzewie");
                    number = s.nextInt();
                    if (avlTree.find(number) != null) {
                        System.out.println("liczba " + number + " jest w drzewie");
                    } else {
                        System.out.println("liczba " + number + " nie jest w drzewie");
                    }
                    break;
                case 3:
                    System.out.println("Podaj liczbę jaką chciałbyś usunąć z drzewa");
                    number = s.nextInt();
                    if (avlTree.find(number) != null) {
                        avlTree.delete(number);
                    } else {
                        System.out.println("Nie ma takiej liczby w drzewie");
                    }
                    break;
                case 4:
                    System.out.println("Wysokość drzewa: " + avlTree.height());
                    System.out.println("Korzeń: " + avlTree.getRoot().key);
                    try {
                        avlTree.printAndSaveTree(System.out, avlTree);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Podaj dwie liczby");
                    int lowNumber = s.nextInt();
                    int highNumber = s.nextInt();
                    System.out.println(avlTree.countInRange(avlTree.getRoot(), lowNumber, highNumber));
                    break;
                case 6:
                    generate(50);
                    while (avlTree.getRoot()!= null) avlTree.delete(avlTree.getRoot().key);
                    String file = "c:/Users/matow/Dev/Algorithms-and-Datastructure/avlin.txt",
                    line;
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    while((line = reader.readLine())!= null){
                        int value = Integer.parseInt(line);
                        avlTree.insert(value);
                    }
                    break;
                case 7:
                    return;
            }
        }
    }
}
