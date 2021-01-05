package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
<<<<<<< HEAD
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
=======
    public String hello(Model model){
        model.addAttribute("data","hello!!");
>>>>>>> e8d7ca10d5be7b00d94cf4b615b5199542d8bd70
        return "hello";
    }
}
