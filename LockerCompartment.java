public class LockerCompartment {
    private String lockerId;
    private Size supportedSize;
    private boolean isOccupied;
    private Parcel assignedParcel;
    private String currentOtp;

    public LockerCompartment(String lockerId, Size supportedSize) {
        this.lockerId = lockerId;
        this.supportedSize = supportedSize;
        this.isOccupied = false;
        this.assignedParcel = null;
        this.currentOtp = null;
    }

    public String getLockerId() { return lockerId; }
    public Size getSupportedSize() { return supportedSize; }
    public boolean isOccupied() { return isOccupied; }
    public Parcel getAssignedParcel() { return assignedParcel; }

    public void assignParcel(Parcel parcel, String otp) {
        this.assignedParcel = parcel;
        this.currentOtp = otp;
        this.isOccupied = true;
        parcel.setStatus(Status.ASSIGNED);
    }

    public void freeLocker() {
        this.assignedParcel = null;
        this.currentOtp = null;
        this.isOccupied = false;
    }

    public boolean validateOtp(String otp) {
        return this.currentOtp != null && this.currentOtp.equals(otp);
    }
}
