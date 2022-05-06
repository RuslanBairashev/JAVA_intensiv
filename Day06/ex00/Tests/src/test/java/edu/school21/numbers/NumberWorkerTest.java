package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {
    private final NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = {5, 7, 11})
    void isPrimeForPrimes(int number){
        assertTrue(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 10, 55})
    void isPrimeForNotPrimes(int number){
        assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, 0})
    void isPrimeForIncorrectNumbers(int number){
        assertThrows(NumberWorker.IllegalNumberException.class,
                () ->
                numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void digitsSum(int number, int sum){
        assertEquals(numberWorker.digitsSum(number), sum);
    }
}
