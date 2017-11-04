/**
 * Created by stephan on 20.03.16.
 */

$(function () {
    // VARIABLES =============================================================
    var TOKEN_KEY = "jwtToken"
    var $notLoggedIn = $("#notLoggedIn");
    var $loggedIn = $("#loggedIn").hide();
    var $loggedInBody = $("#loggedInBody");
    var $response = $("#response");
    var $login = $("#login");
    var $userInfo = $("#userInfo").hide();

    // FUNCTIONS =============================================================
    function getJwtToken() {
        return localStorage.getItem(TOKEN_KEY);
    }

    function setJwtToken(token) {
        localStorage.setItem(TOKEN_KEY, token);
    }

    function removeJwtToken() {
        localStorage.removeItem(TOKEN_KEY);
    }

    function doLogin(loginData) {
        $.ajax({
            url: "/auth",
            type: "POST",
            data: JSON.stringify(loginData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                setJwtToken(data.token);
                $login.hide();
                $notLoggedIn.hide();
                showTokenInformation();
                showUserInformation();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    $('#loginErrorModal')
                            .modal("show")
                            .find(".modal-body")
                            .empty()
                            .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });
    }

    function doLogout() {
        removeJwtToken();
        $login.show();
        $userInfo
                .hide()
                .find("#userInfoBody").empty();
        $loggedIn.hide();
        $loggedInBody.empty();
        $notLoggedIn.show();
    }

    function createAuthorizationTokenHeader() {
        var token = getJwtToken();
        if (token) {
            return {"Authorization": token};
        } else {
            return {};
        }
    }

    function showUserInformation() {
        $.ajax({
            url: "/user",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                var $userInfoBody = $userInfo.find("#userInfoBody");

                $userInfoBody.append($("<div>").text("Username: " + data.username));
                $userInfoBody.append($("<div>").text("Phone: " + data.phone));
                $userInfoBody.append($("<div>").text("Email: " + data.email));

                var $authorityList = $("<ul>");
                data.authorities.forEach(function (authorityItem) {
                    $authorityList.append($("<li>").text(authorityItem.authority));
                });
                var $authorities = $("<div>").text("Authorities:");
                $authorities.append($authorityList);

                $userInfoBody.append($authorities);
                $userInfo.show();
            }
        });
    }

    function showTokenInformation() {
        var jwtToken = getJwtToken();
        var decodedToken = jwt_decode(jwtToken);
        console.log(decodedToken);

        $loggedInBody.append($("<h4>").text("Token"));
        $loggedInBody.append($("<div>").text(jwtToken).css("word-break", "break-all"));
        $loggedInBody.append($("<h4>").text("Token claims"));

        var $table = $("<table>")
                .addClass("table table-striped");
        appendKeyValue($table, "sub", decodedToken.sub);
        appendKeyValue($table, "audience", decodedToken.audience);
        appendKeyValue($table, "created", new Date(decodedToken.created).toString());
        appendKeyValue($table, "exp", decodedToken.exp);

        $loggedInBody.append($table);

        $loggedIn.show();
    }

    function appendKeyValue($table, key, value) {
        var $row = $("<tr>")
                .append($("<td>").text(key))
                .append($("<td>").text(value));
        $table.append($row);
    }

    function showResponse(statusCode, message) {
        $response
                .empty()
                .text("status code: " + statusCode + "\n-------------------------\n" + message);
    }

    // REGISTER EVENT LISTENERS =============================================================
    $("#loginForm").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var formData = {
            username: $form.find('input[name="username"]').val(),
            password: $form.find('input[name="password"]').val()
        };

        doLogin(formData);
    });

    $("#logoutButton").click(doLogout);

    
    $("#exampleServiceBtn").click(function () {
        $.ajax({
            url: "/persons",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#adminServiceBtn").click(function () {
        $.ajax({
            url: "/protected",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#ChoicesGetBtn").click(function() {
        $.ajax({
            url: "/options",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#ChoiceGet1Btn").click(function() {
        $.ajax({
            url: "/options/1",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#ChoiceGet2Btn").click(function() {
        $.ajax({
            url: "/options/2",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#ChoicePostBtn").click(function() {
        $.ajax({
            url: "/options",
            type: "POST",
            data: JSON.stringify({ id: 310, topic: "K", seq: 1, name: "Posted K310", value: "Posted K!"}),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#ChoicePut61Btn").click(function() {
        $.ajax({
            url: "/options/61",
            type: "PUT",
            data: JSON.stringify({ id: 61, topic: "K", seq: 1, name: "First 61 alter", value: "First 61 PUT"}),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#ChoicePut62Btn").click(function() {
        $.ajax({
            url: "/options/62",
            type: "PUT",
            data: JSON.stringify({ id: 62, topic: "K", seq: 2, name: "First 62 alter", value: "First 62 PUT"}),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OrgsGetBtn").click(function() {
        $.ajax({
            url: "/orgs",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            	
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OrgsProvidersGetBtn").click(function() {
        $.ajax({
            url: "/orgs/providers",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OrgGet1Btn").click(function() {
        $.ajax({
            url: "/orgs/1",
            type: "GET",
            headers: createAuthorizationTokenHeader(),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OrgGet2Btn").click(function() {
        $.ajax({
            url: "/orgs/2",
            type: "GET",
            headers: createAuthorizationTokenHeader(),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OrgGet23Btn").click(function() {
        $.ajax({
            url: "/orgs/23",
            type: "GET",
            headers: createAuthorizationTokenHeader(),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OrgGet24Btn").click(function() {
        $.ajax({
            url: "/orgs/24",
            type: "GET",
            headers: createAuthorizationTokenHeader(),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OrgPostBtn").click(function() {
        $.ajax({
            url: "/orgs",
            type: "POST",
            data: JSON.stringify({  provider: true, name: 'Org NEW', address1: 'Address 24',  city: 'City 24', state: 'IL', zip: '60600', phone: '312-00', email: 'emailNew', preferContactMethod: 'E', maySolicit: false, active: true }),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OrgPut1Btn").click(function() {
        $.ajax({
            url: "/orgs/1",
            type: "PUT",
            data: JSON.stringify({ id: 1, provider: false, name: 'Org 1', address1: 'Address 1',  city: 'City 1', state: 'IL', zip: '60601', phone: '312-1', email: 'email1', preferContactMethod: 'E', maySolicit: false, active: true }),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#OrgPut2Btn").click(function() {
        $.ajax({
            url: "/orgs/2",
            type: "PUT",
            data: JSON.stringify({ id: 2, provider: true, name: 'Org 2', address1: 'Address 2',  city: 'City 2', state: 'IL', zip: '60602', phone: '312-2', email: 'email2', preferContactMethod: 'E', maySolicit: false, active: true }),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#OrgPut23Btn").click(function() {
        $.ajax({
            url: "/orgs/23",
            type: "PUT",
            data: JSON.stringify({ id: 23, provider: true, name: 'Org 23', address1: 'Address 23',  city: 'City 23', state: 'IL', zip: '60623', phone: '312-23', email: 'email23', preferContactMethod: 'E', maySolicit: false, active: true }),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OrgPut24Btn").click(function() {
        $.ajax({
            url: "/orgs/24",
            type: "PUT",
            data: JSON.stringify({ id: 24, provider: true, name: 'Org 24', address1: 'Address 24',  city: 'City 24', state: 'IL', zip: '60624', phone: '312-24', email: 'email24', preferContactMethod: 'E', maySolicit: false, active: true }),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#FiltersGetBtn").click(function() {
        $.ajax({
            url: "/filters",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    $("#FavorsGetBtn").click(function() {
        $.ajax({
            url: "/filters/favors",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#TraitsGetBtn").click(function() {
        $.ajax({
            url: "/filters/traits",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#FilterGet21Btn").click(function() {
        $.ajax({
            url: "/filters/21",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#FilterGet22Btn").click(function() {
        $.ajax({
            url: "/filters/22",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#FilterGet99Btn").click(function() {
        $.ajax({
            url: "/filters/99",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#FilterPostBtn").click(function() {
        $.ajax({
            url: "/filters",
            type: "POST",
            data: JSON.stringify({ type: 'T', text: 'Trait NEW', active: true }),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#FilterPut21Btn").click(function() {
        $.ajax({
            url: "/filters/21",
            type: "PUT",
            data: JSON.stringify({ id: 21, type: 'T', text: 'Trait 21', active: true }),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#FilterPut22Btn").click(function() {
        $.ajax({
            url: "/filters/22",
            type: "PUT",
            data: JSON.stringify({ id: 22, type: 'F', text: 'Favor 22', active: true }),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OffersGetBtn").click(function() {
        $.ajax({
            url: "/offers",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OffersGetOrg1Btn").click(function() {
        $.ajax({
            url: "/offers/org/1",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OfferGet55Btn").click(function() {
        $.ajax({
            url: "/offers/55",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#OfferGet99Btn").click(function() {
        $.ajax({
            url: "/offers/99",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#OfferGetOrg1TemplateBtn").click(function() {
        $.ajax({
            url: "/offers/template/1",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#OfferPut52Btn").click(function() {
        $.ajax({
            url: "/offers/52",
            type: "PUT",
            data: JSON.stringify({ id: 52, orgId: 2, template: 0, title: 'Offer 52', address1: 'Offer 52 Address1', openEnded: 0, type: 'Other', filters: [{id: 22, type: 'J', text: 'Jeromxe', active: true }]}),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OfferPostBtn").click(function() {
        $.ajax({
            url: "/offers",
            type: "POST",
            data: JSON.stringify({ orgId: 2, template: 0, title: 'Offer 52', address1: 'Offer 52 Address1', openEnded: 0, type: 'Other', filters: [{id: 22, type: 'J', text: 'Jeromxe', active: true }]}),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#OfferSearchBtn").click(function() {
    	var sDate = (new Date("01/20/2015")).toISOString();
    	var eDate = (new Date("01/21/2015")).toISOString();
        $.ajax({
            url: "/offers/filtered",
            type: "PUT",
            data: JSON.stringify({ typeMusical: 1, typeMovie: 1, typeConcert: 1, typePlay: 1, typeOther: 0, startDate: sDate, endDate: eDate }),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#UsersGetBtn").click(function() {
        $.ajax({
            url: "/users",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#UsersGet1Btn").click(function() {
        $.ajax({
            url: "/users/1",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#UsersGet2Btn").click(function() {
        $.ajax({
            url: "/users/2",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#UsersGet3Btn").click(function() {
        $.ajax({
            url: "/users/3",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#UserPostBtn").click(function() {
        $.ajax({
            url: "/users",
            type: "POST",
            data: JSON.stringify({ orgId: 2, orgProvider: 1, userName: 'newuser', admin: 0, fullName: 'New User', phone: '333-333-3333', email: 'disabled@home.com', preferContactMethod: 'E', active: 0 }),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#UserPut2Btn").click(function() {
        $.ajax({
            url: "/users/2",
            type: "PUT",
            data: JSON.stringify({ id: 2, orgId: 1, orgProvider: 0, userName: 'user', admin: 0, fullName: 'user', phone: '111-111-1111', email: 'enabled@user.com', preferContactMethod: 'E', active: 1 }),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#UserPut3Btn").click(function() {
        $.ajax({
            url: "/users/3",
            type: "PUT",
            data: JSON.stringify({ id: 3, orgId: 2, orgProvider: 1, userName: 'disabled', admin: 0, fullName: 'disabled', phone: '333-333-3333', email: 'disabled@home.com', preferContactMethod: 'E', active: 0 }),
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#UserAssist24Btn").click(function() {
        $.ajax({
            url: "/users/assist/24",
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#AuthUsersGetBtn").click(function() {
        $.ajax({
            url: "/authUsers",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $("#AuthUsersGet1Btn").click(function() {
        $.ajax({
            url: "/authusers/1",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });
    
    $loggedIn.click(function () {
        $loggedIn
                .toggleClass("text-hidden")
                .toggleClass("text-shown");
    });

    // INITIAL CALLS =============================================================
    if (getJwtToken()) {
        $login.hide();
        $notLoggedIn.hide();
        showTokenInformation();
        showUserInformation();
    }
});