import java.util.*;

class Fancy {

    static final long MOD = 1000000007;

    List<Long> arr;
    long mul = 1;
    long add = 0;

    public Fancy() {
        arr = new ArrayList<>();
    }

    public void append(int val) {
        long inv = modInverse(mul);
        long stored = ((val - add) % MOD + MOD) % MOD;
        stored = (stored * inv) % MOD;
        arr.add(stored);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= arr.size()) return -1;
        long val = arr.get(idx);
        val = (val * mul) % MOD;
        val = (val + add) % MOD;
        return (int) val;
    }

    long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    long modPow(long base, long exp) {
        long res = 1;
        base %= MOD;

        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }

        return res;
    }
}