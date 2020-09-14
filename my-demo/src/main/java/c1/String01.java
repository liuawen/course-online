package c1;

import org.junit.Test;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-09-03
 */
public class String01 {
    @Test
    public void test01() {
        String s = "hello";
        s = "world";
        System.out.println(s);//world
        String str = "hello world";
        // 这种写法是替换不掉的，必须接受 replace 方法返回的参数才行。
        str.replace("l", "dd");
        System.out.println(str);//hello world
        str = str.replace("l", "dd");
        System.out.println(str);//heddddo worddd
    }
    @Test
    public void testReplace(){
        String str ="hello word !!";
//        log.info("替换之前 :{}",str);
//        str = str.replace('l','d');
//        log.info("替换所有字符 :{}",str);
//        str = str.replaceAll("d","l");
//        log.info("替换全部 :{}",str);
//        str = str.replaceFirst("l","");
//        log.info("替换第一个 l :{}",str);
    }
//    //输出的结果是：
//    替换之前 :hello word !!
//    替换所有字符 :heddo word !!
//    替换全部 :hello worl !!
//    替换第一个 :helo worl !!

}
