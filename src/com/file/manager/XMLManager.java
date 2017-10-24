package com.file.manager;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLManager
{
	public Document read(String fileName) throws MalformedURLException, DocumentException
	{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(fileName));
		return document;
	}

	public Element getRootElement(Document doc)
	{
		return doc.getRootElement();
	}

	// http://www.blogjava.net/i369/articles/154264.html
	
	public static void main(String args[])
	{
		XMLManager xmlManager = new XMLManager();
		Document doc;
		try
		{
			doc = xmlManager.read("D://test00012.svg");
			Element root = xmlManager.getRootElement(doc);
			Iterator<Element> it = root.elementIterator();
			while (it.hasNext())
			{
				Element e = it.next();
//				if(e.get)
//				System.out.println(e.getName());
//				System.out.println("****");
				Iterator<Element> it2 = e.elementIterator();
				while(it2.hasNext())
				{
					Element e2 = it2.next();
//					e2.get
//					System.out.println(e2.getText());
//					System.out.println(e2.get);
//					e2.get
				}
				
			}
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
