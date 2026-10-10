import java.util.List;
import java.util.Optional;

public class LockerAllocationService {
    public void allocateLocker(Parcel parcel, List<LockerCompartment> lockers) throws NoLockerAvailableException {
        Optional<LockerCompartment> exactMatch = lockers.stream()
                .filter(l -> !l.isOccupied() && l.getSupportedSize() == parcel.getSize())
                .findFirst();

        LockerCompartment selectedLocker = exactMatch.orElseGet(() -> lockers.stream()
                .filter(l -> !l.isOccupied() && isLargerSize(l.getSupportedSize(), parcel.getSize()))
                .findFirst()
                .orElse(null));

        if (selectedLocker == null) {
            throw new NoLockerAvailableException("NoLockerAvailableException: No compartment available for parcel " + parcel.getParcelId());
        }

        String otp = "654321";
        selectedLocker.assignParcel(parcel, otp);
        System.out.println("[LOG] Parcel " + parcel.getParcelId() + " allocated to Locker " + selectedLocker.getLockerId());
    }

    public String pickupParcel(String lockerId, String otp, List<LockerCompartment> lockers) throws Exception {
        LockerCompartment locker = lockers.stream()
                .filter(l -> l.getLockerId().equals(lockerId))
                .findFirst()
                .orElseThrow(() -> new Exception("Invalid Locker ID"));

        if (!locker.isOccupied() || !locker.validateOtp(otp)) {
            throw new Exception("Pickup Authentication Failed: Invalid OTP or empty locker.");
        }

        Parcel parcel = locker.getAssignedParcel();
        parcel.setStatus(Status.COLLECTED);
        locker.freeLocker();

        return "RECEIPT: Parcel " + parcel.getParcelId() + " collected successfully from " + lockerId;
    }

    private boolean isLargerSize(Size lockerSize, Size parcelSize) {
        return (lockerSize == Size.LARGE && (parcelSize == Size.MEDIUM || parcelSize == Size.SMALL)) ||
               (lockerSize == Size.MEDIUM && parcelSize == Size.SMALL);
    }
}
