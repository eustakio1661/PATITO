<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!--plugins-->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/simplebar@latest/dist/simplebar.css"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/metismenujs/dist/metismenujs.min.css"
    />

    <!-- loader-->
    <script src="https://cdn.jsdelivr.net/npm/pace-js@latest/pace.min.js"></script>
    <link href="assets/css/pace.min.css" rel="stylesheet" />

    <!-- Bootstrap CSS -->

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
      crossorigin="anonymous"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&amp;display=swap"
      rel="stylesheet"
    />
    <link href="assets/css/app.css" rel="stylesheet" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"
    />
    <title>Patito Restaurant</title>
  </head>
  <body>
    <!--wrapper-->
    <div class="wrapper">
      <!--sidebar wrapper -->
      <div class="sidebar-wrapper" data-simplebar="true">
        <div class="sidebar-header">
          <div>
            <img
              src="assets/images/logo-icon.png"
              class="logo-icon"
              alt="logo icon"
            />
          </div>
          <div>
            <h4 class="logo-text">🦆 Patito</h4>
          </div>
          <div class="toggle-icon ms-auto">
            <i class="bx bx-arrow-to-left"></i>
          </div>
        </div>
        <!--navigation-->
        <ul class="metismenu" id="menu">
          <li>
            <a href="javascript:;" class="has-arrow">
              <div class="parent-icon"><i class="bx bx-home-circle"></i></div>
              <div class="menu-title">Dashboard</div>
            </a>
            <ul>
              <li>
                <a href="index.html"
                  ><i class="bx bx-right-arrow-alt"></i>Inicio</a
                >
              </li>
              <li>
                <a href="#"
                  ><i class="bx bx-right-arrow-alt"></i>Pedidos Pendientes</a
                >
              </li>
            </ul>
          </li>
          <li class="menu-label">Mantenimiento</li>
          <li>
            <a href="javascript:;" class="has-arrow">
              <div class="parent-icon"><i class="bx bx-id-card"></i></div>
              <div class="menu-title">Empleados</div>
            </a>
            <ul>
              <li>
                <a href="ecommerce-products.html"
                  ><i class="bx bx-right-arrow-alt"></i>Registro</a
                >
              </li>
              <li>
                <a href="ecommerce-products-details.html"
                  ><i class="bx bx-right-arrow-alt"></i>Listado</a
                >
              </li>
            </ul>
          </li>
          <li>
            <a class="has-arrow" href="javascript:;">
              <div class="parent-icon">
                <i class="bx bx-user-circle"></i>
              </div>
              <div class="menu-title">Clientes</div>
            </a>
            <ul>
              <li>
                <a href="component-alerts.html"
                  ><i class="bx bx-right-arrow-alt"></i>Registro</a
                >
              </li>
              <li>
                <a href="component-accordions.html"
                  ><i class="bx bx-right-arrow-alt"></i>Listado</a
                >
              </li>
            </ul>
          </li>
          <li>
            <a class="has-arrow" href="javascript:;">
              <div class="parent-icon"><i class="bx bx-package"></i></div>
              <div class="menu-title">Productos</div>
            </a>
            <ul>
              <li>
                <a href="content-grid-system.html"
                  ><i class="bx bx-right-arrow-alt"></i>Registro</a
                >
              </li>
              <li>
                <a href="content-typography.html"
                  ><i class="bx bx-right-arrow-alt"></i>Listado</a
                >
              </li>
            </ul>
          </li>

          <li class="menu-label">Transacciones</li>
          <li>
            <a href="javascript:;">
              <div class="parent-icon">
                <i class="bx bx-cart"></i>
              </div>
              <div class="menu-title">Nuevo Pedido</div>
            </a>
          </li>
          <li class="menu-label">Reportes</li>
          <li>
            <a href="user-profile.html">
              <div class="parent-icon"><i class="bx bx-receipt"></i></div>
              <div class="menu-title">Reporte de Ventas</div>
            </a>
          </li>
        </ul>
        <!--end navigation-->
      </div>
      <!--end sidebar wrapper -->
      <!--start header -->
      <header>
        <div class="topbar d-flex align-items-center">
          <nav class="navbar navbar-expand">
            <div class="mobile-toggle-menu"><i class="bx bx-menu"></i></div>
            <div class="search-bar flex-grow-1">
              <div class="position-relative search-bar-box">
                <input
                  type="text"
                  class="form-control search-control"
                  placeholder="Type to search..."
                />
                <span
                  class="position-absolute top-50 search-show translate-middle-y"
                  ><i class="bx bx-search"></i
                ></span>
                <span
                  class="position-absolute top-50 search-close translate-middle-y"
                  ><i class="bx bx-x"></i
                ></span>
              </div>
            </div>
            <!--Buscar-icono-->
            <div class="top-menu ms-auto">
              <ul class="navbar-nav align-items-center">
                <li class="nav-item mobile-search-icon">
                  <a class="nav-link" href="#">
                    <i class="bx bx-search"></i>
                  </a>
                </li>
              </ul>
            </div>
            <div class="user-box dropdown">
              <a
                class="d-flex align-items-center nav-link dropdown-toggle dropdown-toggle-nocaret"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <img
                  src="assets/images/avatars/avatar-2.png"
                  class="user-img"
                  alt="user avatar"
                />
                <div class="user-info ps-3">
                  <p class="user-name mb-0">Pauline Seitz</p>
                  <p class="designattion mb-0">Asistente de Ventas</p>
                </div>
              </a>
              <ul class="dropdown-menu dropdown-menu-end">
                <li>
                  <a class="dropdown-item" href="javascript:;"
                    ><i class="bx bx-user"></i><span>Perfil</span></a
                  >
                </li>
                <li>
                  <a class="dropdown-item" href="javascript:;"
                    ><i class="bx bx-cog"></i><span>Configuraciones</span></a
                  >
                </li>
                <li>
                  <a class="dropdown-item" href="javascript:;"
                    ><i class="bx bx-home-circle"></i><span>Inicio</span></a
                  >
                </li>
                <li>
                  <div class="dropdown-divider mb-0"></div>
                </li>
                <li>
                  <a class="dropdown-item" href="javascript:;"
                    ><i class="bx bx-log-out-circle"></i
                    ><span>Cerrar Sesion</span></a
                  >
                </li>
              </ul>
            </div>
          </nav>
        </div>
      </header>
      <!--end header -->
      <!--start page wrapper -->
      <div class="page-wrapper">
        <div class="page-content">
          <div class="row row-cols-1 row-cols-md-2 row-cols-xl-4 mb-2">
            <div class="col">
              <div class="card radius-10 bg-gradient-deepblue mb-4 box-shadow">
                <div class="card-body">
                  <div class="d-flex align-items-center">
                    <h5 class="mb-0 text-white">9526</h5>
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
                    <h5 class="mb-0 text-white">$8323</h5>
                    <div class="ms-auto">
                      <i class="bx bx-dollar fs-3 text-white"></i>
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
                    <h5 class="mb-0 text-white">6200</h5>
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
                    <p class="mb-0">Clientes Atendidos</p>
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
                    <h5 class="mb-0 text-white">5630</h5>
                    <div class="ms-auto">
                      <i class="bx bx-envelope fs-3 text-white"></i>
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
                    <p class="mb-0">Messages</p>
                    <p class="mb-0 ms-auto">
                      +2.2%<span><i class="bx bx-up-arrow-alt"></i></span>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!--end row-->

          <div class="card radius-10 mb-5 box-shadow">
            <div class="card-body">
              <div class="d-flex align-items-center">
                <div>
                  <h5 class="mb-0">Ultimos Pedidos Realizados</h5>
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
                      <th>Orden Id</th>
                      <th>Producto</th>
                      <th>Cliente</th>
                      <th>Fecha</th>
                      <th>Precio</th>
                      <th>Estado</th>
                      <th>Acciones</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>#897656</td>
                      <td>
                        <div class="d-flex align-items-center">
                          <div class="recent-product-img">
                            <img src="assets/images/icons/chair.png" alt="" />
                          </div>
                          <div class="ms-2">
                            <h6 class="mb-1 font-14">Light Blue Chair</h6>
                          </div>
                        </div>
                      </td>
                      <td>Brooklyn Zeo</td>
                      <td>12 Jul 2020</td>
                      <td>$64.00</td>
                      <td>
                        <div
                          class="badge rounded-pill bg-light-info text-info w-100"
                        >
                          En Progreso
                        </div>
                      </td>
                      <td>
                        <div class="d-flex order-actions">
                          <a href="javascript:;" class=""
                            ><i class="bx bx-cog"></i
                          ></a>
                          <a href="javascript:;" class="ms-4"
                            ><i class="bx bx-down-arrow-alt"></i
                          ></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>#987549</td>
                      <td>
                        <div class="d-flex align-items-center">
                          <div class="recent-product-img">
                            <img src="assets/images/icons/shoes.png" alt="" />
                          </div>
                          <div class="ms-2">
                            <h6 class="mb-1 font-14">Green Sport Shoes</h6>
                          </div>
                        </div>
                      </td>
                      <td>Martin Hughes</td>
                      <td>14 Jul 2020</td>
                      <td>$45.00</td>
                      <td>
                        <div
                          class="badge rounded-pill bg-light-success text-success w-100"
                        >
                          Completado
                        </div>
                      </td>
                      <td>
                        <div class="d-flex order-actions">
                          <a href="javascript:;" class=""
                            ><i class="bx bx-cog"></i
                          ></a>
                          <a href="javascript:;" class="ms-4"
                            ><i class="bx bx-down-arrow-alt"></i
                          ></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>#685749</td>
                      <td>
                        <div class="d-flex align-items-center">
                          <div class="recent-product-img">
                            <img
                              src="assets/images/icons/headphones.png"
                              alt=""
                            />
                          </div>
                          <div class="ms-2">
                            <h6 class="mb-1 font-14">Red Headphone 07</h6>
                          </div>
                        </div>
                      </td>
                      <td>Shoan Stephen</td>
                      <td>15 Jul 2020</td>
                      <td>$67.00</td>
                      <td>
                        <div
                          class="badge rounded-pill bg-light-danger text-danger w-100"
                        >
                          Cancelado
                        </div>
                      </td>
                      <td>
                        <div class="d-flex order-actions">
                          <a href="javascript:;" class=""
                            ><i class="bx bx-cog"></i
                          ></a>
                          <a href="javascript:;" class="ms-4"
                            ><i class="bx bx-down-arrow-alt"></i
                          ></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>#887459</td>
                      <td>
                        <div class="d-flex align-items-center">
                          <div class="recent-product-img">
                            <img src="assets/images/icons/idea.png" alt="" />
                          </div>
                          <div class="ms-2">
                            <h6 class="mb-1 font-14">Mini Laptop Device</h6>
                          </div>
                        </div>
                      </td>
                      <td>Alister Campel</td>
                      <td>18 Jul 2020</td>
                      <td>$87.00</td>
                      <td>
                        <div
                          class="badge rounded-pill bg-light-success text-success w-100"
                        >
                          Completado
                        </div>
                      </td>
                      <td>
                        <div class="d-flex order-actions">
                          <a href="javascript:;" class=""
                            ><i class="bx bx-cog"></i
                          ></a>
                          <a href="javascript:;" class="ms-4"
                            ><i class="bx bx-down-arrow-alt"></i
                          ></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>#335428</td>
                      <td>
                        <div class="d-flex align-items-center">
                          <div class="recent-product-img">
                            <img
                              src="assets/images/icons/user-interface.png"
                              alt=""
                            />
                          </div>
                          <div class="ms-2">
                            <h6 class="mb-1 font-14">Purple Mobile Phone</h6>
                          </div>
                        </div>
                      </td>
                      <td>Keate Medona</td>
                      <td>20 Jul 2020</td>
                      <td>$75.00</td>
                      <td>
                        <div
                          class="badge rounded-pill bg-light-info text-info w-100"
                        >
                          En Progreso
                        </div>
                      </td>
                      <td>
                        <div class="d-flex order-actions">
                          <a href="javascript:;" class=""
                            ><i class="bx bx-cog"></i
                          ></a>
                          <a href="javascript:;" class="ms-4"
                            ><i class="bx bx-down-arrow-alt"></i
                          ></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>#224578</td>
                      <td>
                        <div class="d-flex align-items-center">
                          <div class="recent-product-img">
                            <img src="assets/images/icons/watch.png" alt="" />
                          </div>
                          <div class="ms-2">
                            <h6 class="mb-1 font-14">Smart Hand Watch</h6>
                          </div>
                        </div>
                      </td>
                      <td>Winslet Maya</td>
                      <td>22 Jul 2020</td>
                      <td>$80.00</td>
                      <td>
                        <div
                          class="badge rounded-pill bg-light-danger text-danger w-100"
                        >
                          Cancelado
                        </div>
                      </td>
                      <td>
                        <div class="d-flex order-actions">
                          <a href="javascript:;" class=""
                            ><i class="bx bx-cog"></i
                          ></a>
                          <a href="javascript:;" class="ms-4"
                            ><i class="bx bx-down-arrow-alt"></i
                          ></a>
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td>#447896</td>
                      <td>
                        <div class="d-flex align-items-center">
                          <div class="recent-product-img">
                            <img src="assets/images/icons/tshirt.png" alt="" />
                          </div>
                          <div class="ms-2">
                            <h6 class="mb-1 font-14">T-Shirt Blue</h6>
                          </div>
                        </div>
                      </td>
                      <td>Emy Jackson</td>
                      <td>28 Jul 2020</td>
                      <td>$96.00</td>
                      <td>
                        <div
                          class="badge rounded-pill bg-light-success text-success w-100"
                        >
                          Completado
                        </div>
                      </td>
                      <td>
                        <div class="d-flex order-actions">
                          <a href="javascript:;" class=""
                            ><i class="bx bx-cog"></i
                          ></a>
                          <a href="javascript:;" class="ms-4"
                            ><i class="bx bx-down-arrow-alt"></i
                          ></a>
                        </div>
                      </td>
                    </tr>
                  </tbody>
                </table>
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
      <footer class="page-footer mt-3 p-2">
        <p class="mb-0">
          Copyright &copy; 🦆 Patito 2021. Todos los derechos reservados.
        </p>
      </footer>
    </div>
    <!--end wrapper-->

    <!-- Bootstrap JS -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
      crossorigin="anonymous"
    ></script>
    <!-- Plugins -->

    <script src="assets/js/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simplebar@latest/dist/simplebar.min.js"></script>
    <script src="assets/plugins/metismenu/js/metisMenu.min.js"></script>
    <!--Efecto más suave JQ-->
    <!--<script src="https://cdn.jsdelivr.net/npm/metismenujs/dist/metismenujs.min.js"></script>--><!--Efecto más tosco JS-->

    <!--<script src="assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>-->
    <script src="assets/js/app.js"></script>
  </body>
</html>
    