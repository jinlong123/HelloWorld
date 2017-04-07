import java.util.*;

/**
 * Created by JinLong on 2017/3/3.
 */
public class MED {
    private static int min(int a,int b,int c){
        return a<b?(a<c?a:c):(b<c?b:c);
    }

    public static int MED(String str1,String str2,int[][] d,int[][] p,int k){
        int rows=str1.length();
        int cols=str2.length();
        int temp;

        //base condition
        for(int i=0;i<=rows;i++){
            d[i][0]=i;
            p[i][0]=i;
        }
        for (int j=0;j<=cols;j++){
            d[0][j]=j;
            p[0][j]=j;
        }

        //recurrence relation
        for (int i=1;i<=rows;i++){
            char c1=str1.charAt(i-1);
            for (int j=1;j<=cols;j++){
                char c2=str2.charAt(j-1);
                if(c1==c2){
                    temp=0;
                }
                else{
                    temp=k;
                }
                d[i][j]=min(d[i-1][j]+1,d[i][j-1]+1,d[i-1][j-1]+temp);
                if(d[i][j]==d[i-1][j-1]+temp){
                    p[i][j]=3;    //from diag
                }
                else if(d[i][j]==d[i][j-1]+1){
                    p[i][j]=1;    //from left
                }
                else{
                    p[i][j]=2;    //from own
                }
            }
        }
        return d[rows][cols];
    }

    private static void print(int[][] matrix,String str1,String str2){
        System.out.print("\t\t");
        for(char a :str2.toCharArray()){
            System.out.print(a+"\t");
        }
        System.out.println();
        int count = -1;
        for(int[] a : matrix){
            if(count>=0){
                System.out.print(str1.charAt(count));
            }
            System.out.print("\t");
            for(int b:a){
                System.out.print(b+"\t");
            }
            System.out.println();
            count++;
        }
    }

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入第一个字符串：\n");
        String  str1=scanner.nextLine();
        System.out.println("请输入第二个字符串：\n");
        String  str2=scanner.nextLine();
        int str1_length=str1.length();
        int str2_length=str2.length();
        int distance[][]=new int[str1_length+1][str2_length+1];    //record MED
        int path[][]=new int[str1_length+1][str2_length+1];    //record path
        int k=2;    //replacement cost
        int dis=MED(str1,str2,distance,path,k);
        System.out.println("最短编辑距离矩阵为：");
        print(distance,str1,str2);
        System.out.println("路径矩阵为：");
        print(path,str1,str2);
        System.out.printf("最短编辑距离为：%d",dis);
    }
}
