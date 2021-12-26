let searchInput = document.getElementById("search-input");

searchInput.addEventListener("keyup", (e) => {
	if (e.code === "Enter") {
		let searchTerm = searchInput.value;
		if (searchTerm.length > 0) {
			let url = new URL(window.location.origin);
			window.location.href = url + `?start=0&limit=10&search=${searchTerm}`;
		}
	}
});

function openPost(postId) {
	window.location.href = `/blog/${postId}`;
}

function prevPage() {
	const url = new URL(window.location);
	let start = url.searchParams.get("start");
	let limit = url.searchParams.get("limit");
	url.searchParams.set("start", Number(start) - Number(limit));
	window.location.href = url;
}

function nextPage() {
	const url = new URL(window.location);
	let start = url.searchParams.get("start");
	let limit = url.searchParams.get("limit");
	url.searchParams.set("start", Number(start) + Number(limit));
	window.location.href = url;
}

let availableTags = [];

document.getElementById("tag-input").addEventListener("keyup", (e) => {
	let tagList = $("#tags-list");

	if (e.code === "Enter" && e.target.value.length > 0) {
		let tagId = availableTags.filter((tag) => tag.name === e.target.value)[0].id;
		if (tagId !== -1) {
			let url = new URL(window.location);
			url.searchParams.append("tagId", tagId);
			window.location.href = url;
		}
		e.target.value = "";
	}
	if (e.target.value.length >= 2) {
		$.ajax({
			url: "/tag/search",
			type: "GET",
			data: {
				tag: e.target.value,
			},
			success: (data) => {
				availableTags = data.map((tag) => {
					return { id: tag.id, name: tag.name };
				});
				tagList.empty();
				availableTags.forEach((tag) => {
					tagList.append(`<option data-value="${tag.id}">${tag.name}</option>`);
				});
			},
		});
	}
});

let availableAuthors = [];

$("#author-input").keyup(function (e) {
	let authorList = $("#authors-list");

	if (e.key === "Enter" && e.target.value.length > 0) {
		debugger;
		let authorId = availableAuthors.filter((author) => author.name == e.target.value)[0].id;
		if (authorId !== -1) {
			let url = new URL(window.location);
			url.searchParams.set("authorId", authorId);
			window.location.href = url;
		}
		e.target.value = "";
	}

	if (this.value.length >= 2) {
		let xhr = new XMLHttpRequest();
		xhr.open("GET", "user/search/" + "?name=" + this.value);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
			if (this.readyState === 4 && this.status === 200) {
				let data = JSON.parse(this.responseText);
				availableAuthors = data.map((author) => {
					return { id: author.id, name: author.name };
				});
				authorList.empty();
				data.forEach((author) => {
					authorList.append(`<option data-value="${author.id}">${author.name}</option>`);
				});
			}
		};
		xhr.send();
	}
});

function searchSort() {
	let sortFieldOrder = $("#sort-field-order").val();
	let sortField = sortFieldOrder.split(",")[0];
	let sortOrder = sortFieldOrder.split(",")[1];
	let url = new URL(window.location);
	url.searchParams.set("sortField", sortField);
	url.searchParams.set("order", sortOrder);
	window.location.href = url;
}

function removeTag(tagId) {
	let url = new URL(window.location);
	let params = removeParam(url.search, "tagId", tagId);
	window.location.href = url.origin + params;
}

function removeAuthor(authorId) {
	let url = new URL(window.location);
	let params = removeParam(url.search, "authorId", authorId);
	window.location.href = url.origin + params;
}

function removeParam(sourceURL, key, value) {
	var params = sourceURL.replace("?", "").split("&");
	let removeParam = key + "=" + value;
	params = params.filter((param) => param !== removeParam);
	return "/?" + params.join("&");
}
