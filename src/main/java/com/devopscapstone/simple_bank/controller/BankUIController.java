package com.devopscapstone.simple_bank.controller;

import com.devopscapstone.simple_bank.service.BankAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Controller
public class BankUIController {

    private final BankAccountService bankService;

    public BankUIController(BankAccountService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("balance", bankService.getBalance());
        return "index";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam BigDecimal amount) {
        bankService.deposit(amount);
        return "redirect:/";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam BigDecimal amount, Model model) {
        try {
            bankService.withdraw(amount);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("balance", bankService.getBalance());
            return "index";
        }
    }
}
