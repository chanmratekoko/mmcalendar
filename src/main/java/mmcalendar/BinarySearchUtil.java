package mmcalendar;

/**
 * Binary Search Utility
 * 
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @version 1.0
 *
 */
public final class BinarySearchUtil {

	/**
	 * Don't let anyone instantiate this class.
	 */
	private BinarySearchUtil() {
	}


	/**
	 * Search first dimension in a 2D array
	 * @param key search key
	 * @param array search in array
	 * @return index
	 */
	public static int search(double key, int[][] array) {
		int i;
		int l = 0;
		int u = array.length - 1;
		while (u >= l) {
			i = (int) Math.floor((l + u) / 2.0);
			if (array[i][0] > key) {
				u = i - 1;
			} else if (array[i][0] < key) {
				l = i + 1;
			} else {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Search first dimension in a 1D array
	 * @param key search key
	 * @param array search in array
	 * @return index
	 */
	public static int search(double key, int[] array) {
		int i;
		int l = 0;
		int u = array.length - 1;

		while (u >= l) {
			i = (int) Math.floor((l + u) / 2.0);
			if (array[i] > key) {
				u = i - 1;
			} else if (array[i] < key) {
				l = i + 1;
			} else {
				return i;
			}
		}
		return -1;
	}
}
