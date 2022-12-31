package com.hspedu.fgrspringmvc.handler;

import java.lang.reflect.Method;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 记录url 和控制器方法映射关系
 */
public class FgrHandler {
    private String url;
    private Object controller;
    private Method method;

    public FgrHandler() {
    }

    public FgrHandler(String url, Object controller, Method method) {
        this.url = url;
        this.controller = controller;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "FgrHandler{" +
                "url='" + url + '\'' +
                ", controller=" + controller +
                ", method=" + method +
                '}';
    }
}
