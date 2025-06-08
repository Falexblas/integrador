package Vista;

import Controlador.CategoriaControlador;
import Controlador.EntradaControlador;
import Controlador.ProductoController;
import Controlador.ProveedorControlador;
import Modelo.Categoria;
import Modelo.Entrada;
import Modelo.Producto;
import Modelo.Proveedor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VistaEntradas extends javax.swing.JFrame {

// Controladores
    private final ProductoController productoController = new ProductoController();
    private final ProveedorControlador proveedorController = new ProveedorControlador();
    private final EntradaControlador entradaController = new EntradaControlador();
    private final CategoriaControlador categoriaController = new CategoriaControlador();

// IDs seleccionados
    private int idProductoSeleccionado = -1;
    private int idProveedorSeleccionado = -1;
    private int idEntradaSeleccionado = -1;

    public VistaEntradas() {
        initComponents();
        cargarProductos();
        cargarProveedores();
        cargarEntradas();
    }

    private void calcularPrecioVenta() {
        try {
            double precioCompra = Double.parseDouble(txtPrecioEntrada.getText());
            double margen = Double.parseDouble(txtMargen.getText());
            double precioVenta = precioCompra + (precioCompra * (margen / 100));
            txtPrecioVenta.setText(String.valueOf(precioVenta));  // Evita errores de coma/punto
        } catch (NumberFormatException e) {
            txtPrecioVenta.setText("");
        }
    }

    private void cargarProductos() {
        DefaultTableModel modelo = (DefaultTableModel) TablaProducto.getModel();
        modelo.setRowCount(0);

        List<Producto> productos = productoController.listarProductos();
        for (Producto p : productos) {
            String nombreCategoria = "";
            if (p.getIdCategoria() != 0) {
                nombreCategoria = categoriaController.getCategoriaById(p.getIdCategoria()).getNombre();
            }
            modelo.addRow(new Object[]{
                p.getIdProducto(),
                p.getNombre(),
                nombreCategoria
            });
        }
    }

    private void cargarProveedores() {
        DefaultTableModel modelo = (DefaultTableModel) TablaProveedor.getModel();
        modelo.setRowCount(0); // limpiar tabla

        List<Proveedor> proveedores = proveedorController.listarProveedores();
        for (Proveedor pr : proveedores) {
            modelo.addRow(new Object[]{
                pr.getId_proveedor(),
                pr.getNombre(),
                pr.getApellido(),
                pr.getDocumento(),
                pr.getRazon_social(),
                pr.getDireccion(),
                pr.getTelefono()
            });
        }
    }

    private void cargarEntradas() {
        DefaultTableModel modelo = (DefaultTableModel) TablaEntrada.getModel();
        modelo.setRowCount(0);

        for (Entrada e : entradaController.listarEntradas()) {
            Producto p = productoController.obtenerProductoPorId(e.getIdProducto());
            String nombreProducto;
            if (p == null) {
                nombreProducto = "Producto no encontrado";
            } else {
                nombreProducto = p.getNombre();
            }

            String nombreProveedor = proveedorController.obtenerPorId(e.getIdProveedor()).getNombre();

            modelo.addRow(new Object[]{
                e.getIdEntrada(),
                nombreProducto,
                e.getCantidad(),
                e.getFechaIngreso(),
                e.getFechaVencimiento(),
                nombreProveedor,
                e.getPrecioCompra(),
                e.getMargen(),
                e.getPrecioVenta()
            });
        }
    }

    private void limpiarCampos() {
        txtNombreProducto.setText("");
        txtNombreProveedor.setText("");
        txtStock.setText("");
        txtPrecioEntrada.setText("");
        txtMargen.setText("");
        txtPrecioVenta.setText("");
        jDateChooserFecha_Entrada.setDate(null);
        jDateChooserFecha_Vencimiento.setDate(null);
        idProductoSeleccionado = -1;
        idProveedorSeleccionado = -1;
        idEntradaSeleccionado = -1;
    }

    private int buscarIdProductoPorNombre(String nombre) {
        for (int i = 0; i < TablaProducto.getRowCount(); i++) {
            String nombreTabla = TablaProducto.getValueAt(i, 1).toString();
            if (nombreTabla.equalsIgnoreCase(nombre)) {
                return Integer.parseInt(TablaProducto.getValueAt(i, 0).toString());
            }
        }
        return -1; // no encontrado
    }

    private int buscarIdProveedorPorNombre(String nombre) {
        for (int i = 0; i < TablaProveedor.getRowCount(); i++) {
            String nombreTabla = TablaProveedor.getValueAt(i, 1).toString(); // o la columna donde está el nombre proveedor
            if (nombreTabla.equalsIgnoreCase(nombre)) {
                return Integer.parseInt(TablaProveedor.getValueAt(i, 0).toString());
            }
        }
        return -1; // no encontrado
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnVistaCategorias = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jDateChooserFecha_Entrada = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jDateChooserFecha_Vencimiento = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioEntrada = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMargen = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaEntrada = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaProducto = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaProveedor = new javax.swing.JTable();
        txtNombreProveedor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(14, 76, 116));

        jButton1.setText("HOME");

        btnVistaCategorias.setText("CATEGORIAS");
        btnVistaCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVistaCategoriasActionPerformed(evt);
            }
        });

        jButton3.setText("PRODUCTOS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("PROVEEDOR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("ENTRADAS");

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
                .addGap(89, 89, 89)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(btnVistaCategorias)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jButton1)
                .addGap(61, 61, 61)
                .addComponent(btnVistaCategorias)
                .addGap(61, 61, 61)
                .addComponent(jButton3)
                .addGap(62, 62, 62)
                .addComponent(jButton4)
                .addGap(67, 67, 67)
                .addComponent(jButton5)
                .addGap(50, 50, 50)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Registro de entradas");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Stock");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Fecha entrada");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Fecha de vencimiento(opcional)");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Precio de entrada");

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Precio venta");

        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyReleased(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Margen(%)");

        txtMargen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMargenKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooserFecha_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtMargen, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPrecioVenta, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPrecioEntrada, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDateChooserFecha_Vencimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jDateChooserFecha_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jDateChooserFecha_Vencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtPrecioEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMargen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        TablaEntrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Stock", "F.Entrada", "F.Vencimiento", "Proveedor", "P.Entrada", "Margen", "P.Venta"
            }
        ));
        TablaEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaEntradaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaEntrada);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProductoActionPerformed(evt);
            }
        });
        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyReleased(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nombre ");

        TablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Categoria"
            }
        ));
        TablaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProductoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaProducto);

        jLabel9.setText("Proveedor");

        TablaProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellido", "Documento", "R. Social", "Direccion", "Telefono"
            }
        ));
        TablaProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProveedorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablaProveedor);

        txtNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(208, 208, 208)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1043, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProductoActionPerformed

    }//GEN-LAST:event_txtNombreProductoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String stockStr = txtStock.getText().trim();
        String precioEntradaStr = txtPrecioEntrada.getText().trim();
        String precioVentaStr = txtPrecioVenta.getText().trim();
        String margenStr = txtMargen.getText().trim();

        if (stockStr.isEmpty() || precioEntradaStr.isEmpty() || precioVentaStr.isEmpty() || margenStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos numéricos.");
            return;
        }

        try {
            int stock = Integer.parseInt(stockStr);
            double precioEntrada = Double.parseDouble(precioEntradaStr);
            double precioVenta = Double.parseDouble(precioVentaStr);
            double margen = Double.parseDouble(margenStr);

            // Crear objeto entrada
            Entrada entrada = new Entrada();
            entrada.setIdProducto(idProductoSeleccionado);
            entrada.setIdProveedor(idProveedorSeleccionado);
            entrada.setCantidad(stock);
            entrada.setPrecioCompra(precioEntrada);
            entrada.setPrecioVenta(precioVenta);
            entrada.setMargen(margen);
            entrada.setFechaIngreso(jDateChooserFecha_Entrada.getDate());
            entrada.setFechaVencimiento(jDateChooserFecha_Vencimiento.getDate());
            entrada.setIdUsuario(1);

            if (entradaController.registrarEntrada(entrada)) {
                JOptionPane.showMessageDialog(this, "Entrada registrada.");
                limpiarCampos();
                cargarEntradas();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar entrada.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifica los campos numéricos (stock, precio, margen).");
        }


    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (idEntradaSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una entrada para editar.");
            return;
        }

        String stockStr = txtStock.getText().trim();
        String precioEntradaStr = txtPrecioEntrada.getText().trim();
        String precioVentaStr = txtPrecioVenta.getText().trim();

        if (stockStr.isEmpty() || precioEntradaStr.isEmpty() || precioVentaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos numéricos.");
            return;
        }

        try {
            int stockNuevo = Integer.parseInt(stockStr);
            double precioEntrada = Double.parseDouble(precioEntradaStr);
            double precioVenta = Double.parseDouble(precioVentaStr);
            double margen = (precioEntrada > 0) ? ((precioVenta - precioEntrada) / precioEntrada) * 100 : 0.0;

            // 🔹 Obtener el stock anterior de esa entrada
            Entrada entradaAnterior = entradaController.obtenerEntradaPorId(idEntradaSeleccionado);
            int stockAnterior = entradaAnterior.getCantidad();

            // 🔹 Calcular la diferencia
            int diferenciaStock = stockNuevo - stockAnterior;

            // 🔹 Crear objeto actualizado
            Entrada entrada = new Entrada();
            entrada.setIdEntrada(idEntradaSeleccionado);
            entrada.setIdProducto(idProductoSeleccionado);
            entrada.setIdProveedor(idProveedorSeleccionado);
            entrada.setCantidad(stockNuevo);
            entrada.setPrecioCompra(precioEntrada);
            entrada.setPrecioVenta(precioVenta);
            entrada.setMargen(margen);
            entrada.setFechaIngreso(jDateChooserFecha_Entrada.getDate());
            entrada.setFechaVencimiento(jDateChooserFecha_Vencimiento.getDate());
            entrada.setIdUsuario(1); // Ajusta según el usuario actual

            // 🔹 Actualizar entrada
            if (entradaController.actualizarEntrada(entrada)) {

                // 🔹 Actualizar stock del producto
                productoController.actualizarStockProducto(idProductoSeleccionado, diferenciaStock);

                JOptionPane.showMessageDialog(this, "Entrada y stock actualizados correctamente.");
                limpiarCampos();
                cargarEntradas();
                idEntradaSeleccionado = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la entrada.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifica los campos numéricos.");
        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (idEntradaSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una entrada para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar esta entrada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            // 🔹 Obtener la entrada antes de eliminarla (para conocer cantidad y producto)
            Entrada entrada = entradaController.obtenerEntradaPorId(idEntradaSeleccionado);
            int cantidadEliminar = entrada.getCantidad();
            int idProducto = entrada.getIdProducto();

            // 🔹 Eliminar entrada
            if (entradaController.eliminarEntrada(idEntradaSeleccionado)) {

                // 🔹 Actualizar el stock restando la cantidad de la entrada eliminada
                productoController.actualizarStockProducto(idProducto, -cantidadEliminar);

                JOptionPane.showMessageDialog(this, "Entrada eliminada correctamente.");
                limpiarCampos();
                cargarEntradas();
                idEntradaSeleccionado = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la entrada.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al eliminar la entrada: " + e.getMessage());
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNombreProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyReleased
        String texto = txtNombreProducto.getText().trim().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) TablaProducto.getModel();
        modelo.setRowCount(0);

        for (Producto p : productoController.listarProductos()) {
            if (p.getNombre().toLowerCase().contains(texto)) {
                String categoria = categoriaController.listarCategorias().stream()
                        .filter(c -> c.getId_categoria() == p.getIdCategoria())
                        .findFirst().map(Categoria::getNombre).orElse("Desconocida");

                modelo.addRow(new Object[]{p.getIdProducto(), p.getNombre(), categoria});
            }
        }
    }//GEN-LAST:event_txtNombreProductoKeyReleased

    private void txtNombreProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedorKeyReleased
        String texto = txtNombreProveedor.getText().trim().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) TablaProveedor.getModel();
        modelo.setRowCount(0);

        for (Proveedor p : proveedorController.listarProveedores()) {
            String fullName = (p.getNombre() + " " + p.getApellido()).toLowerCase();
            if (fullName.contains(texto)) {
                modelo.addRow(new Object[]{
                    p.getId_proveedor(),
                    p.getNombre(),
                    p.getApellido(),
                    p.getDocumento(),
                    p.getRazon_social(),
                    p.getDireccion(),
                    p.getTelefono()
                });
            }
        }
    }//GEN-LAST:event_txtNombreProveedorKeyReleased

    private void txtMargenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMargenKeyReleased
        calcularPrecioVenta();
    }//GEN-LAST:event_txtMargenKeyReleased

    private void txtPrecioVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyReleased
        calcularPrecioVenta();
    }//GEN-LAST:event_txtPrecioVentaKeyReleased

    private void TablaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProductoMouseClicked
        int fila = TablaProducto.getSelectedRow();
        if (fila != -1) {
            idProductoSeleccionado = (int) TablaProducto.getValueAt(fila, 0);
            txtNombreProducto.setText((String) TablaProducto.getValueAt(fila, 1));
        }
    }//GEN-LAST:event_TablaProductoMouseClicked

    private void TablaProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProveedorMouseClicked
        int fila = TablaProveedor.getSelectedRow();
        if (fila != -1) {
            idProveedorSeleccionado = (int) TablaProveedor.getValueAt(fila, 0);
            txtNombreProveedor.setText(
                    TablaProveedor.getValueAt(fila, 1) + " " + TablaProveedor.getValueAt(fila, 2)
            );
        }
    }//GEN-LAST:event_TablaProveedorMouseClicked

    private void btnVistaCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVistaCategoriasActionPerformed
        VistaCategorias vista = new VistaCategorias();
        vista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVistaCategoriasActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        VistaProductos vista = new VistaProductos();
        vista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        VistaProveedor vista = new VistaProveedor();
        vista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void TablaEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaEntradaMouseClicked
        int fila = TablaEntrada.getSelectedRow();
        if (fila != -1) {
            // ID entrada (asumiendo que está en la columna 0)
            idEntradaSeleccionado = Integer.parseInt(TablaEntrada.getValueAt(fila, 0).toString());

            // Nombre Producto (columna 1)
            String nombreProducto = TablaEntrada.getValueAt(fila, 1).toString();
            txtNombreProducto.setText(nombreProducto);

            // Obtener idProducto desde TablaProducto buscando por nombre
            idProductoSeleccionado = obtenerIdProductoPorNombre(nombreProducto);

            // Stock (columna 2)
            txtStock.setText(TablaEntrada.getValueAt(fila, 2).toString());

            // Fecha entrada (columna 3)
            Object fechaEntradaObj = TablaEntrada.getValueAt(fila, 3);
            setFechaEnDateChooser(fechaEntradaObj, jDateChooserFecha_Entrada);

            // Fecha vencimiento (columna 4)
            Object fechaVencimientoObj = TablaEntrada.getValueAt(fila, 4);
            setFechaEnDateChooser(fechaVencimientoObj, jDateChooserFecha_Vencimiento);

            // Nombre Proveedor (columna 5)
            String nombreProveedor = TablaEntrada.getValueAt(fila, 5).toString();
            txtNombreProveedor.setText(nombreProveedor);

            // Obtener idProveedor desde TablaProveedor buscando por nombre
            idProveedorSeleccionado = obtenerIdProveedorPorNombre(nombreProveedor);

            // Precio entrada (columna 6)
            txtPrecioEntrada.setText(TablaEntrada.getValueAt(fila, 6).toString());

            // Margen (columna 7)
            txtMargen.setText(TablaEntrada.getValueAt(fila, 7).toString());

            // Precio venta (columna 8)
            txtPrecioVenta.setText(TablaEntrada.getValueAt(fila, 8).toString());
        }
    }

