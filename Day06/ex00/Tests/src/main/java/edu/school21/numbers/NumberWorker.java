package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException();
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
        return flag;
    }

    public int digitsSum(int number) {
        int razryad = 1;
        int tmp = number;
        while (tmp / 10 != 0) {
            razryad++;
            tmp /= 10;
        }

        int sum = 0;
        for (int i = 0; i < razryad; i++) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    public class IllegalNumberException extends RuntimeException {

    }
}
