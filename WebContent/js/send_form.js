const myForm = document.getElementById('myForm');

const enviarFormulario = (form) => {
  const formData = new FormData(form);
  const action = form.getAttribute('action');
  const method = form.method;

  return fetch(action, {
    method,
    body: formData,
  })
  .then((response) => {
    if (!response.ok) {
      throw new Error(response.statusText);
    }
    return response.json()
  })
  .catch(err => {
    console.log(err);
  })
};

// ${objeto} ${txtDescripcionProd.value}
const mostrarAlertRegistro = (form) => {
  Swal.fire({
    title: `Registrar Cliente`,
    text: `\u00bfDesea registrar el Cliente a la BD?`,
    icon: 'question',
    showCancelButton: true,
    confirmButtonText: 'Aceptar',
    cancelButtonText: 'Cancelar',
    showLoaderOnConfirm: true,
    preConfirm: () => {
      return enviarFormulario(form);
    },
    allowOutsideClick: () => !Swal.isLoading()
  }).then(result => {
    console.log(result);
  })
}

// Valida los formularios
(function () {
  'use strict';

  var forms = document.querySelectorAll('.needs-validation');

  Array.prototype.slice.call(forms).forEach(function (form) {
    form.addEventListener(
      'submit',
      function (event) {
        event.preventDefault();
        if (!form.checkValidity()) {
          event.stopPropagation();
        } else {
          mostrarAlertRegistro(form);
        }

        form.classList.add('was-validated');
      },
      false
    );
  });
})();
