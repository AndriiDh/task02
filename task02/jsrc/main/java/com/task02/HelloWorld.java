package com.task02;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.LambdaUrlConfig;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.lambda.url.AuthType;
import com.syndicate.deployment.model.lambda.url.InvokeMode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@LambdaHandler(lambdaName = "hello_world",
	roleName = "hello_world-role",
	isPublishVersion = true
)
@LambdaUrlConfig(authType = AuthType.NONE,
		invokeMode = InvokeMode.BUFFERED
)
public class HelloWorld implements RequestHandler<Object, Map<String, Object>> {

	public Map<String, Object> handleRequest(Object request, Context context) {
		System.out.println("Hello from lambda");
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("statusCode", 200);
		resultMap.put("body", new Response(200, "Hello from Lambda"));
		return resultMap;
	}
}

class Response implements Serializable {
	public int statusCode;
	public String message;
	public Response(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
}
