package com.fnhelper.fnapp.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public abstract class BaseHandler<T> extends Handler {
    protected WeakReference<T> activity;

    public BaseHandler(T activity) {
	this.activity = new WeakReference<T>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
	if (activity.get() == null)
	    return;
	handleMessage(activity.get(), msg);
    }

    protected abstract void handleMessage(T reference, Message msg);
}