import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        char[] savedChars = new char[1000];
        int[] charCount = new int[1000];
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        char[] array = str.toCharArray();
        boolean isFound;
        int curIndex = 0;
        for (int i = 0; i < array.length; i++) {
            isFound = false;
            for (int j = 0; j < savedChars.length; j++) {
                if (array[i] == savedChars[j]) {
                    (charCount[j])++;
                    isFound = true;
                    break;
                }
            }
            if (isFound == false) {
                savedChars[curIndex] = array[i];
                (charCount[curIndex])++;
                curIndex++;
            }
        }

        char[] key = new char[curIndex];
        int[] value = new int[curIndex];
        int max, index = 0;
        float scale = 0;
        for (int i = 0; i < curIndex; i++) {
            max = 0;
            for (int j = 0; j < curIndex; j++) {
                if (charCount[j] > max) {
                    max = charCount[j];
                    index = j;
                }
            }
            if (scale == 0 ) {
                scale = max;
            }
            key[i] = savedChars[index];
            value[i] = charCount[index];
            charCount[index] = -1;
        }
        char tmp;
        do {
            tmp = 0;
            for (int i = 0; i < value.length; i++) {
                if (i != 0 && (value[i] == value[i - 1])) {
                    if (key[i] < key[i - 1]) {
                        tmp = key[i];
                        key[i] = key[i - 1];
                        key[i - 1] = tmp;
                    }
                }
            }
        }
        while (tmp != 0);

        float interval = scale / 10;
        for (int level = 0; level < 10; level++) {
            for (int i = 0; i < curIndex && i < 10; i++) {

                if ((float)value[i] >= interval * (10 - level)) {
                    if ((float) value[i] - interval * (10 - level)
                            > interval - 0.001) {
                        System.out.printf("%4c", '#');
                    } else {
                        System.out.printf("%4d", value[i]);
                    }
                }
            }
                System.out.println();
        }
        for (int i = 0; i < curIndex && i < 10; i++) {
            if (value[i]  > scale / 10) {
                System.out.printf("%4c",'#');
            } else {
                System.out.printf("%4d", value[i]);
            }
        }
        System.out.println();

        for (int i = 0; i < curIndex && i < 10; i++) {
            System.out.printf("%4c" ,key[i]);
        }
        System.out.println();
    }
}
