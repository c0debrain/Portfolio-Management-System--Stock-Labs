package com.impetus.portfolio.domain;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * History class contains all the data which are xml feeds and used for graph
 * generation.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Component
@Entity
@Table(name = "history")
public class History {
    public static final int SEVEN=7;
    private String tickersymbol;

    private String time;

    /**
     * 
     * 
    
     * @return time */
    public String getTime() {
        return time;
    }

    /**
     * 
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    private int id;

    /**
     * Method getId.
     * 
    
     * @return int */
    @Id
    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method getTickersymbol.
     * 
    
     * @return String */
    @Column(name = "ticker_symbol",length=SEVEN)
    /**
     *
     * @return tickersymbol
     */
    public String getTickersymbol() {
        return tickersymbol;
    }

    /**
     * 
     * @param tickersymbol
     */
    public void setTickersymbol(String tickersymbol) {
        this.tickersymbol = tickersymbol;
    }

    /**
     * Method getPrice.
     * 
    
     * @return Double */
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 
     * 
    
     * @return date */
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

    private Double price;

    private Date date;

}
