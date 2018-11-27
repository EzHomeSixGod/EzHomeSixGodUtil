package com.ezhomesixgod;

import java.math.BigDecimal;

/**
 * @author renyang
 * @description
 * @createTime 2018-11-27 10:27
 */
public class RewardVo {

    private Integer id;

    private String name;

    private String head;

    private BigDecimal point;

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
