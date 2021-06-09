const getDatosXFila = (btnFila) => {

  let idProd = btnFila.dataset.idprod.trim();
  const filaElement = btnFila.parentElement.parentElement.parentElement;
  const imageUrl = filaElement.cells[0].firstElementChild.src;
  const celdas = Array.from(filaElement.cells).slice(1,-1);
  const datos = celdas.map( celda => celda.innerText );
  let objProducto = {
    id : idProd,
    descripcion : datos[0],
    categoria : datos[1],
    precio : datos[2],
    stock : datos[3],
    image : imageUrl
  };

  console.log(objProducto);
  return objProducto;
}

const btnsSelectProd  = document.querySelectorAll('.select-prod');

if (btnsSelectProd.length > 0) {
  btnsSelectProd.forEach((btn) => {
    btn.addEventListener('click', () => {
      getDatosXFila(btn);
    });
  });
}