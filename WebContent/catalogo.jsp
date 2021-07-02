<%@page import="beans.EmpleadoDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <title>Catalogo de Productos</title>
  </head>
  <body>
    <div class="wrapper">
      <jsp:include page="components/sidebar.jsp"></jsp:include>
      <jsp:include page="components/header.jsp"></jsp:include>
      <div class="page-wrapper">
        <div class="page-content">
          <jsp:include page="components/breadcrumb.jsp">
            <jsp:param name="pagina" value="Ventas" />
            <jsp:param name="accion" value="Nuevo Pedido" />
          </jsp:include>

          <div class="row">
            <div class="col mx-auto">
              <hr />
              <div class="card radius-10 mb-2 box-shadow">
                <div class="card-body">
                  <div class="d-flex align-items-center">
                    <div>
                      <h5 class="mb-0">Buscar Cliente</h5>
                    </div>
                    <div class="font-22 ms-auto">
                      <i class="bx bx-dots-horizontal-rounded"></i>
                    </div>
                  </div>
                  <hr />
                  <div class="row g-3">
                    <form id="formBuscarCliente" class="needs-validation g-3 mt-2 row" novalidate>                      
                      <div class="col-md-4">                        
                        <input
                          type="text"
                          class="form-control"
                          id="txtDNICli"
                          name="txtDNICli"
                          placeholder="Ingrese DNI del cliente"
                          required
                          pattern="[0-9]{8}"
                        />
                        <div class="invalid-feedback">
                          Se requiere de un DNI v&aacute;lido
                        </div>
                      </div>
                      <div class="col-md-4">
                        <button class="btn btn-primary" type="submit">
                          <i class="bx bx-search-alt"></i> Buscar Cliente
                        </button>
                        <button class="btn btn-danger" type="button" id="btnLimpiarClienteTxt">
                          <i class='bx bx-trash mx-0' ></i>
                        </button>
                      </div>
                    </form>
                    <div class="col-md-3">
                      <label for="txtNombreCli" class="form-label"
                        >Nombre Completo</label
                      >
                      <input
                        type="text"
                        class="form-control"
                        id="txtNombreCli"
                        placeholder="Nombre del Cliente"
                        readonly
                      />
                    </div>
                    <div class="col-md-3">
                      <label for="txtDistritoCli" class="form-label"
                        >Distrito</label
                      >
                      <input
                        type="text"
                        class="form-control"
                        id="txtDistritoCli"
                        placeholder="Distrito del Cliente"
                        readonly
                      />
                    </div>
                    <div class="col-md-6">
                      <label for="txtDireccionCli" class="form-label"
                        >Direcci&oacute;n</label
                      ><input
                        type="text"
                        class="form-control"
                        id="txtDireccionCli"
                        placeholder="Direcci&oacute;n del Cliente"
                        readonly
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col mx-auto">
              <hr />
              <div class="card radius-10 mb-5 box-shadow">
                <div class="card-body">
                  <div class="d-flex align-items-center">
                    <div>
                      <h5 class="mb-0">Catalogo de Productos</h5>
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
                          <th></th>
                          <th>Descripci&oacute;n</th>
                          <th>Categor&iacute;a</th>
                          <th>Precio</th>
                          <th>Stock</th>
                          <th>Acciones</th>
                        </tr>
                      </thead>
                      <tbody class="tbody">
                        <c:forEach items="${ lstProductos }" var="prod">
                          <tr>
                            <td>
                              <img src="${ prod.imagen }" alt="${ prod.descripcion }" width="100">
                            </td>
                            <td>${fn:toUpperCase(prod.descripcion)}</td>
                            <td>${prod.descCategoria}</td>
                            <td>
                              <fmt:setLocale value="es_PE" />
                              <fmt:formatNumber
                                type="currency"
                                value="${prod.precio}"
                              />
                            </td>
                            <td id="fila-id-prod-${ prod.idProducto }">
                              ${prod.cantidad}
                            </td>
                            <td>
                              <div class="d-flex">
                                <button
                                  type="button"
                                  class="ms-4 btn btn-link select-prod"
                                  data-idprod="${ prod.idProducto }"
                                  data-bs-toggle="tooltip"
                                  data-bs-placement="bottom"
                                  title="Seleccionar Producto"
                                >
                                  Seleccionar Producto
                                </button>
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
      <a href="javaScript:;" class="back-to-top">
        <i class="bx bxs-up-arrow-alt"></i>
      </a>
      <jsp:include page="components/footer.jsp"></jsp:include>
    </div>
    
    <jsp:include page="components/canasta.jsp"></jsp:include>

    <jsp:include page="reusable/scripts.jsp"></jsp:include>
    <jsp:include page="reusable/datatable_scripts.jsp"></jsp:include>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="js/catalogo.js"></script>
  </body>
</html>
