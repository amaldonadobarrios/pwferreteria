<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    function validar() {
        var evento = document.getElementById('hdEvento').value;
        var validar = true;
        if (evento != '') {
            var descripcion = document.getElementById('descripcion').value;
            var marca = document.getElementById('marca').value;
            var presentacion = document.getElementById('presentacion').value;
            var medida = document.getElementById('medida').value;
            var insu = document.getElementById('chkinsumo').checked;
            var prod = document.getElementById('chkproducto').checked;
            $('#lbldescripcion').css("color", "black");
            $('#lblmarca').css("color", "black");
            $('#lblpresentacion').css("color", "black");
            $('#lblmedida').css("color", "black");
            $('#lblinsu').css("color", "black");
            $('#lblprod').css("color", "black");
            if (descripcion == '') {
                $('#lbldescripcion').css("color", "red");
                validar = false;
            }
            if (marca == '') {
                $('#lblmarca').css("color", "red");
                validar = false;
            }
            if (presentacion == '') {
                $('#lblpresentacion').css("color", "red");
                validar = false;
            }
            if (medida == '') {
                $('#lblmedida').css("color", "red");
                validar = false;
            }
            if ((insu == false && prod == false)) {
                $('#lblinsu').css("color", "red");
                $('#lblprod').css("color", "red");
                validar = false;
            }
        }
        return validar;
    }
</script>
<div class="page-header">
    <h1>
        PRODUCTOS
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Registro de Productos
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form action="ServProducto" data-toggle="validator"  method="POST" class="form-horizontal" role="form" id="form1" enctype="multipart/form-data" >
            <input name="evento" type="hidden" value="registrarProducto" id="hdEvento">
            <input name="idproducto" type="hidden" value="${objpro.id_producto}">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" id="lbldescripcion" for="form-field-1"> Nombre del Producto </label>
                <div class="col-sm-9">
                    <input type="text" id="descripcion" name="descripcion"  placeholder="Nombre del Producto" class="col-xs-10 col-sm-5" required  value="${objpro.descripcion}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" id="lblmarca" for="form-field-1"> Marca del Producto </label>
                <div class="col-sm-9">
                    <input type="text" id="marca" name="marca" placeholder="Marca delProducto" class="col-xs-10 col-sm-5" required value="${objpro.marca}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" id="lblpresentacion" for="form-field-1"> Presentación </label>
                <div class="col-sm-9">
                    <input type="text" id="presentacion" name="presentacion" placeholder="Presentación del Producto" class="col-xs-10 col-sm-5" required value="${objpro.presentacion}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1" id="lblmedida">Unidad de Medida</label>
                <div class="col-sm-3">
                    <input type="hidden" id="txtmedida"  value="${objpro.medida}"/>
                    <select class="chosen-select form-control" id="medida" name="medida" data-placeholder="Unidad de Medida" required>
                        <option value="">Seleccione</option>
                        <option value="Unidades">Unidades</option>
                        <option value="Metro">Metro</option>
                        <option value="Metro Cuadrado(m2)">Metro Cuadrado(m2)</option>
                        <option value="Metro Cubico (m3)">Metro Cúbico (m3)</option>
                        <option value="Litro">Litro</option>
                        <option value="Kilogramos">Kilogramos</option>
                    </select>
                </div>
                <div class="col-sm-3">   
                    <div class="checkbox">
                        <label id="lblprod">
                            <input type="checkbox" name="chkproducto" id="chkproducto" value="PRODUCTO">
                            <span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span>
                            Producto terminado
                        </label> 
                    </div>
                    <div class="checkbox">
                        <label id="lblinsu">
                            <input type="checkbox" name="chkinsumo" id="chkinsumo" value="INSUMO">
                            <span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span>
                            Insumo
                        </label>
                    </div>
                    <input type="hidden" id="prod_insu"  value="${objpro.prod_insu}"/>
                </div>
            </div>
            <div class="container" align="center">
                <div class="col-md-6" align="center">
                    <div class="col-md-3 sidenav" align="center">

                        <div  id="fileForm">
                            <label class="btn btn-primary btn-sm btn-block"  id="labelfoto" >	
                                <p style="font-size:18px;text-align:center;" class="fa fa-cloud-upload"></p><br>Subir Foto
                                <input type="file" name="file"  id="file"  style="opacity:0;cursor: pointer; color: white;" />
                            </label>
                        </div>
                    </div>
                    
                    <div class="col-md-3 sidenav" align="center" id="divfoto" >
                        <c:if test = "${img64 !=null}">

                            <img  src="data:image/jpg;base64,${img64}"width="200px" height="200px" class="img-circle"
                                  name="image" id="image" />
                        </c:if>
                        <c:if test = "${img64 ==null}">
                            <img src="assets/images/sinfoto.png" width="200px" height="200px" class="img-circle"
                                 name="image" id="image" />
                        </c:if>
                    </div>
                </div>
                  
                <label id="mensaje" style="color: red; text-align: center;"/>
            </div>   



            <div class="space-4"></div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="button" onclick="enviar('${EVENTO}');">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        ${EVENTO}
                    </button>

                    &nbsp; &nbsp; &nbsp;
                    <a href="SMenu?action=pageRegistroProductos"><button class="btn" type="button" >
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
        <h3 class="header smaller lighter blue">Productos Registrados</h3>

        <div class="clearfix">
            <div class="pull-right tableTools-container"></div>
        </div>
        <div class="table-header">
            Resultado para "Productos Registrados"
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
                        <th>Marca</th>
                        <th>Presentación</th>
                        <th>Unidad de medida</th>
                        <th>Tipo</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="producto" items="${lista}" varStatus="loop">
                        <tr>
                            <td> ${loop.count}</td>
                            <td> ${producto.descripcion}</td>
                            <td> ${producto.marca}</td>
                            <td> ${producto.presentacion} </td>
                            <td> ${producto.medida}</td>
                            <td> ${producto.prod_insu}</td>
                            <td>
                                <div class="hidden-sm hidden-xs action-buttons">
                                    

                                    <a class="green" href="ServProducto?evento=IrformActualizarProducto&idproducto=${producto.id_producto}" >
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
    function enviar(e) {
        if (validar()) {
            if (e == 'REGISTRAR') {
                confirmar = confirm("Desea registrar un nuevo producto");
                if (confirmar) {
                    document.getElementById("form1").submit();
                }
            } else if (e == 'ACTUALIZAR') {
                confirmar = confirm("Desea actualizar el producto");
                if (confirmar) {
                    document.getElementById("hdEvento").value = 'actualizarProducto';
                    document.getElementById("form1").submit();
                }
            }
        }
    }
