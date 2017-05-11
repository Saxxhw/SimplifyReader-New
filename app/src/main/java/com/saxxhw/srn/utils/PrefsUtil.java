package com.saxxhw.srn.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 作者：Create By Saxxhw on 16/9/6 16:14
 * 邮箱：Saxxhw@126.com
 * 功能：SharedPreferences操作封装工具类
 */
public class PrefsUtil {

    /**
     * <p>Put a <b>boolean</b>/<b>float</b>/<b>int</b>/<b>long</b>/<b>String</b> value into
     * preferences file named as {@linkplain Context#getPackageName() packageName}.
     *
     * @throws IllegalArgumentException If The type of <code>value</code> is not one of those.
     * @see SharedPreferences.Editor
     */
    public static void put(Context context, String key, Object value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE).edit();
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else {
            throw new IllegalArgumentException("Argument illegal, see JavaDocs.");
        }
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * <p>Retrieve a <b>boolean</b>/<b>float</b>/<b>int</b>/<b>long</b>/<b>String</b> value from preferences.
     *
     * @return value corresponds to <code>key</code> if <code>key</code> exists,
     * <code>defValue</code> otherwise.
     * @throws IllegalArgumentException If The type of <code>defValue</code> is not one of those.
     * @see SharedPreferences
     */
    @SuppressWarnings("unchecked")
    public static <P> P get(Context context, String key, P defValue) {
        SharedPreferences prefs = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        Object result;
        if (defValue instanceof Boolean) {
            result = prefs.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            result = prefs.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Integer) {
            result = prefs.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Long) {
            result = prefs.getLong(key, (Long) defValue);
        } else if (defValue instanceof String) {
            result = prefs.getString(key, (String) defValue);
        } else {
            throw new IllegalArgumentException("Argument illegal, see JavaDocs.");
        }
        return (P) result;
    }

    public static void delete(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        if (prefs.contains(key)) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(key);
            SharedPreferencesCompat.apply(editor);
        }
    }

    /**
     * 清除所有数据
     *
     * @param context 上下文对象
     */
    public static void clear(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context 上下文对象
     * @param key     键
     * @return true or false
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context 上下文对象
     * @return 数据集合
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return Method
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor 编辑器对象
         */
        static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }
}
