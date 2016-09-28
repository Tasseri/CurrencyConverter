package se.academy.project1.group3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * Created by Administrator on 9/28/2016.
 */

@Controller
public class Index {

    @Autowired
    DataSource dataSource;

//    @GetMapping("/")
//    public String index() {
//
//        return "index";
//    }
}
