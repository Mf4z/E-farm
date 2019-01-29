package emef4z.gmail.com.e_farm;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by USER on 15-Mar-17.
 */

public class Message {

    public static void message(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();

    }
}
