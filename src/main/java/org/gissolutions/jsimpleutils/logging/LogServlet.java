package org.gissolutions.jsimpleutils.logging;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.xml.DOMConfigurator;
/**
 * Edite el web.xml y adicione el siguiente snippet: <br/>
 * <code>
 * &lt;servlet&gt;<br/>
		&lt;servlet-name&gt;LogServlet&lt;/servlet-name&gt;<br/>
		&lt;servlet-class&gt;org.gissolutions.jsimpleutils.logging.LogServlet&lt;/servlet-class&gt;<br/>
		&lt;init-param&gt;<br/>
			&lt;param-name&gt;setup&lt;/param-name&gt;<br/>
			&lt;param-value&gt;WEB-INF/log4jprops.xml&lt;/param-value&gt;<br/>
		&lt;/init-param&gt;<br/>
		&lt;load-on-startup&gt;1&lt;/load-on-startup&gt;<br/>
	&lt;/servlet&gt;<br/>
	</code>
 * @author LBerrocal
 *
 */
public class LogServlet extends HttpServlet
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2433609215822261945L;
	private String _configurationFile;
    static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LogServlet.class);
    @Override
    public void init() throws ServletException
    {
        super.init();
        //String configfile ="/log4jprops.xml";				
        this._configurationFile = getServletContext().getRealPath("/") + getInitParameter("setup");
        DOMConfigurator.configure(this._configurationFile);
        //PropertyConfigurator.configure(_configurationFile);
        logger.info("Log4j configured with " + _configurationFile);
        
    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        String err = null;
        

        try
        {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            if (err != null)
            {
                out.println("<h1> Error IO </h1>");
                out.println("<p>" + err + "</p>");
            } else
            {
                out.println("<h1> Logging Properties </h1>");
                out.println("<p> Configuration file: " + this._configurationFile+ "</p>");

            }

            out.println("</body>");
            out.println("</html>");
        } finally
        {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     */
    public String getServletInfo()
    {
        return "Log4j Configurator";
    }
    // </editor-fold>
}
