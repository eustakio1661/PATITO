package etiquetas;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import beans.CategoriaDTO;
import dao.DAOFactory;

public class ComboCategoriaTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    public String idCategoria = "-1";

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ArrayList<CategoriaDTO> listaCategoria = factory.getProductoDAO().listadoCategoria();

        try {

            out.println("<option selected disabled hidden value=\"\">");
            out.println("Seleccione una categoría");
            out.println("</option>");

             if (idCategoria == null || idCategoria.isEmpty())
                 idCategoria = "-1";

            for (CategoriaDTO c : listaCategoria) {

                if (c.getIdcategoria() == Integer.parseInt(idCategoria))
                    out.println("<option selected value=\"" + c.getIdcategoria() + "\">" + c.getDescripcion()
                            + "</option>");
                else
                    out.println("<option value=\"" + c.getIdcategoria() + "\">" + c.getDescripcion() + "</option>");
            }

        } catch (Exception e) {
            System.out.println("Error en inicio de ComboCategoriaTag : " + e.getMessage());
        }

        return SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
