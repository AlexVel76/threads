package find_max_value;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class Main {
    static AtomicLong max = new AtomicLong(0);
    static int[] numbers = new int[100000000];
    static CountDownLatch latch;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Count number in array: " + numbers.length);

        numbers = generateNumbers(numbers.length);

        System.out.println("------------Find by foreach---------------");
        findByForEach();

        System.out.println("------------Find by custom thread---------------");
        findByInThreads();

        System.out.println("------------Find by common stream---------------");
        findByStream(Arrays.stream(numbers));

        System.out.println("------------Find by parallel stream---------------");
        findByStream(Arrays.stream(numbers).parallel());

    }

    private static int findByForEach() {
        int max = 0;
        long startTime = System.currentTimeMillis();
        long endTime = 0;
        for (int i : numbers) {
            if (max < i)
                max = i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("Running Time (millis): " + (endTime - startTime));
        System.out.println("Maximum value in array is: " + max);
        return max;
    }

    private static void findByInThreads() {
        int cpuCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cpuCount);

        System.out.println("cpu count: " + cpuCount);
        int batch = new Double(Math.ceil(new Double(numbers.length) / cpuCount)).intValue();
        //System.out.println("batch: " + batch);

        int indexStart = 0;
        int indexEnd = batch - 1;
        int threadCount = new Double(Math.ceil(new Double(numbers.length) / batch)).intValue();
        System.out.println("Running treads: " + threadCount);

        latch = new CountDownLatch(threadCount);
        long startTime = System.currentTimeMillis();
        long endTime = 0;
        for (int i = 1; i <= threadCount; i++) {
            //System.out.println("indexStart: " + indexStart);
            //System.out.println("indexEnd: " + indexEnd);
            executorService.execute(new FindMax(indexStart, indexEnd));

            indexStart = indexEnd + 1;
            indexEnd = indexStart + batch - 1;
            if (indexEnd > numbers.length - 1) {
                indexEnd = numbers.length - 1;
            }
        }

        try {
            latch.await();
        } catch (InterruptedException E) {
            System.out.println("Some problem.");
        }
        endTime = System.currentTimeMillis();
        System.out.println("Running Time (millis): " + (endTime - startTime));
        System.out.println("Maximum value in array is: " + max.get());
    }

    private static void findByStream(IntStream intStream) {
        IntStream s = Arrays.stream(numbers);

        long startTime = System.currentTimeMillis();
        long endTime = 0;
        int max = intStream.max().getAsInt();
        endTime = System.currentTimeMillis();
        System.out.println("Running Time (millis): " + (endTime - startTime));
        System.out.println("Maximum value in array is: " + max);
    }

    private static int[] generateNumbers(int length) {
        Random random = new Random();
        return random.ints(length).toArray();
    }

    static class FindMax implements Runnable {
        private int indexStart;
        private int indexEnd;

        public FindMax(int indexStart, int indexEnd) {
            this.indexStart = indexStart;
            this.indexEnd = indexEnd;
        }

        @Override
        public void run() {
            getMax(numbers);

            latch.countDown();
        }

        protected void getMax(final int[] numbers) {
            for (int i = indexStart; i <= indexEnd; i++) {
                if (max.get() < numbers[i])
                    max.set(numbers[i]);
            }

            //System.out.println("max in thread: " + Thread.currentThread().getName() + ". Max Value: " + max);
        }
    }
}
