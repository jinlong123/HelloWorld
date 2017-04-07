import java.util.Scanner;

/**
 * Created by JinLong on 2017/3/8.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        for(int i=1;i<=t;i++)
        {
            System.out.println("Case "+i+":");
            int a,n=in.nextInt(),fir=1,las=1,temp=1,sum=0,Maxsum=-0xfffffff;
            for(int j=1;j<=n;j++)
            {
                a=in.nextInt();
                sum+=a;
                if(sum>Maxsum)
                {
                    Maxsum=sum;
                    fir=temp;
                    las=j;
                }
                if(sum<0)
                {
                    sum=0;
                    temp=j+1;
                }
            }
            System.out.println(Maxsum+" "+fir+" "+las);
            if(i!=t)
                System.out.println();
        }
    }
}
