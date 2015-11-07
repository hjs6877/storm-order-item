package kr.ac.korea;

/**
 * Created by ideapad on 2015-11-05.
 */
public class Order {
    private long orderKey;
//    private long custKey;
//    private String orderStatus;
//    private double totalPrice;
    private String orderDate;
    private String orderPriority;
//    private String clerk;
//    private int shipPriority;
//    private String comment;

    public Order(long orderKey, String orderDate, String orderPriority) {
        this.orderKey = orderKey;
//        this.custKey = custKey;
//        this.orderStatus = orderStatus;
//        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderPriority = orderPriority;
//        this.clerk = clerk;
//        this.shipPriority = shipPriority;
//        this.comment = comment;
    }

    public long getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(long orderKey) {
        this.orderKey = orderKey;
    }


    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(String orderPriority) {
        this.orderPriority = orderPriority;
    }

}
