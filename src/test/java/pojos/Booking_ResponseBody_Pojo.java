package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Booking_ResponseBody_Pojo {

    private Integer bookingid;
    private Booking_Pojo booking;

    public Booking_ResponseBody_Pojo(Integer bookingid, Booking_Pojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }
    public Booking_ResponseBody_Pojo() {
    }

    public Integer getBookingid() {
        return bookingid;
    }
    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }
    public Booking_Pojo getBooking() {
        return booking;
    }
    public void setBooking(Booking_Pojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponseBodyPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}