package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

		@RequestMapping("/hello")
		public String index(Model model)
		{
			int num = 10;
			String passowrd = "1234";
			
			model.addAttribute("num", num);
			model.addAttribute("password", passowrd);
			
			return "index";
			
		}
}