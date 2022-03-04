package Tests;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class K_GET_POST_OAUTH_1_2 {

    /**
     * Most oauth v1 endpoints will have the chance to work in this restassured native way:
     *  given().accept(ContentType.JSON).auth().oauth(consumerKey, consumerSecret, accessToken, tokenSecret)
     */
    @Test
    public void oauth1WithHeaderAuthorization() {
        given()
                .header("Authorization", getOauthKey())
                .get("https://postman-echo.com/oauth1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("status", equalTo("pass"))
                .body("message", equalTo("OAuth-1.0a signature verification was successful"));
    }

    //TODO move this method to the correspondent folder
    private String getOauthKey(){
        return "OAuth " +
                "oauth_consumer_key=\"RKCGzna7bv9YD57c\"," +
                "oauth_signature_method=\"HMAC-SHA1\"," +
                "oauth_timestamp=\"1645397061\"," +
                "oauth_nonce=\"Mme6HA\"," +
                "oauth_version=\"1.0\"," +
                "oauth_signature=\"P%2BdQH1kKpfgu7%2BMCACeEodbT1gE%3D\"";
    }

    /**
     * https://www.baeldung.com/rest-assured-authentication
     */
    @Test
    public void oauth2() {
    given()
            .auth()
            .oauth2("response_type=code&client_id=M1M5R3BMVy13QmpScXkzTUt5OE46MTpjaQ&redirect_uri=https://www.example.com&scope=tweet.read%20users.read%20follows.read%20follows.write&state=state&code_challenge=challenge&code_challenge_method=plain")
            .get("https://twitter.com/i/oauth2/authorize")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getOauth2() {
        given()
                .header("Content-Type: application/x-www-form-urlencoded", "Authorization: Basic V1ROclFTMTRiVWhwTWw4M2FVNWFkVGQyTldNNk1UcGphUTotUm9LeDN4NThKQThTbTlKSXQyZm1BanEzcTVHWC1icVozdmpKeFNlR3NkbUd0WEViUA==")
                .param("code=VGNibzFWSWREZm01bjN1N3dicWlNUG1oa2xRRVNNdmVHelJGY2hPWGxNd2dxOjE2MjIxNjA4MjU4MjU6MToxOmFjOjE",
                        "grant_type=authorization_code",
                        "redirect_uri=https://www.example.com",
                        "code_verifier=challenge")
                .get("https://twitter.com/i/oauth2/authorize")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    /**
     * Sources:
     *https://developer.twitter.com/en/docs/authentication/oauth-2-0/user-access-token
     * https://developer.okta.com/blog/2017/06/21/what-the-heck-is-oauth
     * https://www.oauth.com/oauth2-servers/server-side-apps/example-flow/
     * https://www.oauth.com/oauth2-servers/access-tokens/
     * https://aaronparecki.com/oauth-2-simplified/
     * https://help.akana.com/content/current/cm/api_oauth/oauth_oauth20/m_oauth20_getTokenPOST.htm
     * https://ravthiru.medium.com/automated-testing-of-oauth2-openid-protected-api-605f8a70351f
     * https://developers.onelogin.com/api-docs/1/samples/csharp/get-token-and-users
     * https://developers.onelogin.com/api-docs/1/oauth20-tokens/generate-tokens-2
     * https://www.baeldung.com/rest-assured-header-cookie-parameter
     */
    @Test
    public void postOauth2() {
        System.out.println("testK----");
        int code = given()
                .header("Content-Type: application/x-www-form-urlencoded", "Authorization: Basic V1ROclFTMTRiVWhwTWw4M2FVNWFkVGQyTldNNk1UcGphUTotUm9LeDN4NThKQThTbTlKSXQyZm1BanEzcTVHWC1icVozdmpKeFNlR3NkbUd0WEViUA==")
                .param("code=VGNibzFWSWREZm01bjN1N3dicWlNUG1oa2xRRVNNdmVHelJGY2hPWGxNd2dxOjE2MjIxNjA4MjU4MjU6MToxOmFjOjE",
                        "grant_type=authorization_code",
                        "redirect_uri=https://www.example.com",
                        "code_verifier=challenge")
                .post("https://api.twitter.com/2/oauth2/token")
                .getStatusCode();

        code = given()
                //.header("Content-Type: application/x-www-form-urlencoded", "Authorization: Basic V1ROclFTMTRiVWhwTWw4M2FVNWFkVGQyTldNNk1UcGphUTotUm9LeDN4NThKQThTbTlKSXQyZm1BanEzcTVHWC1icVozdmpKeFNlR3NkbUd0WEViUA==")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic V1ROclFTMTRiVWhwTWw4M2FVNWFkVGQyTldNNk1UcGphUTotUm9LeDN4NThKQThTbTlKSXQyZm1BanEzcTVHWC1icVozdmpKeFNlR3NkbUd0WEViUA==")
                //.header("Basic", "V1ROclFTMTRiVWhwTWw4M2FVNWFkVGQyTldNNk1UcGphUTotUm9LeDN4NThKQThTbTlKSXQyZm1BanEzcTVHWC1icVozdmpKeFNlR3NkbUd0WEViUA==")
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", "M1M5R3BMVy13QmpScXkzTUt5OE46MTpjaQ")
                .queryParam("code_verifier", "challenge")
                .queryParam("code", "VGNibzFWSWREZm01bjN1N3dicWlNUG1oa2xRRVNNdmVHelJGY2hPWGxNd2dxOjE2MjIxNjA4MjU4MjU6MToxOmFjOjE")
                .post("https://api.twitter.com/2/oauth2/token")
                .statusCode();

        System.out.println("Code:" + code);

        String body = given()
                //.header("Content-Type: application/x-www-form-urlencoded", "Authorization: Basic V1ROclFTMTRiVWhwTWw4M2FVNWFkVGQyTldNNk1UcGphUTotUm9LeDN4NThKQThTbTlKSXQyZm1BanEzcTVHWC1icVozdmpKeFNlR3NkbUd0WEViUA==")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
                //.header("Authorization", "Basic V1ROclFTMTRiVWhwTWw4M2FVNWFkVGQyTldNNk1UcGphUTotUm9LeDN4NThKQThTbTlKSXQyZm1BanEzcTVHWC1icVozdmpKeFNlR3NkbUd0WEViUA==")
                //.header("Basic", "V1ROclFTMTRiVWhwTWw4M2FVNWFkVGQyTldNNk1UcGphUTotUm9LeDN4NThKQThTbTlKSXQyZm1BanEzcTVHWC1icVozdmpKeFNlR3NkbUd0WEViUA==")
                .queryParam("grant_type", "authorization_code")
                //.queryParam("client_id", "rG9n6402A3dbUJKzXTNX4oWHJ")
                .queryParam("client_id", "M1M5R3BMVy13QmpScXkzTUt5OE46MTpjaQ")
                .queryParam("code_verifier", "challenge")
                .queryParam("code", "VGNibzFWSWREZm01bjN1N3dicWlNUG1oa2xRRVNNdmVHelJGY2hPWGxNd2dxOjE2MjIxNjA4MjU4MjU6MToxOmFjOjE")
                .queryParam("redirect_uri", "https://www.example.com")
                .post("https://api.twitter.com/2/oauth2/token")
                .getBody().asString(); //.body().asString();


        System.out.println("Body: " +  body);
    }

    //https://developer.twitter.com/en/docs/authentication/oauth-2-0/user-access-token

    /*curl --location --request POST 'https://api.twitter.com/2/oauth2/token' \
            --header 'Content-Type: application/x-www-form-urlencoded' \
            --data-urlencode 'code=VGNibzFWSWREZm01bjN1N3dicWlNUG1oa2xRRVNNdmVHelJGY2hPWGxNd2dxOjE2MjIxNjA4MjU4MjU6MToxOmFjOjE' \
            --data-urlencode 'grant_type=authorization_code' \
            --data-urlencode 'client_id=rG9n6402A3dbUJKzXTNX4oWHJ' \
            --data-urlencode 'redirect_uri=https://www.example.com' \
            --data-urlencode 'code_verifier=challenge'*/



    /*curl --location --request POST 'https://api.twitter.com/2/oauth2/token' \
            --header 'Content-Type: application/x-www-form-urlencoded' \
            --header 'Authorization: Basic V1ROclFTMTRiVWhwTWw4M2FVNWFkVGQyTldNNk1UcGphUTotUm9LeDN4NThKQThTbTlKSXQyZm1BanEzcTVHWC1icVozdmpKeFNlR3NkbUd0WEViUA=='\
            --data-urlencode 'code=VGNibzFWSWREZm01bjN1N3dicWlNUG1oa2xRRVNNdmVHelJGY2hPWGxNd2dxOjE2MjIxNjA4MjU4MjU6MToxOmFjOjE' \
            --data-urlencode 'grant_type=authorization_code' \
            --data-urlencode 'redirect_uri=https://www.example.com' \
            --data-urlencode 'code_verifier=challenge'*/

    /*curl --location --request POST 'https://api.twitter.com/2/oauth2/token' \
            --header 'Content-Type: application/x-www-form-urlencoded' \
            --header 'Authorization: Basic V1ROclFTMTRiVWhwTWw4M2FVNWFkVGQyTldNNk1UcGphUTotUm9LeDN4NThKQThTbTlKSXQyZm1BanEzcTVHWC1icVozdmpKeFNlR3NkbUd0WEViUA=='\
            --data-urlencode 'code=VGNibzFWSWREZm01bjN1N3dicWlNUG1oa2xRRVNNdmVHelJGY2hPWGxNd2dxOjE2MjIxNjA4MjU4MjU6MToxOmFjOjE' \
            --data-urlencode 'grant_type=authorization_code' \
            --data-urlencode 'redirect_uri=https://www.example.com' \
            --data-urlencode 'code_verifier=challenge'*/


//https://developer.okta.com/blog/2017/06/21/what-the-heck-is-oauth

    /*GET https://accounts.google.com/o/oauth2/auth?scope=gmail.insert gmail.send
            &redirect_uri=https://app.example.com/oauth2/callback
            &response_type=code&client_id=812741506391
            &state=af0ifjsldkj
            */

    /*GET https://accounts.google.com/o/oauth2/auth?
    scope=openid email&
    redirect_uri=https://app.example.com/oauth2/callback&
    response_type=code&
    client_id=812741506391&
    state=af0ifjsldkj*/

    //https://developers.onelogin.com/api-docs/1/oauth20-tokens/generate-tokens-2
}
