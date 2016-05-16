function ordina(tableId, cellIndex, cellType ){
	
		//alert("Start! tableId="+tableId);
		
		var table      = document.getElementById(tableId);
		var rowHeader  = table.rows[0];
		var orderBy    = rowHeader.cells[cellIndex].className;

		//alert("start="+orderBy);
			
		bubbleSort(table, cellIndex, cellType, orderBy , 1 )
		
		//alert("finito");
		
		//Cambio l'ordinamento
		if (orderBy=="asc") {
			rowHeader.cells[cellIndex].className="desc";
		}else {
			rowHeader.cells[cellIndex].className="asc";
		}
	}



function bubbleSort( table, cellIndex , cellType , orderBy , top ){
	
		var swap=false;
		var i = table.rows.length - 1;
		
		while(i>top){
			var rowUnder  = table.rows[i];
			var cellUnderValue=rowUnder.cells[cellIndex].innerHTML;
			
			var rowOver  = table.rows[i-1];
			var cellOverValue=rowOver.cells[cellIndex].innerHTML;
	
			if (cellType==1) {
				cellUnderValue=parseFloat( cellUnderValue  )	;
				cellOverValue=parseFloat( cellOverValue  )	;
			}
	
			var swapTest=false;
			if (orderBy=="asc") {
				swapTest=cellUnderValue < cellOverValue;
			}else {
				swapTest=cellUnderValue > cellOverValue;
			}
			
			if ( swapTest ) {
				swap=true;
	
			    var htmlOver  =table.rows[i-1].innerHTML;
			    var htmlUnder =table.rows[i].innerHTML;
	
			    rowUnder.innerHTML =htmlOver;
			    rowOver.innerHTML  =htmlUnder
			}
			i--;
		}
	
		if (swap) {
			bubbleSort(table, cellIndex , cellType  , orderBy , top + 1 );
		}
	}
