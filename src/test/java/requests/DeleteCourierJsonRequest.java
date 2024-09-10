package requests;

public class DeleteCourierJsonRequest {
    private int id;

    public DeleteCourierJsonRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
