class SemanticAnalyzer implements StmtVisitor<Void>, ExprVisitor<Void> {

    private final Map<String, Integer> declared = new HashMap<>();
    private final Set<String> used = new HashSet<>();

    public void analyze(List<Stmt> statements) {
        for (Stmt stmt : statements) {
            stmt.accept(this);
        }
        for (String name : declared.keySet()) {
            if (!used.contains(name)) {
                System.out.println("Warning: Variable '" + name +
                        "' declared at line " + declared.get(name) + " but never used.");
            }
        }
    }

    @Override
    public Void visitVarDecl(VarDeclStmt stmt) {
        if (declared.containsKey(stmt.name)) {
            throw new RuntimeException("Semantic Error [line " + stmt.line +
                    "]: Variable '" + stmt.name + "' is already declared.");
        }
        stmt.initializer.accept(this);
        declared.put(stmt.name, stmt.line);
        return null;
    }

    @Override
    public Void visitPrint(PrintStmt stmt) {
        stmt.expression.accept(this);
        return null;
    }

    @Override
    public Void visitBinary(BinaryExpr expr) {
        expr.left.accept(this);
        expr.right.accept(this);
        return null;
    }

    @Override
    public Void visitLiteral(LiteralExpr expr) {
        return null;
    }

    @Override
    public Void visitVariable(VariableExpr expr) {
        if (!declared.containsKey(expr.name)) {
            throw new RuntimeException("Semantic Error [line " + expr.line +
                    "]: Variable '" + expr.name + "' is not declared.");
        }
        used.add(expr.name);
        return null;
    }
}