package stack;

//栈结构
public class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组，将数组模拟栈，数据就放在该数组中
    private int top = -1; //top表示栈顶，初始化为-1

    public ArrayStack(int _maxSize) {
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
        stack[++top]=value;
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
}

