<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="reusable/styles.jsp"></jsp:include>
    <title>Perfil</title>
  </head>
  <body>
    <div class="wrapper">
      <jsp:include page="components/sidebar.jsp"></jsp:include>
      <jsp:include page="components/header.jsp"></jsp:include>
      <div class="page-wrapper">
        <div class="page-content">
          <jsp:include page="components/breadcrumb.jsp">
            <jsp:param name="pagina" value="Perfil" />
            <jsp:param name="accion" value="Actualizar datos" />
          </jsp:include>

          <div class="container">
            <div class="main-body">
              <form
                action="emse?opcion=perfil"
                method="POST"
                class="needs-validation"
                novalidate
              >
                <div class="row">
                  <div class="col-lg-4 mt-3">
                    <div class="card">
                      <div class="card-body">
                        <div
                          class="
                            d-flex
                            flex-column
                            align-items-center
                            text-center
                          "
                        >                        
                        <c:if test="${e.imagen == null }">
                          <img
                            src="https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png"
                            alt="${e.usuario }"
                            class="rounded-circle p-1 bg-primary img-upload"
                            width="110"
                            height="110"
                          />
                        </c:if>
                        <c:if test="${e.imagen != null }">
                          <img
                            src="${e.imagen }"
                            alt="${e.usuario }"
                            class="rounded-circle p-1 bg-primary img-upload"
                            width="110"
                            height="110"
                          />
                        </c:if>
                        <input
                            id="input-file"
                            type="file"                            
                            style="display: none"
                            data-imgurl="${e.imagen }"
                          />                        
                          <div class="mt-3">
                            <h4>${e.nombre }</h4>
                            <p class="text-secondary mb-1">
                              ${e.usuario }
                            </p>
                            <p class="text-muted font-size-sm">
                              ${e.direccion }
                            </p>
                            
                            <div>
                              <button
                              id="select-img"
                              type="button"
                              class="btn btn-outline-warning mx-1"
                            >
                              Subir
                            </button>
                            <button id="remove-img" type="button" class="btn btn-outline-danger mx-1">
                              <i class='bx bx-minus-circle mx-0' ></i>
                            </button>
                            </div>
                            
                            
                          </div>
                        </div>
                        <hr class="my-4" />
                      </div>
                    </div>
                  </div>

                  <div class="col-lg-8 mt-3">
                    <div class="card">
                      <div class="card-body">
                        <div class="row mb-3">
                           <input
                              type="hidden"
                              class="input-hidden"
                              id="input-hidden"
                              name="txtIdEmpleado"
                              value="${ e.id }"
                            />
                          <div class="col-sm-3">
                          <label for="txtNombreEmpleado" class="mb-0"
                              >Nombre:</label
                            >                            
                          </div>
                          <div class="col-sm-9 text-secondary">                          
                            <input
                              id="txtNombreEmpleado"
                              name="txtNombreEmpleado"
                              type="text"
                              class="form-control"
                              value="${ e.nombre }"
                              placeholder="Ingrese nombre"
                              required
                            />
                            <div class="invalid-feedback">
                              Ingrese un nombre v&aacute;lido
                            </div>
                          </div>
                        </div>
                        <div class="row mb-3">
                          <div class="col-sm-3">
                            <label for="txtApellidoEmpleado" class="mb-0"
                              >Apellido</label
                            >
                          </div>
                          <div class="col-sm-9 text-secondary">
                            <input
                              id="txtApellidoEmpleado"
                              name="txtApellidoEmpleado"
                              type="text"
                              class="form-control"
                              value="${e.apellido }"
                              placeholder="Ingrese apellido"
                              required
                            />
                            <div class="invalid-feedback">
                              Ingrese un apellido v&aacute;lido
                            </div>
                          </div>
                        </div>
                        <div class="row mb-3">
                          <div class="col-sm-3">
                            <label for="txtTelefonoEmpleado" class="mb-0"
                              >Tel&eacute;fono</label
                            >
                          </div>
                          <div class="col-sm-9 text-secondary">
                            <input
                              id="txtTelefonoEmpleado"
                              name="txtTelefonoEmpleado"
                              type="text"
                              class="form-control"
                              value="${e.telefono }"
                              placeholder="Fijo o celular"
                              required
                            />
                            <div class="invalid-feedback">
                              Ingrese un tel&eacute;fono v&aacute;lido
                            </div>
                          </div>
                        </div>
                        <div class="row mb-3">
                          <div class="col-sm-3">
                            <label for="txtDireccionEmpleado" class="mb-0"
                              >Direcci&oacute;n</label
                            >
                          </div>
                          <div class="col-sm-9 text-secondary">
                            <textarea
                              class="form-control"
                              id="txtDireccionEmpleado"
                              name="txtDireccionEmpleado"
                              placeholder="Ingrese direcci&oacute;n"
                              required
                              rows="2"
                              minlength="3"
                            >${e.direccion }</textarea>
                            <div class="invalid-feedback">
                              Ingrese una direcci&oacute;n v&aacute;lida
                            </div>
                          </div>
                        </div>
                        <div class="row mb-3">
                          <div class="col-sm-3">
                            <label for="txtEmailEmpleado" class="mb-0"
                              >Email</label
                            >
                          </div>
                          <div class="col-sm-9 text-secondary">
                            <input
                              id="txtEmailEmpleado"
                              name="txtEmailEmpleado"                              
                              type="text"
                              class="form-control"
                              value="${e.correo }"
                              placeholder="Ingrese email"
                              required
                            />
                            <div class="invalid-feedback">
                              Ingrese un email v&aacute;lido
                            </div>
                          </div>
                        </div>
                        <div class="row mb-3">
                          <div class="col-sm-3">
                            <label for="txtClaveEmpleado" class="mb-0"
                              >Contrase&ntilde;a</label
                            >
                          </div>
                          <div class="col-sm-9 text-secondary">
                            <div
                              class="input-group mb-3"
                              id="show_hide_password"
                            >
                              <input
                                type="password"
                                class="form-control"
                                id="txtClaveEmpleado"
                                name="txtClaveEmpleado"
                                placeholder="Ingrese contrase&ntilde;a"
                                aria-label="Ingrese contrase&ntilde;a"
                                aria-describedby="icon-hide"
                                minlength="6"
                                maxlength="30"
                                required
                                value="${e.clave }"
                              />
                              <a
                                href="#"
                                class="input-group-text bg-transparent"
                                id="icon-hide"
                                ><i class="bx bx-hide"></i
                              ></a>
                            </div>
                            <div class="invalid-feedback">
                              Ingrese una contrase&ntilde;a v&aacute;lida +6
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-sm-3"></div>
                          <div class="col-sm-9 text-secondary">
                            <button
                              type="submit"
                              class="btn btn-primary px-4"
                            >
                              Actualizar perfil
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </form>
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
    <script src="js/perfil.js"></script>
  </body>
</html>
