$(document).ready(function () {
    GenerateGrid();
})
function GenerateGrid() {
    $("#gridview").empty();
    $("#gridview").append("<div id='gvBahanBaku' style='width:100%;'></div>");

        var dataSource = new kendo.data.DataSource({
            transport: {
                read: {
                    url: get_uri() + "/api/bahan-bakus/grid",
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

        grid = $("#gvBahanBaku").kendoGrid({
            dataSource: dataSource,
            sortable: false,
            scrollable: true,
            toolbar: ['excel'],
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
                field: "namaBahan",
                title: "Nama Bahan",
                filterable: false,
                width: 200
            }, {
                field: "qty",
                title: "Stock Bahan",
                filterable: false,
                width: 50
            }, {
                field: "hargaBeli",
                title: "Harga Beli",
                filterable: false,
                width: 100
            },
             {
                            field: "tanggalBeli",
                            title: "Tanggal Beli",
                            filterable: false,
                            width: 50
                        },
                         {
                                        field: "tanggalUpdate",
                                        title: "Tanggal Update",
                                        filterable: false,
                                        width: 50
                                    }],
            dataBound: function (e) {
            },
            dataBinding: function () {
                No = (this.dataSource.page() - 1) * this.dataSource.pageSize();
            }
        }).data("kendoGrid");

}