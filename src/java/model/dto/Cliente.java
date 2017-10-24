package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Cliente entity. @author MyEclipse Persistence Tools
 */

public class Cliente implements java.io.Serializable {

	// Fields

	private int idCliente;
	private String naturalezaCliente;
	private int idTipoCliente;
	private String dniRuc;
	private String razonSocial;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String telefono;
	private String direccion;
	private String email;
	private int usuarioReg;
	private int usuarioMod;
	private Date fechaReg;
	private Date fechaMod;

	// Constructors

	/** default constructor */
	public Cliente() {
	}

	/** minimal constructor */
	public Cliente(String naturalezaCliente, int idTipoCliente) {
		this.naturalezaCliente = naturalezaCliente;
		this.idTipoCliente = idTipoCliente;
	}

	/** full constructor */
	public Cliente(String naturalezaCliente, int idTipoCliente,
			String dniRuc, String razonSocial, String nombres,
			String apellidoPaterno, String apellidoMaterno, String telefono,
			String direccion, String email, int usuarioReg,
			int usuarioMod, Date fechaReg, Date fechaMod) {
		this.naturalezaCliente = naturalezaCliente;
		this.idTipoCliente = idTipoCliente;
		this.dniRuc = dniRuc;
		this.razonSocial = razonSocial;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.telefono = telefono;
		this.direccion = direccion;
		this.email = email;
		this.usuarioReg = usuarioReg;
		this.usuarioMod = usuarioMod;
		this.fechaReg = fechaReg;
		this.fechaMod = fechaMod;
	}

	// Property accessors

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNaturalezaCliente() {
		return this.naturalezaCliente;
	}

	public void setNaturalezaCliente(String naturalezaCliente) {
		this.naturalezaCliente = naturalezaCliente;
	}

	public int getIdTipoCliente() {
		return this.idTipoCliente;
	}

	public void setIdTipoCliente(int idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public String getDniRuc() {
		return this.dniRuc;
	}

	public void setDniRuc(String dniRuc) {
		this.dniRuc = dniRuc;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUsuarioReg() {
		return this.usuarioReg;
	}

	public void setUsuarioReg(int usuarioReg) {
		this.usuarioReg = usuarioReg;
	}

	public int getUsuarioMod() {
		return this.usuarioMod;
	}

	public void setUsuarioMod(Integer usuarioMod) {
		this.usuarioMod = usuarioMod;
	}

	public Date getFechaReg() {
		return this.fechaReg;
	}

	public void setFechaReg(Date fechaReg) {
		this.fechaReg = fechaReg;
	}

	public Date getFechaMod() {
		return this.fechaMod;
	}

	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}
public Cliente loadRs (ResultSet rs) throws SQLException {
  Cliente temp=new Cliente();
  temp.setApellidoMaterno(rs.getString("apellido_materno"));
  temp.setApellidoPaterno(rs.getString("apellido_paterno"));
  temp.setDireccion(rs.getString("direccion"));
  temp.setEmail(rs.getString("email"));
  temp.setFechaMod(rs.getDate("fecha_mod"));
  temp.setFechaReg(rs.getDate("fecha_reg"));
  temp.setIdCliente(rs.getInt("id_cliente"));
  temp.setIdTipoCliente(rs.getInt("id_tipo_cliente"));
  temp.setNaturalezaCliente(rs.getString("naturaleza_cliente"));
  temp.setNombres(rs.getString("nombres"));
  temp.setRazonSocial(rs.getString("razon_social"));
  temp.setTelefono(rs.getString("telefono"));
  temp.setUsuarioMod(rs.getInt("usuario_mod"));
  temp.setUsuarioReg(rs.getInt("usuario_reg"));
  temp.setDniRuc(rs.getString("dni_ruc"));
return temp;
}
    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", naturalezaCliente=" + naturalezaCliente + ", idTipoCliente=" + idTipoCliente + ", dniRuc=" + dniRuc + ", razonSocial=" + razonSocial + ", nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", telefono=" + telefono + ", direccion=" + direccion + ", email=" + email + ", usuarioReg=" + usuarioReg + ", usuarioMod=" + usuarioMod + ", fechaReg=" + fechaReg + ", fechaMod=" + fechaMod + '}';
    }
       
}