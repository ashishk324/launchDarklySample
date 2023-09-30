package com.launchdarkly.test.featureflag.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
public class FeatureResponseDTO {
    Map<String,Boolean>featureFlagList;
}
