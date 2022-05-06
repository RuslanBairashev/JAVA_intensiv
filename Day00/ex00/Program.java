public class Program {
    public static void main(String[] args) {
        int number = 479598;

        int sum;
        sum =   number % 10 / 1 +
                number % 100 / 10 +
                number % 1000 / 100 +
                number % 10000 / 1000 +
                number % 100000 / 10000 +
                number % 1000000 / 100000;
        System.out.println(sum);
    }
}
