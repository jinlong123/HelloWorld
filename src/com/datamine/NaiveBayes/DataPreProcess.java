package com.datamine.NaiveBayes;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by JinLong on 2017/3/31.
 */
public class DataPreProcess {
    private static ArrayList<String> stopWordsArray = new ArrayList<String>();

    /**
     * 输入文件的路径，处理数据
     * @param srcDir 文件目录的绝对路径
     * @param desDir 清洗后的文件路径
     * @throws Exception
     */
    public void doProcess(String srcDir) throws Exception{

        File fileDir = new File(srcDir);
        if(!fileDir.exists()){
            System.out.println("文件不存在!");
            return ;
        }

        String subStrDir = srcDir.substring(srcDir.lastIndexOf('/'));
        String dirTarget = srcDir+"/../../processedSample"+subStrDir;
        File fileTarget = new File(dirTarget);

        if(!fileTarget.exists()){
            //注意processedSample需要先建立目录建出来，否则会报错，因为母目录不存在
            boolean mkdir = fileTarget.mkdir();
        }

        File[] srcFiles = fileDir.listFiles();

        for(int i =0 ;i <srcFiles.length;i++){

            String fileFullName = srcFiles[i].getCanonicalPath(); //CanonicalPath不但是全路径，而且把..或者.这样的符号解析出来。
            String fileShortName = srcFiles[i].getName(); //文件名

            if(!new File(fileFullName).isDirectory()){ //确认子文件名不是目录，如果是可以再次递归调用
                System.out.println("开始预处理："+fileFullName);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(dirTarget+"/"+fileShortName);

                createProcessFile(fileFullName,stringBuilder.toString());

            }else{
                fileFullName = fileFullName.replace("\\", "/");
                doProcess(fileFullName);
            }
        }
    }

    /**
     * 进行文本预处理生成目标文件
     * @param srcDir 源文件文件目录的绝对路径
     * @param targetDir 生成目标文件的绝对路径
     * @throws Exception
     */
    private void createProcessFile(String srcDir, String targetDir) throws Exception {

        FileReader srcFileReader = new FileReader(srcDir);
        FileWriter targetFileWriter = new FileWriter(targetDir);
        BufferedReader srcFileBR = new BufferedReader(srcFileReader);
        String line,resLine;

        while((line = srcFileBR.readLine()) != null){
            resLine = lineProcess(line);
            if(!resLine.isEmpty()){
                //按行写，一行写一个单词
                String[] tempStr = resLine.split(" ");
                for(int i =0; i<tempStr.length ;i++){
                    if(!tempStr[i].isEmpty())
                        targetFileWriter.append(tempStr[i]+"\n");
                }
            }
        }

        targetFileWriter.flush();
        targetFileWriter.close();
        srcFileReader.close();
        srcFileBR.close();

    }

    /**
     * 对每行字符串进行处理，主要是词法分析、去停用词和stemming(去除时态)
     * @param line 待处理的一行字符串
     * @param stopWordsArray 停用词数组
     * @return String 处理好的一行字符串，是由处理好的单词重新生成，以空格为分隔符
     */
    private String lineProcess(String line) {

        /*
         * step1
         * 英文词法分析，去除数字、连字符、标点符号、特殊字符，
         * 所有大写字符转换成小写，可以考虑使用正则表达式
         */
        String res[] = line.split("[^a-zA-Z]");

        //step2 去停用词，大写转换成小写
        //step3 Stemmer.run()
        String resString = new String();

        for(int i=0;i<res.length;i++){
            if(!res[i].isEmpty() && !stopWordsArray.contains(res[i].toLowerCase()))
                resString += " " + Stemmer.run(res[i].toLowerCase()) + " ";
        }

        return resString;
    }

    /**
     * 用stopWordsArray构造停用词的ArrayList容器
     * @param stopwordsPath
     * @throws Exception
     */
    private static void stopWordsToArray(String stopwordsPath) throws Exception {

        FileReader stopWordsReader = new FileReader(stopwordsPath);
        BufferedReader stopWordsBR = new BufferedReader(stopWordsReader);
        String stopWordsLine = null;

        //用stopWordsArray构造停用词的ArrayList容器
        while((stopWordsLine = stopWordsBR.readLine()) != null){
            if(!stopWordsLine.isEmpty())
                stopWordsArray.add(stopWordsLine);
        }

        stopWordsReader.close();
        stopWordsBR.close();
    }

