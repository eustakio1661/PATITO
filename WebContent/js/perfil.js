const API_CLOUDINARY_URL = 'https://api.cloudinary.com/v1_1/LP2/image/upload';
const API_KEY = '366667499388496';
const UPLOAD_PRESET = 'bibo9msx';

let imagenUrlAEnviar = null;
let imagenFile = null;

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

      document
        .querySelector('.user-img')
        .setAttribute('src', event.target.result);
    });

    imagenFile = input.files[0];
    reader.readAsDataURL(input.files[0]);
  }
};

const inputFile = document.getElementById('input-file');
const btnSelectImg = document.getElementById('select-img');
const btnRemoveImg = document.getElementById('remove-img');

if (inputFile) {
  inputFile.addEventListener('change', (e) => {
    if (e.target.files.length > 0) {
      // Has seleccionado imagen
      mostrarImagen(inputFile);
      imagenUrlAEnviar = null;
    } else {
      console.log('CHANGE IMAGEFILE : ', imagenFile);
    }
  });

  // Obteniendo supuesta imagen si es que existe
  const existeDataImagenUrl = inputFile.dataset.imgurl.trim();
  if (existeDataImagenUrl) {
    imagenUrlAEnviar = existeDataImagenUrl;
  }
}

if (btnSelectImg) {
  btnSelectImg.addEventListener('click', () => {
    inputFile.click();
  });
}

if (btnRemoveImg) {
  btnRemoveImg.addEventListener('click', () => {
    document.querySelector('.img-upload').src = 'https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png';
    document.querySelector('.user-img').src = 'https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png';
    // Limpia el input file
    inputFile.value = '';
    imagenFile = null;
    imagenUrlAEnviar = null;
  });
}

const subirImagenCloudinary = async () => {
  if (inputFile && imagenFile) {
    //const file = inputFile.files[0];
    const formData = new FormData();

    formData.append('file', imagenFile);
    //formData.append('file', file);
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

let inputHidden = document.getElementById('input-hidden');
let palabra = 'actualizar'
let sweetTitle = 'Actualizar Perfil';
let sweetText = '\u00bfDeseas actualizar tu perfil? ';

const cargarDataForm = async (form, imgUrl) => {
  const formData = new FormData(form);

  if (imgUrl) {
    formData.append('imgEmpleado', imgUrl);
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

    console.log(response);

    return await response.json();
  } catch (err) {
    console.log(err);
    Swal.fire('Error', `Oops, sucedi\u00F3 un error inesperado`, 'error');
  }
};

const enviarFormulario = async (form) => {
  let dataCloudinary;

  if (inputFile && imagenFile) {
    dataCloudinary = await subirImagenCloudinary();
    const imgUrl = dataCloudinary.url;
    return await cargarDataForm(form, imgUrl);
  }

  if (inputFile && palabra == 'actualizar' && imagenUrlAEnviar) {
    return await cargarDataForm(form, imagenUrlAEnviar);
  }

  return await cargarDataForm(form, null);
};

const mostrarAlertUpdate = (form) => {
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

  let forms = document.querySelectorAll('.needs-validation');

  Array.prototype.slice.call(forms).forEach(function (form) {
    form.addEventListener(
      'submit',
      function (event) {
        event.preventDefault();
        if (!form.checkValidity()) {
          event.stopPropagation();
        } else {
          mostrarAlertUpdate(form);
        }

        form.classList.add('was-validated');
      },
      false
    );
  });
})();

$(document).ready(function () {
  $('#show_hide_password a').on('click', function (event) {
    event.preventDefault();
    if ($('#show_hide_password input').attr('type') == 'text') {
      $('#show_hide_password input').attr('type', 'password');
      $('#show_hide_password i').addClass('bx-hide');
      $('#show_hide_password i').removeClass('bx-show');
    } else if (
      $('#show_hide_password input').attr('type') == 'password'
    ) {
      $('#show_hide_password input').attr('type', 'text');
      $('#show_hide_password i').removeClass('bx-hide');
      $('#show_hide_password i').addClass('bx-show');
    }
  });
});