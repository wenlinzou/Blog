function $(inputid) {
	return document.getElementById(inputid);
}
function showAddBtn() {
	var nodediv = $('showAddCategoryDiv');
	if (nodediv.style.display == "none") {
		nodediv.style.display = "block";
	} else {
		nodediv.style.display = "none";
	}
}