import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Proof implements Axioms {
    List<String> proofsKIV;
    String tenthV = "";
    Map<Integer, String> hashProof;
    List<String> hyps;

    public Proof(List<String> proofsKIV, Map<Integer, String> hashProof, String[] hyps) {
        this.proofsKIV = proofsKIV;
        this.hashProof = hashProof;
        this.hyps = Arrays.asList(hyps);
    }

    public void proof() {
        for (int i = 0; i < proofsKIV.size(); i++) {
            String currentProof = proofsKIV.get(i);
            if (hyps.contains(currentProof)) {
                proof1(currentProof);
            } else if (!isMP(currentProof, i)) {
                if (isTenthAx(currentProof)) {
                    proof2(getTenthV());
                } else {
                    proof1(currentProof);
                }
            }
        }
    }

    public void proof1(String a) {
        List<String> proofs = new ArrayList<>();
//        System.out.println(a + " |- !!" + a);
        proofs = specialWrite(a, proofs);
        String b = "!" + a;
        String current = firstAx(proofs.get(0), b);
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(0), current);
        proofs = specialWrite(current, proofs);
        current = firstAx(b, b);
        proofs = specialWrite(current, proofs);
        current = secondAx(b, "(" + b + " -> " + b + ")", b);
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(3), current);
        proofs = specialWrite(current, proofs);
        current = firstAx(b, "(" + b + " -> " + b + ")");
        proofs = specialWrite(current, proofs);
        current = MP(current, proofs.get(5));
        proofs = specialWrite(current, proofs);
        current = ninthAx(b, a);
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(2), current);
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(7), current);
        specialWrite(current, proofs);
    }

    public void proof2(String a) {
        List<String> proofs = new ArrayList<>();
        proofs.add(a);//0
        //b = !a
        //a = (A&!!A)
        String b = "!" + a;
        String current = firstAx(proofs.get(0), "!" + b);//1
        proofs = specialWrite(current, proofs);
        String element = "!(!" + b + " -> " + proofs.get(0) + ")";
        current = firstAx(proofs.get(1), element);//2
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(1), proofs.get(2));//3
        proofs = specialWrite(current, proofs);
        current = firstAx(element, proofs.get(0));//4
        proofs = specialWrite(current, proofs);
        current = firstAx(element, element);//5
        proofs = specialWrite(current, proofs);
        current = firstAx(element, "(" + element + " -> " + element + ")");//6
        proofs = specialWrite(current, proofs);
        current = secondAx(element, "(" + element + " -> " + element + ")", element);//7
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(5), proofs.get(7));//8
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(6), proofs.get(8));//9
        proofs = specialWrite(current, proofs);
        current = ninthAx(proofs.get(0), element.substring(1));//10
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(10), element);//11
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(10), proofs.get(11));//12
        proofs = specialWrite(current, proofs);
        current = secondAx(element, proofs.get(1), "((" + proofs.get(0) + " -> " + element + ") -> " + b + ")");//13
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(3), proofs.get(13));//14
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(12), proofs.get(14));//15
        proofs = specialWrite(current, proofs);
        current = secondAx(element, "(" + proofs.get(0) + " -> " + element + ")", b);//16
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(4), proofs.get(16));//17
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(15), proofs.get(17));//18
        proofs = specialWrite(current, proofs);
        current = tenthAx(b, proofs.get(0));//19
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(19), element);//20
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(19), proofs.get(20));//21
        proofs = specialWrite(current, proofs);
        current = secondAx(element, b, element.substring(1));//22
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(18), proofs.get(22));//23
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(21), proofs.get(23));//24
        proofs = specialWrite(current, proofs);
        current = ninthAx(element, element.substring(1));//25
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(24), proofs.get(25));//26
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(9), proofs.get(26));
        specialWrite(current, proofs);//27
    }

    public void proof3(String a, String b) {
        String impl = "(" + a + " -> " + b + ")";
        String negA = "!" + a;
        String negB = "!" + b;
//        System.out.println(negB);
        List<String> proofs = new ArrayList<>();
        String current = firstAx(negB, impl);//1
        proofs = specialWrite(current, proofs);
        current = firstAx(negB, a);//2
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(1), negB);//3
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(1), proofs.get(2));//4
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(1), impl);//5
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(4), negB);//6
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(4), proofs.get(5));//7
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, proofs.get(1), "(" + impl + " -> " + proofs.get(1) + ")");//8
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(3), proofs.get(7));//9
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(6), proofs.get(8));//10
        proofs = specialWrite(current, proofs);
        current = secondAx(impl, negB, "(" + a + " -> " + negB + ")");//11
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(10), negB);//12
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(10), proofs.get(11));//13
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, "(" + impl + " -> " + negB + ")", "((" + impl + " -> (" + negB + " -> (" + a + " -> " + negB + "))) -> (" + impl + " -> " + "(" + a + " -> " + negB + ")))");//14
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(0), proofs.get(13));//15
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(12), proofs.get(14));//16
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, "(" + impl + " -> (" + negB + " -> (" + a + " -> " + negB + ")))", "(" + impl + " -> " + "(" + a + " -> " + negB + "))");//17
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(9), proofs.get(16));//18
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(15), proofs.get(17));//19
        proofs = specialWrite(current, proofs);
        current = ninthAx(a, b);//20
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(19), negB);//21
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(19), proofs.get(20));//22
        proofs = specialWrite(current, proofs);
        current = secondAx(impl, "(" + a + " -> " + negB + ")", negA);//23
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(22), negB);//24
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(22), proofs.get(23));//25
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, "(" + impl + " -> " + "(" + a + " -> " + negB + "))", "((" + impl + " -> " + "((" + a + " -> " + negB + ") -> " + negA + ")) -> (" + impl + " -> " + negA + "))");//26
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(18), proofs.get(25));//27
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(24), proofs.get(26));//28
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, "(" + impl + " -> " + "((" + a + " -> " + negB + ") -> " + negA + "))", "((" + a + " -> " + b + ") -> " + negA + ")");//29
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(21), proofs.get(28));//30
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(27), proofs.get(29));//31
        proofs = specialWrite(current, proofs);
        current = "!" + negA;//32
        proofs = specialWrite(current, proofs);
        current = firstAx("!" + negA, negB);//33
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(31), proofs.get(32));//34
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(31), impl);//35
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(34), negB);//36
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(34), proofs.get(35));//37
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, "!" + negA, "(" + impl + " -> !" + negA + ")");//38
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(33), proofs.get(37));//39
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(36), proofs.get(38));//40
        proofs = specialWrite(current, proofs);
        current = tenthAx(negA, "!" + impl);//41
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(40), negB);//42
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(40), proofs.get(41));//43
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(40), impl);//44
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(43), negB);//45
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(43), proofs.get(44));//46
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, proofs.get(40), "(" + impl + " -> (" + negA + " -> (!" + negA + " -> !" + impl + ")))");//47
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(42), proofs.get(46));//48
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(45), proofs.get(47));//49
        proofs = specialWrite(current, proofs);
        current = secondAx(impl, negA, "(!" + negA + " -> !" + impl + ")");//50
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(49), negB);//51
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(49), proofs.get(50));//52
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, "(" + impl + " -> " + negA + ")", "((" + impl + " -> (" + negA + " -> (!" + negA + " -> !" + impl + "))) -> (" + impl + " -> (!" + negA + " -> !" + impl + ")))");//53
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(30), proofs.get(52));//54
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(51), proofs.get(53));//55
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, "(" + impl + " -> (" + negA + " -> (!" + negA + " -> !" + impl + ")))", "(" + impl + " -> (!" + negA + " -> !" + impl + "))");//56
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(48), proofs.get(55));//57
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(54), proofs.get(56));//58
        proofs = specialWrite(current, proofs);
        current = secondAx(impl, "!" + negA, "!" + impl);//59
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(58), negB);//60
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(58), proofs.get(59));//61
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, "(" + impl + " -> !!" + a + ")", "((" + impl + " -> (!" + negA + " -> !" + impl + ")) -> (" + impl + " -> !" + impl + "))");
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(39), proofs.get(61));//63
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(60), proofs.get(62));//64
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, "(" + impl + " -> (!" + negA + " -> !" + impl + "))", "(" + impl + " -> !" + impl + ")");//65
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(57), proofs.get(64));//66
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(63), proofs.get(65));//67
        proofs = specialWrite(current, proofs);
        current = "!!" + impl;//68
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(67), negB);//69
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(67), proofs.get(68));//70
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(67), impl);//71
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(70), negB);//72
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(70), proofs.get(71));//73
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, proofs.get(67), "(" + impl + " -> " + proofs.get(67) + ")");//74
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(69), proofs.get(73));//75
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(72), proofs.get(74));//76
        proofs = specialWrite(current, proofs);
        current = ninthAx(impl, "!" + impl);//77
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(76), negB);//78
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(76), proofs.get(77));//79
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, "(" + impl + " -> " + "!" + impl + ")", "((" + impl + " -> " + proofs.get(67) + ") -> !" + impl + ")");//80
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(66), proofs.get(79));//81
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(78), proofs.get(80));//82
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, "(" + impl + " -> " + proofs.get(67) + ")", "!" + impl);//83
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(75), proofs.get(82));//84
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(81), proofs.get(83));//85
        proofs = specialWrite(current, proofs);
        current = tenthAx("!" + impl, negA);//86
        proofs = specialWrite(current, proofs);
        current = firstAx(proofs.get(85), negB);//87
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(85), proofs.get(86));//88
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, "!" + impl, "(" + proofs.get(67) + " -> " + negA + ")");//89
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(84), proofs.get(88));//90
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(87), proofs.get(89));
        proofs = specialWrite(current, proofs);
        current = secondAx(negB, proofs.get(67), negA);//91
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(69), proofs.get(91));//92
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(90), proofs.get(92));//93
        proofs = specialWrite(current, proofs);
        current = ninthAx(negB, negA);//94
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(93), proofs.get(94));//95
        proofs = specialWrite(current, proofs);
        current = MP(proofs.get(33), proofs.get(95));//96
        specialWrite(current, proofs);
    }

    public String MP(String a, String result) {
        String delete = "(" + a + " -> ";
        String newResult;
        if (result.startsWith(delete)) {
            newResult = result.substring(a.length() + 5, result.length() - 1);
        } else {
            newResult = result;
        }
//        String newResult = result.replaceFirst(delete, "");
        return newResult;
    }

    public List<String> specialWrite(String str, List<String> proofs) {
        System.out.println(str);
        proofs.add(str);
        return proofs;
    }

    public boolean isMP(String currentProof, int index) {
        for (int i = 0; i < index; i++) {
            String impl = "(" + proofsKIV.get(i) + " -> " + currentProof + ")";
            if (hashProof.containsValue(impl)) {
                proof3(proofsKIV.get(i), currentProof);
                return true;
            }
        }
        return false;
    }
    public boolean isTenthAx(String str) {
        if (str.startsWith("(!!")) {
            String newLine = str.substring(3, str.length() - 1);
            String[] elements = newLine.split(" -> ");
            if (elements.length % 2 == 0) {
                int lastIndex = elements.length / 2;
                StringBuilder left = new StringBuilder(elements[0]);
                StringBuilder right = new StringBuilder(elements[lastIndex]);
                for (int i = 1; i < lastIndex; i++) {
                    left.append(" -> ").append(elements[i]);
                }
                for (int i = lastIndex + 1; i < elements.length; i++) {
                    right.append(" -> ").append(elements[i]);
                }
                setTenthV(left.toString());
                return ((left.toString()).equals(right.toString()));
            }
            return false;
        }
        return false;
    }

