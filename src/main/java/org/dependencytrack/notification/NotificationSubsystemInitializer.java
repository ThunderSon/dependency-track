/*
 * This file is part of Dependency-Track.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) Steve Springett. All Rights Reserved.
 */
package org.dependencytrack.notification;

import alpine.notification.NotificationService;
import alpine.notification.Subscription;
import org.dependencytrack.RequirementsVerifier;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Initializes the notification subsystem and configures the notification router
 *
 * @author Steve Springett
 * @since 3.2.0
 */
public class NotificationSubsystemInitializer implements ServletContextListener {

    // Starts the NotificationService
    private static final NotificationService NOTIFICATION_SERVICE = NotificationService.getInstance();

    /**
     * {@inheritDoc}
     */
    public void contextInitialized(final ServletContextEvent event) {
        if (RequirementsVerifier.failedValidation()) {
            return;
        }
        NOTIFICATION_SERVICE.subscribe(new Subscription(NotificationRouter.class));
    }

    /**
     * {@inheritDoc}
     */
    public void contextDestroyed(final ServletContextEvent event) {
        NOTIFICATION_SERVICE.shutdown();
    }
}
