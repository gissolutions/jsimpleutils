package org.gissolutions.jsimpleutils.info;
import java.io.InputStream;
import java.util.Properties;

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
    

}
