package assn06;

import assn04.BST;

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
            _size++;
            _height++;
        }
        else if (element.compareTo(this.getValue())>0){
            if (_right.isEmpty()){
                _size++;
                if (_left.isEmpty()){
                    _height++;
                }
                _right=(AVLTree<T>) _right.insert(element);
            }
            else{
                _size++;
                _right.insert(element);
            }
        }
        else if (element.compareTo(this.getValue())<0){
            if (_left.isEmpty()){
                _size++;
                if (_right.isEmpty()){
                    _height++;
                }
                _left=(AVLTree<T>) _left.insert(element);
            }
            else{
                _size++;
                _left.insert(element);
            }
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
            else if (_right.isEmpty() || _right._right.isEmpty() || _left.height()-_right.height()>=0){//LL imbalance
                return this.rotateRight();
            }
            else if(_right.height()-_left.height()>0){//LR imbalance
                _left=_left.rotateLeft();
                return this.rotateRight();
            }
        }
        else if(balanceFactor<-1){ //right imbalance
            if (_right._right.isEmpty()){//LR imbalance
                _right=_right.rotateRight();
                return this.rotateLeft();
            }
            else if (_left.isEmpty() || _left._left.isEmpty() || _right.height()-_left.height()>=0){//LL imbalance
                return this.rotateLeft();
            }
            else if(_left.height()-_right.height()>0){//LR imbalance
                _right=_right.rotateRight();
                return this.rotateLeft();
            }
        }
        return this;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
    	// TODO

        return null;
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // TODO
        AVLTree<T> minPoint=this;
        for (int i=0; i<this._height; i++) {
            if (minPoint._left==null) {
                return minPoint.getValue();
            }
            else{
                minPoint=this._left;
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
        for (int i=0; i<this._height; i++) {
            if (maxPoint._right==null) {
                return maxPoint.getValue();
            }
            else{
                maxPoint=this._right;
            }
        }
        return maxPoint.getValue();
    }

    @Override
    public boolean contains(T element) {
    	// TODO
        AVLTree<T> ptr=this;
        for (int i=0; i<this._height; i++){
            if (element.compareTo(ptr.getValue())==0){
                return true;
            }
            if (element.compareTo(ptr.getValue())>0){ // element > current value, go to the right
                ptr=this._right;
            }
            else{ // element < current value, go to the left
                ptr=this._left;
            }
        }
        return false;
    }


    @Override
    public boolean rangeContain(T start, T end) {
        // TODO
        _left.rangeContain(start, end);
        _right.rangeContain(start, end);
        if (getValue().compareTo(start)>=0 && getValue().compareTo(end)<=0){

        }
        return false;
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
