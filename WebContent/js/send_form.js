const API_CLOUDINARY_URL = 'https://api.cloudinary.com/v1_1/LP2/image/upload';
const API_KEY = '366667499388496';
const UPLOAD_PRESET = 'bibo9msx';

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
    document.querySelector('.img-upload').src = 'https://cutt.ly/unbQLrJ';
  });
}

const subirImagenCloudinary = async () => {
  if (inputFile) {
    const file = inputFile.files[0];
    const formData = new FormData();

    formData.append('file', file);
    formData.append('api_key', API_KEY);
    formData.append('upload_preset', UPLOAD_PRESET);

    try {
      const response = await fetch(API_CLOUDINARY_URL, {
        method: 'POST',
        body: formData,
      });

      return await response.json();
    } catch (err) {
      console.log('Error Cloudinary ', err);
    }
  }
};

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

const cargarDataForm = async (form, imgUrl) => {
  const formData = new FormData(form);

  if (imgUrl) {
    formData.append('imgProducto', imgUrl);
  }

  const action = form.getAttribute('action');
  const method = form.method;

  try {
    const response = await fetch(action, {
      method,
      body: formData,
    });
    if (!response.ok) {
      throw new Error(response.statusText);
    }
    return await response.json();
  } catch (err) {
    console.log(err);
    Swal.fire('Error', `Oops, sucedi\u00F3 un error inesperado`, 'error');
  }
};

const enviarFormulario = async (form) => {
  let dataCloudinary;

  if (inputFile) {
    dataCloudinary = await subirImagenCloudinary();
    const imgUrl = dataCloudinary.url;
    return await cargarDataForm(form, imgUrl);
  }

  return await cargarDataForm(form, null);
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

      if (data.ok) {
        if (inputFile) {
          document.querySelector('.img-upload').src = 'https://cutt.ly/unbQLrJ';
        }
        form.reset();
      }

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
