package org.mechaevil.algos.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ArrayUtils {

	private static Random random = new Random();

	// Array reverse
	public static boolean[] reverse(boolean[] a) {
		return reverse(a, 0, a.length);
	}

	public static boolean[] reverse(boolean[] a, int fromIndex, int toIndex) {
		while (fromIndex < toIndex) {
			swap(a, fromIndex++, --toIndex);
		}
		return a;
	}

	public static byte[] reverse(byte[] a) {
		return reverse(a, 0, a.length);
	}

	public static byte[] reverse(byte[] a, int fromIndex, int toIndex) {
		while (fromIndex < toIndex) {
			swap(a, fromIndex++, --toIndex);
		}
		return a;
	}

	public static char[] reverse(char[] a) {
		return reverse(a, 0, a.length);
	}

	public static char[] reverse(char[] a, int fromIndex, int toIndex) {
		while (fromIndex < toIndex) {
			swap(a, fromIndex++, --toIndex);
		}
		return a;
	}

	public static double[] reverse(double[] a) {
		return reverse(a, 0, a.length);
	}

	public static double[] reverse(double[] a, int fromIndex, int toIndex) {
		while (fromIndex < toIndex) {
			swap(a, fromIndex++, --toIndex);
		}
		return a;
	}

	public static float[] reverse(float[] a) {
		return reverse(a, 0, a.length);
	}

	public static float[] reverse(float[] a, int fromIndex, int toIndex) {
		while (fromIndex < toIndex) {
			swap(a, fromIndex++, --toIndex);
		}
		return a;
	}

	public static int[] reverse(int[] a) {
		return reverse(a, 0, a.length);
	}

	public static int[] reverse(int[] a, int fromIndex, int toIndex) {
		while (fromIndex < toIndex) {
			swap(a, fromIndex++, --toIndex);
		}
		return a;
	}

	public static long[] reverse(long[] a) {
		return reverse(a, 0, a.length);
	}

	public static long[] reverse(long[] a, int fromIndex, int toIndex) {
		while (fromIndex < toIndex) {
			swap(a, fromIndex++, --toIndex);
		}
		return a;
	}

	public static Object[] reverse(Object[] a) {
		return reverse(a, 0, a.length);
	}

	public static Object[] reverse(Object[] a, int fromIndex, int toIndex) {
		while (fromIndex < toIndex) {
			swap(a, fromIndex++, --toIndex);
		}
		return a;
	}

	public static short[] reverse(short[] a) {
		return reverse(a, 0, a.length);
	}

	public static short[] reverse(short[] a, int fromIndex, int toIndex) {
		while (fromIndex < toIndex) {
			swap(a, fromIndex++, --toIndex);
		}
		return a;
	}

	// Array swap
	public static void swap(boolean[] a, int i, int j) {
		boolean t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void swap(byte[] a, int i, int j) {
		byte t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void swap(char[] a, int i, int j) {
		char t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void swap(double[] a, int i, int j) {
		double t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void swap(float[] a, int i, int j) {
		float t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void swap(long[] a, int i, int j) {
		long t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void swap(Object[] a, int i, int j) {
		Object t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void swap(short[] a, int i, int j) {
		short t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	// Array indexOf

	public static int indexOf(boolean[] a, boolean key) {
		return indexOf(a, 0, key);
	}

	public static int indexOf(boolean[] a, int fromIndex, boolean key) {
		int l = a.length;
		while (fromIndex < l) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex++;
		}
		return -1;
	}

	public static int indexOf(byte[] a, byte key) {
		return indexOf(a, 0, key);
	}

	public static int indexOf(byte[] a, int fromIndex, byte key) {
		int l = a.length;
		while (fromIndex < l) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex++;
		}
		return -1;
	}

	public static int indexOf(char[] a, char key) {
		return indexOf(a, 0, key);
	}

	public static int indexOf(char[] a, int fromIndex, char key) {
		int l = a.length;
		while (fromIndex < l) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex++;
		}
		return -1;
	}

	public static int indexOf(double[] a, double key) {
		return indexOf(a, 0, key);
	}

	public static int indexOf(double[] a, int fromIndex, double key) {
		int l = a.length;
		while (fromIndex < l) {
			if (Double.compare(a[fromIndex], key) == 0)
				return fromIndex;
			fromIndex++;
		}
		return -1;
	}

	public static int indexOf(float[] a, float key) {
		return indexOf(a, 0, key);
	}

	public static int indexOf(float[] a, int fromIndex, float key) {
		int l = a.length;
		while (fromIndex < l) {
			if (Float.compare(a[fromIndex], key) == 0)
				return fromIndex;
			fromIndex++;
		}
		return -1;
	}

	public static int indexOf(int[] a, int key) {
		return indexOf(a, 0, key);
	}

	public static int indexOf(int[] a, int fromIndex, int key) {
		int l = a.length;
		while (fromIndex < l) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex++;
		}
		return -1;
	}

	public static int indexOf(long[] a, long key) {
		return indexOf(a, 0, key);
	}

	public static int indexOf(long[] a, int fromIndex, long key) {
		int l = a.length;
		while (fromIndex < l) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex++;
		}
		return -1;
	}

	public static int indexOf(Object[] a, Object key) {
		return indexOf(a, 0, key);
	}

	public static int indexOf(Object[] a, int fromIndex, Object key) {
		int l = a.length;
		while (fromIndex < l) {
			if (a[fromIndex].equals(key))
				return fromIndex;
			fromIndex++;
		}
		return -1;
	}

	public static int indexOf(short[] a, short key) {
		return indexOf(a, 0, key);
	}

	public static int indexOf(short[] a, int fromIndex, short key) {
		int l = a.length;
		while (fromIndex < l) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex++;
		}
		return -1;
	}

	// Array lastIndexOf
	public static int lastIndexOf(boolean[] a, boolean key) {
		return lastIndexOf(a, a.length - 1, key);
	}

	public static int lastIndexOf(boolean[] a, int fromIndex, boolean key) {
		while (fromIndex >= 0) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex--;
		}
		return -1;
	}

	public static int lastIndexOf(byte[] a, byte key) {
		return lastIndexOf(a, a.length - 1, key);
	}

	public static int lastIndexOf(byte[] a, int fromIndex, byte key) {
		while (fromIndex >= 0) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex--;
		}
		return -1;
	}

	public static int lastIndexOf(char[] a, char key) {
		return lastIndexOf(a, a.length - 1, key);
	}

	public static int lastIndexOf(char[] a, int fromIndex, char key) {
		while (fromIndex >= 0) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex--;
		}
		return -1;
	}

	public static int lastIndexOf(double[] a, double key) {
		return lastIndexOf(a, a.length - 1, key);
	}

	public static int lastIndexOf(double[] a, int fromIndex, double key) {
		while (fromIndex >= 0) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex--;
		}
		return -1;
	}

	public static int lastIndexOf(float[] a, float key) {
		return lastIndexOf(a, a.length - 1, key);
	}

	public static int lastIndexOf(float[] a, int fromIndex, float key) {
		while (fromIndex >= 0) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex--;
		}
		return -1;
	}

	public static int lastIndexOf(int[] a, int key) {
		return lastIndexOf(a, a.length - 1, key);
	}

	public static int lastIndexOf(int[] a, int fromIndex, int key) {
		while (fromIndex >= 0) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex--;
		}
		return -1;
	}

	public static int lastIndexOf(long[] a, long key) {
		return lastIndexOf(a, a.length - 1, key);
	}

	public static int lastIndexOf(long[] a, int fromIndex, long key) {
		while (fromIndex >= 0) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex--;
		}
		return -1;
	}

	public static int lastIndexOf(Object[] a, Object key) {
		return lastIndexOf(a, a.length - 1, key);
	}

	public static int lastIndexOf(Object[] a, int fromIndex, Object key) {
		while (fromIndex >= 0) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex--;
		}
		return -1;
	}

	public static int lastIndexOf(short[] a, short key) {
		return lastIndexOf(a, a.length - 1, key);
	}

	public static int lastIndexOf(short[] a, int fromIndex, short key) {
		while (fromIndex >= 0) {
			if (a[fromIndex] == key)
				return fromIndex;
			fromIndex--;
		}
		return -1;
	}

	// Array shuffle
	public static boolean[] shuffle(boolean[] a) {
		return shuffle(a, 0, a.length);
	}

	public static boolean[] shuffle(boolean[] a, int fromIndex, int toIndex) {
		for (int i = toIndex - 1; i > fromIndex; i--)
			swap(a, i, random.nextInt(i + 1));
		return a;
	}

	public static byte[] shuffle(byte[] a) {
		return shuffle(a, 0, a.length);
	}

	public static byte[] shuffle(byte[] a, int fromIndex, int toIndex) {
		for (int i = toIndex - 1; i > fromIndex; i--)
			swap(a, i, random.nextInt(i + 1));
		return a;
	}

	public static char[] shuffle(char[] a) {
		return shuffle(a, 0, a.length);
	}

	public static char[] shuffle(char[] a, int fromIndex, int toIndex) {
		for (int i = toIndex - 1; i > fromIndex; i--)
			swap(a, i, random.nextInt(i + 1));
		return a;
	}

	public static double[] shuffle(double[] a) {
		return shuffle(a, 0, a.length);
	}

	public static double[] shuffle(double[] a, int fromIndex, int toIndex) {
		for (int i = toIndex - 1; i > fromIndex; i--)
			swap(a, i, random.nextInt(i + 1));
		return a;
	}

	public static float[] shuffle(float[] a) {
		return shuffle(a, 0, a.length);
	}

	public static float[] shuffle(float[] a, int fromIndex, int toIndex) {
		for (int i = toIndex - 1; i > fromIndex; i--)
			swap(a, i, random.nextInt(i + 1));
		return a;
	}

	public static int[] shuffle(int[] a) {
		return shuffle(a, 0, a.length);
	}

	public static int[] shuffle(int[] a, int fromIndex, int toIndex) {
		for (int i = toIndex - 1; i > fromIndex; i--)
			swap(a, i, random.nextInt(i + 1));
		return a;
	}

	public static long[] shuffle(long[] a) {
		return shuffle(a, 0, a.length);
	}

	public static long[] shuffle(long[] a, int fromIndex, int toIndex) {
		for (int i = toIndex - 1; i > fromIndex; i--)
			swap(a, i, random.nextInt(i + 1));
		return a;
	}

	public static Object[] shuffle(Object[] a) {
		return shuffle(a, 0, a.length);
	}

	public static Object[] shuffle(Object[] a, int fromIndex, int toIndex) {
		for (int i = toIndex - 1; i > fromIndex; i--)
			swap(a, i, random.nextInt(i + 1));
		return a;
	}

	public static short[] shuffle(short[] a) {
		return shuffle(a, 0, a.length);
	}

	public static short[] shuffle(short[] a, int fromIndex, int toIndex) {
		for (int i = toIndex - 1; i > fromIndex; i--)
			swap(a, i, random.nextInt(i + 1));
		return a;
	}

	// Array min
	public static byte min(byte[] a) {
		return min(a, 0, a.length);
	}

	public static byte min(byte[] a, int fromIndex, int toIndex) {
		byte min = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (min > a[fromIndex])
				min = a[fromIndex];
			fromIndex++;
		}
		return min;
	}

	public static char min(char[] a) {
		return min(a, 0, a.length);
	}

	public static char min(char[] a, int fromIndex, int toIndex) {
		char min = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (min > a[fromIndex])
				min = a[fromIndex];
			fromIndex++;
		}
		return min;
	}

	public static double min(double[] a) {
		return min(a, 0, a.length);
	}

	public static double min(double[] a, int fromIndex, int toIndex) {
		double min = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (min > a[fromIndex])
				min = a[fromIndex];
			fromIndex++;
		}
		return min;
	}

	public static float min(float[] a) {
		return min(a, 0, a.length);
	}

	public static float min(float[] a, int fromIndex, int toIndex) {
		float min = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (min > a[fromIndex])
				min = a[fromIndex];
			fromIndex++;
		}
		return min;
	}

	public static int min(int[] a) {
		return min(a, 0, a.length);
	}

	public static int min(int[] a, int fromIndex, int toIndex) {
		int min = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (min > a[fromIndex])
				min = a[fromIndex];
			fromIndex++;
		}
		return min;
	}

	public static long min(long[] a) {
		return min(a, 0, a.length);
	}

	public static long min(long[] a, int fromIndex, int toIndex) {
		long min = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (min > a[fromIndex])
				min = a[fromIndex];
			fromIndex++;
		}
		return min;
	}

	public static Object min(Object[] a, Comparator<Object> comparator) {
		return min(a, 0, a.length, comparator);
	}

	public static Object min(Object[] a, int fromIndex, int toIndex,
			Comparator<Object> comparator) {
		Object min = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (comparator.compare(min, a[fromIndex]) > 0)
				min = a[fromIndex];
			fromIndex++;
		}
		return min;
	}

	public static short min(short[] a) {
		return min(a, 0, a.length);
	}

	public static short min(short[] a, int fromIndex, int toIndex) {
		short min = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (min > a[fromIndex])
				min = a[fromIndex];
			fromIndex++;
		}
		return min;
	}

	// Array max
	public static byte max(byte[] a) {
		return max(a, 0, a.length);
	}

	public static byte max(byte[] a, int fromIndex, int toIndex) {
		byte max = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (max < a[fromIndex])
				max = a[fromIndex];
			fromIndex++;
		}
		return max;
	}

	public static char max(char[] a) {
		return max(a, 0, a.length);
	}

	public static char max(char[] a, int fromIndex, int toIndex) {
		char max = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (max < a[fromIndex])
				max = a[fromIndex];
			fromIndex++;
		}
		return max;
	}

	public static double max(double[] a) {
		return max(a, 0, a.length);
	}

	public static double max(double[] a, int fromIndex, int toIndex) {
		double max = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (max < a[fromIndex])
				max = a[fromIndex];
			fromIndex++;
		}
		return max;
	}

	public static float max(float[] a) {
		return max(a, 0, a.length);
	}

	public static float max(float[] a, int fromIndex, int toIndex) {
		float max = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (max < a[fromIndex])
				max = a[fromIndex];
			fromIndex++;
		}
		return max;
	}

	public static int max(int[] a) {
		return max(a, 0, a.length);
	}

	public static int max(int[] a, int fromIndex, int toIndex) {
		int max = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (max < a[fromIndex])
				max = a[fromIndex];
			fromIndex++;
		}
		return max;
	}

	public static long max(long[] a) {
		return max(a, 0, a.length);
	}

	public static long max(long[] a, int fromIndex, int toIndex) {
		long max = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (max < a[fromIndex])
				max = a[fromIndex];
			fromIndex++;
		}
		return max;
	}

	public static Object max(Object[] a, Comparator<Object> comparator) {
		return max(a, 0, a.length, comparator);
	}

	public static Object max(Object[] a, int fromIndex, int toIndex,
			Comparator<Object> comparator) {
		Object max = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (comparator.compare(max, a[fromIndex]) < 0)
				max = a[fromIndex];
			fromIndex++;
		}
		return max;
	}

	public static short max(short[] a) {
		return max(a, 0, a.length);
	}

	public static short max(short[] a, int fromIndex, int toIndex) {
		short max = a[fromIndex++];
		while (fromIndex < toIndex) {
			if (max < a[fromIndex])
				max = a[fromIndex];
			fromIndex++;
		}
		return max;
	}

	// Array Sum
	public static int sum(byte[] a) {
		return sum(a, 0, a.length);
	}

	public static int sum(byte[] a, int fromIndex, int toIndex) {
		int s = 0;
		for (int i = fromIndex; i < toIndex; i++)
			s += a[i];
		return s;
	}

	public static int sum(char[] a) {
		return sum(a, 0, a.length);
	}

	public static int sum(char[] a, int fromIndex, int toIndex) {
		int s = 0;
		for (int i = fromIndex; i < toIndex; i++)
			s += a[i];
		return s;
	}

	public static double sum(double[] a) {
		return sum(a, 0, a.length);
	}

	public static double sum(double[] a, int fromIndex, int toIndex) {
		double s = 0;
		for (int i = fromIndex; i < toIndex; i++)
			s += a[i];
		return s;
	}

	public static float sum(float[] a) {
		return sum(a, 0, a.length);
	}

	public static float sum(float[] a, int fromIndex, int toIndex) {
		float s = 0;
		for (int i = fromIndex; i < toIndex; i++)
			s += a[i];
		return s;
	}

	public static int sum(int[] a) {
		return sum(a, 0, a.length);
	}

	public static int sum(int[] a, int fromIndex, int toIndex) {
		int s = 0;
		for (int i = fromIndex; i < toIndex; i++)
			s += a[i];
		return s;
	}

	public static long sum(long[] a) {
		return sum(a, 0, a.length);
	}

	public static long sum(long[] a, int fromIndex, int toIndex) {
		long s = 0;
		for (int i = fromIndex; i < toIndex; i++)
			s += a[i];
		return s;
	}

	public static int sum(short[] a) {
		return sum(a, 0, a.length);
	}

	public static int sum(short[] a, int fromIndex, int toIndex) {
		int s = 0;
		for (int i = fromIndex; i < toIndex; i++)
			s += a[i];
		return s;
	}

	// Array Product
	public static int product(byte[] a) {
		return product(a, 0, a.length);
	}

	public static int product(byte[] a, int fromIndex, int toIndex) {
		int p = 1;
		for (int i = fromIndex; i < toIndex; i++)
			p *= a[i];
		return p;
	}

	public static int product(char[] a) {
		return product(a, 0, a.length);
	}

	public static int product(char[] a, int fromIndex, int toIndex) {
		int p = 1;
		for (int i = fromIndex; i < toIndex; i++)
			p *= a[i];
		return p;
	}

	public static double product(double[] a) {
		return product(a, 0, a.length);
	}

	public static double product(double[] a, int fromIndex, int toIndex) {
		double p = 1;
		for (int i = fromIndex; i < toIndex; i++)
			p *= a[i];
		return p;
	}

	public static float product(float[] a) {
		return product(a, 0, a.length);
	}

	public static float product(float[] a, int fromIndex, int toIndex) {
		float p = 1;
		for (int i = fromIndex; i < toIndex; i++)
			p *= a[i];
		return p;
	}

	public static long product(int[] a) {
		return product(a, 0, a.length);
	}

	public static long product(int[] a, int fromIndex, int toIndex) {
		int p = 1;
		for (int i = fromIndex; i < toIndex; i++)
			p *= a[i];
		return p;
	}

	public static long product(long[] a) {
		return product(a, 0, a.length);
	}

	public static long product(long[] a, int fromIndex, int toIndex) {
		long p = 1;
		for (int i = fromIndex; i < toIndex; i++)
			p *= a[i];
		return p;
	}

	public static int product(short[] a) {
		return product(a, 0, a.length);
	}

	public static int product(short[] a, int fromIndex, int toIndex) {
		int p = 1;
		for (int i = fromIndex; i < toIndex; i++)
			p *= a[i];
		return p;
	}

	// Boolean any,all,indices
	public static boolean any(boolean[] a) {
		return any(a, 0, a.length);
	}

	public static boolean any(boolean[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; i++)
			if (a[i])
				return true;
		return false;
	}

	public static boolean all(boolean[] a) {
		return all(a, 0, a.length);
	}

	public static boolean all(boolean[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; i++)
			if (!a[i])
				return false;
		return true;
	}

	public static boolean[] not(boolean[] a) {
		return not(a, 0, a.length);
	}

	public static boolean[] not(boolean[] a, int fromIndex, int toIndex) {
		for (int i = fromIndex; i < toIndex; i++)
			a[i] = !a[i];
		return a;
	}

	public static int[] indices(boolean[] a, boolean value) {
		return indices(a, 0, a.length, value);
	}

	public static int[] indices(boolean[] a, int fromIndex, int toIndex,
			boolean value) {
		int c = 0;
		for (int i = fromIndex; i < toIndex; i++)
			if (a[i] == value)
				c++;
		int[] indices = new int[c];
		for (int i = fromIndex, k = 0; i < toIndex; i++)
			if (a[i] == value)
				indices[k++] = i;
		return indices;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 3 };
		System.out.println(min(arr));
		System.out.println(Arrays.toString(arr));
		int N = 100;
		boolean[] composites = new boolean[N];
		composites[0] = composites[1] = true;
		for (int j = 4; j < N; j += 2)
			composites[j] = true;
		for (int i = 3, sqrt = (int) Math.sqrt(N); i <= sqrt; i += 2)
			if (!composites[i]) {
				for (int j = i * i; j < N; j += 2 * i)
					composites[j] = true;
			}

		int[] primes = indices(composites, true);
		System.out.println(Arrays.toString(primes));
	}
}
