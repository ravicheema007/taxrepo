package com.tax.calculator.service;

import com.tax.calculator.domain.Salary;
import com.tax.calculator.domain.Tax;
import com.tax.calculator.util.SocialSecurityConstant;
import com.tax.calculator.util.TaxSlabConstant;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxCalculatorService {

    public BigDecimal calculateTaxComponent(TaxSlabConstant taxSlab, BigDecimal salary) {
        BigDecimal incomeTax = new BigDecimal("0.00");
        if (salary.compareTo(taxSlab.getBase_slab_amount()) <= 0) {
            return incomeTax;
        }
        if (salary.compareTo(taxSlab.getBase_slab_amount()) > 0 && salary.compareTo(taxSlab.getSlab1_amount()) <= 0) {
            incomeTax = incomeTax.add(getPercentageValue(taxSlab.getBase_slab_rate(), salary.min(taxSlab.getBase_slab_amount())));
        }
        if (salary.compareTo(taxSlab.getSlab1_amount()) > 0 && salary.compareTo(taxSlab.getSlab2_amount()) <= 0) {
            incomeTax = incomeTax.add(getPercentageValue(taxSlab.getSlab1_rate(), salary.min(taxSlab.getSlab1_amount())));
        }
        if (salary.compareTo(taxSlab.getSlab2_amount()) > 0 && salary.compareTo(taxSlab.getSlab3_amount()) <= 0) {
            incomeTax = incomeTax.add(getPercentageValue(taxSlab.getSlab2_rate(), salary.min(taxSlab.getSlab2_amount())));
        }
        if (salary.compareTo(taxSlab.getSlab3_amount()) > 0 && salary.compareTo(taxSlab.getSlab4_amount()) <= 0) {
            incomeTax = incomeTax.add(getPercentageValue(taxSlab.getSlab3_rate(), salary.min(taxSlab.getSlab3_amount())));
        }
        if (salary.compareTo(taxSlab.getSlab4_amount()) > 0) {
            incomeTax = incomeTax.add(getPercentageValue(taxSlab.getSlab4_rate(), salary.min(taxSlab.getSlab4_amount())));
        }
        return incomeTax;

    }


    public BigDecimal getSocialSecurityComponent(SocialSecurityConstant socialSecuritySlab, BigDecimal salary) {

        BigDecimal socialSecurityTaxgetLønnsinntekt = new BigDecimal("0.00");
        BigDecimal socialSecurityTaxUpperLimit= new BigDecimal("0.00");
        if(salary.compareTo(socialSecuritySlab.getLowerlimit()) >0){
            socialSecurityTaxUpperLimit = getPercentageValue(socialSecuritySlab.getLevelingRate(),salary.min(socialSecuritySlab.getLowerlimit()));
            socialSecurityTaxgetLønnsinntekt = getPercentageValue(socialSecuritySlab.getLønnsinntekt(),salary);
            if(socialSecurityTaxUpperLimit.compareTo(socialSecurityTaxgetLønnsinntekt)<0){
                return socialSecurityTaxUpperLimit;
            }
        }
        return socialSecurityTaxgetLønnsinntekt;
    }

    public Tax getTotalTax(Salary salary) {
        Tax tax = new Tax();
        TaxSlabConstant taxSlab = getTaxSlabFromYear(salary.getYear());
        SocialSecurityConstant socialSecurityConstant = getSocialSecurityFromYear(salary.getYear());
        tax.setIncomeTax(calculateTaxComponent(taxSlab, new BigDecimal(salary.getSalary())));
        tax.setSocialSecurityTax(getSocialSecurityComponent(socialSecurityConstant,new BigDecimal(salary.getSalary())));
        tax.generateTotalTax();
        return tax;
    }

    public TaxSlabConstant getTaxSlabFromYear(String year) {
        switch (year) {
            case "2017":
                return TaxSlabConstant.YEAR_2017;
            case "2018":
                return TaxSlabConstant.YEAR_2018;
            default:
                return TaxSlabConstant.YEAR_2018;
        }
    }

    public BigDecimal getPercentageValue(BigDecimal percent_rate, BigDecimal salary) {
        return salary.multiply(percent_rate).divide(new BigDecimal(100.00));
    }

    public SocialSecurityConstant getSocialSecurityFromYear(String year) {
        switch (year) {
            case "2017":
                return SocialSecurityConstant.YEAR_2017;
            case "2018":
                return SocialSecurityConstant.YEAR_2018;
            default:
                return SocialSecurityConstant.YEAR_2018;
        }
    }
}
