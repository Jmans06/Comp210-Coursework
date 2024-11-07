package assn04;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
    private T _element;
    private BST<T> _left;
    private BST<T> _right;

    public NonEmptyBST(T element) {
        _left = new EmptyBST<T>();
        _right = new EmptyBST<T>();
        _element = element;
    }

    // TODO: insert
    @Override
    public BST<T> insert(T element){
        if (element.compareTo(this.getElement())>0){
            if (_right.isEmpty()){
                _right=_right.insert(element);
            }
            else{
                _right.insert(element);
            }
        }
        else if (element.compareTo(this._element)<0){
            if (_left.isEmpty()){
                _left=_left.insert(element);
            }
            else{
                _left.insert(element);
            }
        }
        return this;
    }

    // TODO: findMin
    @Override
    public T findMin() {
        BST<T> minPoint=this;
        for (int i=0; i<this.getHeight(); i++) {
            if (this.getLeft().isEmpty()) {
                return minPoint.getElement();
            }
            else{
                minPoint=this._left;
            }
        }
        return minPoint.getElement();
    }

    // TODO: remove
    @Override
    public BST<T> remove(T element) {
        if (element.compareTo(this.getElement())>0){
            _right=_right.remove(element);
        }
        else if (element.compareTo(this.getElement())<0){
            _left=_left.remove(element);
        }
        else if (element.compareTo(this.getElement())==0){
            if (this.getRight().isEmpty() && this.getLeft().isEmpty()){
                return new EmptyBST<>();
            }
            else if (this.getRight().isEmpty()){
                return _left;
            }
            else if (this.getLeft().isEmpty()){
                return _right;
            }
            else{
                T low = this._right.findMin();
                this._element=low;
                this._right=this._right.remove(low);
                return this;
            }
        }
        return this;
    }

    // TODO: replace_range
    @Override
    public BST<T> replace_range(T start, T end, T newValue) {
        if (this.getElement().compareTo(start)>=0 && this.getElement().compareTo(end)<=0){
            _right=_right.replace_range(start, end, newValue);
            _left=_left.replace_range(start, end, newValue);
            return this.remove(this.getElement());
        }
        else if (this.getElement().compareTo(start)<0){
            if (!getRight().isEmpty()){
                _right=_right.replace_range(start, end, newValue);
            }
        }
        else if (this.getElement().compareTo(end)>0) {
            if (!getLeft().isEmpty()) {
                _left=_left.replace_range(start, end, newValue);
            }
        }
        if (newValue.compareTo(start)<0 || newValue.compareTo(end)>0){
            return this.insert(newValue);
        }
        return this;
    }
    // TODO: printPreOrderTraversal
    @Override
    public void printPreOrderTraversal() {
        System.out.print(this._element);
        System.out.print(" ");
        _left.printPreOrderTraversal();
        _right.printPreOrderTraversal();
    }

    // TODO: printPostOrderTraversal
    @Override
    public void printPostOrderTraversal() {
        _left.printPostOrderTraversal();
        _right.printPostOrderTraversal();
        System.out.print(this._element);
        System.out.print(" ");
    }

    // Do not change the methods below
    @Override
    public int getHeight() {
        return Math.max(_left.getHeight(), _right.getHeight())+1;
    }

    @Override
    public BST<T> getLeft() {
        return _left;
    }

    @Override
    public BST<T> getRight() {
        return _right;
    }

    @Override
    public T getElement() {
        return _element;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
