/*
 * Fresco Play (Hackerrank) - Reference Types (hands-on)
 */

class _CipherDecipher {
    /**
     * This method is used to cipher the message (normal) by following steps.
     * Swap the cases of String - UpperCase letters to LowerCase letters and viceversa.
     * Reverse the String
     * Replace the spaces of string with a star character("*")
     * Replace the characters in the even positions of the string
     * Append any integer at the last in that String
     *
     * @param normal
     * @return the ciphered message
     */
    public String ciphering(String normal) {
        int n = normal.length();
        char[] c = normal.toCharArray();
        for (int i = 0; i < n; i++) {
            if (c[i] >= 65 && c[i] <= 90) {
                c[i] = (char) (c[i] + 32);
            } else if (c[i] >= 97 && c[i] <= 122) {  //
                c[i] = (char) (c[i] - 32);
            } else if (c[i] == 32) {    // space
                c[i] = '*';
            }
        }
        StringBuilder sb1 = new StringBuilder();
        sb1.append(new String(c));
        sb1.reverse();
        sb1.append("3");
        String[] res = sb1.toString().split("");
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                res[i] = Integer.toString((int) (res[i].charAt(0)));
            }
        }

        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            sb2.append(res[i]);
        }
        String str = sb2.toString();
        System.out.println(new String(sb1));
        return str;
    }

    /**
     * This method is used to get the normal text by the reverse process of ciphering.
     *
     * @param ciphered
     * @return the deciphered message
     */
    public String deciphering(String ciphered) {
        int n = ciphered.length();
        ciphered = ciphered.substring(0,n-1);
        return null;
    }

    public static void main(String[] args) {
        String normal = "Welcome to hackerrank";
        String ciphered = "?85O89*69R65*87O104*33I1043";

        _CipherDecipher cipherOrDecipher = new _CipherDecipher();
        System.out.println(cipherOrDecipher.ciphering(normal));
        //System.out.println(cipherOrDecipher.deciphering(ciphered));
    }

}
