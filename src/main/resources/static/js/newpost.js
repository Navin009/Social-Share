document.addEventListener("keypress", function (e) {
	if (e.key === "Enter") {
		e.preventDefault();
		return false;
	}
});

const tagInput = document.getElementById("tag-input");
const tagsList = document.getElementById("tags");
const tagBlock = document.getElementById("tag-block");
const tags = document.getElementById("tagsdata");

let availableTags = [];

let tagSet = new Set();
document.getElementById("tag-input").addEventListener("keyup", (e) => {
	let tagList = $("#tags-list");
	if (e.key === "Enter" && tagSet.has(tagInput.value) === false) {
		tagBlock.innerHTML += `  <span class='chip'>${tagInput.value}
                            <data value="${tagInput.value}" class="far fa-times-circle" onclick="removeTag(this)"></data>
                        	</span>`;
		tags.value += `${tagInput.value},`;
		tagSet.add(tagInput.value);
		tagInput.value = "";
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

function removeTag(tagElement) {
	tagSet.delete(tagElement.value);
	tagElement.parentNode.outerHTML = "";
	tags.value = tags.value.replace(tagElement.value + ",", "");
}
