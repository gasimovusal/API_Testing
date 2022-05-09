package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDates_Pojo {

    // 1. create private variables for every key
    private String checkin;
    private String checkout;

    // 2) create 2 constructor:  with all parameters and without any parameter


    public BookingDates_Pojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDates_Pojo() {
    }

    // 3) create getters and setters

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // 4) create toString method
    @Override
    public String toString() {
        return "BookingDates_Pojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
