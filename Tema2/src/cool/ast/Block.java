package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class Block extends Expression {
  LinkedList<Expression> instructions;


  public Block(Token token, LinkedList<Expression> instructions, ParserRuleContext context) {
    super(token, context);
    this.instructions = instructions;
  }

  public LinkedList<Expression> getInstructions() {
    return instructions;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
