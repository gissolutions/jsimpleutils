package org.gissolutions.jsimpleutils.aims;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ServiceAdapter {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ServiceAdapter.class);
	private final IAIMSConnector connector;

	public ServiceAdapter(IAIMSConnector connector) {
		super();
		this.connector = connector;
	}

	public List<String> getServicesNames() throws AIMSConnectionException {
		List<String> services = new ArrayList<String>();
		try {
			String axl = this.connector.getClientServices();
			services = ServiceAdapter.getServicesNames(axl);
		} catch (AIMSConnectionException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
			throw e;
		}
		return services;

	}

	public static void main(String[] args) {
		if(args.length ==0) {
			throw new IllegalArgumentException("Server name must be summitted");
		}
		ServiceAdapter sa = new ServiceAdapter( new HTTPConnector(args[0]));
		List<String> names;
		try {
			names = sa.getServicesNames();
			System.out.println("Server: " +  args[0]);
			for (String name : names) {
				System.out.println("Service: " + name);
			}
			System.out.println("Total Services: " + names.size());
		} catch (AIMSConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static List<String> getServicesNames(String axl) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		List<String> services = new ArrayList<String>();
		try {
			db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(axl));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("SERVICE");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				String name = element.getAttribute("name");
				services.add(name);
			}
		} catch (ParserConfigurationException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (SAXException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (IOException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
		return services;

	}
}
