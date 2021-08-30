package com.kolosensei.springboottooltemplate.algorithm.sort;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2021/2/25 15:36
 * @description: 希尔排序
 *
 */
public class ShellSort {

    public static void main(String[] args) {

        int[] data = {3,2,1,8,2,5,3,0,9};
        shell(data);
        for (int datum : data) {
            System.out.println(datum);
        }
    }

    /**
     * 核心思想：对插入排序的优化，对一个排序数组进行分组，每隔一定步长划分在一个分组，分组内使用插入排序进行排序，不断缩小步长，最后步长为1 时，进行最后一次插入排序
     * @param data
     */
    private static void shell(int[] data) {

    }
}
