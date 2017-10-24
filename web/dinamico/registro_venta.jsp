<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="util.ConstanteSystem"%>
<%double igv = ConstanteSystem.getIgv();%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<input type="hidden" id="contexto" value="${context}">
<input type="hidden" id="igvConstante" value="<%=igv%>">
<script language="JavaScript">
    function mueveReloj() {
    momentoActual = new Date()
            day = momentoActual.getDate()
            mes = momentoActual.getMonth() + 1;
    año = momentoActual.getFullYear()
            hora = momentoActual.getHours()
            minuto = momentoActual.getMinutes()
            segundo = momentoActual.getSeconds()

            horaImprimible = hora + " : " + minuto + " : " + segundo
            fechaImprimible = day + "/" + mes + "/" + año
            document.getElementById("reloj").value = horaImprimible
            document.getElementById("fecha").value = fechaImprimible
            setTimeout("mueveReloj()", 1000)
    }
   

    //***************************************************************************
    //VENTA
    function fn_pintaRegVenta(response) {
    if (response == 'NOSESION') {
    mensaje('ERROR', 'SESION EXPIRADA');
    location.href = "login.jsp";
    } else {
    var v_resultado = response + "";
    var respuesta = v_resultado.split('%');
    var estado = respuesta[0];
    var MSJ = respuesta[1];
    var rpta = respuesta[2];
    if (estado == 'ERROR') {
    $('#modalLoaging').modal('hide');
    mensaje('ERROR', MSJ);
    } else {
    $('#modalLoaging').modal('hide');
    mensajeOK('VALIDADO', MSJ, rpta);
    var popUp = window.open('ServReporte?evento=venta&estado=VENDIDO&num=' + rpta, 'ventana1', "width=700,height=500,scrollbars=SI");
    if (popUp == null || typeof (popUp) == 'undefined') {
    $('#modalLoaging').modal('show');
    setTimeout("redireccionarPagina()", 20000);
    } else {
    $('#modalLoaging').modal('hide');
    setTimeout("redireccionarPagina()", 5000);
    }
    }
    }
    }
    function myFunction() {
    var myWindow = window.open("", "", "width=200,height=100");
    myWindow.document.write("<p>A new window!</p>");
    myWindow.focus();
    }

    function fn_registrarVentaAjax(jdatos) {
    var vruta = '/ServVenta';
    var vevento = 'RegistrarVentaAJAX';
    var jqdata = jdatos;
    fnEjecutarPeticion(vruta, jqdata, vevento);
    }

    function fn_registrar_Venta() {
    var doc = $("#cbxdoc").val();
    var num = $("#numero").val();
    var idcli = $("#txtidcliente").val();
    var total = $("#total").val();
    var igv = $("#igv").val();
    var neto = $("#neto").val();
    confirmar = confirm("¿Desea Registrar la Venta?");
    if (confirmar) {
    if (validarventa()) {
    $('#modalLoaging').modal('show');
    var jdatos = {
    evento: 'RegistrarVentaAJAX',
            documento: doc,
            numero: num,
            id_cliente: idcli,
            total: total,
            igv: igv,
            neto:neto
    };
    fn_registrarVentaAjax(jdatos);
    }
    }
    }
    function validarventa() {
    var doc = $("#cbxdoc").val();
    var num = $("#numero").val();
    var idcli = $("#txtidcliente").val();
    var total = $("#total").val();
    var val = true;
    if (doc == '') {

    mensaje('ERROR', 'SELECCIONE UN COMPROBANTE');
    val = false;
    }
    if (num == '') {

    mensaje('ERROR', 'INGRESE UN NÚMERO DE COMPROBANTE');
    val = false;
    }
    if (idcli == '') {

    mensaje('ERROR', 'SELECCIONE UN CLIENTE');
    val = false;
    }
    if (total == '') {

    mensaje('ERROR', 'NO HA REGISTRADO PRODUCTOS');
    val = false;
    }
    if (total == '0.00') {

    mensaje('ERROR', 'NO HA REGISTRADO PRODUCTOS');
    val = false;
    }
    return val;
    }


    //***************************************************************************
    //PRODUCTO

    function fneliminarItem(item) {
    var it = Number(item - 1);
    var vruta = '/ServVenta';
    var vevento = 'EliminarProductoAJAX';
    var jqdata = {
    evento: 'EliminarProductoAJAX',
            item: it
    };
    fnEjecutarPeticion(vruta, jqdata, vevento);
    }
    function  fn_pintacarrito(response) {
    var constIGV = document.getElementById("igvConstante").value;
    if (response === 'NOSESION') {
    mensaje('ERROR', 'SESION EXPIRADA');
    location.href = "login.jsp";
    } else {
    var igv = 0;
    var neto = 0;
    var total = 0;
    var v_resultado = response + "";
    var respuesta = v_resultado.split('%');
    var estado = respuesta[0];
    var subtotal = respuesta[1];
    var datos = respuesta[2];
    if (estado == 'ERROR') {
    mensaje('ERROR', datos);
    } else {
    igv = subtotal * constIGV;
    neto = subtotal - igv;
    total = neto + igv;
    document.getElementById("neto").value = Number(neto).toFixed(2);
    document.getElementById("igv").value = Number(igv).toFixed(2);
    document.getElementById("total").value = Number(total).toFixed(2);
    $('#detalleventa').html(datos);
    $('#dynamic-table').DataTable({
    responsive: true
    });
    $('#dynamic-table').stacktable();
    }
    }
    }
    function iraServletAñadirProducto(jdatos) {
    var vruta = '/ServVenta';
    var vevento = 'AñadirProductoAJAX';
    var jqdata = jdatos;
    fnEjecutarPeticion(vruta, jqdata, vevento);
    }
    function fn_añadir_producto() {
    $('#smsgVenta').html('');
    var idtipo = document.getElementById("txttipocli").value;
    var cantidad = document.getElementById("cantidad").value;
    var price = document.getElementById("precio").value;
    var stock = document.getElementById("stock").value;
    var id_cliente = document.getElementById('txtidcliente').value;
    var id_producto = document.getElementById('cbxprod').value;
    if (cantidad != '' && price != '' && stock != '' && idtipo != '' && id_cliente != '' && cantidad > 0 && id_producto!= '') {
    cantidad = parseFloat(cantidad);
    price = parseFloat(price);
    stock = parseFloat(stock);
    if (cantidad <= stock) {
    var jdatos = {
    evento: 'AñadirProductoAJAX',
            idtipocliente: idtipo,
            cantidad: cantidad,
            precio: price,
            id_cliente: id_cliente,
            id_producto: id_producto
    }
    iraServletAñadirProducto(jdatos);
    } else {
    mensaje('ERROR', 'NO HAY SUFICIENTES PRODUCTOS');
    document.getElementById("cantidad").value = '';
    $('#cantidad').focus();
    }
    } else {
    mensaje('ERROR', ' DEBE SELECCIONAR UN PRODUCTO Y  LA CANTIDAD');
    }
    }
    function mensaje(titulo, mensaje) {
    swal({
    type: 'warning',
            title: titulo,
            text: mensaje,
            timer: 2000,
            showConfirmButton: false
    });
    }
    function mensajeOK(titulo, mensaje, rpta) {
    swal({
    type: 'success',
            title: titulo,
            text: mensaje + ' Venta :' + rpta + ' REGISTRADA ',
            showConfirmButton: true,
    });
    }

    function redireccionarPagina() {
    window.location = "SMenu?action=pageRegistroVenta";
    }

    function mostrarStock(id) {
    var vruta = '/ServProducto';
    var vevento = 'GetProductoDetallesAjax';
    var jqdata = {
    evento: vevento,
            idproducto: id
    };
    if (id != '') {
    fnEjecutarPeticion(vruta, jqdata, vevento);
    } else {
    document.getElementById("precio").value = '';
    document.getElementById("stock").value = '';
    var contexto = document.getElementById("contexto").value;
    document.getElementById("image").src = contexto + "/assets/images/sinfoto.png";
    document.getElementById("cantidad").value = '';
    }


    }
    function fn_pintaprod(response) {
    if (response == 'NOSESION') {
    mensaje('ERROR', 'SESION EXPIRADA');
    location.href = "login.jsp";
    } else {
    $('#smsgVenta').html('');
    var v_resultado = response + "";
    var respuesta = v_resultado.split('%');
    var imagen = respuesta[0];
    var pv1 = respuesta[1];
    var pv2 = respuesta[2];
    var pv3 = respuesta[3];
    var stock = respuesta[4];
    var idtipo = document.getElementById("txttipocli").value;
    var precio;
    if (idtipo != '') {
    if (idtipo == '1') {
    precio = pv1;
    } else if (idtipo == '2') {
    precio = pv2;
    } else if (idtipo == '3') {
    precio = pv3;
    }
    document.getElementById("precio").value = Number(precio).toFixed(2);
    document.getElementById("stock").value = stock;
    document.getElementById("cantidad").value = '';
    if (imagen != 'null') {
    document.getElementById("image").src = "data:image/jpg;base64," + imagen;
    } else {
    var contexto = document.getElementById("contexto").value;
    document.getElementById("image").src = contexto + "/assets/images/sinfoto.png";
    }
    } else {
    $('#smsgVenta').html('ERROR! ANTES DEBE SELECCIONAR EL CLIENTE');
    mensaje('ERROR', ' ANTES DEBE SELECCIONAR EL CLIENTE');
    }


    }
    }



