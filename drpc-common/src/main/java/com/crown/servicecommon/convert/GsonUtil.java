//package com.crown.servicecommon.convert;
//
//import com.google.gson.Gson;
//
//public class GsonUtil {
//
//    private static final Gson gson = new Gson();//做成单例
//
//    public static byte[] encode(Object object){
//        return gson.toJson(object).getBytes();
//    }
//
//    public static <T> T decode(String json,Class<T> tClass){
//        return gson.fromJson(json,tClass);
//    }
//}
