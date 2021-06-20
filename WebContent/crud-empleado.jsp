<%@page import="beans.EmpleadoDTO"%>
<%@ taglib  uri="/WEB-INF/libreria.tld" prefix="tools"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
  EmpleadoDTO user = (EmpleadoDTO) request.getSession().getAttribute("e");
  if (user == null) {
      response.sendRedirect("login.jsp");
  }
  
%> 

<%
  EmpleadoDTO existeEmpleado = (EmpleadoDTO) request.getAttribute("empleadoEncontrado");
  String action = "emse?opcion=registro";
  String typeColor = "primary";
  String opcion = "Registrar";
  String tituloForm = "Registro de Empleados";
  String entidad = "empleado";
           
  if (existeEmpleado != null) {
      action = "emse?opcion=actualizar";
      typeColor = "success";
      opcion="Actualizar";
      tituloForm = "Actualizar un Empleado";
   } 
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="reusable/styles.jsp"></jsp:include>
    <title><%=opcion%> Empleado</title>
  </head>
  <body>
    <div class="wrapper">
      <jsp:include page="components/sidebar.jsp"></jsp:include>
      <jsp:include page="components/header.jsp"></jsp:include>
      <div class="page-wrapper">
        <div class="page-content">
          <jsp:include page="components/breadcrumb.jsp">
            <jsp:param name="pagina" value="Empleado" />
            <jsp:param name="accion" value="<%=opcion%>" />
          </jsp:include>
          <div class="row">
            <div class="col-xl-9 mx-auto">
              <hr />
              <div
                class="card border-top border-0 border-4 border-<%=typeColor%>"
              >
                <div class="card-body p-5">
                  <div class="card-title d-flex align-items-center">
                    <div>
                      <i
                        class="bx bxs-user me-1 font-22 text-<%=typeColor%>"
                      ></i>
                    </div>
                    <h5 class="mb-0 text-<%=typeColor%>"><%=tituloForm%></h5>
                  </div>
                  <hr />
                  <form
                    class="row g-3 needs-validation"
                    action="<%=action%>"
                    method="POST"
                    novalidate
                  >
                    <input
                      type="hidden"
                      class="input-hidden"
                      id="input-hidden"
                      name="txtIdEmpleado"
                      data-entidad="<%=entidad %>"
                      value="${ empleadoEncontrado.id }"
                    />
                    <div class="col-12">
                      <label for="txtDNIEmpleado" class="form-label"
                        >DNI :
                      </label>
                      <input
                        type="text"
                        class="form-control"
                        id="txtDNIEmpleado"
                        name="txtDNIEmpleado"
                        placeholder="Ingrese DNI"
                        required
                        value="${ empleadoEncontrado.dni }"
                      />
                      <div class="invalid-feedback">
                        Ingrese un DNI v&aacute;lido
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label for="txtNombreEmpleado" class="form-label"
                        >Nombre :</label
                      >
                      <input
                        type="text"
                        class="form-control"
                        id="txtNombreEmpleado"
                        name="txtNombreEmpleado"
                        placeholder="Ingrese nombre"
                        value="${ empleadoEncontrado.nombre }"
                        required
                      />
                      <div class="invalid-feedback">
                        Ingrese un nombre v&aacute;lido
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label for="txtApellidoEmpleado" class="form-label"
                        >Apellido :</label
                      >
                      <input
                        type="text"
                        class="form-control"
                        id="txtApellidoEmpleado"
                        name="txtApellidoEmpleado"
                        placeholder="Ingrese apellido"
                        value="${ empleadoEncontrado.apellido }"
                        required
                      />
                      <div class="invalid-feedback">
                        Ingrese un apellido v&aacute;lido
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label for="txtTelefonoEmpleado" class="form-label"
                        >Tel&eacute;fono</label
                      >
                      <input
                        type="text"
                        class="form-control"
                        id="txtTelefonoEmpleado"
                        name="txtTelefonoEmpleado"
                        placeholder="Fijo o celular"
                        value="${ empleadoEncontrado.telefono }"
                        required
                      />
                      <div class="invalid-feedback">
                        Ingrese un tel&eacute;fono v&aacute;lido
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label for="cboTipoEmpleado" class="form-label"
                        >Tipo :</label
                      >
                      <select
                        id="cboTipoEmpleado"
                        class="form-select"
                        name="cboTipoEmpleado"
                      >
                        <tools:comboTipoEmp
                          idTipoEmp="${ empleadoEncontrado.idTipo }"
                        />
                      </select>
                      <div class="invalid-feedback">
                        Seleccione un tipo v&aacute;lido
                      </div>
                    </div>
                    <div class="col-12">
                      <label for="txtDireccionEmpleado" class="form-label"
                        >Direcci&oacute;n :
                      </label>
                      <textarea
                        class="form-control"
                        id="txtDireccionEmpleado"
                        name="txtDireccionEmpleado"
                        placeholder="Ingrese direcci&oacute;n"
                        rows="3"
                        minlength="0"
                      >${ empleadoEncontrado.direccion }</textarea>
                    </div>
                    <div class="col-12">
                      <button type="submit" class="btn btn-<%=typeColor%> px-5">
                        <%=opcion%> Empleado
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="overlay toggle-icon"></div>
      <a href="javaScript:;" class="back-to-top"
        ><i class="bx bxs-up-arrow-alt"></i
      ></a>

      <jsp:include page="components/footer.jsp"></jsp:include>
    </div>

    <jsp:include page="reusable/scripts.jsp"></jsp:include>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="js/send_form.js"></script>
  </body>
</html>
