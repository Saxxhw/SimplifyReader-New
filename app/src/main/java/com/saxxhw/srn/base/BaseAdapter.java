package com.saxxhw.srn.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saxxhw on 2017/1/17.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */
public abstract class BaseAdapter<E> extends android.widget.BaseAdapter {

    protected LayoutInflater mInflater;

    private List<E> mList = new ArrayList<>();

    private Context mContext;

    public BaseAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public E getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 添加数据
     *
     * @param e 添加对象
     */
    public void addItem(E e) {
        mList.add(e);
    }

    /**
     * 在指定索引位置添加数据
     *
     * @param location 索引
     * @param e        数据
     */
    public void addItem(int location, E e) {
        mList.add(location, e);
    }

    /**
     * 以集合的形式添加数据
     *
     * @param collection
     */
    public void addItems(List<E> collection) {
        mList.addAll(collection);
    }

    /**
     * 在指定索引位置添加数据集合
     *
     * @param location   索引
     * @param collection 集合
     */
    public void addItems(int location, List<E> collection) {
        mList.addAll(location, collection);
    }

    /**
     * 移除指定对象数据
     *
     * @param e 被移除对象
     */
    public void removeItem(E e) {
        mList.remove(e);
    }

    /**
     * 移除指定索引位置处的对象
     *
     * @param location 索引
     */
    public void removeItem(int location) {
        mList.remove(location);
    }

    /**
     * 移除指定对象集合
     *
     * @param collection 待移除集合
     */
    public void removeItems(List<E> collection) {
        mList.removeAll(collection);
    }

    /**
     * 清空数据集
     */
    public void clear() {
        mList.clear();
    }

    /**
     * 获取上下文对象
     *
     * @return
     */
    public Context getContext() {
        return mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = bindView(position, convertView, parent);
        return convertView;
    }

    public abstract View bindView(int position, View convertView, ViewGroup parent);

    public List<E> getList() {
        return mList;
    }
}
