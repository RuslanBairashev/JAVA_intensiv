import java.util.Scanner;
import java.util.UUID;

public class Menu {
    public void printMainMenu() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV - remove a transfer by ID");
        System.out.println("6. DEV - check transfer validity");
        System.out.println("7. Finish execution");
    }

    public void errMsg() {
        System.out.println("Input is not valid! Try again!");
    }

    public void addUser(TransactionService service) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a user name and a balance");
        String userInput = sc.nextLine();
        String[] inputArray = userInput.split(" ");
        try {
            String name = inputArray[0];
            int amount = Integer.parseInt(inputArray[1]);
            User user = new User(name);
            user.setBalance(amount);
            service.addUserToList(user);
            System.out.println("User with id = " + user.getIdentifier() + " is added");
        } catch (RuntimeException e) {
            errMsg();
        }
        System.out.println("_________________________________________________");
    }

    public void viewUserBalance(TransactionService service) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a user ID");
        try {
            int id = sc.nextInt();
            System.out.println(service.getUserList().getById(id).getName() +
                    " - " + service.getUserBalance(id));
        } catch (RuntimeException e) {
            errMsg();
        }
        System.out.println("_________________________________________________");
    }

    public void makeTransfer(TransactionService service) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        String userInput = sc.nextLine();
        String[] inputArray = userInput.split(" ");
        try {
            int sender = Integer.parseInt(inputArray[0]);
            int recipient = Integer.parseInt(inputArray[1]);
            int amount = Integer.parseInt(inputArray[2]);
            service.makeTransaction(recipient, sender, amount);
            System.out.println("The transfer is completed");
        } catch (RuntimeException e) {
            errMsg();
        }
        System.out.println("_________________________________________________");
    }

    public void viewUserTransactions(TransactionService service) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a user ID");
        try {
            int id = sc.nextInt();
            User user = service.getUserList().getById(id);
            for (int i = 0; i < user.getTransactionsList().toArray().length; i++) {
                if (user.getTransactionsList().toArray()[i].getaCategory().equals(Transaction.Category.OUTCOME)) {
                    System.out.println("To " + user.getTransactionsList().toArray()[i].getRecipient().getName() +
                            "(id = " + user.getTransactionsList().toArray()[i].getRecipient().getIdentifier()
                            + ") " + user.getTransactionsList().toArray()[i].getAmount()
                            + " with id = " + user.getTransactionsList().toArray()[i].getIdentifier());
                } else {
                    System.out.println("From " + user.getTransactionsList().toArray()[i].getSender().getName() +
                            "(id = " + user.getTransactionsList().toArray()[i].getSender().getIdentifier()
                            + ") " + user.getTransactionsList().toArray()[i].getAmount()
                            + " with id = " + user.getTransactionsList().toArray()[i].getIdentifier());
                }

            }
        } catch (RuntimeException e) {
            errMsg();
        }
        System.out.println("_________________________________________________");
    }

    public void removeTransfer(TransactionService service) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a user ID and a transfer ID");
        String userInput = sc.nextLine();
        String[] inputArray = userInput.split(" ");
        try {
            int id = Integer.parseInt(inputArray[0]);
            String uuid = inputArray[1];
            Transaction[] tsArray = service.getUserList().getById(id).getTransactionsList().toArray();
            int amount = 0;
            int usrId = 0;
            String prefix = "";
            String name = "";
            for (int i = 0; i < tsArray.length; i++) {
                if (tsArray[i].getIdentifier().equals(UUID.fromString(uuid))) {
                    amount = tsArray[i].getAmount();
                    if (tsArray[i].getaCategory().equals(Transaction.Category.OUTCOME)){
                        prefix = "Transfer to ";
                        name = tsArray[i].getRecipient().getName();
                        usrId = tsArray[i].getRecipient().getIdentifier();
                    } else {
                        prefix = "Transfer from ";
                        name = tsArray[i].getSender().getName();
                        usrId = tsArray[i].getSender().getIdentifier();
                    }
                }
            }
            if (amount != 0) {
                    service.removeTransactionFromClient(UUID.fromString(uuid), id);
                    System.out.println(prefix + name +
                            "(id = " + usrId + ") " + amount + " removed");
            }
        } catch (RuntimeException e) {
            errMsg();
        }
        System.out.println("_________________________________________________");
    }

    public void checkTransferValidity(TransactionService service) {
        Transaction[] unpaired = service.checkValidity();

        System.out.println("Check results:");
        if (unpaired != null) {
            String hasTs, noTs;
            int hasNum, noNum;
            User user;
            for (int i = 0; i < unpaired.length; i++) {
                hasTs = noTs = "";
                hasNum = noNum = 0;
//                if (unpaired[i].getaCategory().equals(Transaction.Category.INCOME)) {
//                    user = unpaired[i].getRecipient();
//                } else {
                    user = unpaired[i].getRecipient();
//                }
                for (int j = 0; j < user.getTransactionsList().toArray().length; j++) {
                    if (unpaired[i].getIdentifier().equals(user.getTransactionsList().toArray()[j].getIdentifier())) {
                        hasTs = user.getName();
                        hasNum = user.getIdentifier();
                        break;
                    }
                }
                if (hasNum == 0) {
                    user = unpaired[i].getSender();
                    hasTs = user.getName();
                    hasNum = user.getIdentifier();
                }
                if (hasTs.equals(user.getTransactionsList().toArray()[i].getRecipient().getName())) {
                    noTs = user.getTransactionsList().toArray()[i].getSender().getName();
                    noNum = user.getTransactionsList().toArray()[i].getSender().getIdentifier();
                } else {
                    noTs = user.getTransactionsList().toArray()[i].getRecipient().getName();
                    noNum = user.getTransactionsList().toArray()[i].getRecipient().getIdentifier();
                }
                System.out.println(hasTs + "(id = " +
                        hasNum +
                        ") has an unacknoweledged transfer id = " +
                        unpaired[i].getIdentifier() + " from " +
                        noTs + "(id = " +
                        noNum +
                        ") for " + unpaired[i].getAmount());
            }
        }
        System.out.println("_________________________________________________");
    }
}
