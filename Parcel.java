enum Size { SMALL, MEDIUM, LARGE }
enum Status { RECEIVED, ASSIGNED, COLLECTED, OVERDUE }

public class Parcel {
    private String parcelId;
    private String recipientId;
    private Size size;
    private Status status;

    public Parcel(String parcelId, String recipientId, Size size) {
        this.parcelId = parcelId;
        this.recipientId = recipientId;
        this.size = size;
        this.status = Status.RECEIVED;
    }

    public String getParcelId() { return parcelId; }
    public String getRecipientId() { return recipientId; }
    public Size getSize() { return size; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
