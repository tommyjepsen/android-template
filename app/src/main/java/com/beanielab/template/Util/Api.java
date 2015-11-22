package com.beanielab.template.Util;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tommyjepsen on 22/11/15.
 */
public class Api {

    public static HashMap<String, String> headers = new LinkedHashMap();
    public static OkHttpClient okHttpClient = new OkHttpClient();

    public static final String url = "http://www.okhttp.com"; //Add url

    static {
//        headers.put("Accept", "application");
    }

    public static void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public static Request.Builder request() {
        Request.Builder r = new Request.Builder();
        for (Map.Entry<String, String> e : headers.entrySet()) {
            r.addHeader(e.getKey(), e.getValue());
        }
        return r;
    }

}
