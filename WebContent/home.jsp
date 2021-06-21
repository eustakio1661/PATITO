<%@page import="beans.PedidoDTO"%>
<%@page import="beans.BoletaDTO"%>
<%@page import="beans.ClienteDTO"%>
<%@page import="mantenimiento.MySQLReporteDAO"%>
<%@page import="mantenimiento.MySQLVentaDAO"%>
<%@page import="dao.DAOFactory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.EmpleadoDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <title>Patito Restaurant</title>
  </head>
  <body>
    <fmt:setLocale value="es_PE" />
    <div class="wrapper">
      <jsp:include page="components/sidebar.jsp"></jsp:include>
      <jsp:include page="components/header.jsp"></jsp:include>
      <div class="page-wrapper">
        <div class="page-content">
          <div class="row row-cols-1 row-cols-md-2 row-cols-xl-4 mb-2">
            <div class="col">
              <div class="card radius-10 bg-gradient-deepblue mb-4 box-shadow">
                <div class="card-body">
                  <div class="d-flex align-items-center">
                    <%
                    ArrayList<PedidoDTO> listado2 = new MySQLReporteDAO().cantidadPedidos();
                    if(listado2 != null){
        				for(PedidoDTO p : listado2){	%>
        					<h5 class="mb-0 text-white"><%=p.getId_pe()%></h5> 
        				<%}
        			}
                    %>  
                    <div class="ms-auto">
                      <i class="bx bx-cart fs-3 text-white"></i>
                    </div>
                  </div>
                  <div
                    class="progress my-3 bg-light-transparent"
                    style="height: 3px"
                  >
                    <div
                      class="progress-bar bg-white"
                      role="progressbar"
                      style="width: 55%"
                      aria-valuenow="25"
                      aria-valuemin="0"
                      aria-valuemax="100"
                    ></div>
                  </div>
                  <div class="d-flex align-items-center text-white">
                    <p class="mb-0">Ventas Totales</p>
                    <p class="mb-0 ms-auto">
                      +4.2%<span><i class="bx bx-up-arrow-alt"></i></span>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div class="col">
              <div class="card radius-10 bg-gradient-orange mb-4 box-shadow">
                <div class="card-body">
                  <div class="d-flex align-items-center">
                        <%
                    ArrayList<BoletaDTO> listado1 = new MySQLReporteDAO().VentaTotal();
                    if(listado1 != null){
        				for(BoletaDTO b : listado1){	%>
        					<h5 class="mb-0 text-white"><fmt:formatNumber
                              type="currency"
                              value="<%=b.getPrecioTotal()%>"
                            /></h5> 
        				<%}
        			}
                    %>  
                    <div class="ms-auto">
                      <i class="bx bx-money fs-3 text-white"></i>
                    </div>
                  </div>
                  <div
                    class="progress my-3 bg-light-transparent"
                    style="height: 3px"
                  >
                    <div
                      class="progress-bar bg-white"
                      role="progressbar"
                      style="width: 55%"
                      aria-valuenow="25"
                      aria-valuemin="0"
                      aria-valuemax="100"
                    ></div>
                  </div>
                  <div class="d-flex align-items-center text-white">
                    <p class="mb-0">Ganancia Total</p>
                    <p class="mb-0 ms-auto">
                      +1.2%<span><i class="bx bx-up-arrow-alt"></i></span>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div class="col">
              <div
                class="card radius-10 bg-gradient-ohhappiness mb-4 box-shadow"
              >
                <div class="card-body">
                  <div class="d-flex align-items-center">                    
                      <%
                    ArrayList<ClienteDTO> list = new MySQLReporteDAO().cantidadClientes();
                    if(list != null){
        				for(ClienteDTO c : list){	%>
        					<h5 class="mb-0 text-white"><%=c.getCodigo()%></h5> 
        				<%}
        			}
                    %>  
                    <div class="ms-auto">
                      <i class="bx bx-group fs-3 text-white"></i>
                    </div>
                  </div>
                  <div
                    class="progress my-3 bg-light-transparent"
                    style="height: 3px"
                  >
                    <div
                      class="progress-bar bg-white"
                      role="progressbar"
                      style="width: 55%"
                      aria-valuenow="25"
                      aria-valuemin="0"
                      aria-valuemax="100"
                    ></div>
                  </div>
                  <div class="d-flex align-items-center text-white">
                    <p class="mb-0">Clientes</p>
                    <p class="mb-0 ms-auto">
                      +5.2%<span><i class="bx bx-up-arrow-alt"></i></span>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div class="col">
              <div class="card radius-10 bg-gradient-ibiza mb-4 box-shadow">
                <div class="card-body">
                  <div class="d-flex align-items-center">                    
                    <%
                    ArrayList<EmpleadoDTO> listado = new MySQLReporteDAO().cantidadEmpleados();
                    if(listado != null){
        				for(EmpleadoDTO e : listado){	%>
        					<h5 class="mb-0 text-white"><%=e.getId() %></h5> 
        				<%}
        			}
                    %>                           
                    <div class="ms-auto">
                      <i class="bx bx-group fs-3 text-white"></i>
                    </div>
                  </div>
                  <div
                    class="progress my-3 bg-light-transparent"
                    style="height: 3px"
                  >
                    <div
                      class="progress-bar bg-white"
                      role="progressbar"
                      style="width: 55%"
                      aria-valuenow="25"
                      aria-valuemin="0"
                      aria-valuemax="100"
                    ></div>
                  </div>
                  <div class="d-flex align-items-center text-white">
                    <p class="mb-0">Empleados</p>
                    <p class="mb-0 ms-auto">
                      +2.2%<span><i class="bx bx-up-arrow-alt"></i></span>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="card radius-10 mb-5 box-shadow">
            <div class="bg-light p-5 rounded-lg m-3">
              <h1 class="display-4">Bienvenido ${ e.nombre } ${ e.apellido }</h1>
              <p class="lead">
                Usted se encuentra disponible para comenzar a realizar ventas en
                el restaurante PATITO
              </p>
              <hr class="my-4" />
              <p>
                Acerquese al catalogo para seleccionar los productos que el
                cliente va a solicitar en su pedido
              </p>
              <a
                class="btn btn-primary btn-lg"
                href="ps?opcion=catalogo"
                role="button"
                >Ir al Catalogo</a
              >
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
  </body>
</html>
