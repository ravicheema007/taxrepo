package com.tax.calculator.service;

import com.tax.calculator.domain.Salary;
import com.tax.calculator.domain.Tax;
import com.tax.calculator.util.SocialSecurityConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class TaxCalculatorServiceTest {

    @InjectMocks
    TaxCalculatorService taxCalculatorService;

    // Task 1 -- paid tax when income is below the Exemption card limit
    @Test
    public void getSocialSecurityTaxIfIncomeIsBelowExemptionCardLimit() {
        BigDecimal salary = new BigDecimal("54000");
        BigDecimal expectedTax  = new BigDecimal("0.00");
        assertThat(taxCalculatorService.getSocialSecurityComponent(SocialSecurityConstant.YEAR_2018, salary))
                .isEqualTo(expectedTax);

        assertThat(taxCalculatorService.getSocialSecurityComponent(SocialSecurityConstant.YEAR_2017, salary))
                .isEqualTo(expectedTax);
    }

    // Task 2 --How much social security tax do you need to pay if you earn 54000 and 60000
    @Test
    public void getSocialSecurityTaxIfEarn() {
        BigDecimal firstSalary = new BigDecimal("54000");
        BigDecimal secondSalary = new BigDecimal("60000");
        BigDecimal expectedTaxForFirstSalaryFor2018  = new BigDecimal("0.00");
        BigDecimal expectedTaxForSecondSalaryFor2018  = new BigDecimal("4920.00");
        BigDecimal expectedTaxForFirstSalaryFor2017  = new BigDecimal("0.00");
        BigDecimal expectedTaxForSecondSalaryFor2017  = new BigDecimal("4920.00");

        assertThat(taxCalculatorService.getSocialSecurityComponent(SocialSecurityConstant.YEAR_2018, firstSalary))
                .isEqualTo(expectedTaxForFirstSalaryFor2018);

        assertThat(taxCalculatorService.getSocialSecurityComponent(SocialSecurityConstant.YEAR_2018, secondSalary))
                .isEqualTo(expectedTaxForSecondSalaryFor2018);

        assertThat(taxCalculatorService.getSocialSecurityComponent(SocialSecurityConstant.YEAR_2017, firstSalary))
                .isEqualTo(expectedTaxForFirstSalaryFor2017);

        assertThat(taxCalculatorService.getSocialSecurityComponent(SocialSecurityConstant.YEAR_2017, secondSalary))
                .isEqualTo(expectedTaxForSecondSalaryFor2017);

    }

    // Task 3 -- How much social security tax do you need to pay if you earn 54000 and 60000
    @Test
    public void getSocialSecurityTaxIfEarnHighSalary() {
        BigDecimal firstSalary = new BigDecimal("75000");
        BigDecimal secondSalary = new BigDecimal("100000");
        BigDecimal expectedTaxForFirstSalary  = new BigDecimal("6150.00");
        BigDecimal expectedTaxForSecondSalary  = new BigDecimal("8200.00");

        assertThat(taxCalculatorService.getSocialSecurityComponent(SocialSecurityConstant.YEAR_2018, firstSalary))
                .isEqualTo(expectedTaxForFirstSalary);

        assertThat(taxCalculatorService.getSocialSecurityComponent(SocialSecurityConstant.YEAR_2018, secondSalary))
                .isEqualTo(expectedTaxForSecondSalary);
    }


    // Total tax test cases

    @Test
    public void getTotalTaxIfIncomeIsBelowExemptionCardLimit() {
        Salary salary = new Salary(50000.00, "2018");

        BigDecimal incomeTax = new BigDecimal("0.00");
        BigDecimal socialSecurityTax= new BigDecimal("0.00");
        BigDecimal totalTax= new BigDecimal("0.00");

        Tax expectedTax  = new Tax(incomeTax, socialSecurityTax);
        Tax actualTax = taxCalculatorService.getTotalTax(salary);

        expectedTax.generateTotalTax();
        actualTax.generateTotalTax();

        assertThat(actualTax.getIncomeTax()).isEqualTo(expectedTax.getIncomeTax());
        assertThat(actualTax.getSocialSecurityTax()).isEqualTo(expectedTax.getSocialSecurityTax());
        assertThat(actualTax.getTotalTax()).isEqualTo(expectedTax.getTotalTax());
    }


    @Test
    public void getTotalTaxIfEarn() {
        Salary firstSalary = new Salary(54000.00, "2018");
        Salary secondSalary = new Salary(60000.00, "2018");

        BigDecimal incomeTaxForFirstSalary = new BigDecimal("0.00");
        BigDecimal socialSecurityTaxForFirstSalary = new BigDecimal("0.00");
        BigDecimal totalTaxForFirstSalary = new BigDecimal("0.00");

        BigDecimal incomeTaxForSecondSalary = new BigDecimal("12592.50");
        BigDecimal socialSecurityTaxForSecondSalary = new BigDecimal("4920.00");
        BigDecimal totalTaxForSecondSalary = new BigDecimal("0.00");

        Tax expectedTaxForFirstSalary  = new Tax(incomeTaxForFirstSalary, socialSecurityTaxForFirstSalary);
        Tax expectedTaxForSecondSalary  = new Tax(incomeTaxForSecondSalary, socialSecurityTaxForSecondSalary);

        Tax actualTaxForFirstSalary = taxCalculatorService.getTotalTax(firstSalary);
        Tax actualTaxForSecondSalary = taxCalculatorService.getTotalTax(secondSalary);

        expectedTaxForFirstSalary.generateTotalTax();
        actualTaxForFirstSalary.generateTotalTax();
        expectedTaxForSecondSalary.generateTotalTax();
        actualTaxForSecondSalary.generateTotalTax();

        assertThat(actualTaxForFirstSalary.getIncomeTax()).isEqualTo(expectedTaxForFirstSalary.getIncomeTax());
        assertThat(actualTaxForFirstSalary.getSocialSecurityTax()).isEqualTo(expectedTaxForFirstSalary.getSocialSecurityTax());
        assertThat(actualTaxForFirstSalary.getTotalTax()).isEqualTo(expectedTaxForFirstSalary.getTotalTax());

        assertThat(actualTaxForSecondSalary.getIncomeTax()).isEqualTo(expectedTaxForSecondSalary.getIncomeTax());
        assertThat(actualTaxForSecondSalary.getSocialSecurityTax()).isEqualTo(expectedTaxForSecondSalary.getSocialSecurityTax());
        assertThat(actualTaxForSecondSalary.getTotalTax()).isEqualTo(expectedTaxForSecondSalary.getTotalTax());
    }

    @Test
    public void getTotalTaxIfEarnHighSalary() {
        Salary firstSalary = new Salary(75000.00, "2018");
        Salary secondSalary = new Salary(100000.00, "2018");

        BigDecimal incomeTaxForFirstSalary = new BigDecimal("12592.50");
        BigDecimal socialSecurityTaxForFirstSalary = new BigDecimal("6150.00");
        BigDecimal totalTaxForFirstSalary = new BigDecimal("18742.50");

        BigDecimal incomeTaxForSecondSalary = new BigDecimal("12592.50");
        BigDecimal socialSecurityTaxForSecondSalary = new BigDecimal("8200.00");
        BigDecimal totalTaxForSecondSalary = new BigDecimal("20792.50");

        Tax expectedTaxForFirstSalary  = new Tax(incomeTaxForFirstSalary, socialSecurityTaxForFirstSalary);
        Tax expectedTaxForSecondSalary  = new Tax(incomeTaxForSecondSalary, socialSecurityTaxForSecondSalary);

        Tax actualTaxForFirstSalary = taxCalculatorService.getTotalTax(firstSalary);
        Tax actualTaxForSecondSalary = taxCalculatorService.getTotalTax(secondSalary);

        expectedTaxForFirstSalary.generateTotalTax();
        actualTaxForFirstSalary.generateTotalTax();
        expectedTaxForSecondSalary.generateTotalTax();
        actualTaxForSecondSalary.generateTotalTax();

        assertThat(actualTaxForFirstSalary.getIncomeTax()).isEqualTo(expectedTaxForFirstSalary.getIncomeTax());
        assertThat(actualTaxForFirstSalary.getSocialSecurityTax()).isEqualTo(expectedTaxForFirstSalary.getSocialSecurityTax());
        assertThat(actualTaxForFirstSalary.getTotalTax()).isEqualTo(expectedTaxForFirstSalary.getTotalTax());

        assertThat(actualTaxForSecondSalary.getIncomeTax()).isEqualTo(expectedTaxForSecondSalary.getIncomeTax());
        assertThat(actualTaxForSecondSalary.getSocialSecurityTax()).isEqualTo(expectedTaxForSecondSalary.getSocialSecurityTax());
        assertThat(actualTaxForSecondSalary.getTotalTax()).isEqualTo(expectedTaxForSecondSalary.getTotalTax());
    }

}
