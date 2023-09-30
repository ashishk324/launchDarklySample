package com.launchdarkly.test.featureflag.controller;

import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;
import com.launchdarkly.test.featureflag.dto.FeatureResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
public class FeatureFlagController {
    static final String FEATURE_FLAG1 = "featureFlag1";
    static final String FEATURE_FLAG2 = "featureFlag2";
    static final String FEATURE_FLAG3 = "featureFlag3";

    //TODO replace with your SDK_KEY generate on launchDarkly site
    static final String SDK_KEY = "############";

    @GetMapping("/featureFlags")
    public FeatureResponseDTO featureFlags() throws IOException {
        LDConfig config = new LDConfig.Builder().build();
        LDClient client = new LDClient(SDK_KEY, config);
        LDContext context = LDContext.builder("test-feature-flags")
                .name("Ashish")
                .build();

        boolean flagValue1 = client.boolVariation(FEATURE_FLAG1, context, false);
        boolean flagValue2 = client.boolVariation(FEATURE_FLAG2, context, false);
        boolean flagValue3 = client.boolVariation(FEATURE_FLAG3, context, false);


        showMessage("Feature flag '" + FEATURE_FLAG1 + "' is " + flagValue1 + " for this context");
        showMessage("Feature flag '" + FEATURE_FLAG2 + "' is " + flagValue2 + " for this context");
        showMessage("Feature flag '" + FEATURE_FLAG3 + "' is " + flagValue3 + " for this context");
        client.close();
        FeatureResponseDTO featureResponseDTO = new FeatureResponseDTO();
        featureResponseDTO.setFeatureFlagList(Map.of(FEATURE_FLAG1,flagValue1, FEATURE_FLAG2,flagValue2,FEATURE_FLAG3,flagValue3));
        return featureResponseDTO;
    }

    private static void showMessage(String s) {
        System.out.println("*** " + s);
        System.out.println();
    }

}
