package simple_calc;
//栈结构
public class MyStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组，将数组模拟栈，数据就放在该数组中
    private int top = -1; //top表示栈顶，初始化为-1
    public MyStack(int _maxSize) {
        this.maxSize = _maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈满
    public boolean isStackFull() {
        return top == maxSize - 1;
    }

    //判断栈空
    public boolean isStackEmpty() {
        return top == -1;
    }

    //入栈:将数据压入栈中
    public void pushToStack(int value) {
        if (isStackFull()) {
            System.out.println("栈已经满了");
            return;
        }
        //入栈成功
//        top++;
//        stack[top] = value;
        stack[++top] = value;
    }

    //出栈:将数据从栈中取出
    public int popFromStack() {
        if (isStackEmpty()) {
            throw new RuntimeException("栈已经空了");
        }
        //保存栈顶值
        int tempNum = stack[top];
        top--;
        return tempNum;
        //return stack[top--];
    }

    //遍历:需要从栈顶取出数据
    public void listStack() {
        if (isStackEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员决定的。优先级使用数字表示，数字越大 则优先级越高
    public int priority(int oper) {
        /**
         * 在java中 char在进行比较的时候 底层依旧是使用int型进行比较的
         * */
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前表达式只有加减乘除
        }
    }

    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法 次出栈数-首先出栈数
    public int cal(int num1, int num2, int oper) {
        int res = 0;//用于存放结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
    //返回当前栈顶的值,但是不是真正的出栈
    public int peek(){
        return stack[top];
    }
}

