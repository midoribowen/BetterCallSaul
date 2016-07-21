package com.mpbowen.bettercallsaul.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpbowen.bettercallsaul.exception.exceptions.BusinessUnavailable;
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

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ErrorHandlingInterceptor implements Interceptor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

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


        //TODO: Use Gson instead. Need to change POJOs as well


        //Jackson
        JsonNode errorJsonNode = objectMapper.readTree(responseBody).path("error");
        String id = errorJsonNode.path("id").asText();
        String errorText = errorJsonNode.path("text").asText();

        if (errorJsonNode.has("field")) {
            errorText += ": " + errorJsonNode.path("field").asText();
        }

        switch (id) {
            case "INTERNAL_ERROR":
                return new InternalError(code, errorText, id);
            case "EXCEEDED_REQS":
                return new ExceededReqs(code, errorText, id);
            case "MISSING_PARAMETER":
                return new MissingParameter(code, errorText, id);
            case "INVALID_PARAMETER":
                return new InvalidParameter(code, errorText, id);
            case "INVALID_SIGNATURE":
                return new InvalidSignature(code, errorText, id);
            case "INVALID_OAUTH_CREDENTIALS":
                return new InvalidOAuthCredentials(code, errorText, id);
            case "INVALID_OAUTH_USER":
                return new InvalidOAuthUser(code, errorText, id);
            case "UNAVAILABLE_FOR_LOCATION":
                return new UnavailableForLocation(code, errorText, id);
            case "MULTIPLE_LOCATIONS":
                return new MultipleLocations(code, errorText, id);
            case "BUSINESS_UNAVAILABLE":
                return new BusinessUnavailable(code, errorText, id);
            default:
                return new UnexpectedAPIError(code, errorText, id);
        }
    }
}
