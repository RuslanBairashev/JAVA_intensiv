import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Error: invalid arguments!");
            return;
        }
        String[] arguments = args[0].split("=");
        if (!"--threadsCount".equals(arguments[0])) {
            System.out.println("Error: invalid arguments!");
            return;
        }
        int threadsCount = Integer.parseInt(arguments[1]);
        if (threadsCount < 1) {
            System.out.println("Error: invalid arguments!");
            return;
        }

        File file = new File("./ex03/resources/files_urls.txt");
        Scanner sc = new Scanner(file);
        String pathURL;
        Object object = new Object();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadsCount; i++) {
            Thread tmp = new Thread(new MyThread(threads, sc, i, object));
            threads.add(tmp);
        }
        for (Thread tmp: threads) {
            tmp.start();
        }
        for (Thread tmp: threads) {
            try {
                tmp.join();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
