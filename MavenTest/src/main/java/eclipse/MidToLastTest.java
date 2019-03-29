package eclipse;


/**
 * 符号优先级规定,优先级越高，数字越小
 */
class Constant {
    /**
     * 栈内加法
     */
    static final int OPERATORS_PRIO_PLUS_IN = 4;
    /**
     * 栈内减法
     */
    static final int OPERATORS_PRIO_SUB_IN = 4;
    /**
     * 栈内乘法
     */
    static final int OPERATORS_PRIO_MULTY_IN = 2;
    /**
     * 栈内除法
     */
    static final int OPERATORS_PRIO_DIV_IN = 2;
    /**
     * 栈内左括号
     */
    static final int OPERATORS_PRIO_LEFT_BRAK_IN = 10;
    /**
     * 栈外加法
     */
    static final int OPERATORS_PRIO_PLUS_OUT = 5;
    /**
     * 栈外减法
     */
    static final int OPERATORS_PRIO_SUB_OUT = 5;
    /**
     * 栈外乘法
     */
    static final int OPERATORS_PRIO_MULTY_OUT = 3;
    /**
     * 栈外除法
     */
    static final int OPERATORS_PRIO_DIV_OUT = 3;
    /**
     * 栈外左括号
     */
    static final int OPERATORS_PRIO_LEFT_BRAK_OUT = 1;
    /**
     * 栈外右括号
     */
    static final int OPERATORS_PRIO_RIGHT_BRAK_OUT = 10;
    /**
     * 错误
     */
    static final int OPERATORS_PRIO_ERROR = -1;
}

/**
 * description：中缀表达式转后缀表达式
 *
 * @author ajie
 * data 2018/5/29
 */
public class MidToLastTest {
    /**
     * 符号优先级的比较
     * @param opera 符号
     * @param instack 栈内还是栈外的标志，true表示栈内，false表示栈外
     * @return 符号的优先级数字表示
     */
    public static int Get_Prio(char opera, boolean instack) {
        int prio;

        if (instack) {
            switch (opera) {
                case '+':
                    prio = Constant.OPERATORS_PRIO_PLUS_IN;
                    break;
                case '-':
                    prio = Constant.OPERATORS_PRIO_SUB_IN;
                    break;
                case '*':
                    prio = Constant.OPERATORS_PRIO_MULTY_IN;
                    break;
                case '/':
                    prio = Constant.OPERATORS_PRIO_DIV_IN;
                    break;
                case '(':
                    prio = Constant.OPERATORS_PRIO_LEFT_BRAK_IN;
                    break;
                default:
                    prio = Constant.OPERATORS_PRIO_ERROR;
                    break;
            }
        } else {
            switch (opera) {
                case '+':
                    prio = Constant.OPERATORS_PRIO_PLUS_OUT;
                    break;
                case '-':
                    prio = Constant.OPERATORS_PRIO_SUB_OUT;
                    break;
                case '*':
                    prio = Constant.OPERATORS_PRIO_MULTY_OUT;
                    break;
                case '/':
                    prio = Constant.OPERATORS_PRIO_DIV_OUT;
                    break;
                case '(':
                    prio = Constant.OPERATORS_PRIO_LEFT_BRAK_OUT;
                    break;
                case ')':
                    prio = Constant.OPERATORS_PRIO_RIGHT_BRAK_OUT;
                    break;
                default:
                    prio = Constant.OPERATORS_PRIO_ERROR;
                    break;
            }
        }
        return prio;
    }

    /**
     * 转换过程
     * @param mid 中缀表达式
     * @param last 后缀表达式的字符保存数组
     */
    public static void midToLast(String mid, char[] last) {
        char[] stack = new char[mid.length()];
        int len = mid.length();
        // 中缀表达式遍历索引
        int i = 0;
        // 后缀表达式字符串保存索引
        int j = 0;
        int top = 0;
        int prioIN;
        int prioOut;
        while (i != len) {
            // 是数字的情况
            if (Character.isDigit(mid.charAt(i))) {
                last[j++] = mid.charAt(i);
                i++;
                // 是符号的情况
            } else {
                // 若栈中已经有元素
                if (top != 0) {
                    prioIN = Get_Prio(stack[top - 1], true);
                    prioOut = Get_Prio(mid.charAt(i), false);
                    // 栈内优先级低，栈外符号直接入栈
                    if (prioIN > prioOut) {
                        stack[top++] = mid.charAt(i);
                        i++;
                        // 栈内括号出栈，栈外括号忽略
                    } else if (prioIN == prioOut) {
                        top--;
                        i++;
                        // 栈内优先级高，出栈到后缀表达式字符数组中
                    } else {
                        last[j++] = stack[--top];
                    }
                    // 若为空栈则直接入栈
                } else {
                    stack[top++] = mid.charAt(i);
                    i++;
                }
            }
        }
        // 中缀表达式遍历完毕若是栈中还有元素则直接出栈保存到后缀字符数组中
        while (top > 0) {
            last[j++] = stack[--top];
        }
    }

    /**
     * 后缀表达式字符的输出
     * @param strLast 字符数组
     */
    public static void print(char[] strLast) {
        for (int x = 0; x < strLast.length; x++) {
            System.out.print(strLast[x] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String strMid = "2+3*5-4*(5-3)";
        char[] strLast = new char[strMid.length()];
        midToLast(strMid, strLast);
        print(strLast);
    }
}
