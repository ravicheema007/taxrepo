package com.tax.calculator.util;

import java.math.BigDecimal;

public enum SocialSecurityConstant {

    YEAR_2017(new BigDecimal("54650"),new BigDecimal("25.00"),new BigDecimal("8.20")),
    YEAR_2018(new BigDecimal("54650"),new BigDecimal("25.00"),new BigDecimal("8.20"));

    private final BigDecimal lowerlimit;
    private final BigDecimal levelingRate;
    private final BigDecimal Lønnsinntekt;

    SocialSecurityConstant(BigDecimal lowerlimit, BigDecimal levelingRate, BigDecimal lønnsinntekt) {
        this.lowerlimit = lowerlimit;
        this.levelingRate = levelingRate;
        Lønnsinntekt = lønnsinntekt;
    }

    public BigDecimal getLowerlimit() {
        return lowerlimit;
    }

    public BigDecimal getLevelingRate() {
        return levelingRate;
    }

    public BigDecimal getLønnsinntekt() {
        return Lønnsinntekt;
    }
}
