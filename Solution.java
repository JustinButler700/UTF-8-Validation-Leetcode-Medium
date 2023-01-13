// Justin Butler
class Solution {
    public boolean validUtf8(int[] data) {
        byte bytes = 0;
        /* 
        a given string can have between 1 to 4 bytes. we are representing these based on their power
        Example: 2^0, 2^1, 2^2, 2^3
        We know the power based on the prefix of our binary
        if prefix == 11110, then we know it's UTF-8, and 2^3.
        */
        for (int i: data) {
            /*
            if bytes is at it's default value, we either have data.length==1 
            or we need to set the number of bytes.
            */
            if (bytes == 0) {
                //Check prefix of binary
                if (i >> 3 == 0b11110) {
                    bytes = 3; // 2^3
                } else if (i >> 4 == 0b1110) {
                    bytes = 2;
                } else if (i >> 5 == 0b110) {
                    bytes = 1;
                } else if (i >> 7 == 0b0) {
                    bytes = 0;
                } else {
                    //Handle edge cases, such as data = {255}
                    return false;
                }
            } else {
                //Followed by n-1 bytes with the most significant 2 bits being 10.
                if (i >> 6 == 0b10) {
                    //Each neighboring UTF-8 data will be 1 byte less than the first
                    bytes--;
                } else {
                    return false;
                }
            }
        }
        return bytes == 0;

    }
}
