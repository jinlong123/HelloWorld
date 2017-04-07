import java.io.Console;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Created by JinLong on 2017/3/5.
 */
public class test {
    public static void main(String[] args){
//        Console cons=System.console();
//        if (cons!=null){
//            String username=new String(cons.readLine("User name:"));
//            String password=new String(cons.readLine("User password:"));
//            cons.printf("User name is: " + username + "\n");
//            cons.printf("User password is: " + password + "\n");
//        }else{
//            System.out.println("Console is unavailable!");
//        }
//        Scanner in=new Scanner(System.in);
//        int sum=0;
//        int goal=10;
//        while(sum<goal){
//            System.out.println("Enter a number:");
//            int n=in.nextInt();
//            if (n<0) continue;
//            sum+=n;
//        }
//        System.out.println("The sum is:"+sum);
//        int[] a={1,2,3,4,5};
//        System.out.println(Arrays.toString(a));

        GregorianCalendar d=new GregorianCalendar();
//        int today= d.get(Calendar.DAY_OF_MONTH);
//        int month= d.get(Calendar.MONTH);
//        d.set(Calendar.DAY_OF_MONTH,1);
        System.out.println(d.getClass().getName());


    }
}
