<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contexto = request.getContextPath();
%>
<script>
    function validar() {
        var evento = document.getElementById('hdEvento').value;
        var validar = true;
        if (evento != '') {
            var usu = document.getElementById('txtusuario').value;
            var apepat = document.getElementById('txtapellidopat').value;
            var apemat = document.getElementById('txtapellidomat').value;
            var dni = document.getElementById('txtdni').value;
            var pass = document.getElementById('txtpassword').value;
            var tel = document.getElementById('txttelefono').value;
            var per = document.getElementById('cbxperfil').value;
            var estado = document.getElementById('cbxestado').value;
            var nombre = document.getElementById('txtnombre').value;
            $('#lblnombre').css("color", "black");
            $('#lblapepat').css("color", "black");
            $('#lblapemat').css("color", "black");
            $('#lblnombre').css("color", "black");
            $('#lbldni').css("color", "black");
            $('#lblestado').css("color", "black");
            $('#lblusu').css("color", "black");
            $('#lblpassword').css("color", "black");
            $('#lblperfil').css("color", "black");
            $('#lbltel').css("color", "black");
            if (evento == 'registrarUsuario') {
                if (usu == '') {
                    $('#lblusu').css("color", "red");
                    validar = false;
                }
                if (apepat == '') {
                    $('#lblapepat').css("color", "red");
                    validar = false;
                }
                if (apemat == '') {
                    $('#lblapemat').css("color", "red");
                    validar = false;
                }
                if (dni == '') {
                    $('#lbldni').css("color", "red");
                    validar = false;
                }
                if (pass == '') {
                    $('#lblpassword').css("color", "red");
                    validar = false;
                }
                if (tel == '') {
                    $('#lbltel').css("color", "red");
                    validar = false;
                }
                if (per == '') {
                    $('#lblperfil').css("color", "red");
                    validar = false;
                }
                if (estado == '') {
                    $('#lblestado').css("color", "red");
                    validar = false;
                }
                if (nombre == '') {
                    $('#lblnombre').css("color", "red");
                    validar = false;
                }
            } else if (evento == '') {
                //alert('REGISTRAR UN NUEVO USUARIO')

            }
        }
        return validar;
    }
    function perfiltext(valor) {

        var t = document.getElementById("cbxperfil");
        var selectedText = t.options[valor].text;
        return selectedText;
    }
</script>


<div class="page-header">
    <input value="<%=contexto%>" type="hidden" id="contexto">
    <h1>
        USUARIOS
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Registro de Usuarios
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form  id="form1" action="ServUsuario" method="POST" class="form-horizontal" role="form">
            <input name="evento" type="hidden" value="registrarUsuario" id="hdEvento">
            <input name="idusuario" type="hidden" value="${objusu.idUsuario}">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblnombre"> Nombres</label>
                <div class="col-sm-9">
                    <input type="text" id="txtnombre" name="nombres" placeholder="nombre" class="col-xs-10 col-sm-5" value="${objusu.nombres}"required />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblapepat"> Apellido Paterno </label>
                <div class="col-sm-9">
                    <input type="text" id="txtapellidopat" name="paterno" placeholder="Apellido Paterno" class="col-xs-10 col-sm-5" value="${objusu.apellidoPaterno}" required/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblapemat"> Apellido Materno </label>
                <div class="col-sm-9">
                    <input type="text" id="txtapellidomat" name="materno" placeholder="Apellido Materno" class="col-xs-10 col-sm-5" value="${objusu.apellidoMaterno}" required />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lbldni"> DNI </label>
                <div class="col-sm-9">
                    <input type="text" id="txtdni" name="dni" pattern="[0-9]{8}"  maxlength="8"  onkeypress="return solo_numeros(event)" placeholder="Documento Nacional de Identidad" class="col-xs-10 col-sm-5" value="${objusu.dni}" required />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lbltel"> Telefonos </label>
                <div class="col-sm-9">
                    <input type="text" id="txttelefono" pattern="[0-9]{8}"  maxlength="9"  onkeypress="return solo_numeros(event)" name="telefono" placeholder="Telefonos" class="col-xs-10 col-sm-5" value="${objusu.telefonos}" required />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblusu"> Usuario del Sistema </label>
                <div class="col-sm-9">
                    <input type="text" id="txtusuario"  maxlength="15"name="usuario" placeholder="Usuario del Sistema" autocomplete="off" class="col-xs-10 col-sm-5" value="${objusu.usuario}" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblpassword"> Clave </label>
                <div class="col-sm-9">
                    <input type="password" id="txtpassword" maxlength="10" name="clave" placeholder="Clave" autocomplete="off" class="col-xs-10 col-sm-5" value="${objusu.password}" required/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblperfil">Perfil de Usuario</label>
                <div class="col-sm-3">
                    <input type="hidden" id="hdperfil" value="${objusu.perfil.idperfil}">
                    <select class="chosen-select form-control" id="cbxperfil" name="perfil" data-placeholder="Perfil..." required>
                        <option value="">Seleccione</option>
                        <option value="1">ADMINISTRADOR</option>
                        <option value="2">SECRETARIA</option>
                        <option value="3">OPERADOR</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblestado">Estado</label>
                <div class="col-sm-3">
                    <input type="hidden" id="hdestado" value="${objusu.estado}">
                    <select class="chosen-select form-control" id="cbxestado" name="estado" data-placeholder="Perfil..." required >
                        <option value="">Seleccione</option>
                        <option value="A">Activo</option>
                        <option value="I">Inactivo</option>
                    </select>
                </div>
            </div>
            <div class="space-4"></div>
            <div id="mensaje"></div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="button" onclick="enviar('${EVENTO}');">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        ${EVENTO}
                    </button>

                    &nbsp; &nbsp; &nbsp;
                   <a href="SMenu?action=pageRegistroUsuario"><button class="btn" type="button" >
                            <i class="ace-icon fa fa-undo bigger-110"></i>
                            Limpiar
                        </button>
                    </a>
                </div>
            </div>
        </form>
    </div>
