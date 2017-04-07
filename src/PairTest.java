import java.util.*;

/**
 * Created by JinLong on 2017/3/12.
 */
public class PairTest {
    public static void main(String[] args){
        GregorianCalendar[] birthday= {
                new GregorianCalendar(1906, Calendar.DECEMBER,9),
                new GregorianCalendar(1815, Calendar.DECEMBER,10),
                new GregorianCalendar(1903, Calendar.DECEMBER,3),
                new GregorianCalendar(1910, Calendar.JUNE,22),
        };
        Pair<GregorianCalendar> mm=ArrayAlg.minmax(birthday);
        System.out.println("min= "+mm.getFirst().getTime());
        System.out.println("min= "+mm.getSecond().getTime());
    }
}

class ArrayAlg {
    public static <T extends Comparable> Pair<T> minmax(T[] a){
        if (a==null||a.length==0) return null;
        T min=a[0];
        T max=a[0];
        for (int i=0;i<a.length;i++){
            if(min.compareTo(a[i])>0) min=a[i];
            if(max.compareTo(a[i])<0) max=a[i];
        }
        return new Pair<T>(min,max);
    }
}