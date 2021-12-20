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
