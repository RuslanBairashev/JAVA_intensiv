import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        if (number < 2) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        boolean flag = true;
        int count = 1;
        for (int i = 2; i * i <= number; i++) {
            if(number % i == 0 && number != 4) {
                flag = false;
                break;
            }
            count++;
        }
        if (flag == true) {
            System.out.println("true " + count);
        } else {
            System.out.println("false " + count);
        }
    }
}
