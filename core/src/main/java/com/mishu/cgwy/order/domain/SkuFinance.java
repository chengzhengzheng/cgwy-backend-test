package com.mishu.cgwy.order.domain;

import java.math.BigDecimal;

import lombok.core.Agent;

import com.mishu.cgwy.common.domain.Warehouse;
import com.mishu.cgwy.inventory.domain.Vendor;


public interface SkuFinance {

    public Vendor getVendor();

    public void setVendor(Vendor vendor);

    public Agent getAgent();

    public Warehouse getWarehouse();

    public BigDecimal getSalePrice();

    public BigDecimal getPurchasePrice();

    public void setPurchasePrice(BigDecimal purchasePrice);

    public int getSumOfSalesQuantity();

    public int getSumOfRefundQuantity();

    public int getSumOfQuantity();

    public BigDecimal getSumOfSales();

    public BigDecimal getSumOfPurchases();
}
