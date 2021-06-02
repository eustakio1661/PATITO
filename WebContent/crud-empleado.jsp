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
    <%@ taglib uri="/WEB-INF/libreria.tld" prefix="tools"%>
    <title>Registrar Empleado</title>
  </head>
  <body>
    <!--wrapper-->
    <div class="wrapper">
      <!--sidebar wrapper -->
      <jsp:include page="components/sidebar.jsp"></jsp:include>
      <!--start header -->
      <jsp:include page="components/header.jsp"></jsp:include>
      <!--start page wrapper -->
      <div class="page-wrapper">
        <div class="page-content">
          <jsp:include page="components/breadcrumb.jsp">
            <jsp:param name="pagina" value="Empleado" />
            <jsp:param name="accion" value="Registrar" />
          </jsp:include>
          <div class="row">
            <div class="col-xl-9 mx-auto">
              <hr />
              <div class="card border-top border-0 border-4 border-primary">
                <div class="card-body p-5">
                  <div class="card-title d-flex align-items-center">
                    <div>
                      <i class="bx bxs-user me-1 font-22 text-primary"></i>
                    </div>
                    <h5 class="mb-0 text-primary">Registro de Empleados</h5>
                  </div>
                  <hr />
                  <form class="row g-3 needs-validation" action="emse" method="POST" novalidate>
                    <div class="col-12">
                      <label for="txtDNIEmpleado" class="form-label"
                        >DNI :
                      </label>
                      <input
                        type="text"
                        class="form-control"
                        id="txtDNIEmpleado"
                        name="txtDNIEmpleado"
                        placeholder="Ingrese DNI"
                        required
                        value=""
                      />
                      <div class="invalid-feedback">
                         Ingrese un DNI v&aacute;lido
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label for="txtNombreEmpleado" class="form-label"
                        >Nombre :</label
                      >
                      <input
                        type="email"
                        class="form-control"
                        id="txtNombreEmpleado"
                        name="txtNombreEmpleado"
                        placeholder="Ingrese nombre"
                        required
                      />
                      <div class="invalid-feedback">
                         Ingrese un nombre v&aacute;lido
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label for="txtApellidoEmpleado" class="form-label"
                        >Apellido :</label
                      >
                      <input
                        type="text"
                        class="form-control"
                        id="txtApellidoEmpleado"
                        name="txtApellidoEmpleado"
                        placeholder="Ingrese apellido"
                        required
                      />
                      <div class="invalid-feedback">
                         Ingrese un apellido v&aacute;lido
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label for="txtTelefonoEmpleado" class="form-label"
                        >Tel&eacute;fono</label
                      >
                      <input
                        type="text"
                        class="form-control"
                        id="txtTelefonoEmpleado"
                        name="txtTelefonoEmpleado"
                        placeholder="Fijo o celular"
                        required
                      />
                      <div class="invalid-feedback">
                         Ingrese un tel&eacute;fono v&aacute;lido
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label for="cboTipoEmpleado" class="form-label"
                        >Tipo :</label
                      >
                      <select id="cboTipoEmpleado" class="form-select" name="cboTipoEmpleado">
                        <tools:comboTipoEmp/>
                      </select>
                      <div class="invalid-feedback">
                         Seleccione un tipo v&aacute;lido
                      </div>
                    </div>
                    <div class="col-12">
                      <label for="txtDireccionEmpleado" class="form-label"
                        >Direcci&oacute;n :
                      </label>
                      <textarea
                        class="form-control"
                        id="txtDireccionEmpleado"
                        name="txtDireccionEmpleado"
                        placeholder="Ingrese direcci&oacute;n"
                        rows="3"
                        minlength="0"
                      ></textarea>
                    </div>                    
                    <div class="col-12">
                      <button type="submit"   name="opcion" value="registro" class="btn btn-primary px-5">
                        Registrar Empleado
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--end page wrapper -->
      <!--start overlay-->
      <div class="overlay toggle-icon"></div>
      <!--end overlay-->
      <!--Start Back To Top Button-->
      <a href="javaScript:;" class="back-to-top"
        ><i class="bx bxs-up-arrow-alt"></i
      ></a>
      <!--End Back To Top Button-->
      <jsp:include page="components/footer.jsp"></jsp:include>
    </div>
    <!--end wrapper-->

    <jsp:include page="reusable/scripts.jsp"></jsp:include>
  </body>
</html>
