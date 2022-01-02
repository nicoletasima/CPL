package cool.structures;

public class IdSymbol extends Symbol {
  private ClassSymbol type;

  public IdSymbol(String name) {
    super(name);
  }

  public void setType(ClassSymbol type) {
    this.type = type;
  }

  public ClassSymbol getType() {
    return type;
  }
}
