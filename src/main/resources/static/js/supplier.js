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
            },
            batch: true,
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
            }, {
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
                var oke = "oke";
                debugger;
            },
            dataBinding: function () {
                No = (this.dataSource.page() - 1) * this.dataSource.pageSize();
            }
        }).data("kendoGrid");

}