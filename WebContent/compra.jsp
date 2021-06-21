<%@page import="beans.DetallePedidoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.ProductoDTO"%>
<%@page import="beans.ClienteDTO"%>
<%@page import="beans.EmpleadoDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
  EmpleadoDTO user = (EmpleadoDTO) request.getSession().getAttribute("e");
  ClienteDTO cli = (ClienteDTO) request.getSession().getAttribute("ClienteCompra");
  @SuppressWarnings("unchecked")
  ArrayList<DetallePedidoDTO> det = (ArrayList<DetallePedidoDTO>) request.getSession().getAttribute("carro");
  if (user == null) {
      response.sendRedirect("login.jsp");
      return;
  }
	if (cli == null || det == null){
      response.sendRedirect("ps?opcion=catalogo");
      return;
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
    <title>Compra</title>
  </head>
  <body>
  <fmt:setLocale value="es_PE" />
    <div class="wrapper">
      <jsp:include page="components/sidebar.jsp"></jsp:include>
      <jsp:include page="components/header.jsp"></jsp:include>
      <div class="page-wrapper">
        <div class="page-content">
          <jsp:include page="components/breadcrumb.jsp">
            <jsp:param name="pagina" value="Ventas" />
            <jsp:param name="accion" value="Compra" />
          </jsp:include>
          <div class="card">
            <div class="card-body p-4">
              <h5 class="card-title">Carrito de Compras</h5>
              <hr />
              <div class="form-body mt-4">
                <div class="row">
                  <div class="col-lg-8">
                    <div class="border border-3 p-4 rounded">
                      <div class="card-body">
                        <div class="d-flex align-items-center">
                          <div>
                            <h5 class="mb-0">Productos Seleccionados</h5>
                          </div>
                          <div class="font-22 ms-auto">
                            <i class="bx bx-dots-horizontal-rounded"></i>
                          </div>
                        </div>
                        <hr />
                        <div class="table-responsive text-nowrap">
                          <table class="table align-middle mb-0">
                            <thead class="table-light">
                              <tr>
                                <th>Producto</th>
                                <th>Precio</th>
                                <th>Cantidad</th>
                                <th>Importe</th>
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach items="${ carro }" var="carro">
                                <tr>
                                  <td>
                                    <div class="d-flex align-items-center">
                                      <div class="recent-product-img">
                                        <img src="${carro.imagen }" alt="" />
                                      </div>
                                      <div class="ms-2">
                                        <h6 class="mb-1 font-14">
                                          ${carro.nombreProd }
                                        </h6>
                                      </div>
                                    </div>
                                  </td>
                                  <td>${carro.precio }</td>
                                  <td>${carro.cantidad }</td>
                                  <td><fmt:formatNumber
                              type="currency"
                              value="${carro.importe}"
                            /></td>
                                </tr>
                              </c:forEach>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                    <div class="my-2 mx-2">
                      <a class="btn btn-primary" href="ps?opcion=catalogo">Seguir Comprando</a>
                      <button id="btnCancelarCompra" class="mx-1 btn btn-danger">Cancelar Compra</button>
                    </div>
                  </div>
                  <div class="col-lg-4">
                    <div class="border border-3 p-4 rounded">
                      <form id="formCompra" action="venser?opcion=procCompra" method="post">
                        <h5>Resumen de tu Pedido</h5>
                        <hr />
                        <div class="row g-3">
                          <div class="col-md-10">
                            <h4>Cliente</h4>
                            <label class="mb-3"
                              >Nombre: ${ClienteCompra.nombreCompleto }</label
                            >
                            <br />
                            <label class="mb-3"
                              >Distrito: ${ClienteCompra.nombreDistrito }</label
                            >
                            <br />
                            <label
                              >Direccion: ${ClienteCompra.direccion }</label
                            >
                          </div>
                          <hr />
                          
                          <div class="col-md-6">
                            <label>Subtotal(${cantidadProductos })</label>
                          </div>
                          <div class="col-sm-4">
                            <label
                              ><fmt:formatNumber
                                type="currency"
                                value="${subTotalVentas }"
                            /></label>
                          </div>
                          <hr />
                          <div class="col-md-6">
                            <label>Descuento </label>
                          </div>
                          <div class="col-sm-4">
                            <c:if test="${ClienteDescuento.descuento != 0}">
                              <fmt:formatNumber
                                type="percent"
                                value="${ClienteDescuento.descuento}"
                              />
                            </c:if>
                            <c:if test="${ClienteDescuento.descuento == 0}">
                              <label>No Aplica</label>
                            </c:if>
                          </div>
                          <hr />
                          <div class="col-md-6">
                            <h4>Total</h4>
                          </div>
                          <div class="col-sm-4">
                            <fmt:formatNumber
                              type="currency"
                              value="${subTotalVentas-(subTotalVentas * ClienteDescuento.descuento) }"
                            />
                          </div>
                          <div class="col-12">
                            <div class="d-grid">
                              <button
                                type="submit"
                                class="btn btn-success"
                              >
                                Finalizar Compra
                              </button>
                            </div>
                          </div>
                        </div>
                      </form>
                    </div>
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

    <jsp:include page="reusable/scripts.jsp"></jsp:include>
    <jsp:include page="reusable/datatable_scripts.jsp"></jsp:include>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script src="js/compra.js"></script>
  </body>
</html>
