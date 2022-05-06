import java.util.Scanner;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        boolean isDev = false;
        if (args.length > 0 && "--profile=dev".equals(args[0])) {
            isDev = true;
        }
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        TransactionService service = new TransactionService();

        int userInput = 0;
        String strInput;
        while (true) {
            menu.printMainMenu();
            try {
                strInput = sc.nextLine();
                userInput = Integer.parseInt(strInput);
            } catch (RuntimeException e) {
                System.out.println("Wrong input. Input value: 1 ... 7");
                continue;
            }
            if (userInput == 1) {
                menu.addUser(service);
            } else if (userInput == 2) {
                menu.viewUserBalance(service);
            } else if (userInput == 3) {
                menu.makeTransfer(service);
            } else if (userInput == 4) {
                menu.viewUserTransactions(service);
            } else if (isDev == true && userInput == 5) {
                menu.removeTransfer(service);
            } else if (isDev == true && userInput == 6) {
                menu.checkTransferValidity(service);
            } else if (userInput == 7) {
                return;
            } else {
                System.out.println("Wrong input. Input value: 1 ... 7");
            }
        }
    }
}
