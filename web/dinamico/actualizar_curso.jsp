<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-header">
    <h1>
        CURSOS
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            Actualizacion de Cursos
        </small>
    </h1>
</div><!-- /.page-header -->
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <jsp:useBean id="cursox" class="model.dto.Curso"/>
        <form action="Curso" method="POST" class="form-horizontal" role="form">
            <input name="action" type="hidden" value="actualizarCurso">
            <input name="idcurso" type="hidden" value="${curso.idcurso}">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Nombre de Curso</label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="nombre" value="${curso.nombre}" placeholder="Nombre de Curso" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Tipo de Curso </label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="tipo" value="${curso.tipo}" placeholder="Tipo de Curso" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Descripcion </label>
                <div class="col-sm-9">
                    <textarea id="form-field-1" name="descripcion" value="${curso.descripcion}" class="autosize-transition col-xs-10 col-sm-5"></textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Fecha de Inicio </label>
                <div class="col-sm-9">
                    <div class="input-group col-sm-3">
                        <input class="form-control date-picker" id="id-date-picker-1" type="text" name="fechaini" 
                               data-date="<fmt:formatDate value="${curso.fechaini}" pattern="dd-MM-yyyy"/>"  data-date-format="dd-mm-yyyy"/>
                        <span class="input-group-addon">
                            <i class="fa fa-calendar bigger-110"></i>
                        </span>
                    </div>
                </div>
            </div>
                        
                        <div class="input-append date" id="dp3" data-date="12-02-2012" data-date-format="dd-mm-yyyy">
                        
                        

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Fecha de Fin </label>
                <div class="col-sm-9">
                    <div class="input-group col-sm-3">
                        <input class="form-control date-picker" id="id-date-picker-1" data-date="<fmt:formatDate value="${curso.fechafin}" pattern="dd-MM-yyyy"/>" data-date-format="dd-mm-yyyy" name="fechafin" type="text" />
                        <span class="input-group-addon">
                            <i class="fa fa-calendar bigger-110"></i>
                        </span><
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Requisitos </label>
                <div class="col-sm-9">
                    <textarea id="form-field-1" name="requisitos" value="${curso.requisito}" class="autosize-transition col-xs-10 col-sm-5"></textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Vacantes </label>
                <div class="col-sm-9">
                    <input type="text" id="form-field-1" name="vacantes" value="${curso.vacante}" placeholder="Cantidades de Vacantes" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="space-4"></div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="submit">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        Actualizar
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
