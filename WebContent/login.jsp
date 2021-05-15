<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <jsp:include page="reusable/styles.jsp"></jsp:include>
    <title>Iniciar sesión</title>
  </head>

  <body class="bg-login">
    <!--wrapper-->
    <div class="wrapper">
      <div
        class="
          section-authentication-signin
          d-flex
          align-items-center
          justify-content-center
          my-5 my-lg-0
        "
      >
        <div class="container-fluid">
          <div class="row row-cols-1 row-cols-lg-2 row-cols-xl-3">
            <div class="col mx-auto">
              <div class="card">
                <div class="card-body">
                  <div class="border p-4 rounded">
                    <div class="text-center">
                      <h3 class="">Iniciar Sesión</h3>
                      <p>Bienvenido usuario, inicie sesión para comenzar</p>
                    </div>
                    <div class="d-grid">
                      <!-- Inserte imagen del pato -->
                    </div>
                    <div class="login-separater text-center mb-4">
                      <span>Ingrese correo y contraseña</span>
                      <hr />
                    </div>
                    <div class="form-body">
                      <form
                        class="row g-3 needs-validation"
                        action="#"
                        method="POST"
                        novalidate
                      >
                        <div class="col-12">
                          <label for="txtCorreo" class="form-label"
                            >Correo</label
                          >
                          <input
                            type="text"
                            class="form-control"
                            id="txtCorreo"
                            name="txtCorreo"
                            placeholder="example@example.com"
                            pattern="^[\w]{1,}[\w.+-]{0,}@[\w-]{1,}([.][a-zA-Z]{2,}|[.][\w-]{2,}[.][a-zA-Z]{2,4})$"
                            required
                          />
                          <div class="invalid-feedback">
                            Ingrese un correo válido
                          </div>
                        </div>
                        <div class="col-12">
                          <label for="txtClave" class="form-label"
                            >Contraseña</label
                          >
                          <div class="input-group mb-3" id="show_hide_password">
                            <input
                              type="password"
                              class="form-control"
                              id="txtClave"
                              name="txtClave"
                              placeholder="Ingrese contraseña"
                              aria-label="Ingrese contraseña"
                              aria-describedby="icon-hide"
                              minlength="6"
                              maxlength="30"
                              required
                            />                            
                              <a
                                href="#"
                                class="input-group-text bg-transparent"
                                id="icon-hide"
                                ><i class="bx bx-hide"></i
                              ></a>
                            <div class="invalid-feedback">
                              Se requiere de una clave +6
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-check form-switch">
                            <input
                              class="form-check-input"
                              type="checkbox"
                              id="chkRecordarClave"
                              checked
                            />
                            <label
                              class="form-check-label"
                              for="chkRecordarClave"
                              >Recordar contraseña</label
                            >
                          </div>
                        </div>
                        <div class="col-md-6 text-end">
                          <a href="authentication-forgot-password.html"
                            >¿ Olvidaste tu contraseña ?</a
                          >
                        </div>
                        <div class="col-12">
                          <div class="d-grid">
                            <button type="submit" class="btn btn-primary">
                              <i class="bx bxs-lock-open"></i>Iniciar sesión
                            </button>
                          </div>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!--end row-->
        </div>
      </div>
    </div>
    <!--end wrapper-->

    <jsp:include page="reusable/scripts.jsp"></jsp:include>

    <script>
      (function () {
        'use strict';
        var forms = document.querySelectorAll('.needs-validation');

        Array.prototype.slice.call(forms).forEach(function (form) {
          form.addEventListener(
            'submit',
            function (event) {
              if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
              }

              form.classList.add('was-validated');
            },
            false
          );
        });
      })();

      $(document).ready(function () {
        $('#show_hide_password a').on('click', function (event) {
          event.preventDefault();
          if ($('#show_hide_password input').attr('type') == 'text') {
            $('#show_hide_password input').attr('type', 'password');
            $('#show_hide_password i').addClass('bx-hide');
            $('#show_hide_password i').removeClass('bx-show');
          } else if (
            $('#show_hide_password input').attr('type') == 'password'
          ) {
            $('#show_hide_password input').attr('type', 'text');
            $('#show_hide_password i').removeClass('bx-hide');
            $('#show_hide_password i').addClass('bx-show');
          }
        });
      });
    </script>
  </body>
</html>
    