
package views;

import data.Persistencia;
import domain.Carnivoro;
import domain.Especie;
import domain.Herbivoro;
import domain.Pais;
import domain.Sector;
import domain.TipoAlimentacion;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorAgregarAnimalView {
    static AgregarAnimalView view = new AgregarAnimalView();
     public static void mostrar(){
        view.setVisible(true);
        cargarComboBox();
     }
      public static void ocultar(){
        view.setVisible(false);
     }


     //funcion para cargar los comboBox
    public static void cargarComboBox(){
        limpiarComponentes();
        ArrayList<Especie> especies = Persistencia.getEspecies();
        ArrayList<Pais> paises = Persistencia.getPaises();
        for (Especie especie : especies) {
            view.getjComboBox_especie().addItem(especie.getNombre());
        }
        for (Pais pais : paises) {
            view.getjComboBox_paisOrigen().addItem(pais.getNombre());
        }
    }  
    
    public static void seleccionarAnimal(){
        
         Object seleccionado = view.getjComboBox_especie().getSelectedItem();
         if (seleccionado == null || seleccionado.toString().equals("Seleccionar")) {
                return; // cuando no hay seleccion valida
          }

        String nombre =  view.getjComboBox_especie().getSelectedItem().toString();
        
         ArrayList<Especie> especies = Persistencia.getEspecies();
         ArrayList<Sector> sectores = Persistencia.getSectores();

          for (Especie especie : especies) {
            if(nombre.equals(especie.getNombre())){
                 
                if(especie.getTipoAlimentacion().esHerbivoro()){
                    view.getjTextField_valorFijo().setEditable(true);
                    view.getjTextField_tipoAlimentacion().setText("HERBIVORO");
                }else{
                    view.getjTextField_valorFijo().setEditable(false);
                    view.getjTextField_tipoAlimentacion().setText("CARNIVORO");
                }
                
                view.getjComboBox_sector().removeAllItems();
                view.getjComboBox_sector().addItem("Seleccionar");
                for (Sector sector : sectores) {
                        if(especie.getTipoAlimentacion()== sector.getTipoAlimentacion()){
                            view.getjComboBox_sector().addItem(sector.getNumero()+"");
                        }          
                 }
            }
        }
    }
    
    
    public static void botonAgregarAnimal(){              
        if(view.getjComboBox_especie().getSelectedItem().toString() == "Seleccionar" ||
                view.getjComboBox_sector().getSelectedItem().toString() ==  "Seleccionar" ||
                view.getjComboBox_paisOrigen().getSelectedItem().toString() ==  "Seleccionar" ||
                "".equals(view.getjTextField_Edad().getText()) || 
                "".equals(view.getjTextField_Peso().getText())){
            JOptionPane.showMessageDialog(view,"ERROR/ Completar todos los campos");
        }else{
            String especie = view.getjComboBox_especie().getSelectedItem().toString();
            int sector = Integer.parseInt(view.getjComboBox_sector().getSelectedItem().toString());
            String pais = view.getjComboBox_paisOrigen().getSelectedItem().toString();
            int edad = Integer.parseInt(view.getjTextField_Edad().getText());
            double peso = Double.parseDouble(view.getjTextField_Peso().getText());
            String tipoAlimentacion = view.getjTextField_tipoAlimentacion().getText();
            Especie e = obtenerEspecie(especie);
            Sector s = obtenerSector(sector);
            Pais p = obtenerPais(pais);
       
            if("HERBIVORO".equals(view.getjTextField_tipoAlimentacion().getText())){
                if("".equals(view.getjTextField_valorFijo().getText())){
                     JOptionPane.showMessageDialog(view,"ERROR/ Debe ingresar el valor Fijo");
                }else{
                    double valorFijo = Double.parseDouble(view.getjTextField_valorFijo().getText()); 
                    try {
                        Persistencia.guardarAnimal(new Herbivoro(
                            edad,
                            peso,
                            e,
                            s,
                            valorFijo,
                            p
                        ));
                        
                         JOptionPane.showMessageDialog(null, "Animal Guardado Correctamente");
                         limpiarComponentes();
                         cargarComboBox();
                        } catch (InvalidPropertiesFormatException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al guardar el animal: " + ex.getMessage());
                        }
                }
            }else{
                try {
                    Persistencia.guardarAnimal(new Carnivoro(edad, peso, e, s, p));
                    JOptionPane.showMessageDialog(null, "Animal Guardado Correctamente");
                    limpiarComponentes();
                    cargarComboBox();
                } catch (InvalidPropertiesFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar el animal: " + ex.getMessage());
                }
            }  
        }
        
    }
    
    public static void limpiarComponentes(){
        
         view.getjComboBox_especie().removeAllItems();
         view.getjComboBox_sector().removeAllItems();
         view.getjComboBox_paisOrigen().removeAllItems();
         
         view.getjComboBox_especie().addItem("Seleccionar");
         view.getjComboBox_sector().addItem("Seleccionar");
         view.getjComboBox_paisOrigen().addItem("Seleccionar");
         view.getjTextField_Edad().setText("");
         view.getjTextField_Peso().setText("");
         view.getjTextField_tipoAlimentacion().setText("");
         view.getjTextField_valorFijo().setText("");
    }
    public static Especie obtenerEspecie(String especie){
        ArrayList<Especie> especies = Persistencia.getEspecies();
        
        for (Especie especy : especies) {
            if(especy.getNombre().equals(especie)){
                return especy;    
            }
        }
          return null; 
    }
    
     public static Sector obtenerSector(int s){
        ArrayList<Sector> sectores = Persistencia.getSectores();
         for (Sector sector : sectores) {
             if(s == sector.getNumero()){
                 return sector;
             }
         }
          return null; 
    }
     
     public static Pais obtenerPais(String p){
        ArrayList<Pais> paises = Persistencia.getPaises();
         for (Pais paise : paises) {
             if(p.equals(paise.getNombre())){
                 return paise;
             }
         }
          return null; 
    }
}
