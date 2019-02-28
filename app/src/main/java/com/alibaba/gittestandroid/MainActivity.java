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
    
    
    public static String getRandom() {
        return (int) (Math.random() * 9000) + 1000 + "";
    }

    public static String createSign(Object obj, String key) {
        SortedMap dataMap = getAllParam(obj);
        return createSign(dataMap, key);
    }

    public static SortedMap<String, String> getAllParam(Object obj) {
        SortedMap<String, String> dataMap = new TreeMap<>();
        try {
            //得到类对象
            Class reqClz = (Class) obj.getClass();
            /*
             * 得到类中的所有属性集合
             */
            Field[] fs = reqClz.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true); //设置些属性是可以访问的
                Object val = f.get(obj);//得到此属性的值
                if (val != null) {
                    dataMap.put(f.getName(), val.toString());
                    //Log.e("Test","sign Param name:"+f.getName()+"\t value = "+val);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public static String createSign(SortedMap<String, String> packageParams, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            //Android Studio升级2.3后类默认加入serialVersionUID
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k) && !"URL".equals(k) && !"openKey".equals(k) && !"serialVersionUID".equals(k)
                    && !"versionCode".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        //System.out.println("md5 sb:" + sb+"key="+this.getKey());
        Log.d("dd", "代签名======" + sb);
        Log.d("dd", "key======" + key);
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8")
                .toUpperCase();
        if (BuildConfig.DEBUG) {
            //System.out.println("parmas:" + sb);
            // System.out.println("packge签名:" + sign);
        }
        Log.d("dd","sign======"+sign);
        return sign;

    }
}
