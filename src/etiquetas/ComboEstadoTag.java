package etiquetas;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ComboEstadoTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    public String data = "";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.println("<select\r\n" + "id=\"cboEstado\"\r\n"
                    + " class=\"form-select\"\r\n"
                    + " name=\"cboEstado\"\r\n" + "data-servlet=" + data + ""
                    + " required\r\n" + " > ");
            out.println("<option selected disabled hidden value=\"\">");
            out.println("Seleccione Estado");
            out.println("</option>");

            out.println("<option value='1'>Activo</option>");
            out.println("<option value='0'>Inactivo</option></select>");
        } catch (Exception e) {
            System.out.println("Error en inicio de ComboEstadoTag : " + e.getMessage());
        }

        return SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
