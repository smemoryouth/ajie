package io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

/**
 * description：charSet解码类测试
 *
 * @author ajie
 * data 2018/7/13
 */
public class CharSetTest {
    public static void main(String[] args) {
        // 获取系统默认的编码格式
        Charset charset = Charset.defaultCharset();
        System.out.println(charset);

        // 获取系统支持的所有编码格式
        SortedMap<String, Charset> sortedMap = Charset.availableCharsets();
        Iterator<Map.Entry<String, Charset>> iterator = sortedMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Charset> next = iterator.next();
            String key = next.getKey();
            Charset value = next.getValue();
            System.out.println(key + ":" + value);
        }

        // 判断系统是否支持某编码格式
        String charSetName = "GBK";
        boolean supported = Charset.isSupported(charSetName);
        System.out.println(supported);

        // 使用指定编码进行编码和解码
        Charset charset1 = Charset.forName(charSetName);
        // 实例化一个编码器
        CharsetEncoder encoder = charset1.newEncoder();
        // 实例化一个解码器
        CharsetDecoder decoder = charset1.newDecoder();

        CharBuffer wrap = CharBuffer.wrap("ajie".toCharArray());
        try {
            // 编码
            ByteBuffer encode = encoder.encode(wrap);
            System.out.println(encode);
            // 解码
            CharBuffer decode = decoder.decode(encode);
            System.out.println(decode.toString());
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
    }
}
