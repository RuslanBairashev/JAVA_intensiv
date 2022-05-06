import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.Scanner;

public class MyThread implements Runnable{
    private List<Thread> threads;
    private Scanner sc;
    private int id;
    private Object object;

    private String path;

    public MyThread(List<Thread> threads, Scanner sc, int id, Object object) {
        this.threads = threads;
        this.sc = sc;
        this.id = id;
        this.object = object;
    }

    ReadableByteChannel rbc;
    FileOutputStream fos;
    String[] filename;
    String userinput;
    String fileNum;

    @Override
    public void run() {
        while (true) {
            synchronized (object) {
                if (sc.hasNextLine()) {
                    userinput = sc.nextLine();
                } else {
                    break;
                }
            }
            fileNum = userinput.split(" ")[0];
            path = userinput.split(" ")[1];
            try {
                rbc = Channels.newChannel((new URL(path).openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            filename = path.split("/");
            File file = new File("./ex03/resources/"
                    + filename[filename.length - 1]);
            try {
                fos = new FileOutputStream("./ex03/resources/"
                        + filename[filename.length - 1]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Thread-" + id + " start download file number " + fileNum);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Thread-" + id + " finish download file number " + fileNum);
        }
    }
}
