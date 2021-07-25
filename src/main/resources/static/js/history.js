$(document).ready(function () {
     getHistorys();
});

function getHistorys(){
    var url = get_uri() + '/api/history';
        $('#tblHistory').DataTable( {
                "ajax": {
                    "url": url,
                    "dataSrc": ""
                },
                "columns": [
                    { "data": "activity" },
                    { "data": "namaUser" },
                    { "data": "tanggalActivity" }
                ]
            } );
}