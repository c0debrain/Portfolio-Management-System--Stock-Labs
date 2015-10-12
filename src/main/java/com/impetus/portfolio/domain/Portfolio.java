package com.impetus.portfolio.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

/**
 * Portfolio class contains the details related to user stocks.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Component
@Entity
@Table(name = "userportfolio")
public class Portfolio {
    public static final int FORTY=40;
    public static final int TWENTY=20;
    public static final int ELEVEN=11;
    private int pkid;

    /**
     * 
    
     * @return pkid */
    @Id
    @Column(name = "id",length=ELEVEN)
    public int getPkid() {
        return pkid;
    }

    /**
     * Method setPkid.
     * 
     * @param pkid
     *            int
     */
    public void setPkid(int pkid) {
        this.pkid = pkid;
    }

    private int userid;

    /**
     * 
     * 
    
     * @return userid */
    @Column(name = "userid",length=ELEVEN,nullable=false)
    public int getUserid() {
        return userid;
    }

    /**
     * 
     * @param userid
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * 
     * 
    
     * @return stockPrice */
    @Column(name = "stockprice",nullable=false)
    public Double getStockPrice() {
        return stockPrice;
    }

    /**
     * 
     * @param stockPrice
     */
    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
    }

    /**
     * 
     * 
    
     * @return tickerSymbol */
    @Column(name = "ticker_symbol",nullable=false)
    public String getTickerSymbol() {
        return tickerSymbol;
    }

    /**
     * 
     * @param tickerSymbol
     */
    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    /**
     * 
     * 
    
     * @return variance */
    @Column(name = "variance",nullable=false)
    public Double getVariance() {
        return variance;
    }

    /**
     * 
     * @param variance
     */
    public void setVariance(Double variance) {
        this.variance = variance;
    }

    private String companyName;

    /**
     * 
     * 
    
     * @return companyName */
    @Column(name = "companyname",length=FORTY,nullable=false)
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private Double stockPrice;

    private String tickerSymbol;

    private Double variance;

    private Date date;

    /**
     * 
     * 
    
     * @return date */
    @Column(name = "date",nullable=false)
    public Date getDate() {
        return date;
    }

    /**
     * 
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    private String portfolioName;

    /**
     * 
     * 
    
     * @return portfolioname */
    @Column(name = "portfolioname",length=TWENTY,nullable=false)
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * Method setPortfolioName.
     * 
     * @param portfolioName
     *            String
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
}
