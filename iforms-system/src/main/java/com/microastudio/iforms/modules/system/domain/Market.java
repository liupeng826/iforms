package com.microastudio.iforms.modules.system.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author peng
 */
@Entity
@Table(name = "market")
public class Market implements Serializable {

    private static final long serialVersionUID = -3511754758539154938L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marketId;
    private String description;
    private byte isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }
}
