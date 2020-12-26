var itemArray = [];
var parameter;
var parameterUpdate;

$(document).ready(function() {

         var date = new Date();
         var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());

         parameter = {
                         tanggal : null,
                         price : null,
                         supplierId : null,
                         orderDetailDTOS: null
                 }

         parameterUpdate = {
            id_obat: null,
            id_pembelian: null,
            stok: null,
            unite_price: null,
            total_price: null,
            keterangan: null
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

                      $("#cboRak").select2({
                                          placeholder: "-Selected Rak-",
                                          minimumInputLength: 2,
                                          ajax:{
                                              url: get_uri() + '/api/rak/searchRakByName',
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
           $("#cboKategori").select2({
                       placeholder: "-Selected Kategori-",
                       minimumInputLength: 2,
                       ajax:{
                           url: get_uri() + '/api/kategori/searchKategoriByName',
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
                { "data": "totalPrice", render: $.fn.dataTable.render.number( ',', ',', 2, 'Rp' )},
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
                     $("#detailObat > tbody > tr").empty();
                     $("#txtTanggal").text(data.pembelian.tanggal);
                     $("#txtSupplier").text(data.pembelian.supplier.nameSupplier);
                     $("#txtTotal").text(formatRupiah(data.pembelian.totalPrice));

                    for(var i=0; i<data.pembelian_detail.length; i++){
                        var keterangan_detail = "";
                        if(data.pembelian_detail[i].keterangan != null){
                            keterangan_detail = data.pembelian_detail[i].keterangan;
                        }
                        $("#detailObat > tbody:last-child").append("<tr> <td class='nama_obat'>"+data.pembelian_detail[i].obats.nameObat+"</td> <td class='qty_obat'><input id='qty_ok' type='number' min='1' value="+data.pembelian_detail[i].qty+" size='4'></input></td> <td class='harga_obat'>"+formatRupiah(data.pembelian_detail[i].unitPrice)+"</td> <td><textarea class='keterangan'>"+keterangan_detail+"</textarea></td> <td><button id='btn-"+data.pembelian_detail[i].obats.id+"' class='btn btn-primary' type='button' onclick='myFunction($(this), "+data.pembelian_detail[i].obats.id+", "+data.pembelian_detail[i].obats.hargaSupplier+", "+data.pembelian.id+")'>Hitung</button></td> </tr>");
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

$("#btnAddBarang").click(function(){
    if($("#cboObat").val() === "" || $("#cboObat").val() === null){
        $("#modal-obat").modal("show");
        $("#obat-title").text("Create Obat");
        clearForm();
    }
    else{
        var id = parseInt($("#cboObat").val());
        $.ajax({
                    type:"GET",
                    url:get_uri() + "/api/obat/getById/"+id,
                    contentType:"application/json",
                    success:function(data){
                        var setKategoriName = data.kategori.nameKategori + " - " + data.kategori.satuan
                        $("#ObatId").val(data.id);
                        $("#nameObat").val(data.nameObat);
                        $("#hargaJual").val(data.hargaJual);
                        $("#hargaSupplier").val(data.hargaSupplier);
                        $("#qty").val(data.qty);
                        var kategoriSelect = $('#cboKategori');
                        var option = new Option(setKategoriName, data.kategori.id, true, true);
                        kategoriSelect.append(option).trigger('change');
                        kategoriSelect.trigger({
                            type: 'select2:select'
                        });
                        $("#obat-title").text("Update Obat");
                        $("#modal-obat").modal("show");
                    }
                })
    }

})

function clearForm(){
    $("#ObatId").val("");
    $("#nameObat").val("");
    $("#hargaJual").val("");
    $("#hargaSupplier").val("");
    $("#qty").val("");
    var kategoriSelect = $('#cboKategori');
    var option = new Option("", "", true, true);
    kategoriSelect.append(option).trigger('change');
    kategoriSelect.trigger({
       type: 'select2:select'
    });
    var obatSelect = $('#cboObat');
        var option = new Option("", "", true, true);
        obatSelect.append(option).trigger('change');
        obatSelect.trigger({
           type: 'select2:select'
        });
}

$("#btnSaveObat").click(function(){
       if($("#cboObat").val() === "" || $("#cboObat").val() === null){
               //save

               parameter.nameObat = $("#nameObat").val();
               parameter.hargaJual = $("#hargaJual").val();
               parameter.hargaSupplier = $("#hargaSupplier").val();
               parameter.qty = $("#qty").val();
               parameter.idKategori = $("#cboKategori").val();


               $.ajax({
                       type:"POST",
                       url:get_uri() + "/api/obat/createObat",
                       data:JSON.stringify(parameter),
                       contentType:"application/json",
                       success:function(data){
                           var obj = JSON.parse(data);
                           if(obj.status === "failure"){
                               $.toast({
                                   heading: 'Error',
                                   text: obj.message,
                                   position: 'top-right',
                                   stack: false,
                                   icon: 'error'
                               })
                           }
                           else{
                               $.toast({
                                   heading: 'Success',
                                   text: 'Data Barang Berhasil Disimpan',
                                   position: 'top-right',
                                   stack: false,
                                   icon: 'success'
                               })
                               $("#modal-obat").modal("hide");
                           }
                       }
                   })
           }
           else{
               //edit
                       parameter.id = $("#cboObat").val();
                       parameter.nameObat = $("#nameObat").val();
                       parameter.hargaJual = $("#hargaJual").val();
                       parameter.hargaSupplier = $("#hargaSupplier").val();
                       parameter.qty = $("#qty").val();
                       parameter.idKategori = $("#cboKategori").val();


                       $.ajax({
                               type:"POST",
                               url:get_uri() + "/api/obat/updateObat",
                               data:JSON.stringify(parameter),
                               contentType:"application/json",
                               success:function(data){
                                   var obj = JSON.parse(data);
                                   if(obj.status === "failure"){
                                       $.toast({
                                           heading: 'Error',
                                           text: obj.message,
                                           position: 'top-right',
                                           stack: false,
                                           icon: 'error'
                                       })
                                       clearForm();
                                   }
                                   else{
                                       $.toast({
                                           heading: 'Success',
                                           text: 'Data Barang Berhasil Dirubah',
                                           position: 'top-right',
                                           stack: false,
                                           icon: 'success'
                                       })
                                       $("#modal-obat").modal("hide");
                                   }
                               }
                           })

           }

})

function calc_total_update(){
  var sum = 0;
  $(".harga_obat").each(function(){
    var angka = $(this).text();
    var hasil_replace = angka.replace(/,/g, '');
    sum += parseFloat(hasil_replace);
  });
  $('#txtTotal').text(formatRupiah(sum));
}


function myFunction(e, id_obat, harga_supplier, id_pembelian){
     var qty_obat = e.closest("tr").find("td:eq(1) input[type='number']").val();
     var keterangan = e.closest("tr").find("td:eq(3) input[type='text']").val();
     var hasil = parseInt(qty_obat) * harga_supplier;

     e.closest("tr").find(".harga_obat").text(formatRupiah(hasil));
     calc_total_update();

     parameterUpdate.id_obat = parseInt(id_obat);
     parameterUpdate.id_pembelian = parseInt(id_pembelian);
     parameterUpdate.stok = parseInt(qty_obat);
     parameterUpdate.unite_price = parseFloat(hasil);
     var replace_total = $('#txtTotal').text().replace(/,/g, '');
     parameterUpdate.total_price = parseFloat(replace_total);
     parameterUpdate.keterangan = keterangan;


                    $.ajax({
                            type:"POST",
                            url:get_uri() + "/api/pembelian/updatedetail",
                            data:JSON.stringify(parameterUpdate),
                            contentType:"application/json",
                            success:function(data){
                                var obj = JSON.parse(data);
                                if(obj.status === "failure"){
                                    $.toast({
                                        heading: 'Error',
                                        text: obj.message,
                                        position: 'top-right',
                                        stack: false,
                                        icon: 'error'
                                    });
                                }
                                else{
                                    $.toast({
                                        heading: 'Success',
                                        text: 'Data Pembelian Berhasil Diupdate',
                                        position: 'top-right',
                                        stack: false,
                                        icon: 'success'
                                    });
                                    $("#tblRequestBarangKeluar").DataTable().ajax.reload();
                                }
                            }
                        })

}