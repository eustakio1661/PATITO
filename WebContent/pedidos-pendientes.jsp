<%@page import="beans.EmpleadoDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>Pedidos Pendientes</title>
  </head>
  <body>
    <div class="wrapper">
      <jsp:include page="components/sidebar.jsp"></jsp:include>
      <jsp:include page="components/header.jsp"></jsp:include>
      <div class="page-wrapper">
        <div class="page-content">
          <jsp:include page="components/breadcrumb.jsp">
            <jsp:param name="pagina" value="Dashboard" />
            <jsp:param name="accion" value="Pedidos" />
          </jsp:include>
          <div class="row">
            <div class="col mx-auto">
              <hr />
              <div class="card radius-10 mb-5 box-shadow">
                <div class="card-body">
                  <div class="d-flex align-items-center">
                    <div>
                      <h5 class="mb-0">Pedidos Pendientes</h5>
                    </div>
                    <div class="font-22 ms-auto">
                      <i class="bx bx-dots-horizontal-rounded"></i>
                    </div>
                  </div>
                  <hr />
                  <div class="table-responsive text-nowrap">
                    <table id="myTable" class="table align-middle mb-0">
                      <thead class="table-light">
                        <tr>
                          <th>ID</th>
                          <th>Nombre Empleado</th>
                          <th>Nombre Cliente</th>
                          <th>Fecha Pedido</th>
                          <th>Cantidad Total</th>
                          <th>Estado</th>
                          <th>Acciones</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${ lstPedidosPendientes }" var="pe">
                          <tr>
                            <td>${pe.id_pe}</td>
                            <td>${pe.nombreEmpleado}</td>
                            <td>${pe.nombreCliente}</td>
                            <td>
                            	${pe.fechaPedido}
                    
                            </td>
                            <td>${pe.cantidadTotal}</td>
                            <td>
                            <div class="d-flex align-items-center text-danger">	<i class='bx bx-radio-circle-marked bx-burst bx-rotate-90 align-middle font-18 me-1'></i>
												<span>
												<c:if test="${pe.estado == 1 }">
												Pendiente
												</c:if>
												</span>
											</div>
                            </td>
                            <td>
                              <div class="d-flex order-actions">
                                <a
                                  href="venser?opcion=actualizarPedido&codigo=${pe.id_pe}"
                                  class="btn btn-success"
                                  data-bs-toggle="tooltip"
                                  data-bs-placement="bottom"
                                  title="Actualizar Pedido"
                                >
                                  <i class='bx bx-station mx-0'></i>
                                  </a>
                               
                              </div>
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
