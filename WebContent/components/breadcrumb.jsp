<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="page-breadcrumb d-none d-sm-flex align-items-center mb-3">
  <div class="breadcrumb-title pe-3"><c:out value="${param.pagina}" /></div>
  <div class="ps-3">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb mb-0 p-0">
        <li class="breadcrumb-item">
          <a href="home.jsp"><i class="bx bx-home-alt"></i></a>
        </li>
        <li class="breadcrumb-item active" aria-current="page">
          <%=request.getParameter("accion") %>
        </li>
      </ol>
    </nav>
  </div>
</div>
