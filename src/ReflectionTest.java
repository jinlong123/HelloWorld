import java.lang.reflect.*;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by JinLong on 2017/3/10.
 */
public class ReflectionTest {
    public static void main(String[] args ){
        String name;
        if (args.length>0) name=args[0];
        else{
            Scanner scanner=new Scanner(System.in);
            System.out.println("Enter class name(e.g java.util.Date):");
            name=scanner.next();
        }

        try {
            Class c1=Class.forName(name);
            Class superc1=c1.getSuperclass();
            String modifiers= Modifier.toString(c1.getModifiers());
            if (modifiers.length()>0) System.out.print(modifiers+" ");
            System.out.print("class "+name);
            if (superc1!=null &&superc1!= Object.class) System.out.print("extens "+superc1.getName());
            System.out.print("\n{\n");
            printConstructors(c1);
            System.out.println();
            printMethods(c1);
            System.out.println();
            printFields(c1);
            System.out.println();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void printConstructors(Class c1){
        Constructor[] constructors=c1.getDeclaredConstructors();

        for(Constructor c:constructors){
            String name=c.getName();
            System.out.print("  ");
            String modifiers=Modifier.toString(c.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers+" ");
            System.out.print(name+"(");

            //print paramter types
            Class[] paramTypes=c.getParameterTypes();
            for (int j=0;j<paramTypes.length;j++){
                if (j>0) System.out.print(",");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    public static void printMethods(Class c1){
        Method[] methods=c1.getDeclaredMethods();

        for (Method m:methods){
            Class retType=m.getReturnType();
            String name=m.getName();

            System.out.print("  ");
            //print modfiers,return type and method name
            String modifiers=Modifier.toString(m.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers+" ");
            System.out.print(retType.getName()+" "+name+"(");
            //print paramter types
            Class[] paramTypes=m.getParameterTypes();
            for (int j=0;j<paramTypes.length;j++){
                if (j>0) System.out.print(",");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");

        }
    }

    public static  void printFields(Class c1){
        Field[] fields=c1.getDeclaredFields();

        for (Field f:fields){
            Class type=f.getType();
            String name=f.getName();
            System.out.print("  ");
            String modifiers=Modifier.toString(f.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers+" ");
            System.out.println(type.getName()+" "+name+";");
        }
    }
}
