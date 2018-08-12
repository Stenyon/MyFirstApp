/* function getXmlHttpRequestObject() {
if (window.XMLHttpRequest) {
return new XMLHttpRequest(); //To support the browsers IE7+, Firefox, Chrome, Opera, Safari
} else if(window.ActiveXObject) {
return new ActiveXObject("Microsoft.XMLHTTP"); // For the browsers IE6, IE5
} else {
alert("Error due to old verion of browser upgrade your browser");
}
}

 *//* var data = new FormData();
data.append("columns_name", columns_name);
data.append("pwd", "password");
data.append("organization", "place");
data.append("requiredkey", "key"); */


//create table

var rcvReq = new XMLHttpRequest();
function create_table() {
	if (rcvReq.readyState == 4 || rcvReq.readyState == 0) {
		var new_table_name;
		var column_msg1 = prompt("Please enter new table name:");
		if (column_msg1 == null || column_msg1 == "") {

			throw new Error("Create table didn't succeed!");
		}
		/*
		 * else if(myarray.includes(column_msg)){ alert("This column name alerdy
		 * exist!"); throw new Error("This column name alerdy exist!"); }
		 */
		else {

			new_table_name = column_msg1;
			var data2 = new FormData();
			data2.append("new_table_name", new_table_name);

		}

		rcvReq.open("Post", 'Create_table', true);
		rcvReq.onreadystatechange = handleAlterContent;
		rcvReq.send(data2);
		location.reload();
	}
}
function handleAlterContent() {
	if (rcvReq.readyState == 4) {
		document.getElementById("create_table").innerHTML = rcvReq.responseText;
	}

}

//delete table 

var rcvReq = new XMLHttpRequest();
function delete_table() {
if (rcvReq.readyState == 4 || rcvReq.readyState == 0) {
	
	var column_msg = confirm("Are you sure you want to delete this table?");
	if (column_msg == null || column_msg == "") {
		throw new Error("Delete table didn't succeed!");
	} else {
		new_column_name = column_msg ;
	}
	
rcvReq.open("Post", 'Delete_table', true);
rcvReq.onreadystatechange = handleAlterContent; 
rcvReq.send(null);
} 
}
function handleAlterContent() {
if (rcvReq.readyState == 4) {
document.getElementById("Delete_table").innerHTML = rcvReq.responseText;
}
location.reload();
}

//add row 

var rcvReq = new XMLHttpRequest();
function add_row() {
if (rcvReq.readyState == 4 || rcvReq.readyState == 0) {
	
	if (confirm("All input data will be deleted. Did you save?")){
		
	}else{
		throw new Error("Add row didn't succeed!");
	}	
	
rcvReq.open("Post", 'Add_row', true);
rcvReq.onreadystatechange = handle_add_row; 
rcvReq.send(null);
} 
}
function handle_add_row() {
if (rcvReq.readyState == 4) {
document.getElementById("add_row").innerHTML = rcvReq.responseText;
}
location.reload();
}

//add column 

var rcvReq = new XMLHttpRequest();
function add_column() {
	
	if (confirm("All input data will be deleted. Did you save?")){
		
	}else{
		throw new Error("Add column didn't succeed!");
	}

	var new_column_name;

	var x = $('.table_head').length;
	var myarray = [];
	for (var i = 1; i <= x; i++) {
		myarray.push($('#col_' + i).val());
	}
			
	var column_msg = prompt("Please enter new column name:");
	if (column_msg == null || column_msg == "") {

		throw new Error("Add column didn't succeed!");
	} else if (myarray.includes(column_msg)) {
		alert("This column name alerdy exist!");
		throw new Error("This column name alerdy exist!");
	} else {
		new_column_name = column_msg;
	}

	if (rcvReq.readyState == 4 || rcvReq.readyState == 0) {

		rcvReq.open("Post", 'Add_column', true);
		rcvReq.onreadystatechange = handle_add_column;

		var data = new FormData();
		data.append("new_column_name", new_column_name);

		rcvReq.send(data);

		location.reload();
	}
}
function handle_add_column() {
	if (rcvReq.readyState == 4) {
		document.getElementById("add_column").innerHTML = rcvReq.responseText;
	}

}


