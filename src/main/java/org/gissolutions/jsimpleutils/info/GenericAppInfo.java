package org.gissolutions.jsimpleutils.info;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;


/**
 * La clase GenericAppInfo representa la información de la aplicación, como
 * versión, número de build, y fecha de build.
 * @author LBerrocal
 */
public abstract class GenericAppInfo {

   private static org.apache.log4j.Logger logger = org.apache.log4j.Logger
		.getLogger(GenericAppInfo.class);
    
    protected Properties props;   
    protected String version;
    protected String buildNumber;
    protected String buildDate;
    protected String buildHost;
    protected String buildUser;
    
    public GenericAppInfo()
    {
        InputStream in = null;
        props = new Properties();
        try
        {
        	
            in = getClass().getResourceAsStream("appinfo.properties");
            props.load(in);
            this.version = props.getProperty("program.VERSION");
            this.buildNumber = props.getProperty("program.BUILDNUM");
            this.buildDate = props.getProperty("program.BUILDDATE");
            this.buildHost = props.getProperty("program.BUILDHOST");
            this.buildUser  = props.getProperty("program.BUILDUSER");
        } catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }
    }

    public Properties getProps()
    {
        return props;
    }

    public String getVersion()
    {
        return version;
    }
    
    

    public String getBuildNumber()
    {
        return buildNumber;
    }

    public String getBuildDate()
    {
        return buildDate;
    }

	public String getBuildHost() {
		return buildHost;
	}

	public String getBuildUser() {
		return buildUser;
	}
	public static void configureLog4J(Class<?> cls, String appHomeVariable, String configfile) {
		
		//String configfile = "/log4jprops.xml";
		URL log4jConfigUrl =  cls.getClassLoader().getResource(configfile);//cmd.getClass().getResource(configfile);
		if(log4jConfigUrl == null){
			String path = getApplicationHome(appHomeVariable);
			try {
				log4jConfigUrl = new URL("file:/" +  path + configfile);
			} catch (MalformedURLException e) {				
				e.printStackTrace();
			}
		}		
		DOMConfigurator.configure(log4jConfigUrl);
		logger.info("Log4j configured with " + log4jConfigUrl.getFile());
	}
	/**
	 * Reads the application home based on the Home Variable. The method will first try to read
	 * the Home Variable as property supplied in the java invocation, the will try to read
	 * the Home Variable as a enviroment variable, if none is found the application will try the
	 * current path use the path of a new file constructed frome new File(".")
	 */
	public static String getApplicationHome(String appHomeVariable) {
		String mapaminHome = null;
		mapaminHome = System.getProperty(appHomeVariable);
		if(mapaminHome == null) {
			mapaminHome = System.getenv(appHomeVariable);
		}
		if(mapaminHome == null) {
			File f = new File(".");
			mapaminHome = f.getAbsolutePath();
		}
		return mapaminHome.substring(0, mapaminHome.length()-2);
		
	}
}
