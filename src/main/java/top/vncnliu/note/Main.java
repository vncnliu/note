package top.vncnliu.note;

import java.io.*;
import java.util.*;

/**
 * User: liuyq
 * Date: 2018/7/2
 * Description:
 */
public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<Character,List<String>> titleCharIndex = new HashMap<>();
        HashMap<Character,List<String>> contentCharIndex = new HashMap<>();

        File file = new File(System.getProperty("user.dir")+"/sources");

        for (File file1 : Objects.requireNonNull(file.listFiles(pathname -> {
            String fileName = pathname.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            return "md".equals(suffix);
        }))) {
            String fileName = file1.getName();
            String title = fileName.substring(0,fileName.lastIndexOf("."));
            buildIndex(titleCharIndex, fileName, title);
            InputStreamReader streamReader = new InputStreamReader(new FileInputStream(file1));
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            while (true){
                String content = bufferedReader.readLine();
                if(content!=null){
                    buildIndex(contentCharIndex, fileName, content);
                }else {
                    break;
                }
            }
        }
        System.out.println(titleCharIndex);

    }

    private static void buildIndex(HashMap<Character, List<String>> contentCharIndex, String fileName, String content) {
        for (char c : content.toCharArray()) {
            List<String> temp = contentCharIndex.get(c);
            temp = temp==null?new ArrayList<>():temp;
            temp.add(fileName);
            contentCharIndex.put(c,temp);
        }
    }
}
