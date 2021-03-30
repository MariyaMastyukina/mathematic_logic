interface Axioms {
    public default String firstAx(String a, String b) {
        return "(" + a + " -> (" + b + " -> " + a + "))";
    }
    public default String secondAx(String a, String b, String c) {
        return "((" + a + " -> " + b + ") -> ((" + a + " -> (" + b + " -> " + c + "))" + " -> " + "(" + a + " -> " + c + ")))";
    }
    public default String thirdAx(String a, String b) {
        return "(" + a + " -> " + "(" + b + " -> " + "(" + a + " & " + b + "))";
    }
    public default String forthAx(String a, String b) {
        return "((" + a + " & " + b + ") -> " + a + ")";
    }
    public default String fifthAx(String a, String b) {
        return "((" + a + " & " + b + ") -> " + b + ")";
    }
    public default String sixthAx(String a, String b) {
        return "(" + a + " -> (" + a + " | " + b + "))";
    }
    public default String seventhAx(String a, String b) {
        return "(" + b + " -> (" + a + " | " + b + "))";
    }
    public default String eighthAx(String a, String b, String c) {
        return "((" + a + " -> " + c + ") -> ((" + b + " -> " + c + ")" + " -> " + "((" + a + " | " + c + ") -> " + c + ")))";
    }
    public default String ninthAx(String a, String b) {
        return "((" + a + " -> " + b + ") -> ((" + a + " -> !" + b + ") -> !" + a + "))";
    }
    public default String tenthAx(String a, String b) {
        return "(" + a + " -> (!" + a + " -> " + b + "))";
    }

}
