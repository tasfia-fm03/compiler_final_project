class semanticanalyzer implements structure_astVisitor<Void>, ExprVisitor<Void> {

    private final Map<String, Integer> declared = new HashMap<>();
    private final Set<String> used = new HashSet<>();
    public void analyze(List<structure_ast> statements) {
        for (structure_ast stmt : statements) {
            stmt.accept(this);              
            
        }
    }

