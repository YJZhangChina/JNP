package pool;

public class Test {

	public int indexOf(String a, String b) {
		if (a == null || b == null) {
			return -1;
		}
		int la = a.length(), lb = b.length();
		if (lb == 0) {
			return 0;
		}
		if (la < lb) {
			return -1;
		}
		char[] ca = a.toCharArray(), cb = b.toCharArray();
		for (int i = 0, j = la - lb + 1; i < j; ++i) {
			for (int k = 0; k < lb; ++k) {
				if (ca[i + k] == cb[k]) {
					if (k == (lb - 1)) {
						return i;
					}
				} else {
					break;
				}
			}
		}
		return -1;
	}

}
