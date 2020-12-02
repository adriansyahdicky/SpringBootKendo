var parameter;

$(document).ready(function () {
        getKategoris();
        $('.select2').select2();

        parameter = {
             id : null,
             nameKategori : null,
             satuan : null
        }
})

function getKategoris(){
    var url = get_uri() + '/api/kategori/getKategoris';
    $('#tblKategori').DataTable( {
            "ajax": {
                "url": url,
                "dataSrc": ""
            },
            "columns": [
                { "data": "nameKategori" },
                { "data": "satuan" },
                {
                   "data": 'id',
                   "bSortable": false,
                   "mRender":function(data, type, row){
                       var htmlStr = "";
                       htmlStr += '<button onclick="findKategoriById('+data+');" class="btn btn-sm btn-primary">Edit</button>&nbsp;';
                       htmlStr += '<button onclick="deleteKategori('+data+');" class="btn btn-sm btn-danger">Delete</button>';
                       return htmlStr;
                   }
                }
            ]
        } );
}

$("#addKategori").click(function(){
    $("#modal-kategori").modal("show");
    clearForm();
})

$("#btnSaveCategory").click(function(){
    parameter.nameKategori = $("#nameKategori").val();
    parameter.satuan = $("#satuan").val();

    $.ajax({
        type:"POST",
        url:get_uri() + "/api/kategori/createKategori",
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
                $("#tblKategori").DataTable().ajax.reload();
                $("#modal-kategori").modal("hide");
            }
        }
    })
})

function findKategoriById(id){
    $.ajax({
            type:"GET",
            url:get_uri() + "/api/kategori/getById/"+id,
            contentType:"application/json",
            success:function(data){
                $("#idKategori").val(data.id);
                $("#nameKategoriEdit").val(data.nameKategori);
                $('#satuanEdit').val(data.satuan);
                $('#satuanEdit').trigger('change');
                $("#modal-kategori-edit").modal("show");
            }
        })
}

function clearForm(){
    $("#nameKategori").val("");
    $("#satuan").val("");
}

$("#btnEditCategory").click(function(){
    parameter.id = $("#idKategori").val();
    parameter.nameKategori = $("#nameKategoriEdit").val();
    parameter.satuan = $("#satuanEdit").val();

    $.ajax({
        type:"POST",
        url:get_uri() + "/api/kategori/updateKategori",
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
                $("#tblKategori").DataTable().ajax.reload();
                $("#modal-kategori-edit").modal("hide");
            }
        }
    })
})

function deleteKategori(id) {
    swal({
        title: "Are you sure?",
        text: "Deleted Kategori !",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    type: "POST",
                    url: get_uri() + "/api/kategori/deleteKategori/" + id,
                    success: function (data) {
                        debugger;
                        var obj = JSON.parse(data);
                        swal("Message", obj.status, "success").then((value) => {
                            if (value) {
                                 debugger;
                                 $("#tblKategori").DataTable().ajax.reload();
                            }
                        });
                    }
                })
            }
        });
}