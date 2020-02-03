var fs = require('fs');

['lib', 'library'].forEach(function(path) {
	var libs = [];

	try {
		libs = fs.readdirSync(__dirname + '/../../' + path);
	}
	catch (e) {}

	libs.forEach(function(lib) {
		if (/^\./.test(lib)) return; // ignore dotfiles
		console.log('local-lib: loading library "%s"', lib);
		exports[lib.split('.')[0]] = require('../../' + path + '/' + lib);
	});
});
