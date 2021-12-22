function deletePost() {
	if (confirm('Are you sure you want to delete this post?')) {
		let postId = window.location.pathname.split('/')[2];
		let xhr = new XMLHttpRequest();
		xhr.open('DELETE', '/blog/delete/' + postId);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.onload = function () {
			if (xhr.status === 200) {
				alert('Post deleted');
				window.location.href = '/';
				location.reload();
			} else {
				alert('An error occurred');
			}
		};
		xhr.send();
	}
}

function updatePost() {
	if (confirm('Are you sure you want to update this post?')) {
		let postId = window.location.pathname.split('/')[2];
		window.location.href = '../updatepost/' + postId;
	}
}

function updateComment(commentId) {
	if (confirm('Are you sure you want to update this comment?')) {
		let xhr = new XMLHttpRequest();
		xhr.open('PUT', '/comment/update' + commentId);
		xhr.onload = function () {
			if (xhr.status === 200) {
				let comment = JSON.parse(xhr.responseText);
				document.getElementById('comment-id').value = comment.id;
				document.getElementById('comment-author').value = comment.author;
				document.getElementById('comment-content').value = comment.content;
				document.getElementById('comment-update').style.display = 'block';
				document.getElementById('comment-submit').style.display = 'none';
			} else {
				alert('An error occurred');
			}
		};
		xhr.send();
	}
}

function deleteComment() {
	if (confirm('Are you sure you want to delete this comment?')) {
		let commentId = window.location.pathname.split('/')[3];
		let xhr = new XMLHttpRequest();
		xhr.open('DELETE', '/blog/deletecomment/' + commentId);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.onload = function () {};
	}
}

let commentDiv = document.getElementsByClassName('comments')[0].firstElementChild;

if (commentDiv == null) {
	document.getElementsByClassName('comments')[0].style.display = 'none';
}
