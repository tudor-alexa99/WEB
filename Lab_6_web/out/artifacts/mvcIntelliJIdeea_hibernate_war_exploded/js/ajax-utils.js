function getUserImages(userId, callbackFunction) {
	$.getJSON(
		"ImagesController",
		{action: 'getAllUser', userId: userId},
		callbackFunction
	);

}

function addReview(user_id, image_id, stars, callbackFunction) {
	$.get(
		"ReviewsController",
		{action: "addReview",user_id:user_id, image_id: image_id, stars: stars},
		callbackFunction
	);

}

function deleteImage(image_id, callbackFunction) {
	var result = confirm("Are you sure you want to delete this?");
	if (result) {
		$.get(
			"ImagesController",
			{action: "deleteImage", image_id: image_id},
			callbackFunction
		);
	}
}

function addImage(userId, imageSrc, description, callbackFunction) {
	var result = confirm("Confirm you want to add this image:");
	if (result) {
		$.get(
			"ImagesController",
			{
				action: "add",
				userId: userId,
				image: imageSrc,
				description: description
			},
			callbackFunction
		)
	}
}

function getAllImages(callbackFunction) {
	$.getJSON(
		"ImagesController",
		{action: 'getAll'},
		callbackFunction
	);

}

function getUserReviews(userId, callbackFunction) {
	$.getJSON(
		"ReviewsController",
		{action:'getAll', userId: userId},
		callbackFunction
	);
}

function showMostPopular(n_images, callbackFunction) {
	$.getJSON(
		"ImagesController",
		{action: 'getMostPopular', n_images:n_images},
		callbackFunction
	)
}


