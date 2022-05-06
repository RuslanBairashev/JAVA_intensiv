public class myThread extends Thread{
    private String name;
    private int count;

    public myThread(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public void run(){
        for (int i = 0; i < count; i++) {
            System.out.println(name);
        }
    }
}
