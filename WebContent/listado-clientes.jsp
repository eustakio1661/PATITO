<%@page import="beans.EmpleadoDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
  EmpleadoDTO user = (EmpleadoDTO) request.getSession().getAttribute("e");
  if (user == null) {
      response.sendRedirect("login.jsp");
  }
  
%> 

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="reusable/styles.jsp"></jsp:include>
    <link
      href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap5.min.css"
      rel="stylesheet"
    />
    <title>Lista de Clientes</title>
  </head>
  <body>
    <div class="wrapper">
      <jsp:include page="components/sidebar.jsp"></jsp:include>
      <jsp:include page="components/header.jsp"></jsp:include>
      <div class="page-wrapper">
        <div class="page-content">
          <jsp:include page="components/breadcrumb.jsp">
            <jsp:param name="pagina" value="Cliente" />
            <jsp:param name="accion" value="Listado" />
          </jsp:include>
          <div class="row">
            <div class="col mx-auto">
              <hr />
              <div class="card radius-10 mb-5 box-shadow">
                <div class="card-body">
                  <div class="d-flex align-items-center">
                    <div>
                      <h5 class="mb-0">Lista de Clientes</h5>
                    </div>
                    <div class="font-22 ms-auto">                
                      <select
                        id="cboEstado"
                        class="form-select"
                        data-servlet="cs?opcion=listado"
                        name="cboEstado"
                        required
                      >                                    
                       <option selected disabled hidden="hidden" value="">Seleccione Estado</option>
                       <option  value="1">Activo</option>
                       <option  value="0">Inactivo</option>
                      </select>               
                    </div>
                  </div>
                  <hr />
                  <div class="table-responsive text-nowrap">
                    <table id="myTable" class="table align-middle mb-0">
                      <thead class="table-light">
                        <tr>
                          <th>Número Cliente</th>
                          <th>DNI</th>
                          <th>Nombre</th>
                          <th>Apellido</th>
                          <th>Teléfono</th>
                          <th>Distrito</th>
                          <th>Acciones</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${ lstClientes }" var="cli">
                          <tr>
                            <td>${cli.codigo}</td>
                            <td>${cli.dni }</td>
                            <td>${cli.nombre }</td>
                            <td>${cli.apellido }</td>
                            <td>${cli.telefono }</td>
                            <td>${cli.nombreDistrito }</td>
                            <td>
                            <c:if test="${cli.estado == 1 }">
                              <div class="d-flex order-actions">
                                <a
                                  href="cs?opcion=buscar&codigo=${cli.codigo}"
                                  class="btn btn-success"
                                  data-bs-toggle="tooltip"
                                  data-bs-placement="bottom"
                                  title="Editar Cliente"
                                >
                                  <i class="bx bx-edit mx-0"></i
                                ></a>
                                <button
                                  type="button"
                                  data-entidad="cliente"
                                  data-nombre="${ cli.nombre } ${ cli.apellido }"
                                  data-opciones="eliminar"
                                  data-action="cs?opcion=eliminar&codigo=${cli.codigo}"
                                  class="ms-4 btn btn-danger btnEliminarEntidad"
                                  data-bs-toggle="tooltip"
                                  data-bs-placement="bottom"
                                  title="Eliminar Cliente"
                                >
                                  <i class="bx bx-trash mx-0"></i
                                ></button>
                              </div>
                              </c:if>
                              <c:if test="${cli.estado == 0 }">
                              <div class="d-flex order-actions">                           
                                <button
                                  type="button"
                                  data-entidad="cliente"
                                  data-nombre="${ cli.nombre } ${ cli.apellido }"
                                  data-opciones="regresar"
                                  data-action="cs?opcion=actualizarEstado&codigo=${cli.codigo}"
                                  class="ms-4 btn btn-primary btnActualizarEntidad"
                                  data-bs-toggle="tooltip"
                                  data-bs-placement="bottom"
                                  title="Actualizar Estado"
                                >
                                  <i class="bx bx-user-check mx-0" ></i>
                                </button>
                              </div>
                              </c:if>
                            </td>
                          </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
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
    <jsp:include page="reusable/datatable_scripts.jsp"></jsp:include>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="js/delete_data.js"></script>
  </body>
</html>
