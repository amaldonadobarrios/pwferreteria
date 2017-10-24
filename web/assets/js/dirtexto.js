// onkeypress="return solo_numeros(event)" 

function solo_numeros(e) { 
    tecla = (document.all) ? e.keyCode : e.which; 
    if (tecla==8) return true; 
    patron =/\d/; 
    te = String.fromCharCode(tecla); 
    
    return patron.test(te); 
} 
 
 function solo_alfanumerico(e) { 
	    tecla = (document.all) ? e.keyCode : e.which; 
	    if (tecla==8) return true;
	    if (tecla==32) return true;
	    if (tecla==40) return true;
	    if (tecla==41) return true;
	    if (tecla==45) return true;
	    if (tecla==241) return true;
	    if (tecla==209) return true;
	    if (tecla==211) return true; //�
	    if (tecla==243) return true; //�
	    if (tecla==193) return true; //�
	    if (tecla==225) return true; //�
	    if (tecla==201) return true; //�
	    if (tecla==233) return true; //�
	    if (tecla==218) return true; //�
	    if (tecla==250) return true; //�
	    if (tecla==205) return true; //�
	    if (tecla==237) return true; //�
	    patron =/[��;:.,"#@\w]/; 
	    te = String.fromCharCode(tecla); 
	    //te = te.toUpperCase();
		e.keyCode = te.charCodeAt(0);
	    return patron.test(te); 
	} 
 
 function solo_letras(e) { 
	    tecla = (document.all) ? e.keyCode : e.which; 
	    if (tecla==8) return true; 
	    if (tecla==32) return true;
	    if (tecla==211) return true; //�
	    if (tecla==243) return true; //�
	    if (tecla==193) return true; //�
	    if (tecla==225) return true; //�
	    if (tecla==201) return true; //�
	    if (tecla==233) return true; //�
	    if (tecla==218) return true; //�
	    if (tecla==250) return true; //�
	    if (tecla==205) return true; //�
	    if (tecla==237) return true; //�
	    patron =/[a-zA-�Z�������\s]/; 
	    te = String.fromCharCode(tecla); 
	    //te = te.toUpperCase();
		e.keyCode = te.charCodeAt(0);
	    return patron.test(te); 
	} 


 function fnConvertirMayuscula(txtinput)
 {
	return txtinput.toUpperCase();
 }
 
 

 function fnTablaFormato(dv_seccion_tabla)
 {
 	$(dv_seccion_tabla).DataTable({
            	   		"language": {
 				        		   "decimal":        "",
 				        		    "emptyTable":     "No existe datos disponibles en la Tabla",
 				        		    "info":           "Mostrando _START_ a _END_ de _TOTAL_ Registros",
 				        		    "infoEmpty":      "Mostrando 0 a 0 de 0 Registros",
 				        		    "infoFiltered":   "(filtrado de _MAX_ Registros totales)",
 				        		    "infoPostFix":    "",
 				        		    "thousands":      ",",
 				        		    "lengthMenu":     "Mostrar _MENU_ Registros",
 				        		    "loadingRecords": "Cargando...",
 				        		    "processing":     "Procesando...",
 				        		    "search":         "Buscar:",
 				        		    "zeroRecords":    "No se encontraron Registros",
 				        		    "paginate": {
 								        		        "first":      "Primera",
 								        		        "last":       "Ultima",
 								        		        "next":       "Siguiente",
 								        		        "previous":   "Anterior"
 								        		    },
 				        		    "aria": {
 								        		        "sortAscending":  ": Ordenar forma ascendente",
 								        		        "sortDescending": ": Ordenar forma descendente"
 								        	}
                }
         
         });

 }
 
 
 