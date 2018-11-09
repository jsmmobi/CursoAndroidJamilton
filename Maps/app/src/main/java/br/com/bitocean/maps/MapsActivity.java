package br.com.bitocean.maps;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import static br.com.bitocean.maps.R.drawable.oldcar;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    LatLng ibirapuera;
    private LatLng localizacaoAntiga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);



        // Add a marker in Sydney and move the camera
        // Ibirapuera -23.587878, -46.657988
        ibirapuera = new LatLng(-23.587878, -46.657988);

        // Add Circle
        addCircle(ibirapuera,500);
        localizacaoAntiga = ibirapuera;
        addPolygon();
        addPolygon2();

        //Adiciona marcador
        addMarker(ibirapuera);

        moveCameraZoom(ibirapuera);
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(ibirapuera).title("Ibirapuera"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ibirapuera));
    }

    private void moveCameraZoom(LatLng ibirapuera) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ibirapuera,15));
    }

    private void addMarker(LatLng ibirapuera, int resource,String title,String description) {

        mMap.addMarker(
                new MarkerOptions()
                .position(ibirapuera)
                .title(title)
                .snippet(description)
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                .icon(BitmapDescriptorFactory.fromResource(resource))

        );
    }

    private void addMarker(LatLng latLng,int resource){
        addMarker(latLng,resource,"Local","");
    }

    private void addMarker(LatLng latLng){
        addMarker(latLng,R.drawable.car,"Local","");
    }

    @Override
    public void onMapClick(LatLng latLng) {
        addMarker(latLng,R.drawable.store,"Local","");

        addPolyline(ibirapuera,latLng);
        localizacaoAntiga = latLng;

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        addMarker(latLng,R.drawable.oldcar);


    }

    private void addCircle(LatLng latLng,int radius){
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(radius);//Metros
        circleOptions.fillColor(Color.argb(128,145,148,5));
        circleOptions.strokeWidth(1);
        circleOptions.strokeColor(Color.GREEN);
        mMap.addCircle(circleOptions);
    }


    private void addPolygon(){
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-23.586332, -46.658754));
        polygonOptions.add(new LatLng(-23.585615, -46.656662));
        polygonOptions.add(new LatLng(-23.587158, -46.657037));
        polygonOptions.add(new LatLng(-23.587247, -46.658797));

        polygonOptions.strokeWidth(1);


        polygonOptions.fillColor(Color.BLUE);

        mMap.addPolygon(polygonOptions);
    }

    private void addPolygon2(){
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-23.586332, -46.658754));

        polygonOptions.add(new LatLng(-23.584110, -46.660533));
        polygonOptions.add(new LatLng(-23.584859, -46.660105));
        polygonOptions.add(new LatLng(-23.585189, -46.661244));
        polygonOptions.strokeColor(Color.MAGENTA);

        mMap.addPolygon(polygonOptions);
    }


    private void addPolyline(LatLng origem,LatLng destino){
        PolygonOptions options = new PolygonOptions();
        options.add(origem);
        options.add(destino);
        options.fillColor(Color.RED);
        options.strokeColor(Color.RED);
        options.strokeWidth(20);

        mMap.addPolygon(options);

    }

    /*
    -23.586332, -46.658754
2) -23.585615, -46.656662
3) -23.587158, -46.657037
4) -23.587247, -46.658797
     */
}
