public class FixLine {
    String newLine = "";
    String curLine = "";
    int curIndex = 0;
    public String fixLine(String curLine) {
        this.curLine = curLine;
        this.curIndex = 0;
        implication();
        return newLine;
    }
    private String implication() {
        newLine = disjunction();
        if (checkOperation("->")) {
            newLine = "(" + newLine + " -> " + implication() + ")";
        }
        return newLine;
    }
    private String disjunction() {
        newLine = conjunction();
        while (checkOperation("|")) {
            newLine = "(" + newLine + " | " + conjunction() + ")";
        }
        return newLine;
    }
    private String conjunction() {
        newLine = negation();
        while (checkOperation("&")) {
            newLine = "(" + newLine + " & " + negation() + ")";
        }
        return newLine;
    }
    private String negation() {
        if (checkOperation("(")){
            newLine = implication();
            checkOperation(")");
            return newLine;
        }
        if (checkOperation("!")) {
            return "!" + negation();
        }
        newLine = "";
        String element = "";
        if (curIndex < curLine.length() - 1) {
            element = curLine.substring(curIndex, curIndex + 1);
        } else {
            element = curLine.substring(curIndex);
        }
        while (element.matches("[0-9]+") || element.equals("'") || element.matches("[a-zA-z]+")) {
            newLine += element;
            curIndex++;
            if (curIndex < curLine.length() - 1) {
                element = curLine.substring(curIndex, curIndex + 1);
            } else {
                element = curLine.substring(curIndex);
            }
        }
        return newLine;
    }
    private boolean checkOperation(String operator) {
        if (curLine.startsWith(operator, curIndex)) {
            curIndex += operator.length();
            return true;
        } else {
            return false;
        }
    }
}
