package org.gissolutions.jsimpleutils.aims;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class HTTPConnector implements IAIMSConnector {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HTTPConnector.class);
	private final String host;
	private final int	port = 80;
	private final String encoding;
	
	public HTTPConnector(String host) {
		super();
		this.host = host;
		//this.port=port;
		encoding = "UTF-8";
	}
	
	@Override
	public String getClientServices() {
		String serviceName = "catalog";
		String axl = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <GETCLIENTSERVICES/>";
		return sendRequest(serviceName, axl);
	}

	@Override
	public String getHost() {		
		return host;
	}

	@Override
	public int getPort() {
		
		return port;
	}

	@Override
	public String sendRequest(String serviceName, String axl) {
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("ServiceName", serviceName));
		qparams.add(new BasicNameValuePair("ClientVersion", "4.0"));
		qparams.add(new BasicNameValuePair("Form", "True"));
		qparams.add(new BasicNameValuePair("Encode", "False"));

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("ArcXMLRequest",axl));

		URI uri;
		HttpClient httpclient = new DefaultHttpClient();
		
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, encoding);
			uri = buildURI(qparams);
			//HttpGet httpget = new HttpGet(uri);
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setEntity(entity);
			//logger.debug(httpPost.getURI());
			
			 // Create a response handler
	        ResponseHandler<String> responseHandler = new BasicResponseHandler();
	        String responseBody = httpclient.execute(httpPost, responseHandler);
	        return cleanResponse(responseBody);
		} catch (URISyntaxException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (ClientProtocolException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (IOException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
		
		return null;
	}

	/**
	 * @param qparams
	 * @return
	 * @throws URISyntaxException
	 */
	private URI buildURI(List<NameValuePair> qparams) throws URISyntaxException {
		URI uri;
		uri = URIUtils.createURI("http", this.host, -1, "/servlet/com.esri.esrimap.Esrimap", 
		    URLEncodedUtils.format(qparams, encoding), null);
		return uri;
	}
	public static String cleanResponse(String axl) {
		int st = axl.indexOf("<ARCXML version=\"1.1\">");
		int e = axl.indexOf("</ARCXML>") + 9;
		//logger.debug("Len: " + axl.length() +" st: " + st + " e: " +e);
		return axl.substring(st, e);
	}
	
	public String ping(PingType pingType) {
		String response = null;
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("CMD", pingType.getCommand()));
		URI uri;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			
			uri = buildURI(qparams);
			HttpGet httpget = new HttpGet(uri);			
			
			 // Create a response handler
	        ResponseHandler<String> responseHandler = new BasicResponseHandler();
	        response = httpclient.execute(httpget, responseHandler);
	        

	        // When HttpClient instance is no longer needed, 
	        // shut down the connection manager to ensure
	        // immediate deallocation of all system resources
	        httpclient.getConnectionManager().shutdown();
	        
		} catch (URISyntaxException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (ClientProtocolException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		} catch (IOException e) {
			String msg = "%s: %s";
			msg = String.format(msg, e.getClass().getName(), e.getMessage());
			logger.error(msg);
		}
		return response;
		
	}
}
