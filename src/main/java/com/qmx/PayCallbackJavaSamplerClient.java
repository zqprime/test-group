package com.qmx;

import bsh.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * @author zqprime
 */
public class PayCallbackJavaSamplerClient extends AbstractJavaSamplerClient {

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        final SampleResult sr = new SampleResult();
        final String x = javaSamplerContext.getParameter("x");
        final String y = javaSamplerContext.getParameter("y");
        sr.setSamplerData("请求参数x为："+x);
        sr.setSamplerData("请求参数y为："+y);
        try{
            sr.sampleStart();
            if(StringUtils.isNotBlank(x)){
                //do sth start
                sr.setResponseData("x:"+x+"success, y:"+y+"success","utf-8");
                //do sth end
                sr.setSuccessful(true);
            }else {
                sr.setResponseData("操作失败 x:"+x+"success, y:"+y+"success","utf-8");
                //do sth end
                sr.setSuccessful(false);
            }

        }catch (Exception e) {
            //有异常,执行失败
            sr.setSuccessful(false);
            e.printStackTrace();
        }finally {
            sr.sampleEnd();
        }
        return sr;
    }

    @Override
    public void setupTest(JavaSamplerContext context) {
        //runtest 前 执行一遍，可以对参数进行处理
        super.setupTest(context);
    }

    @Override
    public Arguments getDefaultParameters() {
        final Arguments arguments = new Arguments();
        arguments.addArgument("x","");
        arguments.addArgument("y","world");
        return arguments;
    }


}
