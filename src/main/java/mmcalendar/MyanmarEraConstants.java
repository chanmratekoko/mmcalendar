package mmcalendar;

/**
 * Typed value class for Myanmar calendar era constants.
 * Replaces the stringly-typed Map&lt;String, Double&gt; return from MyanmarYearConstants.
 *
 * @author Chan Mrate Ko Ko
 * @version 1.1.2
 */
public final class MyanmarEraConstants {

    private final double eraId;
    private final double watatOffset;
    private final double numberOfMonths;
    private final double exceptionInWatatYear;

    MyanmarEraConstants(double eraId, double watatOffset, double numberOfMonths, double exceptionInWatatYear) {
        this.eraId = eraId;
        this.watatOffset = watatOffset;
        this.numberOfMonths = numberOfMonths;
        this.exceptionInWatatYear = exceptionInWatatYear;
    }

    /**
     * @return Myanmar calendar era id [1.1, 1.2, 1.3, 2, 3]
     */
    public double getEraId() {
        return eraId;
    }

    /**
     * @return watat offset to compensate
     */
    public double getWatatOffset() {
        return watatOffset;
    }

    /**
     * @return number of months to find excess days
     */
    public double getNumberOfMonths() {
        return numberOfMonths;
    }

    /**
     * @return exception in watat year [0=normal, 1=exception]
     */
    public double getExceptionInWatatYear() {
        return exceptionInWatatYear;
    }

    @Override
    public String toString() {
        return "MyanmarEraConstants{" +
                "eraId=" + eraId +
                ", watatOffset=" + watatOffset +
                ", numberOfMonths=" + numberOfMonths +
                ", exceptionInWatatYear=" + exceptionInWatatYear +
                '}';
    }
}
