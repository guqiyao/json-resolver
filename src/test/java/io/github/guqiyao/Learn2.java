package io.github.guqiyao;

/**
 * @Author: qiyao.gu
 * @Eamil: qiyao.gu@nalaa.com
 * @Date: 2019/6/4 15:12
 */
public class Learn2 {

	public static void main(String[] args) {
		boolean isPalindrome = isPalindrome(121);

		System.out.println(isPalindrome);
	}

	private static boolean isPalindrome(int x) {
		boolean isMinus = isMinus(x);
		if (isMinus) {
			return false;
		}

		int absValue = x;
		long reverseNumber = 0;
		do {

			long temp = absValue % 10;
			reverseNumber = reverseNumber * 10 + temp;

			absValue /= 10;
		} while (absValue != 0);

		return reverseNumber == x;
	}

	private static boolean isMinus(int x) {
		return x < 0;
	}
}