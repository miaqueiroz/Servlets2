
package serlvetsabc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mayara
 */
@WebServlet(name = "CidadesC", urlPatterns = {"/CidadesC"})
public class CidadesC extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <title>Cidades com C</title>");            
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Cidades com C</h1>");
            out.println("           <ul>");
            try{
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CidadesA.class.getName()).log(Level.SEVERE, null, ex);
                }
                java.sql.Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/web2?autoReconnect=true&useSSL=false", "root", "wi-fi147");

                PreparedStatement ps = conexao.prepareStatement("SELECT Nome FROM Municipio WHERE Nome LIKE 'C%' ");
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    out.println("<li>" + rs.getString("nome") + "</li>");
                }
                conexao.close();
            }catch(SQLException e){ 
                e.printStackTrace();
            }
            out.println("           </ul>");
            out.println("   </body>");
            out.println("</html>");
        }
    }

}
