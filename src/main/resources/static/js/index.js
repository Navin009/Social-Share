let searchInput = document.getElementById('search-input');

searchInput.addEventListener('keydown', (e) => {
	if (e.code === 'Enter') {
		let searchTerm = searchInput.value;
		if (searchTerm.length > 0) {
			let url = new URL(window.location);
			url.searchParams.append('search', searchTerm);
			window.location.href = url;
		}
	}
});

function openPost(postId) {
	window.location.href = `/blog/${postId}`;
}

function prevPage() {
	const url = new URL(window.location);
	let start = url.searchParams.get('start');
	let limit = url.searchParams.get('limit');
	url.searchParams.set('start', Number(start) - Number(limit));
	window.location.href = url;
}

function nextPage() {
	const url = new URL(window.location);
	let start = url.searchParams.get('start');
	let limit = url.searchParams.get('limit');
	url.searchParams.set('start', Number(start) + Number(limit));
	window.location.href = url;
}

let availableTags = ['Java', 'C++'];
let tagsList = [];
document.getElementById('tag-input').addEventListener('keydown', (e) => {
	if (e.code === 'Enter' && e.target.value.length > 0) {
		let tagBlock = document.getElementById('tag-block');
		let tagIndex = availableTags.indexOf(e.target.value);
		if (tagIndex !== -1) {
			tagBlock.innerHTML += `<span class='tag'>${e.target.value}</span>`;
			tagsList.push(availableTags[tagIndex]);
			let url = new URL(window.location);
			url.searchParams.append('tags', e.target.value);
			window.location.href = url;
		}
		e.target.value = '';
	}
});

let tagBlock = document.getElementById('tag-block');
window.addEventListener('load', function (e) {
	for (let i = 0; i < tagsList.length; i++) {
		tagBlock.innerHTML = `<span class='tag'>${tagsList[i]}</span>`;
	}
});

function searchSort() {
	let sortOrder = $('#sort-order').val();
	let sortField = $('#sort-field').val();
	let url = new URL(window.location);
	url.searchParams.set('sortField', sortField);
	url.searchParams.set('order', sortOrder);
	window.location.href = url;
}
