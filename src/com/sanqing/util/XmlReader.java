package com.sanqing.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sanqing.test.test;


/**
 * 
 * @author Damon
 *
 */
public class XmlReader {
	//≈‰÷√Œƒº˛√˚
	private static String fileName = "conf.xml";
	
	public static int getEveryPage() {
		int everyPage = 5;	//default 5
		try {
			String path = XmlReader.class.getResource("/").getPath();
	        path = path.replace("/build/classes", "").replace("%20"," ").replace("classes/", "").replaceFirst("/", "");
			File f = new File(path+fileName);
			if(!f.exists()) {
				System.out.println("Error: "+path+fileName+"doesn't not exist!");
				return everyPage;
			}
			DocumentBuilderFactory domFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder domBuilder = domFac.newDocumentBuilder();
			InputStream is = new FileInputStream(f);
			Document doc = domBuilder.parse(is);
			Element root = doc.getDocumentElement();
			NodeList dbInfo = root.getChildNodes();
			
			if(dbInfo != null) {
				for (int i = 0; i < dbInfo.getLength(); i++) {
					Node db = dbInfo.item(i);
					for(Node node = db.getFirstChild(); node != null; node = node.getNextSibling()) {
						if(node.getNodeType() == Node.ELEMENT_NODE) {
							if(node.getNodeName().equals("EVERYPAGE")) {
								everyPage = Integer.parseInt(node.getFirstChild().getNodeValue());
								return everyPage;
							}
						}
					}
				}
			}
		} catch(Exception e) {
			System.out.println("Error:" + e.toString());
		}
		return everyPage;
	}
}
