package com.ezhomesixgod.prize;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author renyang
 * @description
 * @createTime 2018-11-27 10:24
 */
public class TbUserGameYybf {

    private Integer id;

    private Integer userId;

    private Integer yybfId;

    private String orderNo;


    private BigDecimal point;


    private Byte status;

    private Integer count;


    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getYybfId() {
        return yybfId;
    }

    public void setYybfId(Integer yybfId) {
        this.yybfId = yybfId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
