package Vista;

import Controlador.ProductoController;
import Controlador.SalidaController;
import Modelo.DetalleSalida;
import Modelo.Producto;
import Modelo.Salida;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VistaSalida extends javax.swing.JFrame {

    // Controladores
    private final ProductoController productoController = new ProductoController();

// Modelo de tabla
    private DefaultTableModel modeloTablaProducto;
    private DefaultTableModel modeloTablaSalida;
    private double subtotal = 0.0;

// ID del producto seleccionado
    private int idProductoSeleccionado = -1;

    public VistaSalida() {
        initComponents();
        modeloTablaProducto = (DefaultTableModel) TablaProducto.getModel();
        modeloTablaSalida = (DefaultTableModel) TablaSalida.getModel();
        cargarTodosLosProductos();

    }

    private void cargarTodosLosProductos() {
        modeloTablaProducto.setRowCount(0);

        List<Producto> productos = productoController.listarProductos(); // Asume que retorna todos
        for (Producto p : productos) {
            modeloTablaProducto.addRow(new Object[]{
                p.getIdProducto(),
                p.getNombre(),
                p.getStock(),
                p.getPrecioVenta()
            });
        }
    }

    private void agregarSalida() {
        try {
            // Validar que los campos estén llenos
            String nombreProducto = txtNombreP.getText().trim();
            String precioStr = txtPrecioVenta.getText().trim();
            String cantidadStr = txtCantidad.getText().trim();
            java.util.Date fechaSalida = jDateChooserFechaSalida.getDate();

            if (nombreProducto.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty() || fechaSalida == null) {
                JOptionPane.showMessageDialog(this, "Completa todos los campos antes de agregar (incluyendo la fecha).");
                return;
            }

            double precio = Double.parseDouble(precioStr);
            int cantidad = Integer.parseInt(cantidadStr);

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero.");
                return;
            }

            double importe = precio * cantidad;

            // Obtener el ID del producto seleccionado en TablaProducto
            int filaSeleccionada = TablaProducto.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un producto de la tabla.");
                return;
            }
            int idProducto = (int) TablaProducto.getValueAt(filaSeleccionada, 0);

            // Formatear la fecha a String (opcional, si la tabla la muestra como texto)
            String fechaFormateada = new java.text.SimpleDateFormat("yyyy-MM-dd").format(fechaSalida);

            // Añadir la fila a TablaSalida
            modeloTablaSalida.addRow(new Object[]{
                idProducto,
                nombreProducto,
                precio,
                cantidad,
                importe,
                fechaFormateada
            });

            // Limpiar campos
            txtNombreP.setText("");
            txtPrecioVenta.setText("");
            txtCantidad.setText("");
            txtStock.setText("");
            jDateChooserFechaSalida.setDate(null); // Limpiar fecha
            TablaProducto.clearSelection();

            // Recalcular totales
            recalcularTotales();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifica que precio y cantidad sean números válidos.");
        }
    }

    private void recalcularTotales() {
        double subTotal = 0;

        for (int i = 0; i < modeloTablaSalida.getRowCount(); i++) {
            double importe = (double) modeloTablaSalida.getValueAt(i, 4);
            subTotal += importe;
        }

        double igv = subTotal * 0.18; // 18%
        double total = subTotal + igv;

        txtSubTotal.setText(String.format("%.2f", subTotal));
        txtIGV.setText(String.format("%.2f", igv));
        txtTotal.setText(String.format("%.2f", total));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnProveedor = new javax.swing.JButton();
        btnEntradas = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProducto = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jDateChooserFechaSalida = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaSalida = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        txtIGV = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnGenerar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(14, 76, 116));

        jButton1.setText("HOME");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("CATEGORIAS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("PRODUCTOS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnProveedor.setText("PROVEEDOR");
        btnProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedorActionPerformed(evt);
            }
        });

        btnEntradas.setText("ENTRADAS");
        btnEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradasActionPerformed(evt);
            }
        });

        jButton6.setText("SALIDAS");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(btnProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEntradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Registro de Salidas");

        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyReleased(evt);
            }
        });

        TablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Stock", "P.Venta"
            }
        ));
        TablaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaProducto);

        jLabel2.setText("Stock");

        jLabel3.setText("Cantidad");

        jLabel4.setText("Precio");

        jLabel5.setText("Fecha Salida");

        jLabel6.setText("Nombre");

        jLabel7.setText("Numero Salida");

        jLabel8.setText("---");

        TablaSalida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Precio U.", "Cantidad", "Importe", "Fecha salida"
            }
        ));
        jScrollPane2.setViewportView(TablaSalida);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jButton5.setText("Eliminar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel9.setText("Sub Total: ");

        jLabel10.setText("IGV: ");

        jLabel11.setText("Total:");

        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 559, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel8)
                        .addGap(44, 44, 44))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(167, 167, 167)
                                        .addComponent(jButton5)
                                        .addGap(13, 13, 13)
                                        .addComponent(btnAgregar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(138, 138, 138)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel11))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtTotal)
                                                    .addComponent(txtIGV)
                                                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel5))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(28, 28, 28)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(txtCantidad)
                                                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jDateChooserFechaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                                            .addComponent(txtPrecioVenta)))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGap(26, 26, 26)
                                                        .addComponent(txtNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(228, 228, 228)
                                        .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jDateChooserFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar)
                            .addComponent(jButton5))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        VistaCategorias vista = new VistaCategorias();
        vista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        VistaProveedor vista = new VistaProveedor();
        vista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void btnEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradasActionPerformed
        VistaEntradas vista = new VistaEntradas();
        vista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEntradasActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNombreProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyReleased
        String texto = txtNombreProducto.getText().trim().toLowerCase();
        modeloTablaProducto.setRowCount(0); // Limpiar la tabla

        List<Producto> productos = productoController.listarProductos(); // Asume que devuelve todos los productos

        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(texto)) {
                modeloTablaProducto.addRow(new Object[]{
                    p.getIdProducto(),
                    p.getNombre(),
                    p.getStock(),
                    p.getPrecioVenta()
                });
            }
        }
    }//GEN-LAST:event_txtNombreProductoKeyReleased

    private void TablaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProductoMouseClicked
        int fila = TablaProducto.getSelectedRow();
        if (fila >= 0) {
            // Obtener los datos de la fila seleccionada
            int idProducto = Integer.parseInt(TablaProducto.getValueAt(fila, 0).toString());
            String nombre = TablaProducto.getValueAt(fila, 1).toString();
            int stock = Integer.parseInt(TablaProducto.getValueAt(fila, 2).toString());
            double precioVenta = Double.parseDouble(TablaProducto.getValueAt(fila, 3).toString());

            // Llenar los campos
            txtNombreP.setText(nombre);
            txtStock.setText(String.valueOf(stock));
            txtPrecioVenta.setText(String.valueOf(precioVenta));

            // Guardar ID del producto para usarlo al momento de registrar la salida
            idProductoSeleccionado = idProducto;
        }
    }//GEN-LAST:event_TablaProductoMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarSalida();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int filaSeleccionada = TablaSalida.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla de salidas para eliminar.");
            return;
        }

        // Confirmación
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar esta salida?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        modeloTablaSalida.removeRow(filaSeleccionada);
        recalcularTotales();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        if (modeloTablaSalida.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay productos en la salida.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Deseas generar esta salida?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

// Obtener totales (reemplazar coma por punto para evitar error)
        double subtotal, igv, total;
        try {
            subtotal = Double.parseDouble(txtSubTotal.getText().replace(",", "."));
            igv = Double.parseDouble(txtIGV.getText().replace(",", "."));
            total = Double.parseDouble(txtTotal.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al leer los montos. Asegúrate de que tengan el formato correcto.");
            return;
        }

// Obtener la fecha desde la primera fila (columna 5)
        Date fechaUnica = null;
        try {
            String fechaStr = (String) modeloTablaSalida.getValueAt(0, 5); // Asegúrate de que esta columna sea String
            fechaUnica = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error con la fecha de salida en la primera fila.");
            return;
        }

// Crear objeto Salida (cabecera)
        Salida salida = new Salida();
        salida.setFecha(fechaUnica);
        salida.setSubtotal(subtotal);
        salida.setIgv(igv);
        salida.setTotal(total);
        salida.setIdUsuario(1); // Ajustar según el usuario logueado

// Crear lista de detalles
        List<DetalleSalida> listaDetalle = new ArrayList<>();

        for (int i = 0; i < modeloTablaSalida.getRowCount(); i++) {
            int idProducto = (int) modeloTablaSalida.getValueAt(i, 0);
            double precioVenta = (double) modeloTablaSalida.getValueAt(i, 2);
            int cantidad = (int) modeloTablaSalida.getValueAt(i, 3);
            double importe = (double) modeloTablaSalida.getValueAt(i, 4);

            DetalleSalida detalle = new DetalleSalida();
            detalle.setIdProducto(idProducto);
            detalle.setPrecioVenta(precioVenta);
            detalle.setCantidad(cantidad);
            detalle.setImporte(importe);

            listaDetalle.add(detalle);
        }

// Registrar salida y detalles
        SalidaController salidaController = new SalidaController();
        boolean exito = salidaController.registrarSalida(salida, listaDetalle);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Salida registrada correctamente.");
            modeloTablaSalida.setRowCount(0);
            txtSubTotal.setText("");
            txtIGV.setText("");
            txtTotal.setText("");
            jDateChooserFechaSalida.setDate(null);
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al registrar la salida.");
        }

    }//GEN-LAST:event_btnGenerarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaSalida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaProducto;
    private javax.swing.JTable TablaSalida;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEntradas;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooserFechaSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtNombreP;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
