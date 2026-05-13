function onlyNumbers(evt) {
	var keyCode = event.keyCode;
	if( keyCode == 8 // back space
		|| keyCode == 13 // enter
		|| keyCode == 16 // enter
		|| (keyCode >= 35 && keyCode <= 40) // home, end, arrows
		|| keyCode == 46) { // Delete
		this.$doKeyDown_(evt);
	} else if ( !evt.shiftKey &&  // only do without shift key
		(keyCode >= 48 && keyCode <= 57)
		|| (keyCode >= 96 && keyCode <= 105)) {// number range
		this.$doKeyDown_(evt);
	} else {
		evt.stop();
		return;
	}
}

