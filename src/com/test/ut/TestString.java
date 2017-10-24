package com.test.ut;

public class TestString
{

    public static void main(String[] args)
    {
        String mainPic = "/srv/www/htdocs/shop/images/ccc65483/02b8e7a5-8f07-45ff-b3b5-26819a388ecd/QQ图片20170621164401.png ";
        String[] picFile = mainPic.split("/");
        StringBuffer newPic = new StringBuffer();
//        newPic.append("/");
        for(int i =0;i<(picFile.length -1);i++)
        {
            newPic.append(picFile[i]);
            newPic.append("/");
//            System.out.println(picFile[i]);
        }
        newPic.append("midcompress/");
        newPic.append(picFile[picFile.length -1]);
        
        System.out.println(newPic.toString());
    }

}
