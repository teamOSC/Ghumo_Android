package in.tosc.ghumo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import in.tosc.ghumo.fetchdata.FareOps;
import in.tosc.ghumo.pojos.Fare;

public class MeterService extends Service {
    public LocationManager locationManager;
    public Location lastLocation = null;
    public Float distance = (float) 0.0;
    public Criteria criteria;
    LocationListener locLis;

    public MeterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        distance = (float) 0.0;
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final Fare autoFare = ((GhumoApp) getApplication()).getFare();

        locLis = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location", location.getLatitude() + "  " + location.getLongitude());
                if (lastLocation == null) {
                    lastLocation = location;
                }
                distance += lastLocation.distanceTo(location) / 1000;
                lastLocation = location;
                float totalFare = FareOps.calcFare(distance, autoFare);

                Intent fareIntent = new Intent("fare_update");
                fareIntent.putExtra("fare", totalFare);
                fareIntent.putExtra("distance", distance);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(fareIntent);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                }

                locationManager.requestLocationUpdates(locationManager.getBestProvider(criteria, false), 600, 50, this);
            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        try {
            locationManager.requestLocationUpdates(locationManager.getBestProvider(criteria, false), 600, 50, locLis);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            locationManager.removeUpdates(locLis);
        } catch (Exception e ) {
            //Nothing to do here
        }
    }


}
