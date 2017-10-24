package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import util.BatEncriptador;

/**
 * Usuario entity. @author MyEclipse Persistence Tools
 */

public class Usuario implements java.io.Serializable {

	// Fields
	private int idUsuario;
	private Perfil perfil;
	private String usuario;
	private String password;
	private String dni;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombres;
	private String telefonos;
	private String estado;
	private Date fechaReg;
	private Date fechaMod;
	private int usuarioMod;
	private int usuarioReg;

	// Constructors

	/** default constructor */
	public Usuario() {
	}

	/** minimal constructor */
	public Usuario(Perfil perfil, String usuario, String password, String dni,
			String apellidoPaterno, String estado) {
		this.perfil = perfil;
		this.usuario = usuario;
		this.password = password;
		this.dni = dni;
		this.apellidoPaterno = apellidoPaterno;
		this.estado = estado;
	}

	/** full constructor */
	public Usuario(Perfil perfil, String usuario, String password, String dni,
			String apellidoPaterno, String apellidoMaterno, String nombres,
			String telefonos, String estado, Date fechaReg, Date fechaMod,
			Integer usuarioMod, Integer usuarioReg) {
		this.perfil = perfil;
		this.usuario = usuario;
		this.password = password;
		this.dni = dni;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.nombres = nombres;
		this.telefonos = telefonos;
		this.estado = estado;
		this.fechaReg = fechaReg;
		this.fechaMod = fechaMod;
		this.usuarioMod = usuarioMod;
		this.usuarioReg = usuarioReg;
	}

	// Property accessors

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefonos() {
		return this.telefonos;
	}

	public void setTelefonos(String telefonos) {
		this.telefonos = telefonos;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public int getUsuarioMod() {
		return this.usuarioMod;
	}

	public void setUsuarioMod(int usuarioMod) {
		this.usuarioMod = usuarioMod;
	}

	public int getUsuarioReg() {
		return this.usuarioReg;
	}

	public void setUsuarioReg(int usuarioReg) {
		this.usuarioReg = usuarioReg;
	}
    public Usuario loadRs(ResultSet rs) throws SQLException {

        Usuario temp = new Usuario();
        Perfil perfilTemp=new Perfil();
        temp.setIdUsuario(rs.getInt("id_usuario"));
        temp.setApellidoMaterno(rs.getString("apellido_materno"));
        temp.setApellidoPaterno(rs.getString("apellido_paterno"));
        temp.setDni(rs.getString("dni"));
        temp.setEstado(rs.getString("estado"));
        temp.setFechaMod(rs.getDate("fecha_mod"));
        temp.setFechaReg(rs.getDate("fecha_reg"));
        temp.setNombres(rs.getString("nombres"));
        temp.setPassword(BatEncriptador.getInstance().Desencripta(rs.getString("password")));
        perfilTemp.setIdperfil(rs.getInt("perfil_idperfil"));
        perfilTemp.setCodigo(rs.getString("codigo"));
        perfilTemp.setDescripcion(rs.getString("descripcion"));
        perfilTemp.setTipo(rs.getString("tipo"));
        temp.setPerfil(perfilTemp);
        temp.setTelefonos(rs.getString("telefono"));
        temp.setUsuario(rs.getString("usuario"));
        temp.setUsuarioMod(rs.getInt("usuario_mod"));
        temp.setUsuarioReg(rs.getInt("usuario_reg"));
 return temp;
    }
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", perfil=" + perfil + ", usuario=" + usuario + ", password=" + password + ", dni=" + dni + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", nombres=" + nombres + ", telefonos=" + telefonos + ", estado=" + estado + ", fechaReg=" + fechaReg + ", fechaMod=" + fechaMod + ", usuarioMod=" + usuarioMod + ", usuarioReg=" + usuarioReg + '}';
    }

}