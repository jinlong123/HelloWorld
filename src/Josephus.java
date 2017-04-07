import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by JinLong on 2017/3/18.
 */
public class Josephus {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入总人数: ");
        int totalNum=scanner.nextInt();
        System.out.println("请输入报数大小：");
        int cycleNum=scanner.nextInt();
        Josephus(totalNum,cycleNum);
    }

    public static void Josephus(int totalNum,int cycleNum){
        ArrayList<Integer> array=new ArrayList<Integer>();
        for (int i=1;i<=totalNum;i++)
        {
            array.add(i);
        }
        int start=0;
        while (array.size()>0)
        {
            start=start+cycleNum;
            start=start % array.size()-1;
            if (start<0)
            {
                System.out.println(array.get(array.size()-1));
                array.remove(array.size() - 1);
                start=0;

            }
            else
            {
                System.out.println(array.get(start));
                array.remove(start);
            }

        }
    }
}
