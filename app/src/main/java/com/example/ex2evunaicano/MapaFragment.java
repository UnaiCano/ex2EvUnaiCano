package com.example.ex2evunaicano;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ex2evunaicano.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;


public class MapaFragment extends Fragment {


    private MapView map = null;
    private IMapController mapController;
    private MyLocationNewOverlay myLocationOverlay;
    private GeoPoint geoPoint, geoPoint2;

    public MapaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mapa, container, false);

        // Configuración de osmdroid
        Context ctx = requireActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        Configuration.getInstance().setUserAgentValue(requireActivity().getPackageName());

        map = view.findViewById(R.id.mapview);

        // Inicializar myLocationOverlay antes de usarlo
        myLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(ctx), map);
        myLocationOverlay.enableMyLocation();
        myLocationOverlay.enableFollowLocation();
        map.getOverlays().add(myLocationOverlay);
        geoPoint = new GeoPoint( 43.2837756904145, -2.964419127575523);
        geoPoint2 = new GeoPoint(43.264171, 2.949346);

        setupMap();


        return view;
    }

    private void setupMap() {
        // Configuración del mapa
        map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        map.setMultiTouchControls(true);
        mapController = map.getController();
        mapController.setZoom(18.0);

        //Poner centro de las Coordenadas
        mapController.setCenter(geoPoint);

        map.setUseDataConnection(true);

        // Llamar a la función para agregar el marcador en las coordenadas especificadas
        addMarker(geoPoint.getLatitude(), geoPoint.getLongitude(), "Elorrieta");
        addMarker(geoPoint2.getLatitude(), geoPoint2.getLongitude(), "San Mames");


        //SALE EL SEGUNDO MARKER EN FRANCIA
    }

    private void addMarker(double latitude, double longitude, String title) {
        // Crear un marcador en las coordenadas especificadas
        org.osmdroid.views.overlay.Marker marker = new org.osmdroid.views.overlay.Marker(map);
        marker.setPosition(new GeoPoint(latitude, longitude));
        marker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_BOTTOM);
        marker.setTitle(title);

        // Agregar el marcador al mapa
        map.getOverlays().add(marker);

        // Configurar OnClickListener para el marcador
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Activity a = getActivity();
                if(marker.getTitle().equals("Elorrieta")){
                    ((MainActivity) a).cambiarFragment(2);
                }
                if(marker.getTitle().equals("San Mames")){
                    ((MainActivity) a).cambiarFragment(3);
                }

                return true; // Indica que el evento ha sido consumido
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }
}