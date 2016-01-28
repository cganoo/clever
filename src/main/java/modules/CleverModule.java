package modules;

import api.CleverAPI;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by cganoo on 28/01/2016.
 */
public class CleverModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CleverModule.class);
        bind(String.class)
                .annotatedWith(Names.named("serviceEndpoint"))
                .toInstance("https://api.clever.com/v1.1/");
    }

    @Provides
    @Singleton
    @Named("CleverAPI")
    public CleverAPI cleverApi(@Named("retrofit") Retrofit retrofit) {
        return retrofit.create(CleverAPI.class);
    }

    @Provides
    @Singleton
    @Named("retrofit")
    public Retrofit retrofit(
            @Named("serviceEndpoint") String serviceEndpoint,
            @Named("httpClient") OkHttpClient httpClient) {
        return new Retrofit.Builder()
                .baseUrl(serviceEndpoint)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @Named("httpClient")
    public OkHttpClient httpClient() {
        return new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).build();
    }

    @Provides
    @Singleton
    @Named("gson")
    public Gson gson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setPrettyPrinting()
                .create();
    }

}
