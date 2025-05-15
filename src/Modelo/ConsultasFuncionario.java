package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasFuncionario extends Conexion {

    public boolean registrar(Funcionario f) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO funcionario (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, f.getTipoIdentificacion());
            ps.setString(2, f.getNumeroIdentificacion());
            ps.setString(3, f.getNombres());
            ps.setString(4, f.getApellidos());
            ps.setString(5, f.getEstadoCivil());
            ps.setString(6, f.getSexo());
            ps.setString(7, f.getDireccion());
            ps.setString(8, f.getTelefono());
            ps.setDate(9, f.getFechaNacimiento());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al registrar funcionario: " + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean modificar(Funcionario f) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE funcionario SET tipo_identificacion=?, numero_identificacion=?, nombres=?, apellidos=?, estado_civil=?, sexo=?, direccion=?, telefono=?, fecha_nacimiento=? "
                   + "WHERE id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, f.getTipoIdentificacion());
            ps.setString(2, f.getNumeroIdentificacion());
            ps.setString(3, f.getNombres());
            ps.setString(4, f.getApellidos());
            ps.setString(5, f.getEstadoCivil());
            ps.setString(6, f.getSexo());
            ps.setString(7, f.getDireccion());
            ps.setString(8, f.getTelefono());
            ps.setDate(9, f.getFechaNacimiento());
            ps.setInt(10, f.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al modificar funcionario: " + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminar(Funcionario f) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM funcionario WHERE id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, f.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar funcionario: " + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean buscar(Funcionario f) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM funcionario WHERE numero_identificacion=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, f.getNumeroIdentificacion());
            rs = ps.executeQuery();

            if (rs.next()) {
                f.setId(rs.getInt("id"));
                f.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                f.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                f.setNombres(rs.getString("nombres"));
                f.setApellidos(rs.getString("apellidos"));
                f.setEstadoCivil(rs.getString("estado_civil"));
                f.setSexo(rs.getString("sexo"));
                f.setDireccion(rs.getString("direccion"));
                f.setTelefono(rs.getString("telefono"));
                f.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                return true;
            }

            return false;
        } catch (SQLException e) {
            System.err.println("Error al buscar funcionario: " + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
