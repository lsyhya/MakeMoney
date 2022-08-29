package com.example.makemoney;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

@Entity
public class Transactions {

    @Id(autoincrement = true)
    private Long id;
    //订单开始位置
    private double startPosition;
    //订单止损位置
    private double stopLoss;
    //订单盈利位置
    private double stopProfit;
    //止损大小/盈利大小 因为测试1:1盈亏比 所以止盈止损大小一样
    private double dVaule;
    //选择的交易周期
    private String cycle; //1min 5min 15min 30min 1h 4h 1d
    //选择的交易策略
    private String tradingStrategies; //todo ：
    //订单日期
    private Date date;
    //订单状态
    private int state; //0:交易或者交易中,1：失效,2:盈利,3:亏损
    //盈利金额or亏损金额 仅当state=2,3有效
    private double profitOrLossM;
    //交易品种
    private String varieties;
    //最终金额
    private double totalAmount;
    //交易类型
    private String sellOrBuy;

    public String getSellOrBuy() {
        return sellOrBuy;
    }

    public void setSellOrBuy(String sellOrBuy) {
        this.sellOrBuy = sellOrBuy;
    }

    @Generated(hash = 175924170)
    public Transactions(Long id, double startPosition, double stopLoss,
            double stopProfit, double dVaule, String cycle,
            String tradingStrategies, Date date, int state, double profitOrLossM,
            String varieties, double totalAmount, String sellOrBuy) {
        this.id = id;
        this.startPosition = startPosition;
        this.stopLoss = stopLoss;
        this.stopProfit = stopProfit;
        this.dVaule = dVaule;
        this.cycle = cycle;
        this.tradingStrategies = tradingStrategies;
        this.date = date;
        this.state = state;
        this.profitOrLossM = profitOrLossM;
        this.varieties = varieties;
        this.totalAmount = totalAmount;
        this.sellOrBuy = sellOrBuy;
    }

    @Generated(hash = 338950345)
    public Transactions() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(double startPosition) {
        this.startPosition = startPosition;
    }

    public double getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public double getStopProfit() {
        return stopProfit;
    }

    public void setStopProfit(double stopProfit) {
        this.stopProfit = stopProfit;
    }

    public double getdVaule() {
        return dVaule;
    }

    public void setdVaule(double dVaule) {
        this.dVaule = dVaule;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getTradingStrategies() {
        return tradingStrategies;
    }

    public void setTradingStrategies(String tradingStrategies) {
        this.tradingStrategies = tradingStrategies;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getProfitOrLossM() {
        return profitOrLossM;
    }

    public void setProfitOrLossM(double profitOrLossM) {
        this.profitOrLossM = profitOrLossM;
    }

    public String getVarieties() {
        return varieties;
    }

    public void setVarieties(String varieties) {
        this.varieties = varieties;
    }

    public double getDVaule() {
        return this.dVaule;
    }

    public void setDVaule(double dVaule) {
        this.dVaule = dVaule;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
