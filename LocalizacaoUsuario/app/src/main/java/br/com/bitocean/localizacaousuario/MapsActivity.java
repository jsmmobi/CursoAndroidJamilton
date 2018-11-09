package br.com.bitocean.localizacaousuario;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] permissoes = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Configura Permissões
        AndroidPermissions.validaPermissoes(this, permissoes, 1);




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void startLocationManager(){
        // Configura
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location:: ",location.toString());
                mMap.clear();
                updateLocationInMap(location);


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        // Verifica e adiona o listener
        verificarSeEstaHabilidadoPegarLocalizacao();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        startLocationManager();

    }


    private void updateLocationInMap(Location location){
        LatLng latLng =new LatLng(location.getLatitude(),location.getLongitude());
        if(mMap !=null){
            List<Address> addressList =geocoder(location);
            if(addressList!=null && !addressList.isEmpty()){
              Address address = addressList.get(0);
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(address.getAddressLine(0))
                        .snippet(address.getAdminArea()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
            }else{
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(location.getExtras().toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
            }


            List<Address> addresses = geocoder("Rua Bertióga ");
            for(Address a:addresses){
                Log.d("Address", "updateLocationInMap: "+a.getAddressLine(0));
            }

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int resultado : grantResults) {
            if (resultado == PackageManager.PERMISSION_DENIED) {
                // Alerta de negação
                alertaValidacaoPermissao();
            } else if (resultado == PackageManager.PERMISSION_GRANTED) {
                // Recupera localização
                verificarSeEstaHabilidadoPegarLocalizacao();

            }
        }
    }


    private void verificarSeEstaHabilidadoPegarLocalizacao(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    10000,
                    0,
                    locationListener

            );
        }
    }

    private List<Address> geocoder(Location location){
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> result = null;
        try {
            result = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private List<Address> geocoder(String locationName){
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> result = null;
        try {
            result = geocoder.getFromLocationName(locationName,10);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }


    private void alertaValidacaoPermissao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Permissões negadas")
                .setMessage("Para utilizar o aplicativo é necessário ativar essa permissão")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
