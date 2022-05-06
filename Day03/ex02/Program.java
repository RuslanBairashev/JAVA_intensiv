import java.util.Random;

public class Program {
    public static int[] sum;

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            System.out.println("Error: invalid arguments!");
            return;
        }
        String[] arguments = args[0].split("=");
        if (!"--arraySize".equals(arguments[0])) {
            System.out.println("Error: invalid arguments!");
            return;
        }
        int arraySize = Integer.parseInt(arguments[1]);
        arguments = args[1].split("=");
        if (!"--threadsCount".equals(arguments[0])) {
            System.out.println("Error: invalid arguments!");
            return;
        }
        int threadsCount = Integer.parseInt(arguments[1]);
        if ((threadsCount > arraySize) || (arraySize < 0) || (arraySize > 2000000)
        || (threadsCount < 1)) {
            System.out.println("Error: invalid arguments!");
            return;
        }

        int[] array = new int[arraySize];
        Thread[] threadsArray = new Thread[threadsCount];
        sum = new int[threadsCount];

        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(2001) - 1000;
        }
        int sectionSize = arraySize / threadsCount;
        if (arraySize % threadsCount != 0) {
            sectionSize++;
        }

        int result = 0;
        for (int i = 0; i < arraySize; i++) {
            result += array[i];
        }
        System.out.println("Sum: " + result);

        int min, max;
        String threadName;
        for (int i = 0; i < threadsCount; i++) {
            min = 0 + i * sectionSize;
            if (i != threadsCount - 1) {
                max = min + sectionSize - 1;
            } else {
                max = arraySize - 1;
            }
            Thread temp = new Thread(new myThread(array, min, max, sum, i));
            threadsArray[i] = temp;
            temp.start();
        }

        for (Thread tmp: threadsArray) {
            tmp.join();
        }
        result = 0;
        for (int i = 0; i < sum.length; i++) {
            result += sum[i];
        }
        System.out.println("Sum by threads: " + result);
    }
}
