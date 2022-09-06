import toastr from 'toastr'

toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-center",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
  }

  export function showMessage(title, message, type){
    toastr[type](message, title)
  }

  export function errorMessage(message){
      showMessage('Erro', message, 'warning');
  }

  export function successMessage(message){
    showMessage('Sucesso', message, 'success');
  }

  export function alertMessage(message){
    showMessage('Alerta', message, 'danger');
}