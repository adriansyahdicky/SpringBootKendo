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

         $(document).on('click', '.btn-remove', function(){
                  var button_id = $(this).attr("id");
                  var index = -1;
                  var filteredObj = itemArray.find(function(item, i){
                    var menuId = parseInt(button_id);
                    if(item.menuId === menuId){
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
});

$("#btnAdd").click(function(){
    var cboMenu = $("#cboMenu").val();
    var qty = $("#txtQtyItem").val();
    if(cboMenu === null || cboMenu === undefined || cboMenu === ""){
        alert("Data Menu Harus Dipilih Terlebih dahulu");
    }
    else if(qty === null || qty === undefined || qty === ""){
        alert("Qty Menu Harus Disi");
    }
    else if(parseInt(qty) <= 0){
        alert("Qty Menu Harus Disi Minimal 1");
    }
    else{
        $.ajax({
                    type:"GET",
                    url:get_uri() + "/api/menu/getById/"+cboMenu,
                    contentType:"application/json",
                    success:function(data){

                        if(itemArray.length != 0){
                              var cekIdMenu = $.grep(itemArray, function (n, i) {
                                  return n.menuId == cboMenu;
                              });

                              if(cekIdMenu.length === 0 ){
                                $("#itemKasir").append("<tr id='row"+data.id+"'> <td><span>"+data.namaMenu+"</span></td> <td><span>"+$("#txtQtyItem").val()+"</span></td> <td><span class='total'>"+data.harga * parseInt($("#txtQtyItem").val())+"</span></td> <td><button type='button' class='btn btn-danger btn-remove' id="+data.id+">X</button></td> </tr>");
                                     itemArray.push({
                                       'qty': $("#txtQtyItem").val(),
                                       'menuId' : data.id
                                 });
                              }
                              else{
                                alert("Nama Menu Sudah Ada Di Daftar Item");
                              }
                        }
                        else{
                           $("#itemKasir").append("<tr id='row"+data.id+"'> <td><span>"+data.namaMenu+"</span></td> <td><span>"+$("#txtQtyItem").val()+"</span></td> <td><span class='total'>"+data.harga * parseInt($("#txtQtyItem").val())+"</span></td> <td><button type='button' class='btn btn-danger btn-remove' id="+data.id+">X</button></td> </tr>");
                           itemArray.push({
                              'qty': $("#txtQtyItem").val(),
                              'menuId' : data.id
                           });
                        }
                            $("#txtQtyItem").val("");
                            var obatSelect = $('#cboMenu');
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
        alert("Silahkan Isi Item Menu Terlebih Dahulu !");
    }
    else if($("#cboMetode").val() === ""){
        alert("Harap memilih metode pembayaran anda !");
    }
    else{
        parameter.catatan = $("#catatan").val();
        parameter.totalBayar = parseFloat($("#sum").text());
        parameter.userId = parseInt($("#user_id").text());
        parameter.tipePembayaran = $("#cboMetode").val();
        parameter.transaksiDetailDtos = itemArray;

        $.ajax({
              type:"POST",
              url:get_uri() + "/api/transaksi/saveTransaksi",
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
                                 $("#cboMetode").val("");
                                 $("#catatan").val("");
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
                               $("#catatan").val("");
                               itemArray = [];
                               calc_total();
                           }
                  }
              })
    }
})