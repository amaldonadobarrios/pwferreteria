<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function fn_tipo_cliente(tipo) {
        if (tipo != '') {
            if (tipo == 'P') {
                //alert("Persona natural")
                var w = document.getElementById('DivRaz');
                w.style.display = 'none';
                var x = document.getElementById('DivPer');
                x.style.display = 'block';
                var y = document.getElementById('DivRuc');
                y.style.display = 'none';
                var zn = document.getElementById('DivDatPerN');
                zn.style.display = 'block';
                var zap = document.getElementById('DivDatPerAP');
                zap.style.display = 'block';
                var zam = document.getElementById('DivDatPerAM');
                zam.style.display = 'block';

            }
            if (tipo == 'E') {
                //alert("Empresa")
                var w = document.getElementById('DivRaz');
                w.style.display = 'block';
                var x = document.getElementById('DivRuc');
                x.style.display = 'block';
                var y = document.getElementById('DivPer');
                y.style.display = 'none';
                var zn = document.getElementById('DivDatPerN');
                zn.style.display = 'none';
                var zap = document.getElementById('DivDatPerAP');
                zap.style.display = 'none';
                var zam = document.getElementById('DivDatPerAM');
                zam.style.display = 'none';

            }
        } else {
            var w = document.getElementById('DivRaz');
            w.style.display = 'none';
            var x = document.getElementById('DivRuc');
            x.style.display = 'none';
            var y = document.getElementById('DivPer');
            y.style.display = 'none';
            var zn = document.getElementById('DivDatPerN');
            zn.style.display = 'none';
            var zap = document.getElementById('DivDatPerAP');
            zap.style.display = 'none';
            var zam = document.getElementById('DivDatPerAM');
            zam.style.display = 'none';
        }
    }


</script>
<script>
    function validar() {
        var evento = document.getElementById('hdEvento').value;
        var validar = true;
        if (evento != '') {
            var nat = document.getElementById('naturaleza').value;
            var tipo = document.getElementById('tipo').value;
            var ruc = document.getElementById('ruc').value;
            var dni = document.getElementById('dni').value;
            var RazonSocial = document.getElementById('RazonSocial').value;
            var nombres = document.getElementById('nombres').value;
            var paterno = document.getElementById('paterno').value;
            var materno = document.getElementById('materno').value;
            var telefono = document.getElementById('telefono').value;
            var direccion = document.getElementById('direccion').value;
            var correo = document.getElementById('correo').value;
            $('#lblnaturaleza').css("color", "black");
            $('#lbltipo').css("color", "black");
            $('#lblruc').css("color", "black");
            $('#lbldni').css("color", "black");
            $('#lblrazonsocial').css("color", "black");
            $('#lblnombres').css("color", "black");
            $('#lblpaterno').css("color", "black");
            $('#lblmaterno').css("color", "black");
            $('#lbltelefono').css("color", "black");
            $('#lbldireccion').css("color", "black");
            $('#lblcorreo').css("color", "black");
            if (nat == '') {
                $('#lblnaturaleza').css("color", "red");
                validar = false;
            }
            if (tipo == '') {
                $('#lbltipo').css("color", "red");
                validar = false;
            }
            if (telefono == '') {
                $('#lbltelefono').css("color", "red");
                validar = false;
            }
            if (direccion == '') {
                $('#lbldireccion').css("color", "red");
                validar = false;
            }
            if (correo == '') {
                $('#lblcorreo').css("color", "red");
                validar = false;
            }

            if (nat != '') {
                if (nat == 'P') {
                    if (dni == '') {
                        $('#lbldni').css("color", "red");
                        validar = false;
                    }
                    if (nombres == '') {
                        $('#lblnombres').css("color", "red");
                        validar = false;
                    }
                    if (paterno == '') {
                        $('#lblpaterno').css("color", "red");
                        validar = false;
                    }
                    if (materno == '') {
                        $('#lblmaterno').css("color", "red");
                        validar = false;
                    }
                } else if (nat == 'E') {
                    if (ruc == '') {
                        $('#lblruc').css("color", "red");
                        validar = false;
                    }
                    if (RazonSocial == '') {
                        $('#lblRazonSocial').css("color", "red");
                        validar = false;
                    }
                }

            }
        }
        return validar;
    }
    function perfiltext(valor) {

        var t = document.getElementById("tipo");
        var selectedText = t.options[valor].text;
        return selectedText;
    }
