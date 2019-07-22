package io.github.guqiyao;

/**
 * @Author: qiyao.gu
 * @Eamil: qiyao.gu@nalaa.com
 * @Date: 2019/6/4 10:33
 */
public class Learn {

	public static void main(String[] args) {
//		int[] result = twoSum(new int[]{-3, 4, 3, -90}, 0);
//		int[] result = twoSum(new int[]{1, 4, 5, -90}, 6);
//		int[] result = twoSum(new int[]{-10, 7, 19, 15}, 9);
//		int[] result = twoSum(new int[]{-1, -2, -3, -4, -5}, -8);
		int[] result = twoSum(new int[]{3, 2, 4}, 6);

		System.out.println(String.format("out 0 : %d, 1 : %d", result[0], result[1]));
	}

	public static int[] twoSum(int[] nums, int target) {

		int i = 0;
		while (true) {
			for (int j = i + 1; j < nums.length; j ++) {
				int num = nums[i];
				if (target - num == nums[j]) {
					return new int[]{i, j};
				}
			}
			i ++;
		}
	}

	private static int abs(int num) {
		return num > 0 ? num : -num;
	}
}