package data;

import java.util.HashMap;
import java.util.Map;

/**
 * description：
 * <p>
 * using namespace std;
 * int main()
 * {
 * string str;
 * map<string, int>temp;
 * getline(cin, str);
 * int plow = 0, phigh = 0;
 * string s = "";
 * while (phigh < str.length())
 * {
 * if (str[phigh] == ' ')
 * {
 * phigh++;
 * plow = phigh;
 * temp[s]++;
 * s = "";
 * continue;
 * <p>
 * }
 * else
 * {
 * s = s + str[phigh];
 * if (phigh == str.length() - 1) temp[s]++;
 * phigh++;
 * }
 * <p>
 * }
 * vector<string>res;
 * auto j = temp.begin();
 * while(j != temp.end())
 * {
 * string tmp = j->first;
 * int len = tmp.size();
 * for (int i = 0; i < len; i++)
 * {
 * if (!(tmp[i] >= 65 && tmp[i] <= 90 || tmp[i] >= 97 && tmp[i] <= 122))
 * {
 * if (tmp[i] >= 48 && tmp[i] <= 57)
 * {
 * res.push_back(tmp);
 * j = temp.erase(j);
 * j--;
 * break;
 * }
 * <p>
 * }
 * else if (tmp[len-1] == 44 || tmp[len-1] == 46 || tmp[len-1] == 58 || tmp[len-1] == 33) {
 * string new_tmp(tmp.begin(), tmp.end() - 1);
 * int b = j->second;
 * temp.insert(make_pair(new_tmp, b));
 * j = temp.erase(j);
 * j--;
 * break;
 * }
 * }
 * j++;
 * }
 * for (auto s = temp.begin(); s != temp.end(); s++)
 * {
 * cout << s->first << " " << s->second << endl;
 * }
 * cout << res.size() << endl;
 * <p>
 * system("pause");
 * return 0;
 * }
 *
 * @author 阿劼
 * data 2019/3/15 20:13
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(getSangFor("sangforsangangfro"));
//        getNumberOfWord("wo hi hi hi f1d f1d");
//        String regex = "[a-zA-Z]";
//        System.out.println("a".matches(regex));
    }

    /**
     * 求一个字符串中指定字符出现的个数
     * @param str
     * @return
     */
    private static int getSangFor(String str) {
        if (str.length() > 10001){
            return -1;
        }
        char[] arr = str.toCharArray();
        String regex = "sangfor";
        int flag = 0;
        int num = 0;
        for (char anArr : arr) {
            if (regex.charAt(flag) == anArr) {
                flag++;
                if (flag == regex.length()) {
                    flag = 0;
                    num++;
                }
            }
        }
        return num;
    }


    /**
     * 找单词出现的个数和非法单词的个数
     * @param str
     */
    public static void getNumberOfWord(String str) {
        if (str.length() > 1000) {
            return;
        }
        int unWordNumber = 0;
        String regex = "\\w+";
        HashMap<String, Integer> map = new HashMap<>();
        String[] arr = str.split(" ");
        for (String anArr : arr) {
            if (!anArr.matches(regex)) {
                unWordNumber++;
            } else {
                if (map.get(anArr) == null) {
                    map.put(anArr, 1);
                } else {
                    int value = map.get(anArr);
                    map.put(anArr, value + 1);
                }
            }
        }

        for (Map.Entry<String, Integer> it : map.entrySet()) {
            System.out.println(it.getKey() + "出现" + it.getValue() + "次");
        }
        System.out.println("非法单词出现" + unWordNumber + "次");
    }
}
