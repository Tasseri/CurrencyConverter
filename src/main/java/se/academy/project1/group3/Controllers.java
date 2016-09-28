package se.academy.project1.group3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;

/**
 * Created by Administrator on 9/28/2016.
 */

@Controller
public class Controllers {

    @Autowired
    DataSource dataSource;

    double usdSek = 1.50;

    @RequestMapping(method = RequestMethod.GET, path = "/converter")
    public ModelAndView convert(@RequestParam long amount, @RequestParam String from, @RequestParam String to) {
        ModelAndView modelAndView = new ModelAndView("converter");
        modelAndView.addObject("amount", amount)
                    .addObject("from", from)
                    .addObject("to", to);
        modelAndView.addObject("result", "" + usdSek);
        return modelAndView;
    }
}
