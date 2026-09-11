package vallegrande.edu.pe.misistema.controller;

import vallegrande.edu.pe.misistema.view.MainView;

public class MainController {

    private MainView view;
    public MainController(MainView view){
        this.view = view;
        configurarEventos();
    }
    private void configurarEventos(){
        view.getBntInicio().setOnAction(e->{
            view.mostrarInicio();
        });

        view.getBtnUsuario().setOnAction(e->{
            view.mostrarUsuario();
        });

        view.getBtnProductos().setOnAction(e->{
            view.mostrarProductos();
        });
        view.getBtnVentas().setOnAction(e-> {
            view.mostrarVentas();
        });
        view.getBtnReportes().setOnAction(e->{
             view.mostrarReportes();
        });
        view.getBtnConfiguracion().setOnAction(e->{
             view.mostrarConfiguracion();
        });
    }
}
