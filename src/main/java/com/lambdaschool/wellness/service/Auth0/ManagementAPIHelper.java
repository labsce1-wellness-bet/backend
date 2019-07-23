package com.lambdaschool.wellness.service.Auth0;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public final class ManagementAPIHelper {
    public static HttpResponse<JsonNode> getUsersInfoFromAuth0(Set<String> auth0Ids, String queryFields) {
        String idsArr[] = new String[auth0Ids.size()];

        idsArr = auth0Ids.toArray(idsArr);
        String findUsersByIdQuery = "user_id:(";
        for (int i = 0; i < idsArr.length; i++) {
            String id = idsArr[i];
            findUsersByIdQuery = findUsersByIdQuery.concat("\"" + id + "\"");

            if (i != idsArr.length - 1) {
                findUsersByIdQuery = findUsersByIdQuery.concat(" OR ");
            }
            if (i == idsArr.length - 1) {
                findUsersByIdQuery = findUsersByIdQuery.concat(")");
            }
        }
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + System.getenv("AUTH0_MANAGEMENT_TOKEN"));
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("fields", queryFields);
        queryParams.put("q", findUsersByIdQuery);
        HttpResponse<JsonNode> jsonResponse = Unirest
        .get("https://akshay-gadkari.auth0.com/api/v2/users")
        .headers(headers)
        .queryString(queryParams)
        .asJson();

        return jsonResponse;
    }
}