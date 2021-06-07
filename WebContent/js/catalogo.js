
const btnsSelectProd  = document.querySelectorAll('.select-prod');


if (btnsSelectProd.length > 0) {
  btnsSelectProd.forEach((btn) => {
    btn.addEventListener('click', () => {
      const filaElement = btn.parentElement.parentElement.parentElement;
      console.log('fila : ', filaElement);
      console.log('num fila : ', filaElement.rowIndex);
    });
  });
}