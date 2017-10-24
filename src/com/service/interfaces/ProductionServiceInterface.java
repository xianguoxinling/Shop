package com.service.interfaces;

import java.util.List;

import com.model.production.Production;
import com.until.queryterm.QueryTerm;

public interface ProductionServiceInterface
{
    public int createProduction(Production production,String userID);
    public int createProductionPic(String uuid,List<String> picList,String userID,String magicKey);
    public List<Production> queryAllProduction(String magicKey,QueryTerm queryTerm);
    public List<Production> queryAllShopProduction(String magicKey);
    public Production queryProductionByID(String id,String magicKey);
    public List<Production> queryProductionByCategoryName(String categoryName,String magicKey,QueryTerm queryTerm);
    public int checkProductionExist(String productionID,String magicKey);
    public long queryProductionNum(String id,String magicKey);
}
