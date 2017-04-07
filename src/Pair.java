/**
 * Created by JinLong on 2017/3/12.
 */
public class Pair<T> {
    private T first;
    private T second;

    public Pair() {first=null;second=null;}
    public Pair(T first,T second) {this.first=first;this.second=second;}

    public T getFirst(){ return first;}
    public T getSecond(){return second;}

    public void setFirst(T newvalue){first=newvalue;}
    public void setSecond(T newvalue){second=newvalue;}
}
