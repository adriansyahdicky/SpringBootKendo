var parameter;

$(document).ready(function () {
        getRaks();

        parameter = {
             id : null,
             nameRak : null
        }
})

function getRaks(){
    var url = get_uri() + '/api/rak/getRaks';
    $('#tblRak').DataTable( {
            "ajax": {
                "url": url,
                "dataSrc": ""
            },
            "columns": [
                { "data": "nameRak" },
                {
                   "data": 'id',
                   "bSortable": false,
                   "mRender":function(data, type, row){
                       var htmlStr = "";
                       htmlStr += '<button onclick="findRakById('+data+');" class="btn btn-sm btn-primary">Edit</button>&nbsp;';
                       htmlStr += '<button onclick="deleteRak('+data+');" class="btn btn-sm btn-danger">Delete</button>';
                       return htmlStr;
                   }
                }
            ]
        } );
}

$("#addRak").click(function(){
    $("#modal-rak").modal("show");
    clearForm();
})

$("#btnSaveRak").click(function(){
    parameter.nameRak = $("#nameRak").val();

    $.ajax({
        type:"POST",
        url:get_uri() + "/api/rak/createRak",
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
                    text: 'Data Rak Berhasil Disimpan',
                    position: 'top-right',
                    stack: false,
                    icon: 'success'
                })
                $("#tblRak").DataTable().ajax.reload();
                $("#modal-rak").modal("hide");
            }
        }
    })
})

function findRakById(id){
    $.ajax({
            type:"GET",
            url:get_uri() + "/api/rak/getById/"+id,
            contentType:"application/json",
            success:function(data){
                $("#idRak").val(data.id);
                $("#nameRakEdit").val(data.nameRak);
                $("#modal-rak-edit").modal("show");
            }
        })
}

function clearForm(){
    $("#nameRak").val("");
}

$("#btnEditRak").click(function(){
    parameter.id = $("#idRak").val();
    parameter.nameRak = $("#nameRakEdit").val();

    $.ajax({
        type:"POST",
        url:get_uri() + "/api/rak/updateRak",
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
                    text: 'Data Rak Berhasil Dirubah',
                    position: 'top-right',
                    stack: false,
                    icon: 'success'
                })
                $("#tblRak").DataTable().ajax.reload();
                $("#modal-rak-edit").modal("hide");
            }
        }
    })
})

function deleteRak(id) {
    swal({
        title: "Are you sure?",
        text: "Deleted Rak !",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    type: "POST",
                    url: get_uri() + "/api/rak/deleteRak/" + id,
                    success: function (data) {
                        debugger;
                        var obj = JSON.parse(data);
                        swal("Message", obj.status, "success").then((value) => {
                            if (value) {
                                 $("#tblRak").DataTable().ajax.reload();
                            }
                        });
                    }
                })
            }
        });
}