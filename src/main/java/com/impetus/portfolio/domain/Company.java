package com.impetus.portfolio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.NGramFilterFactory;
import org.apache.solr.analysis.StandardFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.apache.solr.analysis.StopFilterFactory;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 * Company class contains the basic details related to a company.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */

@XmlRootElement(name = "Company")
@XmlAccessorType(XmlAccessType.FIELD)
@AnalyzerDef(name = "ngram",
tokenizer = @org.hibernate.search.annotations.TokenizerDef(factory = StandardTokenizerFactory.class ),
filters = {
  @org.hibernate.search.annotations.TokenFilterDef(factory = StandardFilterFactory.class),
  @org.hibernate.search.annotations.TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @org.hibernate.search.annotations.TokenFilterDef(factory = StopFilterFactory.class),
  @org.hibernate.search.annotations.TokenFilterDef(factory = NGramFilterFactory.class,
    params = { 
      @org.hibernate.search.annotations.Parameter(name = "minGramSize", value = "3"),
      @org.hibernate.search.annotations.Parameter(name = "maxGramSize", value = "3") } )
}
)
@Entity
@Table(name = "company")
@Indexed
public class Company {
    public static final int FORTY=40;
    public static final int SEVEN=7;
    /**
     * Method getTickerSymbol.
     * 
    
     * @return String */
    @Field
    @Column(name = "ticker_symbol", length= SEVEN,nullable=false,unique=true)
    public String getTickerSymbol() {
        return tickerSymbol;
    }

    /**
     * Method setTickerSymbol.
     * 
     * @param tickerSymbol
     *            String
     */
    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    /**
     * Method getCompanyName.
     * 
    
     * @return String */
    @Field(store = Store.NO,analyzer=@org.hibernate.search.annotations.Analyzer(definition="ngram"))
    @Column(name = "company_name", length=FORTY,nullable=false,unique=true)
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Method setCompanyName.
     * 
     * @param companyName
     *            String
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Method getStockPrice.
     * 
    
     * @return Double */
    @Column(name = "stock_price")
    public Double getStockPrice() {
        return stockPrice;
    }

    /**
     * Method setStockPrice.
     * 
     * @param stockPrice
     *            Double
     */
    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
    }

    /**
     * Method getId.
     * 
    
     * @return int */
    @Id
    @GeneratedValue
    @Column(name = "id")
    @DocumentId
    public int getId() {
        return id;
    }

    /**
     * Method setId.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    private String tickerSymbol;

    @XmlElement
    private String companyName;

    @XmlElement
    private Double stockPrice;

    private int id;

}
