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
					<jsp:param name="pagina" value="Perfil" />
					<jsp:param name="accion" value="Actualizar datos" />
				</jsp:include>

				<div class="container">
					<div class="main-body">
						<form action="emse" method="POST" class="needs-validation"
							novalidate>
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
                          ">
												<img src="${e.imagen }" alt="Admin"
													class="rounded-circle p-1 bg-primary" width="110" name="ImgEmpleado"/>
												<div class="mt-3">
													<h4>${e.nombre }</h4>
													<p class="text-secondary mb-1">
														${e.descripcionTipoEmpleado }</p>
													<p class="text-muted font-size-sm">${e.direccion }</p>
													<button type="button" class="btn btn-outline-warning">
														Actualizar fotografía</button>
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
												<div class="col-sm-3">
													<input type="hidden" class="input-hidden" id="input-hidden"
														name="txtIdEmpleado" value="${ e.id }" />
													<label for="txtNombreEmpleado" class="mb-0">Nombre</label>
												</div>
												<div class="col-sm-9 text-secondary">
													<input id="txtNombreEmpleado" name="txtNombreEmpleado"
														type="text" class="form-control" value="${e.nombre }"
														placeholder="Ingrese nombre" required />
													<div class="invalid-feedback">Ingrese un nombre
														válido</div>
												</div>
											</div>
											<div class="row mb-3">
												<div class="col-sm-3">
													<label for="txtApellidoEmpleado" class="mb-0">Apellido</label>
												</div>
												<div class="col-sm-9 text-secondary">
													<input id="txtApellidoEmpleado" name="txtApellidoEmpleado"
														type="text" class="form-control" value="${e.apellido }"
														placeholder="Ingrese apellido" required />
													<div class="invalid-feedback">Ingrese un apellido
														válido</div>
												</div>
											</div>
											<div class="row mb-3">
												<div class="col-sm-3">
													<label for="txtTelefonoEmpleado" class="mb-0">Teléfono</label>
												</div>
												<div class="col-sm-9 text-secondary">
													<input id="txtTelefonoEmpleado" name="txtTelefonoEmpleado"
														type="text" class="form-control" value="${e.telefono }"
														placeholder="Fijo o celular" required />
													<div class="invalid-feedback">Ingrese un teléfono
														válido</div>
												</div>
											</div>
											<div class="row mb-3">
												<div class="col-sm-3">
													<label for="txtDireccionEmpleado" class="mb-0">Dirección</label>
												</div>
												<div class="col-sm-9 text-secondary">
													<textarea class="form-control" id="txtDireccionEmpleado"
														name="txtDireccionEmpleado"
														placeholder="Ingrese dirección" rows="2" minlength="0">${e.direccion }</textarea>
												</div>
											</div>
											<div class="row mb-3">
												<div class="col-sm-3">
													<label for="txtEmailEmpleado" class="mb-0">Email</label>
												</div>
												<div class="col-sm-9 text-secondary">
													<input id="txtEmailEmpleado" name="txtEmailEmpleado"
														type="text" class="form-control" value="${e.correo }"
														placeholder="Ingrese email" />
													<div class="invalid-feedback">Ingrese un email válido
													</div>
												</div>
											</div>
											<div class="row mb-3">
												<div class="col-sm-3">
													<label for="txtClaveEmpleado" class="mb-0">Contraseña</label>
												</div>
												<div class="col-sm-9 text-secondary">
													<div class="input-group mb-3" id="show_hide_password">
														<input type="password" class="form-control"
															id="txtClaveEmpleado" name="txtClaveEmpleado"
															placeholder="Ingrese contraseña"
															aria-label="Ingrese contraseña"
															aria-describedby="icon-hide" minlength="6" maxlength="30"
															required value="${e.clave }" />
														<a href="#" class="input-group-text bg-transparent"
															id="icon-hide"><i
															class="bx bx-hide"></i></a>
													</div>
													<div class="invalid-feedback">Ingrese una contraseña
														válido +6</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-3"></div>
												<div class="col-sm-9 text-secondary">
													<button type="submit" name="opcion" value="perfil"
														class="btn btn-primary px-4">Actualizar perfil</button>
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
		<!--end page wrapper -->
		<!--start overlay-->
		<div class="overlay toggle-icon"></div>
		<!--end overlay-->
		<!--Start Back To Top Button-->
		<a href="javaScript:;" class="back-to-top"><i
			class="bx bxs-up-arrow-alt"></i></a>
		<!--End Back To Top Button-->
		<jsp:include page="components/footer.jsp"></jsp:include>
	</div>
	<!--end wrapper-->

	<jsp:include page="reusable/scripts.jsp"></jsp:include>

	<script>
		(function() {
			'use strict';
			var forms = document.querySelectorAll('.needs-validation');

			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault();
						event.stopPropagation();
					}

					form.classList.add('was-validated');
				}, false);
			});
		})();

		$(document).ready(
				function() {
					$('#show_hide_password a')
							.on(
									'click',
									function(event) {
										event.preventDefault();
										if ($('#show_hide_password input')
												.attr('type') == 'text') {
											$('#show_hide_password input')
													.attr('type', 'password');
											$('#show_hide_password i')
													.addClass('bx-hide');
											$('#show_hide_password i')
													.removeClass('bx-show');
										} else if ($(
												'#show_hide_password input')
												.attr('type') == 'password') {
											$('#show_hide_password input')
													.attr('type', 'text');
											$('#show_hide_password i')
													.removeClass('bx-hide');
											$('#show_hide_password i')
													.addClass('bx-show');
										}
									});
				});
	</script>
</body>
</html>
