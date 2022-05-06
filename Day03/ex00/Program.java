public class Program {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.out.println("Error: invalid arguments!");
            return;
        }
        String[] arguments = args[0].split("=");
        if (!"--count".equals(arguments[0])) {
            System.out.println("Error: invalid arguments!");
            return;
        }
        int count = Integer.parseInt(arguments[1]);

        Thread thread1 = new myThread("Egg", count);
        Thread thread2 = new myThread("Hen", count);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}
