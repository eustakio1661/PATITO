<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="sidebar-wrapper" data-simplebar="true">
  <div class="sidebar-header">
    <div>
      <img src="img/logo-icon.png" class="logo-icon" alt="logo icon" />
    </div>
    <div>
      <h4 class="logo-text">Patito</h4>
    </div>
    <div class="toggle-icon ms-auto">
      <i class="bx bx-arrow-to-left"></i>
    </div>
  </div>
  <!--navigation -->
  <ul class="metismenu" id="menu">
    <li>
      <a href="#" class="has-arrow">
        <span class="parent-icon"><i class="bx bx-home-circle"></i></span>
        <span class="menu-title">Dashboard</span>
      </a>
      <ul>
        <li>
          <a href="home.jsp"><i class="bx bx-right-arrow-alt"></i>Inicio</a>
        </li>
        <li>
          <a href="venser?opcion=pedidosPendientes"
            ><i class="bx bx-right-arrow-alt"></i>Pedidos Pendientes</a
          >
        </li>
      </ul>
    </li>
    <li class="menu-label">Mantenimiento</li>

    <c:if test="${ e.idTipo == 1 }">
      <li>
        <a href="#" class="has-arrow">
          <span class="parent-icon"><i class="bx bx-id-card"></i></span>
          <span class="menu-title">Empleados</span>
        </a>
        <ul>
          <li>
            <a href="crud-empleado.jsp"
              ><i class="bx bx-right-arrow-alt"></i>Registro</a
            >
          </li>
          <li>
            <a href="emse?opcion=listar"
              ><i class="bx bx-right-arrow-alt"></i>Listado</a
            >
          </li>
        </ul>
      </li>
    </c:if>
    <li>
      <a class="has-arrow" href="javascript:;">
        <span class="parent-icon">
          <i class="bx bx-user-circle"></i>
        </span>
        <span class="menu-title">Clientes</span>
      </a>
      <ul>
        <li>
          <a href="crud-cliente.jsp"
            ><i class="bx bx-right-arrow-alt"></i>Registro</a
          >
        </li>
        <li>
          <a href="cs?opcion=listado"
            ><i class="bx bx-right-arrow-alt"></i>Listado</a
          >
        </li>
      </ul>
    </li>
    <li>
      <a class="has-arrow" href="javascript:;">
        <span class="parent-icon"><i class="bx bx-package"></i></span>
        <span class="menu-title">Productos</span>
      </a>
      <ul>
        <li>
          <a href="crud-producto.jsp"
            ><i class="bx bx-right-arrow-alt"></i>Registro</a
          >
        </li>
        <li>
          <a href="ps?opcion=listado"
            ><i class="bx bx-right-arrow-alt"></i>Listado</a
          >
        </li>
      </ul>
    </li>

    <li class="menu-label">Transacciones</li>
    <li>
      <a href="ps?opcion=catalogo">
        <span class="parent-icon">
          <i class="bx bx-cart"></i>
        </span>
        <span class="menu-title">Nuevo Pedido</span>
      </a>
    </li>
    <li class="menu-label">Reportes</li>
    <li>
      <a class="has-arrow" href="javascript:;">
        <span class="parent-icon"><i class="bx bx-receipt"></i></span>
        <span class="menu-title">Reporte de Ventas</span>
      </a>
      <ul>
        <li>
          <a href="rese?opcion=listado"
            ><i class="bx bx-right-arrow-alt"></i>Reporte entre Fechas</a
          >
        </li>
      </ul>
    </li>
    <li>
      <a href="rese?opcion=reporteCliente">
        <span class="parent-icon">
          <i class="bx bx-receipt"></i>
        </span>
        <span class="menu-title">Reporte de Clientes</span>
      </a>
    </li>
  </ul>
  <!--end navigation-->
</div>
