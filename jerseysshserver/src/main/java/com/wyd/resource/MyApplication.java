package com.wyd.resource;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

//@ApplicationPath("/")
public class MyApplication extends ResourceConfig{
	 public MyApplication() {
		 // 需要注入spring组件解析过滤器
	     register(RequestContextFilter.class);
		 register(MultiPartFeature.class);
	     register(FileResource.class);
	     register(RecordResource.class);
	    }
}
