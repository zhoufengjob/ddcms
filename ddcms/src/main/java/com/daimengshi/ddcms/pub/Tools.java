package com.daimengshi.ddcms.pub;

import com.daimengshi.ddcms.admin.bean.SysInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by zhoufeng on 2017/12/8.
 * 静态工具类
 */
public class Tools {

    private static List<SysInfo> sysInfos;

    public static List<SysInfo> getSysInfos() {
        if (sysInfos == null) {
            sysInfos = new ArrayList<>();
            Properties sysProperty = System.getProperties(); //系统属性
            sysInfos.add(new SysInfo("操作系统的名称：", sysProperty.getProperty("os.name")));
            sysInfos.add(new SysInfo("操作系统的构架：", sysProperty.getProperty("os.arch")));
            sysInfos.add(new SysInfo("操作系统的版本：", sysProperty.getProperty("os.version")));
            sysInfos.add(new SysInfo("用户的账户名称：", sysProperty.getProperty("user.name")));
            sysInfos.add(new SysInfo("用户的主目录：", sysProperty.getProperty("user.home")));
            sysInfos.add(new SysInfo("用户的当前工作目录：", sysProperty.getProperty("user.dir")));
            sysInfos.add(new SysInfo("Java的运行环境版本：", sysProperty.getProperty("java.version")));
            sysInfos.add(new SysInfo("Java的运行环境供应商：", sysProperty.getProperty("java.vendor")));
            sysInfos.add(new SysInfo("Java供应商的URL：", sysProperty.getProperty("java.vendor.url")));
            sysInfos.add(new SysInfo("Java的安装路径：", sysProperty.getProperty("java.home")));
        }
        return sysInfos;
    }

}
