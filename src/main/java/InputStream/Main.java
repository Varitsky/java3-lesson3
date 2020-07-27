package InputStream;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream wow = new FileInputStream("1234/1.txt");
//        printByte50kb(wow);   // задание номер 1.
        try {
//            sewingKit(); // Эта штука была ошибкой.

            deleteFileNamed("output.txt"); // Задание номер 2.
            merge();                       // Задание номер 2.

//            calculateCharsInFile("output.txt"); //подсчет количества символов нашего 10мб файла для подсчета количества страниц

            readPage(23);                    // Задание номер 3.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**1. Считать файл 50кб в виде байтов*/
    public static void printByte50kb(FileInputStream wow) {
        long t = System.currentTimeMillis();

        try (FileInputStream in = wow) {

            byte[] arr = new byte[51];

            int x;
            while ((x = in.read()) != -1) {
                System.out.print((char) x);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n" + "Время печати 50кб: " + (System.currentTimeMillis() - t) + "мс");
    }

    /**2.Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
    // Может пригодиться следующая конструкция:
    // ArrayList<InputStream> al = new ArrayList<>(); ... Enumeration<InputStream> e = Collections.enumeration(al);*/
//
//    public static void sewingKit() throws IOException {
//        long t = System.currentTimeMillis();
//        PrintWriter pw = new PrintWriter("output.txt");
//
//        ArrayList<InputStream> ali = new ArrayList<>();
//        ali.add(new FileInputStream("1234/100.txt"));
//        ali.add(new FileInputStream("1234/200.txt"));
//        ali.add(new FileInputStream("1234/300.txt"));
//        ali.add(new FileInputStream("1234/400.txt"));
//        ali.add(new FileInputStream("1234/500.txt"));
//
//
//        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));
//        int x;
//
//        while ((x = in.read()) != -1) {
//            System.out.print((char) x);
//
//            try (FileWriter f = new FileWriter("test.txt", true);
//                 BufferedWriter b = new BufferedWriter(f);
//                 PrintWriter p = new PrintWriter(b);) {
//                p.println(x);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        in.close();
//    }
//        System.out.println("\n" + "Время склейки 5x100кб: " + (System.currentTimeMillis() - t) + "мс");
//    }

    public static void merge() throws IOException {
        long t = System.currentTimeMillis();
        ArrayList<File> our100kbFiles = new ArrayList<>();
        our100kbFiles.add(new File("1234/100.txt"));
        our100kbFiles.add(new File("1234/200.txt"));
        our100kbFiles.add(new File("1234/300.txt"));
        our100kbFiles.add(new File("1234/400.txt"));
        our100kbFiles.add(new File("1234/500.txt"));

        // Новый файл
        PrintWriter pw = new PrintWriter("output.txt");

        // Считываем все файлы
        for (File e : our100kbFiles) {
            System.out.println("Читаем файл " + e);

            BufferedReader br = new BufferedReader(new FileReader(e));
            pw.println("Содержимое файла" + e);
            //читаем построчно файл
            String line = br.readLine();
            while (line != null) {
                // записываем в наш PrintWriter
                pw.println(line);
                line = br.readLine();
            }
            pw.flush();
        }

        System.out.println("\n" + "Время склейки 5x100кб: " + (System.currentTimeMillis() - t) + "мс");
        String fileName = "output.txt";
        File f = new File(fileName);
        long fileSize = f.length();
        System.out.format("Размер нового файла: %d байт(а)", fileSize);
    }
    /**Написать консольное приложение, которое умеет постранично читать текстовые файлы
     * (размером > 10 mb). Вводим страницу (за страницу можно принять 1800 символов),
     * программа выводит ее в консоль.  Контролируем время выполнения: программа
     * не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.*/

    public static void readPage(int pageNumber) throws IOException {
        int charNumberFromPageNumber = pageNumber*1800;
        long t = System.currentTimeMillis();
        try (RandomAccessFile raf = new RandomAccessFile("1234/10mb.txt", "r")) {
            for (int i = charNumberFromPageNumber; i < charNumberFromPageNumber+1800; i++) {
                raf.seek(i);
                System.out.print((char) raf.read());
            }
        }
        System.out.println("\n" + "Время печати страницы: " + (System.currentTimeMillis() - t) + "мс");
    }



    //вспомогательные методы Удалить файл и ПодсчитатьСимволы

    public static void deleteFileNamed(String name) {
        String fileName = name;
        try {
            boolean result = Files.deleteIfExists(Paths.get(fileName));
            if (result) {
                System.out.println("Файл удалён");
            } else {
                System.out.println("не было такого файла");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void calculateCharsInFile(String fileName)throws IOException {

        FileInputStream in = new FileInputStream(fileName);
        byte[] array = new byte[in.available()];
        in.read(array);
        String text = new String(array);
        int count = 0;
        for(char ch : text.toCharArray()) {
            if(!Character.isWhitespace(ch)) {
                count++;
            }
        }
        System.out.println();
        System.out.println("\n"+"Количество символов: " + count);
        System.out.println((count/1800) + " страницы, последняя покороче, видимо с ней будут проблемы");
    }
}


