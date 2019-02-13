package com.school.service;

public interface RedisService {

    /**
     * 根据指定key获取String
     * @param key
     * @return
     */
    public String getStr(String key);

    /**
     * 设置Str缓存
     * @param key
     * @param val
     * @param expire 时长
     */
    public void setStr(String key, String val,long expire);

    /**
     * 删除指定key
     * @param key
     */
    public void del(String key);

    /**
     * 根据指定o获取Object
     * @param o
     * @return
     */
    public Object getObj(Object o);

    /**
     * 设置obj缓存
     * @param o1
     * @param o2
     * @param expire 时长
     */
    public void setObj(Object o1, Object o2,long expire);

    /**
     * 删除Obj缓存
     * @param o
     */
    public void delObj(Object o);
}
