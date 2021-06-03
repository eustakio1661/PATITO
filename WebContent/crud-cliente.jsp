<%@ taglib  uri="/WEB-INF/libreria.tld" prefix="tools"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="reusable/styles.jsp"></jsp:include>
    <title>Registrar Cliente</title>
  </head>
  <body>
    <div class="wrapper">
      <jsp:include page="components/sidebar.jsp"></jsp:include>
      <jsp:include page="components/header.jsp"></jsp:include>
      <div class="page-wrapper">
        <div class="page-content">
          <jsp:include page="components/breadcrumb.jsp">
            <jsp:param name="pagina" value="Cliente"/>
            <jsp:param name="accion" value="Registrar"/>
          </jsp:include>
          <div class="row">
            <div class="col-xl-9 mx-auto">
              <hr />
              <form
                action="#"
                class="needs-validation"
                method="POST"
                novalidate
              >
                <div class="card border-top border-0 border-4 border-primary">
                  <div class="card-body">
                    <div class="border p-4 rounded">
                      <div class="card-title d-flex align-items-center">
                        <div>
                          <i class="bx bxs-user me-1 font-22 text-primary"></i>
                        </div>
                        <h5 class="mb-0 text-primary">Registro de Clientes</h5>
                      </div>
                      <hr />
                      <div class="row mb-3">
                        <label
                          for="txtNombreCliente"
                          class="col-sm-3 col-form-label"
                          >Ingrese Nombre:
                        </label>
                        <div class="col-sm-9">
                          <input
                            type="text"
                            class="form-control"
                            id="txtNombreCliente"
                            name="txtNombreCliente"
                            placeholder="Nombre del cliente"
                            value="${ clienteEncontrado.nombre }"
                          />
                          <div class="invalid-feedback">
                            Ingrese un nombre v&aacute;lido
                          </div>
                        </div>
                      </div>
                      <div class="row mb-3">
                        <label
                          for="txtApellidoCliente"
                          class="col-sm-3 col-form-label"
                          >Ingrese Apellido:
                        </label>
                        <div class="col-sm-9">
                          <input
                            type="text"
                            class="form-control"
                            id="txtApellidoCliente"
                            name="txtApellidoCliente"
                            placeholder="Apellido del cliente"
                            value="${ clienteEncontrado.apellido }"
                          />
                          <div class="invalid-feedback">
                            Ingrese un apellido v&aacute;lido
                          </div>
                        </div>
                      </div>
                      <div class="row mb-3">
                        <label
                          for="txtTelefonoCliente"
                          class="col-sm-3 col-form-label"
                          >Número tel&eacute;fonico:
                        </label>
                        <div class="col-sm-9">
                          <input
                            type="text"
                            class="form-control"
                            id="txtTelefonoCliente"
                            name="txtTelefonoCliente"
                            placeholder="Celular o fijo"
                            value="${ clienteEncontrado.telefono }"
                          />
                          <div class="invalid-feedback">
                            Ingrese un número tel&eacute;fonico válido
                          </div>
                        </div>
                      </div>
                      <div class="row mb-3">
                        <label
                          for="txtDNICliente"
                          class="col-sm-3 col-form-label"
                          >DNI :</label
                        >
                        <div class="col-sm-9">
                          <input
                            type="email"
                            class="form-control"
                            id="txtDNICliente"
                            name="txtDNICliente"
                            placeholder="Dni del cliente"
                            value="${ clienteEncontrado.dni }"
                          />
                          <div class="invalid-feedback">
                            Ingrese un DNI v&aacute;lido
                          </div>
                        </div>
                      </div>
                      <div class="row mb-3">
                        <label
                          for="cboDistritoCliente"
                          class="col-sm-3 col-form-label"
                          >Distrito :</label
                        >
                        <div class="col-sm-9">
                          <select
                            id="cboDistritoCliente"
                            name="cboDistritoCliente"
                            class="form-select"
                            aria-label="Distrito del Cliente"
                            required
                          >
                            <tools:comboDistrito idDistrito="${ clienteEncontrado.codigoDistrito }"/>
                          </select>
                          <div class="invalid-feedback">
                            Seleccione un distrito
                          </div>
                        </div>
                      </div>
                      <div class="row mb-3">
                        <label
                          for="txtDireccionCliente"
                          class="col-sm-3 col-form-label"
                          >Direcci&oacute;n :</label
                        >
                        <div class="col-sm-9">
                          <textarea
                            class="form-control"
                            id="txtDireccionCliente"
                            rows="3"
                            placeholder="Direcci&oacute;n completa del cliente"
                          >${ clienteEncontrado.direccion }</textarea>
                          <div class="invalid-feedback">
                            El campo direcci&oacute;n es obligatorio
                          </div>
                        </div>
                      </div>
                      <div class="row">
                        <label class="col-sm-3 col-form-label"></label>
                        <div class="col-sm-9">
                          <button type="submit" class="btn btn-primary px-5">
                            Registrar Cliente
                          </button>
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
  </body>
</html>
