package com.kolosensei.springboottooltemplate.algorithm.sort;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2021/2/25 14:43
 * @description: 算法第四版——选择排序
 *
 * 实现思想：找到元素中最小的元素，将它和数组的第一个元素交换位置，然后在剩余元素中找出最小元素，和第二个元素交换位置，以此类推。
 *         因为是不断选择剩余元素中的最小者，因此说是选择排序
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] data = {3,2,1,8,2,5,3,0,9};
        select(data);
        for (int datum : data) {
            System.out.println(datum);
        }
    }

    /**
     * 选择排序：
     * 1. 运行时间和输入无关，无论传入的数组是否有序，因为不记录输入的初始状态，总是N^2/2次比较
     * 2. 数据移动最少，每次交换都会改变两个数组元素的值，因此有N次交换，交换次数和数组大小是线性关系
     * @param data
     */
    private static void select(int[] data) {
        int length = data.length;
        for (int i=0;i<length;i++) {
            int min = i;
            //选择剩余元素中的最大元素
            for (int j = i+1;j<length;j++) {
                //注意这里是小于data[min]
                if (data[j] < data[min]) {
                    min = j;
                }
            }
            //交换
            int tmp = data[i];
            data[i] = data[min];
            data[min] = tmp;
        }
    }
}
