package com.fgw.project.service.msghttp.client;

import com.fgw.project.service.msghttp.client.Request.ContentType;

public interface RequestBuilder {

	RequestBuilder uri(String uri);

	RequestBuilder method(String post);

	RequestBuilder contentType(ContentType json);

	RequestBuilder connections(int defaultNum, int maxNum);

	RequestBuilder header(String key, String value);

	RequestBuilder timeout(int millisecond);

	Request build() throws Exception;

	RequestBuilder charset(String charset);

}