</script>

<div class="page-header">
    <h1>
        CLIENTES
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Registro de Clientes
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form action="ServCliente" id="form1" method="POST" class="form-horizontal" role="form">
            <input name="evento" type="hidden" value="registrarCliente" id="hdEvento">
            <input name="idcliente" type="hidden" value="${objcli.idCliente}">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" id="lblnaturaleza" for="form-field-1">Naturaleza del Cliente</label>
                <div class="col-sm-3">
                    <input type="hidden" id="hdnaturaleza" value="${objcli.naturalezaCliente}">
                    <select class="chosen-select form-control" id="naturaleza" name="naturaleza" data-placeholder=" Naturaleza del Cliente" onchange="fn_tipo_cliente(this.value);"  required>
                        <option value="">Seleccione</option>
                        <option value="P">Persona Natural</option>
                        <option value="E">Empresa</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" id="lbltipo" for="form-field-1">Tipo de Cliente</label>
                <div class="col-sm-3">
                    <input type="hidden" id="hdtipo" value="${objcli.idTipoCliente}">
                    <select class="chosen-select form-control" id="tipo" name="tipo" data-placeholder=" Tipo de Cliente" required>
                        <option value="">Seleccione</option>
                        <option value="1">Distribuidor</option>
                        <option value="2">Mayorista</option>
                        <option value="3">Minorista</option>
                    </select>
                </div>
            </div>
            <div class="form-group" style="display: none" id="DivRuc">
                <label class="col-sm-3 control-label no-padding-right" id="lblruc" for="form-field-1"> RUC </label>
                <div class="col-sm-9">
                    <input type="text" id="ruc" name="ruc" placeholder=" RUC" class="col-xs-10 col-sm-5" value="${objcli.dniRuc}" pattern="[0-9]{8}"  maxlength="11"  onkeypress="return solo_numeros(event)" />
                </div>
            </div>
            <div class="form-group" style="display: none" id="DivPer">
                <label class="col-sm-3 control-label no-padding-right"  id="lbldni" for="form-field-1"> DNI </label>
                <div class="col-sm-9">
                    <input type="text" id="dni" name="dni" placeholder="Documento Nacional de Identidad" pattern="[0-9]{8}"  maxlength="8"  onkeypress="return solo_numeros(event)" class="col-xs-10 col-sm-5" value="${objcli.dniRuc}" />
                </div>
            </div>

            <div class="form-group" style="display: none" id="DivRaz">
                <label class="col-sm-3 control-label no-padding-right" id="lblrazonsocial" for="form-field-1"> Razón Social</label>
                <div class="col-sm-9">
                    <input type="text" id="RazonSocial" name="RazonSocial" placeholder=" Razón Social" class="col-xs-10 col-sm-5" value="${objcli.razonSocial}" />
                </div>
            </div>
            <div class="form-group" style="display: none" id="DivDatPerN">
                <label class="col-sm-3 control-label no-padding-right" id="lblnombres" for="form-field-1"> Nombres</label>
                <div class="col-sm-9">
                    <input type="text" id="nombres" name="nombres" placeholder="nombre" class="col-xs-10 col-sm-5" value="${objcli.nombres}"  />
                </div>
            </div>
            <div class="form-group" style="display: none" id="DivDatPerAP">
                <label class="col-sm-3 control-label no-padding-right" id="lblpaterno" for="form-field-1"> Apellido Paterno </label>
                <div class="col-sm-9">
                    <input type="text" id="paterno" name="paterno" placeholder="Apellido Paterno" class="col-xs-10 col-sm-5" value="${objcli.apellidoPaterno}" />
                </div>
            </div>
            <div class="form-group" style="display: none" id="DivDatPerAM">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblmaterno"> Apellido Materno </label>
                <div class="col-sm-9">
                    <input type="text" id="materno" name="materno" placeholder="Apellido Materno" class="col-xs-10 col-sm-5" value="${objcli.apellidoMaterno}" />
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lbltelefono"> Telefono </label>
                <div class="col-sm-9">
                    <input type="tel" id="telefono" name="telefono"  placeholder="Telefono" class="col-xs-10 col-sm-5" value="${objcli.telefono}" pattern="[0-9]{8}"  maxlength="9"  onkeypress="return solo_numeros(event)" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" id="lbldireccion" for="form-field-1"> Dirección </label>
                <div class="col-sm-9">
                    <input type="text" id="direccion" name="direccion" placeholder=Dirección" class="col-xs-10 col-sm-5" value="${objcli.direccion}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblcorreo"> Correo electrónico </label>
                <div class="col-sm-9">
                    <input type="email" id="correo" name="correo" placeholder="Correoelectrónico" class="col-xs-10 col-sm-5"value="${objcli.email}" />
                </div>
            </div>

            <div class="space-4"></div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="button" onclick="enviar('${EVENTO}');">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        ${EVENTO}
                    </button>

                    &nbsp; &nbsp; &nbsp;
                    <a href="SMenu?action=pageRegistroCliente"><button class="btn" type="button" >
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
<div class="hr hr-24"></div>

