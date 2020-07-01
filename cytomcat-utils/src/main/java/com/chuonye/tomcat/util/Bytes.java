/**
 * Copyright 2019 chuonye.com - 小创编程
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chuonye.tomcat.util;

/**
 * 字节数组功能类
 * 
 * @author wskwbog
 */
public final class Bytes {
    private Bytes() { }

    public static byte[] int2bytes(int v) {
        byte[] b = new byte[4];
        b[0] = (byte) (v >>> 24);
        b[1] = (byte) (v >>> 16);
        b[2] = (byte) (v >>>  8);
        b[3] = (byte) (v >>>  0);
        return b;
    }

    public static byte[] int2bytesLE(int v) {
        byte[] b = new byte[4];
        b[0] = (byte) (v >>>  0);
        b[1] = (byte) (v >>>  8);
        b[2] = (byte) (v >>> 16);
        b[3] = (byte) (v >>> 24);
        return b;
    }
    
    public static byte[] long2bytes(long v) {
        byte[] b = new byte[8];
        b[0] = (byte) (v >>> 56);
        b[1] = (byte) (v >>> 48);
        b[2] = (byte) (v >>> 40);
        b[3] = (byte) (v >>> 32);
        b[4] = (byte) (v >>> 24);
        b[5] = (byte) (v >>> 16);
        b[6] = (byte) (v >>>  8);
        b[7] = (byte) (v >>>  0);
        return b;
    }

    public static byte[] long2bytesLE(long v) {
        byte[] b = new byte[8];
        b[0] = (byte) (v >>>  0);
        b[1] = (byte) (v >>>  8);
        b[2] = (byte) (v >>> 16);
        b[3] = (byte) (v >>> 24);
        b[4] = (byte) (v >>> 32);
        b[5] = (byte) (v >>> 40);
        b[6] = (byte) (v >>> 48);
        b[7] = (byte) (v >>> 56);
        return b;
    }
    
    public static int bytes2int(byte[] b) {
        if (b.length != 4) {
            throw new IllegalArgumentException("The length of byte array must be 4");
        }
        return (int) bytes2long(b);
    }
    
    public static int bytes2intLE(byte[] b) {
        if (b.length != 4) {
            throw new IllegalArgumentException("The length of byte array must be 4");
        }
        return (int) bytes2longLE(b);
    }

    public static long bytes2long(byte[] b) {
        long retval = 0;
        for (int i = 0; i < b.length; i++) {
            retval += (b[i] & 0xFF) << ((b.length - i - 1) * 8);
        }
        return retval;
    }
    public static long bytes2longLE(byte[] b) {
        long retval = 0;
        for (int i = 0; i < b.length; i++) {
            retval += (b[i] & 0xFF) << (i * 8);
        }
        return retval;
    }

    public static String asHex(byte[] b) {
        return asHex(b, 0, b.length);
    }
    public static String asHex(byte[] b, int offset, int length) {
        StringBuilder hexStr = new StringBuilder();
        for (int i = offset; i < length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hexStr.append('0');
            }
            hexStr.append(hex).append(" ");
            if (i % 16 == 0) {
                hexStr.append("\r\n");
            }
        }
        return hexStr.toString();
    }
}
