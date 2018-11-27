package com.ezhomesixgod;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author renyang
 * @description
 * @createTime 2018-11-27 10:28
 */
public class TbGameYybf {

    private Integer id;

    private BigDecimal point;


    private BigDecimal pointGet;


    private Byte status;


    private String result;


    private Byte type;


    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public BigDecimal getPointGet() {
        return pointGet;
    }

    public void setPointGet(BigDecimal pointGet) {
        this.pointGet = pointGet;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
