package com.tax.calculator.util;

import java.math.BigDecimal;

public enum TaxSlabConstant {
    YEAR_2017(new BigDecimal("53150"),new BigDecimal("24.00"),new BigDecimal("164100"),new BigDecimal("0.93"),new BigDecimal("230950"),new BigDecimal("2.41"),new BigDecimal("580650"),new BigDecimal("11.52"),new BigDecimal("934050"),new BigDecimal("14.52")),
    YEAR_2018(new BigDecimal("54750"),new BigDecimal("23.00"),new BigDecimal("169000"),new BigDecimal("1.40"),new BigDecimal("237900"),new BigDecimal("3.30"),new BigDecimal("598050"),new BigDecimal("12.40"),new BigDecimal("962050"),new BigDecimal("15.40"));

    private final BigDecimal base_slab_amount;
    private final BigDecimal base_slab_rate;
    private final BigDecimal slab1_amount;
    private final BigDecimal slab1_rate;
    private final BigDecimal  slab2_amount;
    private final BigDecimal slab2_rate;
    private final BigDecimal slab3_amount;
    private final BigDecimal slab3_rate;
    private final BigDecimal slab4_amount;
    private final BigDecimal slab4_rate;

    TaxSlabConstant(BigDecimal base_slab_amount, BigDecimal base_slab_rate, BigDecimal slab1_amount, BigDecimal slab1_rate, BigDecimal slab2_amount, BigDecimal slab2_rate, BigDecimal slab3_amount, BigDecimal slab3_rate, BigDecimal slab4_amount, BigDecimal slab4_rate) {
        this.base_slab_amount = base_slab_amount;
        this.base_slab_rate = base_slab_rate;
        this.slab1_amount = slab1_amount;
        this.slab1_rate = slab1_rate;
        this.slab2_amount = slab2_amount;
        this.slab2_rate = slab2_rate;
        this.slab3_amount = slab3_amount;
        this.slab3_rate = slab3_rate;
        this.slab4_amount = slab4_amount;
        this.slab4_rate = slab4_rate;
    }

    public BigDecimal getBase_slab_amount() {
        return base_slab_amount;
    }

    public BigDecimal getBase_slab_rate() {
        return base_slab_rate;
    }

    public BigDecimal getSlab1_amount() {
        return slab1_amount;
    }

    public BigDecimal getSlab1_rate() {
        return slab1_rate;
    }

    public BigDecimal getSlab2_amount() {
        return slab2_amount;
    }

    public BigDecimal getSlab2_rate() {
        return slab2_rate;
    }

    public BigDecimal getSlab3_amount() {
        return slab3_amount;
    }

    public BigDecimal getSlab3_rate() {
        return slab3_rate;
    }

    public BigDecimal getSlab4_amount() {
        return slab4_amount;
    }

    public BigDecimal getSlab4_rate() {
        return slab4_rate;
    }
}
