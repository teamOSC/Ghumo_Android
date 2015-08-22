package in.tosc.ghumo.pojos;

/**
 * Created by championswimmer on 22/8/15.
 */
public class Cab {
    String operatorName;
    String category;
    float totalCost;
    float perKmRate;
    String imageURL;
    float baseFare;
    float minDistance;
    float baseDistance;
    float timeLimit;
    float lat;
    float lng;

    public Cab(String operatorName, String category, float totalCost, float perKmRate, String imageURL, float baseFare, float minDistance, float baseDistance, float timeLimit, float lat, float lng) {
        this.operatorName = operatorName;
        this.category = category;
        this.totalCost = totalCost;
        this.perKmRate = perKmRate;
        this.imageURL = imageURL;
        this.baseFare = baseFare;
        this.minDistance = minDistance;
        this.baseDistance = baseDistance;
        this.timeLimit = timeLimit;
        this.lat = lat;
        this.lng = lng;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(float perKmRate) {
        this.perKmRate = perKmRate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public float getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(float baseFare) {
        this.baseFare = baseFare;
    }

    public float getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(float minDistance) {
        this.minDistance = minDistance;
    }

    public float getBaseDistance() {
        return baseDistance;
    }

    public void setBaseDistance(float baseDistance) {
        this.baseDistance = baseDistance;
    }

    public float getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(float timeLimit) {
        this.timeLimit = timeLimit;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
