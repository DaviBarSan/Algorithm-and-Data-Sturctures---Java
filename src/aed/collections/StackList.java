package aed.collections;

import java.util.Iterator;

// Create Stack Using nexted list 
public class StackList<T> implements Iterable<T> {

    // A nexted list node
    public class Node<T> {
        public Node<T> next = null;
        public T data;

        public Node(T data) {
            this.data = data;
        };
    }

    // create global top reference variable global
    public Node<T> top;
    int size;

    // Constructor
    public StackList() {
        this.top = null;
        this.size = 0;
    }

    // Utility function to add an element x in the stack
    public void push(T x) { // insert at the beginning
        // create new node newNode and allocate memory
        Node<T> newNode = new Node<T>(x);


        // put top reference into newNode next
        newNode.next = top;
        // update top reference
        top = newNode;
        // updating stack size
        size++;
    }

    // Utility function to check if the stack is empty or not
    public boolean isEmpty() {
        return top == null;
    }

    // Utility function to return top element in a stack
    public T peek() {
        // check for empty stack

        if (!isEmpty()) {
            return top.data;
        }

        return null;
    }

    public T pop() {
        if (top == null) {
            return null;
        }

        
        var topData = top.data;
        top = top.next;
        size--;
        return topData;
    }

    public int size() {
        return size;

    }

    public T lastData() {
        if (isEmpty()) {
            return null;
        } else if (top.next == null) {
            return top.data;
        }
        while (top.next != null) {
            top = top.next;
        }
        return top.data;
    }


    private class StackIterator implements Iterator<T> {

        public Node<T> currentNode = top;
        

        public StackIterator() {
            currentNode = top;
        }

        @Override
        public boolean hasNext() {
        	return currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        };
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }  
        
    public StackList<T> shallowCopy() {
    	
  	
        StackList<T> newStackList = new StackList<T>();
        
        var i = this.iterator();
        while (newStackList.size != size() ) {
        	while (!i.hasNext()) {
        		newStackList.push(i.next());
            }

        }
                return newStackList;
    }
    
	
}