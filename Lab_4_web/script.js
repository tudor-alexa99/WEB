
function clickBurgerIcon() {
    // the function will be called from html when we click the burger icon
    // clicking it -> call the clickBurger( ) function
    // select the list that you want to modify (i. e. the nav list)
    let mainMenu = document.getElementById("main_menu_nav");
    if (mainMenu.style.display === "none") {
        mainMenu.style.display = "block";
    }
    else {
        mainMenu.style.display = "none";
    }
}

function clickBurgerItem() {
    let sublist = document.getElementsByClassName("submenu_item");
    for (i = 0; i< 3; i ++)
        if (sublist[i].style.display === "none"){
            sublist[i].style.display = "block";
        }
        else
            sublist[i].style.display = "none";
}

function clickPizzaItem() {
    let sublist = document.getElementsByClassName("submenu_item");
    for (i = 3; i< 6; i ++)
        if (sublist[i].style.display === "none"){
            sublist[i].style.display = "block";
        }
        else
            sublist[i].style.display = "none";
}

function clickPastaItem() {
    let sublist = document.getElementsByClassName("submenu_item");
    for (i = 6; i< 9; i ++)
        if (sublist[i].style.display === "none"){
            sublist[i].style.display = "block";
        }
        else
            sublist[i].style.display = "none";
}

function clickHotDogItem() {
    let sublist = document.getElementsByClassName("submenu_item");
    for (i = 9; i<12; i ++)
        if (sublist[i].style.display === "none"){
            sublist[i].style.display = "block";
        }
        else
            sublist[i].style.display = "none";
}

function clickDesertItem() {
    let sublist = document.getElementsByClassName("submenu_item");
    for (i = 12; i< 15; i ++)
        if (sublist[i].style.display === "none"){
            sublist[i].style.display = "block";
        }
        else
            sublist[i].style.display = "none";
}

