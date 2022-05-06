import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Program {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) throws IOException {
        String arg1, arg2;
        arg1 = args[0];
        arg2 = args[1];

        Scanner sc = new Scanner(arg1);
        ArrayList<String> first = new ArrayList<String>();
        String[] tmpArray;
        try (BufferedReader reader = new BufferedReader(new FileReader(arg1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tmpArray = line.split(" ");
                for (int i = 0; i < tmpArray.length; i++) {
                    first.add(tmpArray[i]);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
        sc = new Scanner(arg2);
        ArrayList<String> second = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(arg2))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tmpArray = line.split(" ");
                for (int i = 0; i < tmpArray.length; i++) {
                    second.add(tmpArray[i]);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();

        ArrayList<String> all = new ArrayList<String>();
        for(String tmp: first) {
            if (!all.contains(tmp)) {
                all.add(tmp);
            }
        }
        for(String tmp: second) {
            if (!all.contains(tmp)) {
                all.add(tmp);
            }
        }
        int[] accurFirst = new int[all.size()];
        int[] accurSecond = new int[all.size()];

        for (int i = 0; i < all.size(); i++) {
            for (int j = 0; j < first.size(); j++) {
                if (first.get(j).equals(all.get(i))) {
                    (accurFirst[i])++;
                }
            }
        }
        for (int i = 0; i < second.size(); i++) {
            for (int j = 0; j < all.size(); j++) {
                if (second.get(i).equals(all.get(j))) {
                    (accurSecond[j])++;
                }
            }
        }

        int numerator = 0;
        for (int i = 0; i < accurFirst.length; i++) {
            numerator += accurFirst[i] * accurSecond[i];
        }

        int sum1 = 0;
        for (int i = 0; i < accurFirst.length; i++) {
            sum1 += accurFirst[i] * accurFirst[i];
        }
        int sum2 = 0;
        for (int i = 0; i < accurSecond.length; i++) {
            sum2 += accurSecond[i] * accurSecond[i];
        }
        double denominator = Math.sqrt((double) sum1) * Math.sqrt((double) sum2);
        double similarity = numerator / denominator;

        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println("Similarity = " + df.format(similarity));
    }
}