//   ******************************************************************************/ 
    //CLIENTE
    function fn_grabar_cliente() {
    if (validar()) {
    var nat = document.getElementById('naturaleza').value;
    var tipo = document.getElementById('tipo').value;
    var telefono = document.getElementById('telefono').value;
    var direccion = document.getElementById('direccion').value;
    var correo = document.getElementById('correo').value;
    var dni;
    var nombres;
    var paterno;
    var materno
            var ruc;
    var RazonSocial;
    if (nat == 'P') {
    dni = document.getElementById('dni').value;
    nombres = document.getElementById('nombres').value;
    paterno = document.getElementById('paterno').value;
    materno = document.getElementById('materno').value;
    } else if (nat == 'E') {
    ruc = document.getElementById('ruc').value;
    RazonSocial = document.getElementById('RazonSocial').value;
    }
    var jqdatos = {
    evento: 'RegistrarClienteAJAX',
            naturaleza: nat,
            correo: correo,
            direccion: direccion,
            telefono: telefono,
            materno: materno,
            paterno: paterno,
            nombres: nombres,
            RazonSocial: RazonSocial,
            dni: dni,
            ruc: ruc,
            tipo: tipo
    };
    fn_ejecutar_grabar_cli(jqdatos);
    }
    }
    function fn_ejecutar_grabar_cli(jqdatos) {
    var vruta = '/ServVenta';
    var vevento = 'RegistrarClienteAJAX';
    var jqdata = jqdatos;
    fnEjecutarPeticion(vruta, jqdata, vevento);
    }
    function fnSeleccionarCliente(rz, apep, apem, nom, doc, dir, nat, tip, id) {
    var tipdesc;
    if (nat == 'P') {
    document.getElementById('txtcliente').value = apep + ' ' + apem + ' ' + nom;
    } else if (nat == 'E') {
    document.getElementById('txtcliente').value = rz;
    }
    if (tip == '1') {
    tipdesc = 'DISTRIBUIDOR';
    } else if (tip == '2') {
    tipdesc = 'MAYORISTA';
    } else if (tip == '3') {
    tipdesc = 'MINORISTA';
    }
    document.getElementById('txtidcliente').value = id;
    document.getElementById('txtdnioRuc').value = doc;
    document.getElementById('txtdomi').value = dir;
    document.getElementById('txttipocli').value = tip;
    document.getElementById('txttipoclidesc').value = tipdesc;
    document.getElementById("precio").value = '';
    document.getElementById("stock").value = '';
    document.getElementById("cantidad").value = '';
    document.getElementById("cbxprod").selectedIndex = "0";
    $('#1modal').modal('hide');
    }
    function linpiar_modal() {
    document.getElementById('txtdni_ruc').value = '';
    document.getElementById('txtape_raz').value = '';
    $('#tablabusquedaCli').html('');
    document.getElementById('naturaleza').value = '';
    document.getElementById('tipo').value = '';
    document.getElementById('ruc').value = '';
    document.getElementById('dni').value = '';
    document.getElementById('RazonSocial').value = '';
    document.getElementById('nombres').value = '';
    document.getElementById('paterno').value = '';
    document.getElementById('materno').value = '';
    document.getElementById('naturaleza').value = '';
    document.getElementById('telefono').value = '';
    document.getElementById('direccion').value = '';
    document.getElementById('correo').value = '';
    $('#msj').html('');
    }
    function fn_buscar_cli_doc(val) {
    $('#lbldniRuc').css("color", "black");
    $('#tablabusquedaCli').html('');
    if (val == '') {
    $('#lbldniRuc').css("color", "red");
    } else {
    var vruta = '/ServVenta';
    var vevento = 'BuscarClientexDOC';
    var jqdata = {
    evento: 'BuscarClientexDOC',
            parametro: val
    };
    fnEjecutarPeticion(vruta, jqdata, vevento);
    }
    }
    function fn_buscar_cli_aperaz(val) {
    $('#lblaperazbusc').css("color", "black");
    $('#tablabusquedaCli').html('');
    if (val == '') {
    $('#lblaperazbusc').css("color", "red");
    } else {
    var vruta = '/ServVenta';
    var vevento = 'BuscarClientexAPERAZ';
    var jqdata = {
    evento: 'BuscarClientexAPERAZ',
            parametro: val
    };
    fnEjecutarPeticion(vruta, jqdata, vevento);
    }
    }
    function fn_pintalistacli(response) {
    if (response == 'NOSESION') {
    mensaje('ERROR', 'SESION EXPIRADA');
    location.href = "login.jsp";
    } else {
    $('#tablabusquedaCli').html(response);
    $('#dataTables-example').DataTable({
    responsive: true
    });
    $('#dataTables-example').stacktable();
    }
    }
    function fn_pintaRegCliente(response) {
    if (response == 'NOSESION') {
    mensaje('ERROR', 'SESION EXPIRADA');
    location.href = "login.jsp";
    } else {
    var v_resultado = response + "";
    var respuesta = v_resultado.split('%');
    var mensaje = respuesta[0];
    var json = respuesta[1];
    var arr = JSON.parse(json);
    var i = Number(1);
    $('#msj').html(mensaje);
    fnSeleccionarCliente(arr.razonSocial, arr.apellidoPaterno, arr.apellidoMaterno, arr.nombres, arr.dniRuc, arr.direccion, arr.naturalezaCliente, arr.idTipoCliente, arr.idCliente);
    }
    }
