<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    <title>Lista de Productos</title>
  </head>
  <body>
    <div class="wrapper">
      <jsp:include page="components/sidebar.jsp"></jsp:include>
      <jsp:include page="components/header.jsp"></jsp:include>
      <div class="page-wrapper">
        <div class="page-content">
          <jsp:include page="components/breadcrumb.jsp">
            <jsp:param name="pagina" value="Producto" />
            <jsp:param name="accion" value="Listado" />
          </jsp:include>
          <div class="row">
            <div class="col mx-auto">
              <hr />
              <div class="card radius-10 mb-5 box-shadow">
                <div class="card-body">
                  <div class="d-flex align-items-center">
                    <div>
                      <h5 class="mb-0">Lista de Productos</h5>
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
                          <th>N&uacute;mero</th>
                          <th>Descripci&oacute;n</th>
                          <th>Categor&iacute;a</th>
                          <th>Precio</th>
                          <th>Stock</th>
                          <th>Acciones</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${ lstProductos }" var="prod">
                          <tr>
                            <td>${prod.idProducto}</td>
                            <td>${prod.descripcion}</td>
                            <td>${prod.descCategoria}</td>
                            <td>
                              <fmt:setLocale value="es_PE" />
                              <fmt:formatNumber
                                type="currency"
                                value="${prod.precio}"
                              />
                            </td>
                            <td>${prod.cantidad}</td>
                            <td>
                              <div class="d-flex order-actions">
                                <a
                                  href="ps?opcion=buscar&codigo=${prod.idProducto}"
                                  class="btn btn-success"
                                  data-bs-toggle="tooltip"
                                  data-bs-placement="bottom"
                                  title="Editar Producto"
                                >
                                  <i class="bx bx-edit mx-0"></i
                                ></a>
                                <a
                                  href="ps?opcion=eliminar&codigo=${prod.idProducto}"
                                  class="ms-4 btn btn-danger"
                                  data-bs-toggle="tooltip"
                                  data-bs-placement="bottom"
                                  title="Eliminar Producto"
                                >
                                  <i class="bx bx-trash mx-0"></i
                                ></a>
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
  </body>
</html>
