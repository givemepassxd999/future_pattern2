package personal.givemepass;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public class Data{
        private String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
    public static void main(String[] args) {
        Main m = new Main();
        FutureTask<Data> futureTask = m.setup();

        try {
            while(!futureTask.isDone());
            System.out.println(futureTask.get().getData());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    private FutureTask<Data> setup(){
        FutureTask<Data> future = new FutureTask<Data>(new Callable<Data>(){

            @Override
            public Data call() throws Exception {
                Data data = new Data();
                for(int i = 1; i <= 10; i++){
                    System.out.println(i * 10 + "%");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                data.setData("completed");
                return data;
            }
        });
        Thread thread = new Thread(future);
        thread.start();
        return future;
    }
}
