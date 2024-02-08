package com.example.ex2evunaicano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cambiarFragment(1);

    }

    public void cambiarFragment(int id){
        // Obtén el FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Inicia una transacción
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if(id == 1){
            // Reemplaza el contenido del contenedor con el Fragment
            transaction.replace(R.id.fragmentCV,new MapaFragment());
        }
        else if(id == 2){
            transaction.replace(R.id.fragmentCV,new FragmentInfo_Elorrieta());
        }
        else if(id == 3){
            transaction.replace(R.id.fragmentCV,new FragmentInfo_SanMames());
        }


        // Confirma la transacción
        transaction.commit();
    }
}