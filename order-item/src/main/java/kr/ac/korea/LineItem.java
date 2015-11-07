package kr.ac.korea;

/**
 * Created by ideapad on 2015-11-05.
 */
public class LineItem {
    private long orderKey;
    private double extendedPrice;
    private double discount;

    public LineItem(long orderKey, double extendedPrice, double discount) {
        this.orderKey = orderKey;
        this.extendedPrice = extendedPrice;
        this.discount = discount;
    }

    public long getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(long orderKey) {
        this.orderKey = orderKey;
    }

    public double getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
