open a file
	$ vi filename
open file to specific line number
	$ vi +90 filename
open file in read only mode
	$ vi -R filename
	$ view filename



to quit:
	:q
	:q!
* when in command mode, to save and exit out of vi type ZZ. ZZ is equivalent to :wq

command mode (default mode)
	i - press i for insert mode
	ESC - to go back into command mode
        h - move left one character
	j - move down one character
	k - move up one character
	l - move right one character
        dd - used for deleting line
	3dd - delete next three lines
	u - for undo
	:u - for undo last action
	U - for undo the entire line.
	yy - for copying line
	3yy - copy three lines
	p - for pasting line
	:r filename - for reading text from another file
	/search-text - for searching top to bottom
	?search-text - for searching bottom to top
	n - for following the search text top to bottom
	N - for following the search text bottom to top
	r - for replacing the text under curser
	w - position cursor to the next word
	b - position cursor to previous word
	:w - for saving the current file
	:w <filename> - will save the data to new file named filename.
	:w! - for saving the file forcefully
	:wq - for saving and exiting the file
	:wq! - for saving and exiting the file forcefully
	:history - for history
	$ - for end of the line	
	^ - for beginning of the line
	( - positions cursor to beginning of current sentence
	) - positions cursor to beginning of next sentence	
	:1 - for the first line
	1G - Move to first line of the file
	G - for the last line of the file
	nG - Move to nth line of the file
	:n - for the nth line number
	. - for repeating the command
	:!ls -ltrh - for executing the external command from vi
	:%s/abc/xyz/g - for replacing xyz in place of abc from current file
	:r !ls -ltrh - for reading output of ls -ltrh into the current file.
	
	fc - Move forward to c
	Fc - Move back to c
	H - Move to top of screen
	nH - Move to nth line from the top of the screen
	M - Move to the middle of the screen
	L - Move to bottom of the screen
	nL - Move to nth line from the bottom of the screen
	

	:sp - for horizontally splitting the current file
	:vsp - for vertically splitting the current file.
	:e abcd.csv - edit abcd.csv file
	ctrl+w twice can jump around splitted windows
	