    public static void main(String[] args) throws Exception{

        DataPreProcess dataPrePro = new DataPreProcess();
        String srcDir = "F:/DataMiningSample/orginSample";

        String stopwordsPath = "F:/DataMiningSample/stopwords.txt";

        stopWordsToArray(stopwordsPath);

        dataPrePro.doProcess(srcDir);
    }

}

class Stemmer
{  private char[] b;
    private int i,     /* offset into b */
            i_end, /* offset to end of stemmed word */
            j, k;
    private static final int INC = 50;
    /* unit of size whereby b is increased */
    public Stemmer()
    {  b = new char[INC];
        i = 0;
        i_end = 0;
    }

    /**
     * Add a character to the word being stemmed.  When you are finished
     * adding characters, you can call stem(void) to stem the word.
     */

    public void add(char ch)
    {  if (i == b.length)
    {  char[] new_b = new char[i+INC];
        for (int c = 0; c < i; c++) new_b[c] = b[c];
        b = new_b;
    }
        b[i++] = ch;
    }


    /** Adds wLen characters to the word being stemmed contained in a portion
     * of a char[] array. This is like repeated calls of add(char ch), but
     * faster.
     */

    public void add(char[] w, int wLen)
    {  if (i+wLen >= b.length)
    {  char[] new_b = new char[i+wLen+INC];
        for (int c = 0; c < i; c++) new_b[c] = b[c];
        b = new_b;
    }
        for (int c = 0; c < wLen; c++) b[i++] = w[c];
    }

    /**
     * After a word has been stemmed, it can be retrieved by toString(),
     * or a reference to the internal buffer can be retrieved by getResultBuffer
     * and getResultLength (which is generally more efficient.)
     */
    public String toString() { return new String(b,0,i_end); }

    /**
     * Returns the length of the word resulting from the stemming process.
     */
    public int getResultLength() { return i_end; }

    /**
     * Returns a reference to a character buffer containing the results of
     * the stemming process.  You also need to consult getResultLength()
     * to determine the length of the result.
     */
    public char[] getResultBuffer() { return b; }

   /* cons(i) is true <=> b[i] is a consonant. */

    private final boolean cons(int i)
    {  switch (b[i])
    {  case 'a': case 'e': case 'i': case 'o': case 'u': return false;
        case 'y': return (i==0) ? true : !cons(i-1);
        default: return true;
    }
    }

   /* m() measures the number of consonant sequences between 0 and j. if c is
      a consonant sequence and v a vowel sequence, and <..> indicates arbitrary
      presence,

         <c><v>       gives 0
         <c>vc<v>     gives 1
         <c>vcvc<v>   gives 2
         <c>vcvcvc<v> gives 3
         ....
   */

    private final int m()
    {  int n = 0;
        int i = 0;
        while(true)
        {  if (i > j) return n;
            if (! cons(i)) break; i++;
        }
        i++;
        while(true)
        {  while(true)
        {  if (i > j) return n;
            if (cons(i)) break;
            i++;
        }
            i++;
            n++;
            while(true)
            {  if (i > j) return n;
                if (! cons(i)) break;
                i++;
            }
            i++;
        }
    }

   /* vowelinstem() is true <=> 0,...j contains a vowel */

    private final boolean vowelinstem()
    {  int i; for (i = 0; i <= j; i++) if (! cons(i)) return true;
        return false;
    }

   /* doublec(j) is true <=> j,(j-1) contain a double consonant. */

    private final boolean doublec(int j)
    {  if (j < 1) return false;
        if (b[j] != b[j-1]) return false;
        return cons(j);
    }

   /* cvc(i) is true <=> i-2,i-1,i has the form consonant - vowel - consonant
      and also if the second c is not w,x or y. this is used when trying to
      restore an e at the end of a short word. e.g.

         cav(e), lov(e), hop(e), crim(e), but
         snow, box, tray.

   */

