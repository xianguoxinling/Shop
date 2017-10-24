package com.service.sale;

import com.db.manager.SaleDBManager;
import com.model.order.SaledHistory;
import com.service.interfaces.SaleServiceInterface;
import com.until.errorcode.MAGICCODE;

public class SaleService implements SaleServiceInterface
{

    private SaleDBManager dbManager = null;
    
    public SaleService()
    {
        dbManager = new SaleDBManager();
    }
    
    @Override
    public int createSaleHistory(SaledHistory saleHistory)
    {
        int result = dbManager.createSaleHistory(saleHistory);
        if(MAGICCODE.OK != result)
        {
            return result;
        }
        return result;
    }

}
