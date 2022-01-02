lexer grammar CoolLexer;

tokens { ERROR } 

@header{
    package cool.lexer;	
}

@members{
    private static int MAX_LEN = 1024;

    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
}

/* Keywords
*/

IF: 'if';
THEN: 'then';
ELSE: 'else';
FI: 'fi';
LOOP: 'loop';
POOL: 'pool';
WHILE: 'while';
CASE: 'case';
CASE_BRANCH: '=>';
ESAC: 'esac';
LET: 'let';
BOOL: 'true' | 'false';
CLASS: 'class';
IN: 'in';
INHERITS: 'inherits';
ISVOID: 'isvoid';
NEW: 'new';
OF: 'of';
NOT: 'not';

/* Identifiers
*/

fragment LOWERCASE: [a-z];
fragment UPPERCASE: [A-Z];
fragment LETTER: [a-zA-Z];

fragment ID_FRAGMENT : (LETTER | '_' | DIGIT)*;

// TYPE_ID: UPPERCASE ID_FRAGMENT;
// NAME_ID: LOWERCASE ID_FRAGMENT;
ID: (UPPERCASE | LOWERCASE) ID_FRAGMENT;





/* Integers
*/
fragment DIGIT: [0-9];

INTEGER: DIGIT+;

/* Strings
*/
STRING: '"' ('\\"' | '\\' NEW_LINE | .)*?
        ('"' { String text = getText();
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

        }
        | EOF { raiseError("EOF in string constant"); }
        | NEW_LINE { raiseError("Unterminated string constant") ;}
         )
        ;

/* Operations
*/
DOT: '.';
AT: '@';
COMPLEMENT: '~';
MULT: '*';
DIV: '/';
PLUS: '+';
MINUS: '-';
LE: '<=';
LT: '<';
EQUAL: '=';
ASSIGN: '<-';
COLON: ':';
SEMI: ';';
COMMA: ',';
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';


fragment NEW_LINE: '\r'? '\n';

LINE_COMMENT
    : '--' .*? (NEW_LINE | EOF) -> skip
    ;

BLOCK_COMMENT
    : '(*'
       (BLOCK_COMMENT | .)*?
       ('*)' { skip(); } | EOF { raiseError("EOF in comment"); })
    ;

UNMATCHED_COMMENT
    : '*)' { raiseError("Unmatched *)"); }
    ;

WS
    :   [ \n\f\r\t]+ -> skip
    ;

INVALID_CHARACTER: . { raiseError("Invalid character: " + getText()); };