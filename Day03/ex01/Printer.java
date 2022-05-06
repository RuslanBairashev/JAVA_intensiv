public class Printer {
    private int count;
    private boolean flag;

    public Printer(int count) {
        this.count = count;
        this.flag = true;
    }

    synchronized void printEgg(){
        while (flag == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Egg");
        flag = false;
        notifyAll();
    }

    synchronized void printHen(){
        while (flag == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hen");
        flag = true;
        notifyAll();
    }
}
