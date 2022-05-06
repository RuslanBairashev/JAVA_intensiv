public class myThread implements Runnable{
    private int[] array;
    private int min;
    private int max;
    private int[] sum;
    private int threadNum;

    public myThread(int[] array, int min, int max, int[] sum, int threadNum) {
        this.array = array;
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        int res = 0;
        for (int i = min; i <= max; i++) {
            res += array[i];
        }
        this.sum[threadNum] = res;
        System.out.println("Thread " + (threadNum + 1) + ": from " + min + " to " + max +
                " sum is " + res);
    }
}
