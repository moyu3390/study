package com.nijunyang.util;

import com.google.common.primitives.Ints;

import java.util.Arrays;

/**
 * base58编解码工具类
 */
public class Base58 {
    public static final char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
    private static final char ENCODED_ZERO = ALPHABET[0];
    private static final int[] INDEXES = new int[128];
    static {
        Arrays.fill(INDEXES, -1);
        for (int i = 0; i < ALPHABET.length; i++) {
            INDEXES[ALPHABET[i]] = i;
        }
    }

    public static void main(String[] args) {
//        Integer in = -1678567;
//        System.out.println(in.byteValue());
//        System.out.println(Base58.encode("25".getBytes(StandardCharsets.UTF_8)));
        System.out.println(Base58.encode(Ints.toByteArray(-1)));
        System.out.println(Base58.encode(Ints.toByteArray(-1678567)));
        System.out.println(Ints.fromByteArray(Base58.decode("7YXq9G")));
        System.out.println(Ints.fromByteArray(Base58.decode("7YPEAU")));
//
////        System.out.println(Base58.decode("r"));
////        System.out.println(Base58.decode("5xk"));
////        int memberSeq = Ints.fromByteArray(Base58.decode(""));
//        System.out.println(new String(Base58.decode("4pc"), StandardCharsets.UTF_8));
//        System.out.println(new String(Base58.decode("8ZRSAr5RfLr"), StandardCharsets.UTF_8));
//        System.out.println(Ints.fromByteArray(Base58.decode("8ZRSAr5RfLr")));
//        System.out.println(Ints.fromByteArray(Base58.decode("4pc")));
//        System.out.println(Ints.fromByteArray(Base58.decode("GBR2")));
//        System.out.println(Ints.fromByteArray(Base58.decode("4k8")));
//        System.out.println(new String(Base58.decode("r"), StandardCharsets.UTF_8));
//        System.out.println(new String(Base58.decode("5xk"), StandardCharsets.UTF_8));

//        System.out.println(Base58.encode("-1678567".getBytes(StandardCharsets.UTF_8)));
//        System.out.println(Base58.encode("758199863".getBytes(StandardCharsets.UTF_8)));
//        System.out.println(Ints.fromByteArray(Base58.decode("hkxw87tdBdsY")));
//        System.out.println(Ints.fromByteArray(Base58.decode("8ZRSAr5RfLr")));
    }


    /**
     * encode
     * @param input
     * @return
     */
    public static String encode(byte[] input) {
        if (input.length == 0) {
            return "";
        }
        // Count leading zeros.
        int zeros = 0;
        while (zeros < input.length && input[zeros] == 0) {
            ++zeros;
        }
        // Convert base-256 digits to base-58 digits (plus conversion to ASCII characters)
        // since we modify it in-place
        input = Arrays.copyOf(input, input.length);
        // upper bound
        char[] encoded = new char[input.length * 2];
        int outputStart = encoded.length;
        for (int inputStart = zeros; inputStart < input.length; ) {
            encoded[--outputStart] = ALPHABET[divmod(input, inputStart, 256, 58)];
            if (input[inputStart] == 0) {
                // optimization - skip leading zeros
                ++inputStart;
            }
        }
        // Preserve exactly as many leading encoded zeros in output as there were leading zeros in input.
        while (outputStart < encoded.length && encoded[outputStart] == ENCODED_ZERO) {
            ++outputStart;
        }
        while (--zeros >= 0) {
            encoded[--outputStart] = ENCODED_ZERO;
        }
        // Return encoded string (including encoded leading zeros).
        return new String(encoded, outputStart, encoded.length - outputStart);
    }

    /**
     * decode
     * @param input
     * @return
     */
    public static byte[] decode(String input) {
        if (input.length() == 0) {
            return new byte[0];
        }
        // Convert the base58-encoded ASCII chars to a base58 byte sequence (base58 digits).
        byte[] input58 = new byte[input.length()];
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            int digit = c < 128 ? INDEXES[c] : -1;
            if (digit < 0) {
                throw new IllegalArgumentException("InvalidCharacter " + c + ":" + i);
            }
            input58[i] = (byte) digit;
        }
        // Count leading zeros.
        int zeros = 0;
        while (zeros < input58.length && input58[zeros] == 0) {
            ++zeros;
        }
        // Convert base-58 digits to base-256 digits.
        byte[] decoded = new byte[input.length()];
        int outputStart = decoded.length;
        for (int inputStart = zeros; inputStart < input58.length; ) {
            decoded[--outputStart] = divmod(input58, inputStart, 58, 256);
            if (input58[inputStart] == 0) {
                // optimization - skip leading zeros
                ++inputStart;
            }
        }
        // Ignore extra leading zeroes that were added during the calculation.
        while (outputStart < decoded.length && decoded[outputStart] == 0) {
            ++outputStart;
        }
        // Return decoded data (including original number of leading zeros).
        return Arrays.copyOfRange(decoded, outputStart - zeros, decoded.length);
    }

    private static byte divmod(byte[] number, int firstDigit, int base, int divisor) {
        // this is just long division which accounts for the base of the input digits
        int remainder = 0;
        for (int i = firstDigit; i < number.length; i++) {
            int digit = (int) number[i] & 0xFF;
            int temp = remainder * base + digit;
            number[i] = (byte) (temp / divisor);
            remainder = temp % divisor;
        }
        return (byte) remainder;
    }
}
