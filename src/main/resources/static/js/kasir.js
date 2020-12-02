
$(document).ready(function () {

        var date = new Date();
        var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());

      $('#tanggal').datepicker({
            autoclose: true,
            todayHighlight: true
      })

      $( '#tanggal' ).datepicker( 'setDate', today );

      $("#cboObat").select2({
                  placeholder: "-Selected Obat-",
                  minimumInputLength: 2,
                  ajax:{
                      url: get_uri() + '/api/obat/searchObatByName',
                      dataType: "json",
                      type: "GET",
                      delay: 250,
                      data: function(params){
                          return{
                              q:params.term,
                          }
                      },
                      processResults:function(data){
                          return{
                              results: data
                          }
                      },
                      cache: true
                  }
              });
})

$("#btnAdd").click(function(){
    
})