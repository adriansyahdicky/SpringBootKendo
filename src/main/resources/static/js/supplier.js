$(document).ready(function () {
    GenerateGrid();
})


function GenerateGrid() {
    $("#gridview").empty();
    $("#gridview").append("<div id='gvSupplier' style='width:100%;'></div>");

        var dataSource = new kendo.data.DataSource({
            transport: {
                read: {
                    url: get_uri() + "/api/supplier/getSuppliers",
                    dataType: "json",
                    type: "post"
                },
                 parameterMap: function (data) {
                            // Mapping between Spring data pagination and kendo UI pagination parameters
                            // Pagination
                            var serverUrlParams = {
                              // pagination
                              size: data.pageSize,
                              page: data.page = data.page - 1// as Spring page starts from 0
                            };

                            // Sorting
                            if (data.sort && data.sort.length > 0)
                              serverUrlParams.sort = data.sort[0].field + ',' + data.sort[0].dir;

                            return serverUrlParams;
                }
            },
            batch: true,
            page: 0,
            pageSize: 20,
            serverPaging: true,
            schema: {
                data: "data",
                total: "total_rows",
            }
        });

        grid = $("#gvSupplier").kendoGrid({
            dataSource: dataSource,
            sortable: false,
            scrollable: true,
            toolbar: [{ template: '<a class="btn btn-info" id="btnAdd" onclick="CreateSupplier()"><i class="fa fa-plus-circle fa-fw"></i>Add New Record</a>' }],
            //height: 500,
            pageable: {
                refresh: true,
                pageSizes: true,
                buttonCount: 5
            },
            columns: [{
                title: "No",
                template: '<div align="center">#= ++No #</div>',
                width: 50
            },{
                field: "id",
                hidden: true
            },{
                field: "nameSupplier",
                title: "Nama Supplier",
                filterable: false,
                width: 300
            }, {
                field: "alamat",
                title: "Alamat",
                filterable: false,
                width: 300
            }, {
                field: "telepon",
                title: "Telepon",
                filterable: false,
                width: 100
            },
            {
               filterable: false,
               template: '<a class="btn btn-primary k-button k-grid-Edit" data-toggle="tooltip" title="Edit" onclick="EditSupplier($(this))"><i class="fa fa-pencil fa-fw"></i></a> <a class="btn btn-danger k-button k-grid-myDelete" data-toggle="tooltip" title="Delete" onclick="DeleteSupplier($(this))"><i class="fa fa-trash-o fa-fw"></i></a>',
               title: "Action",
               width: 150
            }],
            dataBound: function (e) {
            },
            dataBinding: function () {
                No = (this.dataSource.page() - 1) * this.dataSource.pageSize();
            }
        }).data("kendoGrid");

}

function clearForm(){
    $("#txtNameSupplier").val("");
    $("#txtTeleponSupplier").val("");
    $("#alamat").val("");
}

function CreateSupplier(){
        clearForm();
        var viewModel = kendo.observable({
            nameSupplier: $("#txtNameSupplier").val(),
            telepon: $("#txtTeleponSupplier").val(),
            alamat: $("#alamat").val(),
            isNew: true,
            saveMenu: function (e) {
                e.preventDefault();
                var data = kendo.observable({
                    nameSupplier: this.nameSupplier,
                    telepon: this.telepon,
                    alamat: this.alamat,
                    isNew: true,
                });
                saveSupplier(data);
            }
        });
        kendo.bind($("#SupplierModals"), viewModel);
        $("#supplier-title").text("Add Supplier");
        ShowHideModal("SupplierModals");

}

function saveSupplier(data) {
     $.ajax({
                    type:"POST",
                    url:get_uri() + "/api/supplier/createSupplier",
                    data:JSON.stringify(data),
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
                                text: 'Data Supplier Berhasil Disimpan',
                                position: 'top-right',
                                stack: false,
                                icon: 'success'
                            })
                            ShowHideModal("SupplierModals");
                            $("#gvSupplier").data('kendoGrid').dataSource.read();
                        }
                    }
                })

}

function EditSupplier(data) {
    var kendoGrid = $("#gvSupplier").data("kendoGrid");
    var dataItem = kendoGrid.dataItem($(data).closest("tr"));
    var idSupplier = dataItem.id;

    var viewModel = kendo.observable({
        nameSupplier: dataItem.nameSupplier,
        telepon: dataItem.telepon,
        alamat: dataItem.alamat,
        isNew: false,
        saveMenu: function (e) {
            e.preventDefault();
            var data = kendo.observable({
                nameSupplier: this.nameSupplier,
                telepon: this.telepon,
                alamat: this.alamat
            });
            updateSupplier(data, idSupplier);
        }
    });
    kendo.bind($("#SupplierModals"), viewModel);
    $("#supplier-title").text("Edit Supplier");
    ShowHideModal("SupplierModals");
}

function updateSupplier(data, id){

    $.ajax({
                        type:"POST",
                        url:get_uri() + "/api/supplier/updateSupplier/"+id,
                        data:JSON.stringify(data),
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
                                    text: 'Data Supplier Berhasil Dirubah',
                                    position: 'top-right',
                                    stack: false,
                                    icon: 'success'
                                })
                                ShowHideModal("SupplierModals");
                                $("#gvSupplier").data('kendoGrid').dataSource.read();
                            }
                        }
                    })

}

function DeleteSupplier(e) {
    var dataItem = $("#gvSupplier").data('kendoGrid').dataItem($(e).closest("tr"));

    var kendoWindow = $("<div />").kendoWindow({
        title: "<span style='color: #000'> Confirmation</span>",
        resizable: false,
        width: 400,
        modal: true
    });
    kendoWindow.data("kendoWindow").content($("#delete-confirmation").html()).center().open();
    var labelText = kendoWindow.find(".delete-message");
    labelText.text('Are you sure, that you want to delete Kategori "' + dataItem.nameSupplier + '" ?');
    kendoWindow.find(".delete-confirm,.delete-cancel").click(function () {
        if ($(this).hasClass("delete-confirm")) {

            $.ajax({
                                    type:"POST",
                                    url:get_uri() + "/api/supplier/deleteSupplier/"+dataItem.id,
                                    contentType:"application/json",
                                    success:function(data){
                                        var obj = JSON.parse(data);
                                        if(obj.status === "Success"){
                                            $("#gvSupplier").data('kendoGrid').dataSource.read();
                                             $.toast({
                                                 heading: 'Success',
                                                 text: 'Data Supplier Berhasil Dihapus',
                                                 position: 'top-right',
                                                 stack: false,
                                                 icon: 'success'
                                             })
                                        }

                                    }
                                })

        }
        kendoWindow.data("kendoWindow").close();
    }).end();
}