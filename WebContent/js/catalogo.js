const Toast = Swal.mixin({
  toast: true,
  position: 'bottom-end',
  showConfirmButton: false,
  timer: 2000,
  timerProgressBar: true,
});

const obtenerCarritoLocalStorage = () => {
  let arr = localStorage.getItem('carrito');

  if (arr) {
    return JSON.parse(arr);
  }
  return [];
};

// CANASTA

const realizarCompra = () => {
  if (localStorage.getItem('dataCliente')) {    
    window.location.href = 'venser?opcion=descuento';
  } else {
    Toast.fire({
      icon: 'error',
      title: 'Cliente No Seleccionado',
    });
  }
};

const crearCardCanastaProd = () => {
  const switcherBody = document.querySelector('.switcher-body');

  const card = document.createElement('div');
  card.classList.add('card', 'card-block', 'bg-faded');

  const cardBody = document.createElement('div');
  cardBody.classList.add('card-body');
  cardBody.setAttribute('id', 'canasta');

  card.appendChild(cardBody);

  switcherBody.appendChild(card);

  // Crea boton de confirmar compra
  const btnContainer = document.createElement('div');
  btnContainer.classList.add('d-flex', 'justify-content-center', 'mt-3');
  btnContainer.setAttribute('id', 'btnConfirmContainer');

  const aBtnConfirm = document.createElement('button');
  aBtnConfirm.classList.add('btn', 'btn-warning');
  aBtnConfirm.innerText = 'Confirmar Compra';
  aBtnConfirm.setAttribute('onclick', 'realizarCompra()');
  aBtnConfirm.onclick = function () {
    realizarCompra();
  };

  btnContainer.appendChild(aBtnConfirm);
  switcherBody.appendChild(btnContainer);
};

// INPUT CLIENTE

const llenarInputsCliente = (nombreCompleto, distrito, direccion) => {
  const txtNombreCliente = document.getElementById('txtNombreCli');
  const txtDistritoCliente = document.getElementById('txtDistritoCli');
  const txtDireccionCliente = document.getElementById('txtDireccionCli');

  txtNombreCliente.value = nombreCompleto;
  txtDistritoCliente.value = distrito;
  txtDireccionCliente.value = direccion;
};

const existeCliente = JSON.parse(localStorage.getItem('dataCliente'));

if (existeCliente) {
  llenarInputsCliente(
    existeCliente.nombre,
    existeCliente.distrito,
    existeCliente.direccion
  );
}

const btnLimpiarClienteTxt = document.getElementById('btnLimpiarClienteTxt');

if (btnLimpiarClienteTxt) {
  btnLimpiarClienteTxt.addEventListener('click', () => {
    llenarInputsCliente('', '', '');
    if (localStorage.getItem('dataCliente')) {
      localStorage.removeItem('dataCliente');
    }
  });
}

const realizarPeticionCliente = (form) => {
  const formData = new FormData(form);

  return fetch('venser?opcion=buscarCliente', {
    method: 'POST',
    body: formData,
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(response.statusText);
      }
      return response.json();
    })
    .catch((err) => {
      console.log(err);
      Swal.fire('Error', `Oops, sucedi\u00F3 un error inesperado`, 'error');
    });
};

const formBuscarCliente = document.getElementById('formBuscarCliente');

if (formBuscarCliente) {
  formBuscarCliente.addEventListener(
    'submit',
    (e) => {
      e.preventDefault();
      if (!formBuscarCliente.checkValidity()) {
        e.stopPropagation();
      } else {
        realizarPeticionCliente(formBuscarCliente).then((data) => {
          Swal.fire(data.titulo, data.mensaje, data.tipo);
          if (data.ok) {
            llenarInputsCliente(
              data.nombreCliente,
              data.distritoCliente,
              data.direccionCliente
            );

            const objCliente = {
              nombre: data.nombreCliente,
              distrito: data.distritoCliente,
              direccion: data.direccionCliente,
            };

            localStorage.setItem('dataCliente', JSON.stringify(objCliente));
          }
        });
      }

      formBuscarCliente.classList.add('was-validated');
    },
    true
  );
}

// PRODUCTO - TABLA - ALERT

