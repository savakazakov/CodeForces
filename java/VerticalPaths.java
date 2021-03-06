
/**
 * This problem's task can be found at: https://codeforces.com/problemset/problem/1675/D
 */

import java.util.ArrayList;
import java.util.List;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class VerticalPaths
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
        int n = input.nextInt();
        int numOfLeaves = 0;

        int[] tree = new int[n + 1];
        boolean[] inner = new boolean[n + 1];

        // Get the input.
        for(int i = 1; i < n + 1; i++)
        {
            tree[i] = input.nextInt();

            if(inner[tree[i]] == false)
                numOfLeaves++;

            inner[tree[i]] = true;
        }

        if(n == 1)
        {
            System.out.println("1\n1\n1");
            return;
        }

        boolean[] visited = new boolean[n + 1];
        List<Integer> lastPath;

        // Print the number of paths.
        System.out.println(n - numOfLeaves);

        for(int i = 1; i <= n; i++)
        {
            if(inner[i])
                continue;
            visited[i] = true;
            lastPath = new ArrayList<>();
            lastPath.add(i);

            int v = i;
            while(!visited[tree[v]] && tree[v] != v)
            {
                v = tree[v];
                visited[v] = true;
                lastPath.add(v);
            }

            System.out.println(lastPath.size());
            for(int j = lastPath.size() - 1; j >= 0; j--)
                System.out.println(lastPath.get(j));
        }
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
            byte[] buf = new byte[64]; // line length
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