/**
 * Created by Administrator on 2016-09-28.
 */



var result;

$(document).ready(function() {
    $('#submit').click(function (e) {
        e.preventDefault();
        var dictController = {};

        var ffrom = document.f.from.value;
        var amount = document.f.amount.value;
        var fto = document.f.to.value;
        var result = document.f.result.value;

        fx.base = ffrom;
        fx.settings = {
            from: "EUR"
        };
        fx.settings.from = ffrom;



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
                result = fx.convert(amount, Inobject); //13.22784197768393
                dictController.result = "" + Math.round(result * 100) / 100


                // we can now use the accounting.js library to format the numbers properly
                result = accounting.formatMoney(result, " ", 2, ",", ".");


                console.log(result);


                $("ul.currencies").append("<li> " + " " + fto+  " estimate: " + result + "</li>");

                console.log(result);
                dictController.from = ffrom;
                dictController.to = fto;

                dictController.amount = amount;
                console.log(dictController);


                $.ajax({
                    type : 'POST',
                    url : 'xxx.mvc',
                    data : JSON.stringify(dictController),
                    error : function(response) {
                        console.log("MVC Operation failed.");
                    },
                    success : function(response) {
                        console.log("successfully called MVC");
                    },
                    contentType : "application/json",
                    dataType : "json"
                });


            }
        );




    });


});