</script>
<script type="text/javascript">
    function updateSize(elementId) {
        var nBytes = 0,
                oFiles = document.getElementById(elementId).files,
                nFiles = oFiles.length;

        for (var nFileId = 0; nFileId < nFiles; nFileId++) {
            nBytes += oFiles[nFileId].size;
        }
        var sOutput = nBytes + " bytes";
        // optional code for multiples approximation
        for (var aMultiples = ["K", "M", "G", "T", "P", "E", "Z", "Y"], nMultiple = 0, nApprox = nBytes / 1024; nApprox > 1; nApprox /= 1024, nMultiple++) {
            sOutput = " (" + nApprox.toFixed(3) + aMultiples[nMultiple] + ")";
        }

        return nBytes;
    }
</script>


<script>
    document.getElementById("file").onchange = function ()
    {
        document.getElementById("mensaje").innerHTML = '';
        var reader = new FileReader();
        reader.onload = function (e)
        {
            var file = $('[name="file"]');
            var filename = $.trim(file.val());
            //alert(updateSize('file'));

            if (!(isJpg(filename))) {
//|| isPng(filename)
                document.getElementById("mensaje").innerHTML = "Extensión no válida, selecione una imagen JPG";
                document.getElementById("image").src = "assets/images/sinfoto.png";
                $("#file").val('');

            } else {
                if (updateSize('file') < 2097152) {
                    document.getElementById("image").src = e.target.result;
                } else {
                    document.getElementById("image").src = "assets/images/sinfoto.png";
                    $("#file").val('');
                    document.getElementById("mensaje").innerHTML = 'ERROR, IMAGEN SUPERA LOS 2MB';
                }
            }


        };
        reader.readAsDataURL(this.files[0]);
    };
    var isJpg = function (name) {
        return name.match(/jpg$/i)
    };
    var isPng = function (name) {
        return name.match(/png$/i)
    };

</script>
<script>
    document.getElementById("medida").value = document.getElementById("txtmedida").value;
    var tipo = document.getElementById("prod_insu").value;
    if (tipo != null) {
        if (tipo == 'PRODUCTO') {
            document.getElementById("chkproducto").checked = true;
        } else if (tipo == 'INSUMO') {
            document.getElementById("chkinsumo").checked = true;
        } else if (tipo == 'PRODUCTO E INSUMO') {
            document.getElementById("chkproducto").checked = true;
            document.getElementById("chkinsumo").checked = true;
        } else {
            document.getElementById("chkproducto").checked = false;
            document.getElementById("chkinsumo").checked = false;
        }
    }
</script>
<style>
    .checkbox label:after, 
    .radio label:after {
        content: '';
        display: table;
        clear: both;
    }

    .checkbox .cr,
    .radio .cr {
        position: relative;
        display: inline-block;
        border: 1px solid #a9a9a9;
        border-radius: .25em;
        width: 1.3em;
        height: 1.3em;
        float: left;
        margin-right: .5em;
    }

    .radio .cr {
        border-radius: 50%;
    }

    .checkbox .cr .cr-icon,
    .radio .cr .cr-icon {
        position: absolute;
        font-size: .8em;
        line-height: 0;
        top: 50%;
        left: 20%;
    }

    .radio .cr .cr-icon {
        margin-left: 0.04em;
    }

    .checkbox label input[type="checkbox"],
    .radio label input[type="radio"] {
        display: none;
    }

    .checkbox label input[type="checkbox"] + .cr > .cr-icon,
    .radio label input[type="radio"] + .cr > .cr-icon {
        transform: scale(3) rotateZ(-20deg);
        opacity: 0;
        transition: all .3s ease-in;
    }

    .checkbox label input[type="checkbox"]:checked + .cr > .cr-icon,
    .radio label input[type="radio"]:checked + .cr > .cr-icon {
        transform: scale(1) rotateZ(0deg);
        opacity: 1;
    }

    .checkbox label input[type="checkbox"]:disabled + .cr,
    .radio label input[type="radio"]:disabled + .cr {
        opacity: .5;
    }
</style>   
