const opciones = {
  eliminar: {
    text: 'eliminar',
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6'
  },
  regresar: {
    text: 'actualizar',
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#757575'
  }
}

const selectEstado = document.getElementById('cboEstado');

const filtrarListaSegunEstado = (estado) => {
  const servlet = selectEstado.dataset.servlet?.trim();
  window.location.href = `${servlet}&cboEstado=${estado}`;
};

selectEstado.addEventListener('change', () => {
  const optionValue = selectEstado.options[selectEstado.selectedIndex].value;
  if (optionValue) {
    filtrarListaSegunEstado(optionValue);
  }
});

const actualizarEstadoEntidad = (action) => {
  return fetch(action, {
    method: 'PATCH',
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

const mostrarAlerta = (action, titulo, entidad, nombre, dataOpcion) => {

  const { text, confirmButtonColor, cancelButtonColor } = opciones[dataOpcion]

  Swal.fire({
    title: titulo,
    text: `\u00bfDesea ${text} el ${entidad} ${nombre}?`,
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor,
    cancelButtonColor,
    confirmButtonText: text[0].toUpperCase() + text.substr(1).toLowerCase(),
    cancelButtonText: 'Cancelar',
  }).then((result) => {
    if (result.isConfirmed) {
      actualizarEstadoEntidad(action).then((data) => {
        Swal.fire(data.titulo, data.mensaje, data.tipo).then((result) => {
          if (result.isConfirmed || result.isDismissed) {
            window.location.reload();
          }
        });
      });
    }
  });
};

const btnsActualizarEstadoEntidad = document.querySelectorAll('.btnActualizarEntidad');

let entidad = '';
let action = '';
let titulo = '';
let nombre = '';
let dataOpcion = 'eliminar';

const obtenerDatos = (btn) => {
  entidad = btn.dataset.entidad?.trim();
  action = btn.dataset.action?.trim();
  titulo = btn.getAttribute('title');
  nombre = btn.dataset.nombre?.trim();
  dataOpcion = btn.dataset.opciones?.trim();

  return { entidad, action, titulo, nombre, dataOpcion };
};

if (btnsActualizarEstadoEntidad.length > 0) {
  btnsActualizarEstadoEntidad.forEach((btn) => {

    btn.addEventListener('click', () => {
      const { entidad, action, titulo, nombre, dataOpcion } = obtenerDatos(btn);
      mostrarAlerta(action, titulo, entidad, nombre, dataOpcion);
    });
  });
}
