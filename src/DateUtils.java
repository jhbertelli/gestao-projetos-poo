import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String formatarDataParaString(Date data) {
        var formatadorData = new SimpleDateFormat("dd/MM/yyyy");

        return formatadorData.format(data);
    }
}
