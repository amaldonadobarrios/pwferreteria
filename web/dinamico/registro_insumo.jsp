<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-header">
    <h1>
        INSUMOS
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Registro de Insumos
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form action="Usuario" method="POST" class="form-horizontal" role="form">
            <input name="action" type="hidden" value="registrarUsuario">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Nombre del Insumo </label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="Nombre" placeholder="Nombre del Insumo" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Marca del Insumo </label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="marca" placeholder="Marca del Insumo" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Presentación </label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="presentacion" placeholder="Presentación del Insumo" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1">Unidad de Medida</label>
                <div class="col-sm-3">
                    <select class="chosen-select form-control" id="form-field-1" name="unidad" data-placeholder="Unidad de Medida">
                        <option value="">Seleccione</option>
                        <option value="1">Unidades</option>
                        <option value="2">Metro</option>
                        <option value="3">Metro Cuadrado(m2)</option>
                        <option value="4">Metro Cúbico (m3)</option>
                        <option value="5">Litro</option>
                        <option value="6">Kilogramos</option>
                    </select>
                </div>
            </div>
            <div class="container" align="center">
                <div class="col-md-6" align="center">
                    <div class="col-md-3 sidenav" align="center">
                    
                    <form  id="fileForm">
                        <label class="btn btn-primary btn-sm btn-block"  id="labelfoto" >	
                            <p style="font-size:18px;text-align:center;" class="fa fa-cloud-upload"></p><br>Subir Foto
                            <input type="file" name="file"  id="file"  style="opacity:0;cursor: pointer; color: white;" />
                        </label>
                    </form>
                    </div>
                    <div class="col-md-3 sidenav" align="center" id="divfoto" >
                    <img src="assets/images/sinfoto.png" width="200px" height="200px" class="img-circle"
                         name="image" id="image" />

                </div>
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
                    <button class="btn" type="reset">
                        <i class="ace-icon fa fa-undo bigger-110"></i>
                        Limpiar
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="hr hr-24"></div>

<div class="row">
    <div class="col-xs-12">
        <h3 class="header smaller lighter blue">Insumos Registrados</h3>

        <div class="clearfix">
            <div class="pull-right tableTools-container"></div>
        </div>
        <div class="table-header">
            Resultado para "Insumos Registrados"
        </div>

        <!-- div.table-responsive -->

        <!-- div.dataTables_borderWrap -->
        <div>
            <table id="dynamic-table" class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th class="center">Nº
                        </th>
                        <th>Nombre del Insumo</th>
                        <th>Marca</th>
                        <th>Presentación</th>
                        <th>Unidad de medida</th>
                        <th>Foto</th>
                        <th>x</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td> XXX</td>
                        <td> XXX</td>
                        <td> XXX</td>
                        <td> XXX</td>
                        <td>XXXX</td>
                        <td>XXXX</td>
                        <td>SELECCIONAR</td>
                    </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    document.getElementById("file").onchange = function ()
    {
        var reader = new FileReader();
        reader.onload = function (e)
        {
            var file = $('[name="file"]');
            var filename = $.trim(file.val());
            if (!(isJpg(filename) || isPng(filename))) {
                //document.getElementById("mensaje").innerHTML = "<p style='color:red;'>extensión no válida, selecione una imagen.</p>";
                document.getElementById("image").src = "assets/images/sinfoto.png";
                $("#file").val('');
            } else {
                // document.getElementById("mensaje").innerHTML ='';
                document.getElementById("image").src = e.target.result;
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

