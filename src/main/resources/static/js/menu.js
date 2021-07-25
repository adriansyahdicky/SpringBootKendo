var parameter;

$(document).ready(function () {
        getMenus();

        parameter = {
             id : null,
             namaMenu : null,
             deskripsiMenu : null,
             harga : null
        }

})

$("#btnSaveMenu").click(function(){
    parameter.id = $("#MenuId").val();
    parameter.namaMenu = $("#namaMenu").val();
    parameter.deskripsiMenu = $("#deskripsiMenu").val();
    parameter.harga = $("#harga").val();
    if($("#MenuId").val() === ""){
        //save

        $.ajax({
                type:"POST",
                url:get_uri() + "/api/menu/createMenu",
                data:JSON.stringify(parameter),
                contentType:"application/json",
                success:function(data){
                    debugger;
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
                            text: 'Data Menu Berhasil Disimpan',
                            position: 'top-right',
                            stack: false,
                            icon: 'success'
                        })
                        $("#tblMenu").DataTable().ajax.reload();
                        $("#modal-menu").modal("hide");
                    }
                }
            })
    }
    else{


                    $.ajax({
                            type:"POST",
                            url:get_uri() + "/api/menu/updateMenu",
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
                                        text: 'Data Menu Berhasil Dirubah',
                                        position: 'top-right',
                                        stack: false,
                                        icon: 'success'
                                    })
                                    $("#tblMenu").DataTable().ajax.reload();
                                    $("#modal-menu").modal("hide");
                                }
                            }
                        })

        }
})



function getMenus(){
    var url = get_uri() + '/api/menu/getMenus';
    $('#tblMenu').DataTable( {
            "ajax": {
                "url": url,
                "dataSrc": ""
            },
            "columns": [
                { "data": "namaMenu" },
                { "data": "deskripsiMenu" },
                { "data": "harga" },
                {
                   "data": 'id',
                   "bSortable": false,
                   "mRender":function(data, type, row){
                       var htmlStr = "";
                       htmlStr += '<button onclick="findMenuById('+data+');" class="btn btn-sm btn-primary">Edit</button>&nbsp;';
                       htmlStr += '<button onclick="deleteMenu('+data+');" class="btn btn-sm btn-danger">Delete</button>';
                       return htmlStr;
                   }
                }
            ]
        } );
}

$("#addMenu").click(function(){
    $("#modal-menu").modal("show");
    $("#menu-title").text("Create Menu");
    clearForm();
})

function findMenuById(id){
    $.ajax({
            type:"GET",
            url:get_uri() + "/api/menu/getById/"+id,
            contentType:"application/json",
            success:function(data){
                $("#MenuId").val(data.id);
                $("#namaMenu").val(data.namaMenu);
                $("#deskripsiMenu").val(data.deskripsiMenu);
                $("#harga").val(data.harga);
                $("#menu-title").text("Update Menu");
                $("#modal-menu").modal("show");
            }
        })
}

function deleteMenu(id) {
    swal({
        title: "Are you sure?",
        text: "Deleted Menu !",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    type: "POST",
                    url: get_uri() + "/api/menu/deleteMenu/" + id,
                    success: function (data) {
                        var obj = JSON.parse(data);
                        swal("Message", obj.status, "success").then((value) => {
                            if (value) {
                                 $("#tblMenu").DataTable().ajax.reload();
                            }
                        });
                    }
                })
            }
        });
}

function clearForm(){
    $("#MenuId").val("");
    $("#namaMenu").val("");
    $("#deskripsiMenu").val("");
    $("#harga").val("");
}
