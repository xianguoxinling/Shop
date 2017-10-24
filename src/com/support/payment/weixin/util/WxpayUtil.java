package com.support.payment.weixin.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.until.string.StringClass;
import com.util.support.MD5Util;

public class WxpayUtil {
	
	static Logger logger = LogManager.getLogger();

	/**
	 * 生成nonce_str 
	 * 随机字符串，不长于32位，主要保证签名sign不可预测
	 * 
	 * @return 当前时间的时分秒+随机4位整数
	 */
	public static String fetchNonceStr(){
		
		String currTime = getCurrTime();  
        String strTime = currTime.substring(8, currTime.length());  
        String strRandom = buildRandom(4) + "";  
        String nonce_str = strTime + strRandom;
        
        return nonce_str;
	}
	
	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * @return
	 */
	public static String getCurrTime() {  
        Date now = new Date();  
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
        String s = outFormat.format(now);  
        return s;  
    }
	
	/**
	 * 取出一个指定长度大小的随机正整数.
	 * @param length 设定所取出随机数的长度。length小于11
	 * @return
	 */
	public static int buildRandom(int length) {  
        int num = 1;  
        double random = Math.random();  
        if (random < 0.1) {  
            random = random + 0.1;  
        }  
        for (int i = 0; i < length; i++) {  
            num = num * 10;  
        }  
        return (int) ((random * num));  
    }
	
	/**
	 * 生成微信签名sign
	 * @param characterEncoding
	 * @param packageParams
	 * @param SECRET_KEY
	 * @return
	 */
	public static String createSign(String characterEncoding, SortedMap<Object, Object> packageParams, String SECRET_KEY) {  
        StringBuffer sb = new StringBuffer();  
        Set es = packageParams.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();  
            String v = (String) entry.getValue();  
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        sb.append("key=" + SECRET_KEY);  
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();  
        return sign;  
    }
	
	/**
	 * 将请求参数转换为xml格式的string
	 * @param parameters
	 * @return
	 */
	public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }
	
	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
	 * 重要：http报文头可能会被伪造ip
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public final static String getIpAddress(HttpServletRequest request) throws IOException {  
  
        String ip = request.getHeader("X-Forwarded-For");  
        if (logger.isInfoEnabled()) {  
            logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);  
        }  
  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("Proxy-Client-IP");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("WL-Proxy-Client-IP");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_CLIENT_IP");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getRemoteAddr();  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);  
                }  
            }  
        } else if (ip.length() > 15) {  
            String[] ips = ip.split(",");  
            for (int index = 0; index < ips.length; index++) {  
                String strIp = (String) ips[index];  
                if (!("unknown".equalsIgnoreCase(strIp))) {  
                    ip = strIp;  
                    break;  
                }  
            }  
        }  
        return ip;  
    }
	
	/**
	 * 微信支付接口调用是否成功
	 * @param responseMap
	 * @return
	 */
	public static Boolean isWxpayApiSuccess(Map<String,String> responseMap){
		
		if(responseMap!=null){
			
        	String return_code = responseMap.get("return_code");//返回状态码
        	
        	if("SUCCESS".equals(return_code)){
        		
        		String result_code = responseMap.get("result_code");//业务结果
        		
        		if("SUCCESS".equals(result_code)){
        			
        			return true;
        		}
        	}
        }
		return false;
	}
	
	/**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @return 生成的签名结果
     */
	private static boolean getSignVeryfy(SortedMap<Object,Object> params, String sign,String apiKey) {

        //获取待签名字符串
//        String mysign = WxpayUtil.createSign("UTF-8", params, WxpayConfig.API_KEY);
        String mysign = WxpayUtil.createSign("UTF-8", params, apiKey);
        //获得签名验证结果
        boolean isSign = false;
        if(mysign.equals(sign)) {
        	isSign = true;
        }
        return isSign;
    }
	
	/**
     * 验证签名
     * @param params 通知返回来的参数数组
     * @return 验证结果
     */
    public static boolean verify(SortedMap<Object,Object> params,String apiKey) {
    	
    	if(params!=null){
			
        	String return_code = StringClass.getString(params.get("return_code"));//返回状态码
        	String result_code = StringClass.getString(params.get("result_code"));//业务结果
        	
        	if("SUCCESS".equals(return_code)
        			&& "SUCCESS".equals(result_code)){
        		
        		String sign = StringClass.getString(params.get("sign"));//签名
        		
        		boolean isSign = getSignVeryfy(params, sign,apiKey);

        		if(isSign){
        			return true;
        		}
        	}
        }
		return false;
    }
	
	
	public static void main(String[] args) {
		
		SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
		packageParams.put("a", 1);
		packageParams.put("c", 1);
		packageParams.put("b", 1);
		
		System.out.println(packageParams);
	}
}
