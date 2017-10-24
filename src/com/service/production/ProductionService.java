package com.service.production;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.db.manager.ProductionDBManager;
import com.model.production.Production;
import com.service.interfaces.ProductionServiceInterface;
import com.until.errorcode.MAGICCODE;
import com.until.queryterm.QueryTerm;

public class ProductionService implements ProductionServiceInterface
{

    private ProductionDBManager dbManager = null;

    public ProductionService()
    {
        dbManager = new ProductionDBManager();
    }

    @Override
    public int createProduction(Production production, String userID)
    {
        int result = MAGICCODE.OK;
        // check auth

        result = dbManager.createProduction(production);
        if (MAGICCODE.OK != result)
        {

        }
        return result;
    }

    public Production queryProduction(String id, String magicKey)
    {
        Production production = dbManager.queryProductionByID(id, magicKey);
        if (null == production)
        {

        }
        return production;
    }

    @Override
    public Production queryProductionByID(String id, String magicKey)
    {
        Production production = dbManager.queryProductionByID(id, magicKey);
        if (null != production)
        {
            int result = dbManager.queryProductionPic(production);
            if (MAGICCODE.OK != result)
            {

            }
        }
        return production;
    }

    @Override
    public List<Production> queryProductionByCategoryName(String categoryName, String magicKey, QueryTerm queryTerm)
    {
        List<Production> productionList = new ArrayList<Production>();
        int result = dbManager.queryProductionByCategoryName(categoryName, productionList, queryTerm, magicKey);
        if (MAGICCODE.OK != result)
        {

        }
        return productionList;
    }

    public List<Production> queryAllProduction(String magicKey,QueryTerm queryTerm)
    {
        List<Production> productionList = new ArrayList<Production>();
        int result = dbManager.queryAllProduction(productionList, queryTerm, magicKey);
        if (MAGICCODE.OK != result)
        {

        }
        return productionList;
    }
    
    public List<Production> queryAllShopProduction(String magicKey)
    {
        List<Production> productionList = new ArrayList<Production>();
        int result = dbManager.queryShopAllProduction(productionList, magicKey);
        if (MAGICCODE.OK != result)
        {

        }
        return productionList;
    }
    
    @Override
    public int createProductionPic(String uuid, List<String> picList, String userID, String magicKey)
    {
        int result = MAGICCODE.OK;
        // check Auth
        if (null == picList)
        {
            return MAGICCODE.OK;
        }
        // Collections.sort(picList);
        // Collections.reverse(picList);
        Collections.sort(picList, new Comparator<Object>()
        {
            @Override
            public int compare(Object o1, Object o2)
            {
                return ((String) o2).compareTo((String) o1);
            }
        });
        String mainPic = picList.get(0);
        result = dbManager.createProductionMainPic(uuid, mainPic, magicKey);
        if (MAGICCODE.OK != result)
        {

        }
        result = dbManager.createProductionPic(uuid, picList, magicKey);
        if (MAGICCODE.OK != result)
        {

        }
        return result;
    }

    @Override
    public int checkProductionExist(String productionID, String magicKey)
    {
        Production production = queryProduction(productionID, magicKey);
        if (null == production)
        {
            return MAGICCODE.SHOP_PRODUCTION_NOT_EXIST;
        }
        return MAGICCODE.OK;
    }

    @Override
    public long queryProductionNum(String id, String magicKey)
    {
        long num = 0;
        num = dbManager.queryProductionNum(id, magicKey);
        if (MAGICCODE.DB_ERROR == num)
        {

        }
        return num;
    }

}
