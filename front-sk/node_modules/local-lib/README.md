# local-lib

Node.js `require` your local library files/modules easily from anywhere in
your project without having to navigate directories.

**painful**

```javascript
	var db = require('../../../../lib/db');
```

**the local-lib way**

```javascript
	var db = require('local-lib').db;
```


## Installation

	npm install local-lib


## Usage

1. add a `lib` or `library` folder in your project root.
2. add `.js` files, `.json` files, or module folders to your library
3. access your library easily using global require

Library folders should contain an `index.js` to export your library,
or a `package.json` file with a `main` directive to point to your main file.


## Example

**project structure**

	project root
	├── lib
	│   ├── utils
	│   │   └── index.js
	│   └── db.js
	└── app.js


**app.js**

```javascript
	var lib = require('local-lib');
	var db = lib.db;
	var utils = lib.utils;
```

or

```javascript
	var db = require('local-lib').db;
```
