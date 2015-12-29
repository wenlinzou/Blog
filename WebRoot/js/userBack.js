function $(inputid) {
	return document.getElementById(inputid);
}
function showAddBtn() {
	var nodediv = $('showAddDiv');
	if (nodediv.style.display == "none") {
		nodediv.style.display = "block";
	} else {
		nodediv.style.display = "none";
	}
}