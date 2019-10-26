package simple_calc;
public class Calculator {
    public static void main(String[] args) {
        //根据的思路 完成表达式的运算
        String expression = "30+2*6-2";
        //1.创建两个栈(数字栈和符号栈)
        MyStack numStack = new MyStack(10);
        MyStack operStack = new MyStack(10);
        //2.定义需要的相关变量
        //用于扫描
        int index = 0;
        //定义操作数
        int num1 = 0;
        int num2 = 0;
        //定义操作符
        int oper = 0;
        //定义结果数
        int res = 0;
        //将每次得到的char保存到ch中
        char ch = ' ';
        String keepNum = "";//用于拼接多位数
        //开始循环扫描
        while (true) {
            //!!!依次得到expression 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么 进行相应的处理
            //ch为符号
            if (operStack.isOper(ch)) {//如果是运算符
                if (!operStack.isStackEmpty()) {
                    // 如果当前的操作符的优先级小于或者等于栈中的操作符，这时，就需要从数栈中pop出两个数，然后从符号栈中pop出一个符号，进行运算，将得到的结果入数栈。还要把当前的操作符入符号栈.
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.popFromStack();
                        num2 = numStack.popFromStack();
                        oper = operStack.popFromStack();
                        res = numStack.cal(num1, num2, oper);
                        //将运算结果入数栈
                        numStack.pushToStack(res);
                        //操作符入符号栈
                        operStack.pushToStack(ch);
                    }
                    // 如果当前的操作符的优先级大于栈中的操作符，就直接入栈即可
                    else {
                        operStack.pushToStack(ch);
                    }
                    // 操作均为 后面一个数 符号 前面一个数

                } else {
                    //如果为空直接入符号栈
                    operStack.pushToStack(ch);
                }
            }
            //ch为数字
            else {
                //如果是数字 则直接入数栈!!! 注意这里 char和int的转换
                //如果是多位数的话 需要进行操作

                // numStack.pushToStack(ch-48);
                /**
                 * 1.当处理多位数的时候 不能发现是一个数就直接入数栈 因为可能是多位数
                 * 2.在处理数时，需要向expression的表达式的index 后再看一位，如果是数就继续扫描如果是符号就直接让数入栈
                 * 3.因此，我们需要定义一个变量(字符串)，用于拼接
                 * */
                keepNum += ch;
                //如果ch已经是expression的最后一位 直接入栈
                if (index == expression.length() - 1) {
                    numStack.pushToStack(Integer.parseInt(keepNum));
                } //如果ch不是expression的最后一位
                else {
                    //寻找当前位的后一位
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符 则入栈
                        numStack.pushToStack(Integer.parseInt(keepNum));
                        //将keepNum清空!!
                        keepNum = "";
                    }
                }
            }
            //继续扫描
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //扫描完毕 开始计算
        while (true) {
            if (operStack.isStackEmpty()) {
                break;
            }
            num1 = numStack.popFromStack();
            num2 = numStack.popFromStack();
            oper = operStack.popFromStack();
            res = numStack.cal(num1, num2, oper);
            numStack.pushToStack(res);
        }
        System.out.printf("表达式 %s = %d ", expression, numStack.popFromStack());
    }
}