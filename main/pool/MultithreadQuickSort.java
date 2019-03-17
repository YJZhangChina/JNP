package pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MultithreadQuickSort {

	private static final int THRESHOLD = 100;

	/**
	 * Sorts the specified 'int' array into ascending numerical order.
	 *
	 * @param ints the 'int' array to be sorted
	 */
	public static void sort(int[] ints) {
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(new ForkJoinQuickSortImpl(ints, 0, ints.length - 1));
		pool = null;
	}

	protected static class ForkJoinQuickSortImpl extends RecursiveAction {
		private static final long serialVersionUID = 3719908001246526743L;
		private int[] ints;
		private int from;
		private int to;

		protected ForkJoinQuickSortImpl(int[] ints, int from, int to) {
			this.ints = ints;
			this.from = from;
			this.to = to;
		}

		@Override
		protected void compute() {
			if (to - from < THRESHOLD) {
				insertSort(ints, from, to);
			} else {
				int pivot = (ints[from] + ints[to] + ints[from + (to - from) / 2]) / 3;
				int i = from, j = from, k = to;
				while (j <= k) {
					if (ints[j] < pivot) {
						swap(ints, i, j);
						++i;
						++j;
					} else if (ints[j] > pivot) {
						swap(ints, j, k);
						--k;
					} else {
						++j;
					}
				}
				ForkJoinQuickSortImpl left = new ForkJoinQuickSortImpl(ints, from, i - 1),
						right = new ForkJoinQuickSortImpl(ints, k + 1, to);
				left.fork(); right.fork();
				left.join(); right.join();
				left = null; right = null;
			}
		}
	}

	private static void insertSort(int[] ints, int from, int to) {
		int curr, i;
		for (int j = from + 1, k = to + 1; j < k; ++j) {
			curr = ints[j];
			int l = from - 1;
			for (i = j - 1; (i > l) && (ints[i] > curr); --i) {
				ints[i + 1] = ints[i];
			}
			ints[i + 1] = curr;
		}
	}

	private static void swap(int[] ints, int i, int j) {
		int var = ints[i];
		ints[i] = ints[j];
		ints[j] = var;
	}

}