//    public boolean isTenthAx(String str) {
//        if (str.startsWith("(!!")) {
//            int check = 0;
//            int pos = 3;
//            while (str.charAt(pos) == '!') {
//                pos++;
//            }
//            if (str.charAt(pos) != '(') {
//                int position = 3;
//                String element = str.substring(3, 4);
//                String line = "";
//                while (element.matches("[0-9]+") || element.equals("'") || element.matches("[a-zA-z]+") || element.equals("!")) {
//                    line += element;
//                    position++;
//                    if (position < str.length() - 1) {
//                        element = str.substring(position, position + 1);
//                    } else {
//                        return false;
//                    }
//                }
//                setTenthV(line);
//                return line.equals(str.substring(position + 4, str.length() - 1)) && str.startsWith(" -> ", position);
//            }
//            for (int i = pos; i < str.length(); i++) {
//                if (str.charAt(i) == '(') {
//                    check++;
//                }
//                if (str.charAt(i) == ')') {
//                    check--;
//                }
//                if (check == 0) {
//                    setTenthV(str.substring(3, i + 1));
//                    return (getTenthV()).equals(str.substring(i + 5, str.length() - 1)) && str.startsWith(" -> ", i + 1);
//                }
//            }
//        }
//        return false;
//    }

    public void setTenthV(String tenthV) {
        this.tenthV = tenthV;
    }

    public String getTenthV() {
        return tenthV;
    }
}
