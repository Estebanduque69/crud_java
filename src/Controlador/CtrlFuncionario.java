package Controlador;


import Modelo.ConsultasFuncionario;
import Modelo.Funcionario;
import Vista.frmFuncionario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;


public class CtrlFuncionario implements ActionListener {

    private final Funcionario modelo;
    private final ConsultasFuncionario consultas;
    private final frmFuncionario vista;

    public CtrlFuncionario(Funcionario modelo, ConsultasFuncionario consultas, frmFuncionario vista) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Gestión de Funcionario");
        vista.setLocationRelativeTo(null);
        vista.txtId.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == vista.btnGuardar) {
                cargarDatosDesdeVista();
                if (consultas.registrar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                }
            }

            if (e.getSource() == vista.btnModificar) {
                modelo.setId(Integer.parseInt(vista.txtId.getText()));
                cargarDatosDesdeVista();
                if (consultas.modificar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Modificar");
                }
            }

            if (e.getSource() == vista.btnEliminar) {
                modelo.setId(Integer.parseInt(vista.txtId.getText()));
                if (consultas.eliminar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar");
                }
            }

            if (e.getSource() == vista.btnBuscar) {
                modelo.setNumeroIdentificacion(vista.txtNumeroIdentificacion.getText());
                if (consultas.buscar(modelo)) {
                    mostrarDatosEnVista();
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró registro");
                    limpiar();
                }
            }

            if (e.getSource() == vista.btnLimpiar) {
                limpiar();
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "ID inválido");
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, "Seleccione una fecha válida");
        }
    }

    private void cargarDatosDesdeVista() {
        modelo.setTipoIdentificacion(vista.txtTipoIdentificacion.getText());
        modelo.setNumeroIdentificacion(vista.txtNumeroIdentificacion.getText());
        modelo.setNombres(vista.txtNombres.getText());
        modelo.setApellidos(vista.txtApellidos.getText());
        modelo.setEstadoCivil(vista.txtEstadoCivil.getText());
        modelo.setSexo(vista.txtSexo.getText());
        modelo.setDireccion(vista.txtDireccion.getText());
        modelo.setTelefono(vista.txtTelefono.getText());

        // Convertir la fecha seleccionada en el JDateChooser a java.sql.Date
        /*java.util.Date fecha = vista.txtFechaNacimiento.getDate();
        if (fecha != null) {
            modelo.setFechaNacimiento(new Date(fecha.getTime()));
        } else {
            throw new NullPointerException("Fecha no seleccionada");
        }*/
    }

    private void mostrarDatosEnVista() {
        vista.txtId.setText(String.valueOf(modelo.getId()));
        vista.txtTipoIdentificacion.setText(modelo.getTipoIdentificacion());
        vista.txtNumeroIdentificacion.setText(modelo.getNumeroIdentificacion());
        vista.txtNombres.setText(modelo.getNombres());
        vista.txtApellidos.setText(modelo.getApellidos());
        vista.txtEstadoCivil.setText(modelo.getEstadoCivil());
        vista.txtSexo.setText(modelo.getSexo());
        vista.txtDireccion.setText(modelo.getDireccion());
        vista.txtTelefono.setText(modelo.getTelefono());
       // vista.txtFechaNacimiento.setDate(modelo.getFechaNacimiento());
    }

    public void limpiar() {
        vista.txtId.setText(null);
        vista.txtTipoIdentificacion.setText(null);
        vista.txtNumeroIdentificacion.setText(null);
        vista.txtNombres.setText(null);
        vista.txtApellidos.setText(null);
        vista.txtEstadoCivil.setText(null);
        vista.txtSexo.setText(null);
        vista.txtDireccion.setText(null);
        vista.txtTelefono.setText(null);
       // vista.txtFechaNacimiento.setDate(null);
    }
} 