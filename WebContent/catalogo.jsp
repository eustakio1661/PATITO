<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<jsp:include page="reusable/styles.jsp"></jsp:include>
<link
	href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap5.min.css"
	rel="stylesheet" />
<title>Catalogo</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="components/sidebar.jsp"></jsp:include>
		<jsp:include page="components/header.jsp"></jsp:include>
		<div class="page-wrapper">
			<div class="page-content">
				<jsp:include page="components/breadcrumb.jsp">
					<jsp:param name="pagina" value="Dashboard" />
					<jsp:param name="accion" value="Catalogo" />
				</jsp:include>
				<div class="row">
					<div class="col mx-auto">
						<hr />
						<div class="card radius-10 mb-5 box-shadow">
							<div class="card-body">
								<div class="d-flex align-items-center">
									<div>
										<h5 class="mb-0">Clientes</h5>
									</div>
									<div class="font-22 ms-auto">
										<i class="bx bx-dots-horizontal-rounded"></i>
									</div>
								</div>
								<hr />
								<div class="table-responsive text-nowrap">
									<table id="clienteTable" class="table align-middle mb-0">
										<thead class="table-light">
											<tr>
												<th>Número Cliente</th>
												<th>DNI</th>
												<th>Nombre</th>
												<th>Apellido</th>
												<th>Acciones</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${lstClientes}" var="cli">
												<tr>
													<td>${cli.codigo}</td>
													<td>${cli.dni }</td>
													<td>${cli.nombre }</td>
													<td>${cli.apellido }</td>

													<td>
														<div class="d-flex order-actions">
															<a href="venser?opcion=cargCliente&codigo=${cli.codigo}"
																class="btn btn-success" data-bs-toggle="tooltip"
																data-bs-placement="bottom" title="Agregar Cliente">
																<i class="bx bx-edit mx-0"></i>
															</a>
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
						</div>

						<div class="container">
							<form class="row g-2 needs-validation" action="venser"
								method="post" novalidate>


								<div class="col-xs-6">
									<div class="row">
										<div class="col-xs-6">
											<figure class="snip1515">
												<div class="profile-image">
													<img
														src="https://img-global.cpcdn.com/recipes/3a2a655a983484d7/680x482cq70/carapulcra-con-sopa-seca-foto-principal.webp"
														alt="sample47" />

												</div>

											</figure>
										</div>
										<div class="col-12 mb-3">
											<label for="txtDescripcionProd" class="form-label">ID
												: </label> <input type="text" class="form-control" id="txtIdProd"
												name="txtIdProd" value="${productoEncontrado.idProducto }"
												readonly="readonly" />

										</div>
										<div class="col-12 mb-3">
											<label for="txtDescripcionProd" class="form-label">Descripci&oacute;n
												: </label> <input type="text" class="form-control"
												id="txtDescripcionProd" name="txtDescripcionProd"
												value="${productoEncontrado.descripcion }"
												readonly="readonly" />

										</div>

										<div class="col-6 mb-3">
											<label for="txtPrecioProd" class="form-label">Precio
												Unitario :</label> <input type="number" class="form-control"
												id="txtPrecioProd" name="txtPrecioProd"
												value="${productoEncontrado.precio }" readonly="readonly" />

										</div>
										<div class="col-6 mb-3">
											<label for="txtStockProd" class="form-label">Cantidad
												:</label> <input type="number" class="form-control" id="txtCantidad"
												name="txtCantidad" value="1" min="1" step="1" required />

										</div>
										<div class="col-12">
											<div class="modal-footer">
												<button class="btn btn-primary" name="opcion"
													value="agrCompra">Agregar Compra</button>


											</div>

										</div>
									</div>
								</div>
							</form>
						</div>

						<div class="card radius-10 mb-5 box-shadow">
							<div class="card-body">
								<div class="d-flex align-items-center">
									<div>
										<h5 class="mb-0">Menus</h5>
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
												<th>Imagen</th>
												<th>Descripci&oacute;n</th>
												<th>Categoria</th>
												<th>Precio</th>
												<th>Stock</th>
												<th>Acciones</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${lstProductos}" var="prod">
												<tr>
													<td>${prod.idProducto}</td>
													<td><img alt="" src="${prod.imagen}" width="50px"
														height="50px"></td>
													<td>${prod.descripcion}</td>
													<td>${prod.descCategoria}</td>
													<td><fmt:setLocale value="es_PE" /> <fmt:formatNumber
															type="currency" value="${prod.precio}" /></td>

													<td>${prod.cantidad}</td>
													<td>
														<div class="d-flex order-actions">
															<a class="btn btn-success" data-bs-toggle="modal"
																data-bs-target="#exampleModal"
																data-bs-placement="bottom" title="Ver Producto"> <i
																class="bx bx-edit mx-0"></i>
															</a>
															<div class="modal fade" id="exampleModal" tabindex="-1"
																aria-labelledby="exampleModalLabel" aria-hidden="true">
																<div class="modal-dialog modal-dialog-centered">
																	<div class="modal-content">
																		<div class="modal-header">
																			<h5 class="modal-title" id="exampleModalLabel">Categoria</h5>
																			<button type="button" class="btn-close"
																				data-bs-dismiss="modal" aria-label="Close"></button>
																		</div>
																		<div class="container">
																			<form class="row g-2 needs-validation" action=""
																				method="POST" novalidate>


																				<div class="col-xs-6">
																					<div class="row">
																						<div class="col-xs-6">
																							<figure class="snip1515">
																								<div class="profile-image">
																									<img
																										src="https://img-global.cpcdn.com/recipes/3a2a655a983484d7/680x482cq70/carapulcra-con-sopa-seca-foto-principal.webp"
																										alt="sample47" />

																								</div>

																							</figure>
																						</div>
																						<div class="col-12 mb-3">
																							<label for="txtDescripcionProd"
																								class="form-label">ID : </label> <input
																								type="text" class="form-control" id="txtIdProd"
																								name="txtIdProd" value="" readonly="readonly" />

																						</div>
																						<div class="col-12 mb-3">
																							<label for="txtDescripcionProd"
																								class="form-label">Descripci&oacute;n :
																							</label> <input type="text" class="form-control"
																								id="txtDescripcionProd"
																								name="txtDescripcionProd" value=""
																								readonly="readonly" />

																						</div>

																						<div class="col-6 mb-3">
																							<label for="txtPrecioProd" class="form-label">Precio
																								Unitario :</label> <input type="number"
																								class="form-control" id="txtPrecioProd"
																								name="txtPrecioProd" value=""
																								readonly="readonly" />

																						</div>
																						<div class="col-6 mb-3">
																							<label for="txtStockProd" class="form-label">Cantidad
																								:</label> <input type="number" class="form-control"
																								id="txtCantidad" name="txtCantidad" value="1"
																								min="1" step="1" required />

																						</div>
																						<div class="col-12">
																							<div class="modal-footer">
																								<button type="button" class="btn btn-primary">Agregar
																									Compra</button>
																								<button type="button" class="btn btn-secondary"
																									data-bs-dismiss="modal">Cerrar</button>

																							</div>
																						</div>
																					</div>
																				</div>
																			</form>
																		</div>

																	</div>
																</div>
															</div>
															<a
																href="venser?opcion=cargProducto&codigo=${prod.idProducto}"
																class="ms-4 btn btn-success" data-bs-toggle="tooltip"
																data-bs-placement="bottom" title="Agregar Cliente">
																<i class="bx bx-edit mx-0"></i>
															</a>
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
		<a href="javaScript:;" class="back-to-top"><i
			class="bx bxs-up-arrow-alt"></i></a>
		<jsp:include page="components/footer.jsp"></jsp:include>
		<div class="switcher-wrapper">
			<div class="switcher-btn">
				<i class='bx bx-cog bx-spin'></i>
			</div>
			<div class="switcher-body">
				<div class="d-flex align-items-center">
					<h5 class="mb-0 text-uppercase">Bolsa</h5>
					<button type="button" class="btn-close ms-auto close-switcher"
						aria-label="Close"></button>
				</div>
				<hr />
				<form action="venser" method="post">
					<h6 class="mb-0">Cliente</h6>
					<hr />
					<div>${clienteEncontrado.codigo }|${clienteEncontrado.nombre }
						| ${clienteEncontrado.apellido }</div>
					<hr />
					<h6 class="mb-0">Productos</h6>
					<hr />
					<div class="container">

						<c:forEach items="${carro }" var="d">
				${d.id_pro }|${d.nombreProd }|${d.cantidad }|${d.precio }| <fmt:setLocale
								value="es_PE" />
							<fmt:formatNumber type="currency" value="${d.importe }" />

							<div class="d-flex order-actions">

								<a href="venser?opcion=eliCompra&codigo=${d.id_pro}"
									class="ms-4 btn btn-danger" data-bs-toggle="tooltip"
									data-bs-placement="bottom" title="Eliminar Producto"> <i
									class="bx bx-trash mx-0"></i>
								</a>
							</div>
						</c:forEach>
					</div>
					<fmt:setLocale value="es_PE" />

					<c:if
						test="${ClienteDescuento.descuento != 0 && ClienteDescuento.descuento != null}">
						<h6 class="mb-0">Descuento</h6>
						<fmt:formatNumber type="percent"
							value="${ClienteDescuento.descuento }" />

					</c:if>
					<hr />
					<h6 class="mb-0">Subtotal (${cantidadProductos })</h6>
					<hr />
					<div>
						<c:if
							test="${ClienteDescuento.descuento != 0 && ClienteDescuento.descuento != null}">
							<c:if test="${subTotalVentas >0}">
								<del>
									<fmt:formatNumber type="currency" value="${subTotalVentas }" />
								</del>
								<br>
							</c:if>

						</c:if>
						
						<fmt:formatNumber type="currency"
							value="${subTotalVentas-(subTotalVentas * ClienteDescuento.descuento) }" />
					</div>

					<button class="btn btn-primary" name="opcion" value="procCompra">Procesar
						Compra</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="reusable/scripts.jsp"></jsp:include>
	<script
		src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap5.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#clienteTable').dataTable({
				"lengthChange" : false,
				"pageLength" : 5
			});
		});
	</script>
	<script src="js/datatable_config.js"></script>
</body>
</html>
