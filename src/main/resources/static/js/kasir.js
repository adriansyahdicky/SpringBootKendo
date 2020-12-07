var itemArray = [];
var parameter;

$(document).ready(function () {

        var date = new Date();
        var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());

        parameter = {
                tanggal : null,
                price : null,
                userId : null,
                orderDetailDTOS: null
        }

      $('#tanggal').datepicker({
            autoclose: true,
            todayHighlight: true,
            format: 'yyyy-mm-dd'
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

      $(document).on('click', '.btn-remove', function(){
          var button_id = $(this).attr("id");
          var index = -1;
          var filteredObj = itemArray.find(function(item, i){
            var idobat = parseInt(button_id);
            if(item.obatId === idobat){
              index = i;
              return i;
            }
          });
          if(itemArray.length === 1){
            itemArray = [];
          }
          else{
            itemArray.splice(index,index);
          }
          $("#row"+button_id+"").remove();
          calc_total();
      })
})

$("#btnAdd").click(function(){
    var idObat = $("#cboObat").val();
    var qty = $("#txtQtyItem").val();
    if(idObat === null || idObat === undefined || idObat === ""){
        alert("Data Obat Harus Dipilih Terlebih dahulu");
    }
    else if(qty === null || qty === undefined || qty === ""){
        alert("Qty Obat Harus Disi");
    }
    else if(parseInt(qty) <= 0){
        alert("Qty Obat Harus Disi Minimal 1");
    }
    else{
        $.ajax({
                    type:"GET",
                    url:get_uri() + "/api/obat/getById/"+idObat,
                    contentType:"application/json",
                    success:function(data){
                        if(parseInt(qty) >= data.qty){
                            alert("Qty Obat " + data.nameObat + " yang dibeli Melebihi qty yang ada di stock")
                            return false;
                        }
                        if(itemArray.length != 0){
                              var cekIdObat = $.grep(itemArray, function (n, i) {
                                  return n.obatId == idObat;
                              });

                              if(cekIdObat.length === 0 ){
                                $("#itemKasir").append("<tr id='row"+data.id+"'> <td><span>"+data.nameObat+"</span></td> <td><span>"+$("#txtQtyItem").val()+"</span></td> <td><span class='total'>"+data.hargaJual * parseInt($("#txtQtyItem").val())+"</span></td> <td><button type='button' class='btn btn-danger btn-remove' id="+data.id+">X</button></td> </tr>");
                                     itemArray.push({
                                       'unitPrice': data.hargaJual * parseInt($("#txtQtyItem").val()),
                                       'qty': $("#txtQtyItem").val(),
                                       'obatId' : data.id
                                 });
                              }
                              else{
                                alert("Nama Obat Sudah Ada Di Daftar Item");
                              }
                        }
                        else{
                           $("#itemKasir").append("<tr id='row"+data.id+"'> <td><span>"+data.nameObat+"</span></td> <td><span>"+$("#txtQtyItem").val()+"</span></td> <td><span class='total'>"+data.hargaJual * parseInt($("#txtQtyItem").val())+"</span></td> <td><button type='button' class='btn btn-danger btn-remove' id="+data.id+">X</button></td> </tr>");
                           itemArray.push({
                              'unitPrice': data.hargaJual * parseInt($("#txtQtyItem").val()),
                              'qty': $("#txtQtyItem").val(),
                              'obatId' : data.id
                           });
                        }
                            $("#txtQtyItem").val("");
                            var obatSelect = $('#cboObat');
                            var option = new Option("", "", true, true);
                            obatSelect.append(option).trigger('change');
                            obatSelect.trigger({
                               type: 'select2:select'
                            });

                        calc_total();
                    }
                })
    }
});

function calc_total(){
  var sum = 0;
  $(".total").each(function(){
    sum += parseFloat($(this).text());
  });
  $('#sum').text(sum);
}

$("#btnSimpan").click(function(){
    if(itemArray.length === 0){
        alert("Silahkan Isi Item Obat Terlebih Dahulu !");
    }
    else{
        parameter.tanggal = $("#tanggal").val();
        parameter.price = parseFloat($("#sum").text());
        parameter.userId = parseInt($("#user_id").text());
        parameter.orderDetailDTOS = itemArray;

        $.ajax({
              type:"POST",
              url:get_uri() + "/api/kasir/orders",
              data:JSON.stringify(parameter),
              contentType:"application/json",
                        success:function(data){
                            var obj = JSON.parse(data);
                            if(obj.status === "Success"){
                                 $.toast({
                                   heading: 'Success',
                                   text: 'Data Order Berhasil Disimpan',
                                   position: 'top-right',
                                   stack: false,
                                   icon: 'success'
                                 });
                                 $("#itemKasir > tr").empty();
                                 itemArray = [];
                                 calc_total();
                            }
                            else{
                               $.toast({
                                   heading: 'Error',
                                   text: obj.message,
                                   position: 'top-right',
                                   stack: false,
                                   icon: 'error'
                               });
                               $("#itemKasir > tr").empty();
                               itemArray = [];
                               calc_total();
                           }
                  }
              })
    }
})