    private final boolean cvc(int i)
    {  if (i < 2 || !cons(i) || cons(i-1) || !cons(i-2)) return false;
        {  int ch = b[i];
            if (ch == 'w' || ch == 'x' || ch == 'y') return false;
        }
        return true;
    }

    private final boolean ends(String s)
    {  int l = s.length();
        int o = k-l+1;
        if (o < 0) return false;
        for (int i = 0; i < l; i++) if (b[o+i] != s.charAt(i)) return false;
        j = k-l;
        return true;
    }

   /* setto(s) sets (j+1),...k to the characters in the string s, readjusting
      k. */

    private final void setto(String s)
    {  int l = s.length();
        int o = j+1;
        for (int i = 0; i < l; i++) b[o+i] = s.charAt(i);
        k = j+l;
    }

   /* r(s) is used further down. */

    private final void r(String s) { if (m() > 0) setto(s); }

   /* step1() gets rid of plurals and -ed or -ing. e.g.

          caresses  ->  caress
          ponies    ->  poni
          ties      ->  ti
          caress    ->  caress
          cats      ->  cat

          feed      ->  feed
          agreed    ->  agree
          disabled  ->  disable

          matting   ->  mat
          mating    ->  mate
          meeting   ->  meet
          milling   ->  mill
          messing   ->  mess

          meetings  ->  meet

   */

    private final void step1()
    {  if (b[k] == 's')
    {  if (ends("sses")) k -= 2; else
    if (ends("ies")) setto("i"); else
    if (b[k-1] != 's') k--;
    }
        if (ends("eed")) { if (m() > 0) k--; } else
        if ((ends("ed") || ends("ing")) && vowelinstem())
        {  k = j;
            if (ends("at")) setto("ate"); else
            if (ends("bl")) setto("ble"); else
            if (ends("iz")) setto("ize"); else
            if (doublec(k))
            {  k--;
                {  int ch = b[k];
                    if (ch == 'l' || ch == 's' || ch == 'z') k++;
                }
            }
            else if (m() == 1 && cvc(k)) setto("e");
        }
    }

   /* step2() turns terminal y to i when there is another vowel in the stem. */

    private final void step2() { if (ends("y") && vowelinstem()) b[k] = 'i'; }

   /* step3() maps double suffices to single ones. so -ization ( = -ize plus
      -ation) maps to -ize etc. note that the string before the suffix must give
      m() > 0. */

    private final void step3() { if (k == 0) return; /* For Bug 1 */ switch (b[k-1])
    {
        case 'a': if (ends("ational")) { r("ate"); break; }
            if (ends("tional")) { r("tion"); break; }
            break;
        case 'c': if (ends("enci")) { r("ence"); break; }
            if (ends("anci")) { r("ance"); break; }
            break;
        case 'e': if (ends("izer")) { r("ize"); break; }
            break;
        case 'l': if (ends("bli")) { r("ble"); break; }
            if (ends("alli")) { r("al"); break; }
            if (ends("entli")) { r("ent"); break; }
            if (ends("eli")) { r("e"); break; }
            if (ends("ousli")) { r("ous"); break; }
            break;
        case 'o': if (ends("ization")) { r("ize"); break; }
            if (ends("ation")) { r("ate"); break; }
            if (ends("ator")) { r("ate"); break; }
            break;
        case 's': if (ends("alism")) { r("al"); break; }
            if (ends("iveness")) { r("ive"); break; }
            if (ends("fulness")) { r("ful"); break; }
            if (ends("ousness")) { r("ous"); break; }
            break;
        case 't': if (ends("aliti")) { r("al"); break; }
            if (ends("iviti")) { r("ive"); break; }
            if (ends("biliti")) { r("ble"); break; }
            break;
        case 'g': if (ends("logi")) { r("log"); break; }
    } }

   /* step4() deals with -ic-, -full, -ness etc. similar strategy to step3. */

    private final void step4() { switch (b[k])
    {
        case 'e': if (ends("icate")) { r("ic"); break; }
            if (ends("ative")) { r(""); break; }
            if (ends("alize")) { r("al"); break; }
            break;
        case 'i': if (ends("iciti")) { r("ic"); break; }
            break;
        case 'l': if (ends("ical")) { r("ic"); break; }
            if (ends("ful")) { r(""); break; }
            break;
        case 's': if (ends("ness")) { r(""); break; }
            break;
    } }

