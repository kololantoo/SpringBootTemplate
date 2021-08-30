package com.kolosensei.springboottooltemplate.algorithm.sort;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2021/2/25 15:07
 * @description: 算法第四版——插入排序
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] data = {3,2,1,8,2,5,3,0,9};
        insert(data);
        for (int datum : data) {
            System.out.println(datum);
        }
    }

    /**
     * 插入排序
     * 核心思想：从剩余元素中，选取元素，插入已经有序的序列中，插入之后，后面的元素全部向右移动一位。当索引到数组右端时，排序就完成了。
     * 插入排序取决于输入元素的初始顺序，对一个接近有序的排序速度，会比随机数组或逆序数组快很多
     * 平均需要N^2/4次比较和交换，最坏需要N^2/2次比较和交换，最好情况下，需要N-1次比较和0次交换
     * @param data
     */
    private static void insert(int[] data) {
        int length = data.length;
        for (int i=1;i<length;i++) {
            //和左边的有序序列进行比较
            for (int j = i;j>0;j--) {
                if (data[j]<data[j-1]) {
                    int tmp = data[j];
                    data[j] = data[j-1];
                    data[j-1] = tmp;
                }
            }
        }
    }

    /**
     * 插入排序的优化，折半插入排序算法
     * 核心思想：在有序序列中，寻找插入位置时，使用二分查找的插入位置
     * @param data
     * todo 学习二分查找之后再来实现
     */
    private static void halfInsert(int[] data) {
        int length = data.length;
        for (int i=1;i<length;i++) {
            int tmp = data[i];
            //使用二分查找搜索插入位置
            int low = 0;
            int high = i-1;
            while (low < high) {
                int mid = low+high/2;
                if (data[mid] > tmp) {
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            }
        }
    }
}
