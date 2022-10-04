package com.bjpowernode.pojo.vo;

public class ProductInfoVo {
    // 这个类用来封装查询条件
    private String pName;

    private Integer typeId;

    private Integer lowPrice;

    private Integer highPrice;

    private Integer pageNo = 1;

    public ProductInfoVo() {
    }

    @Override
    public String toString() {
        return "ProductInfoVo{" +
                "pName='" + pName + '\'' +
                ", typeId=" + typeId +
                ", lowPrice=" + lowPrice +
                ", highPrice=" + highPrice +
                ", pageNo=" + pageNo +
                '}';
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Integer highPrice) {
        this.highPrice = highPrice;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public ProductInfoVo(String pName, Integer typeId, Integer lowPrice, Integer highPrice, Integer pageNo) {
        this.pName = pName;
        this.typeId = typeId;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.pageNo = pageNo;
    }
}
