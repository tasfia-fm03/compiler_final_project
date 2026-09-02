abstract class structure_ast {
    abstract <R> R accept(structure_astVisitor<R> visitor);
}

interface structure_astVisitor<R> {
    R visitVarDecl(VarDeclStmt structure_ast);
    R visitPrint(Printstructure_ast structure_ast);
}

class VarDeclStmt extends structure_ast {
    final String name;
    final Expr initializer;
    final int line;

    VarDeclStmt(String name, Expr initializer, int line) {
        this.name = name;
        this.initializer = initializer;
        this.line = line;
    }

    @Override
    public <R> R accept(structure_astVisitor<R> visitor) {
        return visitor.visitVarDecl(this);
    }
}

class Printstructure_ast extends structure_ast {
    final Expr expression;
    final int line;

    Printstructure_ast(Expr expression, int line) {
        this.expression = expression;
        this.line = line;
    }

    @Override
    public <R> R accept(structure_astVisitor<R> visitor) {
        return visitor.visitPrint(this);
    }
}

abstract class Expr {
    abstract <R> R accept(ExprVisitor<R> visitor);
}

interface ExprVisitor<R> {
    R visitBinary(BinaryExpr expr);
    R visitLiteral(LiteralExpr expr);
    R visitVariable(VariableExpr expr);
}

class BinaryExpr extends Expr {
    final Expr left;
    final banglalanguage operator;
    final Expr right;

    BinaryExpr(Expr left, banglalanguage operator, Expr right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public <R> R accept(ExprVisitor<R> visitor) {
        return visitor.visitBinary(this);
    }
}

class LiteralExpr extends Expr {
    final int value;

    LiteralExpr(int value) {
        this.value = value;
    }

    @Override
    public <R> R accept(ExprVisitor<R> visitor) {
        return visitor.visitLiteral(this);
    }
}

class VariableExpr extends Expr {
    final String name;
    final int line;

    VariableExpr(String name, int line) {
        this.name = name;
        this.line = line;
    }

    @Override
    public <R> R accept(ExprVisitor<R> visitor) {
        return visitor.visitVariable(this);
    }
}