</div>
<div><h4 align="center">${mensaje}</h4> </div>
<div class="hr hr-24">

</div>

<div class="row">
    <div class="col-xs-12">
        <h3 class="header smaller lighter blue">Usuarios Registrados</h3>

        <div class="clearfix">
            <div class="pull-right tableTools-container"></div>
        </div>
        <div class="table-header">
            Resultado para "Usuarios Registrados"
        </div>

        <!-- div.table-responsive -->

        <!-- div.dataTables_borderWrap -->
        <div>
            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th class="center">Nº
                        </th>
                        <th>Nombres y Apellidos</th>
                        <th>DNI</th>
                        <th>Telefono</th>
                        <th>Perfil</th>
                        <th>Estado</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="usuario" items="${lista}" varStatus="loop">
                        <tr>
                            <td> ${loop.count}</td>
                            <td> ${usuario.nombres}  ${usuario.apellidoPaterno}  ${usuario.apellidoMaterno}</td>
                            <td> ${usuario.dni}</td>
                            <td> ${usuario.telefonos}</td>
                            <td>  <script>  document.write(perfiltext("${usuario.perfil.idperfil}"))</script></td>
                            <c:if test = "${usuario.estado== 'A'}">
                                <td>ACTIVO</td>
                            </c:if>
                            <c:if test = "${usuario.estado =='I'}">
                                <td>INACTIVO</td>
                            </c:if>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    

                                    <a class="green" href="ServUsuario?evento=formActualizarUsuario&idusuario=${usuario.idUsuario}" >
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>

<!--                                    <a class="red" href="ServUsuario?evento=eliminarUsuario&idusuario=${usuario.idUsuario}">
                                        <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                    </a>-->
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    document.getElementById("cbxperfil").value = document.getElementById("hdperfil").value;
    document.getElementById("cbxestado").value = document.getElementById("hdestado").value;
    function enviar(e) {
        if (validar()) {
            if (e == 'REGISTRAR') {
                confirmar = confirm("Desea registrar un nuevo usuario");
                if (confirmar) {
                    document.getElementById("form1").submit();
                }
            } else if (e == 'ACTUALIZAR') {
                 confirmar = confirm("Desea actualizar el usuario");
                if (confirmar) {
                document.getElementById("hdEvento").value = 'actualizarUsuario';
                document.getElementById("form1").submit();
            }
            }
        }
    }
</script>
