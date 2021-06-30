/*
const selectEstado = document.getElementById("cboEstado");

selectEstado.addEventListener("click", function() {
    var options = selectEstado.querySelectorAll("option");
    var count = options.length;
    if(typeof(count) === "undefined" || count < 2)
    {
        addActivityItem();
    }
});

selectEstado.addEventListener("change", function() {
    if(selectEstado.value == "addNew")
    {
        addActivityItem();
    }
});

function addActivityItem() {
    // ... Code to add item here
}
*/






const eliminarEntidad = (action) => {
  return fetch(action, {
    method: 'DELETE',
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

const mostrarAlerta = (action, titulo, entidad, nombre) => {
  console.log(action);
  Swal.fire({
    title: titulo,
    text: `\u00bfDesea eliminar el ${entidad} ${nombre}?`,
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6',
    confirmButtonText: 'Eliminar',
    cancelButtonText: 'Cancelar',
  }).then((result) => {
    if (result.isConfirmed) {
      eliminarEntidad(action).then((data) => {
        Swal.fire(data.titulo, data.mensaje, data.tipo).then((result) => {
          if (result.isConfirmed || result.isDismissed) {
            window.location.reload();
          }
        });
      });
    }
  });
};

const btnsEliminarEntidad = document.querySelectorAll('.btnEliminarEntidad');

let entidad = '';
let action = '';
let titulo = '';
let nombre = '';

const obtenerDatos = (btn) => {
  entidad = btn.dataset.entidad.trim();
  action = btn.dataset.action.trim();
  titulo = btn.getAttribute('title');
  nombre = btn.dataset.nombre.trim();

  return { entidad, action, titulo, nombre };
};

if (btnsEliminarEntidad.length > 0) {
  btnsEliminarEntidad.forEach((btn) => {
    btn.addEventListener('click', () => {
      const { entidad, action, titulo, nombre } = obtenerDatos(btn);
      mostrarAlerta(action, titulo, entidad, nombre);
    });
  });
}