   /* step5() takes off -ant, -ence etc., in context <c>vcvc<v>. */

    private final void step5()
    {   if (k == 0) return; /* for Bug 1 */ switch (b[k-1])
    {  case 'a': if (ends("al")) break; return;
        case 'c': if (ends("ance")) break;
            if (ends("ence")) break; return;
        case 'e': if (ends("er")) break; return;
        case 'i': if (ends("ic")) break; return;
        case 'l': if (ends("able")) break;
            if (ends("ible")) break; return;
        case 'n': if (ends("ant")) break;
            if (ends("ement")) break;
            if (ends("ment")) break;
                    /* element etc. not stripped before the m */
            if (ends("ent")) break; return;
        case 'o': if (ends("ion") && j >= 0 && (b[j] == 's' || b[j] == 't')) break;
                                    /* j >= 0 fixes Bug 2 */
            if (ends("ou")) break; return;
                    /* takes care of -ous */
        case 's': if (ends("ism")) break; return;
        case 't': if (ends("ate")) break;
            if (ends("iti")) break; return;
        case 'u': if (ends("ous")) break; return;
        case 'v': if (ends("ive")) break; return;
        case 'z': if (ends("ize")) break; return;
        default: return;
    }
        if (m() > 1) k = j;
    }

   /* step6() removes a final -e if m() > 1. */

    private final void step6()
    {  j = k;
        if (b[k] == 'e')
        {  int a = m();
            if (a > 1 || a == 1 && !cvc(k-1)) k--;
        }
        if (b[k] == 'l' && doublec(k) && m() > 1) k--;
    }

    /** Stem the word placed into the Stemmer buffer through calls to add().
     * Returns true if the stemming process resulted in a word different
     * from the input.  You can retrieve the result with
     * getResultLength()/getResultBuffer() or toString().
     */
    public void stem()
    {  k = i - 1;
        if (k > 1) { step1(); step2(); step3(); step4(); step5(); step6(); }
        i_end = k+1; i = 0;
    }

    /** Test program for demonstrating the Stemmer.  It reads text from a
     * a list of files, stems each word, and writes the result to standard
     * output. Note that the word stemmed is expected to be in lower case:
     * forcing lower case must be done outside the Stemmer class.
     * Usage: Stemmer file-name file-name ...
     */
    public static void main(String[] args)
    {
        char[] w = new char[501];
        Stemmer s = new Stemmer();
        for (int i = 0; i < args.length; i++)
            try
            {
                FileInputStream in = new FileInputStream(args[i]);

                try
                { while(true)

                {  int ch = in.read();
                    if (Character.isLetter((char) ch))
                    {
                        int j = 0;
                        while(true)
                        {  ch = Character.toLowerCase((char) ch);
                            w[j] = (char) ch;
                            if (j < 500) j++;
                            ch = in.read();
                            if (!Character.isLetter((char) ch))
                            {
                       /* to test add(char ch) */
                                for (int c = 0; c < j; c++) s.add(w[c]);

                       /* or, to test add(char[] w, int j) */
                       /* s.add(w, j); */

                                s.stem();
                                {  String u;

                          /* and now, to test toString() : */
                                    u = s.toString();

                          /* to test getResultBuffer(), getResultLength() : */
                          /* u = new String(s.getResultBuffer(), 0, s.getResultLength()); */

                                    System.out.print(u);
                                }
                                break;
                            }
                        }
                    }
                    if (ch < 0) break;
                    System.out.print((char)ch);
                }
                }
                catch (IOException e)
                {  System.out.println("error reading " + args[i]);
                    break;
                }
            }
            catch (FileNotFoundException e)
            {  System.out.println("file " + args[i] + " not found");
                break;
            }
    }

    /**
     * Stemmer中接口，将传入的word进行词根还原
     * @param word 传入单词
     * @return result 处理后的单词
     */
    public static String run(String word){

        Stemmer s = new Stemmer();

        char[] ch = word.toCharArray();

        for (int c = 0; c < ch.length; c++)
            s.add(ch[c]);

        s.stem();
        {
            String u;
            u = s.toString();
            //System.out.print(u);
            return u;
        }

    }

}
