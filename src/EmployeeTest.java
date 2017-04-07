import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by JinLong on 2017/3/8.
 */
public class EmployeeTest {
    public static void main(String[] args){
        Employee[] staff=new Employee[3];
//        staff[0]=new Employee("Jinlong",10000,1994,1,26);
//        staff[1]=new Employee("Lisa",15000,1993,10,10);
//        staff[2]=new Employee("Bill",20000,1995,12,12);
        staff[0]=new Employee("Jinlong",10000);
        staff[1]=new Employee("Lisa",15000);
        staff[2]=new Employee("Bill",20000);

//        for (Employee e:staff) {
//            e.raiseSalary(10);
//        }
//
//        for (Employee e:staff) {
//            System.out.println("Name: "+e.getName()+"Salary: "+e.getSalary()+"HireDay: "+e.getHireDay());
//        }
        for (Employee e:staff){
            e.setId();
            System.out.println("Name= "+e.getName()+"id= "+e.getId()+"Salary= "+e.getSalary());
        }

        int n=Employee.getNextId();
        System.out.println("The next available id= "+n);
    }
}

class Employee
{
    private String name;
    private double salary;
//    private Date hireDay;
    private int id;
    private static int nextId=1;

    public Employee(String n,double s/*,int year,int month,int day */){
        this.name=n;
        this.salary=s;
        id=0;
//        GregorianCalendar calendar=new GregorianCalendar(year,month-1,day);
//        this.hireDay=calendar.getTime();
    }
    public String getName(){
        return this.name;
    }
    public double getSalary(){
        return this.salary;
    }
//    public Date getHireDay(){
//        return this.hireDay;
//    }
    public void setId(){
        this.id=nextId;
        nextId++;
    }
    public int getId(){
        return this.id;
    }
    public static int getNextId(){
        return nextId;
    }
    public void raiseSalary(double byPercent){
        double raise=this.salary*byPercent/100;
        this.salary+=raise;
    }
    public static void main(String[] args){       //unit test
        Employee e=new Employee("Harry",50000);
        System.out.println(e.getName()+" "+e.getSalary());
    }
}