//*****************************************************************************
//CONTROLADOR AJAX

    function fnEjecutarPeticion(ruta, jdata, evento) {
    var contexto = document.getElementById('contexto').value;
    var vservlet = contexto + ruta;
    $.ajax({
    url: vservlet,
            method: 'POST',
            data: jdata,
            success: function (responseText) {
            fnControlEvento(evento, responseText + '');
            }
    });
    }
    function fnControlEvento(vevento, vvrespuesta) {
    if (vevento == 'BuscarClientexDOC') {
    fn_pintalistacli(vvrespuesta);
    }
    if (vevento == 'BuscarClientexAPERAZ') {
    fn_pintalistacli(vvrespuesta);
    }
    if (vevento == 'RegistrarClienteAJAX') {
    fn_pintaRegCliente(vvrespuesta);
    }
    if (vevento == 'GetProductoDetallesAjax') {
    fn_pintaprod(vvrespuesta);
    }
    if (vevento == 'AñadirProductoAJAX') {
    fn_pintacarrito(vvrespuesta);
    }
    if (vevento == 'EliminarProductoAJAX') {
    fn_pintacarrito(vvrespuesta);
    }
    if (vevento == 'RegistrarVentaAJAX') {
    fn_pintaRegVenta(vvrespuesta);
    }



    }

