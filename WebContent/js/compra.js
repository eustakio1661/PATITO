const btnCancelarCompra = document.getElementById('btnCancelarCompra');

if (btnCancelarCompra) {
  btnCancelarCompra.addEventListener('click', () => {
    Swal.fire({
      title: 'Cancelar',
      text: '\u00bfEstas seguro de cancelar la compra',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Confirmar',
      cancelButtonText: 'Cerrar',
    }).then((result) => {
      if (result.isConfirmed) {
        fetch('venser?opcion=canceCompra', {
          method: 'GET',
        })
          .then((resp) => resp.json())
          .then((data) => {
            if (data.ok) {
              localStorage.clear();
              window.location.href = 'ps?opcion=catalogo';
            }
          })
          .catch(console.log);
      }
    });
  });
}

const formCompra = document.getElementById('formCompra');

if (formCompra) {
  formCompra.addEventListener('submit', (e) => {
    e.preventDefault();
    const formData = new FormData(formCompra);
    const action = formCompra.getAttribute('action');
    const method = formCompra.method;

    try {
      fetch(action, {
        method,
        body: formData,
      })
        .then((resp) => resp.json())
        .then((data) => {
          Swal.fire(data.titulo, data.mensaje, data.tipo).then((result) => {
            if (result.isConfirmed || result.isDismissed) {
              localStorage.clear();
              window.location.href = 'home.jsp';
            }
          });
        });
    } catch (error) {
      Swal.fire('Error', `Oops, sucedi\u00F3 un error inesperado`, 'error');
    }
  });
}
