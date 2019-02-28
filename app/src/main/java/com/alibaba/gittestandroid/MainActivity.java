package com.alibaba.gittestandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加些注释
        setContentView(R.layout.activity_main);


    }


//    public static String getRandom() {
//        return (int) (Math.random() * 9000) + 1000 + "";
//    }
//
//    public static String createSign(Object obj, String key) {
//        SortedMap dataMap = getAllParam(obj);
//        return createSign(dataMap, key);
//    }
//
//    public static SortedMap<String, String> getAllParam(Object obj) {
//        SortedMap<String, String> dataMap = new TreeMap<>();
//        try {
//            //得到类对象
//            Class reqClz = (Class) obj.getClass();
//            /*
//             * 得到类中的所有属性集合
//             */
//            Field[] fs = reqClz.getDeclaredFields();
//            for (int i = 0; i < fs.length; i++) {
//                Field f = fs[i];
//                f.setAccessible(true); //设置些属性是可以访问的
//                Object val = f.get(obj);//得到此属性的值
//                if (val != null) {
//                    dataMap.put(f.getName(), val.toString());
//                    //Log.e("Test","sign Param name:"+f.getName()+"\t value = "+val);
//                }
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return dataMap;
//    }
//
//    /**
//     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
//     */
//    public static String createSign(SortedMap<String, String> packageParams, String key) {
//        StringBuffer sb = new StringBuffer();
//        Set es = packageParams.entrySet();
//        Iterator it = es.iterator();
//        while (it.hasNext()) {
//            Map.Entry entry = (Map.Entry) it.next();
//            String k = (String) entry.getKey();
//            String v = (String) entry.getValue();
//            //Android Studio升级2.3后类默认加入serialVersionUID
//            if (null != v && !"".equals(v) && !"sign".equals(k)
//                    && !"key".equals(k) && !"URL".equals(k) && !"openKey".equals(k) && !"serialVersionUID".equals(k)
//                    && !"versionCode".equals(k)) {
//                sb.append(k + "=" + v + "&");
//            }
//        }
//        sb.append("key=" + key);
//        //System.out.println("md5 sb:" + sb+"key="+this.getKey());
//        Log.d("dd", "代签名======" + sb);
//        Log.d("dd", "key======" + key);
//        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8")
//                .toUpperCase();
//        if (BuildConfig.DEBUG) {
//            //System.out.println("parmas:" + sb);
//            // System.out.println("packge签名:" + sign);
//        }
//        Log.d("dd","sign======"+sign);
//        return sign;
//
//    }
//
//
//
//    /**
//     *
//     * @param s
//     * @param format
//     * @return
//     */
//    public static boolean validateNumberForForm(String s,String format){
//        int fn = getFloatNumber(format);
//        if(fn == 0){
//            return validateString(s,PATTERN_NUMBER);
//        }else{
//            String patternFormat = "^[0-9]+(.[0-9]{1,"+fn+"})?$";
//            return validateString(s,patternFormat);
//        }
//    }
//
//    public static boolean validateString(String s,String format){
//        Pattern pattern = Pattern.compile(format);
//        Matcher matcher = pattern.matcher(s);
//        System.out.println(matcher.matches());
//        return matcher.matches();
//    }

    /**
     * 根据format获取最多支持几位小数
     * @param format
     *      N1或者N0.都是整数
     *      N0.0   一位小数
     *      N0.00 两位小数
     * @return
     */
    public static int getFloatNumber(String format){
        int p = format.indexOf(".");
        if(p == -1){
            return 0;
        }else{
            return format.length() - 1 - p;
        }
    }



    public static int parse2Int(String s){
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean parse2Boolean(String s){
        try {
            return "1".equals(s);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static float parse2Float(String s){
        try {
            float f = Float.parseFloat(s);
            //Log.e("Test",">>>>>>>>>>>>>>>>>> "+f);
            return f;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }



    public static int parse2Int(String s,int defaultValue){
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static long parse2Long(String s,long defaultValue){
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static double parse2Double(String s){
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /** 格式化条形码 */
    public static String formatBarCode(String barCode){
        try {
            String one = barCode.substring(0,4);
            String two = barCode.substring(4,8);
            String three = barCode.substring(8,12);
            String four = barCode.substring(12,16);
            String five = barCode.substring(16);
            return one+"    "+two+"    "+three+"    "+four+"    "+five;
        } catch (Exception e) {
            e.printStackTrace();
            return barCode;
        }
    }

    public static String validatePassword(String password,String password2){
        if (password.length() < 6) {
            return "亲，密码要最少6位！";
        }

        if (password.length() > 16) {
            return "亲，密码最多16位！";
        }

        if(!password2.equals(password)){
            return "亲，两次输入的密码不一致！";
        }
        return null;
    }
}
