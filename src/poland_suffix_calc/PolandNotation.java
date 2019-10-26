package poland_suffix_calc;
import stack.ArrayStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class PolandNotation {
    //    后缀表达式在计算机中的求值
    //    从左到右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符的时候，弹出栈顶两个数进行运算 将结果入栈 重复上述结果得到最终的结果
    public static void main(String[] args) {
        //完成一个中缀表达式转后缀表达式的功能
        //1.1+((2+3)*4)-5  -->1 2 3 + 4 x + 5 -
        //2. 因为直接对一个字符串操作不太方便 因此将字符串放入一个List中
        String infixExpression="1+((2+3)*4)-5";
        ;
        //中缀字符串转为中缀list
        List<String> infixList=toInfixExpressionList(infixExpression);
        System.out.println("中缀表达式为"+infixExpression);
        //3.将得到的一个中缀表达式对应的list转成一个后缀表达式对应的list
        //中缀list转为后缀list
         List<String>suffixList=parseSuffixExpressionList(infixList);
        System.out.println("后缀表达式为"+suffixList);
         int result=calculate(suffixList);
        System.out.println("结果为"+result);


    }
    //将一个逆波兰表达式中的数据和运算符直接放入ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffix按照空格分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            //将数据进行装载
            list.add(ele);
        }
        return list;
    }
    //将一个中缀表达式中的数据和运算符直接放入ArrayList中
    public static List<String> toInfixExpressionList(String infixExpression){
        //定义一个List
        List<String> ls=new ArrayList<String>();
        //一个索引，用于遍历infixExpression 字符串
        int i=0;
        String str;//多位数的拼接
        char c;// 每遍历到一个字符 放入c中
        do{
            //如果c是一个非数字,就需要加入到ls中
            if((c=infixExpression.charAt(i))<48 || (c=infixExpression.charAt(i))>57){
                ls.add(""+c);
                i++;
            }
            //如果是一个数 需要考虑多位数的问题
            else{
                str="";
                //
                while(i<infixExpression.length() && (c=infixExpression.charAt(i))>=48 && (c=infixExpression.charAt(i))<=57){
                    str+=c;//开始拼接
                    i++;
                }
                ls.add(str);
            }
        }while (i<infixExpression.length());
        return ls;
    }

    //开始对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //只需要一个栈即可
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            //使用正则表达式判断是否遍历的是数(任意数字均可)！！！
            if (item.matches("\\d+")) {
                //匹配的是多位数
                stack.push(item);
            } else {
                //运算符(从栈中弹出两个数进行运算,再入栈)
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                //开始运算
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("符号错误");
                }
                //将运算后的值入栈
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }


    //将中缀表达式转化为后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义
        Stack<String> s1=new Stack<String>();//符号栈
        //由于在s2栈中,在整个转换过程中，没有pop的操作，而且后面还需要逆序输出。因此比较麻烦，所以不使用栈，直接使用List进行替代
        List<String> s2=new ArrayList<String>();

        //遍历ls
        for (String item : ls) {
            //如果是一个数 加入到s2
            if(item.matches("\\d+")){
                s2.add(item);
            }
            //如果是左括号，就直接入到s1
            else if(item.equals("(")) {
                s1.push(item);
            }
            //如果是右括号 依次弹出s1栈顶的运算符，并且压入s2，直到遇到左括号为止，此时将一对括号丢弃
            else if(item.equals(")")){
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将左括号弹出s1【消除括号】
            }

            //考虑符号的优先级问题
            else{
                //当item的优先级小于等于s1栈顶运算符的优先级,将s1栈顶的运算符弹出加入s2中
                //缺少方法来判断优先级
                while(s1.size()!=0 && Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //遍历后还需要将item压入栈中
                s1.push(item);
            }
            //遍历已经结束了 将s1中剩余运算符加入s2中
        }
        while(s1.size()!=0){
            s2.add(s1.pop());
        }
        //因为是存放到list中，所以无需进行逆转
        return s2;
    }

}
