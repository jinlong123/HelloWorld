import java.util.Stack;
/**
 * Created by JinLong on 2017/3/18.
 */
public class TestStack {
    private String testString = null;
    private Stack<Character> stack = null;

    public TestStack(String testString) {
        this.testString = testString;
        this.stack = new Stack<Character>();
    }

    private void analysisString() {
        for (int i = 0; i < testString.length(); i++) {
            char c = testString.charAt(i);
            if (c == '+' || c == '-') {
                if (stack.isEmpty() || stack.peek() == '(') {
                    stack.push(c);
                } else {
                    while (!stack.isEmpty()
                            && (stack.peek() == '*' || stack.peek() == '/'
                            || stack.peek() == '+' || stack.peek() == '-')) {
                        System.out.print(stack.pop());
                    }
                    stack.push(c);
                }
            } else if (c == '*' || c == '/') {
                if (stack.isEmpty() || stack.peek() == '+'
                        || stack.peek() == '-' || stack.peek() == '(') {
                    stack.push(c);
                } else {
                    while (!stack.isEmpty()
                            && (stack.peek() == '/' || stack.peek() == '*')) {
                        System.out.print(stack.pop());
                    }
                    stack.push(c);
                }
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                char temp = ' ';
                while ((temp = stack.pop()) != '(') {
                    System.out.print(temp);
                }
            } else {
                System.out.print(c);
            }
        }
        if (!stack.isEmpty()) {
            while (!stack.isEmpty()) {
                System.out.print(stack.pop());
            }
        }
    }

    public static void main(String[] args) {
        TestStack testStacknew = new TestStack("A*B-(C+D)+E");
        testStacknew.analysisString();
    }
}
