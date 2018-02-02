package com.daimengshi.ddcms.admin.support.log;

import com.daimengshi.ddcms.admin.support.auth.AuthUtils;
import com.daimengshi.ddcms.service.api.DmsLogService;
import com.daimengshi.ddcms.service.entity.model.DmsLog;
import com.daimengshi.ddcms.service.entity.model.DmsUser;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.xiaoleilu.hutool.date.DateUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import io.jboot.web.controller.JbootController;

/**
 * 系统日志拦截器
 * @author Rlax
 *
 */
public class LogInterceptor implements Interceptor {

	private final static com.jfinal.log.Log logger = com.jfinal.log.Log.getLog(LogInterceptor.class);

	@Override
	public void intercept(Invocation inv) {
		if (inv.getController() instanceof JbootController) {
			JbootController c = (JbootController) inv.getController();

			DmsUser user = AuthUtils.getLoginUser();
			UserAgent userAgent = UserAgent.parseUserAgentString(c.getRequest().getHeader("User-Agent"));
			Browser browser = userAgent.getBrowser();

			DmsLog log = new DmsLog();
			log.setUid(user.getId());
			log.setBrowser(browser.getName());
			log.setOperation(c.getRequest().getMethod());
			log.setFrom(c.getReferer());
			log.setIp(c.getIPAddress());
			log.setUrl(c.getRequest().getRequestURI());
			log.setCreateTime(DateUtil.date());
			log.setLastUpdataAcct(user.getId() == null ? "guest" : user.getName());
			log.setLastUpdataTime(DateUtil.date());

			try {
				DmsLogService logService = ServiceUtils.getService(DmsLogService.class);
				logService.save(log);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			} finally {
				inv.invoke();
			}
		} else {
			inv.invoke();
		}
	}

}
