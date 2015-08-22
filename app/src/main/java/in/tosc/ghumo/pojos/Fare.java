package in.tosc.ghumo.pojos;

/**
 * Created by championswimmer on 22/8/15.
 */
public class Fare {
    int waiting_charges;
    String city;
    int min_dist;
    int booking_fee;
    String operator;
    float fare_per_km;
    float min_fare;

    public int getWaiting_charges() {
        return waiting_charges;
    }

    public void setWaiting_charges(int waiting_charges) {
        this.waiting_charges = waiting_charges;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMin_dist() {
        return min_dist;
    }

    public void setMin_dist(int min_dist) {
        this.min_dist = min_dist;
    }

    public int getBooking_fee() {
        return booking_fee;
    }

    public void setBooking_fee(int booking_fee) {
        this.booking_fee = booking_fee;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public float getFare_per_km() {
        return fare_per_km;
    }

    public void setFare_per_km(float fare_per_km) {
        this.fare_per_km = fare_per_km;
    }

    public float getMin_fare() {
        return min_fare;
    }

    public void setMin_fare(float min_fare) {
        this.min_fare = min_fare;
    }

    public Fare(int waiting_charges, String city, int min_dist, int booking_fee, String operator, float fare_per_km, float min_fare) {
        this.waiting_charges = waiting_charges;
        this.city = city;
        this.min_dist = min_dist;
        this.booking_fee = booking_fee;
        this.operator = operator;
        this.fare_per_km = fare_per_km;
        this.min_fare = min_fare;
    }
}
