import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        int req = 0;
        int sum = 0;
        boolean flag = true;
        while (number != 42) {
            while (number != 0) {
                sum += number % 10;
                number = number / 10;
            }

            flag = true;
            for (int i = 2; i * i <= sum; i++) {
                if(sum % i == 0 && sum != 4) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                req++;
            }

            number = sc.nextInt();
        }
        System.out.println("Count of coffee-request – " + req);
    }
}
