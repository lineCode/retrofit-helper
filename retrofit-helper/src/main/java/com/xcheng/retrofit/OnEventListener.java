package com.xcheng.retrofit;

import android.arch.lifecycle.Lifecycle;
import android.util.Log;

/**
 * 全局监听一些核心事件
 */
public interface OnEventListener {
    OnEventListener NONE = new OnEventListener() {
        @Override
        public void onDisposed(Call<?> call, Lifecycle.Event event) {
            Log.d(Call.TAG, "disposed by-->" + event + ", " + call.request());
        }

        @Override
        public void onObserverCountChanged(LifecycleProvider provider, int oldCount, int newCount) {
            Log.d(Call.TAG, "countChanged-->old:" + oldCount + ", new:" + newCount + ", provider:" + provider);
        }

        @Override
        public void onThrowable(Call<?> call, Throwable t) {
            Log.w(Call.TAG, "onThrowable", t);
        }
    };

    /**
     * 由于生命周期原因请求被取消了,此回调函数不保证与生命周期函数同步的
     */
    void onDisposed(Call<?> call, Lifecycle.Event event);

    /**
     * @param provider current LifecycleProvider
     * @param oldCount old observers count
     * @param newCount new observers count
     */
    void onObserverCountChanged(LifecycleProvider provider, int oldCount, int newCount);

    /**
     * http请求抛出的异常信息
     *
     * @param call {@link Call}
     * @param t    any throwable, exclude {@link DisposedException}
     */
    void onThrowable(Call<?> call, Throwable t);
}