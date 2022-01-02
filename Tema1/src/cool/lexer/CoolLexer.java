// Generated from C:/Users/nicol/Desktop/Facultate/Anul IV/CPL/Tema 1/Schelet + checker - IntelliJ/Tema1/src/cool/lexer\CoolLexer.g4 by ANTLR 4.9.2

    package cool.lexer;	

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, IF=2, THEN=3, ELSE=4, FI=5, LOOP=6, POOL=7, WHILE=8, CASE=9, 
		CASE_BRANCH=10, ESAC=11, LET=12, BOOL=13, CLASS=14, IN=15, INHERITS=16, 
		ISVOID=17, NEW=18, OF=19, NOT=20, TYPE_ID=21, NAME_ID=22, INTEGER=23, 
		STRING=24, DOT=25, AT=26, COMPLEMENT=27, MULT=28, DIV=29, PLUS=30, MINUS=31, 
		LE=32, LT=33, EQUAL=34, ASSIGN=35, COLON=36, SEMI=37, COMMA=38, LPAREN=39, 
		RPAREN=40, LBRACE=41, RBRACE=42, LINE_COMMENT=43, BLOCK_COMMENT=44, UNMATCHED_COMMENT=45, 
		WS=46, INVALID_CHARACTER=47;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "THEN", "ELSE", "FI", "LOOP", "POOL", "WHILE", "CASE", "CASE_BRANCH", 
			"ESAC", "LET", "BOOL", "CLASS", "IN", "INHERITS", "ISVOID", "NEW", "OF", 
			"NOT", "LOWERCASE", "UPPERCASE", "LETTER", "ID", "TYPE_ID", "NAME_ID", 
			"DIGIT", "INTEGER", "STRING", "DOT", "AT", "COMPLEMENT", "MULT", "DIV", 
			"PLUS", "MINUS", "LE", "LT", "EQUAL", "ASSIGN", "COLON", "SEMI", "COMMA", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "NEW_LINE", "LINE_COMMENT", "BLOCK_COMMENT", 
			"UNMATCHED_COMMENT", "WS", "INVALID_CHARACTER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'if'", "'then'", "'else'", "'fi'", "'loop'", "'pool'", "'while'", 
			"'case'", "'=>'", "'esac'", "'let'", null, "'class'", "'in'", "'inherits'", 
			"'isvoid'", "'new'", "'of'", "'not'", null, null, null, null, "'.'", 
			"'@'", "'~'", "'*'", "'/'", "'+'", "'-'", "'<='", "'<'", "'='", "'<-'", 
			"':'", "';'", "','", "'('", "')'", "'{'", "'}'", null, null, "'*)'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "IF", "THEN", "ELSE", "FI", "LOOP", "POOL", "WHILE", "CASE", 
			"CASE_BRANCH", "ESAC", "LET", "BOOL", "CLASS", "IN", "INHERITS", "ISVOID", 
			"NEW", "OF", "NOT", "TYPE_ID", "NAME_ID", "INTEGER", "STRING", "DOT", 
			"AT", "COMPLEMENT", "MULT", "DIV", "PLUS", "MINUS", "LE", "LT", "EQUAL", 
			"ASSIGN", "COLON", "SEMI", "COMMA", "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
			"LINE_COMMENT", "BLOCK_COMMENT", "UNMATCHED_COMMENT", "WS", "INVALID_CHARACTER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	    private static int MAX_LEN = 1024;

	    private void raiseError(String msg) {
	        setText(msg);
	        setType(ERROR);
	    }


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 27:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 48:
			BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 49:
			UNMATCHED_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 51:
			INVALID_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 String text = getText();
			                text = text.substring(1, text.length() - 1);
			                text = text.replace("\\n", "\n");
			                text = text.replace("\\t", "\t");
			                text = text.replace("\\b", "\b");
			                text = text.replace("\\f", "\f");
			                text = text.replace("\\r\n", "\r\n");
			                text = text.replaceAll("\\(?!\\)", "");
			                text = text.replaceAll("\\\\(?!\\\\)", "");

			                if (text.length() > MAX_LEN)
			                    raiseError("String constant too long");
			                else if (text.contains("\0"))
			                    raiseError("String contains null character");
			                else
			                  setText(text);

			        
			break;
		case 1:
			 raiseError("EOF in string constant"); 
			break;
		case 2:
			 raiseError("Unterminated string constant") ;
			break;
		}
	}
	private void BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			 skip(); 
			break;
		case 4:
			 raiseError("EOF in comment"); 
			break;
		}
	}
	private void UNMATCHED_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			 raiseError("Unmatched *)"); 
			break;
		}
	}
	private void INVALID_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			 raiseError("Invalid character: " + getText()); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\61\u0155\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a6\n\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3"+
		"\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3"+
		"\30\7\30\u00d5\n\30\f\30\16\30\u00d8\13\30\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\33\3\33\3\34\6\34\u00e3\n\34\r\34\16\34\u00e4\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\7\35\u00ed\n\35\f\35\16\35\u00f0\13\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\5\35\u00f9\n\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3"+
		"\"\3#\3#\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3)\3)\3*\3*\3+\3+\3,\3"+
		",\3-\3-\3.\3.\3/\3/\3\60\5\60\u0122\n\60\3\60\3\60\3\61\3\61\3\61\3\61"+
		"\7\61\u012a\n\61\f\61\16\61\u012d\13\61\3\61\3\61\5\61\u0131\n\61\3\61"+
		"\3\61\3\62\3\62\3\62\3\62\3\62\7\62\u013a\n\62\f\62\16\62\u013d\13\62"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\5\62\u0145\n\62\3\63\3\63\3\63\3\63\3\63"+
		"\3\64\6\64\u014d\n\64\r\64\16\64\u014e\3\64\3\64\3\65\3\65\3\65\5\u00ee"+
		"\u012b\u013b\2\66\3\4\5\5\7\6\t\7\13\b\r\t\17\n\21\13\23\f\25\r\27\16"+
		"\31\17\33\20\35\21\37\22!\23#\24%\25\'\26)\2+\2-\2/\2\61\27\63\30\65\2"+
		"\67\319\32;\33=\34?\35A\36C\37E G!I\"K#M$O%Q&S\'U(W)Y*[+],_\2a-c.e/g\60"+
		"i\61\3\2\7\3\2c|\3\2C\\\4\2C\\c|\3\2\62;\5\2\13\f\16\17\"\"\2\u015f\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\3k\3\2\2\2\5n\3\2\2"+
		"\2\7s\3\2\2\2\tx\3\2\2\2\13{\3\2\2\2\r\u0080\3\2\2\2\17\u0085\3\2\2\2"+
		"\21\u008b\3\2\2\2\23\u0090\3\2\2\2\25\u0093\3\2\2\2\27\u0098\3\2\2\2\31"+
		"\u00a5\3\2\2\2\33\u00a7\3\2\2\2\35\u00ad\3\2\2\2\37\u00b0\3\2\2\2!\u00b9"+
		"\3\2\2\2#\u00c0\3\2\2\2%\u00c4\3\2\2\2\'\u00c7\3\2\2\2)\u00cb\3\2\2\2"+
		"+\u00cd\3\2\2\2-\u00cf\3\2\2\2/\u00d6\3\2\2\2\61\u00d9\3\2\2\2\63\u00dc"+
		"\3\2\2\2\65\u00df\3\2\2\2\67\u00e2\3\2\2\29\u00e6\3\2\2\2;\u00fa\3\2\2"+
		"\2=\u00fc\3\2\2\2?\u00fe\3\2\2\2A\u0100\3\2\2\2C\u0102\3\2\2\2E\u0104"+
		"\3\2\2\2G\u0106\3\2\2\2I\u0108\3\2\2\2K\u010b\3\2\2\2M\u010d\3\2\2\2O"+
		"\u010f\3\2\2\2Q\u0112\3\2\2\2S\u0114\3\2\2\2U\u0116\3\2\2\2W\u0118\3\2"+
		"\2\2Y\u011a\3\2\2\2[\u011c\3\2\2\2]\u011e\3\2\2\2_\u0121\3\2\2\2a\u0125"+
		"\3\2\2\2c\u0134\3\2\2\2e\u0146\3\2\2\2g\u014c\3\2\2\2i\u0152\3\2\2\2k"+
		"l\7k\2\2lm\7h\2\2m\4\3\2\2\2no\7v\2\2op\7j\2\2pq\7g\2\2qr\7p\2\2r\6\3"+
		"\2\2\2st\7g\2\2tu\7n\2\2uv\7u\2\2vw\7g\2\2w\b\3\2\2\2xy\7h\2\2yz\7k\2"+
		"\2z\n\3\2\2\2{|\7n\2\2|}\7q\2\2}~\7q\2\2~\177\7r\2\2\177\f\3\2\2\2\u0080"+
		"\u0081\7r\2\2\u0081\u0082\7q\2\2\u0082\u0083\7q\2\2\u0083\u0084\7n\2\2"+
		"\u0084\16\3\2\2\2\u0085\u0086\7y\2\2\u0086\u0087\7j\2\2\u0087\u0088\7"+
		"k\2\2\u0088\u0089\7n\2\2\u0089\u008a\7g\2\2\u008a\20\3\2\2\2\u008b\u008c"+
		"\7e\2\2\u008c\u008d\7c\2\2\u008d\u008e\7u\2\2\u008e\u008f\7g\2\2\u008f"+
		"\22\3\2\2\2\u0090\u0091\7?\2\2\u0091\u0092\7@\2\2\u0092\24\3\2\2\2\u0093"+
		"\u0094\7g\2\2\u0094\u0095\7u\2\2\u0095\u0096\7c\2\2\u0096\u0097\7e\2\2"+
		"\u0097\26\3\2\2\2\u0098\u0099\7n\2\2\u0099\u009a\7g\2\2\u009a\u009b\7"+
		"v\2\2\u009b\30\3\2\2\2\u009c\u009d\7v\2\2\u009d\u009e\7t\2\2\u009e\u009f"+
		"\7w\2\2\u009f\u00a6\7g\2\2\u00a0\u00a1\7h\2\2\u00a1\u00a2\7c\2\2\u00a2"+
		"\u00a3\7n\2\2\u00a3\u00a4\7u\2\2\u00a4\u00a6\7g\2\2\u00a5\u009c\3\2\2"+
		"\2\u00a5\u00a0\3\2\2\2\u00a6\32\3\2\2\2\u00a7\u00a8\7e\2\2\u00a8\u00a9"+
		"\7n\2\2\u00a9\u00aa\7c\2\2\u00aa\u00ab\7u\2\2\u00ab\u00ac\7u\2\2\u00ac"+
		"\34\3\2\2\2\u00ad\u00ae\7k\2\2\u00ae\u00af\7p\2\2\u00af\36\3\2\2\2\u00b0"+
		"\u00b1\7k\2\2\u00b1\u00b2\7p\2\2\u00b2\u00b3\7j\2\2\u00b3\u00b4\7g\2\2"+
		"\u00b4\u00b5\7t\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7\7v\2\2\u00b7\u00b8"+
		"\7u\2\2\u00b8 \3\2\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7u\2\2\u00bb\u00bc"+
		"\7x\2\2\u00bc\u00bd\7q\2\2\u00bd\u00be\7k\2\2\u00be\u00bf\7f\2\2\u00bf"+
		"\"\3\2\2\2\u00c0\u00c1\7p\2\2\u00c1\u00c2\7g\2\2\u00c2\u00c3\7y\2\2\u00c3"+
		"$\3\2\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6\7h\2\2\u00c6&\3\2\2\2\u00c7\u00c8"+
		"\7p\2\2\u00c8\u00c9\7q\2\2\u00c9\u00ca\7v\2\2\u00ca(\3\2\2\2\u00cb\u00cc"+
		"\t\2\2\2\u00cc*\3\2\2\2\u00cd\u00ce\t\3\2\2\u00ce,\3\2\2\2\u00cf\u00d0"+
		"\t\4\2\2\u00d0.\3\2\2\2\u00d1\u00d5\5-\27\2\u00d2\u00d5\7a\2\2\u00d3\u00d5"+
		"\5\65\33\2\u00d4\u00d1\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d3\3\2\2\2"+
		"\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\60"+
		"\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00da\5+\26\2\u00da\u00db\5/\30\2\u00db"+
		"\62\3\2\2\2\u00dc\u00dd\5)\25\2\u00dd\u00de\5/\30\2\u00de\64\3\2\2\2\u00df"+
		"\u00e0\t\5\2\2\u00e0\66\3\2\2\2\u00e1\u00e3\5\65\33\2\u00e2\u00e1\3\2"+
		"\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"8\3\2\2\2\u00e6\u00ee\7$\2\2\u00e7\u00e8\7^\2\2\u00e8\u00ed\7$\2\2\u00e9"+
		"\u00ea\7^\2\2\u00ea\u00ed\5_\60\2\u00eb\u00ed\13\2\2\2\u00ec\u00e7\3\2"+
		"\2\2\u00ec\u00e9\3\2\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee"+
		"\u00ef\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00f8\3\2\2\2\u00f0\u00ee\3\2"+
		"\2\2\u00f1\u00f2\7$\2\2\u00f2\u00f9\b\35\2\2\u00f3\u00f4\7\2\2\3\u00f4"+
		"\u00f9\b\35\3\2\u00f5\u00f6\5_\60\2\u00f6\u00f7\b\35\4\2\u00f7\u00f9\3"+
		"\2\2\2\u00f8\u00f1\3\2\2\2\u00f8\u00f3\3\2\2\2\u00f8\u00f5\3\2\2\2\u00f9"+
		":\3\2\2\2\u00fa\u00fb\7\60\2\2\u00fb<\3\2\2\2\u00fc\u00fd\7B\2\2\u00fd"+
		">\3\2\2\2\u00fe\u00ff\7\u0080\2\2\u00ff@\3\2\2\2\u0100\u0101\7,\2\2\u0101"+
		"B\3\2\2\2\u0102\u0103\7\61\2\2\u0103D\3\2\2\2\u0104\u0105\7-\2\2\u0105"+
		"F\3\2\2\2\u0106\u0107\7/\2\2\u0107H\3\2\2\2\u0108\u0109\7>\2\2\u0109\u010a"+
		"\7?\2\2\u010aJ\3\2\2\2\u010b\u010c\7>\2\2\u010cL\3\2\2\2\u010d\u010e\7"+
		"?\2\2\u010eN\3\2\2\2\u010f\u0110\7>\2\2\u0110\u0111\7/\2\2\u0111P\3\2"+
		"\2\2\u0112\u0113\7<\2\2\u0113R\3\2\2\2\u0114\u0115\7=\2\2\u0115T\3\2\2"+
		"\2\u0116\u0117\7.\2\2\u0117V\3\2\2\2\u0118\u0119\7*\2\2\u0119X\3\2\2\2"+
		"\u011a\u011b\7+\2\2\u011bZ\3\2\2\2\u011c\u011d\7}\2\2\u011d\\\3\2\2\2"+
		"\u011e\u011f\7\177\2\2\u011f^\3\2\2\2\u0120\u0122\7\17\2\2\u0121\u0120"+
		"\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0124\7\f\2\2\u0124"+
		"`\3\2\2\2\u0125\u0126\7/\2\2\u0126\u0127\7/\2\2\u0127\u012b\3\2\2\2\u0128"+
		"\u012a\13\2\2\2\u0129\u0128\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u012c\3"+
		"\2\2\2\u012b\u0129\3\2\2\2\u012c\u0130\3\2\2\2\u012d\u012b\3\2\2\2\u012e"+
		"\u0131\5_\60\2\u012f\u0131\7\2\2\3\u0130\u012e\3\2\2\2\u0130\u012f\3\2"+
		"\2\2\u0131\u0132\3\2\2\2\u0132\u0133\b\61\5\2\u0133b\3\2\2\2\u0134\u0135"+
		"\7*\2\2\u0135\u0136\7,\2\2\u0136\u013b\3\2\2\2\u0137\u013a\5c\62\2\u0138"+
		"\u013a\13\2\2\2\u0139\u0137\3\2\2\2\u0139\u0138\3\2\2\2\u013a\u013d\3"+
		"\2\2\2\u013b\u013c\3\2\2\2\u013b\u0139\3\2\2\2\u013c\u0144\3\2\2\2\u013d"+
		"\u013b\3\2\2\2\u013e\u013f\7,\2\2\u013f\u0140\7+\2\2\u0140\u0141\3\2\2"+
		"\2\u0141\u0145\b\62\6\2\u0142\u0143\7\2\2\3\u0143\u0145\b\62\7\2\u0144"+
		"\u013e\3\2\2\2\u0144\u0142\3\2\2\2\u0145d\3\2\2\2\u0146\u0147\7,\2\2\u0147"+
		"\u0148\7+\2\2\u0148\u0149\3\2\2\2\u0149\u014a\b\63\b\2\u014af\3\2\2\2"+
		"\u014b\u014d\t\6\2\2\u014c\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014c"+
		"\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151\b\64\5\2"+
		"\u0151h\3\2\2\2\u0152\u0153\13\2\2\2\u0153\u0154\b\65\t\2\u0154j\3\2\2"+
		"\2\21\2\u00a5\u00d4\u00d6\u00e4\u00ec\u00ee\u00f8\u0121\u012b\u0130\u0139"+
		"\u013b\u0144\u014e\n\3\35\2\3\35\3\3\35\4\b\2\2\3\62\5\3\62\6\3\63\7\3"+
		"\65\b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}