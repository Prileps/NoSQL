import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Redis {

    private static final int USERS = 20;
    private static int paidUser;
    private static ArrayList<Integer> paidUsers = new ArrayList<>();
    private static final String SCRIPT = "На главной странице показываем пользователя ";

    public static void main(String[] args) throws InterruptedException {
        RedisStorage redisStorage = new RedisStorage();
        redisStorage.init();

        while (true) {
            for (int i = 1; i <= USERS; i++) {
                if (new Random().nextInt(10) == 0) {
                    paidUser = new Random().nextInt(20) + 1;
                    System.out.println("Пользователь " + paidUser + " оплатил платную услугу");
                    redisStorage.addUser(paidUser);
                    Thread.sleep(1000);
                    System.out.println(SCRIPT + paidUser);
                    paidUsers.add(paidUser);
                }
                if (paidUsers.contains(i)) {
                    continue;
                }
                redisStorage.addUser(i);
                System.out.println(SCRIPT + i);
                Thread.sleep(1000);
            }
            redisStorage.deleteAllUsers();
            paidUsers.clear();
        }
    }
}