package com.beanielab.template.Base;

import android.support.v4.app.Fragment;

import com.beanielab.template.MainFragmentActivity;

/**
 * Created by tommyjepsen on 20/11/15.
 */
public abstract class BaseFragment extends Fragment {


    public MainFragmentActivity getMainFragmentActivity() {
        return (MainFragmentActivity) getActivity();
    }


}
