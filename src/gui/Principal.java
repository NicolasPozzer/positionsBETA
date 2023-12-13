package gui;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JButton;

public class Principal extends javax.swing.JFrame {

    int estadoCandado = 0;

    private List<Moneda> monedas = new ArrayList<>();

    public Principal() {
        initComponents();

        bloquear.setVisible(false);

        leerArchivoJson();
        actualizarBotones();
        actualizarStateBotones();
    }

    private void leerArchivoJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File archivoJson = new File("estados_botones.json");

            if (archivoJson.exists()) {
                // Si el archivo existe, lo leemos y cargamos la lista de monedas
                monedas = objectMapper.readValue(archivoJson, new TypeReference<List<Moneda>>() {
                });
            } else {
                // Si el archivo no existe, inicializamos la lista de monedas
                inicializarMonedas();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*=====================================================
===Se ejecuta solo si NO hay ARCHIVO JSON EXISTENTE====
=========================vvvvvv========================   */
    private void inicializarMonedas() {
        // Aquí puedes inicializar la lista de monedas según la cantidad de botones que tengas en tu interfaz
        // Por ejemplo, si tienes 3 botones, puedes agregar 3 instancias de Moneda a la lista y configurar sus propiedades.
        // Luego, guardar la lista en el archivo JSON.
        Moneda moneda1 = new Moneda();
        moneda1.setID(1);
        moneda1.setNombre("BTC");
        moneda1.setPosicion(0);
        moneda1.setEstadoDePosicion(0);

        Moneda moneda2 = new Moneda();
        moneda2.setID(2);
        moneda2.setNombre("ETH");
        moneda2.setPosicion(0);
        moneda2.setEstadoDePosicion(0);

        Moneda moneda3 = new Moneda();
        moneda3.setID(3);
        moneda3.setNombre("Gold");
        moneda3.setPosicion(0);
        moneda3.setEstadoDePosicion(0);

        Moneda moneda4 = new Moneda();
        moneda4.setID(4);
        moneda4.setNombre("EUR/USD");
        moneda4.setPosicion(0);
        moneda4.setEstadoDePosicion(0);

        Moneda moneda5 = new Moneda();
        moneda5.setID(5);
        moneda5.setNombre("S&P");
        moneda5.setPosicion(0);
        moneda5.setEstadoDePosicion(0);

        Moneda moneda6 = new Moneda();
        moneda6.setID(6);
        moneda6.setNombre("Silver");
        moneda6.setPosicion(0);
        moneda6.setEstadoDePosicion(0);

        Moneda moneda7 = new Moneda();
        moneda7.setID(7);
        moneda7.setNombre("DOW");
        moneda7.setPosicion(0);
        moneda7.setEstadoDePosicion(0);

        Moneda moneda8 = new Moneda();
        moneda8.setID(8);
        moneda8.setNombre("DAX");
        moneda8.setPosicion(0);
        moneda8.setEstadoDePosicion(0);

        Moneda moneda9 = new Moneda();
        moneda9.setID(9);
        moneda9.setNombre("OIL");
        moneda9.setPosicion(0);
        moneda9.setEstadoDePosicion(0);

        Moneda moneda10 = new Moneda();
        moneda10.setID(10);
        moneda10.setNombre("R2K");
        moneda10.setPosicion(0);
        moneda10.setEstadoDePosicion(0);

        Moneda moneda11 = new Moneda();
        moneda11.setID(11);
        moneda11.setNombre("AAPL");
        moneda11.setPosicion(0);
        moneda11.setEstadoDePosicion(0);

        Moneda moneda12 = new Moneda();
        moneda12.setID(12);
        moneda12.setNombre("XRP");
        moneda12.setPosicion(0);
        moneda12.setEstadoDePosicion(0);

        monedas.add(moneda1);
        monedas.add(moneda2);
        monedas.add(moneda3);
        monedas.add(moneda4);
        monedas.add(moneda5);
        monedas.add(moneda6);
        monedas.add(moneda7);
        monedas.add(moneda8);
        monedas.add(moneda9);
        monedas.add(moneda10);
        monedas.add(moneda11);
        monedas.add(moneda12);

        guardarMonedasEnJson();
    }

    /*=====================================================
========TIPO DE ORDEN-ABIERTA-EN-ESPERA-CERRADA========
=======================================================   */
    private Moneda encontrarMonedaPorId(int id) {
        // Encuentra la moneda correspondiente al ID en la lista de monedas
        for (Moneda moneda : monedas) {
            if (moneda.getID() == id) {
                return moneda;
            }
        }
        return null; // Retorna null si no se encuentra la moneda con el ID especificado
    }

    private void actualizarBotones() {

        // Actualiza el estado de los botones en tu interfaz gráfica según la lista de monedas
        // Recorre la lista y ajusta el estado de cada botón.
        for (Moneda moneda : monedas) {

            int id = moneda.getID();
            int estadoDePosicion = moneda.getEstadoDePosicion();

            JButton boton = encontrarBotonPorId(id);
            boton.setText(moneda.getNombre());

            switch (estadoDePosicion) {
                case 0:
                    boton.setBackground(new Color(186, 186, 186)); // Gris

                    break;
                case 1:
                    boton.setBackground(Color.orange); // Naranja

                    break;
                case 2:
                    boton.setBackground(new Color(132, 208, 55)); // Verde

                    break;
            }
        }
    }

    private void actualizarMoneda(int id) {
        // Encuentra la moneda correspondiente al ID en la lista de monedas
        Moneda moneda = encontrarMonedaPorId(id);

        // Actualiza el estado de la moneda
        int estadoDePosicion = moneda.getEstadoDePosicion();
        estadoDePosicion = (estadoDePosicion + 1) % 3; // Cambia el estado cíclicamente entre 0, 1 y 2
        moneda.setEstadoDePosicion(estadoDePosicion);
    }

    /*=====================================================
===================BALANCEADO/DESBALANCEADO============
=======================================================   */
    private void actualizarStateBotones() {
        // Actualiza el estado de los botones en tu interfaz gráfica según la lista de monedas
        // Recorre la lista y ajusta el estado de cada botón.
        for (Moneda moneda : monedas) {
            int id = moneda.getID();
            int posicion = moneda.getPosicion();
            JButton boton = encontrarStateBotonPorId(id);

            switch (posicion) {
                case 0:
                    boton.setText("Desbalanced");
                    boton.setBackground(new Color(252, 99, 86)); // Gris

                    break;

                case 1:
                    boton.setText("Balanced");
                    boton.setBackground(Color.green); // Verde

                    break;
            }
        }
    }

    private void actualizarEstadoMoneda(int id) {
        // Encuentra la moneda correspondiente al ID en la lista de monedas
        Moneda moneda = encontrarMonedaPorId(id);

        // Actualiza el estado de la moneda
        int posicion = moneda.getPosicion();
        posicion = (posicion + 1) % 2; // Cambia el estado cíclicamente entre 0, 1 y 2
        moneda.setPosicion(posicion);

    }

    private JButton encontrarBotonPorId(int id) {
        // Implementa la lógica para encontrar el botón correspondiente al ID en tu interfaz gráfica.
        // Por ejemplo, si el ID 1 representa btnBtc, debes devolver btnBtc.
        // Debes hacer esto para cada botón.
        switch (id) {
            case 1:
                return btnBtc;
            case 2:
                return btnBtc1;
            case 3:
                return btnBtc2;
            case 4:
                return btnBtc3;
            case 5:
                return btnBtc4;
            case 6:
                return btnBtc5;
            case 7:
                return btnBtc6;
            case 8:
                return btnBtc7;
            case 9:
                return btnBtc8;
            case 10:
                return btnBtc9;
            case 11:
                return btnBtc10;
            case 12:
                return btnBtc11;
            default:
                return null;
        }
    }

    private JButton encontrarStateBotonPorId(int id) {
        // Implementa la lógica para encontrar el botón correspondiente al ID en tu interfaz gráfica.
        // Por ejemplo, si el ID 1 representa btnBtc, debes devolver btnBtc.
        // Debes hacer esto para cada botón.
        switch (id) {
            case 1:
                return btnBtcState;
            case 2:
                return btnBtcState1;
            case 3:
                return btnBtcState2;
            case 4:
                return btnBtcState3;
            case 5:
                return btnBtcState4;
            case 6:
                return btnBtcState5;
            case 7:
                return btnBtcState6;
            case 8:
                return btnBtcState7;
            case 9:
                return btnBtcState8;
            case 10:
                return btnBtcState9;
            case 11:
                return btnBtcState10;
            case 12:
                return btnBtcState11;
            default:
                return null;
        }
    }

    private void guardarMonedasEnJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File archivoJson = new File("estados_botones.json");
            objectMapper.writeValue(archivoJson, monedas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        btnBtc = new javax.swing.JButton();
        btnBtcState = new javax.swing.JButton();
        btnBtc1 = new javax.swing.JButton();
        btnBtcState1 = new javax.swing.JButton();
        btnBtc2 = new javax.swing.JButton();
        btnBtcState2 = new javax.swing.JButton();
        btnBtc3 = new javax.swing.JButton();
        btnBtcState3 = new javax.swing.JButton();
        btnBtc4 = new javax.swing.JButton();
        btnBtcState4 = new javax.swing.JButton();
        btnBtc5 = new javax.swing.JButton();
        btnBtcState5 = new javax.swing.JButton();
        btnBtc6 = new javax.swing.JButton();
        btnBtcState6 = new javax.swing.JButton();
        btnBtc7 = new javax.swing.JButton();
        btnBtcState7 = new javax.swing.JButton();
        btnBtc8 = new javax.swing.JButton();
        btnBtcState8 = new javax.swing.JButton();
        btnBtc9 = new javax.swing.JButton();
        btnBtcState9 = new javax.swing.JButton();
        btnBtc10 = new javax.swing.JButton();
        btnBtcState10 = new javax.swing.JButton();
        btnBtc11 = new javax.swing.JButton();
        btnBtcState11 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        candado = new javax.swing.JButton();
        bloquear = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setEnabled(false);

        btnBtc.setBackground(new java.awt.Color(186, 186, 186));
        btnBtc.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBtc.setBorderPainted(false);
        btnBtc.setFocusPainted(false);
        btnBtc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcActionPerformed(evt);
            }
        });

        btnBtcState.setBackground(new java.awt.Color(252, 99, 86));
        btnBtcState.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        btnBtcState.setText("Desbalanced");
        btnBtcState.setBorderPainted(false);
        btnBtcState.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBtcState.setFocusPainted(false);
        btnBtcState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcStateActionPerformed(evt);
            }
        });

        btnBtc1.setBackground(new java.awt.Color(186, 186, 186));
        btnBtc1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBtc1.setBorderPainted(false);
        btnBtc1.setFocusPainted(false);
        btnBtc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtc1ActionPerformed(evt);
            }
        });

        btnBtcState1.setBackground(new java.awt.Color(252, 99, 86));
        btnBtcState1.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        btnBtcState1.setText("Desbalanced");
        btnBtcState1.setBorderPainted(false);
        btnBtcState1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBtcState1.setFocusPainted(false);
        btnBtcState1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcState1ActionPerformed(evt);
            }
        });

        btnBtc2.setBackground(new java.awt.Color(186, 186, 186));
        btnBtc2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBtc2.setBorderPainted(false);
        btnBtc2.setFocusPainted(false);
        btnBtc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtc2ActionPerformed(evt);
            }
        });

        btnBtcState2.setBackground(new java.awt.Color(252, 99, 86));
        btnBtcState2.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        btnBtcState2.setText("Desbalanced");
        btnBtcState2.setBorderPainted(false);
        btnBtcState2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBtcState2.setFocusPainted(false);
        btnBtcState2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcState2ActionPerformed(evt);
            }
        });

        btnBtc3.setBackground(new java.awt.Color(186, 186, 186));
        btnBtc3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBtc3.setBorderPainted(false);
        btnBtc3.setFocusPainted(false);
        btnBtc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtc3ActionPerformed(evt);
            }
        });

        btnBtcState3.setBackground(new java.awt.Color(252, 99, 86));
        btnBtcState3.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        btnBtcState3.setText("Desbalanced");
        btnBtcState3.setBorderPainted(false);
        btnBtcState3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBtcState3.setFocusPainted(false);
        btnBtcState3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcState3ActionPerformed(evt);
            }
        });

        btnBtc4.setBackground(new java.awt.Color(186, 186, 186));
        btnBtc4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBtc4.setBorderPainted(false);
        btnBtc4.setFocusPainted(false);
        btnBtc4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtc4ActionPerformed(evt);
            }
        });

        btnBtcState4.setBackground(new java.awt.Color(252, 99, 86));
        btnBtcState4.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        btnBtcState4.setText("Desbalanced");
        btnBtcState4.setBorderPainted(false);
        btnBtcState4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBtcState4.setFocusPainted(false);
        btnBtcState4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcState4ActionPerformed(evt);
            }
        });

        btnBtc5.setBackground(new java.awt.Color(186, 186, 186));
        btnBtc5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBtc5.setBorderPainted(false);
        btnBtc5.setFocusPainted(false);
        btnBtc5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtc5ActionPerformed(evt);
            }
        });

        btnBtcState5.setBackground(new java.awt.Color(252, 99, 86));
        btnBtcState5.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        btnBtcState5.setText("Desbalanced");
        btnBtcState5.setBorderPainted(false);
        btnBtcState5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBtcState5.setFocusPainted(false);
        btnBtcState5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcState5ActionPerformed(evt);
            }
        });

        btnBtc6.setBackground(new java.awt.Color(186, 186, 186));
        btnBtc6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBtc6.setBorderPainted(false);
        btnBtc6.setFocusPainted(false);
        btnBtc6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtc6ActionPerformed(evt);
            }
        });

        btnBtcState6.setBackground(new java.awt.Color(252, 99, 86));
        btnBtcState6.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        btnBtcState6.setText("Desbalanced");
        btnBtcState6.setBorderPainted(false);
        btnBtcState6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBtcState6.setFocusPainted(false);
        btnBtcState6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcState6ActionPerformed(evt);
            }
        });

        btnBtc7.setBackground(new java.awt.Color(186, 186, 186));
        btnBtc7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBtc7.setBorderPainted(false);
        btnBtc7.setFocusPainted(false);
        btnBtc7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtc7ActionPerformed(evt);
            }
        });

        btnBtcState7.setBackground(new java.awt.Color(252, 99, 86));
        btnBtcState7.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        btnBtcState7.setText("Desbalanced");
        btnBtcState7.setBorderPainted(false);
        btnBtcState7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBtcState7.setFocusPainted(false);
        btnBtcState7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcState7ActionPerformed(evt);
            }
        });

        btnBtc8.setBackground(new java.awt.Color(186, 186, 186));
        btnBtc8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBtc8.setBorderPainted(false);
        btnBtc8.setFocusPainted(false);
        btnBtc8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtc8ActionPerformed(evt);
            }
        });

        btnBtcState8.setBackground(new java.awt.Color(252, 99, 86));
        btnBtcState8.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        btnBtcState8.setText("Desbalanced");
        btnBtcState8.setBorderPainted(false);
        btnBtcState8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBtcState8.setFocusPainted(false);
        btnBtcState8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcState8ActionPerformed(evt);
            }
        });

        btnBtc9.setBackground(new java.awt.Color(186, 186, 186));
        btnBtc9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBtc9.setBorderPainted(false);
        btnBtc9.setFocusPainted(false);
        btnBtc9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtc9ActionPerformed(evt);
            }
        });

        btnBtcState9.setBackground(new java.awt.Color(252, 99, 86));
        btnBtcState9.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        btnBtcState9.setText("Desbalanced");
        btnBtcState9.setBorderPainted(false);
        btnBtcState9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBtcState9.setFocusPainted(false);
        btnBtcState9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcState9ActionPerformed(evt);
            }
        });

        btnBtc10.setBackground(new java.awt.Color(186, 186, 186));
        btnBtc10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBtc10.setBorderPainted(false);
        btnBtc10.setFocusPainted(false);
        btnBtc10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtc10ActionPerformed(evt);
            }
        });

        btnBtcState10.setBackground(new java.awt.Color(252, 99, 86));
        btnBtcState10.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        btnBtcState10.setText("Desbalanced");
        btnBtcState10.setBorderPainted(false);
        btnBtcState10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBtcState10.setFocusPainted(false);
        btnBtcState10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcState10ActionPerformed(evt);
            }
        });

        btnBtc11.setBackground(new java.awt.Color(186, 186, 186));
        btnBtc11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnBtc11.setBorderPainted(false);
        btnBtc11.setFocusPainted(false);
        btnBtc11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtc11ActionPerformed(evt);
            }
        });

        btnBtcState11.setBackground(new java.awt.Color(252, 99, 86));
        btnBtcState11.setFont(new java.awt.Font("Monospaced", 3, 12)); // NOI18N
        btnBtcState11.setText("Desbalanced");
        btnBtcState11.setBorderPainted(false);
        btnBtcState11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBtcState11.setFocusPainted(false);
        btnBtcState11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBtcState11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBtcState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBtc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBtcState2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBtc2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBtcState9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBtc9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBtcState4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBtc4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBtcState1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBtc1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBtcState3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBtc3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBtcState10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBtc10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBtcState5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBtc5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBtcState8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBtc8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBtcState7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBtc7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBtcState11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBtc11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBtcState6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBtc6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnBtc1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBtcState1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnBtc8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBtcState8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnBtc3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBtcState3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnBtc7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBtcState7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnBtc5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBtcState5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnBtc6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBtcState6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnBtc10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBtcState10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnBtc11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBtcState11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnBtc, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBtcState, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnBtc2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBtcState2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnBtc4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBtcState4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnBtc9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBtcState9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jRadioButton1.setSelected(true);
        jRadioButton1.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 2, 36)); // NOI18N
        jLabel1.setText("Positions");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(161, 161, 161));
        jLabel4.setText("-");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 174, 79));
        jLabel3.setText("-");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(158, 225, 80));
        jLabel5.setText("-");

        jLabel6.setText("off");

        jLabel7.setText("Waiting..");

        jLabel8.setText("Open");

        candado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/block.png"))); // NOI18N
        candado.setBorder(null);
        candado.setFocusCycleRoot(true);
        candado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                candadoActionPerformed(evt);
            }
        });

        bloquear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/unlock.png"))); // NOI18N
        bloquear.setBorder(null);
        bloquear.setFocusCycleRoot(true);
        bloquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloquearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(66, 66, 66)
                .addComponent(candado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bloquear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(candado)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bloquear))))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Bookman Old Style", 0, 14)); // NOI18N
        jLabel2.setText("by Niko7even");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(395, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(29, 29, 29))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 31, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnBtcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcActionPerformed
        if (estadoCandado == 1) {
            actualizarMoneda(1); // El ID 1 representa el botón btnBtc
            guardarMonedasEnJson();
            actualizarBotones();
        }
    }//GEN-LAST:event_btnBtcActionPerformed


    private void btnBtcStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcStateActionPerformed
        if (estadoCandado == 1) {
            actualizarEstadoMoneda(1); // El ID 1 representa el botón btnBtcState
            guardarMonedasEnJson();
            actualizarStateBotones();
        }
    }//GEN-LAST:event_btnBtcStateActionPerformed

    private void btnBtc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtc1ActionPerformed
        if (estadoCandado == 1) {
            actualizarMoneda(2); // El ID 1 representa el botón btnBtc
            guardarMonedasEnJson();
            actualizarBotones();
        }
    }//GEN-LAST:event_btnBtc1ActionPerformed

    private void btnBtcState1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcState1ActionPerformed
        if (estadoCandado == 1) {
            actualizarEstadoMoneda(2); // El ID 1 representa el botón btnBtcState
            guardarMonedasEnJson();
            actualizarStateBotones();
        }
    }//GEN-LAST:event_btnBtcState1ActionPerformed

    private void btnBtc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtc2ActionPerformed
        if (estadoCandado == 1) {
            actualizarMoneda(3); // El ID 1 representa el botón btnBtc
            guardarMonedasEnJson();
            actualizarBotones();
        }
    }//GEN-LAST:event_btnBtc2ActionPerformed

    private void btnBtcState2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcState2ActionPerformed
        if (estadoCandado == 1) {
            actualizarEstadoMoneda(3); // El ID 1 representa el botón btnBtcState
            guardarMonedasEnJson();
            actualizarStateBotones();
        }
    }//GEN-LAST:event_btnBtcState2ActionPerformed

    private void btnBtc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtc3ActionPerformed
        if (estadoCandado == 1) {
            actualizarMoneda(4);
            guardarMonedasEnJson();
            actualizarBotones();
        }
    }//GEN-LAST:event_btnBtc3ActionPerformed

    private void btnBtcState3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcState3ActionPerformed
        if (estadoCandado == 1) {
            actualizarEstadoMoneda(4); // El ID 1 representa el botón btnBtcState
            guardarMonedasEnJson();
            actualizarStateBotones();
        }
    }//GEN-LAST:event_btnBtcState3ActionPerformed

    private void btnBtc4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtc4ActionPerformed
        if (estadoCandado == 1) {
            actualizarMoneda(5);
            guardarMonedasEnJson();
            actualizarBotones();
        }
    }//GEN-LAST:event_btnBtc4ActionPerformed

    private void btnBtcState4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcState4ActionPerformed
        if (estadoCandado == 1) {
            actualizarEstadoMoneda(5); // El ID 1 representa el botón btnBtcState
            guardarMonedasEnJson();
            actualizarStateBotones();
        }
    }//GEN-LAST:event_btnBtcState4ActionPerformed

    private void btnBtc5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtc5ActionPerformed
        if (estadoCandado == 1) {
            actualizarMoneda(6);
            guardarMonedasEnJson();
            actualizarBotones();
        }
    }//GEN-LAST:event_btnBtc5ActionPerformed

    private void btnBtcState5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcState5ActionPerformed
        if (estadoCandado == 1) {
            actualizarEstadoMoneda(6); // El ID 1 representa el botón btnBtcState
            guardarMonedasEnJson();
            actualizarStateBotones();
        }
    }//GEN-LAST:event_btnBtcState5ActionPerformed

    private void btnBtc6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtc6ActionPerformed
        if (estadoCandado == 1) {
            actualizarMoneda(7);
            guardarMonedasEnJson();
            actualizarBotones();
        }
    }//GEN-LAST:event_btnBtc6ActionPerformed

    private void btnBtcState6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcState6ActionPerformed
        if (estadoCandado == 1) {
            actualizarEstadoMoneda(7);
            guardarMonedasEnJson();
            actualizarStateBotones();
        }
    }//GEN-LAST:event_btnBtcState6ActionPerformed

    private void btnBtc7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtc7ActionPerformed
        if (estadoCandado == 1) {
            actualizarMoneda(8);
            guardarMonedasEnJson();
            actualizarBotones();
        }
    }//GEN-LAST:event_btnBtc7ActionPerformed

    private void btnBtcState7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcState7ActionPerformed
        if (estadoCandado == 1) {
            actualizarEstadoMoneda(8);
            guardarMonedasEnJson();
            actualizarStateBotones();
        }
    }//GEN-LAST:event_btnBtcState7ActionPerformed

    private void btnBtc8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtc8ActionPerformed
        if (estadoCandado == 1) {
            actualizarMoneda(9);
            guardarMonedasEnJson();
            actualizarBotones();
        }
    }//GEN-LAST:event_btnBtc8ActionPerformed

    private void btnBtcState8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcState8ActionPerformed
        if (estadoCandado == 1) {
            actualizarEstadoMoneda(9);
            guardarMonedasEnJson();
            actualizarStateBotones();
        }
    }//GEN-LAST:event_btnBtcState8ActionPerformed

    private void btnBtc9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtc9ActionPerformed
        if (estadoCandado == 1) {
            actualizarMoneda(10);
            guardarMonedasEnJson();
            actualizarBotones();
        }
    }//GEN-LAST:event_btnBtc9ActionPerformed

    private void btnBtcState9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcState9ActionPerformed
        if (estadoCandado == 1) {
            actualizarEstadoMoneda(10);
            guardarMonedasEnJson();
            actualizarStateBotones();
        }
    }//GEN-LAST:event_btnBtcState9ActionPerformed

    private void btnBtc10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtc10ActionPerformed
        if (estadoCandado == 1) {
            actualizarMoneda(11);
            guardarMonedasEnJson();
            actualizarBotones();
        }
    }//GEN-LAST:event_btnBtc10ActionPerformed

    private void btnBtcState10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcState10ActionPerformed
        if (estadoCandado == 1) {
            actualizarEstadoMoneda(11);
            guardarMonedasEnJson();
            actualizarStateBotones();
        }
    }//GEN-LAST:event_btnBtcState10ActionPerformed

    private void btnBtc11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtc11ActionPerformed
        if (estadoCandado == 1) {
            actualizarMoneda(12);
            guardarMonedasEnJson();
            actualizarBotones();
        }
    }//GEN-LAST:event_btnBtc11ActionPerformed

    private void btnBtcState11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBtcState11ActionPerformed
        if (estadoCandado == 1) {
            actualizarEstadoMoneda(12);
            guardarMonedasEnJson();
            actualizarStateBotones();
        }
    }//GEN-LAST:event_btnBtcState11ActionPerformed

    
    
    /*=================CANDADO=================*/
    
    private void candadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_candadoActionPerformed
        candado.setVisible(false);
        bloquear.setVisible(true);
        estadoCandado = 1;
        
        //cambio de titulo
        jLabel1.setText("Modifing...");
        jPanel1.setBackground(Color.orange);
        jPanel3.setBackground(Color.orange);
        jPanel4.setBackground(Color.orange);
        jSeparator1.setBackground(Color.orange);
        jSeparator2.setBackground(Color.orange);
    }//GEN-LAST:event_candadoActionPerformed

    private void bloquearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloquearActionPerformed
        bloquear.setVisible(false);
        estadoCandado = 0;
        candado.setVisible(true);
        jLabel1.setText("Positions");
        jPanel1.setBackground(new Color(242, 242, 242));
        jPanel3.setBackground(new Color(242, 242, 242));
        jPanel4.setBackground(new Color(242, 242, 242));
        jSeparator1.setBackground(new Color(242, 242, 242));
        jSeparator2.setBackground(new Color(242, 242, 242));
    }//GEN-LAST:event_bloquearActionPerformed
/*============================================*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bloquear;
    private javax.swing.JButton btnBtc;
    private javax.swing.JButton btnBtc1;
    private javax.swing.JButton btnBtc10;
    private javax.swing.JButton btnBtc11;
    private javax.swing.JButton btnBtc2;
    private javax.swing.JButton btnBtc3;
    private javax.swing.JButton btnBtc4;
    private javax.swing.JButton btnBtc5;
    private javax.swing.JButton btnBtc6;
    private javax.swing.JButton btnBtc7;
    private javax.swing.JButton btnBtc8;
    private javax.swing.JButton btnBtc9;
    private javax.swing.JButton btnBtcState;
    private javax.swing.JButton btnBtcState1;
    private javax.swing.JButton btnBtcState10;
    private javax.swing.JButton btnBtcState11;
    private javax.swing.JButton btnBtcState2;
    private javax.swing.JButton btnBtcState3;
    private javax.swing.JButton btnBtcState4;
    private javax.swing.JButton btnBtcState5;
    private javax.swing.JButton btnBtcState6;
    private javax.swing.JButton btnBtcState7;
    private javax.swing.JButton btnBtcState8;
    private javax.swing.JButton btnBtcState9;
    private javax.swing.JButton candado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

}
