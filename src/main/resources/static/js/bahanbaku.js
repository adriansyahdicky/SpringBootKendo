var parameter;

$(document).ready(function () {
        getBahanBakus();

        parameter = {
             id : null,
             namaBahan : null,
             qty : null,
             hargaBeli : null
        }

});

function getBahanBakus(){
    var url = get_uri() + '/api/bahan-bakus';
    $('#tblBahanBaku').DataTable( {
            "ajax": {
                "url": url,
                "dataSrc": ""
            },
            "columns": [
                { "data": "namaBahan" },
                { "data": "qty" },
                { "data": "hargaBeli" },
                { "data": "tanggalBeli" },
                { "data": "tanggalUpdate" },
                {
                   "data": 'id',
                   "bSortable": false,
                   "mRender":function(data, type, row){
                       var htmlStr = "";
                       htmlStr += '<button onclick="findBahanBakuById('+data+');" class="btn btn-sm btn-primary">Edit</button>&nbsp;';
                       htmlStr += '<button onclick="deleteBahanBaku('+data+');" class="btn btn-sm btn-danger">Delete</button>';
                       return htmlStr;
                   }
                }
            ]
        } );
}


$("#btnSaveBahanBaku").click(function(){
    parameter.id = $("#BahanBakuId").val();
    parameter.namaBahan = $("#namaBahan").val();
    parameter.qty = $("#qty").val();
    parameter.hargaBeli = $("#hargaBeli").val();
    if($("#BahanBakuId").val() === ""){
        $.ajax({
                type:"POST",
                url:get_uri() + "/api/bahan-bakus",
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
                            text: 'Data Bahan Baku Berhasil Disimpan',
                            position: 'top-right',
                            stack: false,
                            icon: 'success'
                        })
                        $("#tblBahanBaku").DataTable().ajax.reload();
                        $("#modal-bahan").modal("hide");
                    }
                }
            })
    }
    else{

                    $.ajax({
                            type:"PUT",
                            url:get_uri() + "/api/bahan-bakus",
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
                                        text: 'Data Bahan Baku Berhasil Dirubah',
                                        position: 'top-right',
                                        stack: false,
                                        icon: 'success'
                                    })
                                    $("#tblBahanBaku").DataTable().ajax.reload();
                                    $("#modal-bahan").modal("hide");
                                }
                            }
                        })

        }
});

$("#addBahanBaku").click(function(){
    $("#modal-bahan").modal("show");
    $("#menu-title").text("Create Bahan Baku");
    clearForm();
});

function findBahanBakuById(id){
    $.ajax({
                type:"GET",
                url:get_uri() + "/api/bahan-bakus/"+id+"/by-id",
                contentType:"application/json",
                success:function(data){
                    $("#BahanBakuId").val(data.id);
                    $("#namaBahan").val(data.namaBahan);
                    $("#qty").val(data.qty);
                    $("#hargaBeli").val(data.hargaBeli);
                    $("#bahan-title").text("Update Bahan Baku");
                    $("#modal-bahan").modal("show");
                }
            })
}

function clearForm(){
    $("#BahanBakuId").val("");
    $("#namaBahan").val("");
    $("#qty").val("");
    $("#hargaBeli").val("");
}

function deleteBahanBaku(id) {
    swal({
        title: "Are you sure?",
        text: "Deleted Bahan Baku !",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    type: "DELETE",
                    url: get_uri() + "/api/bahan-bakus/" + id,
                    success: function (data) {
                        var obj = JSON.parse(data);
                        swal("Message", obj.status, "success").then((value) => {
                            if (value) {
                                 $("#tblBahanBaku").DataTable().ajax.reload();
                            }
                        });
                    }
                })
            }
        });
}
