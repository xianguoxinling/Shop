package com.control.sale;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.order.SaledHistory;
import com.service.interfaces.SaleServiceInterface;
import com.service.sale.SaleService;
import com.until.errorcode.MAGICCODE;
import com.until.num.UntilNum;

public class CreateSaleController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {

        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        OutputStream stream = response.getOutputStream();
        String keyID = request.getParameter("keyID");
        if (null == keyID)
        {
            map.put("code", MAGICCODE.MAGIC_KEY_NULL);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }

        String token = request.getParameter("token");
        if (null == token)
        {
            map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }

        //鉴权
        
        String productionName = request.getParameter("production_name");
        String productionID = request.getParameter("production_id");
        String productionIntroduction = request.getParameter("production_introduction");
        String producitonPic = request.getParameter("production_pic");
        String customerID = request.getParameter("customer_id");
        String priceStr = request.getParameter("production_price");
        String numStr = request.getParameter("production_num");
        if ((!UntilNum.isNumber(priceStr)) || (!UntilNum.isNumber(numStr)))
        {
            map.put("code", MAGICCODE.MAGIC_PARAMETER_ERROR);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }

        long num = Long.parseLong(numStr);
        double price = Double.parseDouble(priceStr);
        SaledHistory saledHistory = new SaledHistory();
        saledHistory.setMagicKey(keyID);
        saledHistory.setNum(num);
        saledHistory.setProductionBriftIntroduce(productionIntroduction);
        saledHistory.setProductionID(productionID);
        saledHistory.setProductionName(productionName);
        saledHistory.setProductionPic(producitonPic);
        saledHistory.setRealPrice(price);
        saledHistory.setUserID(customerID);
        
        int result = MAGICCODE.OK;
        SaleServiceInterface saleService = new SaleService();
        result = saleService.createSaleHistory(saledHistory);
        if (MAGICCODE.OK == result)
        {
            map.put("code", MAGICCODE.MAGIC_OK);
        } else
        {
            map.put("code", MAGICCODE.MAGIC_ERROR);
        }

        json = JSONObject.fromObject(map);
        stream.write(json.toString().getBytes("UTF-8"));
        return null;
    }
}
