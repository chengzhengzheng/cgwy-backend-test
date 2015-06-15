package com.mishu.cgwy.order.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;

import com.mishu.cgwy.admin.domain.Agent;
import com.mishu.cgwy.common.domain.Warehouse;
import com.mishu.cgwy.inventory.domain.Vendor;
import com.mishu.cgwy.product.domain.Sku;


/**
 * 每日订单包的财务统计，会冗余sku的名称，进价，售价等数据
 */
@Entity
@Data
public class OrderGroupSkuFinance implements SkuFinance{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_group_finance_id")
    private OrderGroupFinance orderGroupFinance;

    @ManyToOne
    @JoinColumn(name = "sku_id")
    private Sku sku;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    private BigDecimal salePrice = BigDecimal.ZERO;

    private BigDecimal purchasePrice = BigDecimal.ZERO;

    /**
     * 销售数量
     */
    private int sumOfSalesQuantity = 0;

    /**
     * 退货数量
     */
    private int sumOfRefundQuantity = 0;

    /**
     * 扣除退货后的销售数量
     * @return
     */
    @Transient
    public int getSumOfQuantity() {
        return sumOfSalesQuantity - sumOfRefundQuantity;
    }

    /**
     * 扣除退货后的销售总价
     * @return
     */
    @Transient
    // TODO rename
    public BigDecimal getSumOfSales() {
        return salePrice.multiply(BigDecimal.valueOf(getSumOfQuantity()));
    }

    /**
     * 扣除退货后的进货总价
     * @return
     */
    @Transient
    public BigDecimal getSumOfPurchases() {
        return purchasePrice.multiply(BigDecimal.valueOf(getSumOfQuantity()));
    }


}
