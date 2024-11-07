package assn03;

// Starter Code provided with Assignment #3 for COMP210 Fall 2024

public class LinkedList<T> {
    private Node<T> _head = null;
    private Node<T> _tail = null;
    private int _size = 0;

    /**
     * Task1
     * Remove the node at index i of the list.
     * Note that the first element is at index 0
     * If i is larger than the _size of the list, throw an IndexOutOfBounds Exception
     *
     * ex: list: A -> B -> C -> D
     *     i: 1
     *     list after removeAtIndex: A -> C -> D
     *
     * @param i    - index of node to remove
     */
    public void removeAtIndex(int i) {
        if (this._size>0) {
            if (i >= this._size) {
                throw new IndexOutOfBoundsException();
            }
            Node<T> ptr = this._head;
            if (i == 0) {
                this._head = ptr.getNext();
                this._size--;
            } else {
                int j = 0;
                while (j != i - 1) {
                    ptr = ptr.getNext();
                    j++;
                }
                ptr.setNext(ptr.getNext().getNext());
                this._size--;
            }
        }
    }

    /**
     * Task2
     * Return true if this linked list is equal to the list argument, false otherwise.
     * Two lists are equal if they have the same _size, and the same
     * elements in the same order.
     * ex:  list: 1 -> 4 -> 2
     *      list2: 1 -> 4 -> 2
     *      return: true
     *
     *      list: 1 -> 5
     *      list2: 2 -> 5
     *      return false;
     *
     * @param list2 - the list to compare with the current list
     * @return true if the lists have the same elements in the same order, false otherwise
     */
    public boolean isEqual(LinkedList list2) {
        if (this._size==list2._size){
            Node<T> ptr1=this._head;
            Node<T> ptr2=list2._head;
            for (int i=0; i<this._size; i++){
                if (ptr1.getValue()!=ptr2.getValue()){
                    return false;
                }
                ptr1=ptr1.getNext();
                ptr2=ptr2.getNext();
            }
        }
        else{
            return false;
        }
        return true;
    }

    /**
     * Task3
     * Given a sorted linked list, remove the duplicate values from the list
     * ex: list: 5 -> 6 -> 7 -> 7 -> 7 -> 8 -> 8 -> 9
     *     list after removeRepeats: 5 -> 6 -> 7 -> 8 -> 9
     *
     */
    public void removeRepeats() {
        if (!(this._size==0 || this._size==1)){ // the program will not change anything if it cannot have repeats
            Node<T> ptr=this._head;
            for (int i=0; i<this._size; i++){
                if (ptr==this._tail){
                    break;
                }
                if (ptr.getNext().getValue()==ptr.getValue()){
                    ptr.setNext(ptr.getNext().getNext());
                    this._size--;
                }
                else {
                    ptr = ptr.getNext();
                }
            }
        }
    }

    /**
     * Task4
     * Reverse the first n nodes of the list.
     * eg list:  10 -> 9 -> 8 -> 7, and n = 2
     * list after reverse: 9 -> 10 -> 8 -> 7
     */
    public void reverse(int n) {
        if (this._size>1){
            Node<T> prev=null;
            Node<T> next=_head.getNext();
            if (n>this._size){
                n=this._size;
            }

            for (int i=0; i<n; i++) {
                next = this._head.getNext();
                this._head.setNext(prev);
                prev = this._head;
                if (next != null && i+1<n) {
                    _head = next;
                }
            }
            Node<T> ptr=this._head;
            if (next!=null){
                for (int j=0; j<n-1; j++){
                    ptr=ptr.getNext();
                }
                ptr.setNext(next);
            }
        }
    }

