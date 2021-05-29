package com.suchness.realscene.common.utils;

public class SqlUtil {
    public  static String  printArray(int[]arr){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("(");
        for (int i=0;i<arr.length-1;i++){

            stringBuffer.append(arr[i]+",");
        }
        stringBuffer.append(arr[arr.length-1]);
        stringBuffer.append(")");
        return  stringBuffer.toString();

    }

    public  static  String printStringArray(String []arr){

        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("and c.record_id in (");
        for (int i=0;i<arr.length-1;i++){

            stringBuffer.append("\'"+arr[i]+"\'"+",");
        }
        stringBuffer.append("\'"+arr[arr.length-1]+"\'");
        stringBuffer.append(")");
        return  stringBuffer.toString();
    }
}
