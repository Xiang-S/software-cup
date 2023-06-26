package com.ccit.springboot.utils;

/**
 * 选择了ak或使用IP白名单校验：
 */

import cn.hutool.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class SearchHttpAK {

    public static String URL = "https://api.map.baidu.com/location/ip?";

    public static String AK = "ppREbEk6YORpZSEi8UnXGGkKRGo6vrIY";

    public static String getAddress() {

        SearchHttpAK snCal = new SearchHttpAK();

        Map params = new LinkedHashMap<String, String>();
        params.put("ip", "");
        params.put("coor", "bd09ll");
        params.put("ak", AK);


        try {

            return snCal.requestGetAK(URL, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 默认ak
     * 选择了ak，使用IP白名单校验：
     * 根据您选择的AK以为您生成调用代码
     * 检测到您当前的ak设置了IP白名单校验
     * 您的IP白名单中的IP非公网IP，请设置为公网IP，否则将请求失败
     * 请在IP地址为的计算发起请求，否则将请求失败
     */
    public String requestGetAK(String strUrl, Map<String, String> param) throws Exception {
        if (strUrl == null || strUrl.length() <= 0 || param == null || param.size() <= 0) {
            return "";
        }

        StringBuffer queryString = new StringBuffer();
        queryString.append(strUrl);
        for (Map.Entry<?, ?> pair : param.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),
                    "UTF-8") + "&");
        }

        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }

        java.net.URL url = new URL(queryString.toString());
        URLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();

        InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        isr.close();
        JSONObject jsonObject = new JSONObject(buffer.toString());
        JSONObject content = (JSONObject) jsonObject.get("content");
        String address = (String) content.get("address");
        return address;
    }
}
