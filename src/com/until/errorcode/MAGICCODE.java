package com.until.errorcode;

public class MAGICCODE
{
	// 平台错误码
	public static int PLATFORM_SUCCESS = 0;
	public static int PLATFORM_FAILED = 1;
	public static int PLATFORM_OK = 0;
	public static int OK = 0;
	public static int NOT_OK = 1;
	public static int ERROR = 1;
	public static int PLATFORM_ERROR = 1;
	public static int DB_ERROR = -1;
    public static int PLATFORM_EMAIL_NULL = 3;
    public static int PLATFORM_EMAIL_EXCETPION = 4;
    //user 40000-49999
    public static int USER_NEED_RELOGIN = 40001;
    public static int ADDRESS_NULL = 40002;
	//shop 50000-59999
	public static int SHOP_NAME_NOT_EXIST = 50000;
	public static int SHOP_NAME_EXIST = 50001;
	public static int SHOP_CATEGORY_NAME_EXIST = 50002;
	public static int SHOP_CATEGORY_NAME_NOT_EXIST = 50003;
	public static int SHOP_PRODUCTION_NOT_EXIST = 50004;
	public static int SHOP_PRODUCITON_NOT_ENOUGH = 50005;
	public static int SHOP_PRODUCTION_NULL = 50006;
	public static int SHOP_PRODUCTION_NUM_ERROR = 50007;
	public static int SHOP_ORDER_CANOT_BE_CANCLE = 50008;
	
	//平台外部错误吗 , 10000-19999
	public static String MAGIC_OK = "10000";
	public static String MAGIC_ERROR = "10001";
	public static String MAGIC_PARAMETER_ERROR = "10002";
	public static String MAGIC_NOT_LOGIN = "10003";
	public static String MAGIC_KEY_NULL = "10004";
	public static String MAGIC_PRODUCTION_NOT_FOUND = "10005";
	public static String MAGIC_ADDRESS_NULL = "10006";
	public static String MAGIC_COIN_NOT_ENOUGH = "30005";
	
	public static String MAGIC_VIP_NOT_FOUND = "60007";
	//shop 对外错误码
	public static String MAGIC_SHOP_CATAGORY_NAME_NOT_EXIST = "20001";
	public static String MAGIC_SHOP_CATAGORY_ID_NULL = "20002";
	public static String MAGIC_SHOP_PRODUCTION_NUM_ERROR = "20003";
	public static String MAGIC_SHOP_PRODUCITON_NOT_ENOUGH = "20004";
	
}