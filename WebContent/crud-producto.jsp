<%@page import="beans.ProductoDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  uri="/WEB-INF/libreria.tld" prefix="tools"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
  ProductoDTO existeProducto = (ProductoDTO) request.getAttribute("productoEncontrado");
  String action = "ps?opcion=registrar";
  String typeColor = "primary";
  String opcion = "Registrar";
  String tituloForm = "Registro de Productos";
  String entidad = "producto";
           
  if (existeProducto != null) {
      action = "ps?opcion=actualizar";
      typeColor = "success";
      opcion="Actualizar";
      tituloForm = "Actualizar un producto";
    } 
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="reusable/styles.jsp"></jsp:include>
    <title><%=opcion%> Producto</title>
  </head>
  <body>
    <div class="wrapper">
      <jsp:include page="components/sidebar.jsp"></jsp:include>
      <jsp:include page="components/header.jsp"></jsp:include>
      <div class="page-wrapper">
        <div class="page-content">
          <jsp:include page="components/breadcrumb.jsp">
            <jsp:param name="pagina" value="Producto" />
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
                        class="bx bx-package me-1 font-22 text-<%=typeColor%>"
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
                    <div class="col-xs-6 col-md-4">
                      <figure class="snip1515">
                        <div class="profile-image">
                          <c:if test="${ productoEncontrado.imagen == null }">
                            <img
                              class="img-upload"
                              src="https://cutt.ly/unbQLrJ"
                              alt="Imagen Producto"
                            />
                          </c:if>
                          <c:if test="${ productoEncontrado.imagen != null }">
                            <img
                              class="img-upload"
                              src="${ productoEncontrado.imagen }"
                              alt="Imagen Producto"
                            />
                          </c:if>                          
                          <input
                            id="input-file"
                            type="file"                            
                            style="display: none"
                            data-imgurl="${ productoEncontrado.imagen }"
                          />
                        </div>
                        <figcaption>
                          <button id="select-img" type="button" class="btn btn-warning">
                            Subir Imagen
                          </button>
                          <button id="remove-img" type="button" class="btn btn-secondary">
                            <i class="bx bxs-x-square mx-0"></i>
                          </button>
                        </figcaption>
                      </figure>
                    </div>

                    <div class="col-xs-6 col-md-8">
                      <div class="row">
                        <input
                          type="hidden"
                          class="input-hidden"
                          id="input-hidden"
                          name="txtCodigoProd"
                          data-entidad="<%=entidad %>"
                          value="${ productoEncontrado.idProducto }"
                        />
                        <div class="col-12 mb-3">
                          <label for="txtDescripcionProd" class="form-label"
                            >Descripci&oacute;n :
                          </label>
                          <input
                            type="text"
                            class="form-control"
                            id="txtDescripcionProd"
                            name="txtDescripcionProd"
                            placeholder="Ingrese descripci&oacute;n"
                            value="${ productoEncontrado.descripcion }"
                            minlength="5"
                            required
                          />
                          <div class="invalid-feedback">
                            Ingrese una descripci&oacute;n v&aacute;lida
                          </div>
                        </div>
                        <div class="col-12 mb-3">
                          <label for="cboCategoriaProd" class="form-label"
                            >Categor&iacute;a :</label
                          >
                          <select
                            id="cboCategoriaProd"
                            class="form-select"
                            name="cboCategoriaProd"
                            required
                          >
                            <tools:comboCategoria
                              idCategoria="${ productoEncontrado.idCategoria }"
                            />
                          </select>
                          <div class="invalid-feedback">
                            Seleccione una categor&iacute;a v&aacute;lida
                          </div>
                        </div>
                        <div class="col-6 mb-3">
                          <label for="txtPrecioProd" class="form-label"
                            >Precio Unitario :</label
                          >
                          <input
                            type="number"
                            class="form-control"
                            id="txtPrecioProd"
                            name="txtPrecioProd"
                            placeholder="0.00"
                            value="${ productoEncontrado.precio }"
                            min="0"
                            step="any"
                            required
                          />
                          <div class="invalid-feedback">
                            Ingrese un precio v&aacute;lido
                          </div>
                        </div>
                        <div class="col-6 mb-3">
                          <label for="txtStockProd" class="form-label"
                            >Stock :</label
                          >
                          <input
                            type="number"
                            class="form-control"
                            id="txtStockProd"
                            name="txtStockProd"
                            placeholder="0"
                            value="${ productoEncontrado.cantidad }"
                            min="0"
                            step="1"
                            required
                          />
                          <div class="invalid-feedback">
                            El stock debe ser v&aacute;lido y mayor a 0
                          </div>
                        </div>
                        <div class="col-12">
                          <button
                            type="submit"
                            class="btn btn-<%=typeColor%> px-5"
                          >
                            <%=opcion%> Producto
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
    