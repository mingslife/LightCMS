package com.mingslife.web.interceptor;

import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class AuditingInterceptor implements Interceptor {
	@Override
	public Object intercept(Invocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object plugin(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		
	}
}