// Métodos auxiliares que puedes poner en la misma clase (antes o después)
    private void setFechaEnDateChooser(Object fechaObj, com.toedter.calendar.JDateChooser dateChooser) {
        if (fechaObj instanceof Date) {
            dateChooser.setDate((Date) fechaObj);
        } else if (fechaObj instanceof String) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dateChooser.setDate(sdf.parse((String) fechaObj));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    private int obtenerIdProductoPorNombre(String nombreProducto) {
        for (int i = 0; i < TablaProducto.getRowCount(); i++) {
            if (TablaProducto.getValueAt(i, 1).toString().equalsIgnoreCase(nombreProducto)) {
                return Integer.parseInt(TablaProducto.getValueAt(i, 0).toString());
            }
        }
        return -1;
    }

    private int obtenerIdProveedorPorNombre(String nombreProveedor) {
        for (int i = 0; i < TablaProveedor.getRowCount(); i++) {
            if (TablaProveedor.getValueAt(i, 1).toString().equalsIgnoreCase(nombreProveedor)) {
                return Integer.parseInt(TablaProveedor.getValueAt(i, 0).toString());
            }
        }
        return -1;

    }//GEN-LAST:event_TablaEntradaMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        VistaSalida vista = new VistaSalida();
        vista.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(VistaEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaEntradas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaEntrada;
    private javax.swing.JTable TablaProducto;
    private javax.swing.JTable TablaProveedor;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVistaCategorias;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooserFecha_Entrada;
    private com.toedter.calendar.JDateChooser jDateChooserFecha_Vencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtMargen;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtPrecioEntrada;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
