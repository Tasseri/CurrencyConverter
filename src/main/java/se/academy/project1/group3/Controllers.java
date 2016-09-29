package se.academy.project1.group3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 9/28/2016.
 */

@Controller
public class Controllers {
    @Autowired
    Repository rep;

    @RequestMapping(method = RequestMethod.GET, path = "/top5")
    public ModelAndView top5() {
        ModelAndView modelAndView = new ModelAndView("top5");

        List<Transaction> trans = rep.listTransactions();
        modelAndView.addObject("trans", trans);
        return modelAndView;
    }
}
