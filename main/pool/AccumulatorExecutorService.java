package pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AccumulatorExecutorService implements IAccumulator {

    private int numThreads;
    private ExecutorService pool;

    public AccumulatorExecutorService() {
        numThreads = Runtime.getRuntime().availableProcessors();
        pool = Executors.newFixedThreadPool(numThreads);
    }

    public static class AccumulatorExecutorServiceImpl implements Callable<Long> {
        private long[] nums;
        private int from;
        private int to;

        public AccumulatorExecutorServiceImpl(long[] nums, int from, int to) {
            this.nums = nums;
            this.from = from;
            this.to = to;
        }

        public Long call() throws Exception {
            long sum = 0;
            for(int i = from, j = to + 1; i < j; i++) {
                sum += nums[i];
            }
            return sum;
        }
    }

    public long sum(long[] nums) {
        long sum = 0;
        List<Future<Long>> results = new ArrayList<Future<Long>>();
        int part = nums.length / numThreads;
        for(int i = 0; i < numThreads; i++) {
            int from = i * part;
            int to = (i == numThreads - 1) ? nums.length - 1 : (i + 1) * part  - 1;
            results.add(pool.submit(new AccumulatorExecutorServiceImpl(nums, from , to)));
        }
        for(Future<Long> future : results) {
            try {
                sum += future.get();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

}
