import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class MainFile {

    String[] grups = {"ИП-611","ИП-612","ИП-613","ИП-614","ИП-615"};
    JTable[][] tabls;
    JScrollPane[][] scrollPanes;
    TableClass[] tableClass;

    JFrame frame = new JFrame();
    JComboBox<String> box = new JComboBox<String>(grups);
    JTabbedPane testPanels;

    //главный метод запускает создание таблиц и GUI
    public static void main(String[] args){
        MainFile test = new MainFile();
        test.table();
        test.go();
    } // закрываем метод

    //создаёт основную графику также сдесь кнопкам присваиваются слушатели
    public void go(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,700);
        frame.setLocationRelativeTo(null);

        box.addActionListener(new BoxListener());

        testPanels = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

        JButton buttonBall = new JButton("Посчитать баллы");
        buttonBall.addActionListener(new ButtonListener());

        JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("Файл");

                JMenuItem spravkaMenuItem = new JMenuItem("Спарвка");
                spravkaMenuItem.addActionListener(new SpravkaListenerMenu());

                JMenuItem saveMenuItem = new JMenuItem("Сохранить");
                saveMenuItem.addActionListener(new SaveListenerMenu());

                JMenuItem loadMenuItem = new JMenuItem("Загрузить");
                loadMenuItem.addActionListener(new OpenListenerMenu());

            fileMenu.add(saveMenuItem);
            fileMenu.add(loadMenuItem);
            fileMenu.add(spravkaMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(box);
        menuBar.add(buttonBall);

        testPanels.add("Лабораторные", scrollPanes[box.getSelectedIndex()][0]);
        testPanels.add("Д/З", scrollPanes[box.getSelectedIndex()][1]);
        testPanels.add("Лекции", scrollPanes[box.getSelectedIndex()][2]);
        testPanels.add("Курсовые", scrollPanes[box.getSelectedIndex()][3]);
        testPanels.add("Общие", scrollPanes[box.getSelectedIndex()][4]);

        frame.add(testPanels);

        frame.setJMenuBar(menuBar);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    } // закрываем метод

    //служит для перерисовывания таблиц при переходе с одной группы на другую
    public void go2(){

        // удаление 5 старых таблиц и вставка новых 5(изменённых)

        testPanels.remove(0);
        testPanels.remove(0);
        testPanels.remove(0);
        testPanels.remove(0);
        testPanels.remove(0);

        testPanels.add("Лабораторные", scrollPanes[box.getSelectedIndex()][0]);
        testPanels.add("Д/З", scrollPanes[box.getSelectedIndex()][1]);
        testPanels.add("Лекции", scrollPanes[box.getSelectedIndex()][2]);
        testPanels.add("Курсовые", scrollPanes[box.getSelectedIndex()][3]);
        testPanels.add("Общие", scrollPanes[box.getSelectedIndex()][4]);

        frame.add(testPanels);
    } // конец метода

    //полное создание таблиц со всеми их свойствами
    private void table(){

        // происходит создание таблиц и групп в двумерном массиве, так же помещаем все таблици в JScrollPane

        tabls = new JTable[5][5];
        tableClass = new TableClass[5];
        scrollPanes = new JScrollPane[5][5];

        for(int i = 0;  i < 5; i++) {

            tableClass[i] = new TableClass();

            tabls[i][0] = new JTable(tableClass[i].stLab, tableClass[i].stlab);
            tabls[i][1] = new JTable(tableClass[i].stHomeworce, tableClass[i].sthomeworce);
            tabls[i][2] = new JTable(tableClass[i].stLeckcei, tableClass[i].stleckcei);
            tabls[i][3] = new JTable(tableClass[i].stKurs, tableClass[i].stkurs);
            tabls[i][4] = new JTable(tableClass[i].stObh, tableClass[i].stobh);

            for(int j = 0; j < 5; j++){

                tabls[i][j].getTableHeader().setReorderingAllowed(false); // запрещаю менять местами колонки вручную

                tabls[i][j].getColumnModel().getColumn(0).setMinWidth(200);
                tabls[i][j].getColumnModel().getColumn(0).setMaxWidth(200);

                scrollPanes[i][j] = new JScrollPane(tabls[i][j]);
                scrollPanes[i][j].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPanes[i][j].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            } // конец вложенного цикла

            // запрещаем менять ширину столбцов во всех таблицах

            for(int coloms=0; coloms < tabls[i][0].getColumnModel().getColumnCount() ; coloms++){
                tabls[i][0].getColumnModel().getColumn(coloms).setResizable(false);
            }

            for(int coloms=0; coloms < tabls[i][1].getColumnModel().getColumnCount() ; coloms++){
                tabls[i][1].getColumnModel().getColumn(coloms).setResizable(false);
            }

            for(int coloms=0; coloms < tabls[i][2].getColumnModel().getColumnCount() ; coloms++){
                tabls[i][2].getColumnModel().getColumn(coloms).setResizable(false);
            }

            for(int coloms=0; coloms < tabls[i][3].getColumnModel().getColumnCount() ; coloms++){
                tabls[i][3].getColumnModel().getColumn(coloms).setResizable(false);
            }

            for(int coloms=0; coloms < tabls[i][4].getColumnModel().getColumnCount() ; coloms++){
                tabls[i][4].getColumnModel().getColumn(coloms).setResizable(false);
            }

        } // конец цикла

    } // конец метода

    //программа служащая для сортировки массива типа String[][] (метод пузырька)
    private void sort(int x, int y){

        int i1 = tabls[x][y].getRowCount(); // кол-во строк
        int j1 = tabls[x][y].getColumnCount(); // кол-во столбцов

        int max = 0;

        for(int i = 0; i < i1 - 1; i++) {

            for(int j = 0; j < i1 - 1; j++) {

                int a1 = Integer.parseInt(tableClass[x].stLab[j][j1-1]);
                int a2 = Integer.parseInt(tableClass[x].stLab[j+1][j1-1]);

                if (a1 < a2) {

                    for (int g2 = 0; g2 < j1; g2++) {

                        String st1 = tableClass[x].stLab[j][g2];

                        String st2 = tableClass[x].stLab[j+1][g2];

                        tableClass[x].stLab[j][g2] = st2;

                        tableClass[x].stLab[j+1][g2] = st1;

                        //testPanels.repaint();

                    } // конец цикла

                } // конец условия

            } // конец вложенного цикла

        } // конец главного цикла // на строках прямой выбор

    } // конец метода

    //служит для подсчёта баллов из каждой таблици (только для выбранной группы)
    private void actionButton(int x, int y){

        if (y == 0) { // если открыта таблица лабораторных

            int bal = 0;

            int i1 = tabls[x][y].getRowCount();
            int j1 = tabls[x][y].getColumnCount();

            String st = null;

            for (int i = 0; i < i1; i++) {

                bal = 0;

                for (int j = 1; j < j1-1; j++) {

                    st = ""+tabls[x][y].getValueAt(i, j);

                    //System.out.println(st);
                    if (st.equals("3")) {
                        bal += 3;
                    }

                    if (st.equals("4")) {
                        bal += 5;
                    }

                    if (st.equals("5")) {
                        bal += 7;
                    }

                    if (st.equals("5+")) {
                        bal += 10;
                    }

                    if (st.equals("н")) {
                        bal -= 5;
                    }

                } // закрываем вложенный цикл

                tabls[x][y].setValueAt("" + bal, i, 18);

            } // закрываем циклв

        } // закрываем условие

        if(y == 1){ // если открыта таблица Д/З

            int bal = 0;

            int i1 = tabls[x][y].getRowCount();
            int j1 = tabls[x][y].getColumnCount();
            String st = null;

            for (int i = 0; i < i1; i++) {

                bal = 0;

                for (int j = 1; j < j1-1; j++) {

                    st = ""+tabls[x][y].getValueAt(i, j);

                    //System.out.println(st);
                    if (st.equals("3")) {
                        bal += 1;
                    }

                    if (st.equals("4")) {
                        bal += 3;
                    }

                    if (st.equals("5")) {
                        bal += 5;
                    }

                    if (st.equals("5+")) {
                        bal += 7;
                    }

                    if (st.equals("н")) {
                        bal -= 5;
                    }

                } // закрываем вложенный цикл

                tabls[x][y].setValueAt("" + bal, i, 18);

            } // закрываем циклв

        } // закрываем условие

        if(y == 2){ // если открыта таблица Лекций

            int bal = 0;

            int i1 = tabls[x][y].getRowCount();
            int j1 = tabls[x][y].getColumnCount();
            String st = null;

            for (int i = 0; i < i1; i++) {

                bal = 0;

                for (int j = 1; j < j1-1; j++) {

                    st = ""+tabls[x][y].getValueAt(i, j);

                    //System.out.println(st);

                    if (st.equals("б")) {
                        bal += 5;
                    }

                    if (st.equals("н")) {
                        bal -= 5;
                    }

                } // закрываем вложенный цикл

                tabls[x][y].setValueAt("" + bal, i, 18);

            } // закрываем циклв

        } // закрываем условие

        if(y == 3){ // если открыта таблица Курсовых

            int bal = 0;

            int i1 = tabls[x][y].getRowCount();

            String st = null;

            for (int i = 0; i < i1; i++) {

                bal = 0;

                st = ""+tabls[x][y].getValueAt(i, 2);

                //System.out.println(st);
                if (st.equals("3")) {
                    bal += 1;
                }

                if (st.equals("4")) {
                    bal += 3;
                }

                if (st.equals("5")) {
                    bal += 5;
                }

                if (st.equals("5+")) {
                    bal += 7;
                }

                if (st.equals("п")) {
                    bal -= 5;
                }

                tabls[x][y].setValueAt("" + bal, i, 3);

            } // закрываем циклв

        } // закрываем условие

        if (y == 4){ // если открыта таблица Общих

            int i1 = tabls[x][y].getRowCount();

            int bal = 0;

            for(int i = 0; i < i1; i++){

                bal = 0;

                String st1 = "" + tabls[x][0].getValueAt(i, 18);
                String st2 = "" + tabls[x][1].getValueAt(i, 18);
                String st3 = "" + tabls[x][2].getValueAt(i, 18);
                String st4 = "" + tabls[x][3].getValueAt(i, 3);

                bal+=Integer.parseInt(st1);
                bal+=Integer.parseInt(st2);
                bal+=Integer.parseInt(st3);
                bal+=Integer.parseInt(st4);

                tabls[x][y].setValueAt("" + st1, i, 1);
                tabls[x][y].setValueAt("" + st2, i, 2);
                tabls[x][y].setValueAt("" + st3, i, 3);
                tabls[x][y].setValueAt("" + st4, i, 4);
                tabls[x][y].setValueAt("" + bal, i, 5);

            } // закрываем цикл

        } // закрываем условие

    } // закрываем метод

    //сохранение используя Сериализацию
    private void saveMetod(File file){

        try{
            FileOutputStream st = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(st);
            os.writeObject(tableClass[0]);
            os.writeObject(tableClass[1]);
            os.writeObject(tableClass[2]);
            os.writeObject(tableClass[3]);
            os.writeObject(tableClass[4]);
            os.close();
        } catch (Exception e){
            System.out.println("Ошибка =(");
            e.printStackTrace();
        }

    } // закрываем метод

    //загрузка таблиц с последующей установкой их свойст
    private void openMetod(File file){

        try {

            FileInputStream os = new FileInputStream(file);
            ObjectInputStream st = new ObjectInputStream(os);

            tabls = new JTable[5][5];
            tableClass = new TableClass[5];
            scrollPanes = new JScrollPane[5][5];

            for(int i = 0;  i < 5; i++) {

                tableClass[i] = (TableClass)st.readObject();

                tabls[i][0] = new JTable(tableClass[i].stLab, tableClass[i].stlab);
                tabls[i][1] = new JTable(tableClass[i].stHomeworce, tableClass[i].sthomeworce);
                tabls[i][2] = new JTable(tableClass[i].stLeckcei, tableClass[i].stleckcei);
                tabls[i][3] = new JTable(tableClass[i].stKurs, tableClass[i].stkurs);
                tabls[i][4] = new JTable(tableClass[i].stObh, tableClass[i].stobh);

                for(int j = 0; j < 5; j++){

                    tabls[i][j].getTableHeader().setReorderingAllowed(false); // запрещаю менять местами колонки вручную

                    tabls[i][j].getColumnModel().getColumn(0).setMinWidth(200);
                    tabls[i][j].getColumnModel().getColumn(0).setMaxWidth(200);

                    scrollPanes[i][j] = new JScrollPane(tabls[i][j]);
                    scrollPanes[i][j].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollPanes[i][j].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                } // конец вложенного цикла

                // запрещаем менять ширину столбцов во всех таблицах

                for(int coloms=0; coloms < tabls[i][0].getColumnModel().getColumnCount() ; coloms++){
                    tabls[i][0].getColumnModel().getColumn(coloms).setResizable(false);
                }

                for(int coloms=0; coloms < tabls[i][1].getColumnModel().getColumnCount() ; coloms++){
                    tabls[i][1].getColumnModel().getColumn(coloms).setResizable(false);
                }

                for(int coloms=0; coloms < tabls[i][2].getColumnModel().getColumnCount() ; coloms++){
                    tabls[i][2].getColumnModel().getColumn(coloms).setResizable(false);
                }

                for(int coloms=0; coloms < tabls[i][3].getColumnModel().getColumnCount() ; coloms++){
                    tabls[i][3].getColumnModel().getColumn(coloms).setResizable(false);
                }

                for(int coloms=0; coloms < tabls[i][4].getColumnModel().getColumnCount() ; coloms++){
                    tabls[i][4].getColumnModel().getColumn(coloms).setResizable(false);
                }

            } // конец цикла

            go2();

        } catch (Exception e){
            System.out.println("Произошла ошибка =(");
            e.printStackTrace();
        }
    } // закрываем метод

    //служит для сортировки напрямую работает со строками
    private void test(){

        String[][] st = tableClass[0].getStObh();

        System.out.println(st[0][5]);

        int i1 = 50;//tabls[0][4].getRowCount(); // кол-во строк
       // int j1 = tabls[0][0].getColumnCount(); // кол-во столбцов

        for(int i = 0; i < i1 - 2; i++) {

            for(int j = 0; j < i1 - 1 - i; j++) {

                int a1 = Integer.parseInt(st[j][5]);
                int a2 = Integer.parseInt(st[j+1][5]);

                if(a1 < a2) {

                    for (int g = 0; g < 6; g++) {

                        String s = st[j][g];
                        st[j][g] = st[j + 1][g];
                        st[j + 1][g] = s;

                    } // конец цикла перестоновки строк

                } //конец условия

            } // конец вложенного цикла

        } // конец внешнего цикла

        testPanels.repaint();

    } // конец метода

    //определяет длинну слова и передаёт оставшиеся пробелы чтобы при сохранение в текстовый файл получалась ровная таблица
    private String probel(String x) { // нужна для дополнительных пробелов в имени и фамилии

        int y = 0;

        if(x != null) y = x.length();

        String st = "";
        for(int i = 1; i <= 28 - y; i++) {
            st+=" ";
        }

        return st;
    } // конец метода

    //передаёт кол-во пробелов по заданному параметру
    private String probel2(int x) { // по переданному параметру возращает строку с пробелами
        String st = "";
        for(int i = 1; i <= x; i++) {
            st+=" ";
        }
        return st;
    } // конец метода

    //принимает строковый массив перезаписывает его в новый массив и затем сортирует его после происходит возвращение нового массива
    private String[][] str_sortStr(String[][] st) { // запись нового массива String[][] из общей таблици

        int count = 0;

        for(int i = 0; i < 50; i++) if (st[i][0] != null) count++;

        String[][] strings = new String[count][2];

        for(int i = 0; i < count; i++) { // присваиваем новому массиву значение из таблиц
            strings[i][0] = st[i][0];
            strings[i][1] = st[i][5];
        }

        // сортируем новый строковый массив
        for(int i = 0; i < count; i++) {

            for(int j = 0; j < count - 1; j++) {

                int a1 = Integer.parseInt(strings[j][1]);
                int a2 = Integer.parseInt(strings[j+1][1]);

                if(a1 < a2) {

                    String s = strings[j][0];
                    strings[j][0] = strings[j+1][0];
                    strings[j+1][0] = s;

                    s = strings[j][1];
                    strings[j][1] = strings[j+1][1];
                    strings[j+1][1] = s;

                } //конец условия

            } // конец вложенного цикла

        } // конец внешнего цикла

        return  strings;
    } // конец метода

    //происходит сохранение 5 текстовых файлов по группам (отсортированными таблицами)
    private void saveTextFile(File file, int x) {

        try {

            String grup = null;

            if(x == 0) grup = "611";
            if(x == 1) grup = "612";
            if(x == 2) grup = "613";
            if(x == 3) grup = "614";
            if(x == 4) grup = "615";

            String[][] masString = str_sortStr(tableClass[x].stObh); // получим группу и их общий отсотированный балл

            int y = masString.length; // считаем кол-во строк

            PrintWriter writer = new PrintWriter(file + " группа - " + grup + ".txt"); // создаём файл

            writer.println("Группа - " + grup);
            writer.println();
            writer.println("Студенты:                   Общий Балл:");
            writer.println();

            for(int i = 0; i < y; i++) {
                // сохраняем строку
                String st = masString[i][0] + probel(masString[i][0]) + masString[i][1];
                writer.println(st);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // конец метода

    //сохраняет 6 текстовый файл с общими отсортированными таблицами
    private void saveTextFileGroop(File file) {

        try {

            String[][] masString = strSortGroop(); // получим группы и их общий отсотированный балл

            int y = masString.length; // считаем кол-во строк

            PrintWriter writer = new PrintWriter(file + " группы 611 - 615 " + ".txt"); // создаём файл

            writer.println("Группы 611 - 615");
            writer.println();
            writer.println("Студенты:                   Группа:          Общий Балл:");
            writer.println();

            for(int i = 0; i < y; i++) {
                // сохраняем строку
                String st = masString[i][0] + probel(masString[i][0]) + masString[i][1] + probel2(14) + masString[i][2] ;
                writer.println(st);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // конец метода

    //записывает все общие таблици в один строковый массив и сортирует его
    private  String[][] strSortGroop() {

        int count = 0;

        int tab1 = 0, tab2 = 0, tab3 = 0, tab4 = 0, tab5 = 0; // нужны для точной записы из страх строковых массивов в ноый

        //считаем кол-во студентов в каждой группе
        for(int i = 0; i < 50; i++) if (tableClass[0].stObh[i][0] != null){ count++; tab1++;}
        for(int i = 0; i < 50; i++) if (tableClass[1].stObh[i][0] != null){ count++; tab2++;}
        for(int i = 0; i < 50; i++) if (tableClass[2].stObh[i][0] != null){ count++; tab3++;}
        for(int i = 0; i < 50; i++) if (tableClass[3].stObh[i][0] != null){ count++; tab4++;}
        for(int i = 0; i < 50; i++) if (tableClass[4].stObh[i][0] != null){ count++; tab5++;}


        String[][] str = new String[count][3];

        int x = 0; // нужна для соблюдения порядка в массиве

        // присваиваем новому массиву значение из таблиц

        // группа 611
        for(int i = 0; i < tab1; i++) { // присваиваем новому массиву значение из таблиц
            str[i][0] = tableClass[0].stObh[i][0];
            str[i][1] = "611";
            str[i][2] = tableClass[0].stObh[i][5];
        }

        x+=tab1;

        // группа 612
        for(int i = 0; i < tab2; i++) { // присваиваем новому массиву значение из таблиц
            str[i+x][0] = tableClass[1].stObh[i][0];
            str[i+x][1] = "612";
            str[i+x][2] = tableClass[1].stObh[i][5];
        }

        x += tab2;

        // группа 613
        for(int i = 0; i < tab3; i++) { // присваиваем новому массиву значение из таблиц
            str[i+x][0] = tableClass[2].stObh[i][0];
            str[i+x][1] = "613";
            str[i+x][2] = tableClass[2].stObh[i][5];
        }

        x += tab3;

        // группа 614
        for(int i = 0; i < tab4; i++) { // присваиваем новому массиву значение из таблиц
            str[i+x][0] = tableClass[3].stObh[i][0];
            str[i+x][1] = "614";
            str[i+x][2] = tableClass[3].stObh[i][5];
        }

        x += tab4;

        // группа 615
        for(int i = 0; i < tab5; i++) { // присваиваем новому массиву значение из таблиц
            str[i+x][0] = tableClass[4].stObh[i][0];
            str[i+x][1] = "615";
            str[i+x][2] = tableClass[4].stObh[i][5];
        }

        // сортируем новый строковый массив
        for(int i = 0; i < count; i++) {

            for (int j = 0; j < count - 1; j++) {

                int a1 = Integer.parseInt(str[j][2]);
                int a2 = Integer.parseInt(str[j + 1][2]);

                if (a1 < a2) {

                    String s = str[j][0];
                    str[j][0] = str[j + 1][0];
                    str[j + 1][0] = s;

                    s = str[j][1];
                    str[j][1] = str[j + 1][1];
                    str[j + 1][1] = s;

                    s = str[j][2];
                    str[j][2] = str[j + 1][2];
                    str[j + 1][2] = s;

                } //конец условия

            } // конец вложенного цикла

        } // конец внешнего цикла

        return  str;
    } // конец метода

    //слушатель для нажатия кнопки СПРАВКА выводит текстовое окно с документацией
    public class SpravkaListenerMenu implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            test();
        }
    } // закрыаем внутренний класс

    //слушатель для нажатия кнопки ЗАГРУЗИТЬ передаёт загружаемый файл методу openMetod()
    public class OpenListenerMenu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser openFile = new JFileChooser();
            openFile.showOpenDialog(frame);
            openMetod(openFile.getSelectedFile());

        }
    } // закрываем внутренний класс

    //слушатель для нажатия кнопки СОХРАНИТЬ передаёт сохраняемый файл методу saveMetod()
    public class SaveListenerMenu implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveMetod(fileSave.getSelectedFile());

            saveTextFile(fileSave.getSelectedFile(),0);
            saveTextFile(fileSave.getSelectedFile(),1);
            saveTextFile(fileSave.getSelectedFile(),2);
            saveTextFile(fileSave.getSelectedFile(),3);
            saveTextFile(fileSave.getSelectedFile(),4);

            saveTextFileGroop(fileSave.getSelectedFile());
        }
    } // закрываем внутренний класс

    //слушатель для нажатия кнопки ПОСЧИТАТЬ БАЛЛЫ запускает метод actionButton() и также проиходит проверка на активность ячейки
    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(tabls[box.getSelectedIndex()][testPanels.getSelectedIndex()].getCellEditor() != null) { // проверка на выделенную ячейку
                tabls[box.getSelectedIndex()][testPanels.getSelectedIndex()].getCellEditor().stopCellEditing(); // делаем активную ячейку не активной
            }

            actionButton(box.getSelectedIndex(),0);
            actionButton(box.getSelectedIndex(),1);
            actionButton(box.getSelectedIndex(),2);
            actionButton(box.getSelectedIndex(),3);
            actionButton(box.getSelectedIndex(),4);

           // test();

            testPanels.repaint();
        } // закрываем метод

    } // закрываем внутренний класс

    //слушатель для использования JBox служит запускает метод go2() для перехода с группы на группу также перерисовка frame
    public class BoxListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            go2();
            frame.repaint();
        }
    } // закрываем внутренний класс

} // конец класса