const actualizarGuardarCarrito = (compraProd) => {
  let carritoProductos = obtenerCarritoLocalStorage();

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

const enviarProductoServlet = (servlet, idProd, cantidadComprar) => {
  let formData = new FormData();
  formData.append('txtIdProdCarrito', idProd);
  formData.append('txtCantComprarCarrito', cantidadComprar);

  return fetch(servlet, {
    method: 'POST',
    body: formData,
  });
};

const quitarBtnConfirmButton = () => {
  const btnConfirmContainer = document.getElementById('btnConfirmContainer');

  if (btnConfirmContainer) {
    btnConfirmContainer.remove();
  }
};

const quitarProducto = (btn) => {
  const idProd = btn.dataset.idprod.trim();
  const lista = obtenerCarritoLocalStorage();

  if (lista.length > 0) {
    let objProducto = lista.find((prod) => prod.id == idProd);
    const nuevaLista = lista.filter((prod) => prod.id !== idProd);

    document.getElementById(`row-id-${objProducto.id}`).remove();

    localStorage.setItem('carrito', JSON.stringify(nuevaLista));
    actualizarStockFilaProd(objProducto, objProducto.cantidadComprada, 'sumar');

    if (obtenerCarritoLocalStorage().length === 0) {
      const canastaContainer = document.getElementById('canasta').parentElement;
      canastaContainer.remove();
      quitarBtnConfirmButton();
    }

    enviarProductoServlet(
      'venser?opcion=eliCompra',
      idProd,
      objProducto.cantidadComprada
    );
  }
};

const crearFilaCanastaProd = (objProducto) => {
  const canasta = document.getElementById('canasta');

  if (canasta) {
    // Si existe fila
    if (document.getElementById(`row-id-${objProducto.id}`)) {
      const spanCantidad = document.getElementById(`span-cc-${objProducto.id}`);
      spanCantidad.innerText = objProducto.cantidadComprada;
    } else {
      // Creando row
      const row = document.createElement('div');
      row.classList.add('row', 'mt-2');
      row.setAttribute('id', `row-id-${objProducto.id}`);

      const col4 = document.createElement('div');
      col4.classList.add('col-4');

      const img = document.createElement('img');
      img.classList.add('rounded', 'img-fluid');
      img.setAttribute('src', objProducto.image);
      img.setAttribute('alt', objProducto.descripcion);

      col4.appendChild(img);
      row.appendChild(col4);

      const col6 = document.createElement('div');
      col6.classList.add('col-6');

      const pCardTitle = document.createElement('p');
      pCardTitle.classList.add('card-title');
      const b = document.createElement('b');
      b.innerText = objProducto.descripcion;
      pCardTitle.appendChild(b);

      const p = document.createElement('p');
      p.innerHTML = `Cantidad : <span id="span-cc-${objProducto.id}">${objProducto.cantidadComprada}</span>`;

      col6.appendChild(pCardTitle);
      col6.appendChild(p);
      row.appendChild(col6);

      const col2 = document.createElement('div');
      col2.classList.add('col-2');

      const button = document.createElement('button');
      button.classList.add(
        'btn',
        'btn-outline-danger',
        'btn-close',
        'ms-auto',
        'btn-quitar-producto'
      );
      button.setAttribute('type', 'button');
      button.setAttribute('aria-label', 'Close');
      button.setAttribute('data-idprod', objProducto.id);
      button.setAttribute(
        'data-canticomprada',
        `${objProducto.cantidadComprada}`
      );

      button.setAttribute('onclick', 'quitarProducto(this)');
      button.onclick = function () {
        quitarProducto(this);
      };

      col2.appendChild(button);
      row.appendChild(col2);

      canasta.appendChild(row);
    }
  }
};

const llenarCanastaActualizarFilasLS = () => {
  const listaProductos = obtenerCarritoLocalStorage();

  if (listaProductos.length > 0) {
    crearCardCanastaProd();
    // Problemas de rendimiento por no usar fragment
    for (const objProducto of listaProductos) {
      crearFilaCanastaProd(objProducto);
      actualizarStockFilaProd(
        objProducto,
        objProducto.cantidadComprada,
        'restar'
      );
    }
  }
};

llenarCanastaActualizarFilasLS();

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
    </div>`;

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

      if (obtenerCarritoLocalStorage().length === 0) {
        crearCardCanastaProd();
      }

      objProducto = actualizarStockFilaProd(
        objProducto,
        cantidadComprar,
        'resta'
      );
      actualizarGuardarCarrito(objProducto);
      enviarProductoServlet(
        'venser?opcion=agrCompra',
        objProducto.id,
        cantidadComprar
      )
        .then(console.log)
        .catch(console.log);

      crearFilaCanastaProd(objProducto);

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
