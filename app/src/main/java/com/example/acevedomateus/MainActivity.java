package com.example.acevedomateus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Producto> listaPrincipalProductos = new ArrayList<>();
    private RecyclerView rvListadoProductos;
    private AdaptadorPersonalizado miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.txt_listado));

        cargarDatos();

        rvListadoProductos = findViewById(R.id.rv_listado_productos);

        miAdaptador = new AdaptadorPersonalizado(listaPrincipalProductos);

        miAdaptador.setOnItemClickListener(new AdaptadorPersonalizado.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {
                Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                intent.putExtra("producto", miProducto);
                startActivity(intent);
            }

            @Override
            public void onItemBtnEliminarClick(Producto miProducto, int posicion) {
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("productos").document(miProducto.getId()).delete();

                listaPrincipalProductos.remove(posicion);
                miAdaptador.setListadoInformacion(listaPrincipalProductos);
            }
        });
        rvListadoProductos.setAdapter(miAdaptador);
        rvListadoProductos.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume(){
        super.onResume();
        listaPrincipalProductos.clear();
        cargarDatos();
    }

    public void cargarDatos(){

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (DocumentSnapshot document : task.getResult()){
                        Producto productoAtrapado = document.toObject(Producto.class);
                        productoAtrapado.setId(document.getId());
                        listaPrincipalProductos.add(productoAtrapado);
                    }

                    miAdaptador.setListadoInformacion(listaPrincipalProductos);

                }else{
                    Toast.makeText(MainActivity.this, "No se pudo conectar al servidor", Toast.LENGTH_SHORT);
                }
            }
        });

        //crear productos
        Producto producto1 = new Producto();
        producto1.setNombre("Computador HP");
        producto1.setPrecio(8000000.0);
        producto1.setUrl("https://pcsystemcolombia.com/wp-content/uploads/2020/11/Todo-En-Uno-HP-200-G4-22-Core-i3.png");

        Producto producto2 = new Producto("Teclado",150000.0,"https://img2.freepng.es/20180202/ewe/kisspng-computer-keyboard-computer-mouse-computer-hardware-keyboard-png-clipart-5a7537a97d5f00.1614853815176314015135.jpg");
        Producto producto3 = new Producto("Mouse",100000.0,"https://pngimg.com/d/computer_mouse_PNG7700.png");;
        //agregar los productos
        listaPrincipalProductos.add(producto1);
        listaPrincipalProductos.add(producto2);
        listaPrincipalProductos.add(producto3);
        listaPrincipalProductos.add(producto1);
        listaPrincipalProductos.add(producto2);
        listaPrincipalProductos.add(producto3);
        listaPrincipalProductos.add(producto1);
        listaPrincipalProductos.add(producto2);
        listaPrincipalProductos.add(producto3);
    }

    public void clickCerrarSesion(View view){
        SharedPreferences miPreferencias = getSharedPreferences("tienda_app", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = miPreferencias.edit();
        myEditor.clear();
        myEditor.apply();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void clickAgregarProducto(View view){
        startActivity(new Intent(this, FormularyActivity.class));
    }
}