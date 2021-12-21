function deletePost() {
	if (confirm('Are you sure you want to delete this post?')) {
		let postId = window.location.pathname.split('/')[2];
		let xhr = new XMLHttpRequest();
		xhr.open('DELETE', '/blog/delete/' + postId);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.onload = function () {
			if (xhr.status === 200) {
				window.location.href = '/';
			} else {
				alert('An error occurred');
			}
		};
		xhr.send();
	}
}
