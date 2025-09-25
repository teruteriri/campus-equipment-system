package edu.cit.carao.estellefelicity.campusequipmentloan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Welcome to Campus Equipment Loan System!";
    }

    @GetMapping("/secured")
    public String secured() {
        return "You are logged in!";
    }
}
