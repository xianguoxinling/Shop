package com.service.pay;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.db.manager.OrderDBManager;
import com.model.order.Order;
import com.model.order.WxPayInfo;
import com.service.interfaces.OrderServiceInterface;
import com.service.order.OrderService;
import com.support.payment.weixin.util.WxpayUtil;
import com.until.errorcode.MAGICCODE;
import com.util.support.HttpUtil;
import com.util.support.XmlUtil;

public class WxPayService
{
//	private String spbill_create_ip = "NATIVE";
    private String spbill_create_ip = "122.4.241.3";
	private String prepay_id = null;
	private String open_id = null;
	private WxPayInfo wxPayInfo = null;
	private String magicKey = null;
	private Order order = null;
	private OrderDBManager dbManager = null;
	
	public WxPayService()
	{
	    dbManager = new OrderDBManager();
	}
	
	public WxPayService(String magicKey)
	{
	    this.magicKey = magicKey;
	    dbManager = new OrderDBManager();
	}
	
	public int queryWxPayInfo()
	{
	    int result = MAGICCODE.OK;
	    wxPayInfo = dbManager.queryWxPayInfo(magicKey);
	    if(null == wxPayInfo)
	    {
	        return MAGICCODE.DB_ERROR;
	    }
	    return result;
	}
	
