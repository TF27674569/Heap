# 堆
最大（小）的完全二叉树，在这个树中，每个节点的值大于（小于）或等于其他子节点的值  
图如下：  

![大顶堆](https://github.com/TF27674569/Heap/blob/master/image/maxheap.png)![小顶堆](https://github.com/TF27674569/Heap/blob/master/image/miniheap.png)

## 大顶堆
最大的[完全二叉树](https://github.com/TF27674569/Tree)，满足[完全二叉树](https://github.com/TF27674569/Tree)，是一个有序的数据结构  
所以只需要在[完全二叉树](https://github.com/TF27674569/Tree)的基础上满足每个节点的值大于或等于其他子节点的值，就是大顶堆.
1.数据存储使用数组的结构
2.节点之间的关系满足[完全二叉树](https://github.com/TF27674569/Tree)的规律  
3.插入时需要判断当前节点与父节点的大小关系  
4.<font color=#DC143C  face="黑体">删除：由于删除子节点，会破坏树的结构，所以在堆中，我们删除堆顶，将树的最后一个元素补齐堆顶，然后排序（按大小堆的规律）</font>  

#### 插入
```java
    public void insert(int date) {
        // 扩容
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
```
#### 删除
```java
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
```

