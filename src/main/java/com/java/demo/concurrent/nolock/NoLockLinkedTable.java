package com.java.demo.concurrent.nolock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 无锁队列，采用CAS原子操作符：AtomicReference & 自旋 方式操作；
 * AtomicReference： 原子性引用
 *
 * @author: xinyuan.ymm
 * @create: 2018-03-26 上午11:40
 */
public class NoLockLinkedTable<T> {

    private LinedNode<T> head;

    private int size;

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean contains(Object o) {
        if (o != null) {
            for (LinedNode<T> node = head; node != null; node = node.getNext().get()) {
                if (o.equals(node.getContent())) {
                    return true;
                }
            }
        } else {
            for (LinedNode<T> node = head; node != null; node = node.getNext().get()) {
                if (node.getContent() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 无锁链表，如果不进行并发控制，可能导致新增的节点或者对应插入的节点数据丢失
     *
     * @param newNode
     */
    public void add(LinedNode<T> newNode) {
        for (; ; ) {
            AtomicReference headRef = new AtomicReference(head);
            if (headRef.compareAndSet(null, newNode)) {
                return;
            } else {
                LinedNode nextNode = head.getNext().get();
                newNode.getNext().set(nextNode);
                if (head.getNext().compareAndSet(nextNode, newNode)) {
                    return;
                }

            }
        }
    }

    public void remove(Object o) {
        return;
    }

}
