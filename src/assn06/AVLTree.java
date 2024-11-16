package assn06;

import assn04.BST;
import assn04.EmptyBST;

import java.util.ArrayList;
import java.lang.Math;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;

    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateLeft() {
         // You should implement left rotation and then use this 
         // method as needed when fixing imbalances.
    	 // TODO
         AVLTree<T> y=this._right;
         this._right=_right._left;
         y._left=this;
         this._height=1+Math.max(this._left._height, this._right._height);
         y._height=1+Math.max(y._right._height, y._left._height);
         int track=y._size;
         y._size=this._size;
         this._size=track;
         return y;
     }
    
    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateRight() {
         // You should implement right rotation and then use this 
         // method as needed when fixing imbalances.
    	 // TODO
         AVLTree<T> y=this._left;
         this._left=_left._right;
         y._right=this;
         this._height=1+Math.max(this._left._height, this._right._height);
         y._height=1+Math.max(y._right._height, y._left._height);
         int track=y._size;
         y._size=this._size;
         this._size=track;
         return y;
     }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
    	// TODO
        if (isEmpty()){
            _left=new AVLTree<T>();
            _right=new AVLTree<T>();
            _value=element;
            _size=1;
            _height=0;
        }
        else if (element.compareTo(this.getValue())>0){
            _right=(AVLTree<T>) _right.insert(element);
            _size++;
        }
        else if (element.compareTo(this.getValue())<0){
            _left=(AVLTree<T>) _left.insert(element);
            _size++;
        }
        _height=1+Math.max(_left._height, _right._height);
        int balanceFactor;
        if (_left.isEmpty() && _right.isEmpty()){
            balanceFactor=0;
        }
        else if(getLeft().isEmpty()){
            balanceFactor=-(_right.height())-1;
        }
        else if (getRight().isEmpty()){
            balanceFactor=_left.height()+1;
        }
        else {
            balanceFactor=_left.height()-_right.height();
        }
        if (balanceFactor>1){ //left imbalance
            //fix rotation checking
            if (_left._left.isEmpty()){//LR imbalance
                _left=_left.rotateLeft();
                return this.rotateRight();
            }
            else if (_left._right.isEmpty() || _left._left.height()-_left._right.height()>=0){//LL imbalance
                return this.rotateRight();
            }
            else if(_left._right.height()-_left._left.height()>0){//LR imbalance
                _left=_left.rotateLeft();
                return this.rotateRight();
            }
        }
        else if(balanceFactor<-1){ //right imbalance
            if (_right._right.isEmpty()){//LR imbalance
                _right=_right.rotateRight();
                return this.rotateLeft();
            }
            else if (_right._left.isEmpty() || _right._right.height()-_right._left.height()>=0){//LL imbalance
                return this.rotateLeft();
            }
            else if(_right._left.height()-_right._right.height()>0){//LR imbalance
                _right=_right.rotateRight();
                return this.rotateLeft();
            }
        }
        return this;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
    	// TODO
        if(!isEmpty()) {
            if (element.compareTo(this.getValue()) == 0) {
                _size--;
                if (this._right.isEmpty() && this._left.isEmpty()) {
                    return new AVLTree<>();
                } else if (this.getRight().isEmpty()) {
                    return _left;
                } else if (this.getLeft().isEmpty()) {
                    return _right;
                } else {
                    T low = this._right.findMin();
                    this._value = low;
                    _right = (AVLTree<T>) _right.remove(low);
                    return this;
                }
            } else if (element.compareTo(this.getValue()) > 0) {
                _right = (AVLTree<T>) _right.remove(element);
                _size--;
            } else if (element.compareTo(this.getValue()) < 0) {
                _left = (AVLTree<T>) _left.remove(element);
                _size--;
            }
            _height = 1 + Math.max(_left._height, _right._height);
            int balanceFactor;
            if (_left.isEmpty() && _right.isEmpty()) {
                balanceFactor = 0;
            } else if (getLeft().isEmpty()) {
                balanceFactor = -(_right.height()) - 1;
            } else if (getRight().isEmpty()) {
                balanceFactor = _left.height() + 1;
            } else {
                balanceFactor = _left.height() - _right.height();
            }
            if (balanceFactor > 1) { //left imbalance
                //fix rotation checking
                if (_left._left.isEmpty()) {//LR imbalance
                    _left = _left.rotateLeft();
                    return this.rotateRight();
                } else if (_left._right.isEmpty() || _left._left.height() - _left._right.height() >= 0) {//LL imbalance
                    return this.rotateRight();
                } else if (_left._right.height() - _right._left.height() > 0) {//LR imbalance
                    _left = _left.rotateLeft();
                    return this.rotateRight();
                }
            } else if (balanceFactor < -1) { //right imbalance
                if (_right._right.isEmpty()) {//RL imbalance
                    _right = _right.rotateRight();
                    return this.rotateLeft();
                } else if (_right._left.isEmpty() || _right._right.height() - _right._left.height() >= 0) {//RR imbalance
                    return this.rotateLeft();
                } else if (_right._left.height() - _right._right.height() > 0) {//LR imbalance
                    _right = _right.rotateRight();
                    return this.rotateLeft();
                }
            }
        }
        return this;
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // TODO
        AVLTree<T> minPoint=this;
        int height=this._height;
        for (int i=0; i<=height; i++) {
            if (minPoint._left.isEmpty()) {
                return minPoint.getValue();
            }
            else{
                minPoint=minPoint._left;
            }
        }
        return minPoint.getValue();
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // TODO
        AVLTree<T> maxPoint=this;
        int height=this._height;
        for (int i=0; i<=height; i++) {
            if (maxPoint._right.isEmpty()) {
                return maxPoint.getValue();
            }
            else{
                maxPoint=maxPoint._right;
            }
        }
        return null;
    }

    @Override
    public boolean contains(T element) {
    	// TODO
        AVLTree<T> ptr=this;
        int height=this._height;
        for (int i=0; i<=height; i++){
            if (ptr.isEmpty()){
                break;
            }
            if (element.compareTo(ptr.getValue())==0){
                return true;
            }
            else if (element.compareTo(ptr.getValue())>0){ // element > current value, go to the right
                ptr=ptr._right;
            }
            else if (element.compareTo(ptr.getValue())<0){ // element < current value, go to the left
                ptr=ptr._left;
            }
        }
        return false;
    }


    @Override
    public boolean rangeContain(T start, T end) {
        // TODO
        Integer low=(Integer) start;
        Integer high=(Integer) end;
        for (Integer i=low; i<=high; i++){
            if (!contains((T) i)){
                return false;
            }
        }
        return true;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
         return _right;
    }

}

