package aed.collections;

import java.util.Iterator;
import java.lang.OutOfMemoryError;

public class QueueArray<T> implements Iterable<T> {

    private T queueArray[];
    public int size, head, rear;
    public int maxSize;

    @SuppressWarnings("unchecked")
    public QueueArray(int max) {
        maxSize = max;
        this.head = this.rear = this.size = 0;
        queueArray = (T[]) new Object[maxSize];
    }

    public Integer circularNext() {
        return (head + 1) % maxSize;
    }

    public Integer circularRear() {
        return (rear + 1) % maxSize;
    }

    public void enqueue(T item) {
        if (size == maxSize) {
            throw new OutOfMemoryError();
        }

        queueArray[rear] = item;
        rear = circularRear();
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        T dataHead = queueArray[head];
        size--;
        head = circularNext();
        return dataHead;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return queueArray[head];

    }

    private class QueueIterator implements Iterator<T> {

        private int index;
        private int itHead;

        public QueueIterator() {
            index = 0;
            itHead = head;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }

            T oldHead = queueArray[itHead];
            itHead = (itHead + 1) % maxSize;
            index++;
            return oldHead;
        };
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    };

    public QueueArray<T> shallowCopy() {

        QueueArray<T> newQueue = new QueueArray<T>(maxSize);
        var i = newQueue.iterator();
        T oldQueueHead = peek();

        while(newQueue.size() < size) {
            while(i.hasNext()) {
                newQueue.enqueue(oldQueueHead);
            }
        }
        return newQueue;
    };
}