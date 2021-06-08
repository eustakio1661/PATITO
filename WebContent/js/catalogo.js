const getDatosXFila = (fila) => {
  const celdas = Array.from(fila.cells).slice(1,-1);
  console.log(celdas);
  const datos = celdas.map( celda => celda.innerText );
  console.log(datos);
}

const btnsSelectProd  = document.querySelectorAll('.select-prod');

if (btnsSelectProd.length > 0) {
  btnsSelectProd.forEach((btn) => {
    btn.addEventListener('click', () => {
      const filaElement = btn.parentElement.parentElement.parentElement;
      getDatosXFila(filaElement);
    });
  });
}