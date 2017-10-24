package com.control.order;

import java.io.InputStream;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.until.errorcode.MAGICCODE;
import com.until.string.StringClass;
import com.model.order.Order;
import com.model.order.OrderState;
import com.model.order.WxPayInfo;
import com.service.interfaces.OrderServiceInterface;
import com.service.order.OrderService;
import com.service.pay.WxPayService;
import com.support.payment.weixin.util.WxpayUtil;

public class NotifyOrderController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {

        String resXml = "";

        // 解析结果存储在HashMap
        SortedMap<Object, Object> map = new TreeMap<Object, Object>();
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;

        String return_code = StringClass.getString(map.get("return_code"));// 返回状态码
        String result_code = StringClass.getString(map.get("result_code"));// 业务结果
        String return_msg = "FAIL";
        if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code))
        {
            String appid = StringClass.getString(map.get("appid"));
            String mch_id = StringClass.getString(map.get("mch_id"));
            String sign_type = StringClass.getString(map.get("sign_type"));
            String trade_type = StringClass.getString(map.get("trade_type"));
            String fee_type = StringClass.getString(map.get("fee_type"));
            String out_trade_no = StringClass.getString(map.get("out_trade_no"));
            String transaction_id = StringClass.getString(map.get("transaction_id"));
            String sign = StringClass.getString(map.get("sign"));// 签名
            Double total_fee = 0d;
            try
            {
                total_fee = Double.parseDouble(StringClass.getString(map.get("total_fee")));// 订单总金额，单位为分
                total_fee = total_fee / 100;// 转为元

            } catch (NumberFormatException e)
            {
                return_code = "FAIL";
                resXml = getReturnMeg(resXml,return_code,return_msg);
                response.getWriter().print(resXml);
                return null;
            }

            OrderServiceInterface orderService = new OrderService();
            Order order = orderService.queryOrderInfoByUUID(out_trade_no);
            if (null == order || !OrderState.PR_PAY.equals(order.getState()))
            {
                return_code = "FAIL";
                resXml = getReturnMeg(resXml,return_code,return_msg);
                response.getWriter().print(resXml);
//                System.out.println("OrderInfo error!");
                return null;
            }

            String magicKey = order.getMagicKey();
            WxPayService wxService = new WxPayService(magicKey);
            int result = wxService.queryWxPayInfo();
            if (MAGICCODE.OK != result)
            {
                return_code = "FAIL";
                resXml = getReturnMeg(resXml,return_code,return_msg);
                response.getWriter().print(resXml);
//                System.out.println("DB error!");
                return null;
            }
            WxPayInfo wxPayInfo = wxService.getWxPayInfo();
            String mysign = WxpayUtil.createSign("UTF-8", map, wxPayInfo.getAPI_KEY());
            if (!mysign.equals(sign))
            {
                return_code = "FAIL";
                resXml = getReturnMeg(resXml,return_code,return_msg);
                response.getWriter().print(resXml);
//                System.out.println("SIGN error!");
                return null;
            }
            if (wxPayInfo.getAPP_ID().equals(appid) && wxPayInfo.getMCH_ID().equals(mch_id) && ("".equals(sign_type) || wxPayInfo.getSIGN_TYPE().equals(sign_type))
                    && wxPayInfo.getTRADE_TYPE().equals(trade_type) && ("".equals(fee_type) || wxPayInfo.getFEE_TYPE().equals(fee_type)))
            {
                //因为四舍五入允许有0.1元的误差
                if(Math.abs(total_fee-order.getTotalPrice())<0.1)
                {
                     return_code = "SUCCESS";
                     result = orderService.payOrder(order.getId(), order.getCustomer(), order.getMagicKey());
                     if(MAGICCODE.OK != result)
                     {
                         return_code = "FAIL";
                         resXml = getReturnMeg(resXml,return_code,return_msg);
                         response.getWriter().print(resXml);
//                         System.out.println("ORDER DB error!");
                         return null;
                     }
                     return_code = "OK";
                     resXml = getReturnMeg(resXml,return_code,return_msg);
                     response.getWriter().print(resXml);
                     return null;
                }else
                {
                    return_code = "FAIL";
                    resXml = getReturnMeg(resXml,return_code,return_msg);
                    response.getWriter().print(resXml);
//                    System.out.println("PRICE error!");
                    return null;
                }
            }else
            {
                return_code = "FAIL";
                resXml = getReturnMeg(resXml,return_code,return_msg);
                response.getWriter().print(resXml);
//                System.out.println("INFO error!");
                return null;
            }

        } else
        {
            return_code = "FAIL";
            resXml = getReturnMeg(resXml,return_code,return_msg);
            response.getWriter().print(resXml);
            System.out.println("RETURN error!");
            return null;
        }

    }
    
    public String getReturnMeg(String resXml,String return_code,String return_msg)
    {
         resXml = "<xml>" +
         "<return_code><![CDATA["+return_code+"]]></return_code>" +
         "<return_msg><![CDATA["+return_msg+"]]></return_msg>" +
         "</xml> ";
         return resXml;
    }
}
