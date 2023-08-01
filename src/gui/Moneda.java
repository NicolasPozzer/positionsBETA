
package gui;

import com.fasterxml.jackson.annotation.JsonProperty;




public class Moneda {
    @JsonProperty("ID")
    private int ID;
    

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("posicion")
    private int posicion;

    @JsonProperty("estadoDePosicion")
    private int estadoDePosicion;

    // Getters y Setters (puedes generarlos automáticamente en la mayoría de IDEs)

    // Agrega los getters y setters para las propiedades
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getEstadoDePosicion() {
        return estadoDePosicion;
    }

    public void setEstadoDePosicion(int estadoDePosicion) {
        this.estadoDePosicion = estadoDePosicion;
    }
}



/*
    private void btnBtcActionPerformed(java.awt.event.ActionEvent evt) {                                       

        if (numBtc < 3) {
            numBtc = numBtc + 1;
            
            if (numBtc == 3) {
                numBtc = 0;
            }
        }

        switch (numBtc) {
            case 0:
                btnBtc.setBackground(new Color(186,186,186)); // Numero 0 = Gris
                
            break;
            case 1:
                btnBtc.setBackground(Color.orange); // Numero 1 = Naranja
            break;
            case 2:
                btnBtc.setBackground(new Color(132, 208, 55)); // Numero 2 = Verde
            break;
        }
        

    }   */                                   














/*
    private void btnBtcStateActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
        
        if (numBtcState < 2) {
            numBtcState = numBtcState + 1;
            
            if (numBtcState == 2) {
                numBtcState = 0;
            }
        }

        switch (numBtcState) {
            
            case 0:
                btnBtcState.setBackground(new Color(252,99,86)); // Numero 0 = Gris
                btnBtcState.setText("Desbalanced");
            break;
            case 1:
                btnBtcState.setBackground(Color.green); // Numero 1 = Verde
                btnBtcState.setText("Balanced");
            break;
        } 
    }    */   