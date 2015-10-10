package co.jp.souya.project1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

public class TokenizerTest {

	public final static char QUOTE = '\'';
	public final static char DOUBLE_QUOTE = '"';

	public TokenizerTest(String inputFileName)
		throws FileNotFoundException {
		FileReader fr = new FileReader(inputFileName);
		tokenizer = new StreamTokenizer(fr);
		tokenizer.resetSyntax();
		tokenizer.wordChars('0', '9');
		tokenizer.wordChars('a', 'z');
		tokenizer.wordChars('A', 'Z');
		tokenizer.wordChars('_', '_');
		tokenizer.whitespaceChars(' ', ' ');
		tokenizer.whitespaceChars('\t', '\t');
		tokenizer.whitespaceChars('\n', '\n');
		tokenizer.whitespaceChars('\r', '\r');
		tokenizer.quoteChar(QUOTE);
		tokenizer.quoteChar(DOUBLE_QUOTE);
		tokenizer.parseNumbers();
		tokenizer.eolIsSignificant(false);
		tokenizer.slashStarComments(true);
		tokenizer.slashSlashComments(true);
	}

	public void printToken() throws IOException {
		int token;
		while ((token = tokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
			switch (token) {
			case StreamTokenizer.TT_EOL:
				System.out.println("<EOL/>");
				break;
			case StreamTokenizer.TT_NUMBER:
				System.out.println("<number>" + tokenizer.nval + "</number>");
				break;
			case StreamTokenizer.TT_WORD:
				System.out.println("<word>" + tokenizer.sval + "</word>");
				break;
			case QUOTE:
				System.out.println("<char>" + tokenizer.sval + "</char>");
				break;
			case DOUBLE_QUOTE:
				System.out.println("<string>" + tokenizer.sval + "</string>");
				break;
			default:
				System.out.print("<token>" + (char)tokenizer.ttype + "</token>");
			}
		}
	}

	private StreamTokenizer tokenizer;

	public static void main(String[] args) throws Exception {

		args = new String[]{"C:\\Users\\hsatomi\\Desktop\\test\\aa.java"};

		if (args.length != 1) {
			System.err.println("Usage: TokenizerTest file");
			System.exit(1);
		}
		TokenizerTest tt = new TokenizerTest(args[0]);
		tt.printToken();
	}
}
