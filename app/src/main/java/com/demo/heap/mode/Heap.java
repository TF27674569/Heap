package com.demo.heap.mode;

/**
 * Description : 堆抽象接口 结构必须满足完全二叉树
 * <p/>
 * Created : TIAN FENG
 * Date : 2018/5/10
 * Email : 27674569@qq.com
 * Version : 1.0
 */
public interface Heap {

    /**
     * 插入数据
     *
     * @param date
     */
    void insert(int date);


    /**
     * 删除堆只处理堆顶
     */
    void deleteHeapTop();

    /**
     * 是否是空堆
     */
    boolean isEmpty();

    /**
     * 获取堆顶元素
     * @return
     */
    int getHeadTop();

    /**
     * 打印数组中的存储结构
     */
    void printArr();
}
