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

                var Inobject = {};

                Inobject.to = fto;

                result = fx.convert(amount, Inobject);
                dictController.result = "" + Math.round(result * 100) / 100

                result = accounting.formatMoney(result, " ", 2, ",", ".");


                console.log(result);


                $("ul.currencies").append("<li>" +  ffrom + " ->" + " " + fto+  " estimate: " + result + "</li>");

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
                        console.log("Operation failed.");
                    },
                    success : function(response) {
                        console.log("ajax call succesfull");
                    },
                    contentType : "application/json",
                    dataType : "json"
                });


            }
        );




    });


});
