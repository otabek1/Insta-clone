package  otabek.io.instagramclone;

import com.parse.Parse;
import android.app.Application;

public class ParseStarter extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("iH80B69pdGyqmngfwt6f6IKDv7EcdvIEo69ybhTI")
                .clientKey("B3M7OAMyRelu75oC2OIzzltqSqsnDr5tfd26IDGp")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}