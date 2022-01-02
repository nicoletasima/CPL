package cool.compiler;

import cool.ast.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import cool.lexer.*;
import cool.parser.*;

import java.io.*;
import java.util.LinkedList;


public class Compiler {
    // Annotates class nodes with the names of files where they are defined.
    public static ParseTreeProperty<String> fileNames = new ParseTreeProperty<>();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("No file(s) given");
            return;
        }
        
        CoolLexer lexer = null;
        CommonTokenStream tokenStream = null;
        CoolParser parser = null;
        ParserRuleContext globalTree = null;

        
        // True if any lexical or syntax errors occur.
        boolean lexicalSyntaxErrors = false;
        
        // Parse each input file and build one big parse tree out of
        // individual parse trees.
        for (var fileName : args) {
            var input = CharStreams.fromFileName(fileName);
            
            // Lexer
            if (lexer == null)
                lexer = new CoolLexer(input);
            else
                lexer.setInputStream(input);

            // Token stream
            if (tokenStream == null)
                tokenStream = new CommonTokenStream(lexer);
            else
                tokenStream.setTokenSource(lexer);

            // Test lexer only.
//            tokenStream.fill();
//            List<Token> tokens = tokenStream.getTokens();
//            tokens.stream().forEach(token -> {
//                var text = token.getText();
//                var name = CoolLexer.VOCABULARY.getSymbolicName(token.getType());
//
//                System.out.println(text + " : " + name);
//                //System.out.println(token);
//            });
            
            // Parser
            if (parser == null)
                parser = new CoolParser(tokenStream);
            else
                parser.setTokenStream(tokenStream);
            
            // Customized error listener, for including file names in error
            // messages.
            var errorListener = new BaseErrorListener() {
                public boolean errors = false;
                
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer,
                                        Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg,
                                        RecognitionException e) {
                    String newMsg = "\"" + new File(fileName).getName() + "\", line " +
                                        line + ":" + (charPositionInLine + 1) + ", ";
                    
                    Token token = (Token)offendingSymbol;
                    if (token.getType() == CoolLexer.ERROR)
                        newMsg += "Lexical error: " + token.getText();
                    else
                        newMsg += "Syntax error: " + msg;
                    
                    System.err.println(newMsg);
                    errors = true;
                }
            };
            
            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);
            
            // Actual parsing
            var tree = parser.program();
            if (globalTree == null)
                globalTree = tree;
            else
                // Add the current parse tree's children to the global tree.
                for (int i = 0; i < tree.getChildCount(); i++)
                    globalTree.addAnyChild(tree.getChild(i));
                    
            // Annotate class nodes with file names, to be used later
            // in semantic error messages.
            for (int i = 0; i < tree.getChildCount(); i++) {
                var child = tree.getChild(i);
                // The only ParserRuleContext children of the program node
                // are class nodes.
                if (child instanceof ParserRuleContext)
                    fileNames.put(child, fileName);
            }
            
            // Record any lexical or syntax errors.
            lexicalSyntaxErrors |= errorListener.errors;
        }

        // Stop before semantic analysis phase, in case errors occurred.
        if (lexicalSyntaxErrors) {
            System.err.println("Compilation halted");
            return;
        }
        
        // TODO Print tree
        var astConstructionVisitor = new CoolParserBaseVisitor<ASTNode>() {
            @Override
            public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
                LinkedList<ClassDef> visited = new LinkedList<>();

                for (var classVar : ctx.classes) {
                    visited.add((ClassDef) visit(classVar));
                }

                return new Program(visited);
            }

            @Override
            public ASTNode visitClassDef(CoolParser.ClassDefContext ctx) {
                LinkedList<Feature> visited = new LinkedList<>();
                for (var feature : ctx.features) {
                    visited.add((Feature) visit(feature));
                }

                return new ClassDef(visited, ctx.type, ctx.parentType);
            }

            @Override
            public ASTNode visitAssign(CoolParser.AssignContext ctx) {
                return new Assign(ctx.start,
                                ctx.name,
                                (Expression)visit(ctx.e));
            }

            @Override
            public ASTNode visitFormal(CoolParser.FormalContext ctx) {
                return new Formal(ctx.name, ctx.type);
            }

            @Override
            public Feature visitFuncDef(CoolParser.FuncDefContext ctx) {
                LinkedList<Formal> visited = new LinkedList<>();
                LinkedList<Expression> instructions = new LinkedList<>();
                for (var formal : ctx.formals) {
                    visited.add((Formal) visit(formal));
                }

                for (var instr : ctx.body) {
                    instructions.add((Expression) visit(instr));
                }

                return new FuncDef(visited, instructions, ctx.type, ctx.name);

            }

            @Override
            public ASTNode visitVarDef(CoolParser.VarDefContext ctx) {
                if (ctx.init != null) {
                    return new VarDef((Expression) visit(ctx.init), ctx.name, ctx.type);
                }
                return new VarDef(ctx.name, ctx.type);
            }

            @Override
            public ASTNode visitMemberDef(CoolParser.MemberDefContext ctx) {
                if (ctx.init == null)
                    return new MemberDef(ctx.name, ctx.type);
                return new MemberDef((Expression) visit(ctx.init), ctx.name, ctx.type);

            }

            @Override
            public Expression visitInt(CoolParser.IntContext ctx) {
                return new Int(ctx.INTEGER().getSymbol());
            }

            @Override
            public Expression visitBool(CoolParser.BoolContext ctx) {
                return new Bool(ctx.BOOL().getSymbol());
            }

            @Override
            public Expression visitStringNode(CoolParser.StringNodeContext ctx) {
                return new StringNode(ctx.STRING().getSymbol());
            }

            @Override
            public Expression visitNameID(CoolParser.NameIDContext ctx) {
                return new NameID(ctx.NAME_ID().getSymbol());
            }

            @Override
            public Expression visitTypeID(CoolParser.TypeIDContext ctx) {
                return new TypeID(ctx.TYPE_ID().getSymbol());
            }

            @Override
            public ASTNode visitMult(CoolParser.MultContext ctx) {
                return new Mult(ctx.MULT().getSymbol(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
            }

            @Override
            public ASTNode visitDiv(CoolParser.DivContext ctx) {
                return new Div(ctx.DIV().getSymbol(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
            }

            @Override
            public ASTNode visitPlus(CoolParser.PlusContext ctx) {
                return new Plus(ctx.PLUS().getSymbol(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
            }

            @Override
            public ASTNode visitMinus(CoolParser.MinusContext ctx) {
                return new Minus(ctx.MINUS().getSymbol(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
            }

            @Override
            public ASTNode visitLessOrEqual(CoolParser.LessOrEqualContext ctx) {
                return new LessOrEqual(ctx.LE().getSymbol(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
            }

            @Override
            public ASTNode visitLess(CoolParser.LessContext ctx) {
                return new LessOrEqual(ctx.LT().getSymbol(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
            }

            @Override
            public ASTNode visitEqual(CoolParser.EqualContext ctx) {
                return new Equal(ctx.EQUAL().getSymbol(), (Expression) visit(ctx.left), (Expression) visit(ctx.right));
            }

            @Override
            public ASTNode visitNot(CoolParser.NotContext ctx) {
                return new Not(ctx.NOT().getSymbol(), (Expression) visit(ctx.op));
            }

            @Override
            public ASTNode visitParentheses(CoolParser.ParenthesesContext ctx) {
                return ctx.op.accept(this);
            }

            @Override
            public ASTNode visitComplement(CoolParser.ComplementContext ctx) {
                return new Complement(ctx.COMPLEMENT().getSymbol(), (Expression) visit(ctx.op));
            }

            public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
                return new Complement(ctx.ISVOID().getSymbol(), (Expression) visit(ctx.op));
            }

            @Override
            public ASTNode visitNew(CoolParser.NewContext ctx) {
                return new New(ctx.NEW().getSymbol(), ctx.TYPE_ID().getSymbol());
            }

            @Override
            public ASTNode visitIf(CoolParser.IfContext ctx) {
                return new If(ctx.IF().getSymbol(), (Expression) visit(ctx.cond), (Expression) visit(ctx.thenBranch), (Expression) visit(ctx.elseBranch));
            }

            @Override
            public ASTNode visitWhile(CoolParser.WhileContext ctx) {
                return new While(ctx.WHILE().getSymbol(), (Expression) visit(ctx.cond), (Expression) visit(ctx.body));
            }

            @Override
            public ASTNode visitBlock(CoolParser.BlockContext ctx) {
                LinkedList<Expression> visited = new LinkedList<>();

                for (var instr : ctx.instructions) {
                    visited.add((Expression) visit(instr));
                }

                return new Block(ctx.LBRACE().getSymbol(), visited);
            }

            public ASTNode visitCaseBranch(CoolParser.CaseBranchContext ctx) {
                return new CaseBranch((Expression) visit(ctx.body), ctx.name, ctx.type, ctx.CASE_BRANCH().getSymbol());
            }

            public ASTNode visitCase(CoolParser.CaseContext ctx) {
                LinkedList<Expression> visited = new LinkedList<>();

                for (var caseVar : ctx.cases) {
                    visited.add((Expression) visit(caseVar));
                }
                return new Case(ctx.CASE().getSymbol(), (Expression) visit(ctx.cond), visited);
            }
            
            public ASTNode visitLet(CoolParser.LetContext ctx) {
                LinkedList<VarDef> visited = new LinkedList<>();
                
                for (var v : ctx.vars) {
                    visited.add((VarDef) visit(v));
                }
                
                return new Let(ctx.LET().getSymbol(), visited, (Expression) visit(ctx.body));
            }

            public ASTNode visitImplicitDispatch(CoolParser.ImplicitDispatchContext ctx) {
                LinkedList<Expression> visited = new LinkedList<>();

                for (var param : ctx.params) {
                    visited.add((Expression) visit(param));
                }

                return new ImplicitDispatch(ctx.NAME_ID().getSymbol(), visited);
            }

            public ASTNode visitExplicitDispatch(CoolParser.ExplicitDispatchContext ctx) {
                LinkedList<Expression> visited = new LinkedList<>();

                for (var param : ctx.params) {
                    visited.add((Expression) visit(param));
                }

                if (ctx.type != null) {
                    return new ExplicitDispatch(ctx.DOT().getSymbol(), (Expression) visit(ctx.call), visited, ctx.type,
                            ctx.name);
                }

                return new ExplicitDispatch(ctx.DOT().getSymbol(), (Expression) visit(ctx.call), visited, ctx.name);
            }
        };

        var ast = astConstructionVisitor.visit(globalTree);

        var printVisitor = new ASTVisitor<Void>() {
            int indent = 0;

            @Override
            public Void visit(Formal formal) {
                printIndent("formal");

                indent++;
                printIndent(formal.getName().getText());
                printIndent(formal.getType().getText());
                indent--;
                return null;
            }

            @Override
            public Void visit(Assign assign) {

                printIndent("<-");
                indent++;
                // printIndent(assign.getName().getText());
                printIndent(assign.getToken().getText());
                assign.getE().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(FuncDef funcDef) {
                printIndent("method");
                indent++;
                printIndent(funcDef.getName().getText());
                for (var formal : funcDef.getFormals()) {
                    formal.accept(this);
                }
                printIndent(funcDef.getType().getText());
                for (var instr : funcDef.getBody()) {
                    instr.accept(this);
                }
                indent --;
                return null;
            }

            @Override
            public Void visit(VarDef varDef) {
                indent++;
                printIndent(varDef.getName().getText());
                printIndent(varDef.getType().getText());
                if (varDef.getInit() != null) {
                    varDef.getInit().accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(ClassDef classDef) {
                printIndent( "class");
                indent++;
                printIndent(classDef.getType().getText());
                if (classDef.getParentType() != null) {
                    printIndent(classDef.getParentType().getText());
                }
                for (var feature : classDef.getFeatures()) {
                    feature.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(Program program) {
                printIndent("program");
                indent++;
                for (var classVar : program.getClasses()) {
                    classVar.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(MemberDef memberdef) {
                printIndent("attribute");
                indent++;
                printIndent(memberdef.getName().getText());
                printIndent(memberdef.getType().getText());
                if (memberdef.getInit() != null) {
                    memberdef.getInit().accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(Int intt) {
                printIndent(intt.getToken().getText());
                return null;
            }

            @Override
            public Void visit(Bool booll) {
                printIndent(booll.getToken().getText());
                return null;
            }

            @Override
            public Void visit(StringNode string) {
                printIndent(string.getToken().getText());
                return null;
            }

            @Override
            public Void visit(NameID nameId) {
                printIndent(nameId.getToken().getText());
                return null;
            }

            @Override
            public Void visit(TypeID typeId) {
                printIndent(typeId.getToken().getText());
                return null;
            }

            @Override
            public Void visit(Div div) {
                printIndent(div.getToken().getText());
                indent++;
                div.getLeft().accept(this);

                div.getRight().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Mult mult) {
                printIndent(mult.getToken().getText());
                indent++;
                mult.getLeft().accept(this);

                mult.getRight().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Plus plus) {
                printIndent(plus.getToken().getText());
                indent++;
                plus.getLeft().accept(this);
                plus.getRight().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Minus minus) {
                printIndent(minus.getToken().getText());
                indent++;
                minus.getLeft().accept(this);

                minus.getRight().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(LessOrEqual le) {
                printIndent(le.getToken().getText());
                indent++;
                le.getLeft().accept(this);

                le.getRight().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Less lt) {
                printIndent(lt.getToken().getText());
                indent++;
                lt.getLeft().accept(this);

                lt.getRight().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Equal eq) {
                printIndent(eq.getToken().getText());
                indent++;
                eq.getLeft().accept(this);

                eq.getRight().accept(this);
                indent--;
                return null;
            }


            @Override
            public Void visit(Not not) {
                printIndent(not.getToken().getText());
                indent++;

                not.getOp().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(IsVoid isvoid) {
                printIndent(isvoid.getToken().getText());
                indent++;
                isvoid.getOp().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(New neww) {
                printIndent(neww.getToken().getText());
                indent++;
                printIndent(neww.getType().getText());
                indent--;
                return null;
            }

            @Override
            public Void visit(If iff) {
                printIndent(iff.getToken().getText());
                indent++;
                iff.getCond().accept(this);
                iff.getThenBranch().accept(this);
                iff.getElseBranch().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Block block) {
                printIndent("block");
                indent++;
                for (var instr : block.getInstructions()) {
                    instr.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(While whilee) {
                printIndent(whilee.getToken().getText());
                indent++;
                whilee.getCond().accept(this);
                whilee.getBody().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Let let) {
                printIndent(let.getToken().getText());
                indent++;
                for (var v : let.getVars()) {
                    printIndent("local");
                    v.accept(this);
                }
                let.getBody().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(ImplicitDispatch implicitDispatch) {
                printIndent("implicit dispatch");
                indent++;
                printIndent(implicitDispatch.getToken().getText());
                for (var param : implicitDispatch.getParams()) {
                    param.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(ExplicitDispatch explicitDispatch) {
                printIndent(explicitDispatch.getToken().getText());
                indent++;
                explicitDispatch.getCall().accept(this);
                if (explicitDispatch.getType() != null) {
                    printIndent(explicitDispatch.getType().getText());
                }
                printIndent(explicitDispatch.getName().getText());
                if (explicitDispatch.getParams() != null) {
                    for (var param : explicitDispatch.getParams()) {
                        param.accept(this);
                    }
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(Case casee) {
                printIndent(casee.getToken().getText());
                indent++;
                casee.getCond().accept(this);
                for (var casevar : casee.getCases()) {
                    casevar.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(CaseBranch caseB) {
                printIndent("case branch");
                indent++;
                printIndent(caseB.getName().getText());
                printIndent(caseB.getType().getText());
                caseB.getBody().accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Parentheses paren) {
                paren.getOp().accept(this);
                return null;
            }

            @Override
            public Void visit(Complement compl) {
                printIndent(compl.getToken().getText());
                indent++;
                compl.getOp().accept(this);
                indent--;
                return null;
            }

            void printIndent(String str) {
                for (int i = 0; i < indent; i++)
                    System.out.print("  ");
                System.out.println(str);
            }

        };
        ast.accept(printVisitor);
    }
}
