/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juan.karsten
 */
@WebServlet(urlPatterns = {"/Respond"})
public class Respond extends HttpServlet {

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
            
            //URL REST
            String uri ="http://152.118.26.22:8080/silintong2/webresources/lintongservices/answer/"+username+"/"+password
                    +"/"+request.getParameter("content")+"/"+request.getParameter("id");
            
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
            out.println(sb.toString());
        } catch (ProtocolException ex) {
            out.print("gagal: "+ex);
        } catch (IOException ex) {
            out.print("gagal: "+ex);
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
