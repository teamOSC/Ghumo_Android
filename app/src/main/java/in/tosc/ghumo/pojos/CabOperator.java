package in.tosc.ghumo.pojos;

import java.util.ArrayList;

/**
 * Created by championswimmer on 22/8/15.
 */
public class CabOperator {
    float rating;
    String operatorName;
    ArrayList<CabOptions> options;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public ArrayList<CabOptions> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<CabOptions> options) {
        this.options = options;
    }

    public class CabOptions {
        String category;
        float totalCost;
        float perKmRate;
        String imageURL;
        float baseFare;
        float minDistance;
        float baseDistance;
        CabNearest nearestCab;

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

        public CabNearest getNearestCab() {
            return nearestCab;
        }

        public void setNearestCab(CabNearest nearestCab) {
            this.nearestCab = nearestCab;
        }

        public class CabNearest {
            float timeLimit;
            float lat;
            float lng;

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
    }
}
