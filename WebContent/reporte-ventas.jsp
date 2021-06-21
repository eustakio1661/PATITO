<%@page import="beans.EmpleadoDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
    <link
      href="https://cdn.datatables.net/buttons/1.7.1/css/buttons.bootstrap5.min.css"
      rel="stylesheet"
    />
    <title>Reporte Ventas</title>
  </head>
  <body>
    <div class="wrapper">
      <jsp:include page="components/sidebar.jsp"></jsp:include>
      <jsp:include page="components/header.jsp"></jsp:include>
      <div class="page-wrapper">
        <div class="page-content">
          <jsp:include page="components/breadcrumb.jsp">
            <jsp:param name="pagina" value="Reporte de Ventas" />
            <jsp:param name="accion" value="Reporte de ventas entre fechas" />
          </jsp:include>
          <div class="row">
            <div class="col mx-auto">
              <hr />
              <div class="card radius-15 mb-3 box-shadow">
                <div class="card-body">
                  <div class="d-flex align-items-center">
                    <div>
                      <h5 class="mb-1">Reporte de ventas entre fechas</h5>
                    </div>
                    <div class="col-md-9">
                      <form
                        class="float-md-end"
                        action="rese"
                        method="post"
                        novalidate
                      >
                        <div class="row row-cols-md-auto g-lg-3">
                          <label
                            for="inputFromDate"
                            class="col-md-2 col-form-label text-md-end"
                            >F.Inicial</label
                          >
                          <div class="col-md-4">
                            <input
                              type="date"
                              class="form-control"
                              name="txtFechaInicio"
                              value="reporte"
                            />
                          </div>
                          <label
                            for="inputToDate"
                            class="col-md-2 col-form-label text-md-end"
                            >F.Final</label
                          >
                          <div class="col-md-4">
                            <input
                              type="date"
                              class="form-control"
                              name="txtFechaFinal"
                              value="reporte"
                            />
                          </div>
                          <div class="col-md-12 col-form-label text-md-end">
                            <button
                              class="btn btn-primary"
                              name="opcion"
                              value="reporte"
                            >
                              Filtrar Búsqueda
                            </button>
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>
                  <hr />
                  <div class="table-responsive text-nowrap">
                    <table id="myTable" class="table align-middle mb-0">
                      <thead class="table-light">
                        <tr>
                          <th>ID Boleta</th>
                          <th>Descripci&oacute;n</th>
                          <th>Fecha</th>
                          <th>Cantidad</th>
                          <th>Precio</th>
                          <th>Descuento</th>
                          <th>Importe Total</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${ listadoVentas }" var="ven">
                          <tr>
                            <td>${ven.idBoleta }</td>
                            <td>${ven.descripcion }</td>
                            <td>${ven.fecha }</td>
                            <td>${ven.cantidad }</td>
                            <td>
                              <fmt:setLocale value="es_PE" />
                              <fmt:formatNumber
                                type="currency"
                                value="${ven.precio}"
                              />
                            </td>
                            <td>
                              <fmt:formatNumber
                                type="percent"
                                value="${ven.descuento}"
                              />
                            </td>
                            <td>
                              <fmt:setLocale value="es_PE" />
                              <fmt:formatNumber
                                type="currency"
                                value="${ven.importeTotal}"
                              />
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
    <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap5.min.js"></script>

    <!-- Activar Botones -->
    <script src="https://cdn.datatables.net/buttons/1.7.1/js/dataTables.buttons.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.7.1/js/buttons.bootstrap5.min.js"></script>

    <script src="js/datatable_buttons_config.js"></script>
    <!-- Button Excel -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
    <!-- Button Pdf -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
    <!-- Button Copy and Print -->
    <script src="https://cdn.datatables.net/buttons/1.7.1/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.7.1/js/buttons.print.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.7.1/js/buttons.colVis.min.js"></script>
  </body>
</html>
