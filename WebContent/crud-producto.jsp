<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <jsp:include page="reusable/styles.jsp"></jsp:include>
    <title>Registrar Producto</title>
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
            <jsp:param name="pagina" value="Producto" />
            <jsp:param name="accion" value="Registrar" />
          </jsp:include>
          <div class="row">
            <div class="col-xl-7 mx-auto">
              <hr />
              <div class="card border-top border-0 border-4 border-primary">
                <div class="card-body p-5">
                  <div class="card-title d-flex align-items-center">
                    <div>
                      <i class="bx bx-package me-1 font-22 text-primary"></i>
                    </div>
                    <h5 class="mb-0 text-primary">Registro de Productos</h5>
                  </div>
                  <hr />
                  <form class="row g-3 needs-validation" action="#" method="POST" novalidate>
                    <div class="col-12">
                      <label for="txtDescripcionProd" class="form-label"
                        >Descripción :
                      </label>
                      <input
                        type="text"
                        class="form-control"
                        id="txtDescripcionProd"
                        name="txtDescripcionProd"
                        placeholder="Ingrese descripción"
                        minlength="5"
                        required
                      />
                      <div class="invalid-feedback">
                         Ingrese una descripción válida
                      </div>
                    </div>
                    <div class="col-12">
                      <label for="cboCategoriaProd" class="form-label"
                        >Categoría :</label
                      >
                      <select id="cboCategoriaProd" class="form-select">
                        <option selected>Seleccione categoría</option>
                        <option>...</option>
                      </select>
                      <div class="invalid-feedback">
                         Seleccione un categoría válido
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label for="txtPrecioProd" class="form-label"
                        >Precio Unitario :</label
                      >
                      <input
                        type="number"
                        class="form-control"
                        id="txtPrecioProd"
                        name="txtPrecioProd"
                        placeholder="0.00"
                        required
                      />
                      <div class="invalid-feedback">
                         Ingrese un precio válido
                      </div>
                    </div>
                    <div class="col-md-6">
                      <label for="txtStockProd" class="form-label"
                        >Stock :</label
                      >
                      <input
                        type="number"
                        class="form-control"
                        id="txtStockProd"
                        name="txtStockProd"
                        placeholder="0"
                        required
                      />
                      <div class="invalid-feedback">
                         El stock debe ser válido y mayor a 0
                      </div>
                    </div>                   
                    <div class="col-12">
                      <button type="submit" class="btn btn-primary px-5">
                        Registrar Producto
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