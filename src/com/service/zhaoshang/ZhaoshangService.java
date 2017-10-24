package com.service.zhaoshang;

import com.db.manager.ZhaoshangDBManager;
import com.model.zhaoshang.Zhaoshang;

public class ZhaoshangService
{
    private ZhaoshangDBManager dbManager = null;
    public ZhaoshangService()
    {
        dbManager = new ZhaoshangDBManager();
    }
    public int create(Zhaoshang zs)
    {
        return dbManager.add(zs);
    }
}
