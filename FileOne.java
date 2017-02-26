
    new fail 123
    private void openMetod(File file){

        try {

            FileInputStream os = new FileInputStream(file);
            ObjectInputStream st = new ObjectInputStream(os);

            tableClass[0] = st.readerObject();
            tableClass[1] = st.readerObject();
            tableClass[2] = st.readerObject();
            tableClass[3] = st.readerObject();
            tableClass[4] = st.readerObject();

        } catch (Exception e){
            System.out.println("Произошла ошибка =(");
            e.printStackTrace();
        }
    } // закрываем метод

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