package com.tax.calculator.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Tax {

    public BigDecimal incomeTax;
    public BigDecimal socialSecurityTax;
    public BigDecimal totalTax;

    public Tax() {}

    public Tax(BigDecimal incomeTax, BigDecimal socialSecurityTax) {
        this.incomeTax = incomeTax;
        this.socialSecurityTax = socialSecurityTax;
    }

    public BigDecimal getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    public BigDecimal getSocialSecurityTax() {
        return socialSecurityTax;
    }

    public void setSocialSecurityTax(BigDecimal socialSecurityTax) {
        this.socialSecurityTax = socialSecurityTax;
    }

   public void generateTotalTax() {
        totalTax = incomeTax.add(socialSecurityTax);
   }

    public BigDecimal getTotalTax() {
        return totalTax;
    }
}
