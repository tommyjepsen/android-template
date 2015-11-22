package com.beanielab.template.Util.Tasks;

import com.beanielab.template.Util.ApiASyncTask;
import com.beanielab.template.Util.Api;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tommyjepsen on 22/11/15.
 */
public class GetDataTask extends ApiASyncTask<ArrayList<String>> {

    public GetDataTask(ASyncListener<ArrayList<String>> aSyncListener) {
        super(aSyncListener);
    }

    @Override
    protected Response doWebserviceCall() throws Exception {
        return Api.okHttpClient.newCall(
                Api.request()
                        .get()
                        .url(Api.url + "getusers")
                        .build()
        ).execute();
    }

    @Override
    protected ArrayList<String> doParsing(Response response) throws Exception {
        JSONArray json = new JSONObject(response.body().string()).getJSONArray("data");

        ArrayList<String> users = new ArrayList<>();

        return users;
    }

    @Override
    protected void setData(ArrayList<String> result) throws Exception {

    }
}
