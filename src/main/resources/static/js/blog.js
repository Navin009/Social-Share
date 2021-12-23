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

function updateCommentToDatabase(commentId, commentElement, buttonElement) {
	let commentData = commentElement.value;
	let xhr = new XMLHttpRequest();
	xhr.open('PUT', '../updatecomment/' + commentId);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.onload = function () {
		if (xhr.status === 200) {
			commentElement.innerHTML = commentData;
			commentElement.readOnly = true;
			commentElement.style.border = 'none';
			buttonElement.innerHTML = 'Update';
			buttonElement.onclick = () => updateComment(commentId, buttonElement);
			alert('Comment updated');
			window.location.reload();
		} else {
			alert('An error occurred');
		}
	};
	xhr.send(commentData);
}

function updateComment(commentId, buttonElement) {
	if (confirm('Are you sure you want to update this comment?')) {
		let commentElement = buttonElement.parentNode.parentNode.getElementsByTagName('textarea')[0];
		commentElement.readOnly = false;
		commentElement.style.border = '1px solid black';
		buttonElement.innerHTML = 'Save Changes';
		buttonElement.onclick = () => updateCommentToDatabase(commentId, commentElement, buttonElement);
	}
}

function deleteComment(commentId) {
	if (confirm('Are you sure you want to delete this comment?')) {
		let xhr = new XMLHttpRequest();
		xhr.open('DELETE', '../deletecomment/' + commentId);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.onload = function () {
			if (xhr.status === 200) {
				alert('Comment deleted');
				window.location.reload();
			} else {
				alert('An error occurred');
			}
		};
		xhr.send();
	}
}

let commentDiv = document.getElementsByClassName('comments')[0].firstElementChild;

if (commentDiv == null) {
	document.getElementsByClassName('comments')[0].style.display = 'none';
}
