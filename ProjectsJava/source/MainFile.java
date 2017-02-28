import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class MainFile {

    String[] grups = {"认-611","认-612","认-613","认-614","认-615"};
    JTable[][] tabls;
    JScrollPane[][] scrollPanes;
    TableClass[] tableClass;

    JFrame frame = new JFrame();
    JComboBox<String> box = new JComboBox<String>(grups);
    JTabbedPane testPanels;

    //汶噔睇� 戾蝾� 玎矬耜噱� 耦玟囗桢 蜞犭桷 � GUI
    public static void main(String[] args){
        MainFile test = new MainFile();
        test.table();
        test.go();
    } // 玎牮噱� 戾蝾�

    //耦玟喔� 铖眍忭簋 沭圄桕� 蜞赕� 皲羼� 觏铒赅� 镳桉忄桠帼蝰� 耠篪囹咫�
    public void go(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,700);
        frame.setLocationRelativeTo(null);

        box.addActionListener(new BoxListener());

        testPanels = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

        JButton buttonBall = new JButton("项聍栩囹� 徉腚�");
        buttonBall.addActionListener(new ButtonListener());

        JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("脏殡");

                JMenuItem spravkaMenuItem = new JMenuItem("扬囵怅�");
                spravkaMenuItem.addActionListener(new SpravkaListenerMenu());

                JMenuItem saveMenuItem = new JMenuItem("杨躔囗栩�");
                saveMenuItem.addActionListener(new SaveListenerMenu());

                JMenuItem loadMenuItem = new JMenuItem("青沭箸栩�");
                loadMenuItem.addActionListener(new OpenListenerMenu());

            fileMenu.add(saveMenuItem);
            fileMenu.add(loadMenuItem);
            fileMenu.add(spravkaMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(box);
        menuBar.add(buttonBall);

        testPanels.add("肃犷疣蝾痦", scrollPanes[box.getSelectedIndex()][0]);
        testPanels.add("�/�", scrollPanes[box.getSelectedIndex()][1]);
        testPanels.add("隋牿梃", scrollPanes[box.getSelectedIndex()][2]);
        testPanels.add("鼠瘃钼", scrollPanes[box.getSelectedIndex()][3]);
        testPanels.add("吾�", scrollPanes[box.getSelectedIndex()][4]);

        frame.add(testPanels);

        frame.setJMenuBar(menuBar);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    } // 玎牮噱� 戾蝾�

    //耠箧栩 潆� 镥疱痂耦恹忄龛� 蜞犭桷 镳� 镥疱躅溴 � 钿眍� 沭箫稃 磬 漯筱簋
    public void go2(){

        // 箐嚯屙桢 5 耱囵 蜞犭桷 � 怦蜞怅� 眍恹� 5(桤戾砀眄)

        testPanels.remove(0);
        testPanels.remove(0);
        testPanels.remove(0);
        testPanels.remove(0);
        testPanels.remove(0);

        testPanels.add("肃犷疣蝾痦", scrollPanes[box.getSelectedIndex()][0]);
        testPanels.add("�/�", scrollPanes[box.getSelectedIndex()][1]);
        testPanels.add("隋牿梃", scrollPanes[box.getSelectedIndex()][2]);
        testPanels.add("鼠瘃钼", scrollPanes[box.getSelectedIndex()][3]);
        testPanels.add("吾�", scrollPanes[box.getSelectedIndex()][4]);

        frame.add(testPanels);
    } // 觐礤� 戾蝾溧

    //镱腠铄 耦玟囗桢 蜞犭桷 耦 怦屐� 桴 疋铋耱忄扈
    private void table(){

        // 镳铊聃钿栩 耦玟囗桢 蜞犭桷 � 沭箫� � 溻箪屦眍� 爨耨桠�, 蜞� 驽 镱戾屐 怦� 蜞犭桷� � JScrollPane

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

                tabls[i][j].getTableHeader().setReorderingAllowed(false); // 玎镳妁帼 戾��螯 戾耱囔� 觐腩黻� 怵篦眢�

                tabls[i][j].getColumnModel().getColumn(0).setMinWidth(200);
                tabls[i][j].getColumnModel().getColumn(0).setMaxWidth(200);

                scrollPanes[i][j] = new JScrollPane(tabls[i][j]);
                scrollPanes[i][j].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPanes[i][j].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            } // 觐礤� 怆铈屙眍泐 鲨觌�

            // 玎镳妁噱� 戾��螯 痂眢 耱铍狯钼 忸 怦艴 蜞犭桷圊

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

        } // 觐礤� 鲨觌�

    } // 觐礤� 戾蝾溧

    //镳钽疣祆� 耠箧帙�� 潆� 耦痱桊钼觇 爨耨桠� 蜩镟 String[][] (戾蝾� 矬琨瘘赅)
    private void sort(int x, int y){

        int i1 = tabls[x][y].getRowCount(); // 觐�-忸 耱痤�
        int j1 = tabls[x][y].getColumnCount(); // 觐�-忸 耱铍狯钼

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

                    } // 觐礤� 鲨觌�

                } // 觐礤� 篑腩忤�

            } // 觐礤� 怆铈屙眍泐 鲨觌�

        } // 觐礤� 汶噔眍泐 鲨觌� // 磬 耱痤赅� 镳�祛� 恹犷�

    } // 觐礤� 戾蝾溧

    //耠箧栩 潆� 镱漶鞲蜞 徉腚钼 桤 赅驿铋 蜞犭桷� (蝾朦觐 潆� 恹狃囗眍� 沭箫稃)
    private void actionButton(int x, int y){

        if (y == 0) { // 羼腓 铗牮� 蜞犭桷� 豚犷疣蝾痦

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

                    if (st.equals("�")) {
                        bal -= 5;
                    }

                } // 玎牮噱� 怆铈屙睇� 鲨觌

                tabls[x][y].setValueAt("" + bal, i, 18);

            } // 玎牮噱� 鲨觌�

        } // 玎牮噱� 篑腩忤�

        if(y == 1){ // 羼腓 铗牮� 蜞犭桷� �/�

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

                    if (st.equals("�")) {
                        bal -= 5;
                    }

                } // 玎牮噱� 怆铈屙睇� 鲨觌

                tabls[x][y].setValueAt("" + bal, i, 18);

            } // 玎牮噱� 鲨觌�

        } // 玎牮噱� 篑腩忤�

        if(y == 2){ // 羼腓 铗牮� 蜞犭桷� 隋牿栝

            int bal = 0;

            int i1 = tabls[x][y].getRowCount();
            int j1 = tabls[x][y].getColumnCount();
            String st = null;

            for (int i = 0; i < i1; i++) {

                bal = 0;

                for (int j = 1; j < j1-1; j++) {

                    st = ""+tabls[x][y].getValueAt(i, j);

                    //System.out.println(st);

                    if (st.equals("�")) {
                        bal += 5;
                    }

                    if (st.equals("�")) {
                        bal -= 5;
                    }

                } // 玎牮噱� 怆铈屙睇� 鲨觌

                tabls[x][y].setValueAt("" + bal, i, 18);

            } // 玎牮噱� 鲨觌�

        } // 玎牮噱� 篑腩忤�

        if(y == 3){ // 羼腓 铗牮� 蜞犭桷� 鼠瘃钼

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

                if (st.equals("�")) {
                    bal -= 5;
                }

                tabls[x][y].setValueAt("" + bal, i, 3);

            } // 玎牮噱� 鲨觌�

        } // 玎牮噱� 篑腩忤�

        if (y == 4){ // 羼腓 铗牮� 蜞犭桷� 吾�

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

            } // 玎牮噱� 鲨觌

        } // 玎牮噱� 篑腩忤�

    } // 玎牮噱� 戾蝾�

    //耦躔囗屙桢 桉镱朦珞� 彦痂嚯桤圉棹
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
            System.out.println("硒栳赅 =(");
            e.printStackTrace();
        }

    } // 玎牮噱� 戾蝾�

    //玎沭箸赅 蜞犭桷 � 镱耠邃簋� 篑蜞眍怅铋 桴 疋铋耱
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

                    tabls[i][j].getTableHeader().setReorderingAllowed(false); // 玎镳妁帼 戾��螯 戾耱囔� 觐腩黻� 怵篦眢�

                    tabls[i][j].getColumnModel().getColumn(0).setMinWidth(200);
                    tabls[i][j].getColumnModel().getColumn(0).setMaxWidth(200);

                    scrollPanes[i][j] = new JScrollPane(tabls[i][j]);
                    scrollPanes[i][j].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollPanes[i][j].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                } // 觐礤� 怆铈屙眍泐 鲨觌�

                // 玎镳妁噱� 戾��螯 痂眢 耱铍狯钼 忸 怦艴 蜞犭桷圊

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

            } // 觐礤� 鲨觌�

            go2();

        } catch (Exception e){
            System.out.println("橡铊珙� 铠栳赅 =(");
            e.printStackTrace();
        }
    } // 玎牮噱� 戾蝾�

    //耠箧栩 潆� 耦痱桊钼觇 磬镳�祗� 疣犷蜞弪 耦 耱痤赅扈
    private void test(){

        String[][] st = tableClass[0].getStObh();

        System.out.println(st[0][5]);

        int i1 = 50;//tabls[0][4].getRowCount(); // 觐�-忸 耱痤�
       // int j1 = tabls[0][0].getColumnCount(); // 觐�-忸 耱铍狯钼

        for(int i = 0; i < i1 - 2; i++) {

            for(int j = 0; j < i1 - 1 - i; j++) {

                int a1 = Integer.parseInt(st[j][5]);
                int a2 = Integer.parseInt(st[j+1][5]);

                if(a1 < a2) {

                    for (int g = 0; g < 6; g++) {

                        String s = st[j][g];
                        st[j][g] = st[j + 1][g];
                        st[j + 1][g] = s;

                    } // 觐礤� 鲨觌� 镥疱耱铐钼觇 耱痤�

                } //觐礤� 篑腩忤�

            } // 觐礤� 怆铈屙眍泐 鲨觌�

        } // 觐礤� 忭屮礤泐 鲨觌�

        testPanels.repaint();

    } // 觐礤� 戾蝾溧

    //铒疱溴��弪 潆桧眢 耠钼� � 镥疱溧蛤 铖蜞怿桢�� 镳钺咫� 黩钺� 镳� 耦躔囗屙桢 � 蝈犟蝾恹� 羿殡 镱塍鬣豚顸 痤忭�� 蜞犭桷�
    private String probel(String x) { // 眢骓� 潆� 漕镱腠栩咫 镳钺咫钼 � 桁屙� � 羿扈腓�

        int y = 0;

        if(x != null) y = x.length();

        String st = "";
        for(int i = 1; i <= 28 - y; i++) {
            st+=" ";
        }

        return st;
    } // 觐礤� 戾蝾溧

    //镥疱溧蛤 觐�-忸 镳钺咫钼 镱 玎溧眄铎� 镟疣戾蝠�
    private String probel2(int x) { // 镱 镥疱溧眄铎� 镟疣戾蝠� 忸琊帙噱� 耱痤牦 � 镳钺咫囔�
        String st = "";
        for(int i = 1; i <= x; i++) {
            st+=" ";
        }
        return st;
    } // 觐礤� 戾蝾溧

    //镳桧桁噱� 耱痤觐恹� 爨耨桠 镥疱玎镨覃忄弪 邈� � 眍恹� 爨耨桠 � 玎蝈� 耦痱桊箦� 邈� 镱耠� 镳铊聃钿栩 忸玮疣龛� 眍忸泐 爨耨桠�
    private String[][] str_sortStr(String[][] st) { // 玎镨顸 眍忸泐 爨耨桠� String[][] 桤 钺� 蜞犭桷�

        int count = 0;

        for(int i = 0; i < 50; i++) if (st[i][0] != null) count++;

        String[][] strings = new String[count][2];

        for(int i = 0; i < count; i++) { // 镳桉忄桠噱� 眍忸祗 爨耨桠� 珥圜屙桢 桤 蜞犭桷
            strings[i][0] = st[i][0];
            strings[i][1] = st[i][5];
        }

        // 耦痱桊箦� 眍恹� 耱痤觐恹� 爨耨桠
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

                } //觐礤� 篑腩忤�

            } // 觐礤� 怆铈屙眍泐 鲨觌�

        } // 觐礤� 忭屮礤泐 鲨觌�

        return  strings;
    } // 觐礤� 戾蝾溧

    //镳铊聃钿栩 耦躔囗屙桢 5 蝈犟蝾恹� 羿殡钼 镱 沭箫镟� (铗耦痱桊钼囗睇扈 蜞犭桷囔�)
    private void saveTextFile(File file, int x) {

        try {

            String grup = null;

            if(x == 0) grup = "611";
            if(x == 1) grup = "612";
            if(x == 2) grup = "613";
            if(x == 3) grup = "614";
            if(x == 4) grup = "615";

            String[][] masString = str_sortStr(tableClass[x].stObh); // 镱塍麒� 沭箫矬 � 桴 钺� 铗耦蜩痤忄眄 徉腚

            int y = masString.length; // 聍栩噱� 觐�-忸 耱痤�

            PrintWriter writer = new PrintWriter(file + " 沭箫镟 - " + grup + ".txt"); // 耦玟喔� 羿殡

            writer.println("灭箫镟 - " + grup);
            writer.println();
            writer.println("羊箐屙螓:                   吾� 拎腚:");
            writer.println();

            for(int i = 0; i < y; i++) {
                // 耦躔囗�屐 耱痤牦
                String st = masString[i][0] + probel(masString[i][0]) + masString[i][1];
                writer.println(st);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // 觐礤� 戾蝾溧

    //耦躔囗�弪 6 蝈犟蝾恹� 羿殡 � 钺扈 铗耦痱桊钼囗睇扈 蜞犭桷囔�
    private void saveTextFileGroop(File file) {

        try {

            String[][] masString = strSortGroop(); // 镱塍麒� 沭箫稃 � 桴 钺� 铗耦蜩痤忄眄 徉腚

            int y = masString.length; // 聍栩噱� 觐�-忸 耱痤�

            PrintWriter writer = new PrintWriter(file + " 沭箫稃 611 - 615 " + ".txt"); // 耦玟喔� 羿殡

            writer.println("灭箫稃 611 - 615");
            writer.println();
            writer.println("羊箐屙螓:                   灭箫镟:          吾� 拎腚:");
            writer.println();

            for(int i = 0; i < y; i++) {
                // 耦躔囗�屐 耱痤牦
                String st = masString[i][0] + probel(masString[i][0]) + masString[i][1] + probel2(14) + masString[i][2] ;
                writer.println(st);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // 觐礤� 戾蝾溧

    //玎镨覃忄弪 怦� 钺� 蜞犭桷� � 钿桧 耱痤觐恹� 爨耨桠 � 耦痱桊箦� 邈�
    private  String[][] strSortGroop() {

        int count = 0;

        int tab1 = 0, tab2 = 0, tab3 = 0, tab4 = 0, tab5 = 0; // 眢骓� 潆� 蝾黜铋 玎镨覃 桤 耱疣� 耱痤觐恹� 爨耨桠钼 � 眍

        //聍栩噱� 觐�-忸 耱箐屙蝾� � 赅驿铋 沭箫镥
        for(int i = 0; i < 50; i++) if (tableClass[0].stObh[i][0] != null){ count++; tab1++;}
        for(int i = 0; i < 50; i++) if (tableClass[1].stObh[i][0] != null){ count++; tab2++;}
        for(int i = 0; i < 50; i++) if (tableClass[2].stObh[i][0] != null){ count++; tab3++;}
        for(int i = 0; i < 50; i++) if (tableClass[3].stObh[i][0] != null){ count++; tab4++;}
        for(int i = 0; i < 50; i++) if (tableClass[4].stObh[i][0] != null){ count++; tab5++;}


        String[][] str = new String[count][3];

        int x = 0; // 眢骓� 潆� 耦犭屙�� 镱��潢� � 爨耨桠�

        // 镳桉忄桠噱� 眍忸祗 爨耨桠� 珥圜屙桢 桤 蜞犭桷

        // 沭箫镟 611
        for(int i = 0; i < tab1; i++) { // 镳桉忄桠噱� 眍忸祗 爨耨桠� 珥圜屙桢 桤 蜞犭桷
            str[i][0] = tableClass[0].stObh[i][0];
            str[i][1] = "611";
            str[i][2] = tableClass[0].stObh[i][5];
        }

        x+=tab1;

        // 沭箫镟 612
        for(int i = 0; i < tab2; i++) { // 镳桉忄桠噱� 眍忸祗 爨耨桠� 珥圜屙桢 桤 蜞犭桷
            str[i+x][0] = tableClass[1].stObh[i][0];
            str[i+x][1] = "612";
            str[i+x][2] = tableClass[1].stObh[i][5];
        }

        x += tab2;

        // 沭箫镟 613
        for(int i = 0; i < tab3; i++) { // 镳桉忄桠噱� 眍忸祗 爨耨桠� 珥圜屙桢 桤 蜞犭桷
            str[i+x][0] = tableClass[2].stObh[i][0];
            str[i+x][1] = "613";
            str[i+x][2] = tableClass[2].stObh[i][5];
        }

        x += tab3;

        // 沭箫镟 614
        for(int i = 0; i < tab4; i++) { // 镳桉忄桠噱� 眍忸祗 爨耨桠� 珥圜屙桢 桤 蜞犭桷
            str[i+x][0] = tableClass[3].stObh[i][0];
            str[i+x][1] = "614";
            str[i+x][2] = tableClass[3].stObh[i][5];
        }

        x += tab4;

        // 沭箫镟 615
        for(int i = 0; i < tab5; i++) { // 镳桉忄桠噱� 眍忸祗 爨耨桠� 珥圜屙桢 桤 蜞犭桷
            str[i+x][0] = tableClass[4].stObh[i][0];
            str[i+x][1] = "615";
            str[i+x][2] = tableClass[4].stObh[i][5];
        }

        // 耦痱桊箦� 眍恹� 耱痤觐恹� 爨耨桠
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

                } //觐礤� 篑腩忤�

            } // 觐礤� 怆铈屙眍泐 鲨觌�

        } // 觐礤� 忭屮礤泐 鲨觌�

        return  str;
    } // 觐礤� 戾蝾溧

    //耠篪囹咫� 潆� 磬驵蜩� 觏铒觇 严欣率� 恹忸滂� 蝈犟蝾忸� 铌眍 � 漕牦戾眚圉桢�
    public class SpravkaListenerMenu implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            test();
        }
    } // 玎牮屐 忭篁疱眄栝 觌囫�

    //耠篪囹咫� 潆� 磬驵蜩� 觏铒觇 抢眯忧纫� 镥疱溧蛤 玎沭箧噱禧� 羿殡 戾蝾潴 openMetod()
    public class OpenListenerMenu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser openFile = new JFileChooser();
            openFile.showOpenDialog(frame);
            openMetod(openFile.getSelectedFile());

        }
    } // 玎牮噱� 忭篁疱眄栝 觌囫�

    //耠篪囹咫� 潆� 磬驵蜩� 觏铒觇 盐招劳纫� 镥疱溧蛤 耦躔囗�屐 羿殡 戾蝾潴 saveMetod()
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
    } // 玎牮噱� 忭篁疱眄栝 觌囫�

    //耠篪囹咫� 潆� 磬驵蜩� 觏铒觇 衔炎纫酪� 晾怂� 玎矬耜噱� 戾蝾� actionButton() � 蜞赕� 镳铊躅滂� 镳钼屦赅 磬 嚓蜩忭铖螯 �麇殛�
    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(tabls[box.getSelectedIndex()][testPanels.getSelectedIndex()].getCellEditor() != null) { // 镳钼屦赅 磬 恹溴脲眄簋 �麇殛�
                tabls[box.getSelectedIndex()][testPanels.getSelectedIndex()].getCellEditor().stopCellEditing(); // 溴豚屐 嚓蜩忭簋 �麇殛� 礤 嚓蜩忭铋
            }

            actionButton(box.getSelectedIndex(),0);
            actionButton(box.getSelectedIndex(),1);
            actionButton(box.getSelectedIndex(),2);
            actionButton(box.getSelectedIndex(),3);
            actionButton(box.getSelectedIndex(),4);

           // test();

            testPanels.repaint();
        } // 玎牮噱� 戾蝾�

    } // 玎牮噱� 忭篁疱眄栝 觌囫�

    //耠篪囹咫� 潆� 桉镱朦珙忄龛� JBox 耠箧栩 玎矬耜噱� 戾蝾� go2() 潆� 镥疱躅溧 � 沭箫稃 磬 沭箫矬 蜞赕� 镥疱痂耦怅� frame
    public class BoxListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            go2();
            frame.repaint();
        }
    } // 玎牮噱� 忭篁疱眄栝 觌囫�

} // 觐礤� 觌囫襦
