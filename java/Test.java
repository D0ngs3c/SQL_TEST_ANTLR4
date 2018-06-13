import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;


public class Test{
    public static void main(String[] args) throws Exception{
            // read file content from file
            StringBuffer sb= new StringBuffer("");
            FileReader reader = new FileReader("E://SqlInput.txt");
            BufferedReader br = new BufferedReader(reader);
            String str = null;

            //按行读取语句，并按照语法解析
            while((str = br.readLine()) != null) {
                try {
                    String in = String.valueOf(str);
                    ANTLRInputStream input = new ANTLRInputStream(in); //快被弃用的方法
//                    CharStream input = CharStreams.fromFileName("E://SqlInput.txt"); // 从 antlr4.7 开始启用新的方法
                    SQLLexer lexer = new SQLLexer(input);
                    CommonTokenStream tokens = new CommonTokenStream(lexer);
                    SQLParser parser = new SQLParser(tokens);
                    ParseTree tree = parser.parse();

                    String s = tree.toStringTree(parser);
                    sb.append(s + "\r\n");
                }
                catch(Exception e) {
                        e.printStackTrace();
                    }
                }


            br.close();
            reader.close();

            String text = sb.toString();

            // write string to file
            File file = new File("E://SqlOutput.txt");
            if (!file.exists()) {
                file.delete();
            }
            FileWriter writer = new FileWriter("E://SqlOutput.txt");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(text + "\r\n");

            bw.close();
            writer.close();
        }

}
