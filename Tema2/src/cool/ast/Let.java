package cool.ast;

import cool.structures.LetSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class Let extends Expression {
  protected LinkedList<VarDef> vars;
  protected Expression body;
  protected LetSymbol symbol;

  public Let(Token token, LinkedList<VarDef> vars, Expression body, ParserRuleContext context) {
    super(token, context);
    this.vars = vars;
    this.body = body;
  }

  public LinkedList<VarDef> getVars() {
    return vars;
  }

  public Expression getBody() {
    return body;
  }

  public void setVars(LinkedList<VarDef> vars) {
    this.vars = vars;
  }

  public void setBody(Expression body) {
    this.body = body;
  }

  public LetSymbol getSymbol() {
    return symbol;
  }

  public void setSymbol(LetSymbol symbol) {
    this.symbol = symbol;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
