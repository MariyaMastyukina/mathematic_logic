import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FixLine fix = new FixLine();
        String usl;
        String taskProof;
        String[] hyps;
        List<String> proofs = new ArrayList<>();
        Map<Integer, String> mapProofs = new HashMap<>();
        String line;
        String hypotyses;
        String currentLine;
            usl = scanner.nextLine();

            if (usl == null || usl.replace(" ", "").equals("")) {
                return;
            }
            if (usl.matches("\t") || usl.matches("\n")) {
                return;
            }

            if (usl.equals("|-")) {
                return;
            }
            if (!usl.contains("|-")) {
                return;
            }
            if (usl.startsWith("|-")) {
                hypotyses = "";
                taskProof = usl.substring(2);
                hyps = new String[]{""};
            } else {
                String[] str = usl.split("\\|-");
                if (str[0].charAt(str[0].length() - 1) != ' ') {
                    hypotyses = str[0] + " ";
                } else {
                    hypotyses = str[0];
                }
                if (str[0].contains(", ")) {
                    hyps = str[0].split(", ");
                } else {
                    hyps = new String[]{str[0]};
                }
                if (str.length != 1) {
                    taskProof = str[1];
                } else {
                    taskProof = "";
                }

            }
            taskProof = taskProof.replaceAll(" ", "");
            line = scanner.nextLine();
            if (line == null) {
                return;
            }
            currentLine = line.replace("\t", "").replace("\n", "").replaceAll(" ", "");

        while (!currentLine.equals(taskProof)) {

                if (!currentLine.equals("") && !proofs.contains(currentLine)) {
                    proofs.add(fix.fixLine(currentLine));
                    mapProofs.put(proofs.indexOf(fix.fixLine(currentLine)), fix.fixLine(currentLine));
                }


        try {
            line = scanner.nextLine();
        } catch (NoSuchElementException e){
            break;
        }
            if (line == null) {
                break;
            }
            if (line.replace("\t", "").replace("\n", "").replace(" ", "").equals("")) {
                break;
            }

            currentLine = line.replace("\t", "").replace("\n", "").replaceAll(" ", "");

        }
        if (!proofs.contains(currentLine) && !currentLine.equals("")) {
            proofs.add(fix.fixLine(currentLine));
        }


//        for(String proof:proofs) {
//            System.out.println(proof.toLowerCase());
//        }
        Proof proof = new Proof(proofs, mapProofs, hyps);
        if (!taskProof.equals("")) {
            System.out.println(hypotyses + "|- !!" + fix.fixLine(taskProof));
        }
        proof.proof();
//        System.out.println(proof.isMP(proofs.get(2), 2));
//        Proof proof = new Proof(null, null);
//

//         while (scanner.hasNextLine()) {
//             String usl = scanner.nextLine();
//             System.out.println(proof.isTenthAx(fix.fixLine(usl.replaceAll(" ", ""))));
//             System.out.println(proof.getTenthV());
//////             String a = fix.fixLine(scanner.nextLine().replaceAll(" ", ""));
//////             String b = fix.fixLine(scanner.nextLine().replaceAll(" ", ""));
//////             String impl = fix.fixLine(scanner.nextLine().replaceAll(" ", ""));
//////             System.out.println("(" + a + " -> " + b +")");
//////             System.out.println(impl);
//////             System.out.println(("(" + a + " -> " + b +")").equals(impl));
//         }
    }
}
