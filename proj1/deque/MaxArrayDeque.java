package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max(){
        if(isEmpty()){
            return null;
        }
        T max = this.get(0);
        for(int i = 0 ; i < this.size() ; i++) {
            max = comparator.compare(max, this.get(i)) > 0 ? max : this.get(i);
        }
        return max;
    }

    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        T max = this.get(0);
        for(int i = 0 ; i < this.size() ; i++) {
            max = c.compare(max, this.get(i)) > 0 ? max : this.get(i);
        }
        return max;
    }

}
