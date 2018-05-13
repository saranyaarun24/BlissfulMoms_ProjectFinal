package com.fitbit.api.services;

import android.app.Activity;
import android.content.Loader;

import com.fitbit.api.exceptions.MissingScopesException;
import com.fitbit.api.exceptions.TokenExpiredException;
import com.fitbit.api.loaders.ResourceLoaderFactory;
import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.SleepRate;
import com.fitbit.authentication.Scope;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SleepService {

    private final static String SLEEP_URL = "https://api.fitbit.com/1.2/user/-/sleep/date/%s/%s.json";
    private static final ResourceLoaderFactory<SleepRate> SLEEP_LOG_LOADER_FACTORY = new ResourceLoaderFactory<>(SLEEP_URL, SleepRate.class);
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    public static Loader<ResourceLoaderResult<SleepRate>> getSleepLoader(Activity activityContext, Date startDate, Date endDate) throws MissingScopesException, TokenExpiredException {

        return SLEEP_LOG_LOADER_FACTORY.newResourceLoader(
                activityContext,
                new Scope[]{Scope.sleep},
                dateFormat.format(endDate),
                dateFormat.format(startDate));
    }
}
