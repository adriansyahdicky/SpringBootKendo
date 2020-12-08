var itemArray = [];
var parameter;
$(document).ready(function() {

         var date = new Date();
         var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());

         parameter = {
                         tanggal : null,
                         price : null,
                         supplierId : null,
                         orderDetailDTOS: null
                 }

        $('#tanggalrequest').datepicker({
                    autoclose: true,
                    todayHighlight: true,
                    format: 'yyyy-mm-dd'
        });

        $( '#tanggalrequest' ).datepicker( 'setDate', today );

       $("#cboSupplier").select2({
                      placeholder: "-Selected Supplier-",
                      minimumInputLength: 2,
                      ajax:{
                          url: get_uri() + '/api/supplier/searchSupplierByName',
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
                            });
           getPembelians();
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
                        if(itemArray.length != 0){
                              var cekIdObat = $.grep(itemArray, function (n, i) {
                                  return n.obatId == idObat;
                              });

                              if(cekIdObat.length === 0 ){
                                $("#itemPembelian").append("<tr id='row"+data.id+"'> <td><span>"+data.nameObat+"</span></td> <td><span>"+$("#txtQtyItem").val()+"</span></td> <td><span class='total'>"+data.hargaSupplier * parseInt($("#txtQtyItem").val())+"</span></td> <td><button type='button' class='btn btn-danger btn-remove' id="+data.id+">X</button></td> </tr>");
                                     itemArray.push({
                                       'unitPrice': data.hargaSupplier * parseInt($("#txtQtyItem").val()),
                                       'qty': $("#txtQtyItem").val(),
                                       'obatId' : data.id
                                 });
                              }
                              else{
                                alert("Nama Obat Sudah Ada Di Daftar Item");
                              }
                        }
                        else{
                           $("#itemPembelian").append("<tr id='row"+data.id+"'> <td><span>"+data.nameObat+"</span></td> <td><span>"+$("#txtQtyItem").val()+"</span></td> <td><span class='total'>"+data.hargaSupplier * parseInt($("#txtQtyItem").val())+"</span></td> <td><button type='button' class='btn btn-danger btn-remove' id="+data.id+">X</button></td> </tr>");
                           itemArray.push({
                              'unitPrice': data.hargaSupplier * parseInt($("#txtQtyItem").val()),
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
    else if($("#cboSupplier").val() === "" || $("#cboSupplier").val() === null || $("#cboSupplier").val() === undefined){
        alert("Supplier Belum Dipilih !");
    }
    else{
        parameter.tanggal = $("#tanggalrequest").val();
        parameter.price = parseFloat($("#sum").text());
        parameter.supplierId = parseInt($("#cboSupplier").val());
        parameter.orderDetailDTOS = itemArray;

        $.ajax({
              type:"POST",
              url:get_uri() + "/api/pembelian/requestbarang",
              data:JSON.stringify(parameter),
              contentType:"application/json",
                        success:function(data){
                            var obj = JSON.parse(data);
                            if(obj.status === "Success"){
                                 $.toast({
                                   heading: 'Success',
                                   text: 'Data Request Berhasil Disimpan',
                                   position: 'top-right',
                                   stack: false,
                                   icon: 'success'
                                 });
                                 $("#itemPembelian > tr").empty();
                                 itemArray = [];
                                 calc_total();
                                 var supplierSelect = $('#cboSupplier');
                                 var option = new Option("", "", true, true);
                                 supplierSelect.append(option).trigger('change');
                                 supplierSelect.trigger({
                                       type: 'select2:select'
                                 });
                            }
                            else{
                               $.toast({
                                   heading: 'Error',
                                   text: obj.message,
                                   position: 'top-right',
                                   stack: false,
                                   icon: 'error'
                               });
                               $("#itemPembelian > tr").empty();
                               itemArray = [];
                               calc_total();
                               var supplierSelect = $('#cboSupplier');
                                                                var option = new Option("", "", true, true);
                                                                supplierSelect.append(option).trigger('change');
                                                                supplierSelect.trigger({
                                                                      type: 'select2:select'
                                                                });
                           }
                  }
              })
    }
})

function getPembelians(){
    var url = get_uri() + '/api/pembelian/getPembelians';
    $('#tblRequestBarangKeluar').DataTable( {
            "ajax": {
                "url": url,
                "dataSrc": ""
            },
            "columns": [
                { "data": "tanggal" },
                { "data": "totalPrice" },
                { "data": "supplier.nameSupplier" },
                { "data": "status" },
                {
                   "data": 'id',
                   "bSortable": false,
                   "mRender":function(data, type, row){
                       var htmlStr = "";
                       htmlStr += '<button onclick="findPembelianById('+data+');" class="btn btn-sm btn-primary">Detail</button>&nbsp;';
                       htmlStr += '<button onclick="Approved('+data+');" class="btn btn-sm btn-success">Approved</button>&nbsp;';
                       return htmlStr;
                   }
                }
            ]
        } );
}

function findPembelianById(id){
    $.ajax({
                type:"GET",
                url:get_uri() + "/api/pembelian/getReturnRequestPembelian/"+id,
                contentType:"application/json",
                success:function(data){
                     $("#detailObat > tr").empty();
                     $("#txtTanggal").text(data.pembelian.tanggal);
                     $("#txtSupplier").text(data.pembelian.supplier.nameSupplier);
                     $("#txtTotal").text(formatRupiah(data.pembelian.totalPrice));

                    for(var i=0; i<data.pembelian_detail.length; i++){

                        $("#detailObat").append("<tr> <td><span>"+data.pembelian_detail[i].obats.nameObat+"</span></td> <td><span>"+data.pembelian_detail[i].qty+"</span></td> <td><span >"+formatRupiah(data.pembelian_detail[i].unitPrice)+"</span></td> </tr>");

                    }

                    $("#modal-detail").modal("show");
                }
            })
}

function Approved(id){
    $.ajax({
                type:"GET",
                url:get_uri() + "/api/pembelian/approved/"+id,
                contentType:"application/json",
                success:function(data){

                     var obj = JSON.parse(data);
                     if(obj.status === "Success"){
                          $.toast({
                                 heading: 'Success',
                                 position: 'top-right',
                                 text: 'Data Berhasil DiApproved',
                                 stack: false,
                                 icon: 'success'
                          });
                          $("#tblRequestBarangKeluar").DataTable().ajax.reload();

                     }

                }
            })
}