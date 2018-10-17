import java.util.*;

class ZadB {

    static int algorithmA(int[] array) {
        int counter = 0;
        Arrays.sort(array);
        System.out.println(array.toString());
        return counter;
    }

    public static void main(String[] args) {

        int inData[] = { 2, 3, 4, 4, 5, 1, 3, 2, 5, 4 };
        algorithmA(inData);
    }

}