var parameter;

$(document).ready(function () {
        getObats();

        parameter = {
             id : null,
             nameObat : null,
             hargaJual : null,
             hargaSupplier : null,
             qty : null,
             idKategori : null,
             idRak: null
        }

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
})

$("#btnSaveObat").click(function(){
    if($("#ObatId").val() === ""){
        //save

        parameter.nameObat = $("#nameObat").val();
        parameter.hargaJual = $("#hargaJual").val();
        parameter.hargaSupplier = $("#hargaSupplier").val();
        parameter.qty = $("#qty").val();
        parameter.idKategori = $("#cboKategori").val();
        parameter.idRak = $("#cboRak").val();


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
                            text: 'Data Kategori Berhasil Disimpan',
                            position: 'top-right',
                            stack: false,
                            icon: 'success'
                        })
                        $("#tblObat").DataTable().ajax.reload();
                        $("#modal-obat").modal("hide");
                    }
                }
            })
    }
    else{
        //edit
                parameter.id = $("#ObatId").val();
                parameter.nameObat = $("#nameObat").val();
                parameter.hargaJual = $("#hargaJual").val();
                parameter.hargaSupplier = $("#hargaSupplier").val();
                parameter.qty = $("#qty").val();
                parameter.idKategori = $("#cboKategori").val();
                parameter.idRak = $("#cboRak").val();


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
                            }
                            else{
                                $.toast({
                                    heading: 'Success',
                                    text: 'Data Kategori Berhasil Dirubah',
                                    position: 'top-right',
                                    stack: false,
                                    icon: 'success'
                                })
                                $("#tblObat").DataTable().ajax.reload();
                                $("#modal-obat").modal("hide");
                            }
                        }
                    })

    }
})



function getObats(){
    var url = get_uri() + '/api/obat/getObats';
    $('#tblObat').DataTable( {
            "ajax": {
                "url": url,
                "dataSrc": ""
            },
            "columns": [
                { "data": "nameObat" },
                { "data": "hargaJual" },
                { "data": "hargaSupplier" },
                { "data": "qty" },
                { "data": "kategori.nameKategori" },
                { "data": "rak.nameRak" },
                { "data": "kategori.satuan" },
                {
                   "data": 'id',
                   "bSortable": false,
                   "mRender":function(data, type, row){
                       var htmlStr = "";
                       htmlStr += '<button onclick="findObatById('+data+');" class="btn btn-sm btn-primary">Edit</button>&nbsp;';
                       htmlStr += '<button onclick="deleteObat('+data+');" class="btn btn-sm btn-danger">Delete</button>';
                       return htmlStr;
                   }
                }
            ]
        } );
}

$("#addObat").click(function(){
    $("#modal-obat").modal("show");
    $("#obat-title").text("Create Barang");
    clearForm();
})

function findObatById(id){
    $.ajax({
            type:"GET",
            url:get_uri() + "/api/obat/getById/"+id,
            contentType:"application/json",
            success:function(data){
                var setKategoriName = data.kategori.nameKategori + " - " + data.kategori.satuan;
                var setRakName = data.rak.nameRak;
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

                var rakSelect = $('#cboRak');
                                var option = new Option(setRakName, data.rak.id, true, true);
                                rakSelect.append(option).trigger('change');
                                rakSelect.trigger({
                                    type: 'select2:select'
                                });
                $("#obat-title").text("Update Barang");
                $("#modal-obat").modal("show");
            }
        })
}

function deleteObat(id) {
    swal({
        title: "Are you sure?",
        text: "Deleted Barang !",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    type: "POST",
                    url: get_uri() + "/api/obat/deleteObat/" + id,
                    success: function (data) {
                        debugger;
                        var obj = JSON.parse(data);
                        swal("Message", obj.status, "success").then((value) => {
                            if (value) {
                                 $("#tblObat").DataTable().ajax.reload();
                            }
                        });
                    }
                })
            }
        });
}

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
    var rakSelect = $('#cboRak');
        var option = new Option("", "", true, true);
        rakSelect.append(option).trigger('change');
        rakSelect.trigger({
           type: 'select2:select'
        });
}
