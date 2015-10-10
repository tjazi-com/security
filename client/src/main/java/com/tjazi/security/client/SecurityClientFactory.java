package com.tjazi.security.client;

import java.net.URI;

/**
 * Created by Krzysztof Wasiak on 10/10/15.
 */

public interface SecurityClientFactory {

    SecurityClient createSecurityClient(URI targetUrl);

}
