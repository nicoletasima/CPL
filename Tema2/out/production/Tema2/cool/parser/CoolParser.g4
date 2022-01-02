parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

program
    :   (classes+=classDef)+ EOF
    ;

formal
    : name=ID COLON type=ID
    ;

varDef
    : name=ID COLON type=ID (ASSIGN init=expr)?
    ;

feature
    : name=ID LPAREN (formals+=formal (COMMA formals+=formal)*)? RPAREN COLON type=ID LBRACE (body+=expr)* RBRACE SEMI   # funcDef
    | name=ID COLON type=ID (ASSIGN init=expr)? SEMI                                                                     # memberDef
    ;

classDef
    : CLASS type=ID (INHERITS parentType=ID)? LBRACE (features+=feature)* RBRACE SEMI;

expr:
      name=ID LPAREN (params+=expr (COMMA params+=expr)*)? RPAREN                                         # implicitDispatch
    | call=expr (AT type=ID)? DOT name=ID LPAREN (params+=expr (COMMA params+=expr)*)? RPAREN             # explicitDispatch
    | IF cond=expr THEN thenBranch=expr ELSE elseBranch=expr FI                                           # if
    | WHILE cond=expr LOOP body=expr POOL                                                                 # while
    | LBRACE (instructions+=expr SEMI)+ RBRACE                                                            # block
    | LET vars+=varDef (COMMA vars+=varDef)* IN body=expr                                                 # let
    | CASE cond=expr OF (cases+=expr)+ ESAC                                                               # case
    | name=ID COLON type=ID CASE_BRANCH body=expr SEMI                                                    # caseBranch
    | COMPLEMENT op=expr                                                                                  # complement
    | LPAREN op=expr RPAREN                                                                               # parentheses
    | ID                                                                                                  # id
    | INTEGER                                                                                             # int
    | STRING                                                                                              # stringNode
    | BOOL                                                                                                # bool
    | NEW ID                                                                                              # new
    | ISVOID op=expr                                                                                      # isvoid
    | left=expr MULT right=expr                                                                           # mult
    | left=expr DIV right=expr                                                                            # div
    | left=expr PLUS right=expr                                                                           # plus
    | left=expr MINUS right=expr                                                                          # minus
    | left=expr LT right=expr                                                                             # less
    | left=expr LE right=expr                                                                             # lessOrEqual
    | left=expr EQUAL right=expr                                                                          # equal
    | name=ID ASSIGN e=expr                                                                               # assign
    | NOT op=expr                                                                                         # not

    ;