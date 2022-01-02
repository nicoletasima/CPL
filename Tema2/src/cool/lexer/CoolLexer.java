// Generated from C:/Users/nicol/Desktop/Facultate/Anul IV/CPL/Tema 2/Schelet + checker - IntelliJ/Tema2/src/cool/lexer\CoolLexer.g4 by ANTLR 4.9.2

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
		ISVOID=17, NEW=18, OF=19, NOT=20, ID=21, INTEGER=22, STRING=23, DOT=24, 
		AT=25, COMPLEMENT=26, MULT=27, DIV=28, PLUS=29, MINUS=30, LE=31, LT=32, 
		EQUAL=33, ASSIGN=34, COLON=35, SEMI=36, COMMA=37, LPAREN=38, RPAREN=39, 
		LBRACE=40, RBRACE=41, LINE_COMMENT=42, BLOCK_COMMENT=43, UNMATCHED_COMMENT=44, 
		WS=45, INVALID_CHARACTER=46;
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
			"NOT", "LOWERCASE", "UPPERCASE", "LETTER", "ID_FRAGMENT", "ID", "DIGIT", 
			"INTEGER", "STRING", "DOT", "AT", "COMPLEMENT", "MULT", "DIV", "PLUS", 
			"MINUS", "LE", "LT", "EQUAL", "ASSIGN", "COLON", "SEMI", "COMMA", "LPAREN", 
			"RPAREN", "LBRACE", "RBRACE", "NEW_LINE", "LINE_COMMENT", "BLOCK_COMMENT", 
			"UNMATCHED_COMMENT", "WS", "INVALID_CHARACTER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'if'", "'then'", "'else'", "'fi'", "'loop'", "'pool'", "'while'", 
			"'case'", "'=>'", "'esac'", "'let'", null, "'class'", "'in'", "'inherits'", 
			"'isvoid'", "'new'", "'of'", "'not'", null, null, null, "'.'", "'@'", 
			"'~'", "'*'", "'/'", "'+'", "'-'", "'<='", "'<'", "'='", "'<-'", "':'", 
			"';'", "','", "'('", "')'", "'{'", "'}'", null, null, "'*)'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "IF", "THEN", "ELSE", "FI", "LOOP", "POOL", "WHILE", "CASE", 
			"CASE_BRANCH", "ESAC", "LET", "BOOL", "CLASS", "IN", "INHERITS", "ISVOID", 
			"NEW", "OF", "NOT", "ID", "INTEGER", "STRING", "DOT", "AT", "COMPLEMENT", 
			"MULT", "DIV", "PLUS", "MINUS", "LE", "LT", "EQUAL", "ASSIGN", "COLON", 
			"SEMI", "COMMA", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LINE_COMMENT", 
			"BLOCK_COMMENT", "UNMATCHED_COMMENT", "WS", "INVALID_CHARACTER"
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
		case 26:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 47:
			BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 48:
			UNMATCHED_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 50:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\60\u0153\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a4\n\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\7\30\u00d3"+
		"\n\30\f\30\16\30\u00d6\13\30\3\31\3\31\5\31\u00da\n\31\3\31\3\31\3\32"+
		"\3\32\3\33\6\33\u00e1\n\33\r\33\16\33\u00e2\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\7\34\u00eb\n\34\f\34\16\34\u00ee\13\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\5\34\u00f7\n\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\""+
		"\3\"\3#\3#\3$\3$\3$\3%\3%\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3"+
		",\3,\3-\3-\3.\3.\3/\5/\u0120\n/\3/\3/\3\60\3\60\3\60\3\60\7\60\u0128\n"+
		"\60\f\60\16\60\u012b\13\60\3\60\3\60\5\60\u012f\n\60\3\60\3\60\3\61\3"+
		"\61\3\61\3\61\3\61\7\61\u0138\n\61\f\61\16\61\u013b\13\61\3\61\3\61\3"+
		"\61\3\61\3\61\3\61\5\61\u0143\n\61\3\62\3\62\3\62\3\62\3\62\3\63\6\63"+
		"\u014b\n\63\r\63\16\63\u014c\3\63\3\63\3\64\3\64\3\64\5\u00ec\u0129\u0139"+
		"\2\65\3\4\5\5\7\6\t\7\13\b\r\t\17\n\21\13\23\f\25\r\27\16\31\17\33\20"+
		"\35\21\37\22!\23#\24%\25\'\26)\2+\2-\2/\2\61\27\63\2\65\30\67\319\32;"+
		"\33=\34?\35A\36C\37E G!I\"K#M$O%Q&S\'U(W)Y*[+]\2_,a-c.e/g\60\3\2\7\3\2"+
		"c|\3\2C\\\4\2C\\c|\3\2\62;\5\2\13\f\16\17\"\"\2\u015e\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2\61\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2"+
		"I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3"+
		"\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2"+
		"\2\2e\3\2\2\2\2g\3\2\2\2\3i\3\2\2\2\5l\3\2\2\2\7q\3\2\2\2\tv\3\2\2\2\13"+
		"y\3\2\2\2\r~\3\2\2\2\17\u0083\3\2\2\2\21\u0089\3\2\2\2\23\u008e\3\2\2"+
		"\2\25\u0091\3\2\2\2\27\u0096\3\2\2\2\31\u00a3\3\2\2\2\33\u00a5\3\2\2\2"+
		"\35\u00ab\3\2\2\2\37\u00ae\3\2\2\2!\u00b7\3\2\2\2#\u00be\3\2\2\2%\u00c2"+
		"\3\2\2\2\'\u00c5\3\2\2\2)\u00c9\3\2\2\2+\u00cb\3\2\2\2-\u00cd\3\2\2\2"+
		"/\u00d4\3\2\2\2\61\u00d9\3\2\2\2\63\u00dd\3\2\2\2\65\u00e0\3\2\2\2\67"+
		"\u00e4\3\2\2\29\u00f8\3\2\2\2;\u00fa\3\2\2\2=\u00fc\3\2\2\2?\u00fe\3\2"+
		"\2\2A\u0100\3\2\2\2C\u0102\3\2\2\2E\u0104\3\2\2\2G\u0106\3\2\2\2I\u0109"+
		"\3\2\2\2K\u010b\3\2\2\2M\u010d\3\2\2\2O\u0110\3\2\2\2Q\u0112\3\2\2\2S"+
		"\u0114\3\2\2\2U\u0116\3\2\2\2W\u0118\3\2\2\2Y\u011a\3\2\2\2[\u011c\3\2"+
		"\2\2]\u011f\3\2\2\2_\u0123\3\2\2\2a\u0132\3\2\2\2c\u0144\3\2\2\2e\u014a"+
		"\3\2\2\2g\u0150\3\2\2\2ij\7k\2\2jk\7h\2\2k\4\3\2\2\2lm\7v\2\2mn\7j\2\2"+
		"no\7g\2\2op\7p\2\2p\6\3\2\2\2qr\7g\2\2rs\7n\2\2st\7u\2\2tu\7g\2\2u\b\3"+
		"\2\2\2vw\7h\2\2wx\7k\2\2x\n\3\2\2\2yz\7n\2\2z{\7q\2\2{|\7q\2\2|}\7r\2"+
		"\2}\f\3\2\2\2~\177\7r\2\2\177\u0080\7q\2\2\u0080\u0081\7q\2\2\u0081\u0082"+
		"\7n\2\2\u0082\16\3\2\2\2\u0083\u0084\7y\2\2\u0084\u0085\7j\2\2\u0085\u0086"+
		"\7k\2\2\u0086\u0087\7n\2\2\u0087\u0088\7g\2\2\u0088\20\3\2\2\2\u0089\u008a"+
		"\7e\2\2\u008a\u008b\7c\2\2\u008b\u008c\7u\2\2\u008c\u008d\7g\2\2\u008d"+
		"\22\3\2\2\2\u008e\u008f\7?\2\2\u008f\u0090\7@\2\2\u0090\24\3\2\2\2\u0091"+
		"\u0092\7g\2\2\u0092\u0093\7u\2\2\u0093\u0094\7c\2\2\u0094\u0095\7e\2\2"+
		"\u0095\26\3\2\2\2\u0096\u0097\7n\2\2\u0097\u0098\7g\2\2\u0098\u0099\7"+
		"v\2\2\u0099\30\3\2\2\2\u009a\u009b\7v\2\2\u009b\u009c\7t\2\2\u009c\u009d"+
		"\7w\2\2\u009d\u00a4\7g\2\2\u009e\u009f\7h\2\2\u009f\u00a0\7c\2\2\u00a0"+
		"\u00a1\7n\2\2\u00a1\u00a2\7u\2\2\u00a2\u00a4\7g\2\2\u00a3\u009a\3\2\2"+
		"\2\u00a3\u009e\3\2\2\2\u00a4\32\3\2\2\2\u00a5\u00a6\7e\2\2\u00a6\u00a7"+
		"\7n\2\2\u00a7\u00a8\7c\2\2\u00a8\u00a9\7u\2\2\u00a9\u00aa\7u\2\2\u00aa"+
		"\34\3\2\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7p\2\2\u00ad\36\3\2\2\2\u00ae"+
		"\u00af\7k\2\2\u00af\u00b0\7p\2\2\u00b0\u00b1\7j\2\2\u00b1\u00b2\7g\2\2"+
		"\u00b2\u00b3\7t\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7v\2\2\u00b5\u00b6"+
		"\7u\2\2\u00b6 \3\2\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7u\2\2\u00b9\u00ba"+
		"\7x\2\2\u00ba\u00bb\7q\2\2\u00bb\u00bc\7k\2\2\u00bc\u00bd\7f\2\2\u00bd"+
		"\"\3\2\2\2\u00be\u00bf\7p\2\2\u00bf\u00c0\7g\2\2\u00c0\u00c1\7y\2\2\u00c1"+
		"$\3\2\2\2\u00c2\u00c3\7q\2\2\u00c3\u00c4\7h\2\2\u00c4&\3\2\2\2\u00c5\u00c6"+
		"\7p\2\2\u00c6\u00c7\7q\2\2\u00c7\u00c8\7v\2\2\u00c8(\3\2\2\2\u00c9\u00ca"+
		"\t\2\2\2\u00ca*\3\2\2\2\u00cb\u00cc\t\3\2\2\u00cc,\3\2\2\2\u00cd\u00ce"+
		"\t\4\2\2\u00ce.\3\2\2\2\u00cf\u00d3\5-\27\2\u00d0\u00d3\7a\2\2\u00d1\u00d3"+
		"\5\63\32\2\u00d2\u00cf\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d1\3\2\2\2"+
		"\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\60"+
		"\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00da\5+\26\2\u00d8\u00da\5)\25\2\u00d9"+
		"\u00d7\3\2\2\2\u00d9\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\5/"+
		"\30\2\u00dc\62\3\2\2\2\u00dd\u00de\t\5\2\2\u00de\64\3\2\2\2\u00df\u00e1"+
		"\5\63\32\2\u00e0\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e0\3\2\2\2"+
		"\u00e2\u00e3\3\2\2\2\u00e3\66\3\2\2\2\u00e4\u00ec\7$\2\2\u00e5\u00e6\7"+
		"^\2\2\u00e6\u00eb\7$\2\2\u00e7\u00e8\7^\2\2\u00e8\u00eb\5]/\2\u00e9\u00eb"+
		"\13\2\2\2\u00ea\u00e5\3\2\2\2\u00ea\u00e7\3\2\2\2\u00ea\u00e9\3\2\2\2"+
		"\u00eb\u00ee\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00f6"+
		"\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00f0\7$\2\2\u00f0\u00f7\b\34\2\2\u00f1"+
		"\u00f2\7\2\2\3\u00f2\u00f7\b\34\3\2\u00f3\u00f4\5]/\2\u00f4\u00f5\b\34"+
		"\4\2\u00f5\u00f7\3\2\2\2\u00f6\u00ef\3\2\2\2\u00f6\u00f1\3\2\2\2\u00f6"+
		"\u00f3\3\2\2\2\u00f78\3\2\2\2\u00f8\u00f9\7\60\2\2\u00f9:\3\2\2\2\u00fa"+
		"\u00fb\7B\2\2\u00fb<\3\2\2\2\u00fc\u00fd\7\u0080\2\2\u00fd>\3\2\2\2\u00fe"+
		"\u00ff\7,\2\2\u00ff@\3\2\2\2\u0100\u0101\7\61\2\2\u0101B\3\2\2\2\u0102"+
		"\u0103\7-\2\2\u0103D\3\2\2\2\u0104\u0105\7/\2\2\u0105F\3\2\2\2\u0106\u0107"+
		"\7>\2\2\u0107\u0108\7?\2\2\u0108H\3\2\2\2\u0109\u010a\7>\2\2\u010aJ\3"+
		"\2\2\2\u010b\u010c\7?\2\2\u010cL\3\2\2\2\u010d\u010e\7>\2\2\u010e\u010f"+
		"\7/\2\2\u010fN\3\2\2\2\u0110\u0111\7<\2\2\u0111P\3\2\2\2\u0112\u0113\7"+
		"=\2\2\u0113R\3\2\2\2\u0114\u0115\7.\2\2\u0115T\3\2\2\2\u0116\u0117\7*"+
		"\2\2\u0117V\3\2\2\2\u0118\u0119\7+\2\2\u0119X\3\2\2\2\u011a\u011b\7}\2"+
		"\2\u011bZ\3\2\2\2\u011c\u011d\7\177\2\2\u011d\\\3\2\2\2\u011e\u0120\7"+
		"\17\2\2\u011f\u011e\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121\3\2\2\2\u0121"+
		"\u0122\7\f\2\2\u0122^\3\2\2\2\u0123\u0124\7/\2\2\u0124\u0125\7/\2\2\u0125"+
		"\u0129\3\2\2\2\u0126\u0128\13\2\2\2\u0127\u0126\3\2\2\2\u0128\u012b\3"+
		"\2\2\2\u0129\u012a\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u012e\3\2\2\2\u012b"+
		"\u0129\3\2\2\2\u012c\u012f\5]/\2\u012d\u012f\7\2\2\3\u012e\u012c\3\2\2"+
		"\2\u012e\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\b\60\5\2\u0131"+
		"`\3\2\2\2\u0132\u0133\7*\2\2\u0133\u0134\7,\2\2\u0134\u0139\3\2\2\2\u0135"+
		"\u0138\5a\61\2\u0136\u0138\13\2\2\2\u0137\u0135\3\2\2\2\u0137\u0136\3"+
		"\2\2\2\u0138\u013b\3\2\2\2\u0139\u013a\3\2\2\2\u0139\u0137\3\2\2\2\u013a"+
		"\u0142\3\2\2\2\u013b\u0139\3\2\2\2\u013c\u013d\7,\2\2\u013d\u013e\7+\2"+
		"\2\u013e\u013f\3\2\2\2\u013f\u0143\b\61\6\2\u0140\u0141\7\2\2\3\u0141"+
		"\u0143\b\61\7\2\u0142\u013c\3\2\2\2\u0142\u0140\3\2\2\2\u0143b\3\2\2\2"+
		"\u0144\u0145\7,\2\2\u0145\u0146\7+\2\2\u0146\u0147\3\2\2\2\u0147\u0148"+
		"\b\62\b\2\u0148d\3\2\2\2\u0149\u014b\t\6\2\2\u014a\u0149\3\2\2\2\u014b"+
		"\u014c\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014e\3\2"+
		"\2\2\u014e\u014f\b\63\5\2\u014ff\3\2\2\2\u0150\u0151\13\2\2\2\u0151\u0152"+
		"\b\64\t\2\u0152h\3\2\2\2\22\2\u00a3\u00d2\u00d4\u00d9\u00e2\u00ea\u00ec"+
		"\u00f6\u011f\u0129\u012e\u0137\u0139\u0142\u014c\n\3\34\2\3\34\3\3\34"+
		"\4\b\2\2\3\61\5\3\61\6\3\62\7\3\64\b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}