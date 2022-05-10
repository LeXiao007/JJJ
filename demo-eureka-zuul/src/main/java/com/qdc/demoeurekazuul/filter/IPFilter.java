package com.qdc.demoeurekazuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import jdk.nashorn.internal.ir.WhileNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@Component
public class IPFilter extends ZuulFilter {
    private String[] whitelist;
    @Value("${yxwfilter.ip.whitelist}")
    private String strIPWhitelist;
    @Value("${yxwfilter.ip.whitelistenabled}")
    private String whitelistEnabled;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        if("true".equalsIgnoreCase(whitelistEnabled))
            return true;
        else {
            return false;
        }
    }

    
    public Object run() throws ZuulException {
        System.out.println(strIPWhitelist);
        whitelist=strIPWhitelist.split("\\,");
        RequestContext ctx=RequestContext.getCurrentContext();
        HttpServletRequest req=ctx.getRequest();
        String ipAdder=this.getIpadder(req);
        System.out.println("请求IP地址为：[" + ipAdder + "]");//配置本地IP白名单，生产环境
        List<String> ips=new ArrayList<>();
        for(int i=0;i< whitelist.length;++i){
            System.out.println(whitelist[i]);//输出a,b,c
            ips.add(whitelist[i]);
        }
        System.out.println("whitelist:" + ips.toString());//配置本地ip白名单
        if(!ips.contains(ipAdder)){
            System.out.println("未通过IP地址校验.[" + ipAdder + "]");
            ctx.setResponseStatusCode(401);
            ctx.setSendZuulResponse(false);
            ctx.getResponse().setContentType("application/json;charset=UTF-8");
            ctx.setResponseBody("{\"errocode\":\"00001\",\"errmsg\":\"IpAddr is forbidden!["+ipAdder+"]\"}");
        }
        return null;
    }

    private String getIpadder(HttpServletRequest request) {
        String ip=request.getHeader("X-Forwarded-For");
        if(ip==null||ip.length()==0||"unknow".equalsIgnoreCase(ip)){
            ip=request.getHeader("Proxu-Client-IP");
        }
        if(ip==null||ip.length()==0||"unknow".equalsIgnoreCase(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null||ip.length()==0||"unknow".equalsIgnoreCase(ip)){
            ip=request.getHeader("HTTP_CLIENT_IP");
        }
        if(ip==null||ip.length()==0||"unknow".equalsIgnoreCase(ip)){
            ip=request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if(ip==null||ip.length()==0||"unknow".equalsIgnoreCase(ip)){
            ip=request.getRemoteAddr();
        }
        return ip;
    }

}
