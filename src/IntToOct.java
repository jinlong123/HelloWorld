import java.util.Scanner;

/**
 * Created by JinLong on 2017/3/21.
 */
public class IntToOct {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int sum1=0,sum2=0;
            for(int i=0;2*i+1<=n;i++)
            {
                sum1+=Factorial(2*i+1);
            }

            for (int i=0;2*i+2<=n;i++)
            {
                sum2+=Factorial(2*i+2);
            }
            System.out.print(sum1+" "+sum2);
         }
    }

    public static int Factorial(int n) {
        if (n == 1 || n == 0) {
            return 1;
        } else
            return n * Factorial(n - 1);
    }
}
