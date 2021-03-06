
/**
 * This problem's task can be found at: https://codeforces.com/problemset/problem/1661/B
 */

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class GettingZero
{
    public static void main(String[] args) throws IOException
    {
        Reader input = new Reader();

        int tests = input.nextInt();

        while(tests > 0)
        {
            tests--;
            solve(input);
        }
    }

    public static void solve(Reader input) throws IOException
    {
        int num = input.nextInt();

        // Check if the number is already 0(mod 2^15).
        if(num == 0)
        {
            System.out.println(0);
            return;
        }

        int best = -num2Factors(num), numOfAdds = 0, numOfMults = 0;

        // Get the optimal number of add operations.
        for(int i = 1; i < 15; i++)
        {
            if(i - num2Factors(num + i) < best)
            {
                best = i - num2Factors(num + i);
                numOfAdds = i;
            }
        }

        // In order for the number to be 0(mod 2^15) it has
        // to have 15 factors that are 2. Therefore, if the
        // optimal number of add operations gives x 2-factors,
        // there must be an additional 15 - x multiply operations.
        numOfMults = 15 - num2Factors(num + numOfAdds);

        System.out.println(numOfAdds + numOfMults);
    }

    /**
     * Get the power of the 2 factor.
     * I.e. how many 2s are a factor of n.
     * 
     * @param n - the number
     * @return - the power of the 2 factor.
     */
    public static int num2Factors(int n)
    {
        int i = 0;

        while(n % 2 == 0)
        {
            i++;
            n /= 2;
        }

        return i;
    }

    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64];
            int cnt = 0, c;
            while((c = read()) != -1)
            {
                if(c == '\n')
                {
                    if(cnt != 0)
                    {
                        break;
                    }
                    else
                    {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while(c <= ' ')
            {
                c = read();
            }
            boolean neg = (c == '-');
            if(neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }
            while((c = read()) >= '0' && c <= '9');

            if(neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while(c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if(neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }
            while((c = read()) >= '0' && c <= '9');
            if(neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while(c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if(neg)
                c = read();

            do
            {
                ret = ret * 10 + c - '0';
            }
            while((c = read()) >= '0' && c <= '9');

            if(c == '.')
            {
                while((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if(neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if(bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if(bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if(din == null)
                return;
            din.close();
        }
    }
}