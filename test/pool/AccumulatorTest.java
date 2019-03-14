package pool;

import junit.framework.TestCase;

import java.util.Random;

public class AccumulatorTest extends TestCase {

    private long[] nums;
    private int numsMax;
    private int numsLength;

    @Override
    protected void setUp() {
        numsLength = 1000;
        numsMax = 100;
        nums = new long[numsLength];
        Random random = new Random();
        for (int i = 0; i < numsLength; i++) {
            nums[i] = random.nextInt(numsMax);
        }
    }

    public void test() {
        IAccumulator executorService = new AccumulatorExecutorService();
        long a = executorService.sum(nums);
        IAccumulator forkJoin = new AccumulatorForkJoin();
        long b = forkJoin.sum(nums);
        assertEquals(a, b);
    }

//    public void test() {
//        long[] nums = new long[100];
//        for (int i = 0; i < 100; i++) {
//            nums[i] = i + 1;
//        }
//        IAccumulator executorService = new AccumulatorExecutorService();
//        long a = executorService.sum(nums);
//        assertEquals(a, 5050L);
//        IAccumulator forkJoin = new AccumulatorForkJoin();
//        long b = forkJoin.sum(nums);
//        assertEquals(b, 5050L);
//    }
}
