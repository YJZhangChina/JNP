package pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class AccumulatorForkJoin implements IAccumulator {

    private ForkJoinPool pool;

    public AccumulatorForkJoin() {
        pool = new ForkJoinPool();
    }

    public static class AccumulatorForkJoinImpl extends RecursiveTask<Long> {
        private long[] nums;
        private int from;
        private int to;

        public AccumulatorForkJoinImpl(long[] nums, int from, int to) {
            this.nums = nums;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            if (to - from < 10) {
                long sum = 0;
                for (int i = from, j = to + 1; i < j; i++) {
                    sum += nums[i];
                }
                return sum;
            } else {
                int middle = from + (to - from) / 2;
                AccumulatorForkJoinImpl left = new AccumulatorForkJoinImpl(nums, from, middle);
                AccumulatorForkJoinImpl right = new AccumulatorForkJoinImpl(nums, to, middle);
                left.fork();
                right.fork();
                return left.join() + right.join();
            }
        }
    }

    public long sum(long[] nums) {
        return pool.invoke(new AccumulatorForkJoinImpl(nums, 0, nums.length - 1));
    }

}
