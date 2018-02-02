package com.daimengshi.ddcms.home.controller;

import com.daimengshi.ddcms.admin.support.auth.AuthUtils;
import com.daimengshi.ddcms.common.Consts;
import com.daimengshi.ddcms.service.api.DmsArticleService;
import com.daimengshi.ddcms.service.api.DmsUserService;
import com.daimengshi.ddcms.service.entity.model.DmsArticle;
import com.daimengshi.ddcms.service.entity.model.DmsUser;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.jfinal.upload.UploadFile;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.shiro.SecurityUtils;

import java.util.List;

/**
 * Created by zhoufeng on 2017/12/21.
 * 用户中心
 * 正在实现请关注ddcms
 */
@RequestMapping("/user")
public class HomeUserController extends JbootController {
    private static final Log log = LogFactory.get();
    private DmsArticleService articleService;
    private DmsUserService userService;

    public HomeUserController() {
        this.articleService = ServiceUtils.getService(DmsArticleService.class);
        this.userService = ServiceUtils.getService(DmsUserService.class);
    }

    /**
     * 用户中心
     */
    public void index() {
        DmsUser user = AuthUtils.getLoginUser();
        List<DmsArticle> myArticleList = articleService.findByUId(user.getId());

        setAttr("user", user);
        setAttr("myArticleList", myArticleList);
        setAttr("myArticleListSize", myArticleList.size());
        setAttr("isOpenTopNav2", "off"); //关闭二级导航,用于手机适配
        setAttr("mainTP", "/htmls/home/layui/user/index.html"); //中间内容模板
        render("/htmls/home/layui/lr_global.html");
    }


    /**
     * 我的基本信息设置
     */
    public void config() {
        setAttr("isOpenTopNav2", "off"); //关闭二级导航,用于手机适配
        setAttr("mainTP", "/htmls/home/layui/user/config.html"); //中间内容模板
        render("/htmls/home/layui/lr_global.html");
    }

    /**
     * 我的消息页面
     */
    public void message() {

        setAttr("isOpenTopNav2", "off"); //关闭二级导航,用于手机适配
        setAttr("mainTP", "/htmls/home/layui/user/message.html"); //中间内容模板
        render("/htmls/home/layui/lr_global.html");
    }

    /**
     * 我的主页
     */
    public void home() {
        setAttr("isOpenTopNav2", "off"); //关闭二级导航,用于手机适配
        setAttr("mainTP", "/htmls/home/layui/user/home.html"); //中间内容模板
        render("/htmls/home/layui/lr_global.html");
    }


    /**
     * 上传头像
     */
    public void postUpFileByAvatar() {

        UploadFile uploadFile = getFile();
        if (!"image/jpeg".equals(uploadFile.getContentType())) {
            renderJson(ResponseData.apiError("只能是图片类型"));
        }
        String rootPath = "http://" + getRequest().getServerName() + ":" + getRequest().getServerPort();
        String url = "/upload/" + uploadFile.getFileName();

        DmsUser user = AuthUtils.getLoginUser();
        user.setAvatar(url);
        userService.update(user);
        SecurityUtils.getSubject().getSession().setAttribute(Consts.SESSION_USER, user);//更修会话


        renderJson(ResponseData.ok().putDataValue("fileNameUrl", rootPath + url));

    }

}
