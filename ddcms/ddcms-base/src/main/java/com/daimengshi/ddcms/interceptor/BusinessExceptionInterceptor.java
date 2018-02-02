package com.daimengshi.ddcms.interceptor;

import com.daimengshi.ddcms.common.RestResult;
import com.daimengshi.ddcms.web.base.BaseController;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import io.jboot.exception.JbootException;

/**
 * 业务异常拦截器
 * @author Rlax
 *
 */
public class BusinessExceptionInterceptor implements Interceptor {

	/** 异常内容在模版引擎中的属性TAG */
	public final static String MESSAGE_TAG = "message";

	/** 异常页面 */
	private String exceptionView = "/exception.html";

	public BusinessExceptionInterceptor(String exceptionView) {
		this.exceptionView = exceptionView;
	}

	@Override
	public void intercept(Invocation inv) {
		try {
			inv.invoke();
		} catch (JbootException e) {
			if (inv.getTarget() instanceof BaseController) {
				BaseController controller = inv.getTarget();

				if (controller.isAjaxRequest()) {
					RestResult<String> restResult = new RestResult<String>();
					restResult.error(e.getMessage());
					controller.renderJson(restResult);
				} else {
					controller.setAttr(MESSAGE_TAG, e.getMessage()).render(exceptionView);
				}
			}
		}
	}

}
