package com.daimengshi.ddcms;

import io.jboot.codegen.model.JbootModelGenerator;
import io.jboot.codegen.service.JbootServiceGenerator;

/**
 * Created by zhoufeng on 2017/12/6.
 */
public class GeneratorCode {
    public static void main(String[] args) {

        //生成service 的包名
        String basePackage = "com.daimengshi.ddcms.admin.service";
        //依赖model的包名
        String modelPackage = "com.daimengshi.ddcms.admin.model";

        JbootModelGenerator.run(modelPackage);
        JbootServiceGenerator.run(basePackage, modelPackage);


    }
}