	public int pay(String openID,String orderUUID,String customerIP)
	{

	    System.out.println("OPENID:"+openID);
	    System.out.println("ORDERUUID:"+orderUUID);
	    System.out.println("CUSTOMERIP:"+customerIP);
	    
	    int result = queryWxPayInfo();
	    if(MAGICCODE.OK != result)
	    {
	        
	    }
	    
	    OrderServiceInterface orderService = new OrderService();
	    Order order = orderService.queryOrderByUUID(orderUUID, magicKey);
//	    if(null == order)
//	    {
//	        return MAGICCODE.ERROR;
//	    }
	    open_id = openID;
	    spbill_create_ip = customerIP;
	    
        /**业务参数**/ 
        //商户系统内部的订单号,32个字符内、可包含字母
        //商户支付的订单号由商户自定义生成，
        //微信支付要求商户订单号保持唯一性。
        //重新发起一笔支付要使用原订单号，避免重复支付；
        //已支付过或已调用关单、撤销（请见后文的API列表）的订单号不能重新发起支付。
        String out_trade_no = order.getUuid();
        
//        String body = "烛照艺术-处理图片";//商家名称-销售商品类目
        String body = wxPayInfo.getBody();
        String total_fee = new Integer((int) (order.getTotalPrice()*100)).toString();
        
        System.out.println("TOTAL_FEE:"+total_fee);
        
        /**签名**/
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
        /**公共参数**/
        packageParams.put("appid", wxPayInfo.getAPP_ID());
        packageParams.put("mch_id", wxPayInfo.getMCH_ID());
        packageParams.put("nonce_str", WxpayUtil.fetchNonceStr());
        packageParams.put("sign_type", wxPayInfo.getSIGN_TYPE());
        packageParams.put("notify_url", wxPayInfo.getNOTIFY_URL());
        packageParams.put("trade_type", wxPayInfo.getTRADE_TYPE());
        
        packageParams.put("spbill_create_ip", spbill_create_ip);//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
        /**业务参数**/
        packageParams.put("body", body);
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("fee_type", wxPayInfo.getFEE_TYPE());
        packageParams.put("total_fee", total_fee);
        packageParams.put("openid", open_id);
        
        try{
        
	        //SortedMap 会自己排序
	        String sign = WxpayUtil.createSign("UTF-8", packageParams, wxPayInfo.getAPI_KEY());
	        packageParams.put("sign", sign);
	        
	        String requestXML = WxpayUtil.getRequestXml(packageParams);
	        System.out.println("BEFORE:"+requestXML);
	        
	        String responseXml = HttpUtil.postData(wxPayInfo.getUNIFIEDORDER_URL(), requestXML);
	        System.out.println("RESULT:"+responseXml);
	        
	        Map<String,String> responseMap = XmlUtil.doXMLParse(responseXml);
	        
	        if(WxpayUtil.isWxpayApiSuccess(responseMap)){
	        	prepay_id = responseMap.get("prepay_id");//微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
	        }
        }catch(Exception e){
        }
        
        return MAGICCODE.OK;
	}
    public int pay()
    {

        int result = queryWxPayInfo();
        if(MAGICCODE.OK != result)
        {
            
        }
        
        /**业务参数**/ 
        //商户系统内部的订单号,32个字符内、可包含字母
        //商户支付的订单号由商户自定义生成，
        //微信支付要求商户订单号保持唯一性。
        //重新发起一笔支付要使用原订单号，避免重复支付；
        //已支付过或已调用关单、撤销（请见后文的API列表）的订单号不能重新发起支付。
        String out_trade_no = order.getUuid();
        
//        String body = "烛照艺术-处理图片";//商家名称-销售商品类目
        String body = wxPayInfo.getBody();
        String total_fee = new Integer((int) (order.getTotalPrice()*100)).toString();
        
        /**签名**/
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
        /**公共参数**/
        packageParams.put("appid", wxPayInfo.getAPP_ID());
        packageParams.put("mch_id", wxPayInfo.getMCH_ID());
        packageParams.put("nonce_str", WxpayUtil.fetchNonceStr());
        packageParams.put("sign_type", wxPayInfo.getSIGN_TYPE());
        packageParams.put("notify_url", wxPayInfo.getNOTIFY_URL());
        packageParams.put("trade_type", wxPayInfo.getTRADE_TYPE());
        
        packageParams.put("spbill_create_ip", spbill_create_ip);//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
        /**业务参数**/
        packageParams.put("body", body);
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("fee_type", wxPayInfo.getFEE_TYPE());
        packageParams.put("total_fee", total_fee);
        packageParams.put("openid", open_id);
        
        try{
        
            //SortedMap 会自己排序
            String sign = WxpayUtil.createSign("UTF-8", packageParams, wxPayInfo.getAPI_KEY());
            packageParams.put("sign", sign);
            
            String requestXML = WxpayUtil.getRequestXml(packageParams);
            System.out.println("BEFORE:"+requestXML);
            
            String responseXml = HttpUtil.postData(wxPayInfo.getUNIFIEDORDER_URL(), requestXML);
            System.out.println("RESULT:"+responseXml);
            
            Map<String,String> responseMap = XmlUtil.doXMLParse(responseXml);
            
            if(WxpayUtil.isWxpayApiSuccess(responseMap)){
                prepay_id = responseMap.get("prepay_id");//微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
            }
        }catch(Exception e){
        }
        
        return MAGICCODE.OK;
    }   
	public String getSpbill_create_ip()
	{
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip)
	{
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getPrepay_id()
	{
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id)
	{
		this.prepay_id = prepay_id;
	}
	
	
	public String getOpen_id()
    {
        return open_id;
    }

    public void setOpen_id(String open_id)
    {
        this.open_id = open_id;
    }

    
    
    public WxPayInfo getWxPayInfo()
    {
        return wxPayInfo;
    }

    public void setWxPayInfo(WxPayInfo wxPayInfo)
    {
        this.wxPayInfo = wxPayInfo;
    }

    
    
    public String getMagicKey()
    {
        return magicKey;
    }

    public void setMagicKey(String magicKey)
    {
        this.magicKey = magicKey;
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    public static void main(String args[])
	{
	    WxPayService wxPay = new WxPayService();
	    wxPay.setMagicKey("eb07c48a400a4288a0ee8322250cff04");
	    wxPay.queryWxPayInfo();
	    WxPayInfo wxPayInfo = wxPay.getWxPayInfo();
	    System.out.println("APPID:"+wxPayInfo.getAPP_ID());
	    System.out.println("APPKEY:"+wxPayInfo.getAPI_KEY());
	    System.out.println("APPMCH:"+wxPayInfo.getMCH_ID());
	    Order order = new Order();
	    order.setId("1111");
	    order.setTotalPrice(0.01);
	    
	    wxPay.setOrder(order);
	    wxPay.setOpen_id("oN2Ad0cBpG-yZLjj7xfPNqx6mdPE");
//	    wxPay.pay();
	    wxPay.pay("oN2Ad0cBpG-yZLjj7xfPNqx6mdPE", "69140644d72942d1b000bb0468cf4c5c", "122.4.241.3");
	    String preID = wxPay.getPrepay_id();
//	    System.out.println(preID);
	}
	
}
