Confirm = {
  show: function (message, callback) {
    Swal.fire({
      title: '确认？',
      text: message,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '确认!'
    }).then((result) => {
      if (result.value) {
        if (callback) {
          callback()
        }
      }
    })



  }
}