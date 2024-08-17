package requests;

import model.MetroStation;
import model.Order;
import model.PageInfo;

import java.util.List;

public class GetOrdersJsonResponse {
    private List<Order> orders;
    private PageInfo pageInfo;
    private List<MetroStation> availableStations;

    public GetOrdersJsonResponse(List<Order> orders, PageInfo pageInfo, List<MetroStation> availableStations) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.availableStations = availableStations;
    }

    public GetOrdersJsonResponse() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<MetroStation> getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(List<MetroStation> availableStations) {
        this.availableStations = availableStations;
    }
}
