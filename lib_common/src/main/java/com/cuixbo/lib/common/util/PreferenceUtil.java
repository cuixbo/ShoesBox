package com.cuixbo.lib.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author xiaobocui
 * @date 2019-12-12
 */
@SuppressWarnings("unused")
public class PreferenceUtil {

    private static volatile PreferenceUtil instance;
    private static SharedPreferences sPreferences;

    private PreferenceUtil(Context context) {
        sPreferences = context.getSharedPreferences("prefs_default", Context.MODE_PRIVATE);
    }

    /**
     * 单例返回
     *
     * @return
     */
    public static PreferenceUtil get(Context context) {
        //  1.防止方法的所有调用都走synchronized检查
        if (instance == null) {
            synchronized (PreferenceUtil.class) {
                // 2.防止多个线程重复创建对象
                if (instance == null) {
                    // 3.用volatile修饰instance 禁止指令重排序
                    instance = new PreferenceUtil(context);
                }
            }
        }
        return instance;
    }

    /**
     * 获取boolean类型的值
     *
     * @param key          key
     * @param defaultValue defaultValue
     * @return value or defaultValue if not exist
     */
    public boolean getBool(String key, boolean defaultValue) {
        return sPreferences.getBoolean(key, defaultValue);
    }

    /**
     * 存储boolean类型的值
     *
     * @param key   key
     * @param value value
     */
    public void setBool(String key, boolean value) {
        sPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * 获取int类型的值
     *
     * @param key          key
     * @param defaultValue defaultValue
     * @return value or defaultValue if not exist
     */
    public int getInt(String key, int defaultValue) {
        return sPreferences.getInt(key, defaultValue);
    }

    /**
     * 存储int类型的值
     *
     * @param key   key
     * @param value value
     */
    public void setInt(String key, int value) {
        sPreferences.edit().putInt(key, value).apply();
    }

    /**
     * 获取float类型的值
     *
     * @param key          key
     * @param defaultValue defaultValue
     * @return value or defaultValue if not exist
     */
    public Float getFloat(String key, float defaultValue) {
        return sPreferences.getFloat(key, defaultValue);
    }

    /**
     * 存储float类型的值
     *
     * @param key   key
     * @param value value
     */
    public void setFloat(String key, float value) {
        sPreferences.edit().putFloat(key, value).apply();
    }

    /**
     * 获取long类型的值
     *
     * @param key          key
     * @param defaultValue defaultValue
     * @return value or defaultValue if not exist
     */
    public long getLong(String key, long defaultValue) {
        return sPreferences.getLong(key, defaultValue);
    }

    /**
     * 存储long类型的值
     *
     * @param key   key
     * @param value value
     */
    public void setLong(String key, long value) {
        sPreferences.edit().putLong(key, value).apply();
    }

    /**
     * 获取String类型的值
     *
     * @param key          key
     * @param defaultValue defaultValue
     * @return value or defaultValue if not exist
     */
    public String getString(String key, String defaultValue) {
        return sPreferences.getString(key, defaultValue);
    }

    /**
     * 存储String类型的值
     *
     * @param key   key
     * @param value value
     */
    public void setString(String key, String value) {
        sPreferences.edit().putString(key, value).apply();
    }

    /**
     * 获取String类型的值
     *
     * @param key          key
     * @param defaultValue defaultValue
     * @return value or defaultValue if not exist
     */
    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return sPreferences.getStringSet(key, defaultValue);
    }

    /**
     * 存储String类型的值
     *
     * @param key   key
     * @param value value
     */
    public void setStringSet(String key, Set<String> value) {
        sPreferences.edit().putStringSet(key, value).apply();
    }


}