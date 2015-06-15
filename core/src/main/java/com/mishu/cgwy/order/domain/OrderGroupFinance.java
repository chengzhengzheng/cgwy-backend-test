package com.mishu.cgwy.order.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.core.Agent;

import com.mishu.cgwy.admin.domain.AdminUser;
import com.mishu.cgwy.common.domain.Warehouse;

@Entity
@Table(name = "cgwy_order_group_finance")
@Data
public class OrderGroupFinance {
    @Id
    private Long id;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "order_group_finance_id")
    private List<OrderGroupSkuFinance> skuFinances = new ArrayList<>();

    @JoinColumn(name = "id")
    @OneToOne
    @MapsId
    private OrderGroup orderGroup;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "tracker_id")
    private AdminUser tracker;

    @Temporal(TemporalType.DATE)
    private Date expectedArrivedDate;

    // 订单总金额
    private BigDecimal sumOfTotal = BigDecimal.ZERO;

    // 补贴总金额
    private BigDecimal totalAllowance = BigDecimal.ZERO;
    private BigDecimal totalSalesOfPrepaidSku = BigDecimal.ZERO;
    private BigDecimal totalSalesOfPostpaidSku = BigDecimal.ZERO;
    private BigDecimal totalDiscountOfPromotion = BigDecimal.ZERO;
    
    @Column(columnDefinition = "text")
    private String excelName;


    public void reset() {
        skuFinances.clear();
        sumOfTotal = BigDecimal.ZERO;
        totalAllowance = BigDecimal.ZERO;
        totalSalesOfPrepaidSku = BigDecimal.ZERO;
        totalSalesOfPostpaidSku = BigDecimal.ZERO;
        totalDiscountOfPromotion = BigDecimal.ZERO;
    }


    private void append(OrderItem orderItem, Warehouse warehouse) {
        OrderGroupSkuFinance match = null;
        for (OrderGroupSkuFinance skuSummary : skuFinances) {

            if (skuSummary.getSku().getId().equals(orderItem.getSku().getId())
                    && skuSummary.getSalePrice().equals(orderItem.getPrice())) {
                match = skuSummary;
                break;
            }
        }

        if (match == null) {
            match = new OrderGroupSkuFinance();
            match.setSku(orderItem.getSku());
            match.setSalePrice(orderItem.getPrice());
            match.setWarehouse(warehouse);
            match.setAgent(agent);

            skuFinances.add(match);
        }

        match.setSumOfSalesQuantity(match.getSumOfSalesQuantity() + orderItem.getQuantity());
    }

    private void append(Refund refund, Warehouse warehouse) {
        OrderGroupSkuFinance match = null;
        for (OrderGroupSkuFinance skuSummary : skuFinances) {
            if (skuSummary.getSku().getId().equals(refund.getSku().getId())
                    && skuSummary.getSalePrice().equals(refund.getPrice())) {
                match = skuSummary;
                break;
            }
        }

        if (match == null) {
            match = new OrderGroupSkuFinance();
            match.setSku(refund.getSku());
            match.setSalePrice(refund.getPrice());
            match.setWarehouse(warehouse);
            match.setAgent(agent);

            skuFinances.add(match);
        }

        match.setSumOfRefundQuantity(match.getSumOfRefundQuantity() + refund.getQuantity());
    }

    private void append(Order order, Warehouse warehouse) {
        for (OrderItem orderItem : order.getOrderItems()) {
            append(orderItem, warehouse);
        }

        for (Refund refund : order.getRefunds()) {
            append(refund, warehouse);
        }

        for (Promotion promotion : order.getPromotions()) {
            if (promotion.getPromotableItems() != null && promotion.getPromotableItems().getSku() != null) {
                OrderItem promotionOrderItem = new OrderItem();
                promotionOrderItem.setSku(promotion.getPromotableItems().getSku());
                promotionOrderItem.setQuantity(promotion.getPromotableItems().getQuantity());
                promotionOrderItem.setOrder(order);
                promotionOrderItem.setPrice(BigDecimal.ZERO);
                promotionOrderItem.setTotalPrice(BigDecimal.ZERO);

                append(promotionOrderItem, warehouse);
            }
        }

    }

    /**
     * 初始sku数量
     */
    public void init(Warehouse warehouse) {
        reset();
        for (Order order : orderGroup.getMembers()) {
            append(order, warehouse);
        }

        for (Order order : orderGroup.getMembers()) {
            sumOfTotal = sumOfTotal.add(order.getTotal());
            for (Refund refund : order.getRefunds()) {
                sumOfTotal = sumOfTotal.subtract(refund.getTotalPrice());
            }

            for (Promotion promotion : order.getPromotions()) {
                totalDiscountOfPromotion.add(promotion.getDiscount());
            }
        }


    }

    /**
     * 在更新sku的进价后，计算补贴
     */
    public void updatePrice() {
        totalAllowance = BigDecimal.ZERO;

        for (OrderGroupSkuFinance skuFinance : skuFinances) {
            BigDecimal allowance = BigDecimal.ZERO;

            boolean prepaid = false;
            if (skuFinance.getVendor() != null && skuFinance.getVendor().isPrepaid()) {
                prepaid = true;
            }

            // use compareTo instead of equals to get rid of scale problem
            if (!prepaid && skuFinance.getPurchasePrice().compareTo(BigDecimal.ZERO) > 0) {
                allowance = skuFinance.getPurchasePrice().subtract(skuFinance.getSalePrice()).multiply(BigDecimal.valueOf
                        (skuFinance.getSumOfQuantity()));
            }

            totalAllowance = totalAllowance.add(allowance);
        }

        for (OrderGroupSkuFinance skuFinance : skuFinances) {
            boolean prepaid = false;
            if (skuFinance.getVendor() != null && skuFinance.getVendor().isPrepaid()) {
                prepaid = true;
            }

            if (prepaid) {
                totalSalesOfPrepaidSku = totalSalesOfPrepaidSku.add(skuFinance.getSalePrice().multiply(BigDecimal
                        .valueOf(skuFinance.getSumOfQuantity())));
            } else {
                totalSalesOfPostpaidSku = totalSalesOfPostpaidSku.add(skuFinance.getSalePrice().multiply(BigDecimal
                        .valueOf(skuFinance.getSumOfQuantity())));
            }

        }
    }

    @Override
    public String toString() {
        return "OrderGroupFinance{" +
                "id=" + id +
                ", sumOfTotal=" + sumOfTotal +
                ", totalAllowance=" + totalAllowance +
                ", expectedArrivedDate=" + expectedArrivedDate +
                ", totalSalesOfPrepaidSku=" + totalSalesOfPrepaidSku +
                ", totalSalesOfPostpaidSku=" + totalSalesOfPostpaidSku +
                ", totalDiscountOfPromotion=" + totalDiscountOfPromotion +
                ", agent=" + agent +
                ", tracker=" + tracker +
                '}';
    }

}
