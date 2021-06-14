const existeImagen = (input) => {
  return input.files && input.files[0];
};

const mostrarImagen = (input) => {
  if (existeImagen(input)) {
    const reader = new FileReader();

    reader.addEventListener('load', (event) => {
      document
        .querySelector('.img-upload')
        .setAttribute('src', event.target.result);
    });

    reader.readAsDataURL(input.files[0]);
  }
};

const inputFile = document.getElementById('input-file');
const btnSelectImg = document.getElementById('select-img');
const btnRemoveImg = document.getElementById('remove-img');

if (inputFile) {
  inputFile.addEventListener('change', () => {
    mostrarImagen(inputFile);
  });
}

if (btnSelectImg) {
  btnSelectImg.addEventListener('click', () => {
    inputFile.click();
  });
}

if (btnRemoveImg) {
  btnRemoveImg.addEventListener('click', () => {
    console.log(btnRemoveImg);
    document.querySelector('.img-upload').src =
      'https://res.cloudinary.com/dfuuywyk9/image/upload/v1622821263/notfound_gwgndg.png';
    console.log(document.querySelector('.img-upload').src);
  });
}

const titleCase = (str) => {
  return str
    .split(' ')
    .map((w) => w[0].toUpperCase() + w.substr(1).toLowerCase())
    .join(' ')
    .trim();
};

let inputHidden = document.getElementById('input-hidden');
let palabra = 'registrar';
let entidad = '';
let sweetTitle = '';
let sweetText = '';

if (inputHidden) {
  entidad = inputHidden.dataset.entidad.trim();
  if (inputHidden.value.trim()) {
    palabra = 'actualizar';
  }
  sweetTitle = titleCase(`${palabra} ${entidad}`);
  sweetText = `\u00bfDesea ${palabra} el ${entidad} a la BD?`;
}

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
      return response.json();
    })
    .catch((err) => {
      console.log(err);
      Swal.fire('Error', `Oops, sucedi\u00F3 un error inesperado`, 'error');
    });
};

const mostrarAlertRegistro = (form) => {
  Swal.fire({
    title: sweetTitle,
    text: sweetText,
    icon: 'question',
    showCancelButton: true,
    confirmButtonText: 'Aceptar',
    cancelButtonText: 'Cancelar',
    showLoaderOnConfirm: true,
    preConfirm: () => {
      return enviarFormulario(form);
    },
    allowOutsideClick: () => !Swal.isLoading(),
  }).then((result) => {
    if (result.isConfirmed) {
      const data = result.value;

      if (data.ok) form.reset();

      Swal.fire(data.titulo, data.mensaje, data.tipo).then((result) => {
        if (
          (result.isConfirmed || result.isDismissed) &&
          palabra === 'actualizar'
        ) {
          window.location.reload();
        }
      });

      if (form.classList.contains('was-validated')) {
        form.classList.remove('was-validated');
        return;
      }
    }
  });
};

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
