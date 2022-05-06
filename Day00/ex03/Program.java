import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int weekCount = 1;
        int minGrade;
        int curGrade;
        long result = 0;
        long powFactor;
        Scanner sc = new Scanner(System.in);

        String week = sc.nextLine();
        while (!"42".equals(week)) {
            minGrade = 9;
            powFactor = 1;
            if (!("Week " + weekCount).equals(week)) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }

            for (int i = 0; i < 5; i++) {
                curGrade = sc.nextInt();
                if (curGrade < minGrade) {
                    minGrade = curGrade;
                }
            }
            sc.nextLine();

            for (int i = 1; i < weekCount; i++) {
                powFactor *= 10;
            }
            result = result + minGrade * powFactor;

            weekCount++;
            week = sc.nextLine();
        }
        weekCount = 1;
        while(result != 0) {
            System.out.print("Week " + weekCount + " ");
            for (int i = 0; i < result % 10; i++) {
                System.out.print("=");
            }
            System.out.println(">");
            result/=10;
            weekCount++;
        }
    }
}
