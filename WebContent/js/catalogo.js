const actualizarGuardarCarrito = (carrito = [], compraProd) => {
  carrito.push(compraProd);
  localStorage.setItem('carrito', JSON.stringify(carrito));
}

const obtenerCarritoLocalStorage = () => {
  let arr = localStorage.getItem('carrito');

  if (arr) {
    return JSON.parse(arr);
  }
  return [];
}

const actualizarStockFilaProd = (idProd, cantidadComprar, operacion) => {
  const cellStockProd = document.getElementById(`fila-id-prod-${idProd}`);
  let stock = Number(cellStockProd.innerHTML.trim());
  if (operacion == 'sumar') {
    stock += cantidadComprar;
  } else{
    stock -= cantidadComprar;
  }

  cellStockProd.innerHTML = stock;
}

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
    image: imageUrl,
  };

  return objProducto;
};

const mostrarAlertProducto = (btn) => {
  const objProducto = getDatosXFila(btn);

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
          if (e.target.value === '' || (Number(e.target.value) <= 0 || Number(e.target.value) > objProducto.stock)) {
            Swal.getConfirmButton().disabled = true;
          } else {            
            Swal.getConfirmButton().disabled = false;
          }
        })
      }
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

Swal.getConfirmButton()