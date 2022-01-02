package cool.structures;

public class CaseSymbol implements Scope {
  protected Scope parent;
  protected IdSymbol variable;

  public CaseSymbol(Scope parent) {
    this.parent = parent;
  }

  @Override
  public boolean add(Symbol sym) {
    if (!(sym instanceof IdSymbol)) {
      return false;
    }

    variable = (IdSymbol)sym;
    return true;
  }

  @Override
  public Symbol lookup(String str) {
    if (variable.getName().equals(str)) {
      return variable;
    }

    return parent.lookup(str);
  }

  @Override
  public Scope getParent() {
    return parent;
  }
}
