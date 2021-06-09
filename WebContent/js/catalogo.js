const obtenerCarritoLocalStorage = () => {
  let arr = localStorage.getItem('carrito');

  if (arr) {
    return JSON.parse(arr);
  }
  return [];
};

let carritoProductos = obtenerCarritoLocalStorage();

const actualizarGuardarCarrito = (compraProd) => {
  if (carritoProductos.length > 0) {
    let newCarrito = carritoProductos.filter(
      (prod) => prod.id !== compraProd.id
    );
    newCarrito.push(compraProd);
    carritoProductos = [...newCarrito];
  } else {
    carritoProductos.push(compraProd);
  }

  localStorage.setItem('carrito', JSON.stringify(carritoProductos));
};

const actualizarStockFilaProd = (objProducto, cantidadComprar, operacion) => {
  const cellStockProd = document.getElementById(
    `fila-id-prod-${objProducto.id}`
  );
  let stock = Number(cellStockProd.innerText.trim());

  let prodEncontrado = obtenerCarritoLocalStorage().find(
    (prod) => prod.id === objProducto.id
  );

  if (prodEncontrado) {
    objProducto = { ...prodEncontrado };
  }

  if (operacion == 'sumar') {
    stock += cantidadComprar;
    objProducto.cantidadComprada -= cantidadComprar;
    objProducto.stock += cantidadComprar;
  } else {
    stock -= cantidadComprar;
    objProducto.cantidadComprada += cantidadComprar;
    objProducto.stock -= cantidadComprar;
  }

  cellStockProd.innerHTML = stock;
  return { ...objProducto };
};

const getDatosXFila = (btnFila) => {
  let idProd = btnFila.dataset.idprod.trim();
  const filaElement = btnFila.parentElement.parentElement.parentElement;
  const imageUrl = filaElement.cells[0].firstElementChild.src;
  const celdas = Array.from(filaElement.cells).slice(1, -1);
  const datos = celdas.map((celda) => celda.innerText);
  let objProducto = {
    id: idProd,
    descripcion: datos[0],
    categoria: datos[1],
    precio: datos[2],
    stock: datos[3],
    cantidadComprada: 0,
    image: imageUrl,
  };

  return objProducto;
};

const enviarProductoServlet = (servlet, idProd, cantidadComprar) => {
  let formData = new FormData();
  formData.append('txtIdProdCarrito', idProd);
  formData.append('txtCantComprarCarrito', cantidadComprar);

  return fetch(servlet, {
    method: 'POST',
    body: formData,
  });
};

const Toast = Swal.mixin({
  toast: true,
  position: 'bottom-end',
  showConfirmButton: false,
  timer: 2000,
  timerProgressBar: true,
});

const mostrarAlertProducto = (btn) => {
  let objProducto = getDatosXFila(btn);

  const card = `
  <div class="card card-block bg-faded">
  <div class="card-body">
    <div class="row">
      <div class="col-5">
        <img src="${objProducto.image}" alt="${objProducto.descripcion}" class="rounded img-fluid" />
      </div>
      <div class="col-7">
        <h5 class="card-title">${objProducto.descripcion}</h5>
        <h6 class="card-subtitle mb-2 text-muted">
          ID : <span id="txtIdProducto">${objProducto.id}</span>
        </h6>
        <div class="row mb-2">
          <div class="col-5 text-end">Categoria :</div>
          <div class="col-7 text-start">
            <span>${objProducto.categoria}</span>
          </div>
        </div>
        <div class="row mb-2">
          <div class="col-5 text-end">Precio :</div>
          <div class="col-7 text-start">
            <span>${objProducto.precio}</span>
          </div>
        </div>
        <div class="row mb-2">
          <div class="col-5 text-end">Stock :</div>
          <div class="col-7 text-start">
            <span>${objProducto.stock}</span>
          </div>
        </div>
        <div class="row mb">
          <label for="txtCantidadComprar" class="col-5 col-form-label text-end"
            >Cantidad :</label
          >
          <div class="col-7 text-start">
            <input
              type="number"
              class="form-control"
              name="txtCantidadComprar"
              id="txtCantidadComprar"
              value="0"
              required
              min="0"
              max="${objProducto.stock}"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
`;

  Swal.fire({
    html: card,
    position: 'top',
    width: 700,
    showCancelButton: true,
    confirmButtonText: 'Agregar al carrito',
    cancelButtonText: 'Cancelar',
    didOpen: () => {
      Swal.getConfirmButton().disabled = true;
      const txtCantidadComprar = document.getElementById('txtCantidadComprar');
      if (txtCantidadComprar) {
        txtCantidadComprar.addEventListener('input', (e) => {
          if (
            e.target.value === '' ||
            Number(e.target.value) <= 0 ||
            Number(e.target.value) > objProducto.stock
          ) {
            Swal.getConfirmButton().disabled = true;
          } else {
            Swal.getConfirmButton().disabled = false;
          }
        });
      }
    },
  }).then((result) => {
    if (result.isConfirmed) {
      const txtCantidadComprar = document.getElementById('txtCantidadComprar');
      const cantidadComprar = Number(txtCantidadComprar.value.trim());

      objProducto = actualizarStockFilaProd(
        objProducto,
        cantidadComprar,
        'resta'
      );
      actualizarGuardarCarrito(objProducto);
      enviarProductoServlet('servlet').then(console.log).catch(console.log);

      Toast.fire({
        icon: 'success',
        title: 'Se agrego al carrito',
      });
    }
  });
};

const btnsSelectProd = document.querySelectorAll('.select-prod');

if (btnsSelectProd.length > 0) {
  btnsSelectProd.forEach((btn) => {
    btn.addEventListener('click', () => {
      mostrarAlertProducto(btn);
    });
  });
}
