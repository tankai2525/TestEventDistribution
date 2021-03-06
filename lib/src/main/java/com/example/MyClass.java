package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyClass {

    public static void main(String[] args) {
//        System.out.println(1000 & 0xff);
//        System.out.println(hexStr2BinStr("80000"));
//        System.out.println(~(0x80000));
//
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(0,1);
//        list.add(0,2);
//        list.add(0,3);
//        list.add(1,4);
//        System.out.println(list.size());
//        System.out.println(list);
//
//        System.out.println(1 << 3);

//        List list1 = getRandoms(list,5);
//        System.out.println(list1.size());
//        System.out.println(list1);


        MyClass myClass = new MyClass();
        myClass.test();

    }

    public void test() {
        A a = new A();
        a.i = 1;
        updateA(a);
        System.out.println(a.i);
    }

    public static <T> List<T> getRandoms(List<T> list, int count) {

        List<T> labels = new ArrayList<>();
        if (list != null && list.size() > 0) {
            count = count > list.size() ? list.size() : count;
            Random random = new Random();
            for (int i = 0; i < count; i++) {
                int r = random.nextInt(list.size());
                labels.add(list.get(r));
                list.remove(r);
            }
        }
        return labels;
    }


    private static String hexStr =  "0123456789ABCDEF";
    private static String[] binaryArray =
            {"0000","0001","0010","0011",
                    "0100","0101","0110","0111",
                    "1000","1001","1010","1011",
                    "1100","1101","1110","1111"};
    /**
     *
     * @param hexString
     * @return 将十六进制转换为二进制字节数组   16-2
     */
    public static byte[] hexStr2BinArr(String hexString){
        //hexString的长度对2取整，作为bytes的长度
        int len = hexString.length()/2;
        byte[] bytes = new byte[len];
        byte high = 0;//字节高四位
        byte low = 0;//字节低四位
        for(int i=0;i<len;i++){
            //右移四位得到高位
            high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);
            low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));
            bytes[i] = (byte) (high|low);//高地位做或运算
        }
        return bytes;
    }

    /**
     *
     * @param hexString
     * @return 将十六进制转换为二进制字符串   16-2
     */
    public static String hexStr2BinStr(String hexString){
        return bytes2BinStr(hexStr2BinArr(hexString));
    }

    /**
     *
     * @param bArray
     * @return 二进制数组转换为二进制字符串   2-2
     */
    public static String bytes2BinStr(byte[] bArray){

        String outStr = "";
        int pos = 0;
        for(byte b:bArray){
            //高四位
            pos = (b&0xF0)>>4;
            outStr+=binaryArray[pos];
            //低四位
            pos=b&0x0F;
            outStr+=binaryArray[pos];
        }
        return outStr;
    }

    public static void updateA(A a) {
        a.i = 2;
    }

    private class A {
        public A(){}

        public int i;
    }

}
