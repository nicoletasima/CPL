package cool.ast;

import cool.structures.CaseSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class Case extends Expression {
  protected Expression cond;
  protected LinkedList<CaseBranch> cases;
  protected CaseSymbol symbol;

  public Case(Token token, Expression cond, LinkedList<CaseBranch> cases, ParserRuleContext context) {
    super(token, context);
    this.cond = cond;
    this.cases = cases;
  }

  public Expression getCond() {
    return cond;
  }

  public LinkedList<CaseBranch> getCases() {
    return cases;
  }

  public void setCond(Expression cond) {
    this.cond = cond;
  }

  public void setCases(LinkedList<CaseBranch> cases) {
    this.cases = cases;
  }

  public CaseSymbol getSymbol() {
    return symbol;
  }

  public void setSymbol(CaseSymbol symbol) {
    this.symbol = symbol;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
