package se.academy.project1.group3;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/xxx.mvc", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getValues(@RequestBody Map<String, Object> pvmValues, final HttpServletRequest request, final HttpServletResponse response) {
        long result = (long)Double.parseDouble(pvmValues.get("result").toString());
        long userId = 1;
        long amount = (long)Double.parseDouble(pvmValues.get("amount").toString());
        String from = pvmValues.get("from").toString();
        String to = pvmValues.get("to").toString();
        System.out.println(result);
        rep.addTransaction(userId, from, to, amount, result);
        System.out.println(result);
        System.out.println("Wazzup");
        return pvmValues;

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
    @RequestMapping(method = RequestMethod.GET, path = "/topTrans")
    public ModelAndView topTrans() {
        ModelAndView modelAndView = new ModelAndView("topTrans");

        List<TopTransaction> trans = rep.topTransactions();
        modelAndView.addObject("trans", trans);
        return modelAndView;
    }
    @GetMapping("/main")
    public String index() {
        return "redirect:/";
    }
}
