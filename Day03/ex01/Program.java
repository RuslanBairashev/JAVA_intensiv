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

        Printer printer = new Printer(count);
        Thread thread1 = new Thread(new myThread(count, printer, false));
        Thread thread2 = new Thread(new myThread(count, printer, true));

        thread2.start();
        thread1.start();
    }
}
