package requests;

public class OrderCreateJsonResponse {
    private int track;

    public OrderCreateJsonResponse(int track) {
        this.track = track;
    }

    public OrderCreateJsonResponse() {
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }
}
