/**
 * Created by Administrator on 2016-09-28.
 */





$(document).ready(function() {
    $('#submit').click(function (e) {
        e.preventDefault();
        var ffrom = document.f.from.value;
        var amount = document.f.amount.value;
        var fto = document.f.to.value;

        fx.base = ffrom;
        fx.settings = {
            from: "EUR"
        };
        fx.settings.from = ffrom;
        /*            console.log(fx.base);
         console.log(fx.settings.from);
         console.log(ffrom);*/
        ; //in SolidShops, you could use: {{ product.price }}
        // Load exchange rates data via the cross-domain/AJAX proxy:
        console.log("blabla");
        $.getJSON(
            'https://openexchangerates.org/api/latest.json?app_id=226bf2bab8cd4bb29863c5cf86be73fb',
            function (data) {
                // Check money.js has finished loading:
                if (typeof fx !== "undefined" && fx.rates) {
                    fx.rates = data.rates;
                    fx.base = data.base;
                    console.log("sucess");
                } else {
                    console.log("fail load json");
                    var fxSetup = {
                        rates: data.rates,
                        base: data.base
                    }
                }

                // now that we have exchange rates, add a few to our page
                var Inobject = {};

                Inobject.to = fto;
                console.log(fto);
                var USD = fx.convert(amount, Inobject); //13.22784197768393


                // we can now use the accounting.js library to format the numbers properly
                USD = accounting.formatMoney(USD, "$ ", 2, ",", ".");
                console.log(USD);

                $("ul.currencies").append("<li>USD estimate: " + USD + "</li>");

            }
        );

        /*
         console.log(Inobject);
         */


    });


});
