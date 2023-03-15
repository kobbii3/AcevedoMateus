package com.example.acevedomateus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Producto> listaPrincipalProductos;
    private RecyclerView rvListadoProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarDatos();

        rvListadoProductos = findViewById(R.id.rv_listado_productos);

        AdaptadorPersonalizado miAdaptador = new AdaptadorPersonalizado(listaPrincipalProductos);

        miAdaptador.setOnItemClickListener(new AdaptadorPersonalizado.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {
                Toast.makeText(MainActivity.this, "HICE CLICK DESDE ACTIVITY "+miProducto.getNombre(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemBtnEliminarClick(Producto miProducto, int posicion) {
                listaPrincipalProductos.remove(posicion);
                miAdaptador.setListadoInformacion(listaPrincipalProductos);
            }
        });
        rvListadoProductos.setAdapter(miAdaptador);
        rvListadoProductos.setLayoutManager(new LinearLayoutManager(this));
    }

    public void cargarDatos(){
        Producto producto1 = new Producto();
        producto1.setNombre("Computador HP");
        producto1.setPrecio(8000000.0);
        producto1.setUrl("https://pcsystemcolombia.com/wp-content/uploads/2020/11/Todo-En-Uno-HP-200-G4-22-Core-i3.png");

        Producto producto2 = new Producto("Teclado",150000.0,"https://img2.freepng.es/20180202/ewe/kisspng-computer-keyboard-computer-mouse-computer-hardware-keyboard-png-clipart-5a7537a97d5f00.1614853815176314015135.jpg");
        Producto producto3 = new Producto("Mouse",100000.0,"https://pngimg.com/d/computer_mouse_PNG7700.png");

        listaPrincipalProductos = new ArrayList<>();
        //agregar los productos
        listaPrincipalProductos.add(producto1);
        listaPrincipalProductos.add(producto2);
        listaPrincipalProductos.add(producto3);

    }
}