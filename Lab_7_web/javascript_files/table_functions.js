$(document).ready( function(){
    console.log("I got here!");
    var lastSearch = "";
    
    showAll();
    addEmployee();
    search_position();
    remove_employee();
    updateEmployee();
});

function showAll(){
    $.ajax({
        // initialise the table with all the employees in the list
        url: "php_files/get_all.php",
        type: 'GET',
        action: 'getAll',
        success: (output) => {
            // listEmployees(output);
            // console.log(output);
            // console.log( output);
            listEmployees(output);
        }
    });
}

function listEmployees(data){
    let firstIndex = data.indexOf('{');
    let secondIndex = data.indexOf('}');
    while (data.length >7){
        showEmployee(
            JSON.parse(
                data.substring(
                    data.indexOf('{'),
                    data.indexOf('}')+1
                ))
        );
        data = data.substring(data.indexOf('}')+1);
    }
}


function showEmployee(json_array){
    let container = document.createElement("div");
    let id = document.createElement("p");
    let f_name = document.createElement("p");
    let l_name = document.createElement("p");
    let position = document.createElement("p");
    let salary = document.createElement("p");
    let remove_button = document.createElement("button");

    id.innerHTML = json_array['id'];
    f_name.innerHTML = json_array['f_name'];
    l_name.innerHTML = json_array['l_name'];
    position.innerHTML = json_array['position'];
    salary.innerHTML = json_array['salary'];
    remove_button.innerHTML = "remove";
    $(remove_button).attr("class", "button");

    $(container).append(id);
    $(container).append(f_name);
    $(container).append(l_name);
    $(container).append(position);
    $(container).append(salary);
    $(container).append(remove_button);
    

    document.getElementsByClassName("action_center_container")[0].appendChild(container);
    $(container).attr("class", "user_row");
    $(remove_button).click( () => remove_employee(json_array['id']));
}

function search_position(){
    $("#search_button").click(function(){
        let search_text = $("#search_text").val();
        
        console.log("search text = ", search_text);

        if (search_text.length > 0){
            $.ajax({
                // initialise the table with all the employees in the list
                url: "php_files/search_position.php",
                type: 'GET',
                data:{
                    search_position: search_text
                },
                success: (output) => {
                    console.log(output);
                    emptyTable();
                    listEmployees(output);
                    let last_search = $("#last_search")[0];
                    last_search.innerHTML = "last search: " + search_text;
                }
            });
        }
        else {
            emptyTable();
            showAll();
        }
    }
    );
}


function emptyTable(){

    var child = document.getElementsByClassName("user_row");
    console.log(child);

    var parent = document.getElementsByClassName("action_center_container")[0];
    while (child.length > 0){
        parent.removeChild(child[0]);
    }

}

function addEmployee(){
    $("#add_button").click(function(){
        console.log("clicked button");
        let f_name = $("#f_name").val();
        let l_name = $("#l_name").val();
        let position = $("#position").val();
        let salary = $("#salary").val();

        $.ajax({
            url: "php_files/add.php",
            type: 'POST',
            data : {
                f_name_get: f_name,
                l_name_get: l_name,
                position_get: position,
                salary_get: salary
            },
            success: (output) => {
                console.log(output);
                emptyTable();
                showAll();
            }
        });
    });

}

function updateEmployee(){
    $("#update_button").click(function(){
        console.log("clicked button");
        let id = $("#id_field")[0].value;
        let f_name = $("#f_name").val();
        let l_name = $("#l_name").val();
        let position = $("#position").val();
        let salary = $("#salary").val();

        $.ajax({
            url: "php_files/update.php",
            type: 'POST',
            data : {
                id_get: id, 
                f_name_get: f_name,
                l_name_get: l_name,
                position_get: position,
                salary_get: salary
            },
            success: (output) => {
                console.log(output);
                emptyTable();
                showAll();
            }
        });
    });
}
function remove_employee(id){
    // $("#remove_button").click(function(){
        // let id = $("#id_field")[0].value;

    $.ajax({
        url: "php_files/remove.php",
        type: 'POST',
        data: {
            id_get: id
        },
        success: (output)=>{
            console.log("Success remove");
            console.log(output);
            emptyTable();
            showAll();
        },
        error: (e) => {
            console.error(e);
        }
     }
    );
}
