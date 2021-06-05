package etiquetas;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import beans.TipoEmpleadoDTO;
import dao.DAOFactory;

public class ComboTipoEmpTag extends TagSupport {

    private static final long serialVersionUID = 1L;
    
    public String idTipoEmp = "-1";
    
    public String getIdTipoEmp() {
        return idTipoEmp;
    }
    
    public void setIdTipoEmp(String idTipoEmp) {
        this.idTipoEmp = idTipoEmp;
    }
    
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ArrayList<TipoEmpleadoDTO> listaTipoEmp = factory.getEmpleadoDAO().listadoTipoEmpleado();
        
        try {

            out.println("<option selected disabled hidden value=\"\">");
            out.println("Seleccione un tipo");
            out.println("</option>");

             if (idTipoEmp == null || idTipoEmp.isEmpty())
                 idTipoEmp = "-1";

            for (TipoEmpleadoDTO t : listaTipoEmp) {

                if (t.getId() == Integer.parseInt(idTipoEmp))
                    out.println("<option selected value=\"" + t.getId() + "\">" + t.getDescripcion()
                            + "</option>");
                else
                    out.println("<option value=\"" + t.getId() + "\">" + t.getDescripcion() + "</option>");
            }

        } catch (Exception e) {
            System.out.println("Error en inicio de ComboTipoEmpTag : " + e.getMessage());
        }

        return SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
