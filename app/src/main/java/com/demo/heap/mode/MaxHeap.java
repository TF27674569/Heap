package com.demo.heap.mode;

import android.util.Log;

import java.util.Arrays;

/**
 * Description : 大顶堆
 * <p/>          根结点（亦称为堆顶）的关键字是堆里所有结点关键字中最大者，称为大根堆，又称最大堆（大顶堆）。
 * 大根堆要求根节点的关键字既大于或等于左子树的关键字值，又大于或等于右子树的关键字值。
 * 这里默认为 1 2 3 4 5 6 7 8 9 10 的大根堆
 * <p>                      10
 * <p>               9              6
 * <p>           7       8      2      5
 * <p>        1    4   3
 * Created : TIAN FENG
 * Date : 2018/5/10
 * Email : 27674569@qq.com
 * Version : 1.0
 */
public class MaxHeap implements Heap {


    /**
     * 默认长度
     */
    private static int sDefaultLen = 10;

    /**
     * 堆容器
     */
    private int[] heapContainer;

    /**
     * 容器长度
     */
    private int size;


    public MaxHeap() {
        heapContainer = new int[sDefaultLen];
    }


    /**
     * 插入数据
     * 插入数据时需要判断 自身有没有父节点
     * 有父节点需要判断，需要与父节点进行位置交换
     * 如果不需要交换直接跳出，因为这是一个有序的堆
     * 交换后需要判断父节点是否还有父节点，有则进行交换
     *
     * @param date
     */
    @Override
    public void insert(int date) {

        resize();

        // 赋值
        heapContainer[size] = date;

        // 判断是否需要进行位置调换
        // 只判断自己和父节点

        // 定义临时位置为最后一个
        int tempindex = size;

        // 通过index来判断是否需要调换条件
        while (tempindex != 0) {

            // 计算父节点
            int parentIndex = (tempindex - 1) >> 1;

            // 如果比父节点大 则需要进行位置调换
            if (heapContainer[tempindex] > heapContainer[parentIndex]) {
                // 位置交换的算法
                swap(tempindex, parentIndex);
            } else {
                break;
            }

            // 当前位置在父节点位置上  如果父节点位置还有父节点 再重新进行位置调整
            tempindex = parentIndex;
        }

        // 容量+1
        size++;

    }

    /**
     * 扩容
     */
    private void resize() {
        // 是否到达扩容点
        if (size >= heapContainer.length) {
            // 进行扩容每次扩容 sDefaultLen 长
            heapContainer = Arrays.copyOf(heapContainer, heapContainer.length + sDefaultLen);
        }
    }

    /**
     * 删除堆只处理堆顶
     * <p>
     * 堆的结构不能随便改变，所以删除不能删除子节点，只能删除堆顶
     * 删除步骤：
     * 1.移除堆顶元素
     * 2.将最后一个元素放在堆顶
     * 3.调整堆
     * 4.堆大小减一
     */
    @Override
    public void deleteHeapTop() {

        // 空堆不可删
        if (isEmpty()) {
            return;
        }

        // 大于一个元素才可以操作
        if (size > 1) {
            // 将最后一个元素放在堆顶
            heapContainer[0] = heapContainer[size - 1];
            heapContainer[size - 1] = 0;
        }

        // 将最后一个元素放在堆顶，最大堆的结构已经破坏
        // 需要重新调整堆
        int tempIndex = 0;

        while (true) {
            // 左节点
            int leftIndex = (tempIndex << 1) + 1;
            // 右节点
            int rightIndex = (tempIndex << 1) + 2;

            // 判断左右子树是否存在
            if (leftIndex > size - 1 && rightIndex > size - 1) {
                // 如果越界直接退出
                break;
            }

            // 判断跟左子树比还是根右子树比
            // 默认跟左子树比
            boolean isLeft = true;

            // 如果没有越界存在右子树
            if (rightIndex <= size - 1) {
                // 我们先判断左右子树谁大谁小

                // 如果左子树大于右子树 则左子树与父节点比，否则右子树与父节点比
                isLeft = heapContainer[leftIndex] > heapContainer[rightIndex];
            }

            if (isLeft) {
                // 如果与左子树比较
                // 需要交换
                if (heapContainer[leftIndex] > heapContainer[tempIndex]) {
                    swap(leftIndex, tempIndex);
                    tempIndex = leftIndex;
                } else {
                    break;
                }

            } else {
                // 如果与右子树比较
                if (heapContainer[rightIndex] > heapContainer[tempIndex]) {
                    swap(rightIndex, tempIndex);
                    tempIndex = rightIndex;
                } else {
                    break;
                }
            }
        }


        // 删除后容量-1
        size--;

    }

    /**
     * 是否是空堆
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取堆顶元素
     *
     * @return
     */
    @Override
    public int getHeadTop() {
        if (isEmpty()) {
            throw new IllegalStateException("heap is empty.");
        }
        return heapContainer[0];
    }

    /**
     * 打印数组中的存储结构
     */
    @Override
    public void printArr() {
        for (int i = 0; i < size; i++) {
            Log.e("MaxHeap", String.valueOf(heapContainer[i]));
        }
    }

    /**
     * 交换位置
     */
    private void swap(int index1, int index2) {
        int temp = heapContainer[index1];
        heapContainer[index1] = heapContainer[index2];
        heapContainer[index2] = temp;
    }
}
