package com.daimengshi.ddcms.utils;

import com.daimengshi.ddcms.common.AppInfo;
import io.jboot.Jboot;

/**
 * Created by zhoufeng on 2018/1/17.
 *
 */
public class ServiceUtils {

    /**
     * 动态获取Service实现
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> clazz) {
        AppInfo app = Jboot.config(AppInfo.class);

        //判断是否启用集群
        if ("1".equals(app.getIsRpc())) {//是否开启远程调用
            return Jboot.service(clazz);
        } else {
            return Jboot.bean(clazz);

        }
    }
}
