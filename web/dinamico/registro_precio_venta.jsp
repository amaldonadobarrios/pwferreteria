<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<input type="hidden" id="contexto" value="${context}">
<script>
    function verproducto(id) {
        var vruta = '/ServProducto';
        var vevento = 'IrformActualizarPrecioVentaAjax';
        var jqdata = {
            evento: vevento,
            idproducto: id
        };
        fnEjecutarPeticion(vruta, jqdata, vevento);

    }
    function fnControlEvento(vevento, vvrespuesta) {
        if (vevento == 'IrformActualizarPrecioVentaAjax') {
            fn_pintaprod(vvrespuesta);
        }
    }

    function fn_pintaprod(response) {
        var v_resultado = response + "";
        var respuesta = v_resultado.split('%');
        var imagen = respuesta[0];
        var pv1 = respuesta[1];
        var pv2 = respuesta[2];
        var pv3 = respuesta[3];
        if (pv1 != '0.0') {
            document.getElementById("pv1").value = pv1;
        }else{
           document.getElementById("pv1").value = '';  
        }
        if (pv2 != '0.0') {
            document.getElementById("pv2").value = pv2;
        }else{
           document.getElementById("pv2").value = '';  
        }
        if (pv3 != '0.0') {
            document.getElementById("pv3").value = pv3;
        }else{
           document.getElementById("pv3").value = '';  
        }
        if (imagen != 'null') {
            document.getElementById("image").src = "data:image/jpg;base64," + imagen;
        } else {
            var contexto = document.getElementById("contexto").value;
            document.getElementById("image").src = contexto + "/assets/images/sinfoto.png";
        }
    }

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

</script>
<div class="page-header">
    <h1>
        PRECIO DE VENTA
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Registro de Precio de Venta
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form action="ServProducto" method="POST" class="form-horizontal" role="form">
            <input name="evento" type="hidden" value="registrarprecioventa">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Seleccione Producto</label>
                <div class="col-sm-8"> 
                    <select class="chosen-select form-control" id="id_producto" name="id_producto" data-placeholder="Seleccione un producto..." required="true" onchange="verproducto(this.value);"> 
                        <option value="">  </option>
                        <c:forEach var="producto" items="${sessionScope.comboprod}" varStatus="loop">
                            <option value="${producto.id_producto}">${producto.descripcion} ${producto.marca} ${producto.presentacion} ${producto.medida}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Precio Venta  DISTRIBUIDOR </label>
                <div class="col-sm-9">
                    <input type="number" id="pv1" name="pv1" placeholder=" Precio de Venta al Distribuidor" class="col-xs-10 col-sm-5"required="true" min="0" value="${objProd.pv1}" step="any" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Precio Venta  MAYORISTA </label>
                <div class="col-sm-9">
                    <input type="number" id="pv2" name="pv2" placeholder=" Precio de Venta al Mayorista" class="col-xs-10 col-sm-5" required="true"min="0" value="${objProd.pv2}" step="any"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Precio Venta  MINORISTA </label>
                <div class="col-sm-9">
                    <input type="number" id="pv3" name="pv3" placeholder=" Precio de Venta al Minorista" class="col-xs-10 col-sm-5" required="true"min="0" value="${objProd.pv3}" step="any"/>
                </div><br>
                <div class="col-md-10 sidenav" align="center" id="divfoto" >
                    <c:if test = "${img64 !=null}">

                        <img  src="data:image/jpg;base64,${img64}"width="200px" height="200px" class="img-circle"
                              name="image" id="image" />
                    </c:if>
                    <c:if test = "${img64 ==null}">
                        <img src="assets/images/sinfoto.png" width="200px"  height="200px" class="img-circle"
                             name="image" id="image" />
                    </c:if>
                </div>

            </div>

            <div class="space-4"></div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="submit">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        Registrar
                    </button>

                    &nbsp; &nbsp; &nbsp;
                    <a href="SMenu?action=pageRegistroPrecioVenta"><button class="btn" type="button" >
                            <i class="ace-icon fa fa-undo bigger-110"></i>
                            Limpiar
                        </button>
                    </a>
                </div>
            </div>
        </form>
        <input type="hidden" id="idpro" value="${objProd.id_producto}"/>
    </div>
</div>
<div><h4 align="center">${mensaje}</h4> </div>
<div class="hr hr-24"></div>

<div class="row">
    <div class="col-xs-12">
        <div class="clearfix">
            <div class="pull-right tableTools-container"></div>
        </div>
        <div class="table-header">
            Resultado para "Registro de Precio de Venta"
        </div>

        <!-- div.table-responsive -->

        <!-- div.dataTables_borderWrap -->
        <div>
            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th class="center">Nº
                        </th>
                        <th>Nombre producto</th>
                        <th>Tipo</th>
                        <th>P.Distribuidor</th>
                        <th>P. Mayorista</th>
                        <th>P.Minorista</th>
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
<script>

    document.getElementById("id_producto").value = document.getElementById("idpro").value;
</script>