import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        String s = "2021-09-22 16:38:45";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            long l = sdf.parse(s).getTime();
            long l2 = System.currentTimeMillis();
            System.out.println(l - l2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(sdf.format(date));
    }
}
