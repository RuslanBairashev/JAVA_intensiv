import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) throws IOException {
        File file = new File("./ex00/resources/signatures.txt");
        Scanner sc = new Scanner(file);

        Map<String, String> formatMap = new HashMap<>();
        String argument , arg2;
        String[] strArray;
        StringBuilder stringBuilder = new StringBuilder();
        while(sc.hasNextLine()) {
            stringBuilder.setLength(0);
            argument = sc.nextLine();
            arg2 = argument.split(", ")[1];
            strArray = arg2.split(" ");
            for (String tmp: strArray) {
                stringBuilder.append(tmp);
            }
            arg2 = stringBuilder.toString();
            formatMap.put(argument.split(", ")[0], arg2);
        }
        sc.close();
        sc = new Scanner(System.in);

        InputStream in = null;
        OutputStream out = null;
        String userInput;
        StringBuilder hexToString = new StringBuilder();
        boolean flag = false;
        while(true)
        {
            userInput = sc.nextLine();
            if ("42".equals(userInput)) {
                break;
            }
            try {
                in = new FileInputStream(userInput);
                out = new FileOutputStream("./ex00/resources/result.txt", true);
                hexToString.setLength(0);
                for (int i = 0; i < 20 && in.available() > 0; i++) {
                    hexToString.append(Integer.toHexString(in.read()).toUpperCase());
                }
                userInput = hexToString.toString();
                flag = false;
                for (Map.Entry<String, String> entry: formatMap.entrySet()) {
                    if (userInput.contains(entry.getValue())) {
                        flag = true;
                        System.out.println("PROCESSED");
                        out.write(entry.getKey().getBytes());
                        out.write("\n".getBytes());
                        break;
                    }
                }
                if (flag == false) {
                    System.out.println("UNDEFINED");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (in != null) {
                    in.close();
                }
            }
        }
    }
}
