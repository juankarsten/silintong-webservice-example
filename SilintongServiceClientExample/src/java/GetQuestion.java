/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author juan.karsten
 */
@WebServlet(urlPatterns = {"/GetQuestion"})
public class GetQuestion extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
             {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=null;
        try {
            out = response.getWriter();
            //URI webservice untuk melihat pertanyaan
            //ISI BERDASARKAN USERNAME PASSWORD SENDIRI
            String username ="juju";
            String password ="juju";
            String idCategory="1";
            //MULAI QUERY DI QUESTION BERAPA
            String limitawal="0";
            //BANYAK DATA DIAMBIL
            String limitakhir="10";
            
            //URL REST
            String uri ="http://152.118.26.22:8080/silintong2/webresources/lintongservices/seequestion/"+username+"/"+password
                    +"/"+idCategory+"/"+limitawal+"/"+limitakhir;
            
            URL url = new URL(uri);
            HttpURLConnection connection = 
                (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            InputStream is=connection.getInputStream();
            
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer sb=new StringBuffer();
            while ((line = br.readLine()) != null) {
		sb.append(line);
            }
            ArrayList<HashMap<String,String>> ar=new Parser(sb.toString()).getParse(out);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetQuestion</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<table border=\"1\"><tr>");
            
            out.println("<td>");
            out.println(Parser.IDQUESTION);
            out.println("</td>");
            
            out.println("<td>");
            out.println(Parser.TITLE);
            out.println("</td>");
            
            out.println("<td>");
            out.println(Parser.CONTENT);
            out.println("</td>");
            
            out.println("<td>");
            out.println(Parser.DATEPOSTED);
            out.println("</td>");
            
            out.println("<td>");
            out.println(Parser.DUEDATE);
            out.println("</td>");
            
            out.println("<td>");
            out.println(Parser.ISANSERED);
            out.println("</td>");
            
            out.println("<td>");
            out.println("jawab");
            out.println("</td>");
            
            out.println("</tr>");
            
            for(int ii=0;ii<ar.size();ii++){
                out.println("<tr>");
                
                HashMap<String,String> hashMap=ar.get(ii);
                
                out.println("<td>");
                out.println(hashMap.get(Parser.IDQUESTION));
                out.println("</td>");

                out.println("<td>");
                out.println(hashMap.get(Parser.TITLE));
                out.println("</td>");

                out.println("<td>");
                out.println(hashMap.get(Parser.CONTENT));
                out.println("</td>");

                out.println("<td>");
                out.println(hashMap.get(Parser.DATEPOSTED));
                out.println("</td>");

                out.println("<td>");
                out.println(hashMap.get(Parser.DUEDATE));
                out.println("</td>");

                out.println("<td>");
                out.println(hashMap.get(Parser.ISANSERED));
                out.println("</td>");
                
                out.println("<td>");
                    out.print("<form action=\"jawab.jsp\" method=\"post\">");
                    out.print("<input type=\"submit\" value=\"jawab\"/>");
                    out.print("<input type=\"hidden\" name=\"idquestion\" value=\""+hashMap.get(Parser.IDQUESTION)+"\"/>");
                    out.print("</form>");
                out.println("</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            out.print(ex.toString());
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
