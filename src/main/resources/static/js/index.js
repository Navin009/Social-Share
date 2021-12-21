let searchInput = document.getElementById('search-input');

searchInput.addEventListener('keydown', (e) => {
	if (e.code === 'Enter') {
		let searchTerm = searchInput.value;
		if (searchTerm.length > 0) {
			window.location.href = `?search=${searchTerm}`;
		}
	}
});

function openPost(postId) {
	window.location.href = `/blog/${postId}`;
}

function prevPage() {
	const urlParams = new URLSearchParams(window.location.search);
	let start = urlParams.get('start');
	let limit = urlParams.get('limit');
	window.location.href = `?start=${Number(start) - Number(limit)}&limit=${limit}`;
}

function nextPage() {
	const urlParams = new URLSearchParams(window.location.search);
	let start = urlParams.get('start');
	let limit = urlParams.get('limit');
	window.location.href = `?start=${Number(start) + Number(limit)}&limit=${limit}`;
}