    /**
     * Task5
     * Merge the given linked list2 into the current list. The 2 lists will always be
     * either the same _size, or the current list will be longer than list2.
     * The examples below show how to handle each case.
     *
     * Note: Do NOT create and return a new list, merge the second list into the first one.
     *
     * ex: list: 1 -> 2 -> 3
     *     list2: 4 -> 5 -> 6
     *     return: 1 -> 4 -> 2 -> 5 -> 3 -> 6
     *
     *     list: 1 -> 2 -> 3 -> 4
     *     list2: 5 -> 6
     *     return 1 -> 5 -> 2 -> 6 -> 3 -> 4
     *
     * @param list2
     */
    public void merge(LinkedList list2) {
        if (this._size>0 && list2.size()>0) {
            Node<T> ptr1 = this._head;
            Node<T> ptr2 = list2._head;
            while (ptr2 != null) {
                Node<T> nextPtr2 = ptr2.getNext();
                ptr2.setNext(ptr1.getNext());
                ptr1.setNext(ptr2);
                ptr2 = nextPtr2;
                ptr1 = ptr1.getNext().getNext();
            }
        }
    }


    /* Implementations below are being given to you. Do not modify below this. */

    public int size() {
        return _size;
    }

    public boolean isEmpty() {
        return _size == 0;
    }

    public void clear() {
        _head = null;
        _tail = null;
        _size = 0;
    }

    public boolean contains(Object element) {
        Node<T> current = _head;
        while(current != null) {
            if(current.getValue().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public T[] toArray() {
        T[] arr =  (T[]) new Object[size()];
        Node<T> current = _head;
        int i = 0;
        if(isEmpty()) {
            return arr;
        }
        while(current != null){
            arr[i] = current.getValue();
            current = current.getNext();
            i++;
        }
        return arr;
    }

    public void add(Object element) {
        Node<T> newNode = new NodeImpl<T>((T) element, null);
        if(isEmpty()) {
            _head = newNode;
            _tail = newNode;
            _size++;
        } else {
            _tail.setNext(newNode);
            _tail = newNode;
            _size++;
        }

    }

    public boolean remove(Object element) {
        Node<T> current = _head;
        if(isEmpty()) {
            return false;
        }
        if(current.getValue() == element){
            _head = _head.getNext();
            _size--;
            return true;
        }
        while(current.getNext().getValue() != element) {
            current = current.getNext();
            if(current == null) {
                return false;
            }
        }
        if(current.getNext().getNext() == null) {
            _tail = current;
        }
        current.setNext(current.getNext().getNext());
        _size--;
        return true;
    }

    public T get(int index) {
        validIndex(index);
        Node<T> current = _head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        return current.getValue();
    }

    public T set(int index, Object element) {
        validIndex(index);
        Node<T> current = _head;
        T prevValue = null;
        int i = 0;
        if(index == 0) {
            prevValue = _head.getValue();
            _head.setValue((T) element);
        } else {
            while(current != null) {
                if(i == index) {
                    prevValue = current.getValue();
                    current.setValue((T) element);
                    return prevValue;
                }
                current = current.getNext();
                i++;
            }
        }

        return prevValue;
    }

    public void add(int index, Object element) {
        if(index > _size) {
            validIndex(index);
        }
        Node<T> current = _head;
        int i = 0;
        if(index == 0) {
            if(isEmpty()) {
                add(element);
                return;
            } else {
                Node<T> newNode = new NodeImpl<T>((T) element, _head.getNext());
                _head = newNode;
                _size++;
                return;
            }

        }  else if(index == _size) {
            add(element);
            return;
        }
        while(current != null) {
            if(i == (index - 1)) {
                Node<T> temp = current.getNext();
                Node<T> newNode = new NodeImpl<T>((T) element, temp);
                current.setNext(newNode);
                _size++;
                return;
            } else {
                current = current.getNext();
                i++;
            }
        }
    }

    public int indexOf(Object element) {
        Node<T> current = _head;
        int index = 0;
        while(current != null) {
            if(current.getValue().equals((T) element)) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    public int lastIndexOf(Object element) {
        Node<T> current = _head;
        int index = -1;
        int i = 0;
        while(current != null) {
            if(current.getValue().equals ((T) element)) {
                index = i;
            }
            i++;
            current = current.getNext();
        }
        return index;
    }

    public void validIndex(int i) {
        if(i < 0 || i >= _size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }
    public Node<T> gethead() {
        return _head;
    }

    @Override
    public String toString() {
        String list = "";
        Node<T> current = _head;
        while(current != null) {
            if(current.getNext() == null)
                list+= current.getValue();
            else
                list += current.getValue() + " -> ";
            current = current.getNext();
        }
        return list;
    }
}