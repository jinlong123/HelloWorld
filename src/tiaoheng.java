import java.util.Scanner;

/**
 * Created by JinLong on 2017/3/25.
 */
public class tiaoheng {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String str=scanner.nextLine();
        int min=Integer.MAX_VALUE,girl_count=0,boy_count=0;
        if(str==null)
        {
            System.out.println("Error input");
        }
        if (str.length()==1)
        {
            System.out.println(0);
        }
        else{
            for(int n=0;n<str.length();n++){
                if(str.charAt(n)=='G') girl_count++;
                if(str.charAt(n)=='B') boy_count++;
            }
            if(girl_count==str.length()||boy_count==str.length()) System.out.println(0);
            int difference=Math.abs(girl_count-boy_count);
            int change_time=CalculateMax(str)-difference;
            System.out.println(change_time);
        }
    }
    public static int CalculateMax(String s)
    {
        int max = 0;
        int[] cnt = new int[127];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); // 取出单个字母
            max = (++cnt[c] > max) ? cnt[c] : max;
        }
        return max;
    }
}
