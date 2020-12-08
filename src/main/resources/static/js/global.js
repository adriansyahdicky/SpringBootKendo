$(document).ready(function () {

    showTotalTransaksi();

});


function get_uri(){
    return window.location.origin;
}

function showTotalTransaksi(){
    var url = get_uri() + '/api/dashboard/getTotalTransaksiDateNow';
    $.ajax({
            type:"GET",
            url:url,
            contentType:"application/json",
            success:function(data){
                var obj = JSON.parse(data);
                if(obj.total_transaksi === null || obj.total_transaksi === "" || obj.total_transaksi === undefined){
                    $("#txtTotalTransaksi").text(0);
                }
                else{
                    var format_rp = formatRupiah(obj.total_transaksi);
                    $("#txtTotalTransaksi").text(format_rp);
                }

            }
        })
}

function formatRupiah(angka){
            var	number_string = angka.toString(),
            	sisa 	= number_string.length % 3,
            	rupiah 	= number_string.substr(0, sisa),
            	ribuan 	= number_string.substr(sisa).match(/\d{3}/g);

            if (ribuan) {
            	separator = sisa ? '.' : '';
            	rupiah += separator + ribuan.join('.');
            }
			return rupiah;
		}

function ShowHideModal(modalName) {
    if ($("#" + modalName + "").hasClass('in')) {

        $("#" + modalName + "").modal('hide');
    } else {
        $("#" + modalName + "").modal('show');
    }
}
