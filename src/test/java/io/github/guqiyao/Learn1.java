package io.github.guqiyao;

/**
 * @Author: qiyao.gu
 * @Eamil: qiyao.gu@nalaa.com
 * @Date: 2019/6/4 13:48
 */
public class Learn1 {

	private static final int MAX = 2147483647;
	private static final int MIN = -2147483648;

	public static void main(String[] args) {
//		int reverseNumber = reverse(123);
//		int reverseNumber = reverse(-123);
//		int reverseNumber = reverse(1534236469);
//		int reverseNumber = reverse(-2147483412);
//		int reverseNumber = reverse(120);
//		int reverseNumber = reverse(901000);
		int reverseNumber = reverse(-2147483648);


		System.out.println(reverseNumber);
//		System.out.println(Integer.MAX_VALUE);

	}

	private static int reverse(int x) {
		if (MIN == x) {
			return 0;
		}

		boolean isMinus = isMinus(x);

		int absValue = abs(x);

		long reverseNumber = 0;
		do {

			long temp = absValue % 10;
			reverseNumber = reverseNumber * 10 + temp;

			absValue /= 10;
		} while (absValue != 0);

		if (reverseNumber > MAX) {
			return 0;
		}

		return (int) (isMinus ? -reverseNumber : reverseNumber);
	}

	private static int abs(int x) {
		return isMinus(x) ? -x : x;
	}

	private static boolean isMinus(int number) {
		return number < 0;
	}
}