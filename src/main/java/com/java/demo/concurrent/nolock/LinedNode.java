package com.java.demo.concurrent.nolock;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: xinyuan.ymm
 * @create: 2018-03-26 上午11:40
 */
public class LinedNode<T> implements Serializable {

    private static final long serialVersionUID = -1;

    private T content;

    private AtomicReference<LinedNode<T>> next;

    public LinedNode(T content,
        AtomicReference<LinedNode<T>> next) {
        this.content = content;
        this.next = next;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public AtomicReference<LinedNode<T>> getNext() {
        return next;
    }

    public void setNext(AtomicReference<LinedNode<T>> next) {
        this.next = next;
    }
}
