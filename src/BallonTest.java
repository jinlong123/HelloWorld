import java.util.*;
import java.util.Map.Entry;
/**
 * Created by JinLong on 2017/3/14.
 */
public class BallonTest {
    public static void main(String []args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext())
        {
            int num = in.nextInt();
            if(num > 0 && num < 1001)
            {
                //build map
                Map<String, Integer> map = new HashMap<String,Integer>();
                for( int i = 0; i < num; i++){
                    String str = in.next();
                    if(map.containsKey(str)) map.put(str, map.get(str)+1);
                    else map.put(str,1);
                }
                //sort
                int max = 0;
                String color = null;
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext())
                {
                    Entry entry = (Entry) iter.next();
                    int temp = (Integer)entry.getValue();
                    if( max < temp){
                        max = temp;
                        color = entry.getKey().toString();
                    }
                }
                System.out.println(color);
                map.clear();
            }
            else
            {
                System.exit(0);
            }
        }//while
    }
}

