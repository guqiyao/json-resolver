package io.github.guqiyao.test;

import java.util.Collection;
import java.util.List;

/**
 * @Author: qiyao.gu
 * @Eamil: qiyao.gu@nalaa.com
 * @Date: 2019/7/22 14:14
 */
public class Test {

	public static void main(String[] args) {
		Class<?> list = List.class;

		System.out.println(Collection.class.isAssignableFrom(list));
	}
}