<div class="row">
    <div class="col-xs-12">
        <h3 class="header smaller lighter blue">Clientes Registrados</h3>

        <div class="clearfix">
            <div class="pull-right tableTools-container"></div>
        </div>
        <div class="table-header">
            Resultado para "Clientes Registrados"
        </div>

        <!-- div.table-responsive -->

        <!-- div.dataTables_borderWrap -->
        <div>
            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th class="center">Nº
                        </th>
                        <th>Nombres y Apellidos / RAZON SOCIAL</th>
                        <th>DNI / RUC</th>
                        <th>TIPO</th>
                        <th>Dirección</th>
                        <th>Contacto</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="cliente" items="${lista}" varStatus="loop">
                        <tr>
                            <td> ${loop.count}</td>
                            <c:if test = "${cliente.naturalezaCliente== 'P'}">
                                <td> ${cliente.nombres}  ${cliente.apellidoPaterno}  ${cliente.apellidoMaterno}</td>
                            </c:if>
                            <c:if test = "${cliente.naturalezaCliente== 'E'}">
                                <td> ${cliente.razonSocial}</td>
                            </c:if>
                            <td> ${cliente.dniRuc}</td>
                            <td>  <script>  document.write(perfiltext("${cliente.idTipoCliente}"))</script></td>
                            <td> ${cliente.direccion} </td>
                            <td> ${cliente.telefono} <br> ${cliente.email}</td>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    

                                    <a class="green" href="ServCliente?evento=formActualizarCliente&idcliente=${cliente.idCliente}" >
                                        <i class="ace-icon fa fa-pencil bigger-130"></i>
                                    </a>

<!--                                    <a class="red" href="ServUsuario?evento=eliminarUsuario&idusuario=${cliente.idCliente}">
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
    document.getElementById("naturaleza").value = document.getElementById("hdnaturaleza").value;
    document.getElementById("tipo").value = document.getElementById("hdtipo").value;
    fn_tipo_cliente(document.getElementById("hdnaturaleza").value);
    function enviar(e) {
        if (validar()) {
            if (e == 'REGISTRAR') {
                confirmar = confirm("Desea registrar un nuevo cliente");
                if (confirmar) {
                    document.getElementById("form1").submit();
                }
            } else if (e == 'ACTUALIZAR') {
                confirmar = confirm("Desea actualizar el cliente");
                if (confirmar) {
                    document.getElementById("hdEvento").value = 'actualizarCliente';
                    document.getElementById("form1").submit();
                }
            }
        }
    }
</script>