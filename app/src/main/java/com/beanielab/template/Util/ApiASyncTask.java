package com.beanielab.template.Util;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.Response;

import org.json.JSONObject;

/**
 * Created by tommyjepsen on 22/11/15.
 */
public abstract class ApiASyncTask<T> extends AsyncTask<Void, Void, ApiASyncTask.Holder<T>> {

    private ASyncListener<T> listener;
    private Holder<T> mHolder;
    private boolean mIsFetching = false;

    protected abstract Response doWebserviceCall() throws Exception;

    protected abstract T doParsing(Response response) throws Exception;

    protected abstract void setData(T t) throws Exception;

    public ApiASyncTask(ASyncListener<T> listener) {
        this.listener = listener;
        mHolder = new Holder<T>();
    }

    @Override
    protected Holder<T> doInBackground(Void... params) {
        mIsFetching = true;

        mHolder.obj = null;
        mHolder.response = null;

        try {
            mHolder.response = doWebserviceCall();

            if (mHolder.response == null) {
                listener.onError(600);
            }

        } catch (Exception e) {
            listener.onError(600);
        }
        try {
            if (mHolder.response.isSuccessful()) {
                T temp = doParsing(mHolder.response);
                mHolder.obj = temp;
                setData(temp);
            }
        } catch (Exception e) {
            Log.d("", "" + e);

            mHolder.response = mHolder.response.newBuilder().code(601).build();
        }

        return mHolder;
    }

    @Override
    protected void onPostExecute(Holder<T> mHolder) {
        mIsFetching = false;

        if (listener == null || mHolder.response == null) {
            return;
        }
        if (mHolder.response.isSuccessful()) {
            listener.onSuccess(mHolder.obj);
        } else if (mHolder.response.code() == 401 || mHolder.response.code() == 403) {
            listener.onUnAuthorized(mHolder.response.code());

        } else {
            listener.onError(mHolder.response.code());
            try {
                Log.d("", "Error: " + new JSONObject(mHolder.response.body().toString()).optString("error"));
                Log.d("", "Type: " + new JSONObject(mHolder.response.body().toString()).optString("type"));
            } catch (Exception e) {
                Log.e("", ""+e);
            }
        }

        listener.onAlways();
    }

    /**
     * Will return true if the task is still in the state "doInBackground"
     *
     * @return boolean
     */
    public boolean isFetching() {
        return mIsFetching;
    }

    public static class Holder<T> {
        T obj;
        Response response;
    }

    public interface ASyncListener<T> {
        public void onSuccess(T result);

        public void onError(int code);

        public void onUnAuthorized(int code);

        public void onAlways();
    }
}