var itemArray = [];
var parameter;

$(document).ready(function () {

        parameter = {
                catatan: null,
                totalBayar : null,
                tipePembayaran: null,
                transaksiDetailDtos: null
        }

      $("#cboMenu").select2({
                  placeholder: "-Selected Menu-",
                  minimumInputLength: 2,
                  ajax:{
                      url: get_uri() + '/api/menu/searchMenuByName',
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