Other Control Commands:
	CTRL+d	: Move forward 1/2 screen
	CTRL+f	: Move forward one full screen
	CTRL+u	: Move backward 1/2 screen
	CTRL+b	: Move backward one full screen
	CTRL+e	: Moves screen up one line
	CTRL+y	: Moves screen down one line
	CTRL+u	: Moves screen up 1/2 page
	CTRL+d	: Moves screen down 1/2 page
	CTRL+b	: Moves screen up one page
	CTRL+f	: Moves screen down one page
	CTRL+I	: Redraws screen
	CTRL+R  : to Redo the commands (undo the undo's using u or U)	
	SHIFT+g : End of the file.
	Capital G : End of the file (The caps lock 'g' also works.)
	gg      : Start of the file.
	496G    : Takes the cursor to line number 496
	
	:set number : shows the line number.
	:set nonumber : hides the line number.

Execute External Command:
	Type  :!  followed by an external command to execute that command. Ex: :!ls
	
	
The Search Command:
	/<search> : search for the phrase in the forward editor.
	n         : to search the phrase again.
	N         : to search the phrase in opposite direction.
	?<search> : search for the phrase in the backward direction.
	CTRL+o    : To go back to where you came from (CTRL+ letter o). Repeat to go back further.
	CTRL+I    : Goes forward.
	Note: When the search reaches the end of the file. it will continue at the start, unless the 'wrapscan' option has been reset.
	%         : Place the cursor on any (,[, {, }, ], ) and press % to move the cursor to the other matching bracket.
	
	:s/old/new/g    : to substitute 'new' for 'old'.
	:s/thee/the/g   : Adding the  g  flag means to substitute globally in the line, change all occurrences of "thee" in the line.
	:#,#s/old/new/g : where #,# are the line numbers of the range of lines where the substitution is to be done.
        :%s/old/new/g   : to change every occurrence in the whole file.
        :%s/old/new/gc  : to find every occurrence in the whole file, with a prompt whether to substitute or not.

	
Editing Files: To edit the file, you need to be in the insert mode. There are many ways to enter insert mode from the command mode −
	i	: Inserts text before current cursor location.
	I	: Inserts text at beginning of current line.
	a	: Inserts text after current cursor location.
	A	: Inserts text at end of current line.
	o	: Creates a new line for text entry below cursor location.
	O	: Creates a new line for text entry above cursor location.
	
Deleting Characters: Here is the list of important commands which can be used to delete characters and lines in an opened file − 		
	x	: Deletes the character under the cursor location.
	X	: Deletes the character before the cursor location.
	dw	: Deletes from the current cursor location to the next word, EXCLUDING its first character.
	d^	: Deletes from current cursor position to the beginning of the line.
	d$	: Deletes from current cursor position to the end of the line, INCLUDING the last character.
	D	: Deletes from the cursor position to the end of the current line.
	dd	: Deletes the line the cursor is on.
	2dd : Deletes two lines from the cursor.
	de  : Deletes from the current cursor location to the end of the current word, INCLUDING the last character.
	d2w : Deletes the two words forward from the cursor location. the number 2 can be variable.
	p   : to put previously deleted text after the cursor.
	ce  : deletes the characters till the end of the word from the cursor and places you in insert mode.
	c$  : deletes the entire line from the cursor position. [c: is called Change Operator works the same way as delete].
	c[number] motion : motions are the same as w(word) and $(end of the line.), d(delete)
	c3d : deletes 3 lines from the cursor position.
	
	

Using a count for a motion -
	2w  : to move the cursor two words forward.
	3e  : to move the cursor to the end of the third word forward.
	0   : to move to the start of the line.
	$   : end of the line.
	

SELECTING TEXT TO WRITE
            ** To save part of the file, type  v  motion  :w FILENAME **
   1. Move the cursor to this line.
 
   2. Press  v  and move the cursor to the fifth item below.  Notice that the
      text is highlighted.
 
   3. Press the  :  character.  At the bottom of the screen  :'<,'> will appear.
 
   4. Type  w TEST  , where TEST is a filename that does not exist yet.  Verify
      that you see  :'<,'>w TEST  before you press <ENTER>.
 
   5. Vim will write the selected lines to the file TEST.  Use  :!dir  or  :!ls
      to see it.  Do not remove it yet!  We will use it in the next lesson.
 
 NOTE:  Pressing  v  starts Visual selection.  You can move the cursor around
        to make the selection bigger or smaller.  Then you can use an operator
        to do something with the text.  For example,  d  deletes the text.

Lesson 5.4: RETRIEVING AND MERGING FILES


       ** To insert the contents of a file, type  :r FILENAME  **

  1. Place the cursor just above this line.

NOTE:  After executing Step 2 you will see text from Lesson 5.3.  Then move
       DOWN to see this lesson again.

  2. Now retrieve your TEST file using the command   :r TEST   where TEST is
     the name of the file you used.
     The file you retrieve is placed below the cursor line.

  3. To verify that a file was retrieved, cursor back and notice that there
     are now two copies of Lesson 5.3, the original and the file version.

NOTE:  You can also read the output of an external command.  For example,
       :r !ls  reads the output of the ls command and puts it below the 
       cursor.

  4. :r !dir  reads the output of the dir command and puts it below the
      cursor position.


-----------------------------
Lesson 6.1: THE OPEN COMMAND
----------------------------
$ vimtutor



* http://www.tutorialspoint.com/unix/unix-vi-editor.htm
* https://www.cs.colostate.edu/helpdocs/vi.html
* https://www.cs.rit.edu/~cslab/vi.html
* https://www.ccsf.edu/Pub/Fac/vi.html
* https://kb.iu.edu/d/afdc
* http://cc.iiti.ac.in/vicom.pdf
* http://www.computerhope.com/unix/uvi.htm
* http://www.lagmonster.org/docs/vi.html
* http://www.radford.edu/~mhtay/CPSC120/VIM_Editor_Commands.htm
* http://www.atmos.albany.edu/daes/atmclasses/atm350/vi_cheat_sheet.pdf

