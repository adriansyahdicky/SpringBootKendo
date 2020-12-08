$(document).ready(function () {
    GenerateGrid();
})


function GenerateGrid() {
    $("#gridview").empty();
    $("#gridview").append("<div id='gvStockObat' style='width:100%;'></div>");

        var dataSource = new kendo.data.DataSource({
            transport: {
                read: {
                    url: get_uri() + "/api/laporan/stockobat",
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

        grid = $("#gvStockObat").kendoGrid({
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
                field: "nameObat",
                title: "Nama Obat",
                filterable: false,
                width: 300
            }, {
                field: "qty",
                title: "Stock Obat",
                filterable: false,
                width: 300
            }, {
                field: "kategori.nameKategori",
                title: "Kategori Obat",
                filterable: false,
                width: 100
            }],
            dataBound: function (e) {
            },
            dataBinding: function () {
                No = (this.dataSource.page() - 1) * this.dataSource.pageSize();
            }
        }).data("kendoGrid");

}