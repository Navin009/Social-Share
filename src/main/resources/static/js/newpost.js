document.addEventListener('keypress', function (e) {
	if (e.key === 'Enter') {
		e.preventDefault();
		return false;
	}
});

const tagInput = document.getElementById('tag-input');
const tagList = document.getElementById('tags');

tagInput.addEventListener('keyup', function (e) {
	if (e.key === 'Enter') {
		tagList.value += tagInput.value + '\n';
		tagInput.value = '';
	}
});
