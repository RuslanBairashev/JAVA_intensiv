public class myThread implements Runnable{
    private int count;
    private Printer print;
    private boolean flag;

    public myThread(int count, Printer print, boolean flag) {
        this.count = count;
        this.print = print;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (count > 0) {
            if (flag) {
                print.printEgg();
            } else {
                print.printHen();
            }
            count--;
        }
    }
}
