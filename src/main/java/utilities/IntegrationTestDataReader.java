package utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import models.TestData;

public class IntegrationTestDataReader {

	public static TestData getIntegrationData() {
		TestData td = new TestData();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			String rootPath = System.getProperty("user.dir");
			File fXmlFile = new File(rootPath + "/test-data.xml");
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList integrationNodes = doc.getElementsByTagName("integrationtest");
			List<String> userList = new ArrayList<String>();

			System.out.println("----------------------------");

			for (int temp = 0; temp < integrationNodes.getLength(); temp++) {

				Node nNode = integrationNodes.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("Staff id : " + eElement.getAttribute("username"));
					userList.add(eElement.getAttribute("username"));

				}

			}
			td.setUsernameList(userList);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return td;
	}

}
