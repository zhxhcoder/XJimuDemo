package com.xlab.base.utils;

import android.app.Activity;
import android.util.Log;

import java.util.Stack;

/**
 * Created by zhxh on 2018/12/12
 */
public class ActivityManager {
    private static final String TAG = "ActivityManager";
    private static Stack<Activity> activityStack = new Stack<Activity>();
    private static ActivityManager instance;

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /***
     * 退出栈顶Activity
     * @param activity
     */
    public void popActivity(Activity activity) {
        try {
            if (activity != null) {
                // 在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
                activityStack.remove(activity);
                activity.finish();
                Log.d(TAG, "ActivityManager.popActivity.activity:" + activity.getComponentName());
                Log.d(TAG, "ActivityManager.popActivity.stack.size=" + activityStack.size());
            }
        } catch (Exception e) {
        }
    }

    /**
     * 是否上一个activity(判断是否是某个页面跳转过来)
     *
     * @param cls
     * @return
     */
    public boolean isPreviousActivity(Class<?> cls) {

        try {
            if (null != activityStack && activityStack.size() > 0) {
                Activity activity = activityStack.get(activityStack.size() - 2);
                if (activity.getClass().equals(cls)) {
                    return true;
                }
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return false;
    }

    /**
     * 是否包含某activity
     * * @return
     */
    public boolean hasActivity(Class<?> cls) {
        try {
            if (null != activityStack) {

                for (int i = 0; i < activityStack.size(); i++) {

                    Activity activity = activityStack.get(i);
                    if (activity.getClass().equals(cls)) {
                        return true;
                    }
                }
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return false;
    }

    /***
     *  获得当前栈顶Activity
     * @return
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (!activityStack.empty())
            activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获取所有这栈中activity名字
     *
     * @return
     */
    public String getAllActivityNameInStack() {

        StringBuilder builder = new StringBuilder();
        if (!activityStack.empty()) {
            for (int i = 0; i < activityStack.size(); i++) {
                builder.append(activityStack.get(i).getClass().getSimpleName()).append(" \r");
            }
        }

        return builder.toString();
    }

    /**
     * 获取栈中activity数量
     *
     * @return
     */
    public static int getActivityNum() {
        if (!activityStack.empty())
            return activityStack.size();
        return 0;
    }

    /***
     * 将某一activity推出栈中
     * @param
     */
    public void popOneActivity(Class<?> cls) {
        try {
            if (null != activityStack) {
                for (int i = 0; i < activityStack.size(); i++) {
                    Activity activity = activityStack.get(i);
                    if (activity.getClass().equals(cls)) {
                        popActivity(activity);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /****
     * 将当前Activity推入栈中
     * @param activity
     */
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
//		Log.d(TAG, "ActivityManager.pushActivity.activity:"+activity.getComponentName());
//		Log.d(TAG, "ActivityManager.pushActivity.stack.size="+activityStack.size());
    }

    /***
     * 退出栈中所有Activity
     * @param cls
     */
    public void popAllActivityExceptOne(Class<?> cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            popActivity(activity);
        }
    }

    /****
     * 退出栈中所有Activity
     */
    public void popAllActivity() {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
        }
    }

    /**
     * 返回主页面
     */
    public void popToHomeActivity() {
//		Log.d(TAG, "ActivityManager.popToHomeActivity---------->>>");
        int size = activityStack.size();
        while (size > 1) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
            size = activityStack.size();
        }
//		Log.d(TAG, "ActivityManager.popToHomeActivity.stack.size="+size);
//		Log.d(TAG, "ActivityManager.popToHomeActivity---------->>>");
    }
}
