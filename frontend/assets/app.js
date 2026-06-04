document.addEventListener("DOMContentLoaded", function(){
    const form = document.getElementById("bidForm");
    const bidInput = document.getElementById("bid_amount");
    const errorMsg = document.getElementById("error-msg");

    form.addEventListener("submit", function(event){
        errorMsg.textContent = "";
        const bidValue = bidInput.value;

        if (bidValue === "") {
            event.preventDefault();
            errorMsg.textContent = "Jumlah penawaran tidak boleh kosong.";
            return;
        }

        const numericBid = Number(bidValue);

        if (isNaN(numericBid)) {
            event.preventDefault();
            errorMsg.textContent = "Penawaran harus berupa angka.";
            return;
        }

        if (numericBid <= 0) {
            event.preventDefault();
            errorMsg.textContent = "Penawaran harus lebih besar dari 0.";
            return;
        }
    });
});