package com.daimengshi.ddcms.utils;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.web.base.BaseController;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.xiaoleilu.hutool.util.NumberUtil;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * Created by zhoufeng on 2018/1/23.
 * 校验工具
 * 用于json校验
 */
public class JsonVerifyUtils {

    public static boolean verifyStringIsEmpty(BaseController baseController, String json, String key, String errMsg) {
        if (StrUtil.isEmpty(JSON.parseObject(json).getString(key))) {
            baseController.renderJson(ResponseData.apiError(errMsg));
            return true;
        }
        return false;

    }
    public static boolean verifyStringIsNotNunber(BaseController baseController, String json, String key, String errMsg) {
        if (!NumberUtil.isNumber(JSON.parseObject(json).getString(key))) {
            baseController.renderJson(ResponseData.apiError(errMsg));
            return true;
        }
        return false;

    }

}
