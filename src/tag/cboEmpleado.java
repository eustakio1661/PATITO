package tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import beans.EmpleadoDTO;
import dao.DAOFactory;

@SuppressWarnings("serial")
public class cboEmpleado extends TagSupport {
   
    
    public int doStartTag() throws JspException {
        
        
        JspWriter out = pageContext.getOut();
        try {
            DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
            ArrayList<EmpleadoDTO> lista = fabrica.getEmpleadoDAO().listadoTipoEmpleado(); 
            for (EmpleadoDTO a : lista) {
            out.print("<option  value='" + a.getIdTipoEmpleado()+"'>"+a.getDescripcionTipoEmpleado()+"</option>");
                              
            }
          
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error en cargar autores: " + e);
        }

        return SKIP_BODY;

    }



    public int doEndTag() throws JspException {

        return EVAL_PAGE;

    }
}
