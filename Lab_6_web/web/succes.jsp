<%@ page import="webubb.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: Tudor
  Date: 21.05.2020
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Images page</title>
    <style>
    </style>
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/ajax-utils.js"></script>
</head>
<body>
<%! User user; %>
<%  user = (User) session.getAttribute("user");
    if (user != null) {

%>
        <br>
<div id="all-page-container" style="display: block; background-image: linear-gradient(to right, white, powderblue, white); justify-content: center;">
<div style="display: flex; margin: 0 auto; width: 60%">
    <button id="showAllImagesButton" style="padding: 10px; margin: 10px; color: lightcoral; font-size: 20px; ">Show all images</button>
    <input type="text" style="margin:10px;" id="n-most-popular" placeholder="Show how many?">
    <button id="show-most-popular-button" style="padding: 10px; color: lightcoral; font-size: 20px;">Show most popular images</button>
</div>

        <br/>
        <p><button id="getReviewsButton" type="button" style="display: none">Get Reviews</button></p>
        <section><div id="reviews-container"></div></section>
        <div id="all-images-container" style="display: flex; justify-content: center;  height: 800px; overflow-y: auto;">
            <div id="single-image-container" style="padding: 2%; margin: auto; "></div>
        </div>
        <section id="add-image-section" style="margin: 0 auto; width: 60%">
            <label>Add image url: </label><input type="text" id="image-source">
            <label>Description: </label><input type="text" id="description">
            <button id="add-image-button"  type="button" style="padding: 10px; color: lightcoral; font-size: 20px; border-radius: 5px">Add image</button>
        </section>

        <script>
            $(document).ready(function(){
                $("#getReviewsButton").click(function() {
                    getUserReviews(<%= user.getId() %>, function(reviews) {
                        console.log(reviews);
                        // $("#-table").html("");
                        $("#reviews-container").html("");
                        $("#reviews-container").append("<div style='background-color=red'>Id: , reviewer id: , image id: , stars; </div>");

                        for(var rev in reviews){
                            $("#reviews-container").append("<br>" + reviews[rev].id);
                            $("#reviews-container").append("<br>" + reviews[rev].user_id);
                            $("#reviews-container").append("<br>" + reviews[rev].image_id);
                            $("#reviews-container").append("<br>" + reviews[rev].stars);
                        }

                    })
                });
                $("#showAllImagesButton").click(function () {
                    getAllImages(function (images) {
                        console.log(images);
                        $("#single-image-container").html("");
                        $("#single-image-container").append("Images List:");
                        // images_id = {};
                        for (var img in images){
                            var image_id = images[img].id;
                            if(images[img].image != null) {
                                $("#single-image-container").append("<h3>" + images[img].description + "</h3>");
                                $("#single-image-container").append("<img src='" + images[img].image + "' style='width: 80%%; height: auto; padding: 10%; margin: auto;'>");

                                if(images[img].user_id === <%=user.getId()%>){
                                    $("#single-image-container").append("<button id='delete-image-button-" + images[img].id + "' style='background-color: lightcoral; color: white; padding:10px;'>Delete Image</button>");

                                    $("#delete-image-button-" + images[img].id).click(function () {
                                        deleteImage(images[img].id, function () {
                                            $("#single-image-container").html("");
                                            $("#single-image-container").append("Image deleted successfully!");
                                        })
                                });
                                }
                                else{
                                    $("#single-image-container").append("<input type='number' id='add-review-" + images[img].id + "' min='1' max='10' placeholder='Give it a rating (1 - 10)' style='width: 100px; height: auto; padding: 1%;'>");
                                    $("#single-image-container").append("<button data-id = '" + images[img].id + "' id='review-image-button-" + images[img].id + "' style='background-color: lightblue; color: white; padding:10px;'>Review Image</button>");
                                    $("#review-image-button-" + images[img].id).click(function (e) {
                                        var current_picture_id = $(e.target).data("id");
                                        addReview(<%=user.getId()%>, current_picture_id, $("#add-review-" + current_picture_id ).val());
                                        $("#single-image-container").html("");
                                        $("#single-image-container").append("<h3> " + images[img].description + "</h3>");
                                        $("#single-image-container").append("<img src='" + images[img].image + "' style='width: 80%; height: auto; padding: 10%; margin: auto;'>");
                                        $("#single-image-container").append("<h3>Thanks for your review</h3>");
                                    })
                                }
                            }
                        }

                    });
                });


            });
            $("#add-image-button").click(function () {
                addImage(
                    <%= user.getId() %>,
                    $("#image-source").val(),
                    $("#description").val(),
                    function (response) {
                        $("#add-image-section").html(response);
                    }
                )
            });
            $("#show-most-popular-button").click(function () {
                showMostPopular(
                    $("#n-most-popular").val(),
                    function (images) {
                    console.log(images);
                    $("#single-image-container").html("");
                    $("#single-image-container").append("Images List:");

                    for (var img in images){
                        if(images[img].image != null) {
                            $("#single-image-container").append("<h3>" + images[img].description + "</h3>");
                            $("#single-image-container").append("<h3>" + images[img].review + "</h3>");
                            $("#single-image-container").append("<img src='" + images[img].image + "' style='width: 80%%; height: auto; padding: 10%; margin: auto;'>");
                        }
                    }

                });
            });
        </script>

</div>
<%
    }
%>

</body>
</html>