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

    @RequestMapping(method = RequestMethod.GET, path = "/createUser")
    public ModelAndView createUser () {
        ModelAndView modelAndView = new ModelAndView("createUser");
        return modelAndView;
    }
    @RequestMapping(method = RequestMethod.POST, path = "/addUser")
    public ModelAndView addUser (@RequestParam String firstName, @RequestParam String lastName,
                                 @RequestParam String username, @RequestParam String password) {
        rep.addUser(firstName,lastName,username,password);
        ModelAndView modelAndView = new ModelAndView("success");
        return modelAndView;
    }
}
