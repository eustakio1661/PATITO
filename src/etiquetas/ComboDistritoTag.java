package etiquetas;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import beans.DistritoDTO;
import dao.DAOFactory;

public class ComboDistritoTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    public String idDistrito = "-1";

    public String getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }
    
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ArrayList<DistritoDTO> listaDistrito = factory.getDistritoDAO().listarDistrito();

        try {

            out.println("<option selected disabled hidden value=\"\">");
            out.println("Seleccione un distrito");
            out.println("</option>");

             if (listaDistrito == null || listaDistrito.isEmpty())
                 idDistrito = "-1";

            for (DistritoDTO d : listaDistrito) {

                if (d.getId() == Integer.parseInt(idDistrito))
                    out.println("<option selected value=\"" + d.getId() + "\">" + d.getDescripcion()
                            + "</option>");
                else
                    out.println("<option value=\"" + d.getId() + "\">" + d.getDescripcion() + "</option>");
            }

        } catch (Exception e) {
            System.out.println("Error en inicio de ComboDistritoTag : " + e.getMessage());
        }

        return SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
