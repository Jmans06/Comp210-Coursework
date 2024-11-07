package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;

    /**
     * 1st constructor that creates an empty max heap of prioritized elements.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * @return size of heap
     */
    @Override
    public int size() {
        return _heap.size();
    }

    /**
     * TODO (Task 2A): enqueue using value and priority
     * @param value
     * @param priority
     */
    @Override
    public void enqueue(V value, P priority) {
        if (_heap.isEmpty()){
            _heap.add(new Patient<>(value, priority));
        }
        else{
            _heap.add(new Patient<>(value, priority));
            int currentIndex=_heap.size()-1;
            while (currentIndex>0){
                int parentIndex=(int)((Math.floor(currentIndex-1)/2));
                Prioritized<V, P> parent = _heap.get(parentIndex);
                Prioritized<V, P> child = _heap.get(currentIndex);
                if (child.getPriority().compareTo(parent.getPriority())>0){ //if parent<child, swap and recurse up
                    _heap.set(parentIndex, child);
                    _heap.set(currentIndex, parent);
                    currentIndex=parentIndex;
                }
                else{
                    break;
                }
            }
        }
    }

    /**
     * TODO (Task 2A): enqueue (overloaded) using only value
     * @param value
     */
    public void enqueue(V value) {
        if (_heap.isEmpty()){
            _heap.add(new Patient<>(value));
        }
        else{
            _heap.add(new Patient<>(value));
            int currentIndex=_heap.size()-1;
            while (currentIndex>=0){
                int parentIndex=(int)((Math.floor(currentIndex-1)/2));
                Prioritized<V, P> parent = _heap.get(parentIndex);
                Prioritized<V, P> child = _heap.get(currentIndex);
                if (child.getPriority().compareTo(parent.getPriority())>0){ //if parent<child, swap and recurse up
                    _heap.set(parentIndex, child);
                    _heap.set(currentIndex, parent);
                    currentIndex=parentIndex;
                }
                else{
                    break;
                }
            }
        }
    }

    /**
     * TODO (Task 2A): dequeue from maxBinHeapER
     * @return element with highest priority
     */
    @Override
    public V dequeue() {
        if (_heap.isEmpty()){
            return null;
        }
        Prioritized<V, P> topPriority=_heap.get(0);
        V topPriorityValue=getMax();
        int currentIndex=0;
        if (!_heap.isEmpty() && _heap.size()!=1){ //if heap is not empty, dequeue top element
            //replaces root with last element
            _heap.set(0, _heap.get(_heap.size()-1));
            //_heap.set(_heap.size()-1, First);
            _heap.remove(_heap.size()-1); //removes last element
            while (!(currentIndex*2+1>_heap.size()-1)){ //recurse downwards, if maxChild>parent, swap and keep recursing
                int leftChildIndex=currentIndex*2+1;
                int rightChildIndex=currentIndex*2+2;
                Prioritized<V, P> parent= _heap.get(currentIndex);
                if (rightChildIndex>_heap.size()-1){ // if the right child DNE
                    if (_heap.get(currentIndex).getPriority().compareTo(_heap.get(leftChildIndex).getPriority())<0){
                        // child > parent, so swap
                        _heap.set(currentIndex, _heap.get(leftChildIndex));
                        _heap.set(leftChildIndex, parent);
                        currentIndex=leftChildIndex;
                    }
                    else{
                        break;
                    }
                }
                else{ // if both children exist, compare and swap with the max
                    if (_heap.get(leftChildIndex).getPriority().compareTo(_heap.get(rightChildIndex).getPriority())>0){
                        // left child priority > right child priority
                        if (_heap.get(currentIndex).getPriority().compareTo(_heap.get(leftChildIndex).getPriority())<0){
                            // child > parent, so swap
                            _heap.set(currentIndex, _heap.get(leftChildIndex));
                            _heap.set(leftChildIndex, parent);
                            currentIndex=leftChildIndex;
                        }
                        else{
                            break;
                        }
                    }
                    else{
                        // right child priority > left child priority
                        if (_heap.get(currentIndex).getPriority().compareTo(_heap.get(rightChildIndex).getPriority())<0){
                            // child > parent, so swap
                            _heap.set(currentIndex, _heap.get(rightChildIndex));
                            _heap.set(rightChildIndex, parent);
                            currentIndex=rightChildIndex;
                        }
                        else{
                            break;
                        }
                    }
                }
            }
            return topPriorityValue;
        }
        else { // _heap.size()==1
            _heap.remove(topPriority);
            return topPriorityValue;
        }
    }

    /**
     * TODO (Task 2A): getMax
     * @return return value of element with the highest priority
     */
    @Override
    public V getMax() {
        if (!_heap.isEmpty()){
            return _heap.get(0).getValue();
        }
        else{
            return null;
        }
    }

    /**
     * TODO (Task 2B): updatePriority
     * Change the priority of the patient with the given value.
     * if newPriority is <0 then remove the element with given value
     * (In case value is not matching and existing Priority < 0, nothing is to be done.)
     * @param value
     * @param newPriority
     */
    public void updatePriority(V value, P newPriority) {
        boolean bubDown=false;
        for (int i=0; i<_heap.size(); i++){
            if (_heap.get(i).getValue().equals(value)){
                if (newPriority.compareTo((P)(Integer)0)<0){
                    _heap.remove(i);
                    bubDown=true;
                }
                else{
                    _heap.set(i, new Patient<>(value, newPriority));
                    bubDown=true;
                }

            }
        }
        if (_heap.size()>1 && bubDown){
            for(int j=_heap.size()-1; j>=0; j--){
                int currentIndex=(int)((Math.floor(j-1)/2));
                while (!(currentIndex*2+1>_heap.size()-1)){ //bubble down
                    int leftChildIndex=currentIndex*2+1;
                    int rightChildIndex=currentIndex*2+2;
                    Prioritized<V, P> parent= _heap.get(currentIndex);
                    if (rightChildIndex>_heap.size()-1){ // if the right child DNE
                        if (_heap.get(currentIndex).getPriority().compareTo(_heap.get(leftChildIndex).getPriority())<0){
                            // child > parent, so swap
                            _heap.set(currentIndex, _heap.get(leftChildIndex));
                            _heap.set(leftChildIndex, parent);
                            currentIndex=leftChildIndex;
                        }
                        else{
                            break;
                        }
                    }
                    else{ // if both children exist, compare and swap with the max
                        if (_heap.get(leftChildIndex).getPriority().compareTo(_heap.get(rightChildIndex).getPriority())>0){
                            // left child priority > right child priority
                            if (_heap.get(currentIndex).getPriority().compareTo(_heap.get(leftChildIndex).getPriority())<0){
                                // child > parent, so swap
                                _heap.set(currentIndex, _heap.get(leftChildIndex));
                                _heap.set(leftChildIndex, parent);
                                currentIndex=leftChildIndex;
                            }
                            else{
                                break;
                            }
                        }
                        else{
                            // right child priority > left child priority
                            if (_heap.get(currentIndex).getPriority().compareTo(_heap.get(rightChildIndex).getPriority())<0){
                                // child > parent, so swap
                                _heap.set(currentIndex, _heap.get(rightChildIndex));
                                _heap.set(rightChildIndex, parent);
                                currentIndex=rightChildIndex;
                            }
                            else{
                                break;
                            }
                        }
                    }
                }
            }
        }
//        boolean fixDown = false;
//        boolean fixUp = false;
//        boolean newG = false;
//        P toCompare = (P) (Integer) 0;
//        int index = -1;
//        for (Prioritized<V, P> patient : _heap) {
//            index++;
//            if (patient.getValue().equals(value)) {
//                if (newPriority.compareTo(_heap.get(index).getPriority()) > 0) {
//                    fixUp = true;
//                } else if (newPriority.compareTo(_heap.get(index).getPriority()) < 0) {
//                    fixDown = true;
//                }
//                if (newPriority.compareTo(toCompare) >= 0) { // if newPriority>=0, set node to newPriority
//                    _heap.set(index, new Patient<>(value, newPriority));
//                } else { // if newPriority<0
//                    if (patient.getPriority().compareTo(toCompare) >= 0 || patient.getPriority().compareTo(newPriority) == 0) { // if the patient's current priority>=0 or =newPriority, delete the node and fix heap
//                        _heap.set(index, _heap.get(_heap.size() - 1));
//                        _heap.remove(_heap.size() - 1);
//                    }
//                    // if the patient's current priority<0 and newPriority!=current priority, do nothing
//                }
//                break;
//            }
//        }
//        if (_heap.size() > 1) {
//            if (fixUp) {
//                while (index >= 0) {
//                    int parentIndex = (int) ((Math.floor(index - 1) / 2));
//                    Prioritized<V, P> parent = _heap.get(parentIndex);
//                    Prioritized<V, P> child = _heap.get(index);
//                    if (child.getPriority().compareTo(parent.getPriority()) > 0) { //if parent<child, swap and recurse up
//                        _heap.set(parentIndex, child);
//                        _heap.set(index, parent);
//                        index = parentIndex;
//                    } else {
//                        break;
//                    }
//                }
//            }
//                if (fixDown) {
//                    while (!(index * 2 + 1 > _heap.size() - 1)) { //recurse downwards, if maxChild>parent, swap and keep recursing
//                        int leftChildIndex = index * 2 + 1;
//                        int rightChildIndex = index * 2 + 2;
//                        Prioritized<V, P> parent = _heap.get(index);
//                        if (rightChildIndex > _heap.size() - 1) { // if the right child DNE
//                            if (_heap.get(index).getPriority().compareTo(_heap.get(leftChildIndex).getPriority()) < 0) {
//                                // child > parent, so swap
//                                _heap.set(index, _heap.get(leftChildIndex));
//                                _heap.set(leftChildIndex, parent);
//                                index = leftChildIndex;
//                            } else {
//                                break;
//                            }
//                        } else { // if both children exist, compare and swap with the max
//                            if (_heap.get(leftChildIndex).getPriority().compareTo(_heap.get(rightChildIndex).getPriority()) > 0) {
//                                // left child priority > right child priority
//                                if (_heap.get(index).getPriority().compareTo(_heap.get(leftChildIndex).getPriority()) < 0) {
//                                    // child > parent, so swap
//                                    _heap.set(index, _heap.get(leftChildIndex));
//                                    _heap.set(leftChildIndex, parent);
//                                    index = leftChildIndex;
//                                } else {
//                                    break;
//                                }
//                            } else {
//                                // right child priority > left child priority
//                                if (_heap.get(index).getPriority().compareTo(_heap.get(rightChildIndex).getPriority()) < 0) {
//                                    // child > parent, so swap
//                                    _heap.set(index, _heap.get(rightChildIndex));
//                                    _heap.set(rightChildIndex, parent);
//                                    index = rightChildIndex;
//                                } else {
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//
//        }
    }
    /**
     * TODO (Task 3): MaxBinHeapER
     * 2nd constructor that builds a heap given an initial array of prioritized elements.
     * @param initialEntries This is an initial ArrayList of patients
     */
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap=new ArrayList<>();
        for (int i=0; i<initialEntries.length; i++){
            enqueue(initialEntries[i].getValue(), initialEntries[i].getPriority());
        }
    }

    /**
     * Retrieves contents of heap as an array.
     * @return array of Prioritized elements in the order stored in the heap
     */
    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }
}

