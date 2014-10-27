// this little class processes a printed version of a BST to see if it is balanced
// enough to be an AVL tree. We assume that an empty tree is encoded as "()". And
// we assume that a non-empty tree is encoded as "(<left tree> value <right tree>)".
// So, for example, "(((()0(()1()))2((()3())4(()5())))6())" encodes one tree, and
// "((()0(()1()))2(()3()))" encodes another tree. This second one has a 2 at the
// root, with a 3 to the right of the root and a 1 to the left of the root. To the
// left of the one is a 0. Everything else is empty.
class IsBalanced {

	// returns the height of the tree encoded in the string; throws an exception if it
	// is not balanced
	int checkHeight(String checkMe) {

		// find the position of the first '('
		int startPos = 0;
		while (checkMe.charAt(startPos) != '(')
			startPos++;

		// this is the depth at the left
		int leftDepth = -1;

		// and the depth at the right
		int rightDepth = -1;

		// now, find where the number of parens on each side is equal
		int lParens = 0;
		int rParens = 0;
		for (int i = startPos + 1; i < checkMe.length(); i++) {

			// count each ) or ( in the string
			if (checkMe.charAt(i) == '(')
				lParens++;
			else if (checkMe.charAt(i) == ')')
				rParens++;

			// if the number of ) and ( are equal
			if (lParens == rParens && lParens > 0) {

				// in this case, we just completed the left tree
				if (leftDepth == -1) {
					leftDepth = checkHeight(checkMe.substring(startPos + 1, i + 1));
					startPos = i + 1;
					lParens = 0;
					rParens = 0;

					// in this case, we just completed the right tree
				} else {
					rightDepth = checkHeight(checkMe.substring(startPos + 1, i + 1));
					startPos = i + 1;
					break;
				}
			}
		}

		// check to see if this is not a valid AVL tree
		if (leftDepth - rightDepth >= 2 || leftDepth - rightDepth <= -2)
			throw new RuntimeException("this tree is not balanced! Left: " + leftDepth + " Right: "
					+ rightDepth);

		// search for the closing )
		while (checkMe.charAt(startPos) != ')')
			startPos++;

		// and we are outta here
		if (leftDepth > rightDepth)
			return leftDepth + 1;
		else
			return rightDepth + 1;

	}


}
