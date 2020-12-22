package stockMarket;

import collections.MinPriorityQueue;

import java.time.LocalDateTime;

public class OrderBook {

    public enum OrderType {
        LIMIT,
        MARKET
    }

    public enum ActionType {
        BID,
        ASK
    }

    private static final float ticksPerUnit = 100.0f;
    private static long orderIDCounter = 0;

    private int lastBid;
    private int lastAsk;
    private int lastPrice;
    private int minPrice;
    private int maxPrice;
    private int previousDayPrice;
    private int variation;
    private int processedOrders;
    private int unprocessedOrders;

    private int earnings;
    private int volume;
    private String stockTitle;

    private MinPriorityQueue<Order> asks;
    private MinPriorityQueue<Order> bids;


    public OrderBook(String stockTitle, int lastBid, int lastAsk)
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public String getStockTitle()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int getLastBid()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int getLastAsk()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int getLastPrice()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int getMinPrice()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int getMaxPrice()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int getNextBestBid()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int getNextBestAsk()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int getProcessedOrders()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int getUnprocessedOrders()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int getVariation()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int getVolume()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int getBrokerEarnings()
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public void placeMarketOrder(ActionType action, int quantity)
    {
        Order order = new Order(this.stockTitle,++orderIDCounter,action, OrderType.MARKET, quantity, 0);
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public void placeLimitOrder(ActionType action, int quantity, int price)
    {
        Order order = new Order(this.stockTitle,++orderIDCounter,action, OrderType.LIMIT, quantity, price);
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public int processNextOrder(boolean verbose)
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public boolean processNextOrders(int n, boolean verbose)
    {
        //TODO: implement
		throw new UnsupportedOperationException();
    }

    public void printSummary()
    {
        System.out.println("Daily transaction summary for stock: " + this.getStockTitle());
        System.out.println("Orders processed: " + getProcessedOrders());
        System.out.println("Transaction volume: " + getVolume());
        System.out.println("Last Price: " + getLastBid()/ticksPerUnit + (getVariation() < 0 ? " " : " +") + getVariation()/ticksPerUnit + "%");
        System.out.println("Maximum Price: " + this.maxPrice/ticksPerUnit);
        System.out.println("Minimum Price: " + this.minPrice/ticksPerUnit);
        System.out.println("Orders not processed: " + getUnprocessedOrders());
        System.out.println("Best remaining ask: " + getNextBestAsk()/ticksPerUnit);
        System.out.println("Best remaining bid: " + getNextBestBid()/ticksPerUnit);
        System.out.println("Daily earnings: " + getBrokerEarnings()/ticksPerUnit);
    }
}

class Order implements Comparable<Order> {


    private static final float ticksPerUnit = 100.0f;

    @Override
    public int compareTo(Order o) {

        //TODO: implement
		throw new UnsupportedOperationException();
    }

    final LocalDateTime time;
    final long orderID;
    int quantity;
    final OrderBook.OrderType orderType;
    final int price;
    final OrderBook.ActionType action;
    final String stockTitle;

    public Order(String stockTitle, long orderID, OrderBook.ActionType action, OrderBook.OrderType order, int quantity, int price)
    {
        this.stockTitle = stockTitle;
        this.orderID = orderID;
        this.action = action;
        this.orderType = order;
        this.time = LocalDateTime.now();
        this.quantity = quantity;
        this.price = price;
    }

    public String toString()
    {
        String result = this.action.toString() + " ID: " + this.orderID  + " type: " + this.orderType.toString() + " quantity: " + this.quantity;
        if(this.orderType == OrderBook.OrderType.LIMIT) result += " price: " + this.price/ticksPerUnit;

        return result;
    }

}