</script>
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
    var validar = true;
    var nat = document.getElementById('naturaleza').value;
    var tipo = document.getElementById('tipo').value;
    $('#lblnaturaleza').css("color", "black");
    $('#lbltipo').css("color", "black");
    if (nat == '') {
    $('#lblnaturaleza').css("color", "red");
    validar = false;
    }
    if (tipo == '') {
    $('#lbltipo').css("color", "red");
    validar = false;
    }
    if (nat != '') {
    if (nat == 'P') {
    $('#lbldni').css("color", "black");
    $('#lblnombres').css("color", "black");
    $('#lblpaterno').css("color", "black");
    $('#lblmaterno').css("color", "black");
    var dni = document.getElementById('dni').value;
    var nombres = document.getElementById('nombres').value;
    var paterno = document.getElementById('paterno').value;
    var materno = document.getElementById('materno').value;
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
    $('#lblruc').css("color", "black");
    $('#lblrazonsocial').css("color", "black");
    var ruc = document.getElementById('ruc').value;
    var RazonSocial = document.getElementById('RazonSocial').value;
    if (ruc == '') {
    $('#lblruc').css("color", "red");
    validar = false;
    }
    if (RazonSocial == '') {
    $('#lblrazonsocial').css("color", "red");
    validar = false;
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
        VENTAS 
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Registro de Venta
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="container">
        <div class="col-sm-12">
            <div class="col-sm-6">
                <fieldset>
                    <legend>Cliente</legend>   
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Cliente</label>
                        <div class="col-sm-9">
                            <input type="text" id="txtcliente" name="txtcliente" placeholder="Cliente" class="col-xs-10 col-sm-9"  disabled/>
                            <input type="hidden" id="txtidcliente" name="txtidcliente" placeholder="Cliente" class="col-xs-10 col-sm-9"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> DNI o RUC</label>
                        <div class="col-sm-9">
                            <input type="text" id="txtdnioRuc" name="txtdnioRuc" placeholder="DNI o RUC" class="col-xs-10 col-sm-9" disabled />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Domicilio</label>
                        <div class="col-sm-9">
                            <input type="text" id="txtdomi" name="txtdomi" placeholder="Domicilio" class="col-xs-10 col-sm-9" disabled/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Tipo de Cliente</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="txttipocli" name="txttipocli" placeholder="Tipo de Cliente" class="col-xs-10 col-sm-9" disabled/>
                            <input type="text" id="txttipoclidesc" name="txttipoclidesc" placeholder="Tipo de Cliente" class="col-xs-10 col-sm-9" disabled/>
                        </div>
                    </div>
                    <div  class="col-sm-12" align="center"> <input type="button"  class="buttons bigger-130 colorpicker-with-alpha"value="Seleccionar cliente" onclick="linpiar_modal();
                        $('#1modal').modal('show');"></div>
                </fieldset>
            </div>
            <div class="col-sm-6">             
                <fieldset>
                    <legend>Documento</legend>   
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Documento</label>
                        <div class="col-sm-9">
                            <select class="chosen-select form-control col-xs-10 col-sm-8" id="cbxdoc" name="cbxdoc"   data-placeholder="Unidad de Medida">
                                <option value="">Seleccione</option>
                                <option value="BOL">Boleta de Venta</option>
                                <option value="FAC">Factura</option>
                                <option value="GDV">Guía de venta</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Número</label>
                        <div class="col-sm-9">
                            <input type="text" id="numero" name="numero" placeholder="Número de comprobante" onkeypress="return solo_numeros(event)" class="col-xs-10 col-sm-9" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Fecha</label>
                        <div class="col-sm-9">
                            <input type="text"  readonly="true"  name="fecha" id="fecha"size="10" >
                            <input type="text"  readonly="true"  name="reloj" id="reloj" size="10">
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>    

        <div class="col-sm-12">
            <fieldset>
                <legend>Producto</legend>   
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Producto</label>
                    <div class="col-sm-8">
                        <select class="chosen-select form-control"  id="cbxprod" name="cbxprod"  onchange="mostrarStock(this.value);">
                            <option value=""selected>Seleccione</option>
                            <c:forEach var="producto" items="${combopro}" varStatus="loop">
                                <option value="${producto.id_producto}">${producto.descripcion} ${producto.marca} ${producto.presentacion} ${producto.medida}</option>
                            </c:forEach>
                        </select> <div id="smsgVenta" style="color: red"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Stock</label>
                    <div class="col-sm-9">
                        <input type="text" id="stock" name="stock" placeholder="Stock" class="col-xs-10 col-sm-9" disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Precio</label>
                    <div class="col-sm-9">
                        <input type="text" id="precio" name="precio" placeholder="precio" class="col-xs-10 col-sm-9" disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Cantidad</label>
                    <div class="col-sm-9">
                        <input type="number" id="cantidad" name="cantidad" placeholder="Cantidad" min="0" class="col-xs-10 col-sm-9" step="any"/>
                    </div> 
                </div>
                <div  class="col-sm-12" align="center"> <input type="button" onclick="fn_añadir_producto();" class="buttons bigger-130 colorpicker-with-alpha"value="Añadir Producto"></div>

            </fieldset>   
            <div class="col-md-10 sidenav" align="center" id="divfoto" >
                <img src="assets/images/sinfoto.png" width="200px"  height="200px" class="img-circle"
                     name="image" id="image" />
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="table-header">
                    Resultado para "Registro de  Venta"
                </div>

                <!-- div.table-responsive -->

                <!-- div.dataTables_borderWrap -->
                <div id="detalleventa">
                    <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th class="center">Nº
                                </th>
                                <th>Código</th>
                                <th>Cantidad</th>
                                <th>Detalle de Producto</th>
                                <th>Precio Unitario</th>
                                <th>Sub total</th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="producto" items="${comboprod}" varStatus="loop">
                                <tr>
                                    <td> ${loop.count}</td>
                                    <td> ${producto.descripcion} ${producto.marca} ${producto.presentacion}  ${producto.medida}</td>
                                    <td> ${producto.prod_insu}</td>
                                    <td> ${producto.pv1}</td>
                                    <td> ${producto.pv2}</td>
                                    <td> ${producto.pv3}</td>

                                    <td>
                                        <div class="hidden-sm hidden-xs action-buttons">
                                            <a class="blue" href="SMenu?action=pageRegistroPrecioVenta">
                                                <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                            </a>

                                            <a class="green" href="ServProducto?evento=IrformActualizarPrecioVenta&idproducto=${producto.id_producto}" >
                                                <i class="ace-icon fa fa-pencil bigger-130"></i>
                                            </a>

<!--                                    <a class="red" href="ServUsuario?evento=eliminarUsuario&idusuario=${producto.id_producto}">
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
        <div class="col-sm-12" align="right">
            <div class="form-group col-md-offset-6">
                <label class="col-sm-2 control-label no-padding-right" for="form-field-1"> NETO</label>
                <div class="col-sm-9">
                    <input type="text" id="neto" name="neto" placeholder="NETO" class="col-xs-10 col-sm-4"disabled />
                </div>
            </div> 
            <div class="form-group col-md-offset-6">
                <label class="col-sm-2 control-label no-padding-right" for="form-field-1"> IGV (<%=igv * 100%>%)</label>
                <div class="col-sm-9">
                    <input type="text" id="igv" name="igv" placeholder="IGV" class="col-xs-10 col-sm-4"disabled />
                </div>
            </div>
            <div class="form-group col-md-offset-6">
                <label class="col-sm-2 control-label no-padding-right" for="form-field-1">TOTAL</label>
                <div class="col-sm-9">
                    <input type="text"  id="total" name="total" placeholder="TOTAL" class="col-xs-10 col-sm-4"disabled />
                </div>
            </div> 
        </div>

    </div></div>

<!-- PAGE CONTENT BEGINS -->
<div class="clearfix form-actions">
    <div class="col-md-12" align="center">
        <button class="btn btn-info" type="button" onclick="fn_registrar_Venta();">
            <i class="ace-icon fa fa-check bigger-110"></i>
            Registrar
        </button>

        &nbsp; &nbsp; &nbsp;
        <a  href="SMenu?action=pageRegistroVenta">  <button class="btn" type="button" id="limpiar">
                <i class="ace-icon fa fa-undo bigger-110"></i>
                Limpiar
            </button></a>
    </div>
    <div id="spin"></div>
</div>
<script>
    mueveReloj();</script>



<div class="modal fade" id="1modal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">

    <div  class="modal-dialog modal-lg" role="document">  
        <div class="panel panel-primary"> 
            <div class="panel-heading">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>	</button>
                <h5 id="nombresFam"> CLIENTE</h5>						
            </div>
            <div class="panel-body">
                <div class="modal-body">
                    <form id="form1">
                        <div class="row">
                            <!-- INICIO COLUMNA N° 1 DEL MODAL -->

                            <div class="col-lg-6"> 
                                <div  class="panel panel-info">
                                    <div class="panel-heading">DATOS DE BUSQUEDA</div> <div class="panel-body">
                                        <div class="form-group"><label  id="lbldniRuc">DNI O RUC</label>
                                            <div class="input-group" >
                                                <input type="text" class="form-control" id="txtdni_ruc" placeholder="Dni o Ruc" onkeypress="return solo_numeros(event)">
                                                <span class="input-group-btn">
                                                    <button class="btn btn-primary btn-sm" onclick="fn_buscar_cli_doc(document.getElementById('txtdni_ruc').value);" type="button">Buscar</button>
                                                </span>
                                            </div>

                                        </div>
                                        <div class="form-group"><label  id="lblaperazbusc">Apellido o Razon social</label>
                                            <div class="input-group" >
                                                <input type="text" class="form-control" id="txtape_raz" placeholder="Apellido o Razon Social">
                                                <span class="input-group-btn">
                                                    <button class="btn btn-primary btn-sm" onclick="fn_buscar_cli_aperaz(document.getElementById('txtape_raz').value);" type="button">Buscar</button>
                                                </span>
                                            </div>

                                        </div>
                                    </div></div>
                                <div  class="panel panel-info">
                                    <div class="panel-heading">COINCIDENCIAS ENCONTRADAS</div> <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="tablabusquedaCli">

                                            </div>  
                                        </div>  
                                    </div></div>
                            </div> 
                            <div class="col-lg-6"> <!-- FIN E INICIO DE NUEVA COLUMNA -->

                                <div  class="panel panel-info">
                                    <div class="panel-heading">AGREGAR NUEVO CLIENTE</div> 
                                    <div class="panel-body">

                                        <div class="form-group">
                                            <label class="tit" id="lblnaturaleza" for="form-field-1">Naturaleza del Cliente*</label>
                                            <div>
                                                <input type="hidden" id="hdnaturaleza" value="">
                                                <select class="form-control" id="naturaleza" name="naturaleza" data-placeholder=" Naturaleza del Cliente" onchange="fn_tipo_cliente(this.value);"  required>
                                                    <option value=""  selected="selected">Seleccione</option>
                                                    <option value="P">Persona Natural</option>
                                                    <option value="E">Empresa</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="tit"  id="lbltipo" for="form-field-1">Tipo de Cliente*</label>
                                            <div>
                                                <select class="form-control" id="tipo" name="tipo" data-placeholder=" Tipo de Cliente" required>
                                                    <option value="">Seleccione</option>
                                                    <option value="1">Distribuidor</option>
                                                    <option value="2">Mayorista</option>
                                                    <option value="3">Minorista</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group" style="display: none" id="DivRuc">
                                            <label class="tit"  id="lblruc" for="form-field-1"> RUC* </label>
                                            <div>
                                                <input type="text" class="form-control" id="ruc" name="ruc" placeholder=" RUC"   pattern="[0-9]{8}"  maxlength="11"  onkeypress="return solo_numeros(event)" />
                                            </div>
                                        </div>
                                        <div class="form-group" style="display: none" id="DivPer">
                                            <label class="tit"   id="lbldni" for="form-field-1"> DNI* </label>
                                            <div>
                                                <input type="text" class="form-control" id="dni" name="dni" placeholder="Documento Nacional de Identidad" pattern="[0-9]{8}"  maxlength="8"  onkeypress="return solo_numeros(event)" class="col-xs-10 col-sm-5"  />
                                            </div>
                                        </div>

                                        <div class="form-group" style="display: none" id="DivRaz">
                                            <label class="tit"  id="lblrazonsocial" for="form-field-1"> Razón Social*</label>
                                            <div>
                                                <input type="text" class="form-control" id="RazonSocial" name="RazonSocial" placeholder=" Razón Social"   />
                                            </div>
                                        </div>
                                        <div class="form-group" style="display: none" id="DivDatPerN">
                                            <label class="tit" id="lblnombres" for="form-field-1"> Nombres*</label>
                                            <div>
                                                <input type="text" class="form-control" id="nombres" name="nombres" placeholder="nombre"   />
                                            </div>
                                        </div>
                                        <div class="form-group" style="display: none" id="DivDatPerAP">
                                            <label class="tit"  id="lblpaterno" for="form-field-1"> Apellido Paterno* </label>
                                            <div>
                                                <input type="text" class="form-control" id="paterno" name="paterno" placeholder="Apellido Paterno"  />
                                            </div>
                                        </div>
                                        <div class="form-group" style="display: none" id="DivDatPerAM">
                                            <label class="tit"  for="form-field-1" id="lblmaterno"> Apellido Materno* </label>
                                            <div>
                                                <input type="text" class="form-control" id="materno" name="materno" placeholder="Apellido Materno"  />
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <label class="tit"  for="form-field-1" id="lbltelefono"> Telefono </label>
                                            <div>
                                                <input type="tel"  class="form-control" id="telefono" name="telefono"  placeholder="Telefono" pattern="[0-9]{8}"  maxlength="9"  onkeypress="return solo_numeros(event)" />
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="tit"  id="lbldireccion" for="form-field-1"> Dirección </label>
                                            <div>
                                                <input type="text" class="form-control" id="direccion" name="direccion" placeholder=Dirección" />
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="tit"   class="form-control" for="form-field-1" id="lblcorreo"> Correo electrónico </label>
                                            <div>
                                                <input type="email" class="form-control" id="correo" name="correo" placeholder="Correoelectrónico"  />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="tit"   class="form-control" for="form-field-1" id="campooblig"> (*) campos obligatorios </label>
                                            <div id="msj" style="color: greenyellow"></div>
                                            <div>
                                                <input type="button" class="form-control" id="btngrabar" name="btngrabar"value="Grabar" onclick="fn_grabar_cliente();" />
                                            </div>
                                        </div>
                                    </div>
                                </div> 
                            </div>
                        </div>																																						
                </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset"  id="btnclose" class="btn btn-sm btn-default" data-dismiss="modal" Onclick="">CERRAR</button>

            </div></div></div>
</div>	


<div class="modal fade container-fluid" id="modalLoaging" role="dialog" data-backdrop="static" data-keyboard="false" >
    <div class="modal-dialog modal-sm container-fluid">
        <br><br><br><br><br><br><br><br><br><br><br><br><br>
        <div class="loader  container-fluid">
        </div>
    </div>
</div>
<style>
    .loader {
        border: 16px solid #f3f3f3; /* Light grey */
        border-top: 16px solid #3498db; /* Blue */
        border-radius: 50%;
        width: 120px;
        height: 120px;
        animation: spin 2s linear infinite;
        text-align: center;
    }

    @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
    }

</style>
