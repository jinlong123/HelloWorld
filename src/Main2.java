import java.util.Scanner;

/**
 * Created by JinLong on 2017/4/7.
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int k=scanner.nextInt();
        int result=n/(2*k+1);
        result=result*2;
        if(n%(2*k+1)>=k)
        {
            result+=1;
        }
        System.out.println(result);
    }
}
