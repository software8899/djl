/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ai.djl.paddlepaddle.engine;

import ai.djl.Device;
import ai.djl.paddlepaddle.jna.JnaUtils;
import ai.djl.util.NativeResource;
import com.sun.jna.Pointer;

/**
 * This class is used to create paddlepaddle engine configuration.
 *
 * <p>Provides model path setting, forecast engine running equipment selection and multiple
 * optimization prediction processes.
 */
public class AnalysisConfig extends NativeResource<Pointer> {

    AnalysisConfig(Device device) {
        super(JnaUtils.newAnalysisConfig());
        if (device.equals(Device.cpu())) {
            JnaUtils.disableGpu(this);
        } else {
            JnaUtils.setGpu(this, 100, device.getDeviceId());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void close() {
        JnaUtils.deleteConfig(this);
        super.close();
    }
}
