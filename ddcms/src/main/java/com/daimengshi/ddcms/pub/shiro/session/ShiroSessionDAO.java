package com.daimengshi.ddcms.pub.shiro.session;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.model.DmsSession;
import com.daimengshi.ddcms.admin.service.impl.DmsSessionServiceImpl;
import com.jfinal.aop.Duang;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;

/**
 * Created by zhoufeng on 2017/12/15.
 * 自定义时间会话持久化
 */
public class ShiroSessionDAO extends CachingSessionDAO {

    private DmsSessionServiceImpl userService = Duang.duang(DmsSessionServiceImpl.class);


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        DmsSession dmsSession = new DmsSession();
        dmsSession.setId(sessionId.toString());
        dmsSession.setSession(JSON.toJSONString(session));
        userService.save(dmsSession);
        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        DmsSession dmsSession = userService.findById(sessionId.toString());
        if (dmsSession == null) return null;
        JSON.parseObject(dmsSession.getSession(), Session.class);
        return JSON.parseObject(dmsSession.getSession(), Session.class);
    }

    @Override
    protected void doUpdate(Session session) {
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }

        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        DmsSession dmsSession = new DmsSession();
        dmsSession.setId(sessionId.toString());
        dmsSession.setSession(JSON.toJSONString(session));
        userService.update(dmsSession);

    }

    @Override
    protected void doDelete(Session session) {
        userService.deleteById(session.getId());
    }
}
