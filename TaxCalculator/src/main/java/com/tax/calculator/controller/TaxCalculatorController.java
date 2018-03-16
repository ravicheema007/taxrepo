package com.tax.calculator.controller;

import com.tax.calculator.domain.Salary;
import com.tax.calculator.domain.Tax;
import com.tax.calculator.service.TaxCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaxCalculatorController {

    @Autowired
    TaxCalculatorService taxCalculatorService;

    @Autowired
    Salary salaryobj;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String calculateTax(@RequestParam(value="salary", required=false)  Double salary,
                           @RequestParam(value = "year", required = false) String year,
                           Model model) {
        if (salary != null && year != null) {
            //TaxCalculatorService taxCalculatorService = new TaxCalculatorService();
            //Salary salaryobj = new Salary();
            salaryobj.setSalary(salary);
            salaryobj.setYear(year);
            Tax tax = taxCalculatorService.getTotalTax(salaryobj);
            model.addAttribute("incomeTax", tax.getIncomeTax());

            model.addAttribute("socialSecurityTax", tax.getSocialSecurityTax());

            model.addAttribute("totalTax", tax.getTotalTax());

        }
        return "index";
    }


    @RequestMapping(value = "/new-customer", method = RequestMethod.GET)
    public String registerUser() {
        return "register";
    }


}