// save

var rcvReq = new XMLHttpRequest();
function save_data() {
	if (rcvReq.readyState == 4 || rcvReq.readyState == 0) {

		rcvReq.open("Post", 'Save_data', true);
		rcvReq.onreadystatechange = handle_save_data;

		var column_msg = confirm("Are you sure you want to save this table?");
		if (column_msg == null || column_msg == "") {
			throw new Error("Save table didn't succeed!");
		} else {
			new_column_name = column_msg;
		}

		var data1 = new FormData();

		var x = $('.table_head').length;
		data1.append("NumberOfCol", x);
		for (var i = 1; i <= x; i++) {
			data1.append("col_" + i, $('#col_' + i).val());
		}

		var a = (document.getElementsByClassName('table_content')).length;
		var b = (document.getElementsByClassName('table_head')).length;
		var c = a / b;

		data1.append("NumberOfRow", c);
		for (var k = 1; k <= c; k++) {
			for (var j = 1; j <= b; j++) {

				if (isNaN($('#cell_' + j + '_' + k).val())) {

					data1.append("cell_" + j + "_" + k, "0");
				} else {

					data1.append("cell_" + j + "_" + k, $(
							'#cell_' + j + '_' + k).val());
				}
			}
		}

		rcvReq.send(data1);

		/* location.reload(); */
		window.setTimeout(function() {
			window.location.reload()
		}, 50);
	}
}
function handle_save_data() {
	if (rcvReq.readyState == 4) {
		document.getElementById("save_data").innerHTML = rcvReq.responseText;
	}

}


//sum

jQuery(function($) {
	$('input').on('change', function() {

		var elem = 0;
		var x = $('.table_head').length;
		for (var i = 1; i <= x; i++) {
			var sum = 0;
			$("input[name=cell_of_col_" + i + "]").each(function() {
				elem = parseFloat($(this).val());

				if (isNaN(elem)) {
					elem = 0;
				}
				/* console.log(x); */

				sum = sum + elem;
			})
			$("input[name=sum_of_cell_" + i + "]").val(sum);
		}
		/* console.log("input[name=sum_of_cell_"+i+"]"); */

	}).trigger('change');
});


//test


jQuery(function($) {
	$('.table_head').on('change', function() {
		var save_columns_name = "";
		var array = [];
		var x = $('.table_head').length;
		for (var i = 1; i <= x; i++) {

			save_columns_name = save_columns_name + "," + $('#col_' + i).val();
			array.push($('#col_' + i).val());
		}
		save_columns_name = save_columns_name.substring(1);
		/*
		 * console.log(save_columns_name);
		 * 
		 * console.log(array[1]);
		 */

		var a = (document.getElementsByClassName('table_content')).length;
		var b = (document.getElementsByClassName('table_head')).length;
		var c = a / b;
		var u = new FormData();
		u.append("nr_row", c);
		for (var k = 1; k <= c; k++) {
			for (var j = 1; j <= b; j++) {

				u.append("col_" + j + "_" + k, $('#col_' + j + '_' + k).val());
				/* u=("col_"+j+"_"+k, $('#col_' + j+'_'+k).val()); */
				/* console.log("col_"+j+"_"+k) */
			}
		}
		/* console.log(y) */

	}).trigger('change');
});

// droplist

function change_table() {

	var e = document.getElementById("dropdown");
	var table_name = e.options[e.selectedIndex].text;

	var v = new FormData();
	v.append("table_name", table_name);
	var rcvReq = new XMLHttpRequest();

	if (rcvReq.readyState == 4 || rcvReq.readyState == 0) {
		console.log(table_name)
		rcvReq.open("Post", 'Check', true);
		rcvReq.onreadystatechange = handle_change_table;
		rcvReq.send(v);

	}
}
function handle_change_table() {
	if (rcvReq.readyState == 4) {
		document.getElementById("change_table").innerHTML = rcvReq.responseText;
	}
	location.reload();
}	  
		 

