package com.mpbowen.bettercallsaul.exception;

import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.mpbowen.bettercallsaul.Constants;
import com.mpbowen.bettercallsaul.exception.exceptions.BusinessUnavailable;
import com.mpbowen.bettercallsaul.exception.exceptions.Error;
import com.mpbowen.bettercallsaul.exception.exceptions.ExceededReqs;
import com.mpbowen.bettercallsaul.exception.exceptions.InternalError;
import com.mpbowen.bettercallsaul.exception.exceptions.InvalidOAuthCredentials;
import com.mpbowen.bettercallsaul.exception.exceptions.InvalidOAuthUser;
import com.mpbowen.bettercallsaul.exception.exceptions.InvalidParameter;
import com.mpbowen.bettercallsaul.exception.exceptions.InvalidSignature;
import com.mpbowen.bettercallsaul.exception.exceptions.MissingParameter;
import com.mpbowen.bettercallsaul.exception.exceptions.MultipleLocations;
import com.mpbowen.bettercallsaul.exception.exceptions.UnavailableForLocation;
import com.mpbowen.bettercallsaul.exception.exceptions.UnexpectedAPIError;
import com.mpbowen.bettercallsaul.exception.exceptions.YelpAPIError;
import com.mpbowen.bettercallsaul.models.SearchResponse;
import com.mpbowen.bettercallsaul.services.YelpAPI;
import com.mpbowen.bettercallsaul.services.YelpAPIFactory;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class ErrorHandlingInterceptor implements Interceptor {

    private static final Gson gson = new Gson();
//    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(chain.request());

        if (!response.isSuccessful()) {
            throw parseError(
                    response.code(),
                    response.body() != null ? response.body().string() : null
            );
        }

        return response;
    }

    public static YelpAPIError parseError(int code, String responseBody) throws IOException {

        if (responseBody == null) {
            return new UnexpectedAPIError(code);
        }

        YelpAPIError yelpAPIError = gson.fromJson(responseBody, YelpAPIError.class);
        Error error = yelpAPIError.getError();
        String errorId = error.getId();

        switch (errorId) {
            case "INTERNAL_ERROR":
                return new InternalError(code, error);
            case "EXCEEDED_REQS":
                return new ExceededReqs(code, error);
            case "MISSING_PARAMETER":
                return new MissingParameter(code, error);
            case "INVALID_PARAMETER":
                return new InvalidParameter(code, error);
            case "INVALID_SIGNATURE":
                return new InvalidSignature(code, error);
            case "INVALID_OAUTH_CREDENTIALS":
                return new InvalidOAuthCredentials(code, error);
            case "INVALID_OAUTH_USER":
                return new InvalidOAuthUser(code, error);
            case "UNAVAILABLE_FOR_LOCATION":
                return new UnavailableForLocation(code, error);
            case "MULTIPLE_LOCATIONS":
                return new MultipleLocations(code, error);
            case "BUSINESS_UNAVAILABLE":
                return new BusinessUnavailable(code, error);
            default:
                return new UnexpectedAPIError(code, error);
        }
    }
}
