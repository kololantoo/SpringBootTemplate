package com.kolosensei.springboottooltemplate.template.generic;

import java.time.LocalDate;

/**
 * @author: zhengyang
 * @version: 1.0
 * @date: 2021/8/18
 * @description:
 */
public class PairTest1 {

    public static void main(String[] args) {
        String[] words = {"marry", "had", "a", "little", "lamb"};
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println("min: " + mm.getFirst());
        System.out.println("max: " + mm.getSecond());

        String middle = ArrayAlg.<String>getMiddle("marry", "had", "a", "little", "lamb");
        String middle2 = ArrayAlg.getMiddle("marry", "had", "a", "little", "lamb");
        Number middle1 = ArrayAlg.getMiddle(3.14, 1, 1000);
    }
}

/**
 * 获取数组中最大和最小的字符串
 */
class ArrayAlg {
    public static Pair<String> minmax(String[] a) {
        if (a == null || a.length == 0) return null;
        String min = a[0];
        String max = a[0];
        for (String s : a) {
            if (min.compareTo(s) > 0) min = s;
            if (max.compareTo(s) < 0) max = s;
        }
        return new Pair<>(min, max);
    }

    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }

    public static <T extends Comparable> T min(T[] a) {
        if (a == null || a.length == 0) return null;
        T smallest = a[0];
        for (T t : a) {
            if (smallest.compareTo(t) > 0) smallest = t;
        }
        return smallest;
    }
}
